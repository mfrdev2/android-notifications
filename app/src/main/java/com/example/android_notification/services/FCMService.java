package com.example.android_notification.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.android_notification.DataBean;
import com.example.android_notification.R;
import com.example.android_notification.ui.DialogActivity;
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

    private void handleNow(RemoteMessage remoteMessage, Map<String, String> data) {
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

      //  sendOnChannel1(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),data);
        DataBean dataBean = new DataBean(data.get("orderId"), data.get("type"));
        Intent intent = new Intent(this, DialogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("data",dataBean);
        startActivity(intent);

    }



    public void sendOnChannel1(String title,String message,Map<String, String> data) {

        Intent activityIntent = new Intent(this, NotificationOneActivity.class);
        PendingIntent contentIntent = NotificationUtils.getActivityPendingIntent(this, activityIntent);

/*        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        DataBean dataBean = new DataBean(data.get("orderId"), data.get("type"));
        broadcastIntent.putExtra("toastMessage", dataBean);

        PendingIntent actionIntent = NotificationUtils.getBroadCastPendingIntent(this, broadcastIntent);*/

        Intent serviceIntent = new Intent(this,MyNotificationService.class);
        DataBean dataBean = new DataBean(data.get("orderId"), data.get("type"));
        serviceIntent.putExtra("data", dataBean);
        PendingIntent actionIntent = NotificationUtils.getServicePendingIntent(this, serviceIntent);


        Notification notification = new NotificationCompat.Builder(this, NotificationUtils.NOTIFICATION_CHANNEL_ACTION)
                .setSmallIcon(R.drawable.p_transparent_icon)
                .setColor(ContextCompat.getColor(this,R.color.red))
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                //  .setAutoCancel(true)
                // .setOnlyAlertOnce(true)
                .setContentIntent(contentIntent)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        NotificationUtils.notifyNotification(this, 204, notification);
    }

}
