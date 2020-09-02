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
import com.vinson.mmanager.model.lift.LiftRecord;

import java.util.List;

public class LiftRecordsAdapter extends BaseDataListAdapter<LiftRecord, LiftRecordsAdapter.RecordViewHolder> {


    public LiftRecordsAdapter(List<LiftRecord> data, Context context) {
        super(data, context);
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lift_list, parent, false);
        final RecordViewHolder holder = new RecordViewHolder(root);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        LiftRecord info = mData.get(position);
        holder.name.setText(info.getLift().getNickName());
        holder.code.setText(info.getCategory().getCategoryName());
        holder.owner.setText(info.getRecorder().getRealName());
    }

    static class RecordViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView name, code, owner;
        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            owner = itemView.findViewById(R.id.tv_owner);
        }
    }
}
