package com.example.android_notification.bindingadapter;


import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.databinding.BindingAdapter;

import com.example.android_notification.R;
import com.example.android_notification.model.DDL;

import java.util.List;
import java.util.stream.Collectors;

public class UiBinding {

    @BindingAdapter({"ddL_adapter"})
    public static void addQuestionItems(AutoCompleteTextView autoCompleteTextView, List<DDL> ddlList) {
        if(ddlList == null){
            return;
        }
        List<String> collect = ddlList.stream().map(DDL::getName).collect(Collectors.toList());

        initDDL(autoCompleteTextView,collect);
    }

    private static void initDDL(AutoCompleteTextView autoCompleteTextView,List<String> data) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(autoCompleteTextView.getContext(), R.layout.material_spinner_list_item, data);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}
