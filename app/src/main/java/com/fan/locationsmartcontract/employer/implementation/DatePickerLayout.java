package com.fan.locationsmartcontract.employer.implementation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DatePickerLayout {
    Activity activity;
    AlertDialog alertDialog;
    TextView titleText;
    public OnDateSelect onDateSelect;
    public boolean isRange;
    public static SimpleDateFormat datePickerDate = new SimpleDateFormat("EE, MMM dd, yyyy", Locale.US);
    public interface OnDateSelect{
        void OnDateSelected(Date date);
        void OnDateRangeSelected(Date startDate,Date endDate);
    }
    public DatePickerLayout(Activity activity, boolean isRange) {
        this.activity = activity;
        this.isRange = isRange;
    }

    public void startDatePickerDialog(String title){

        if(!isRange) {
            MaterialDatePicker.Builder<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();
            materialDateBuilder.setTitleText(title);

            // now create the instance of the material date
            // picker
            final MaterialDatePicker<Long> materialDatePicker = materialDateBuilder.build();
            materialDatePicker.addOnPositiveButtonClickListener(
                    selection -> {
                        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                        calendar.setTimeInMillis(selection);
                        onDateSelect.OnDateSelected(calendar.getTime());
                    });
            materialDatePicker.show(((AppCompatActivity)activity).getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        }else{
            MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();
            materialDateBuilder.setTitleText(title);

            // now create the instance of the material date
            // picker
            final MaterialDatePicker<Pair<Long, Long>> materialDatePicker = materialDateBuilder.build();
            materialDatePicker.addOnPositiveButtonClickListener(
                    selection -> {
                        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                        long startDateL = selection.first;
                        calendar.setTimeInMillis(startDateL);
                        Date startDate = calendar.getTime();

                        long endDateL = selection.second;
                        calendar.setTimeInMillis(endDateL);
                        Date endDate = calendar.getTime();

                        onDateSelect.OnDateRangeSelected(startDate,endDate);
                    });
            materialDatePicker.show(((AppCompatActivity)activity).getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        }

        // now define the properties of the
        // materialDateBuilder that is title text as SELECT A DATE


    }

}
