package com.example.android_notification.permission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.android_notification.permission.permissionInterfaceImp.CameraPermissionTextProvider;
import com.example.android_notification.permission.permissionInterfaceImp.DefaultPermissionTextProvider;
import com.example.android_notification.permission.permissionInterfaceImp.NotificationPermissionTextProvider;
import com.example.android_notification.permission.permissionInterfaceImp.PhoneCallPermissionTextProvider;
import com.example.android_notification.permission.permissionInterfaceImp.RecordAudioPermissionTextProvider;
import com.example.android_notification.permission.permissonInterface.PermissionTextProvider;

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

    public PermissionTextProvider getPermissionProvider(String permission) {
        Log.d(TAG, "getPermissionProviderFor: "+permission);
        switch (permission) {
            case Manifest.permission.CAMERA: {
                return new CameraPermissionTextProvider();
            }
            case Manifest.permission.RECORD_AUDIO: {
                return new RecordAudioPermissionTextProvider();
            }
            case Manifest.permission.CALL_PHONE: {
                return new PhoneCallPermissionTextProvider();
            }
            case Manifest.permission.POST_NOTIFICATIONS: {
                return new NotificationPermissionTextProvider();
            }
        }
        return new DefaultPermissionTextProvider();
    }

}
