package com.example.android_notification.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.android_notification.DataBean;
import com.example.android_notification.R;

import java.util.Objects;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        DataBean data = (DataBean) getIntent().getSerializableExtra("data");
        if(Objects.nonNull(data)){
            createDialog(data);
        }
    }

    private void createDialog(DataBean dataBean){
        new AlertDialog.Builder(this)
                .setTitle(dataBean.getType())
                .setMessage(dataBean.getOrderId())
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show();
                })
                .setPositiveButton(android.R.string.cancel, (dialogInterface, i) -> {
                    Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show();
                }).create().show();
       // alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
       // alertDialog.show();
    }
}