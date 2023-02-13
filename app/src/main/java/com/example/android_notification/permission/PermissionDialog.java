package com.example.android_notification.permission;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.example.android_notification.permission.permissonInterface.PermissionTextProvider;
import com.example.android_notification.ui.DialogCallBack;

public class PermissionDialog {
    final Context context;
    final PermissionTextProvider permissionTextProvider;
    final Boolean isPermanentlyDeclined;
    final DialogCallBack dialogCallBack;

    public PermissionDialog(Context context,
                            PermissionTextProvider permissionTextProvider,
                            Boolean isPermanentlyDeclined,
                            DialogCallBack dialogCallBack
    ) {
        this.context = context;
        this.permissionTextProvider = permissionTextProvider;
        this.isPermanentlyDeclined = isPermanentlyDeclined;
        this.dialogCallBack = dialogCallBack;
        dialog();
    }

    private void dialog() {
        final String okBtnText;
        if (isPermanentlyDeclined) {
            okBtnText = "Grant permission";
        } else {
            okBtnText = "OK";
        }
        new AlertDialog.Builder(context)
                .setTitle("Permission required")
                .setMessage(permissionTextProvider.getDescription(isPermanentlyDeclined))
                .setOnDismissListener(dialog -> dialogCallBack.onDismiss())

                .setPositiveButton(okBtnText, (dialog, which) -> {
                    if (isPermanentlyDeclined) {
                        dialogCallBack.onGoToSettingClick();
                    } else {
                        dialogCallBack.onOkClick();
                    }
                })
                .create()
                .show();
    }


}
