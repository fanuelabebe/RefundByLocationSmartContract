package com.fan.locationsmartcontract.implementaition;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;
import com.fan.locationsmartcontract.contract.RefundWithLoc_sol_RefundWithLoc;
import com.fan.locationsmartcontract.models.Account;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class FirstTimeOps {
    Context context;
    private final String TAG = FirstTimeOps.this.getClass().getSimpleName();

    public static List<Account> getAccounts(Web3j web3j){
        List<Account> accountList = new ArrayList<>();
        if(web3j != null){
            try {
                BigInteger blockNumb = web3j.ethBlockNumber().send().getBlockNumber();
                Log.v("First Time","BlockNumb "+blockNumb);
                List<String> accounts = web3j.ethAccounts().send().getAccounts();
                Log.v("First Time","Accounts "+accounts);
                for(int i = 0; i < accounts.size();i++){
                    if(i == 0) {
                        accountList.add(new Account("Admin",accounts.get(0)));
                    }else{
                        accountList.add(new Account("Employee "+i,accounts.get(i)));

                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return accountList;
    }
    public static boolean isConnected(Context context){
        if(isNetworkConnected(context)){
            if(isInternetAvailable()){
                return true;
            }
        }
        return false;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddress = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddress.equals("");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static String getAccountBalance(RefundWithLoc_sol_RefundWithLoc myContract,String address){
        String balance = "";
        try{
            balance = myContract.getAddBalance(address).send().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return balance;
    }


    public static void showToast(Context context,String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
