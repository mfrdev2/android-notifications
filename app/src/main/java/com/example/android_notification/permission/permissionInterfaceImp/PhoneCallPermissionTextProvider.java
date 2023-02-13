package com.example.android_notification.permission.permissionInterfaceImp;

import com.example.android_notification.permission.permissonInterface.PermissionTextProvider;

public class PhoneCallPermissionTextProvider implements PermissionTextProvider {
    @Override
    public String getDescription(Boolean isPermanentlyDeclined) {
         if(isPermanentlyDeclined) {
             return "It seems you permanently declined phone calling permission. " +
                    "You can go to the app settings to grant it.";
        } else {
           return  "This app needs phone calling permission so that you can talk " +
                    "to your friends.";
        }
    }
}
