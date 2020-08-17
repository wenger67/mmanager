package com.vinson.mmanager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.vinson.mmanager.tools.CrashHandler;
import com.vinson.mmanager.utils.ToastCompat;

import im.zego.zegoexpress.ZegoExpressEngine;
import im.zego.zegoexpress.constants.ZegoScenario;

public class App extends Application {
    private static final String SP_NAME = "config";
    private static App INSTANCE;
    private SharedPreferences sp;
    private static ZegoExpressEngine ENGINE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        sp = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        // register crash handler
        CrashHandler.getInstance().init();

        ENGINE = ZegoExpressEngine.createEngine(BuildConfig.ZegoAppId, BuildConfig.ZegoAppSign,
                true, ZegoScenario.GENERAL, this, null);
    }

    public static App getInstance() {
        return INSTANCE;
    }

    public static ZegoExpressEngine getEngine() {
        return ENGINE;
    }

    public void showToast(String msg) {
        Log.d("App", "showToast:" + msg);
        ToastCompat.showToast(this, msg);
    }

    public SharedPreferences getSP() {
        return sp;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ZegoExpressEngine.destroyEngine(null);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
