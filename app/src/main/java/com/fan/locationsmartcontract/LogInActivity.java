package com.fan.locationsmartcontract;

import static com.fan.locationsmartcontract.implementaition.FirstTimeOps.isConnected;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.fan.locationsmartcontract.contract.ConnectToContract;
import com.fan.locationsmartcontract.databinding.ActivityLoginBinding;
import com.fan.locationsmartcontract.driver.ui.DriverActivity;
import com.fan.locationsmartcontract.employer.ui.EmployerActivity;
import com.fan.locationsmartcontract.implementaition.AccountsAutoCompleteAdapter;
import com.fan.locationsmartcontract.implementaition.ConfirmationDialogWithOK;
import com.fan.locationsmartcontract.implementaition.FirstTimeOps;
import com.fan.locationsmartcontract.models.Account;

import org.web3j.protocol.Web3j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogInActivity extends AppCompatActivity implements ConfirmationDialogWithOK.OnButtonClick{
    private final String TAG = LogInActivity.this.getClass().getSimpleName();
    ActivityLoginBinding binding;
    Account currentAccount;
    Web3j web3j;
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
        binding.loginB.setOnClickListener(view -> {
            if(currentAccount != null) {
                if (web3j != null) {
                    login();
                } else {
                    Toast.makeText(this, "Network not connected", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Please input address", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void login(){
        Intent intent;
        if(currentAccount.getName().equals("Admin")){
            intent = new Intent(LogInActivity.this, EmployerActivity.class);
            intent.putExtra("address",currentAccount.getAddress());
        }else{
            intent = new Intent(LogInActivity.this, DriverActivity.class);
            intent.putExtra("address",currentAccount.getAddress());
        }
        startActivity(intent);
    }

    public void setValuesToAutoCompleteText(List<Account> accounts) {
        AccountsAutoCompleteAdapter adapter = new AccountsAutoCompleteAdapter(LogInActivity.this, R.layout.accounts_list_item, accounts);
        binding.addEt.setThreshold(0);
        //Set the adapter
        binding.addEt.setAdapter(adapter);

        binding.addEt.setOnItemClickListener((parent, arg1, pos, id) -> {
            currentAccount = adapter.mFilteredList.get(pos);
            binding.addEt.setText(currentAccount.getAddress());
        });
    }



    public void doWeb3Stuff(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            boolean isOnline = isConnected(LogInActivity.this);
            if(isOnline){
                web3j = Singleton.getInstance(LogInActivity.this).getWeb3j();
                Log.v(TAG,"Web3 "+web3j);
                if(web3j != null){
                    List<Account> accounts = FirstTimeOps.getAccounts(web3j);
                    Log.v(TAG,"Accounts "+accounts);

                    handler.post(()->{
                        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
                        setValuesToAutoCompleteText(accounts);
                    });
                }
            }else{
                handler.post(()->{
                    Toast.makeText(this, "No network", Toast.LENGTH_SHORT).show();
                });
            }

        });
    }

    @Override
    public void OnOkButtonClick() {

    }
}