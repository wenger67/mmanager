package com.vinson.mmanager.model;


import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class Company extends AbstractFlexibleItem<Company.CompanyViewHolder> {

    int ID;
    String CreatedAt;
    String UpdatedAt;
    String DeletedAt;
    String fullName;
    String alias;
    String legalPerson;
    String phone;
    String status;
    String regCode;
    String orgCode;
    String creditCode;
    String taxCode;
    String address;
    Category category;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_company;
    }

    @Override
    public CompanyViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new CompanyViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, CompanyViewHolder holder, int position, List<Object> payloads) {
        holder.name.setText(fullName);
        holder.code.setText(creditCode);
        holder.owner.setText(legalPerson);
    }

    static class CompanyViewHolder extends FlexibleViewHolder {
        MaterialTextView name, code, owner;
        public CompanyViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            owner = itemView.findViewById(R.id.tv_content);
        }
    }
}
