package com.example.android_notification.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Bundle;

import com.example.android_notification.R;
import com.example.android_notification.databinding.ActivityInAppBrowserBinding;
import com.example.android_notification.customtab.CustomTabActivityHelper;
import com.example.android_notification.customtab.WebviewFallback;

public class InAppBrowserActivity extends AppCompatActivity {
    private ActivityInAppBrowserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_in_app_browser);

        binding.iabButton.setOnClickListener(v->{
            Uri parse = Uri.parse("http://developer.google.com");
            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
            CustomTabActivityHelper.openCustomTab(
                    this, customTabsIntent, parse, new WebviewFallback());
        });
    }
}