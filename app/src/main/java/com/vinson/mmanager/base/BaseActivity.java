package com.vinson.mmanager.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ethanhua.skeleton.SkeletonScreen;
import com.google.gson.Gson;
import com.vinson.mmanager.tools.NetworkObserver;

public abstract class BaseActivity extends AppCompatActivity implements NetworkObserver.Listener,
        NavigationCallback {

    public static final String TAG = BaseActivity.class.getSimpleName();
    public static final int MSG_LAUNCH_DATA_LIST = 1;
    public static final int MSG_LAUNCH_LOGIN = 2;
    protected static final int MSG_FETCH_LIST_DATA = 10;
    protected static final int MSG_NETWORK_CHANGE = 5;
    protected Gson mGson = new Gson();
    public NetworkObserver mNetwork;
    protected SkeletonScreen mSkeletonScreen;
    protected Handler mHandler = new Handler(this::handleMessage);

    protected abstract boolean handleMessage(Message message);

    public void routeTo(String url, Context context) {
        ARouter.getInstance().build(url).navigation(context, this);
    }

    public void routeTo(String url, Bundle bundle, Context context) {
        ARouter.getInstance().build(url).withBundle(url, bundle).navigation(context, this);
    }

    public void routeTo(String url, String key, Parcelable parcelable, Context context) {
        ARouter.getInstance().build(url).withParcelable(key, parcelable).navigation(context, this);
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

    protected abstract @LayoutRes
    int getLayoutRes();

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
