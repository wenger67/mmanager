package com.vinson.mmanager.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.login.UserInfo;
import com.vinson.mmanager.tools.Config;
import com.vinson.mmanager.base.BaseFragment;

public class AboutFragment extends BaseFragment {

    MaterialCardView mLogout;
    ShapeableImageView mUserHeader;
    MaterialTextView mUserName;
    MaterialTextView mBrief;

    public AboutFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    protected void initView(View root) {
        mLogout = root.findViewById(R.id.cv_logout);
        mUserHeader = root.findViewById(R.id.iv_avatar);
        mUserName = root.findViewById(R.id.tv_name);
        mBrief = root.findViewById(R.id.tv_brief);

        UserInfo userInfo = Config.getUserInfo();
        Glide.with(root).load(userInfo.avatar).into(mUserHeader);
        mUserName.setText(userInfo.realName);
        mBrief.setText(userInfo.phoneNumber);
    }

    @Override
    protected void initEvent() {
        mLogout.setOnClickListener(v -> {
            Config.setToken("");
            Config.setUserInfo(null);
            Config.setTokenExpire("");
            mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_LOGIN);
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
