package com.vinson.mmanager.ui.login;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinson.mmanager.ui.main.MainActivity;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {

    protected Handler mHandler;

    public BaseFragment() {
    }

    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    protected abstract void initView(View root);

    protected abstract void initEvent();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = ((MainActivity) Objects.requireNonNull(getActivity())).getHandler();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;
        initView(view);
        initEvent();
        return view;
    }
}
