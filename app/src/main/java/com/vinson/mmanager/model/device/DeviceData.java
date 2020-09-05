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

public class DeviceData extends AbstractFlexibleItem<DeviceData.DataViewHolder> {
    int ID;
    String CreatedAt;
    String UpdatedAt;
    String DeletedAt;
    int deviceId;
    Device device;
    int troubleId;
    Category trouble;
    float accx;
    float accy;
    float accz;
    float degx;
    float degy;
    float degz;
    float speedz;
    float floor;
    int doorStateId;
    Category doorState;
    boolean peopleInside;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public DataViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new DataViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, DataViewHolder holder, int position, List<Object> payloads) {
        holder.name.setText(String.valueOf(ID));
        holder.code.setText(accx + ", " + accy + ", " + accz);
        holder.owner.setText(degx + ", " + degy + ", " + degz);
    }

    static class DataViewHolder extends FlexibleViewHolder {
        MaterialTextView name, code, owner;

        public DataViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            owner = itemView.findViewById(R.id.tv_content);
        }
    }
}
