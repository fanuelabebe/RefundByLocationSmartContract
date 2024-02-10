package com.fan.locationsmartcontract.employer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.fan.locationsmartcontract.R;
import com.fan.locationsmartcontract.databinding.ActivityEmployerBinding;

import jnr.ffi.annotations.In;

public class EmployerActivity extends AppCompatActivity {
    ActivityEmployerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListenerToViews();
    }

    public void setListenerToViews(){
        binding.addB.setOnClickListener(view ->{
            startActivity(new Intent(this, AddDeliveryActivity.class));
        });
    }
}