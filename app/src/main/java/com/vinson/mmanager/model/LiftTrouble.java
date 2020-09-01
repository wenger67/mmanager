package com.vinson.mmanager.model;

import com.vinson.mmanager.model.login.UserInfo;

public class LiftTrouble extends BaseModel {
    int liftId;
    LiftInfo lift;
    int fromCategoryId;
    Category fromCategory;
    String startTime;
    int startUserId;
    UserInfo startUser;
    String responseTime;
    int responseUserId;
    UserInfo responseUser;
    String sceneTime;
    int sceneUserId;
    UserInfo sceneUser;
    String fixTime;
    int fixUserId;
    UserInfo fixUser;
    int fixCategoryId;
    Category fixCategory;
    int reasonCategoryId;
    Category reasonCategory;
    String content;
    int progress;
    int recorderId;
    UserInfo recorder;
    String feedbackContent;
    int feedbackRate;
}
