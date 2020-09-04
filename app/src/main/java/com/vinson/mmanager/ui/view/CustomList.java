package com.vinson.mmanager.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.vinson.mmanager.adapter.LiftChangesAdapter;
import com.vinson.mmanager.adapter.LiftRecordsAdapter;
import com.vinson.mmanager.adapter.LiftTroublesAdapter;
import com.vinson.mmanager.adapter.LiftsAdapter;
import com.vinson.mmanager.adapter.base.BaseDataListAdapter;
import com.vinson.mmanager.model.annotation.ModuleType;

import static com.vinson.mmanager.model.annotation.ModuleType.MODULE_LIFT_CHANGES;
import static com.vinson.mmanager.model.annotation.ModuleType.MODULE_LIFT_LIST;
import static com.vinson.mmanager.model.annotation.ModuleType.MODULE_LIFT_RECORDS;
import static com.vinson.mmanager.model.annotation.ModuleType.MODULE_LIFT_TROUBLE;

public class CustomList extends UltimateRecyclerView {

    @ModuleType
    int listType;

    public void setListType(int listType) {
        this.listType = listType;
    }

    public CustomList(@NonNull Context context) {
        this(context, null);
    }

    public CustomList(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomList(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
