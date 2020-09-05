package com.vinson.mmanager.model.device;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.Company;
import com.vinson.mmanager.model.login.UserInfo;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class Device extends AbstractFlexibleItem<Device.DeviceViewHolder> {
        int ID;
    String CreatedAt;
    String UpdatedAt;
    String DeletedAt;
    int typeId;
    Category type;
    int factoryId;
    Company factory;
    String factoryTime;
    String installTime;
    int statusId;
    Category status;
    boolean online;
    String lastOfflineTime;
    String lastOnlineTime;
    UserInfo[] owners;
    DeviceConfig[] configs;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public DeviceViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new DeviceViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, DeviceViewHolder holder, int position, List<Object> payloads) {
        holder.name.setText(type.categoryName);
        holder.code.setText(status.categoryName);
        holder.owner.setText(CreatedAt);
    }

    static class DeviceViewHolder extends FlexibleViewHolder {
        MaterialTextView name, code, owner;
        public DeviceViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            owner = itemView.findViewById(R.id.tv_content);
        }
    }
}
