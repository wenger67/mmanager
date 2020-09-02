package com.vinson.mmanager.model.device;

import com.vinson.mmanager.model.BaseModel;
import com.vinson.mmanager.model.Category;

public class DeviceEvent extends BaseModel {
    int deviceId;
    Device device;
    int typeId;
    Category type;
    String content;

    public int getDeviceId() {
        return deviceId;
    }

    public Device getDevice() {
        return device;
    }

    public int getTypeId() {
        return typeId;
    }

    public Category getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
