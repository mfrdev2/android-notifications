package com.example.android_notification.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MediatorLiveData;

public class PageReloaderStatusReceiver extends BroadcastReceiver {
    public static final String PAGE_LOADER_ACTION = "page_reload";
    public static final String TAG = PageReloaderStatusReceiver.class.getSimpleName();
    private static final MediatorLiveData<String> status = new MediatorLiveData<>();

    public static MediatorLiveData<String> getStatus() {
        return status;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action == null){
            return;
        }
        if(action.equalsIgnoreCase(PAGE_LOADER_ACTION)){
            status.postValue(action);
        }

    }


}
