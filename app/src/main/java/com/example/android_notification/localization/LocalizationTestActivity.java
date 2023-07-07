package com.example.android_notification.localization;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android_notification.R;
import com.example.android_notification.databinding.ActivityLocalizationTestBinding;
import com.example.android_notification.ui.MainActivity;

import java.util.Locale;

public class LocalizationTestActivity extends AppCompatActivity {
    private ActivityLocalizationTestBinding binding;

    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_localization_test);

        binding.btnBn.setOnClickListener(this::onClickBn);
        binding.btnEn.setOnClickListener(this::onClickEn);

        binding.btnNext.setOnClickListener(v->{
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    private void onClickEn(View view) {
        LocaleHelper.setLocale(LocalizationTestActivity.this,"en");
        finish();
        startActivity(getIntent());
    }

    private void onClickBn(View view) {
        LocaleHelper.setLocale(LocalizationTestActivity.this,"bn");
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}