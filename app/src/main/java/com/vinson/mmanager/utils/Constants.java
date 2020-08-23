package com.vinson.mmanager.utils;

public class Constants {
    public static final String BASE_URL = "http://192.168.1.3:8888";
    public static final String WS_BASE_URL = "ws://192.168.1.3:8888";
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
    public static final String AROUTER_PAGE_DATA_LIST = "/page/main/datalist";
}
