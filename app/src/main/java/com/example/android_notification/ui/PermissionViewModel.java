package com.example.android_notification.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.ArrayList;
import java.util.List;

public class PermissionViewModel extends AndroidViewModel {
    public static final String TAG = PermissionViewModel.class.getSimpleName();
    private final List<String> permissions;
    private final MediatorLiveData<List<String>> visiblePermissionDialogQueue;

    public PermissionViewModel(@NonNull Application application) {
        super(application);
        visiblePermissionDialogQueue = new MediatorLiveData<>();
        permissions = new ArrayList<>();
        visiblePermissionDialogQueue.setValue(permissions);
    }

    public void dismissDialog() {
        Log.d(TAG, "dismissDialog: "+permissions);
        if(permissions.isEmpty()){
           return;
        }
        permissions.remove(0);
        visiblePermissionDialogQueue.setValue(permissions);
    }

    public void onPermissionResult(
            String permission,
            Boolean isGranted
    ) {
        Log.d(TAG, "onPermissionResult: "+permission);
        Log.d(TAG, "onPermissionResult: "+isGranted);
        if (!isGranted && !permissions.contains(permission)) {
            permissions.add(permission);
            visiblePermissionDialogQueue.setValue(permissions);
        }
        Log.d(TAG, "onPermissionResult: "+permissions);
    }


    public MediatorLiveData<List<String>> getVisiblePermissionDialogQueue() {
        return visiblePermissionDialogQueue;
    }


}
