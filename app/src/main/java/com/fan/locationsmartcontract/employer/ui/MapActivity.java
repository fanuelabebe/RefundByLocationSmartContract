package com.fan.locationsmartcontract.employer.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.fan.locationsmartcontract.BuildConfig;
import com.fan.locationsmartcontract.R;
import com.fan.locationsmartcontract.databinding.ActivityMapBinding;
import com.fan.locationsmartcontract.driver.implementation.LocationManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapActivity extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback {
    GoogleMap map;
    ArrayList<LatLng> markerPoints;
    LatLng desLatLon;
    LatLng gpsLatLon;
    LatLng currentLoc;
    public String MY_API_KEY = BuildConfig.MapApiKey;
    int mType;
    Handler handler;
    int count = 0;
    double mLat;
    double mLon;
    int type;
    LocationManager gps;
    String lat;
    String lon;
    List<LatLng> mapPoints;
    ActivityMapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        getWindow().setLayout(1500, ViewGroup.LayoutParams.WRAP_CONTENT);

        //added a comment

        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        mapPoints = (List<LatLng>) intent.getSerializableExtra("Location");

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(fm != null) {
            fm.getMapAsync(this);
        }

        markerPoints = new ArrayList<LatLng>();

        gps = new LocationManager(MapActivity.this);


        handler = new Handler();

        setOnClickToViews();

        // trigger first time


    }

    public void startMapOps(){
        final Runnable runnable = new Runnable() {
            public void run() {

                // need to do tasks on the UI thread
                Log.d("", "runn test");
                Log.v("GPSL0001", "" + gps.getLatitude() + " , " + gps.getLongitude());
                if(String.valueOf(gps.getLatitude()).equals("0.0") && String.valueOf(gps.getLongitude()).equals("0.0")) {
                    if (count++ < 5)
                        handler.postDelayed(this, 5000);
                }else{
                    mLat = gps.getLatitude();
                    mLon = gps.getLongitude();
                    currentLoc = new LatLng(mLat, mLon);


                    if (!isGooglePlayServicesAvailable()) {
                        finish();
                    }

                    if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    map.setMyLocationEnabled(true);
                    map.getUiSettings().setMyLocationButtonEnabled(true);
                    map.setOnMyLocationButtonClickListener(MapActivity.this);

                    if(mapPoints != null && !mapPoints.isEmpty()){
                        currentLoc = mapPoints.get(0);
                    }


                    map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            // Creating a marker
                            MarkerOptions markerOptions = new MarkerOptions();

                            //   Setting the position for the marker
                            markerOptions.position(latLng);

                            // Setting the title for the marker.
                            // This will be displayed on taping the marker
                            currentLoc  = latLng;
                            Log.v("MapMarker: ","Lat: "+latLng.latitude + " Long: "+latLng.longitude);

                            markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                            // Clears the previously touched position
                            map.clear();

                            // Animating to the touched position
                            map.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                            // Placing a marker on the touched position
                            map.addMarker(markerOptions);
                        }
                    });
                    drawSingleMarker();
                }

            }
        };
        handler.post(runnable);
    }
    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    public void setOnClickToViews(){
        binding.iconButton.setOnClickListener(view ->{
            this.finish();
        });
        binding.locSaveB.setOnClickListener(view ->{
            doLocationSaveClick();
        });
    }
    public void doLocationSaveClick(){

//        File file = SplashScreen.createActivityAndUpload(2,currentLoc.latitude,currentLoc.longitude);

        Intent resultData = new Intent();
        resultData.putExtra("Location", currentLoc);
        setResult(RESULT_OK, resultData);
        finish();

    }
    void drawSingleMarker(){
        if(map!=null) {
            map.clear();


            Marker current = map.addMarker(new MarkerOptions()
                    .position(currentLoc)
//                    .title("You are Here")
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            current.showInfoWindow();
//            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
            CameraUpdate center =
                    CameraUpdateFactory.newLatLngZoom(currentLoc,14f);


            map.moveCamera(center);
//            map.animateCamera(zoom);
            map.animateCamera(center);
        }
    }
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        LocationManager gps = new LocationManager(MapActivity.this);
        LatLng loc = new LatLng(gps.getLatitude(),gps.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();

        //   Setting the position for the marker
        markerOptions.position(loc);

        // Setting the title for the marker.
        // This will be displayed on taping the marker

        currentLoc = loc;
        markerOptions.title(loc.latitude + " : " + loc.longitude);

        // Clears the previously touched position
        map.clear();

        // Animating to the touched position
        map.animateCamera(CameraUpdateFactory.newLatLng(loc));

        // Placing a marker on the touched position
        map.addMarker(markerOptions);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        startMapOps();
    }

}
