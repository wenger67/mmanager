package com.vinson.mmanager.model.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ModuleType.MODULE_HOME_HEADER_CAMERA, ModuleType.MODULE_HOME_HEADER_HELP,
        ModuleType.MODULE_HOME_HEADER_USERS,
        ModuleType.MODULE_LIFT_LIST, ModuleType.MODULE_LIFT_CHANGES,
        ModuleType.MODULE_LIFT_RECORDS, ModuleType.MODULE_LIFT_TROUBLE,
        ModuleType.MODULE_DEVICE_LIST, ModuleType.MODULE_DEVICE_EVENT,
        ModuleType.MODULE_DEVICE_DATA,
        ModuleType.MODULE_DEVICE_CONFIG, ModuleType.MODULE_COMPANY_LIST,
        ModuleType.MODULE_BUTTON_ALL})
@Retention(RetentionPolicy.SOURCE)
public @interface ModuleType {
    int MODULE_HOME_HEADER_CAMERA = 1;
    int MODULE_HOME_HEADER_HELP = 2;
    int MODULE_HOME_HEADER_USERS = 3;

    int MODULE_LIFT_LIST = 10;
    int MODULE_LIFT_CHANGES = 11;
    int MODULE_LIFT_RECORDS = 12;
    int MODULE_LIFT_TROUBLE = 13;

    int MODULE_DEVICE_LIST = 101;
    int MODULE_DEVICE_EVENT = 102;
    int MODULE_DEVICE_DATA = 103;
    int MODULE_DEVICE_CONFIG = 104;

    int MODULE_COMPANY_LIST = 201;

    int MODULE_BUTTON_ALL = 999;

}
