package com.vinson.mmanager.model.device;

import com.vinson.mmanager.model.BaseModel;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.Company;
import com.vinson.mmanager.model.login.UserInfo;

public class Device extends BaseModel {
    int typeId;
    Category type;
    int factoryId;
    Company factory;
    String factoryTime;
    String installTime;
    int statusId;
    Category status;
    boolean online;
    String lastOfflineTime;
    String lastOnlineTime;
    UserInfo[] owners;
    DeviceConfig[] configs;

    public int getTypeId() {
        return typeId;
    }

    public Category getType() {
        return type;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public Company getFactory() {
        return factory;
    }

    public String getFactoryTime() {
        return factoryTime;
    }

    public String getInstallTime() {
        return installTime;
    }

    public int getStatusId() {
        return statusId;
    }

    public Category getStatus() {
        return status;
    }

    public boolean isOnline() {
        return online;
    }

    public String getLastOfflineTime() {
        return lastOfflineTime;
    }

    public String getLastOnlineTime() {
        return lastOnlineTime;
    }

    public UserInfo[] getOwners() {
        return owners;
    }

    public DeviceConfig[] getConfigs() {
        return configs;
    }
}
