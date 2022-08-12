package com.example.android_notification.services;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.android_notification.DataBean;

import java.util.Map;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String TAG = NotificationReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"WE ARE NOW "+TAG);

      //  String message = intent.getStringExtra("toastMessage");
        Toast.makeText(context, "message", Toast.LENGTH_SHORT).show();

        new AlertDialog.Builder(context)
                .setTitle("Data")
                .setMessage("This is someThing")
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    Toast.makeText(context, "Ok",Toast.LENGTH_LONG).show();
                })
                .setPositiveButton(android.R.string.cancel, (dialogInterface, i) -> {
                    Toast.makeText(context,"Cancel",Toast.LENGTH_LONG).show();
                }).create().show();


      //  Map<String, String> message = ((DataBean) intent.getSerializableExtra("toastMessage")).getData();
      //  Toast.makeText(context, message.get("type"), Toast.LENGTH_SHORT).show();
    }
}
