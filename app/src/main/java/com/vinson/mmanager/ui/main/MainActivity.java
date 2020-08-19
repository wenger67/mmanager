package com.vinson.mmanager.ui.main;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.TableLayout;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.vinson.mmanager.App;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.SimpleFragmentPagerAdapter;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.services.WSService;
import com.vinson.mmanager.utils.Constants;

@Route(path = Constants.AROUTER_PAGE_MAIN)
public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final int MSG_NETWORK_CHANGE = 5;

    WSService.Binder wsService;
    ViewPager mViewPager;
    TabLayout mTabLayout;
    private SimpleFragmentPagerAdapter mPagerAdapter;

    private Handler mHandler = new Handler(this::handleMessage);
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            wsService = (WSService.Binder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            wsService.setCallback(null);
            wsService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Intent intent = new Intent(this, WSService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);

        mPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewPager = findViewById(R.id.vp);
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout = findViewById(R.id.tl);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean handleMessage(Message msg) {
        if (isFinishing() || isDestroyed()) return true;
        switch (msg.what) {
            case MSG_NETWORK_CHANGE:
                break;
        }
        return false;
    }

    @Override
    public void networkChanged(boolean connected) {
        super.networkChanged(connected);
        if (connected) {
            App.getInstance().showToast("Network connected");
        } else {
            App.getInstance().showToast("Network disconnected");
        }
    }
}