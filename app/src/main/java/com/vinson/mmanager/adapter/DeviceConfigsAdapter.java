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
import com.vinson.mmanager.model.device.DeviceConfig;

import java.util.List;

public class DeviceConfigsAdapter extends BaseDataListAdapter<DeviceConfig, DeviceConfigsAdapter.ChangeViewHolder> {


    public DeviceConfigsAdapter(List<DeviceConfig> data, Context context) {
        super(data, context);
    }

    @NonNull
    @Override
    public ChangeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lift_list, parent, false);
        final ChangeViewHolder holder = new ChangeViewHolder(root);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChangeViewHolder holder, int position) {
        DeviceConfig info = mData.get(position);
        holder.name.setText(info.getKey());
        holder.code.setText(info.getValue());
        holder.owner.setText(info.getComment());
    }

    static class ChangeViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView name, code, owner;
        public ChangeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            owner = itemView.findViewById(R.id.tv_content);
        }
    }
}
