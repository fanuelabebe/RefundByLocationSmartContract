package com.fan.locationsmartcontract.employer.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.fan.locationsmartcontract.LogInActivity;
import com.fan.locationsmartcontract.R;
import com.fan.locationsmartcontract.Singleton;
import com.fan.locationsmartcontract.contract.RefundWithLoc_sol_RefundWithLoc;
import com.fan.locationsmartcontract.databinding.ActivityAddDeliveryBinding;
import com.fan.locationsmartcontract.driver.ui.DriverActivity;
import com.fan.locationsmartcontract.employer.implementation.DatePickerLayout;
import com.fan.locationsmartcontract.implementaition.AccountsAutoCompleteAdapter;
import com.fan.locationsmartcontract.implementaition.FirstTimeOps;
import com.fan.locationsmartcontract.models.Account;
import com.google.android.gms.maps.model.LatLng;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddDeliveryActivity extends AppCompatActivity implements DatePickerLayout.OnDateSelect{
    private final String TAG = AddDeliveryActivity.this.getClass().getSimpleName();

    ActivityAddDeliveryBinding binding;
    Account currentAccount;
    Web3j web3j;
    LatLng currentLoc;
    Handler handler;
    ExecutorService executor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDeliveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        web3j = Singleton.getInstance(this).getWeb3j();
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        setListenerToViews();
        setViewData();

    }

    public void setViewData(){

        executor.execute(() ->{
            List<Account> accounts = FirstTimeOps.getAccounts(web3j);
            if(!accounts.isEmpty()){
                handler.post(()->{
                    setValuesToAutoCompleteText(accounts);
                });

            }
        });


    }

    public void setListenerToViews(){
        binding.topAppBar.setNavigationOnClickListener(view -> {
            this.finish();
        });
        binding.locEt.setOnClickListener(view ->{
            goToMap();
        });
        binding.addB.setOnClickListener(view ->{
            addDeliverySession();
        });
    }

    public void addDeliverySession(){
        String name = binding.nameET.getText().toString();
        String address = binding.addET.getText().toString();
//        BigInteger endTime = new BigInteger(binding.endTimeET.getText().toString());
//        BigInteger lat = new BigInteger(String.valueOf(currentLoc.latitude * 1000000));
//        BigInteger lon = new BigInteger(String.valueOf(currentLoc.longitude * 1000000));
//        BigInteger distance = new BigInteger(binding.distanceET.getText().toString());
//        BigInteger initialPay = new BigInteger(binding.initPayET.getText().toString());
//        BigInteger incentivePay = new BigInteger(binding.incentPayET.getText().toString());

        executor.execute(() ->{
            RefundWithLoc_sol_RefundWithLoc myContract = Singleton.getInstance(AddDeliveryActivity.this).getMyContract();
            try {
//            TransactionReceipt response = myContract.startDelivery(address,name,distance,endTime,lat,lon,initialPay,incentivePay).send();
                TransactionReceipt response = myContract.startDelivery(address,"new Delivery",new BigInteger("12"),new BigInteger("121223424")
                        ,new BigInteger("9016676"),new BigInteger("38765347"),new BigInteger("10000000000000000000"),new BigInteger("5000000000000000000")).send();
                List<org.web3j.protocol.core.methods.response.Log> logs = response.getLogs();
                if(logs != null && !logs.isEmpty()){
                    org.web3j.protocol.core.methods.response.Log log = logs.get(0);
                    String data =  log.getData();
                    Log.v(TAG,"data "+data);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });


    }


    public void setValuesToAutoCompleteText(List<Account> accounts) {
        AccountsAutoCompleteAdapter adapter = new AccountsAutoCompleteAdapter(AddDeliveryActivity.this, R.layout.accounts_list_item, accounts);
        binding.addET.setThreshold(0);
        //Set the adapter
        binding.addET.setAdapter(adapter);

        binding.addET.setOnItemClickListener((parent, arg1, pos, id) -> {
            currentAccount = adapter.mFilteredList.get(pos);
            binding.addET.setText(currentAccount.getAddress());
        });
    }
    public void goToMap(){
        if(ActivityCompat.checkSelfPermission(AddDeliveryActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(AddDeliveryActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocPermission();
        }else{
            goToMapActivity();
        }
    }
    public void goToMapActivity(){
        Intent intent = new Intent(AddDeliveryActivity.this, MapActivity.class);
        intent.putExtra("type", 2);

        mapActivityResultLauncher.launch(intent);
    }

    public void requestLocPermission(){
        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });

    }
    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_FINE_LOCATION, false);
                        Boolean coarseLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_COARSE_LOCATION,false);

                        if (fineLocationGranted != null && fineLocationGranted) {
                            goToMapActivity();
                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
                            // Only approximate location access granted.
                        } else {
                            // No location access granted.
                        }
                    }
            );

    ActivityResultLauncher<Intent> mapActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK){
                    Intent data = result.getData();
                    if(data != null) {
                        currentLoc = (LatLng) data.getExtras().getParcelable("Location");
                        if(currentLoc != null){
                           String locText = currentLoc.latitude+ ", "  + currentLoc.longitude;
                            binding.locEt.setText(locText);
                        }

                    }
                }

            });

    public void openDatePicker(){
        DatePickerLayout datePickerLayout = new DatePickerLayout(this,false);
        datePickerLayout.onDateSelect = this;
        datePickerLayout.startDatePickerDialog("Choose Date");
    }

    @Override
    public void OnDateSelected(Date date) {

    }

    @Override
    public void OnDateRangeSelected(Date startDate, Date endDate) {

    }
}