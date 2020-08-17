package com.vinson.mmanager.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.vinson.mmanager.tools.NetworkObserver;

public abstract class BaseActivity extends AppCompatActivity implements NetworkObserver.Listener, NavigationCallback {

    public NetworkObserver mNetwork;

    public void routeTo(String url, Context context) {
        ARouter.getInstance().build(url).navigation(context, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    public void networkChanged(boolean connected) {

    }

    @Override
    public void onFound(Postcard postcard) {

    }

    @Override
    public void onLost(Postcard postcard) {

    }

    @Override
    public void onArrival(Postcard postcard) {

    }

    @Override
    public void onInterrupt(Postcard postcard) {

    }
}
