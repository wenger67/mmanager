package com.vinson.mmanager.model;

import com.vinson.mmanager.model.base.DeletedAt;

public class Category {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public DeletedAt DeletedAt;
    public String categoryName;
    public CategorySubject categorySubject;

    public Category(String categoryName, CategorySubject categorySubject) {
        this.categoryName = categoryName;
        this.categorySubject = categorySubject;
    }
}
