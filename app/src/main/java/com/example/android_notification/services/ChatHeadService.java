package com.example.android_notification.services;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.android_notification.R;

import java.util.ArrayList;
import java.util.List;

public class ChatHeadService extends Service {


        private WindowManager windowManager;
        private List<View> chatHeads;
        private LayoutInflater inflater;

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            inflater = LayoutInflater.from(this);
            chatHeads = new ArrayList<View>();
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {

            final View chatHead = inflater.inflate(R.layout.popupmenu, null);

          //  TextView txt_title = (TextView) chatHead.findViewById(R.id.txt_title);
          //  TextView txt_text = (TextView) chatHead.findViewById(R.id.txt_text);

         //   txt_title.setText(intent.getStringExtra("title"));
         //   txt_text.setText(intent.getStringExtra("text"));

        /*    chatHead.findViewById(R.id.btn_dismiss).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    windowManager.removeView(chatHead);
                }
            });*/

            WindowManager.LayoutParams params;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                params = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT);

                params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

            } else {
                params = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_PHONE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT);

                params.type = WindowManager.LayoutParams.TYPE_PHONE;
            }

            params.gravity = Gravity.CENTER;

         /*   chatHead.findViewById(R.id.txt_title).setOnTouchListener(new View.OnTouchListener() {
                private int initialX;
                private int initialY;
                private float initialTouchX;
                private float initialTouchY;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            initialX = params.x;
                            initialY = params.y;
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            return true;
                        case MotionEvent.ACTION_UP:
                            return true;
                        case MotionEvent.ACTION_MOVE:
                            params.x = initialX + (int) (event.getRawX() - initialTouchX);
                            params.y = initialY + (int) (event.getRawY() - initialTouchY);
                            windowManager.updateViewLayout(chatHead, params);
                            return true;
                    }
                    return false;
                }
            });*/

            addChatHead(chatHead, params);

            return super.onStartCommand(intent, flags, startId);
        }

        public void addChatHead(View chatHead, WindowManager.LayoutParams params) {
            chatHeads.add(chatHead);
            windowManager.addView(chatHead, params);
        }

        public void removeChatHead(View chatHead) {
            chatHeads.remove(chatHead);
            windowManager.removeView(chatHead);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            for (View chatHead : chatHeads) {
                removeChatHead(chatHead);
            }
        }

}
