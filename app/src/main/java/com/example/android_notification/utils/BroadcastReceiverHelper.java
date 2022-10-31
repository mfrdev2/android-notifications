package com.example.android_notification.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BroadcastReceiverHelper {

    public static void sendBroadCastData(final Context mContext,final Intent broadcastIntent) {
        mContext.sendBroadcast(broadcastIntent);
    }

    public static void registerBroadCastReceiver(final Context mContext,final BroadcastReceiver broadcastReceiver,final IntentFilter intentFilter){
        mContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void unregisterReceiver(final Context mContext,final BroadcastReceiver broadcastReceiver){
        mContext.unregisterReceiver(broadcastReceiver);
    }

}
