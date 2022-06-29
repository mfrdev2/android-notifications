package com.example.android_notification.utils;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Arrays;
import java.util.Objects;

public class NotificationUtils {
    public static final String NOTIFICATION_CHANNEL_ORDER = "NOTIFICATION_ORDER";
    public static final String NOTIFICATION_CHANNEL_INFO = "NOTIFICATION_INFO";
    public static final String NOTIFICATION_CHANNEL_ACTION = "NOTIFICATION_ACTION";

    private static NotificationManagerCompat NOTIFICATION_MANAGER_INSTANCE;

    public static void createNotificationChannels(Application application) {
        long pattern[] = {0, 1000, 500, 1000};

        NotificationManager mNotificationManager =
                (NotificationManager) application.getSystemService(application.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ORDER, "Your Notifications",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.setDescription("");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(pattern);
            notificationChannel.enableVibration(true);


            NotificationChannel notificationChannel2 = new NotificationChannel(NOTIFICATION_CHANNEL_INFO, "Your Notifications",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel2.setDescription("");
            notificationChannel2.enableLights(true);
            notificationChannel2.setLightColor(Color.RED);
            notificationChannel2.setVibrationPattern(pattern);
            notificationChannel2.enableVibration(true);

            NotificationChannel notificationChannel3 = new NotificationChannel(NOTIFICATION_CHANNEL_ACTION, "Your Notifications",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel3.setDescription("");
            notificationChannel3.enableLights(true);
            notificationChannel3.setLightColor(Color.RED);
            notificationChannel3.setVibrationPattern(pattern);
            notificationChannel3.enableVibration(true);

            mNotificationManager.createNotificationChannels(Arrays.asList(notificationChannel, notificationChannel2, notificationChannel3));
        }

        // to diaplay notification in DND Mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ORDER);
            if (Objects.isNull(channel)) {
                return;
            }
            channel.canBypassDnd();

            NotificationChannel channel2 = mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_INFO);
            if (Objects.isNull(channel2)) {
                return;
            }
            channel2.canBypassDnd();

            NotificationChannel channel3 = mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ACTION);
            if (Objects.isNull(channel3)) {
                return;
            }
            channel3.canBypassDnd();

        }
    }

    private static NotificationManagerCompat getNotificationManagerInstance(Context context) {
        if (Objects.isNull(NOTIFICATION_MANAGER_INSTANCE)) {
            return NOTIFICATION_MANAGER_INSTANCE = NotificationManagerCompat.from(context);
        }
        return NOTIFICATION_MANAGER_INSTANCE;
    }

    public static void notifyNotification(Context context, int id, Notification notification) {
        getNotificationManagerInstance(context).notify(id, notification);
    }

    public static NotificationCompat.Builder getNotificationBuilder(Context context, String notificationChannelId) {
        return new NotificationCompat.Builder(context, notificationChannelId);
    }

    public static PendingIntent getActivityPendingIntent(Context context, Intent activityIntent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            return PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_MUTABLE);
        } else {
            return PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
    }

    public static PendingIntent getBroadCastPendingIntent(Context context, Intent broadCastIntent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            return PendingIntent.getBroadcast(context, 0, broadCastIntent, PendingIntent.FLAG_MUTABLE);
        } else {
            return PendingIntent.getBroadcast(context, 0, broadCastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
    }

    public static PendingIntent getServicePendingIntent(Context context, Intent serviceIntent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            return PendingIntent.getService(context, 0, serviceIntent, PendingIntent.FLAG_MUTABLE);
        } else {
            return PendingIntent.getService(context, 0, serviceIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
    }

    public static PendingIntent getForegroundServicePendingIntent(Context context, Intent foregroundServiceIntent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return PendingIntent.getForegroundService(context, 0, foregroundServiceIntent, PendingIntent.FLAG_MUTABLE);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return PendingIntent.getForegroundService(context, 0, foregroundServiceIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            }
        }
        return null;
    }
}
