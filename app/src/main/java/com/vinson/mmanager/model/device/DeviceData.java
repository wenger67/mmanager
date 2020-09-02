package com.vinson.mmanager.model.device;

import com.vinson.mmanager.model.BaseModel;
import com.vinson.mmanager.model.Category;

public class DeviceData extends BaseModel {

    int deviceId;
    Device device;
    int troubleId;
    Category trouble;
    float accx;
    float accy;
    float accz;
    float degx;
    float degy;
    float degz;
    float speedz;
    float floor;
    int doorStateId;
    Category doorState;
    boolean peopleInside;

    public int getDeviceId() {
        return deviceId;
    }

    public Device getDevice() {
        return device;
    }

    public int getTroubleId() {
        return troubleId;
    }

    public Category getTrouble() {
        return trouble;
    }

    public float getAccx() {
        return accx;
    }

    public float getAccy() {
        return accy;
    }

    public float getAccz() {
        return accz;
    }

    public float getDegx() {
        return degx;
    }

    public float getDegy() {
        return degy;
    }

    public float getDegz() {
        return degz;
    }

    public float getSpeedz() {
        return speedz;
    }

    public float getFloor() {
        return floor;
    }

    public int getDoorStateId() {
        return doorStateId;
    }

    public Category getDoorState() {
        return doorState;
    }

    public boolean isPeopleInside() {
        return peopleInside;
    }
}
