package com.vinson.mmanager.model.request;

public class LiftRecordCreate {
    public int liftId;
    public int categoryId;

    public LiftRecordCreate(int liftId, int categoryId) {
        this.liftId = liftId;
        this.categoryId = categoryId;
    }
}
