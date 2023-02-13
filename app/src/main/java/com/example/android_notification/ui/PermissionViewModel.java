package com.example.android_notification.ui;

import android.Manifest;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;

public class PermissionViewModel extends AndroidViewModel {
    final ObservableArrayList<String> visiblePermissionDialogQueue = new ObservableArrayList<>();

    public PermissionViewModel(@NonNull Application application) {
        super(application);
        visiblePermissionDialogQueue.add( Manifest.permission.CALL_PHONE);
    }

    public void dismissDialog() {
      visiblePermissionDialogQueue.remove(0);
    }

    public void onPermissionResult(
            String permission,
            Boolean isGranted
    ) {
        if(!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            visiblePermissionDialogQueue.add(permission);
        }
    }
}
