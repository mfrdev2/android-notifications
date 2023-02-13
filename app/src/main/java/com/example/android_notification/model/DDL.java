package com.example.android_notification.model;

import java.io.Serializable;

public class DDL implements Serializable {
    private String name,id;

    public DDL(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DDL{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
