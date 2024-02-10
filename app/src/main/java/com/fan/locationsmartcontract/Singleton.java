package com.fan.locationsmartcontract;

import android.content.Context;
import android.util.Log;

import com.fan.locationsmartcontract.contract.ConnectToContract;
import com.fan.locationsmartcontract.contract.RefundWithLoc_sol_RefundWithLoc;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;

public class Singleton {
    private final String TAG = Singleton.this.getClass().getSimpleName();
    private static Singleton mSingleton;
    private Web3j web3j;
    Context context;

    public static String GAS_PRICE = "20000000000";
    public static String GAS_LIMIT = "6721975";
    public static String contractAddress = "0x7FF76a5823AA97040539D503a9419bcC05CEdE95";
    public static String admin_private_key = "0x0da745bc9f81bae03089fb3f4cacaecbfea2d77b33816348ca3043afa5a4a00c";
    private RefundWithLoc_sol_RefundWithLoc myContract;
    public static synchronized Singleton getInstance(Context context){
        if(mSingleton == null){
            mSingleton = new Singleton(context);
        }
        return mSingleton;
    }
    private Singleton(Context context){
        this.context = context;
        initConnection();
        initContract();
    }
    private void initConnection(){
        web3j = ConnectToContract.returnWeb3Connection();
    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public RefundWithLoc_sol_RefundWithLoc getMyContract() {
        return myContract;
    }

    private void initContract(){
        Credentials credentials = Credentials.create(admin_private_key);
        BigInteger gasPrice = new BigInteger(GAS_PRICE);
        BigInteger gasLimit = new BigInteger(GAS_LIMIT);
        Log.v("Contract","Gas Price "+gasPrice);
        Log.v("Contract","Gas Limit "+gasLimit);

        connectToContract(web3j,contractAddress,credentials,gasPrice,gasLimit);
    }

    private void  connectToContract(Web3j web3j, String contractAddress, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit){

        try {
            myContract = RefundWithLoc_sol_RefundWithLoc.load(contractAddress, web3j, credentials, gasPrice, gasLimit);
            String weiToSend = "500000000000000";
            Log.v("Contract","Deposit "+myContract.deposit(new BigInteger(weiToSend)).send());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
