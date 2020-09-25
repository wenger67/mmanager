package com.vinson.mmanager.model;


import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.base.DeletedAt;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class Company extends AbstractFlexibleItem<Company.CompanyViewHolder> {

    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public DeletedAt DeletedAt;
    public String fullName;
    public String alias;
    public String legalPerson;
    public String phone;
    public String status;
    public String regCode;
    public String orgCode;
    public String creditCode;
    public String taxCode;
    public String address;
    public Category category;

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
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, CompanyViewHolder holder,
                               int position, List<Object> payloads) {
        holder.alias.setText(alias);
        if (alias.length() < 4)
            holder.alias.setTextSize(20);
        else if (alias.length() < 6)
            holder.alias.setTextSize(19);
        else if (alias.length() < 8)
            holder.alias.setTextSize(18);

        holder.name.setText(fullName);
        holder.person.setText(legalPerson);
        holder.phone.setText(phone.equals("0") ? "---" : phone);
        holder.credit.setText(creditCode);
        holder.category.setText(category.categoryName);
        holder.address.setText(address);
    }

    static class CompanyViewHolder extends FlexibleViewHolder {
        MaterialTextView alias, name, person, phone, credit, category, address;

        public CompanyViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            alias = itemView.findViewById(R.id.tv_alias);
            name = itemView.findViewById(R.id.tv_category);
            person = itemView.findViewById(R.id.tv_person);
            phone = itemView.findViewById(R.id.tv_phone);
            credit = itemView.findViewById(R.id.tv_credit);
            category = itemView.findViewById(R.id.tv_category);
            address = itemView.findViewById(R.id.tv_address);
        }
    }
}
