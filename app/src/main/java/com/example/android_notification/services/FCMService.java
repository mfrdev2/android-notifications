package com.example.android_notification.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.android_notification.DataBean;
import com.example.android_notification.R;
import com.example.android_notification.ui.NotificationOneActivity;
import com.example.android_notification.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;


public class FCMService extends FirebaseMessagingService {
    private final static String TAG = FCMService.class.getSimpleName();

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d(TAG, "NewToken:: " + s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ false) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                Map<String, String> data = remoteMessage.getData();
                DataBean dataBean = new DataBean(data.get("orderId"), data.get("type"));
                handleNow(remoteMessage,data);
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void scheduleJob() {

    }

    private void handleNow(RemoteMessage remoteMessage, DataBean data) {
    /*    new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                // write your code here
                new AlertDialog.Builder(FCMService.this)
                        .setTitle(data.get("type"))
                        .setMessage(data.get("orderId"))
                        .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                            Toast.makeText(FCMService.this,"Ok",Toast.LENGTH_LONG).show();
                        })
                        .setPositiveButton(android.R.string.cancel, (dialogInterface, i) -> {
                            Toast.makeText(FCMService.this,"Cancel",Toast.LENGTH_LONG).show();
                        }).create().show();
            }
        });*/

        sendOnChannel1(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),data);


    /*    DataBean dataBean = new DataBean(data.get("orderId"), data.get("type"));
        Intent intent = new Intent(this, DialogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("data",dataBean);
        startActivity(intent);*/

    //    new ATask().execute(data);


    }
    private void handleNow(RemoteMessage remoteMessage,  Map<String, String> data) {
    /*    new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                // write your code here
                new AlertDialog.Builder(FCMService.this)
                        .setTitle(data.get("type"))
                        .setMessage(data.get("orderId"))
                        .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                            Toast.makeText(FCMService.this,"Ok",Toast.LENGTH_LONG).show();
                        })
                        .setPositiveButton(android.R.string.cancel, (dialogInterface, i) -> {
                            Toast.makeText(FCMService.this,"Cancel",Toast.LENGTH_LONG).show();
                        }).create().show();
            }
        });*/

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        sendOnChannel1(getTitle(data,notification),getBody(data,notification),null);


    /*    DataBean dataBean = new DataBean(data.get("orderId"), data.get("type"));
        Intent intent = new Intent(this, DialogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("data",dataBean);
        startActivity(intent);*/

        //    new ATask().execute(data);


    }
    private String getTitle(Map<String, String> data, RemoteMessage.Notification notification){
        String title = data.get("title");
        if(title != null){
            return title;
        }
        if(notification == null){
            return null;
        }
        return notification.getTitle();
    }

    private String getBody(Map<String, String> data, RemoteMessage.Notification notification){
        String body = data.get("body");
        if(body != null){
            return body;
        }
        if(notification == null){
            return null;
        }
        return notification.getBody();
    }


    public void sendOnChannel1(String title,String message,DataBean dataBean) {

        Intent activityIntent = new Intent(this, NotificationOneActivity.class);
        PendingIntent contentIntent = NotificationUtils.getActivityPendingIntent(this, activityIntent);

/*        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        DataBean dataBean = new DataBean(data.get("orderId"), data.get("type"));
        broadcastIntent.putExtra("toastMessage", dataBean);

        PendingIntent actionIntent = NotificationUtils.getBroadCastPendingIntent(this, broadcastIntent);*/

//        Intent serviceIntent = new Intent(this,ChatHeadService.class);
//        serviceIntent.putExtra("data", dataBean);
//        PendingIntent actionIntent = NotificationUtils.getServicePendingIntent(this, serviceIntent);


        Notification notification = new NotificationCompat.Builder(this, NotificationUtils.NOTIFICATION_CHANNEL_ACTION)
                .setSmallIcon(R.drawable.p_transparent_icon)
                .setColor(ContextCompat.getColor(this,R.color.red))
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                //  .setAutoCancel(true)
                // .setOnlyAlertOnce(true)
                .setContentIntent(contentIntent)
             //   .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        NotificationUtils.notifyNotification(this, 204, notification);
    }

    private class ATask extends AsyncTask<DataBean, Void,Context>{
        private  final String TAG = ATask.class.getSimpleName();

        @Override
        protected Context doInBackground(DataBean... dataBeans) {
            DataBean dataBean = dataBeans[0];
            Log.d(TAG,"doInBackground");
            return dataBean.getContext();
        }


        @Override
        protected void onPostExecute(Context context) {
            super.onPostExecute(context);
            Log.d(TAG,"onPostExecute");
           FCMService.this.showCustomPopupMenu();

        }
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

}
