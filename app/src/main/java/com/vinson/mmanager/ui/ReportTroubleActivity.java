package com.vinson.mmanager.ui;

import android.os.Bundle;
import android.os.Message;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.utils.Constants;

@Route(path = Constants.AROUTER_PAGE_CREATE_LIFT_TROUBLE)
public class ReportTroubleActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        mTitle.setText(getResources().getString(R.string.title_report_trouble));
    }

    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_report_lift_trouble;
    }
}
