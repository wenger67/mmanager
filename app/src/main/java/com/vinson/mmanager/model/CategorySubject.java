package com.vinson.mmanager.model;

public class CategorySubject {
        int ID;
    String CreatedAt;
    String UpdatedAt;
    String DeletedAt;
    String subjectName;

    public CategorySubject(String subjectName) {
        this.subjectName = subjectName;
    }
}
