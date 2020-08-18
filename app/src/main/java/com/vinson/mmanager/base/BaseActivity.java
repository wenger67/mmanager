package com.vinson.mmanager.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.vinson.mmanager.tools.NetworkObserver;

public abstract class BaseActivity extends AppCompatActivity implements NetworkObserver.Listener, NavigationCallback {
    public static final String TAG = BaseActivity.class.getSimpleName();
    public NetworkObserver mNetwork;

    public void routeTo(String url, Context context) {
        ARouter.getInstance().build(url).navigation(context, this);
    }
    public void routeTo(String url, Bundle bundle , Context context) {
        ARouter.getInstance().build(url).withBundle(url, bundle).navigation(context, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        initView();
        initEvent();
        // TODO add some common logic
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNetwork = new NetworkObserver(this);
        mNetwork.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mNetwork != null) {
            mNetwork.unregister();
            mNetwork = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mNetwork != null) {
            mNetwork.unregister();
            mNetwork = null;
        }
    }

    protected abstract @LayoutRes int getLayoutRes();
    protected abstract void initView();
    protected abstract void initEvent();

    @Override
    public void networkChanged(boolean connected) {

    }

    @Override
    public void onFound(Postcard postcard) {
        Log.d(TAG, "onFound");
        Log.d(TAG, postcard.toString());
    }

    @Override
    public void onLost(Postcard postcard) {
        Log.d(TAG, "onLost");
        Log.d(TAG, postcard.toString());
    }

    @Override
    public void onArrival(Postcard postcard) {
        Log.d(TAG, "onArrival");
        Log.d(TAG, postcard.toString());
    }

    @Override
    public void onInterrupt(Postcard postcard) {
        Log.d(TAG, "onInterrupt");
        Log.d(TAG, postcard.toString());
    }
}
