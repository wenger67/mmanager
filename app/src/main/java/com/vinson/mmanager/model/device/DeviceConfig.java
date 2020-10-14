package com.vinson.mmanager.model.device;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.base.TimeWrapper;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class DeviceConfig extends AbstractFlexibleItem<DeviceConfig.ConfigViewHolder> {
    int ID;
    String CreatedAt;
    String UpdatedAt;
    TimeWrapper TimeWrapper;
    String key;
    String value;
    String comment;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_device_config;
    }

    @Override
    public ConfigViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new ConfigViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ConfigViewHolder holder,
                               int position, List<Object> payloads) {
        holder.key.setText(key);
        holder.value.setText(value);
        holder.comment.setText(comment);
    }

    static class ConfigViewHolder extends FlexibleViewHolder {
        MaterialTextView key, value, comment;

        public ConfigViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            key = itemView.findViewById(R.id.tv_name);
            value = itemView.findViewById(R.id.tv_value);
            comment = itemView.findViewById(R.id.tv_comment);
        }
    }
}
