package com.vinson.mmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.base.BaseDataListAdapter;
import com.vinson.mmanager.model.LiftInfo;

import java.util.List;

public class LiftsAdapter extends BaseDataListAdapter<LiftInfo, LiftsAdapter.LiftViewHolder> {


    public LiftsAdapter(List<LiftInfo> data, Context context) {
        super(data, context);
    }

    @NonNull
    @Override
    public LiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lift_list, parent, false);
        final LiftViewHolder holder = new LiftViewHolder(root);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LiftViewHolder holder, int position) {
        LiftInfo info = mData.get(position);
        holder.name.setText(info.getNickName());
        holder.code.setText(info.getCode());
        holder.owner.setText(info.getOwner().getFullName());
    }

    static class LiftViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView name, code, owner;
        public LiftViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            owner = itemView.findViewById(R.id.tv_owner);
        }
    }
}
