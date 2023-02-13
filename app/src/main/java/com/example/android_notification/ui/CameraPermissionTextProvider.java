package com.example.android_notification.ui;

public class CameraPermissionTextProvider implements PermissionTextProvider {
    @Override
    public String getDescription(Boolean isPermanentlyDeclined) {
        if(isPermanentlyDeclined) {
           return  "It seems you permanently declined camera permission. " +
                    "You can go to the app settings to grant it.";
        } else {
            return "This app needs access to your camera so that your friends " +
                    "can see you in a call.";
        }
    }
}
