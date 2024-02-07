package com.fan.locationsmartcontract.driver.implementation;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.text.DecimalFormat;

public class LocationManager extends Service implements LocationListener {
    private final String TAG = LocationManager.this.getClass().getSimpleName();
    private Context mContext;

    // flag for GPS status
    boolean isGPSEnabled = false;
    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000;

    // Declaring a Location Manager
    protected android.location.LocationManager locationManager;
    public LocationManager(){

    }
    public LocationManager(Context context) {
        this.mContext = context;
        getLocation();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Location getLocation() {
        try {

            locationManager = (android.location.LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);

            if(!isGPSEnabled && !isNetworkEnabled &&
                    ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled ) {

                    locationManager.requestLocationUpdates(
                            android.location.LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d(TAG, "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(android.location.LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {

                        assert locationManager != null;
                        locationManager.requestLocationUpdates(
                                android.location.LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d(TAG, "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(android.location.LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                Log.v(TAG,"GPS Data"+latitude+" , "+longitude);
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(LocationManager.this);
        }
    }

    /**
     * Function to get latitude
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }


    @Override
    public void onLocationChanged(Location location) {

        DecimalFormat formatter = new DecimalFormat("#.#####");
        formatter.setMaximumFractionDigits(5);
        formatter.setMinimumFractionDigits(5);
        this.location = location;

        double latitude = this.getLatitude();

        double longitude = this.getLongitude();
        Log.v(TAG,"GPSL"+latitude+" , "+longitude);
        //Toast.makeText(mContext, "LOCATION CHANGED - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show();



    }


    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }



}
