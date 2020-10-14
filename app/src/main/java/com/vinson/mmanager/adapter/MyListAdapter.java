package com.vinson.mmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.annotation.ModuleType;
import com.vinson.mmanager.model.lift.LiftRecord;
import com.vinson.mmanager.model.lift.LiftTrouble;
import com.vinson.mmanager.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

import static com.vinson.mmanager.ui.item.LiftRecordDetailActivity.EXTRA_LIFT_RECORD;

public class MyListAdapter extends BaseAdapter {
    private List<AbstractFlexibleItem> mItems;
    private Context mContext;

    public MyListAdapter(List<AbstractFlexibleItem> items, Context context) {
        if (items == null) mItems = new ArrayList<>();
        else mItems = items;
        mContext = context;
    }

    public void setItems(List<AbstractFlexibleItem> items) {
        if (items == null) mItems = new ArrayList<>();
        else mItems = items;
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public AbstractFlexibleItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof LiftRecord)
            return ModuleType.MODULE_LIFT_RECORDS;
        else if (getItem(position) instanceof LiftTrouble)
            return ModuleType.MODULE_LIFT_TROUBLE;
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        switch (getItemViewType(position)) {
            case ModuleType.MODULE_LIFT_RECORDS:
                LiftRecord liftRecord = (LiftRecord) getItem(position);
                view = LayoutInflater.from(mContext).inflate(R.layout.item_lift_record_manage, null);
                MaterialTextView category = view.findViewById(R.id.tv_category);
                category.setText(liftRecord.category.categoryName);
                view.setOnClickListener(v -> {
                    ARouter.getInstance()
                            .build(Constants.AROUTER_PAGE_LIFT_RECORD_DETAIL)
                            .withObject(EXTRA_LIFT_RECORD, liftRecord)
                            .navigation(mContext);
                });
                return view;
        }
        return null;
    }
}
