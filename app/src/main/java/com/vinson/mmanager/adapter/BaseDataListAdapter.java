package com.vinson.mmanager.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseDataListAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    List<T> mData;
    Context mContext;

    public BaseDataListAdapter(List<T> data, Context context) {
        mData = data;
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
