package com.example.android_notification.permission;

public class RecordAudioPermissionTextProvider implements PermissionTextProvider {
    @Override
    public String getDescription(Boolean isPermanentlyDeclined) {
         if(isPermanentlyDeclined) {
          return   "It seems you permanently declined microphone permission. " +
                    "You can go to the app settings to grant it.";
        } else {
           return  "This app needs access to your microphone so that your friends " +
                    "can hear you in a call.";
        }
    }
}
