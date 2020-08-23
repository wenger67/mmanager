package com.vinson.mmanager.model.ui;

import androidx.annotation.IntDef;

public class DataListAnnotation {

    public static final int LIFT = 1;
    public static final int SMART_DEVICE = 2;
    public static final int COMPANY = 3;

    @IntDef({LIFT, SMART_DEVICE, COMPANY,})
    public @interface DataType {
    }
}
