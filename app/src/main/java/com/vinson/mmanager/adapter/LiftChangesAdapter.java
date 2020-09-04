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
import com.vinson.mmanager.model.lift.LiftChange;

import java.util.List;

public class LiftChangesAdapter extends BaseDataListAdapter<LiftChange, LiftChangesAdapter.ChangeViewHolder> {


    public LiftChangesAdapter(List<LiftChange> data, Context context) {
        super(data, context);
    }

    @NonNull
    @Override
    public ChangeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lift_change, parent, false);
        final ChangeViewHolder holder = new ChangeViewHolder(root);

        return holder;
    }

    @Override
    public ChangeViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public ChangeViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public ChangeViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public int getAdapterItemCount() {
        return 0;
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull ChangeViewHolder holder, int position) {
        LiftChange info = mData.get(position);
        holder.name.setText(info.getLift().getNickName());
        holder.code.setText(info.getLift().getCode());
        holder.content.setText(info.getContent());
    }

    static class ChangeViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView name, code, content;
        public ChangeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            content = itemView.findViewById(R.id.tv_content);
        }
    }
}
