package com.vinson.mmanager.utils;

import com.vinson.mmanager.BuildConfig;

public class Constants {
    public static final String BASE_URL = "http://" + BuildConfig.BaseUrl;
    public static final String WS_BASE_URL = "ws://" +  BuildConfig.BaseUrl;
    public static final String WS_PATH = "/api/ws/endpoint";

    public static final String PREFIX_LIFT="LIFT_";
    public static final String PREFIX_USER="USER_";


    /**
     * first launch flag
     * after first launch, set false
     */
    public static final String SP_KEY_FIRST_LAUNCH = "first.launch";


    /**
     * Arouter Constants
     */
    public static final String DATA_LIST_PARAM = "data.list.intent";

    public static final String AROUTE_PAGE_VIDEO_ROOM = "/page/video";

    public static final String AROUTER_PAGE_WELCOME = "/page/welcome";
    public static final String AROUTER_PAGE_SPLASH = "/page/splash";
    public static final String AROUTER_PAGE_LOGIN = "/page/login";
    public static final String AROUTER_PAGE_REGISTER = "/page/register";
    public static final String AROUTER_PAGE_MAIN = "/page/main";
    /**
     * list
     */
    public static final String AROUTER_PAGE_LIFT_LIST = "/page/list/lifts";
    public static final String AROUTER_PAGE_LIFT_CHANGES = "/page/list/liftchanges";
    public static final String AROUTER_PAGE_LIFT_RECORDS = "/page/list/liftrecords";
    public static final String AROUTER_PAGE_LIFT_TROUBLES = "/page/list/lifttroubles";
    public static final String AROUTER_PAGE_USERS = "/page/list/users";
    public static final String AROUTER_PAGE_DEVICE_LIST = "/page/list/devices";
    public static final String AROUTER_PAGE_DEVICE_EVENT = "/page/list/deviceevents";
    public static final String AROUTER_PAGE_DEVICE_DATA = "/page/list/devicedatas";
    public static final String AROUTER_PAGE_DEVICE_CONFIG = "/page/list/deviceconfigs";
    public static final String AROUTER_PAGE_COMPANY_LIST = "/page/list/companys";
    /**
     * item detail
     */
    public static final String AROUTER_PAGE_LIFT_DETAIL = "/page/item/lift";
    public static final String AROUTER_PAGE_LIFT_TROUBLE_DETAIL = "/page/item/lift/trouble";

    /**
     * websocket message what
     */

    public static final String WS_REQUEST_PUSH_STREAM = "request.push.stream";
    public static final String WS_REQUEST_STOP_PUSH_STREAM = "request.stop.push.stream";
    public static final String WS_REMOTE_PUSH_STREAM_SUCCESS = "push.stream.success";
    public static final String WS_REMOTE_STOP_STREAM_SUCCESS = "stop.push.stream.success";
}
