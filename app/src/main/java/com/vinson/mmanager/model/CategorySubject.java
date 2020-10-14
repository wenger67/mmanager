package com.vinson.mmanager.model;

import com.vinson.mmanager.model.base.TimeWrapper;

public class CategorySubject {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public TimeWrapper TimeWrapper;
    public String subjectName;

    public CategorySubject(String subjectName) {
        this.subjectName = subjectName;
    }
}
