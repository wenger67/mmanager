package com.vinson.mmanager.model;

public class BaseModel {
    protected int ID;
    protected String CreatedAt;
    protected String UpdatedAt;
    protected String DeletedAt;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return DeletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        DeletedAt = deletedAt;
    }
}
