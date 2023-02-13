package com.example.android_notification.permission;

public class DefaultPermissionTextProvider implements PermissionTextProvider {
    @Override
    public String getDescription(Boolean isPermanentlyDeclined) {
        if(isPermanentlyDeclined) {
           return  "It seems you permanently declined permission that need this app. " +
                    "You can go to the app settings to grant it.";
        } else {
            return "This app needs to your permission so that you " +
                    "enjoying the full experience of this app.";
        }
    }
}
