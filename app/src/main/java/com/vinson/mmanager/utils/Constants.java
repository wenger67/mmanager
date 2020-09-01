package com.vinson.mmanager.utils;

import com.vinson.mmanager.BuildConfig;

public class Constants {
    public static final String BASE_URL = "http://" + BuildConfig.BaseUrl;
    public static final String WS_BASE_URL = "ws://" +  BuildConfig.BaseUrl;
    public static final String WS_PATH = "/api/ws/endpoint";

    public static final String PREFIX="LIFT_";


    /**
     * first launch flag
     * after first launch, set false
     */
    public static final String SP_KEY_FIRST_LAUNCH = "first.launch";


    /**
     * Arouter Constants
     */
    public static final String DATA_LIST_PARAM = "data.list.intent";

    public static final String AROUTER_PAGE_WELCOME = "/page/welcome";
    public static final String AROUTER_PAGE_SPLASH = "/page/splash";
    public static final String AROUTER_PAGE_LOGIN = "/page/login";
    public static final String AROUTER_PAGE_REGISTER = "/page/register";
    public static final String AROUTER_PAGE_MAIN = "/page/main";
    public static final String AROUTER_PAGE_LIFT_LIST = "/page/list/lifts";
    public static final String AROUTER_PAGE_LIFT_CHANGES = "/page/list/liftchanges";
    public static final String AROUTER_PAGE_LIFT_RECORDS = "/page/list/liftrecords";
    public static final String AROUTER_PAGE_LIFT_TROUBLES = "/page/list/lifttroubles";
}
