package com.vinson.mmanager.model.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ModuleType.MODULE_LIFT_LIST, ModuleType.MODULE_LIFT_CHANGES,
        ModuleType.MODULE_LIFT_RECORDS, ModuleType.MODULE_LIFT_TROUBLE})
@Retention(RetentionPolicy.SOURCE)
public @interface ModuleType {
    int MODULE_LIFT_LIST = 1;
    int MODULE_LIFT_CHANGES = 2;
    int MODULE_LIFT_RECORDS = 3;
    int MODULE_LIFT_TROUBLE = 4;

}
