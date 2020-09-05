package com.vinson.mmanager.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.vinson.mmanager.model.annotation.ModuleType;

public class CustomList extends RecyclerView {

    @ModuleType
    int listType;

    public CustomList(@NonNull Context context) {
        this(context, null);
    }

    public CustomList(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomList(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListType(int listType) {
        this.listType = listType;
    }
}
