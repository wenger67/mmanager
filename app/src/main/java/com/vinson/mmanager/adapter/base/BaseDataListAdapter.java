package com.vinson.mmanager.adapter.base;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataListAdapter<T, VH extends RecyclerView.ViewHolder> extends UltimateViewAdapter<VH> {

    protected List<T> mData;
    protected Context mContext;

    public BaseDataListAdapter(List<T> data, Context context) {
        if (data == null) mData = new ArrayList<>();
        else mData = data;
        mContext = context;
    }

    public void setData(List<T> data) {
        mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
