package com.vinson.mmanager.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinson.mmanager.App;
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

    private Activity mActivity;

    public Context getContext() {
        if (mActivity == null) {
            return App.getInstance();
        }
        return mActivity;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

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
        initView(view);
        initEvent();
        return view;
    }
}
