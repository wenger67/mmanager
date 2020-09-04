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
import com.vinson.mmanager.model.lift.LiftTrouble;

import java.util.List;

public class LiftTroublesAdapter extends BaseDataListAdapter<LiftTrouble, LiftTroublesAdapter.TroubleViewHolder> {


    public LiftTroublesAdapter(List<LiftTrouble> data, Context context) {
        super(data, context);
    }

    @NonNull
    @Override
    public TroubleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lift_list, parent, false);
        final TroubleViewHolder holder = new TroubleViewHolder(root);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TroubleViewHolder holder, int position) {
        LiftTrouble info = mData.get(position);
        holder.name.setText(info.getLift().getNickName());
        holder.code.setText(info.getFromCategory().getCategoryName());
        holder.owner.setText(info.getFixCategory().getCategoryName());
    }

    static class TroubleViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView name, code, owner;
        public TroubleViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            owner = itemView.findViewById(R.id.tv_content);
        }
    }
}
