package com.vinson.mmanager.model;

import com.vinson.mmanager.model.login.UserInfo;

public class LiftRecord extends BaseModel{
    int liftId;
    LiftInfo lift;
    int categoryId;
    Category mCategory;
    String images;
    String content;
    String startTime;
    String endTime;
    int workerId;
    UserInfo worker;
    int recorderId;
    UserInfo recorder;
}
