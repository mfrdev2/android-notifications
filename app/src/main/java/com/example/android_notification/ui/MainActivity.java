package com.example.android_notification.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.example.android_notification.R;
import com.example.android_notification.broadcast.PageReloaderStatusReceiver;
import com.example.android_notification.databinding.ActivityMainBinding;
import com.example.android_notification.localization.LocalizationTestActivity;
import com.example.android_notification.services.ChatHeadService;
import com.example.android_notification.utils.BroadcastReceiverHelper;
import com.example.android_notification.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setViewModel(viewModel);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());



        viewModel.setDDLList();

        binding.btnPermission.setOnClickListener(v->{
            startActivity(new Intent(this,PermissionHandingActivity.class));
        });
        binding.btnNotificationOne.setOnClickListener(v -> {
            startActivity(new Intent(this, NotificationOneActivity.class));
        });
        binding.btnNotificationTwo.setOnClickListener(v -> {
            startActivity(new Intent(this, NotificationTwoActivity.class));
        });

        binding.cameraPreview.setOnClickListener(v -> {
            startActivity(new Intent(this, CameraActivity.class));
        });

        binding.inAppBrowser.setOnClickListener(v -> {
            startActivity(new Intent(this, InAppBrowserActivity.class));
        });
        binding.btnLocalizationText.setOnClickListener(v -> {
            startActivity(new Intent(this, LocalizationTestActivity.class));
        });
        askNotificationPermission();

       // windowPermission();

        BroadcastReceiverHelper.registerBroadCastReceiver(this,new PageReloaderStatusReceiver(),new IntentFilter("page_reload"));

       // PageReloaderStatusReceiver.getStatus().observe(this,str->{
        //    System.out.println(TAG +" :: " +str);
       // });

        passwordView();

        progressIndicator();
    }

    private void progressIndicator() {
       binding.mProgressIndicator1.setForegroundColor(Color.RED);
       binding.mProgressIndicator1.setBackgroundColor(Color.GREEN);
       binding.mProgressIndicator1.setValue(50);
       binding.mProgressIndicator1.setPieStyle(false);
    }

    private void passwordView() {

    }

    private void windowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:"+getPackageName())
            );
            resultLauncher.launch(intent);
        }else {
            Log.d(TAG,"Overlay permission granted");
            Intent intent = new Intent(this, ChatHeadService.class);
            startService(intent);
        }
    }

    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == Activity.RESULT_OK){
            Log.d(TAG,"Overlay permission granted");
            Intent intent = new Intent(this, ChatHeadService.class);
            startService(intent);
        }
    });

    // ...
    private void askNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED) {
            // FCM SDK (and your app) can post notifications.
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                if (Build.VERSION.SDK_INT >= 33) {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                }
            }
        }
    }



}