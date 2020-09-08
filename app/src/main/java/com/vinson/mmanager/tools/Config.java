package com.vinson.mmanager.tools;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vinson.mmanager.App;
import com.vinson.mmanager.model.lift.LiftInfo;
import com.vinson.mmanager.model.login.UserInfo;

import static com.vinson.mmanager.utils.Constants.SP_KEY_FIRST_LAUNCH;

public class Config {

    public static final String SP_KEY_USER_TOKEN_EXPIRE = "user.token.expire";
    public static final String SP_KEY_USER_TOKEN = "user.token";
    public static final String SP_KEY_USER_INFO = "user.info";
    public static final String SP_KEY_LIFT_INFO = "lift.info";
    public static final String SP_KEY_DEVICE_SERIAL = "device.serial";
    public static final String SP_KEY_STREAM_ID = "stream.id";

    public static String getTokenExpire() {
        return App.getInstance().getSP().getString(SP_KEY_USER_TOKEN_EXPIRE, "");
    }

    public static void setTokenExpire(String tokenExpire) {
        if (tokenExpire.equals(getTokenExpire())) return;
        SharedPreferences.Editor editor = App.getInstance().getSP().edit();
        editor.putString(SP_KEY_USER_TOKEN_EXPIRE, tokenExpire);
        editor.apply();
    }


    public static String getToken() {
        return App.getInstance().getSP().getString(SP_KEY_USER_TOKEN, "");
    }

    public static void setToken(String token) {
        if (token.equals(getToken())) return;
        SharedPreferences.Editor editor = App.getInstance().getSP().edit();
        editor.putString(SP_KEY_USER_TOKEN, token);
        editor.apply();
    }

    public static UserInfo getUserInfo() {
        String json = App.getInstance().getSP().getString(SP_KEY_USER_INFO, "");
        return new Gson().fromJson(json, new TypeToken<UserInfo>() {
        }.getType());
    }

    public static void setUserInfo(UserInfo userInfo) {
        if (null != getUserInfo() && getUserInfo().equals(userInfo)) return;
        SharedPreferences.Editor editor = App.getInstance().getSP().edit();
        String json = new Gson().toJson(userInfo);
        editor.putString(SP_KEY_USER_INFO, json);
        editor.apply();
    }

    public static LiftInfo getLiftInfo() {
        String json = App.getInstance().getSP().getString(SP_KEY_LIFT_INFO, "");
        return new Gson().fromJson(json, new TypeToken<LiftInfo>() {
        }.getType());
    }

    public static void setLiftInfo(LiftInfo liftInfo) {
        if (liftInfo.equals(getLiftInfo())) return;
        SharedPreferences.Editor editor = App.getInstance().getSP().edit();
        String json = new Gson().toJson(liftInfo);
        editor.putString(SP_KEY_LIFT_INFO, json);
        editor.apply();
    }

    public static boolean getFirstLaunch() {
        return App.getInstance().getSP().getBoolean(SP_KEY_FIRST_LAUNCH, true);
    }

    public static void setFirstLaunch(boolean flag) {
        if (getFirstLaunch() == flag) return;
        SharedPreferences.Editor editor = App.getInstance().getSP().edit();
        editor.putBoolean(SP_KEY_FIRST_LAUNCH, flag);
        editor.apply();
    }
}
