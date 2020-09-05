package com.vinson.mmanager.model.device;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class DeviceConfig  extends AbstractFlexibleItem<DeviceConfig.ConfigViewHolder> {
        int ID;
    String CreatedAt;
    String UpdatedAt;
    String DeletedAt;
    String key;
    String value;
    String comment;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public ConfigViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new ConfigViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ConfigViewHolder holder, int position, List<Object> payloads) {
        holder.name.setText(key);
        holder.code.setText(value);
        holder.owner.setText(comment);
    }

    static class ConfigViewHolder extends FlexibleViewHolder {
        MaterialTextView name, code, owner;
        public ConfigViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            owner = itemView.findViewById(R.id.tv_content);
        }
    }
}
