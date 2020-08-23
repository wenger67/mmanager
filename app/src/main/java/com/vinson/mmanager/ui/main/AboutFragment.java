package com.vinson.mmanager.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.tools.Config;
import com.vinson.mmanager.ui.login.BaseFragment;

import java.util.Objects;

public class AboutFragment extends BaseFragment {

    MaterialButton mLogout;
    Handler mHandler;

    @Override
    protected void initView(View root) {
        mLogout = root.findViewById(R.id.btn_logout);
    }

    @Override
    protected void initEvent() {
        mLogout.setOnClickListener(v -> {
            KLog.d();
            Config.setToken("");
            Config.setUserInfo(null);
            Config.setTokenExpire("");
            mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_LOGIN);
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = ((MainActivity) Objects.requireNonNull(getActivity())).getHandler();
    }

    public AboutFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
