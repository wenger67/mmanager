package com.vinson.mmanager.ui;

import android.os.Bundle;
import android.os.Message;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.utils.Constants;

@Route(path = Constants.AROUTER_PAGE_CREATE_LIFT_RECORD)
public class CreateRecordActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void setToolbarTitle() {
        mMaterialToolbar.setTitle(getResources().getString(R.string.title_create_record));
    }

    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_create_lift_record;
    }
}
