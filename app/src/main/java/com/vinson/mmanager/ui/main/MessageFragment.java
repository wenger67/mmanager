package com.vinson.mmanager.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseFragment;

public class MessageFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public MessageFragment() {
    }

    public MessageFragment(int contentLayoutId) {
        super(contentLayoutId);
    }


    @Override
    protected void initView(View root) {
        MaterialTextView title = root.findViewById(R.id.tv_tab_title);
        title.setText("Message");
    }

    @Override
    protected void initEvent() {

    }
}
