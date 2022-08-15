package com.example.android_notification.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.android_notification.R;

public class MyNotificationService extends Service {
    private static final String TAG = MyNotificationService.class.getSimpleName();
    public MyNotificationService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"WE ARE NOW IN "+TAG);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                showCustomPopupMenu();
                return;
            }
            return;
        }
        showCustomPopupMenu();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"WE ARE NOW IN  onStartCommand");


        return super.onStartCommand(intent, flags, startId);
    }

    public  void showCustomPopupMenu()
    {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.popupmenu, null);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        params.gravity= Gravity.CENTER|Gravity.CENTER;
        params.x=0;
        params.y=0;
        windowManager.addView(view, params);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"WE ARE NOW IN : onBind");



        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}