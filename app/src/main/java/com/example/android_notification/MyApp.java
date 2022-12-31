package com.example.android_notification;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

import com.example.android_notification.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyApp extends Application {
    private static final String TAG = MyApp.class.getSimpleName();
    private  NotificationManagerCompat notificationManagerCompat;
    private static MyApp myApp;
    @Override
    public void onCreate() {
        super.onCreate();
       NotificationUtils.createNotificationChannels(this);
       myApp = this;
       initFCM();
       initNotificationManager(this);
    }

    public static MyApp getMyApp() {
        return myApp;
    }

    private void initFCM(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();

                    // Log and toast
                    String msg = "FCM Token :"+token;
                    Log.d(TAG, msg);
                  //  Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                });
    }

    private void initNotificationManager(Context context) {
        this.notificationManagerCompat = NotificationManagerCompat.from(context);
    }

    public NotificationManagerCompat getNotificationManagerCompat() {
        return notificationManagerCompat;
    }
}
