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
import com.blankj.utilcode.util.BarUtils;
import com.ethanhua.skeleton.SkeletonScreen;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.vinson.mmanager.R;
import com.vinson.mmanager.tools.NetworkObserver;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements NetworkObserver.Listener,
        NavigationCallback {

    public static final String TAG = BaseActivity.class.getSimpleName();
    public static final int MSG_LAUNCH_LIFT_LIST = 1;
    public static final int MSG_LAUNCH_LOGIN = 2;
    public static final int MSG_LAUNCH_LIFT_CHANGES = 11;
    public static final int MSG_LAUNCH_LIFT_RECORDS = 12;
    public static final int MSG_LAUNCH_LIFT_TROUBLES = 13;
    public static final int MSG_LAUNCH_USERS = 14;
    public static final int MSG_LAUNCH_DEVICE_LIST = 101;
    public static final int MSG_LAUNCH_DEVICE_EVENT = 102;
    public static final int MSG_LAUNCH_DEVICE_DATA = 103;
    public static final int MSG_LAUNCH_DEVICE_CONFIG = 104;
    public static final int MSG_LAUNCH_COMPANY_LIST = 201;

    public static final int MSG_NETWORK_CHANGE = 1001;
    public static final int MSG_FETCH_DATA = 1002;
    public static final int MSG_FETCH_DATA_FAILED = 1003;

    public NetworkObserver mNetwork;
    protected Gson mGson = new Gson();
    protected SkeletonScreen mSkeletonScreen;
    protected Handler mHandler = new Handler(this::handleMessage);

    protected MaterialToolbar mMaterialToolbar;
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
    protected Bundle mBundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        BarUtils.setStatusBarLightMode(this, true);
        mBundle = savedInstanceState;
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

    protected void initView() {
        mMaterialToolbar = findViewById(R.id.tb);
        setToolbarTitle();

        setSupportActionBar(mMaterialToolbar);
        mMaterialToolbar.setNavigationIcon(new IconicsDrawable(this,
                CommunityMaterial.Icon.cmd_arrow_left).sizeDp(16).color(getResources().getColor(android.R.color.black)));
    }

    protected void setToolbarTitle(){

    }

    protected void initEvent(){
        mMaterialToolbar.setNavigationOnClickListener(v -> finish());
    }

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
