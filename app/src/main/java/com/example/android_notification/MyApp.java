package com.example.android_notification;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.android_notification.utils.NotificationUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyApp extends Application {
    private static final String TAG = MyApp.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
       NotificationUtils.createNotificationChannels(this);
       initFCM();
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
}
