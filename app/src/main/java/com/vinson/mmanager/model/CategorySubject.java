package com.vinson.mmanager.model;

import com.vinson.mmanager.model.base.DeletedAt;

public class CategorySubject {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public DeletedAt DeletedAt;
    public String subjectName;

    public CategorySubject(String subjectName) {
        this.subjectName = subjectName;
    }
}
