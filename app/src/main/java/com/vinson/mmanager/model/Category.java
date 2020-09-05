package com.vinson.mmanager.model;

public class Category {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public String DeletedAt;
    public String categoryName;
    public CategorySubject categorySubject;

    public Category(String categoryName, CategorySubject categorySubject) {
        this.categoryName = categoryName;
        this.categorySubject = categorySubject;
    }
}
