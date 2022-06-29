package com.example.android_notification.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.android_notification.R;
import com.example.android_notification.databinding.ActivityNotificationOneBinding;
import com.example.android_notification.services.NotificationReceiver;
import com.example.android_notification.utils.NotificationUtils;

public class NotificationOneActivity extends AppCompatActivity {
    private ActivityNotificationOneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCh1.setOnClickListener(this::sendOnChannel1);
        binding.btnCh2.setOnClickListener(this::sendOnChannel2);

    }

    public void sendOnChannel1(View v) {
        String title = binding.editTextTitle.getText().toString();
        String message = binding.editTextMessage.getText().toString();


        Intent activityIntent = new Intent(this, NotificationOneActivity.class);
        PendingIntent contentIntent = NotificationUtils.getActivityPendingIntent(this,activityIntent);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);

        PendingIntent actionIntent = NotificationUtils.getBroadCastPendingIntent(this,broadcastIntent);


        Notification notification = new NotificationCompat.Builder(this, NotificationUtils.NOTIFICATION_CHANNEL_ACTION)
                .setSmallIcon(R.drawable.ic_baseline_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                //  .setAutoCancel(true)
                // .setOnlyAlertOnce(true)
                .setContentIntent(contentIntent)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        NotificationUtils.notifyNotification(this, 204, notification);
    }

    public void sendOnChannel2(View v) {
        String title = binding.editTextTitle.getText().toString();
        String message = binding.editTextMessage.getText().toString();


        Intent activityIntent = new Intent(this, NotificationOneActivity.class);
        PendingIntent contentIntent = NotificationUtils.getActivityPendingIntent(this,activityIntent);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);

        PendingIntent actionIntent = NotificationUtils.getBroadCastPendingIntent(this,broadcastIntent);


        Notification notification = new NotificationCompat.Builder(this, NotificationUtils.NOTIFICATION_CHANNEL_INFO)
                .setSmallIcon(R.drawable.ic_baseline_notifications_none)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        NotificationUtils.notifyNotification(this, 200, notification);
    }
}