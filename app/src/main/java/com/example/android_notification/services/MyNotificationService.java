package com.example.android_notification.services;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

public class MyNotificationService extends Service {
    private static final String TAG = MyNotificationService.class.getSimpleName();
    public MyNotificationService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"WE ARE NOW IN "+TAG);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"WE ARE NOW IN  onStartCommand");


        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Data")
                        .setMessage("This is someThing")
                        .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                            Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                        })
                        .setPositiveButton(android.R.string.cancel, (dialogInterface, i) -> {
                            Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
                        }).create();
                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                alertDialog.show();

            }
        });



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"WE ARE NOW IN : onBind");



        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}