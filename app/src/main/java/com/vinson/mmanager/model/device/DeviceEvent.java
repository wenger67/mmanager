package com.vinson.mmanager.model.device;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.base.TimeWrapper;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class DeviceEvent extends AbstractFlexibleItem<DeviceEvent.EventViewHolder> {
    int ID;
    String CreatedAt;
    String UpdatedAt;
    TimeWrapper TimeWrapper;
    int deviceId;
    Device device;
    int typeId;
    Category type;
    String content;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_device_event;
    }

    @Override
    public EventViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new EventViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, EventViewHolder holder,
                               int position, List<Object> payloads) {
        holder.type.setText(type.categoryName);
        holder.deviceId.setText(String.valueOf(deviceId));
        holder.content.setText(content);
    }

    static class EventViewHolder extends FlexibleViewHolder {
        MaterialTextView type, deviceId, content;

        public EventViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView,
                    adapter);
            type = itemView.findViewById(R.id.tv_type);
            deviceId = itemView.findViewById(R.id.tv_deviceid);
            content = itemView.findViewById(R.id.tv_content);
        }
    }
}
