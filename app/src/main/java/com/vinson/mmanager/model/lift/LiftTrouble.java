package com.vinson.mmanager.model.lift;

import com.vinson.mmanager.model.BaseModel;
import com.vinson.mmanager.model.Category;
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

    public int getLiftId() {
        return liftId;
    }

    public LiftInfo getLift() {
        return lift;
    }

    public int getFromCategoryId() {
        return fromCategoryId;
    }

    public Category getFromCategory() {
        return fromCategory;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getStartUserId() {
        return startUserId;
    }

    public UserInfo getStartUser() {
        return startUser;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public int getResponseUserId() {
        return responseUserId;
    }

    public UserInfo getResponseUser() {
        return responseUser;
    }

    public String getSceneTime() {
        return sceneTime;
    }

    public int getSceneUserId() {
        return sceneUserId;
    }

    public UserInfo getSceneUser() {
        return sceneUser;
    }

    public String getFixTime() {
        return fixTime;
    }

    public int getFixUserId() {
        return fixUserId;
    }

    public UserInfo getFixUser() {
        return fixUser;
    }

    public int getFixCategoryId() {
        return fixCategoryId;
    }

    public Category getFixCategory() {
        return fixCategory;
    }

    public int getReasonCategoryId() {
        return reasonCategoryId;
    }

    public Category getReasonCategory() {
        return reasonCategory;
    }

    public String getContent() {
        return content;
    }

    public int getProgress() {
        return progress;
    }

    public int getRecorderId() {
        return recorderId;
    }

    public UserInfo getRecorder() {
        return recorder;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public int getFeedbackRate() {
        return feedbackRate;
    }
}
