package com.vinson.mmanager.model.lift;

import com.vinson.mmanager.model.BaseModel;

public class LiftChange extends BaseModel {
    int liftId;
    LiftInfo lift;
    String content;

    public LiftChange(int liftId, LiftInfo lift, String content) {
        this.liftId = liftId;
        this.lift = lift;
        this.content = content;
    }

    public int getLiftId() {
        return liftId;
    }

    public void setLiftId(int liftId) {
        this.liftId = liftId;
    }

    public LiftInfo getLift() {
        return lift;
    }

    public void setLift(LiftInfo lift) {
        this.lift = lift;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
