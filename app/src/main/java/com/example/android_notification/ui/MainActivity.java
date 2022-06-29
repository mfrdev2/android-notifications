package com.example.android_notification.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.android_notification.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNotificationOne.setOnClickListener(v -> {
            startActivity(new Intent(this, NotificationOneActivity.class));
        });
        binding.btnNotificationTwo.setOnClickListener(v -> {
            startActivity(new Intent(this, NotificationTwoActivity.class));
        });
    }
}