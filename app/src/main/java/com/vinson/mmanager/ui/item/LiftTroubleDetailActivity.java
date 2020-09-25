package com.vinson.mmanager.ui.item;

import android.os.Message;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.utils.Constants;

@Route(path = Constants.AROUTER_PAGE_LIFT_TROUBLE_DETAIL)
public class LiftTroubleDetailActivity extends BaseActivity {

    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }
}
