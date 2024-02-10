package com.fan.locationsmartcontract.driver.models;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fan.locationsmartcontract.R;

public class DeliveryViewHolder extends RecyclerView.ViewHolder {
    public TextView nameText;
    public TextView incentiveText;
    public TextView dateText;
    public TextView complianceText;

    public DeliveryViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.name_tv);
        incentiveText = itemView.findViewById(R.id.init_pay_tv);
        dateText = itemView.findViewById(R.id.end_date_tv);
        complianceText = itemView.findViewById(R.id.compliance_tv);

    }
}
