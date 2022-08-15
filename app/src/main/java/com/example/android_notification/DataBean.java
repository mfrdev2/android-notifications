package com.example.android_notification;

import android.content.Context;

import java.io.Serializable;

public class DataBean implements Serializable {
private String orderId;
private String type;
private Context context;

    public DataBean(String orderId, String type) {
        this.orderId = orderId;
        this.type = type;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "orderId='" + orderId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
