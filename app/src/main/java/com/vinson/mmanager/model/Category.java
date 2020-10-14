package com.vinson.mmanager.model;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.base.TimeWrapper;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class Category extends AbstractFlexibleItem<Category.CategoryViewHolder> {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public TimeWrapper TimeWrapper;
    public String categoryName;
    public CategorySubject categorySubject;

    public Category(String categoryName, CategorySubject categorySubject) {
        this.categoryName = categoryName;
        this.categorySubject = categorySubject;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_category;
    }

    @Override
    public CategoryViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new CategoryViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, CategoryViewHolder holder, int position, List<Object> payloads) {
        holder.name.setText(categoryName);
        holder.subject.setText(categorySubject.subjectName);
    }

    static class CategoryViewHolder extends FlexibleViewHolder {
        MaterialTextView name, subject;

        public CategoryViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            name = itemView.findViewById(R.id.tv_name);
            subject = itemView.findViewById(R.id.tv_subject);
        }
    }

}
