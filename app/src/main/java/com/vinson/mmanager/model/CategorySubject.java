package com.vinson.mmanager.model;

public class CategorySubject extends BaseModel{
    private String subjectName;

    public CategorySubject(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
