package com.vinson.mmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.lift.LiftInfo;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public class MySpinnerAdapter extends BaseAdapter {
    private List<AbstractFlexibleItem> mItems;
    private Context mContext;

    public MySpinnerAdapter(List<AbstractFlexibleItem> items, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, null);
        if (convertView != null) {
            MaterialTextView textView = convertView.findViewById(R.id.tv_left);
            if (getItem(position) instanceof LiftInfo)
                textView.setText(((LiftInfo) getItem(position)).nickName);
            else if (getItem(position) instanceof Category) {
                textView.setText(((Category) getItem(position)).categoryName);
            }
        }
        return convertView;
    }
}
