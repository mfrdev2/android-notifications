package com.example.android_notification.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;

import com.example.android_notification.model.DDL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivityViewModel extends AndroidViewModel {

    private final ObservableList<DDL> ddls = new ObservableArrayList<>();


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableList<DDL> getDDDLsList() {
        return ddls;
    }

    public void setDDLList(){
        List<DDL> ddls = Arrays.asList(
                new DDL("A", "xxxx"),
                new DDL("AB", "xsxxx"),
                new DDL("B", "xxsxx")
        );


        this.ddls.addAll(ddls);
    }

    public static Map<String, String> getMapFromList(List<DDL> data) {
        return data.stream().collect(Collectors.toUnmodifiableMap(DDL::getName, DDL::getId));
    }
}
