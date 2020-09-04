package com.vinson.mmanager.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

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
        MaterialTextView textView = root.findViewById(R.id.tb_title);
        textView.setText("Message");
    }

    @Override
    protected void initEvent() {

    }
}
