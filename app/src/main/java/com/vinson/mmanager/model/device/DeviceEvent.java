package com.vinson.mmanager.model.device;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.Category;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class DeviceEvent extends AbstractFlexibleItem<DeviceEvent.EventViewHolder> {

    int ID;
    String CreatedAt;
    String UpdatedAt;
    String DeletedAt;
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
        return 0;
    }

    @Override
    public EventViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new EventViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, EventViewHolder holder,
                               int position, List<Object> payloads) {
        holder.name.setText(type.categoryName);
        holder.code.setText(String.valueOf(deviceId));
        holder.owner.setText(content);
    }

    static class EventViewHolder extends FlexibleViewHolder {
        MaterialTextView name, code, owner;

        public EventViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView,
                    adapter);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            owner = itemView.findViewById(R.id.tv_content);
        }
    }
}
