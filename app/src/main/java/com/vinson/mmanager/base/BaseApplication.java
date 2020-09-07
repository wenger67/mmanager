package com.vinson.mmanager.base;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.vinson.mmanager.BuildConfig;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.utils.Log;

public class BaseApplication extends MultiDexApplication {

    public static BaseApplication application;

    public static BaseApplication getApplication() {
        return application;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        // TODO initialize third-part libraries
        // arouter
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        KLog.init(true);

        FlexibleAdapter.useTag("aaaaaa");
        FlexibleAdapter.enableLogs(Log.Level.VERBOSE);
    }
}
