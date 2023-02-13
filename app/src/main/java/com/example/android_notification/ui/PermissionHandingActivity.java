package com.example.android_notification.ui;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_notification.R;
import com.example.android_notification.databinding.ActivityPermissionHandingBinding;
import com.example.android_notification.permission.CameraPermissionTextProvider;
import com.example.android_notification.permission.PermissionDialog;
import com.example.android_notification.permission.PermissionTextProvider;
import com.example.android_notification.permission.PhoneCallPermissionTextProvider;
import com.example.android_notification.permission.RecordAudioPermissionTextProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PermissionHandingActivity extends AppCompatActivity {
    private ActivityPermissionHandingBinding binding;
    private PermissionViewModel viewModel;
    private ObservableArrayList<String> dialogQueue;
    private List<String> permissionsToRequest = Arrays.asList(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CALL_PHONE
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_permission_handing);
        viewModel = new ViewModelProvider(this).get(PermissionViewModel.class);
        dialogQueue = viewModel.visiblePermissionDialogQueue;
        binding.singlePermission.setOnClickListener(this::onSinglePermissionClick);
        binding.multiplePermision.setOnClickListener(this::onMultiplePermissionClick);
        permissionDialogsShow();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void permissionDialogsShow() {
        dialogQueue.stream()
                .sorted(Collections.reverseOrder())
                .forEach(permission -> {
                    new PermissionDialog(
                            this,
                            getPermissionProvider(permission),
                            shouldShowRequestPermissionRationale(permission),
                            viewModel,
                            new DialogCallBack() {
                                @Override
                                public void onOkClick() {
                                    viewModel.dismissDialog();
                                    multipleRequestPermissionLauncher.launch(
                                            Arrays.asList(permission).toArray(String[]::new)
                                    );
                                }

                                @Override
                                public void onGoToSettingClick() {
                                    openAppSettings();
                                }

                                @Override
                                public void onDismiss() {
                                    viewModel.dismissDialog();
                                }
                            }
                    );
                });
    }

    private PermissionTextProvider getPermissionProvider(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA: {
                return new CameraPermissionTextProvider();

            }
            case Manifest.permission.RECORD_AUDIO: {
                return new RecordAudioPermissionTextProvider();
            }
            case Manifest.permission.CALL_PHONE: {
                return new PhoneCallPermissionTextProvider();
            }
        }
        return new CameraPermissionTextProvider();
    }


    private void onMultiplePermissionClick(View view) {
        multipleRequestPermissionLauncher.launch(permissionsToRequest.toArray(String[]::new));
    }

    private void onSinglePermissionClick(View view) {
        singleRequestPermissionLauncher.launch(
                Manifest.permission.CAMERA
        );
    }


    private final ActivityResultLauncher<String> singleRequestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> viewModel.onPermissionResult(
                    Manifest.permission.CAMERA,
                    isGranted
            ));

    private final ActivityResultLauncher<String[]> multipleRequestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                permissionsToRequest.forEach(
                        permission -> {
                            viewModel.onPermissionResult(
                                    permission,
                                    result.get(permission)
                            );
                        }
                );
            });

    private void openAppSettings() {
        Intent aPackage = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", this.getPackageName(), null)
        );
        startActivity(aPackage);
    }
}