package com.example.android_notification;

import android.app.Application;

import com.example.android_notification.utils.NotificationUtils;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       NotificationUtils.createNotificationChannels(this);
    }
}
