package com.fan.locationsmartcontract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.fan.locationsmartcontract.contract.ConnectToContract;
import com.fan.locationsmartcontract.contract.TruffleContract_sol_TruffleContract;
import com.fan.locationsmartcontract.databinding.ActivityLoginBinding;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogInActivity extends AppCompatActivity {
    private final String TAG = LogInActivity.this.getClass().getSimpleName();
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListenersToViews();

    }

    public void setListenersToViews(){
        binding.connectB.setOnClickListener(view -> {
            doWeb3Stuff();
        });
    }

    public void connectAndDoOp(){
        Web3j web3j = ConnectToContract.returnWeb3Connection();
        Log.v(TAG,"Conn "+web3j);
        if(web3j != null){
            try {
                BigInteger blockNumb = web3j.ethBlockNumber().send().getBlockNumber();
                Log.v(TAG,"BlockNumb "+blockNumb);
//                EthGasPrice gasPrice = web3j.ethGasPrice().send();
//
//                Log.v(TAG,"Gas Price "+gasPrice.getGasPrice());

                String account = web3j.ethAccounts().send().getAccounts().get(0);
                Credentials credentials = Credentials.create(account);
                String contractAddress = "0x5dC44F36Ac8c18c118ca6A5F2F37F1Fae2D0a2cB"; //The deployed contract address, taken from truffle console or ganache logs
                BigInteger gasPrice = new BigInteger("20000000000");
                BigInteger gasLimit = new BigInteger("6721975");
                TruffleContract_sol_TruffleContract myContract = TruffleContract_sol_TruffleContract.load(contractAddress, web3j, credentials, gasPrice, gasLimit);

//Now you can call methods
                Log.v(TAG,"Contract "+myContract.print().send());


            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public boolean isConnected(){
        if(isNetworkConnected()){
            if(isInternetAvailable()){
                return true;
            }
        }
        return false;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) LogInActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddress = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddress.equals("");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void doWeb3Stuff(){
//        DialogOperations.showNoTextLoadingLayout(noTextLoadingLayout);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            boolean isOnline = isConnected();
            if(isOnline){
                connectAndDoOp();
            }else{
                handler.post(()->{
                    Toast.makeText(this, "No network", Toast.LENGTH_SHORT).show();
                });
            }

        });
    }
}