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

    private final MediatorLiveData<String> visiblePermissionDialogQueue;

    public PermissionViewModel(@NonNull Application application) {
        super(application);
        visiblePermissionDialogQueue = new MediatorLiveData<>();
    }

    public void dismissDialog() {
        visiblePermissionDialogQueue.setValue("");
    }

    public void onPermissionResult(
            String permission,
            Boolean isGranted
    ) {

        if (!isGranted) {
            visiblePermissionDialogQueue.setValue(permission);
        }
    }


    public MediatorLiveData<String> getVisiblePermissionDialogQueue() {
        return visiblePermissionDialogQueue;
    }


}
