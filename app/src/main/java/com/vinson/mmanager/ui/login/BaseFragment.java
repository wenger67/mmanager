package com.vinson.mmanager.ui.login;

import android.view.View;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    protected abstract void initView(View root);
    protected abstract void initEvent();
}
