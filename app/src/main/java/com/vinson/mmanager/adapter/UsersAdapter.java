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
import com.vinson.mmanager.model.login.UserInfo;

import java.util.List;

public class UsersAdapter extends BaseDataListAdapter<UserInfo, UsersAdapter.UserViewHolder> {


    public UsersAdapter(List<UserInfo> data, Context context) {
        super(data, context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lift_list, parent, false);
        final UserViewHolder holder = new UserViewHolder(root);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserInfo info = mData.get(position);
        holder.name.setText(info.getRealName());
        holder.code.setText(info.getUuid());
        holder.owner.setText(info.getCompany().getFullName());
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView name, code, owner;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            owner = itemView.findViewById(R.id.tv_content);
        }
    }
}
