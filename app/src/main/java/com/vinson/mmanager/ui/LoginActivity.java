package com.vinson.mmanager.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.utils.Constants;

@Route(path = Constants.AROUTER_PAGE_LOGIN)
public class LoginActivity extends BaseActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TAG);
        setContentView(R.layout.activity_login);
    }


}
