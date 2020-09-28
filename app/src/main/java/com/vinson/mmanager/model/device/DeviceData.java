package com.vinson.mmanager.model.device;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.App;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.base.DeletedAt;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class DeviceData extends AbstractFlexibleItem<DeviceData.DataViewHolder> {
    int ID;
    String CreatedAt;
    String UpdatedAt;
    DeletedAt DeletedAt;
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
        return R.layout.item_device_data;
    }

    @Override
    public DataViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new DataViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, DataViewHolder holder, int position, List<Object> payloads) {
        holder.trouble.setText(trouble.categoryName);
        holder.deviceId.setText(String.valueOf(deviceId));
        holder.acc.setText("三轴加速度:" + accx + " | " + accy + " | " + accz);
        holder.deg.setText("三轴角度:" + degx + " | " + degy + " | " + degz);
        holder.speed.setText(String.valueOf(speedz) + "m/s");
        holder.door.setText(doorState.categoryName);
        holder.people.setText(peopleInside ? "有人":"无人");
        holder.people.setTextColor(peopleInside ? App.getInstance().color(R.color.md_red_A400) : App.getInstance().color(R.color.md_green_500));
    }

    static class DataViewHolder extends FlexibleViewHolder {
        MaterialTextView trouble, deviceId, acc, deg, speed, door, people;

        public DataViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            trouble = itemView.findViewById(R.id.tv_trouble);
            deviceId = itemView.findViewById(R.id.tv_deviceid);
            acc = itemView.findViewById(R.id.tv_acc);
            deg = itemView.findViewById(R.id.tv_deg);
            speed = itemView.findViewById(R.id.tv_speedz);
            door = itemView.findViewById(R.id.tv_door);
            people = itemView.findViewById(R.id.tv_people);
        }
    }
}
