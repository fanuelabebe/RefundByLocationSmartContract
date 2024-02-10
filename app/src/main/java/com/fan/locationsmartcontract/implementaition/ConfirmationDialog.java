package com.fan.locationsmartcontract.implementaition;

import android.content.Context;

import com.fan.locationsmartcontract.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class ConfirmationDialog {
    Context context;
    int code;
    public OnButtonClick onButtonClick;

    public ConfirmationDialog(Context context, int code){
        this.context = context;
        this.code = code;
    }

    public interface OnButtonClick {
        void OnAcceptedClick(int code);
        void OnDeclinedClick(int code);
    }
    public void createDialog(String task){
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
        materialAlertDialogBuilder.setTitle(context.getResources().getString(R.string.app_name));
        materialAlertDialogBuilder.setIcon(R.drawable.green_check_circle_24);
        materialAlertDialogBuilder.setMessage(context.getResources().getString(R.string.are_you_sure)+ task);
        materialAlertDialogBuilder.setNegativeButton(context.getResources().getString(R.string.decline), (dialog, which) -> {
            onButtonClick.OnAcceptedClick(code);
//                Toast.makeText(context,"Declined",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        materialAlertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.accept), (dialog, which) -> {
            onButtonClick.OnDeclinedClick(code);
//                Toast.makeText(context,"Accepted",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        materialAlertDialogBuilder.setCancelable(false);
        materialAlertDialogBuilder.create();
        materialAlertDialogBuilder.show();
    }

}
