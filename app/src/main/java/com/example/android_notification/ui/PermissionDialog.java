package com.example.android_notification.ui;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class PermissionDialog {
    final PermissionTextProvider permissionTextProvider;
    final Boolean isPermanentlyDeclined;
    final Context context;
    final PermissionViewModel permissionViewModel;
    final DialogCallBack dialogCallBack;

    public PermissionDialog(Context context,
                            PermissionTextProvider permissionTextProvider,
                            Boolean isPermanentlyDeclined,
                            PermissionViewModel permissionViewModel,
                            DialogCallBack dialogCallBack
    ) {
        this.context = context;
        this.permissionTextProvider = permissionTextProvider;
        this.isPermanentlyDeclined = isPermanentlyDeclined;
        this.permissionViewModel = permissionViewModel;
        this.dialogCallBack = dialogCallBack;



        dialog();



    }

    private void dialog() {
        final String okBtnText;
        if(isPermanentlyDeclined){
            okBtnText = "Grant permission";
        }else {
            okBtnText = "OK";
        }
         new AlertDialog.Builder(context)
                 .setTitle("Permission required")
                 .setMessage(permissionTextProvider.getDescription(isPermanentlyDeclined))
                 .setOnDismissListener(dialog -> dialogCallBack.onDismiss())

                 .setPositiveButton(okBtnText, (dialog, which) -> {
                     if(isPermanentlyDeclined){
                         dialogCallBack.onGoToSettingClick();
                     }else {
                         dialogCallBack.onOkClick();
                     }
                 })
                 .create()
                 .show();


    }


}
