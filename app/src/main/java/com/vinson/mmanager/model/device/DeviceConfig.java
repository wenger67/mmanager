package com.vinson.mmanager.model.device;

import com.vinson.mmanager.model.BaseModel;

public class DeviceConfig extends BaseModel {
    String key;
    String value;
    String comment;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }
}
