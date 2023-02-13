package com.example.android_notification.permission;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class PermissionUtil {
    private static final String TAG = PermissionUtil.class.getSimpleName();
    private final Context context;

    private final MediatorLiveData<String> visiblePermissionDialogQueue;

    public PermissionUtil(@NonNull Context context) {
        this.context = context;
        this.visiblePermissionDialogQueue = new MediatorLiveData<>();
    }

    public void dismissDialog() {
        visiblePermissionDialogQueue.setValue("");
    }

    public void onPermissionResult(String permission, Boolean isGranted) {
        if (!isGranted) {
            visiblePermissionDialogQueue.setValue(permission);
        }
    }

    public void openAppSettings() {
        Intent aPackage = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", context.getPackageName(), null)
        );
        context.startActivity(aPackage);
    }

    public LiveData<String> getDialogQueue() {
        return visiblePermissionDialogQueue;
    }

}
