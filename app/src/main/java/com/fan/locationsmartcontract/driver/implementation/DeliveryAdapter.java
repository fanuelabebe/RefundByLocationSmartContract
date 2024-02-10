package com.fan.locationsmartcontract.driver.implementation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fan.locationsmartcontract.R;
import com.fan.locationsmartcontract.driver.models.DeliveryData;
import com.fan.locationsmartcontract.driver.models.DeliveryViewHolder;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryViewHolder> {
    Context mContext;
    public List<DeliveryData> mDeliveryDataList;
    public OnItemClick onItemClick;
    public interface OnItemClick{
        void onItemClick(DeliveryData deliveryData);
    }
    public DeliveryAdapter(Context mContext, List<DeliveryData> deliveryDataList) {
        this.mContext = mContext;
        this.mDeliveryDataList = deliveryDataList;
    }

    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewV = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_delivery_list_item, parent, false);

        return new DeliveryViewHolder(viewV);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder holder, int position) {
        DeliveryData deliveryData = mDeliveryDataList.get(position);
        holder.nameText.setText(deliveryData.getName());
        holder.incentiveText.setText(deliveryData.getIncentive());
        holder.dateText.setText(deliveryData.getDate());
        holder.complianceText.setText(deliveryData.getCompliance());
        holder.itemView.setOnClickListener(view ->{
            onItemClick.onItemClick(deliveryData);
        });
    }

    @Override
    public int getItemCount() {
        return mDeliveryDataList.size();
    }
}
