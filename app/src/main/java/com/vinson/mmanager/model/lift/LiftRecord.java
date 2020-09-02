package com.vinson.mmanager.model.lift;

import com.vinson.mmanager.model.BaseModel;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.login.UserInfo;

public class LiftRecord extends BaseModel {
    int liftId;
    LiftInfo lift;
    int categoryId;
    Category category;
    String images;
    String content;
    String startTime;
    String endTime;
    int workerId;
    UserInfo worker;
    int recorderId;
    UserInfo recorder;

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public UserInfo getWorker() {
        return worker;
    }

    public void setWorker(UserInfo worker) {
        this.worker = worker;
    }

    public int getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(int recorderId) {
        this.recorderId = recorderId;
    }

    public UserInfo getRecorder() {
        return recorder;
    }

    public void setRecorder(UserInfo recorder) {
        this.recorder = recorder;
    }
}
