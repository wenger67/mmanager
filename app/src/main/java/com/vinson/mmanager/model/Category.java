package com.vinson.mmanager.model;

import javax.security.auth.Subject;

public class Category extends BaseModel{

    private String categoryName;
    private CategorySubject categorySubject;

    public Category(String categoryName, CategorySubject categorySubject) {
        this.categoryName = categoryName;
        this.categorySubject = categorySubject;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategorySubject getCategorySubject() {
        return categorySubject;
    }

    public void setCategorySubject(CategorySubject categorySubject) {
        this.categorySubject = categorySubject;
    }
}
