package com.fan.locationsmartcontract.driver.ui;


import static com.fan.locationsmartcontract.implementaition.FirstTimeOps.isConnected;
import static com.fan.locationsmartcontract.implementaition.FirstTimeOps.showToast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.fan.locationsmartcontract.LogInActivity;
import com.fan.locationsmartcontract.R;
import com.fan.locationsmartcontract.Singleton;
import com.fan.locationsmartcontract.contract.RefundWithLoc_sol_RefundWithLoc;
import com.fan.locationsmartcontract.databinding.ActivityDriverBinding;
import com.fan.locationsmartcontract.driver.implementation.DeliveryAdapter;
import com.fan.locationsmartcontract.driver.models.DeliveryData;
import com.fan.locationsmartcontract.employer.ui.AddDeliveryActivity;
import com.fan.locationsmartcontract.implementaition.FirstTimeOps;
import com.fan.locationsmartcontract.models.Account;
import com.google.android.gms.drive.Drive;

import org.web3j.abi.Utils;
import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DriverActivity extends AppCompatActivity implements DeliveryAdapter.OnItemClick{
    private final String TAG = DriverActivity.this.getClass().getSimpleName();
    ActivityDriverBinding binding;
    Web3j web3j;
    DeliveryAdapter adapter;
    String address;
    Handler handler;
    ExecutorService executor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDriverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();
        address = intent.getStringExtra("address");
        web3j = Singleton.getInstance(this).getWeb3j();

        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        setListenersToViews();
        setValuesToViews();
    }

    public BigInteger estimateGas() throws IOException {

        EthGasPrice gas=web3j.ethGasPrice().send();
        return gas.getGasPrice();
    }
    public void setValuesToViews(){
        executor.execute(() -> {
            boolean isOnline = isConnected(DriverActivity.this);
            if(isOnline) {
                RefundWithLoc_sol_RefundWithLoc myContract = Singleton.getInstance(DriverActivity.this).getMyContract();
                if (myContract != null) {
                    String balance = FirstTimeOps.getAccountBalance(myContract, address);
                    if (balance != null) {
                        handler.post(() -> {
                            String balanceT = Convert.fromWei(balance, Convert.Unit.ETHER) + "ETH";
                            binding.accTv.setText(balanceT);
                        });
                    } else {
                        handler.post(() -> {

                            showToast(DriverActivity.this, "Balance not found");
                        });
                    }
                    Log.v(TAG,"balance: "+balance);
                    List<DeliveryData> deliveryDataList = getDeliveryData(myContract, address);
                    if(!deliveryDataList.isEmpty()){
                        handler.post(() -> {
                            updateTransactionList(deliveryDataList);
                        });
                    }


                }
            }else{
                handler.post(() -> {

                    showToast(DriverActivity.this, "No network");
                });
            }
        });

    }

    public List<DeliveryData> getDeliveryData(RefundWithLoc_sol_RefundWithLoc myContract,String address){
        List<DeliveryData> deliveryDataList = new ArrayList<>();
        try {
            List<RefundWithLoc_sol_RefundWithLoc.Delivery> response = (List<RefundWithLoc_sol_RefundWithLoc.Delivery>) myContract.getDriverDeliveryHistory(address).send();
            Log.v(TAG,"List "+response.get(0));
            if(!response.isEmpty()){
                for(RefundWithLoc_sol_RefundWithLoc.Delivery del :response){
                    DeliveryData deliveryData = new DeliveryData();
                    deliveryData.setName(del.name);
                    deliveryData.setDate(String.valueOf(del.endtime));
                    deliveryData.setCompliance(String.valueOf(del.compliant));
                    deliveryData.setIncentive(Convert.fromWei(String.valueOf(del.incentivePayWei), Convert.Unit.ETHER) + " ETH");
                    deliveryDataList.add(deliveryData);
                }
            }
//            List<org.web3j.protocol.core.methods.response.Log> logs = response.getLogs();
//            if(logs != null && !logs.isEmpty()){
//                org.web3j.protocol.core.methods.response.Log log = logs.get(0);
//                String data =  log.getData();
//                Log.v(TAG,"data"+data);
//                List<String> topics =  log.getTopics();
//                Log.v(TAG,"topics"+topics);
//
//                String raw =  log.getTransactionIndexRaw();
//                Log.v(TAG,"raw"+raw);
//
//                String blockedHash =  log.getBlockHash();
//                Log.v(TAG,"blockedHash"+blockedHash);
//
////                String blockedHash =  log.get();
////                Log.v(TAG,"blockedHash"+blockedHash);
//
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return deliveryDataList;
    }


    public void updateTransactionList(List<DeliveryData> deliveryDataList){
        adapter = new DeliveryAdapter(DriverActivity.this,deliveryDataList);
        adapter.onItemClick = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.deliveryRv.setLayoutManager(linearLayoutManager);
        binding.deliveryRv.setAdapter(adapter);
    }

    public void setListenersToViews(){
        binding.topAppBar.setNavigationOnClickListener(view -> {
            this.finish();
        });
        binding.checkB.setOnClickListener(view -> {
        });
    }

    @Override
    public void onItemClick(DeliveryData deliveryData) {

    }
}