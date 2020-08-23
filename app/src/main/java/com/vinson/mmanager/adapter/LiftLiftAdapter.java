package com.vinson.mmanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinson.mmanager.model.LiftInfo;
import com.vinson.mmanager.model.LiftModel;

import java.util.List;

public class LiftLiftAdapter extends BaseDataListAdapter<LiftInfo, LiftLiftAdapter.LiftViewHolder> {


    public LiftLiftAdapter(List<LiftInfo> data, Context context) {
        super(data, context);
    }

    @NonNull
    @Override
    public LiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LiftViewHolder holder, int position) {

    }

    class LiftViewHolder extends RecyclerView.ViewHolder {

        public LiftViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
