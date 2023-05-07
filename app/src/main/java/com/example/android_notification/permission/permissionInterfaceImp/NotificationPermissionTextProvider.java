package com.example.android_notification.permission.permissionInterfaceImp;

import com.example.android_notification.permission.permissonInterface.PermissionTextProvider;

public class NotificationPermissionTextProvider implements PermissionTextProvider {
    @Override
    public String getDescription(Boolean isPermanentlyDeclined) {
        if(isPermanentlyDeclined) {
            return "It seems you permanently declined notification permission. " +
                    "You can go to the app settings to grant it.";
        } else {
            return  "This app needs notification permission so that you can get " +
                    "important notification from server.";
        }
    }
}
