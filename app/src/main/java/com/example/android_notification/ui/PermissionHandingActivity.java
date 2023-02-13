package com.example.android_notification.ui;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_notification.R;
import com.example.android_notification.databinding.ActivityPermissionHandingBinding;
import com.example.android_notification.permission.permissionInterfaceImp.CameraPermissionTextProvider;
import com.example.android_notification.permission.permissionInterfaceImp.DefaultPermissionTextProvider;
import com.example.android_notification.permission.PermissionDialog;
import com.example.android_notification.permission.permissonInterface.DialogCallBack;
import com.example.android_notification.permission.permissonInterface.PermissionTextProvider;
import com.example.android_notification.permission.PermissionUtil;
import com.example.android_notification.permission.permissionInterfaceImp.PhoneCallPermissionTextProvider;
import com.example.android_notification.permission.permissionInterfaceImp.RecordAudioPermissionTextProvider;

import java.util.Arrays;
import java.util.List;

public class PermissionHandingActivity extends AppCompatActivity {
    public static final String TAG = PermissionHandingActivity.class.getSimpleName();
    private ActivityPermissionHandingBinding binding;
    private PermissionViewModel viewModel;
    private PermissionUtil permissionUtil;
    private List<String> permissionsToRequest = Arrays.asList(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CALL_PHONE
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_permission_handing);
        viewModel = new ViewModelProvider(this).get(PermissionViewModel.class);
        permissionUtil = new PermissionUtil(this);
        binding.singlePermission.setOnClickListener(this::onSinglePermissionClick);
        binding.multiplePermision.setOnClickListener(this::onMultiplePermissionClick);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionDialogsShow();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void permissionDialogsShow() {

        permissionUtil.getDialogQueue().observe(this,permission->{
            if(permission.isEmpty()){
                return;
            }

            new PermissionDialog(
                    this,
                    getPermissionProvider(permission),
                    !shouldShowRequestPermissionRationale(permission),
                    new DialogCallBack() {
                        @Override
                        public void onOkClick() {
                            permissionUtil.dismissDialog();
                            multipleRequestPermissionLauncher.launch(
                                    Arrays.asList(permission).toArray(String[]::new)
                            );
                        }

                        @Override
                        public void onGoToSettingClick() {
                            permissionUtil.openAppSettings();
                        }

                        @Override
                        public void onDismiss() {
                            permissionUtil.dismissDialog();
                        }
                    }
            );
        });


    }

    private PermissionTextProvider getPermissionProvider(String permission) {
        Log.d(TAG, "getPermissionProviderFor: "+permission);
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
        return new DefaultPermissionTextProvider();
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
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> permissionUtil.onPermissionResult(
                    Manifest.permission.CAMERA,
                    isGranted
            ));

    private final ActivityResultLauncher<String[]> multipleRequestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                Log.d(TAG, "permission.result: "+result);
                result.keySet().forEach(
                        permission -> permissionUtil.onPermissionResult(
                                permission,
                                Boolean.TRUE.equals(result.get(permission)
                                ))
                );
            });


}