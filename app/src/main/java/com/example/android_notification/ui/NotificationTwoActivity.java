package com.example.android_notification.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_notification.databinding.ActivityNotificationTwoBinding;


public class NotificationTwoActivity extends AppCompatActivity {
    private ActivityNotificationTwoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationTwoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}