package com.vinson.mmanager.ui.page;

import android.os.Message;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.utils.Constants;

@Route(path = Constants.AROUTER_PAGE_FILL_RECORD_CONTENT)
public class FillRecordContentActivity extends BaseActivity {


    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fill_record_content;
    }
}
