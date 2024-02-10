package com.fan.locationsmartcontract.implementaition;

import android.content.Context;

import com.fan.locationsmartcontract.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ConfirmationDialogWithOK {
    Context context;
    int resId;
    public OnButtonClick onButtonClick;

    public ConfirmationDialogWithOK(Context context, int resId){
        this.context = context;
        this.resId = resId;
    }

    public interface OnButtonClick {
        void OnOkButtonClick();
    }
    public void createDialog(String message){
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
        materialAlertDialogBuilder.setTitle(context.getResources().getString(R.string.app_name));
        materialAlertDialogBuilder.setIcon(resId);
        materialAlertDialogBuilder.setMessage(message);
        materialAlertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.ok), (dialog, which) -> {
            if(onButtonClick != null) {
                onButtonClick.OnOkButtonClick();
            }
            dialog.dismiss();
        });
        materialAlertDialogBuilder.setCancelable(false);
        materialAlertDialogBuilder.create();
        materialAlertDialogBuilder.show();
    }
}
