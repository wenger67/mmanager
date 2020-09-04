package com.vinson.mmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.google.android.material.textview.MaterialTextView;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.base.BaseDataListAdapter;
import com.vinson.mmanager.model.lift.LiftInfo;

import java.util.List;

public class LiftsAdapter extends BaseDataListAdapter<LiftInfo, LiftsAdapter.LiftViewHolder> {


    public LiftsAdapter(List<LiftInfo> data, Context context) {
        super(data, context);
    }

    @NonNull
    @Override
    public LiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lift, parent, false);
        final LiftViewHolder holder = new LiftViewHolder(root);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LiftViewHolder holder, int position) {
        LiftInfo info = mData.get(position);
        holder.name.setText(info.getNickName());
        holder.code.setText(info.getCode());
        holder.address.setText(info.getAddress().getAddressName() + info.getBuilding() + "dong" + info.getCell()+ "danyuan");
        String installTime = info.getInstallTime();
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
        long uptimeSum = TimeUtils.string2Millis(installTime, pattern);
        long checktimeSum = TimeUtils.string2Millis(info.getCheckTime(), pattern);
        holder.uptime.setText(TimeUtils.getTimeSpanByNow(uptimeSum, TimeConstants.HOUR) + " hours");
        holder.checkTime.setText(TimeUtils.getFitTimeSpanByNow(checktimeSum, 1));
//        holder.online.setText(info.getDevice().isOnline() ? "Online":"Offline");
    }

    static class LiftViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView name, code, address, uptime, checkTime, online;
        public LiftViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            address = itemView.findViewById(R.id.tv_address);
            uptime = itemView.findViewById(R.id.tv_uptime);
            checkTime = itemView.findViewById(R.id.tv_checktime);
            online = itemView.findViewById(R.id.tv_online);
        }
    }
}
