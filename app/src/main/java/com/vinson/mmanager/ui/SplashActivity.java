package com.vinson.mmanager.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.vinson.mmanager.App;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.tools.Config;
import com.vinson.mmanager.utils.Constants;

@Route(path = Constants.AROUTER_PAGE_SPLASH)
public class SplashActivity extends BaseActivity implements NavigationCallback {
    public static final String TAG = SplashActivity.class.getSimpleName();

    private static final String[] mPermissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_CODE_DEFAULT = 1;
    private static final int MSG_LAUNCH_MAIN = 1;
    private static final int MSG_LAUNCH_LOGIN = 2;
    private static final int MSG_LAUNCH_WELCOME = 3;

    private static final int REQUEST_CODE = 200;

    private boolean mPermissionGranted = false;
    private Handler mHandler = new Handler(this::handleMessage);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TAG);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkPermission()) {
            Log.w(TAG, "permission not granted, raise up request tips");
            requestPermissions(mPermissions, REQUEST_CODE_DEFAULT);
        } else {
            // SDK < M, permission granted automatic

            // in case that user exit app before complete read welcome intro
            // goto welcome into again
            if (mPermissionGranted && Config.getFirstLaunch()) {
                checkFirstLaunch();
            } else {
                checkToken();
            }
        }
    }

    private void checkToken() {
        if (Config.getToken().isEmpty()) {
            // not login yet, goto login page
            mHandler.removeCallbacksAndMessages(null);
            mHandler.sendEmptyMessage(MSG_LAUNCH_LOGIN);
        } else {
            mHandler.removeCallbacksAndMessages(null);
            mHandler.sendEmptyMessage(MSG_LAUNCH_MAIN);
        }
    }

    private void checkFirstLaunch() {
        if (Config.getFirstLaunch()) {
            // nav to welcome page
            mHandler.removeCallbacksAndMessages(null);
            mHandler.sendEmptyMessage(MSG_LAUNCH_WELCOME);
        }
    }

    @Override
    public void networkChanged(boolean connected) {
        super.networkChanged(connected);
        App.getInstance().showToast("network connected:" + connected);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkPermission() {
        for (String permission : mPermissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        mPermissionGranted = true;
        return true;
    }

    private boolean handleMessage(Message msg) {
        if (isFinishing() || isDestroyed()) return true;
        switch (msg.what) {
            case MSG_LAUNCH_MAIN:
                Log.d(TAG, "launch MainActivity");
                synchronized (SplashActivity.this) {
                    routeTo(Constants.AROUTER_PAGE_MAIN, SplashActivity.this);
                }
                break;
            case MSG_LAUNCH_LOGIN:
                Log.d(TAG, "launch LoginActivity");
                synchronized (SplashActivity.this) {
                    routeTo(Constants.AROUTER_PAGE_LOGIN, SplashActivity.this);
                }
                break;
            case MSG_LAUNCH_WELCOME:
                Log.d(TAG, "launch welcome");
                synchronized (SplashActivity.this) {
                    routeTo(Constants.AROUTER_PAGE_WELCOME, SplashActivity.this);
                }
                break;
        }
        return false;
    }

    @Override
    public void onArrival(Postcard postcard) {
        Log.d(TAG, postcard.getPath());
        // finish self after router dispatch success
        SplashActivity.this.finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_DEFAULT) {
            boolean granted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    granted = false;
                    break;
                }
            }

            if (granted) {
                // just after granted
                checkFirstLaunch();
                mPermissionGranted = true;
            } else {
                Log.e(TAG, "permission request rejected");
                App.getInstance().showToast("无法获取摄像头权限和媒体访问权限");
                SplashActivity.this.finish();
            }
        }
    }

}
