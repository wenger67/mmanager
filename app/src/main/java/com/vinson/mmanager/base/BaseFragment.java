package com.vinson.mmanager.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinson.mmanager.App;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {

    protected Handler mHandler;
    @LayoutRes
    private int mContentLayoutId = -1;
    private Activity mActivity;

    public BaseFragment() {
    }

    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
        mContentLayoutId = contentLayoutId;
    }

    protected abstract void initView(View root);

    protected abstract void initEvent();

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
        mHandler = ((BaseActivity) Objects.requireNonNull(getActivity())).mHandler;
    }


    public @LayoutRes
    int getLayoutRes() {
        return -1;
    }
    protected View root;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mContentLayoutId == -1) {
            root = inflater.inflate(getLayoutRes(), container, false);
        } else
            root = super.onCreateView(inflater, container, savedInstanceState);

        initView(root);
        initEvent();
        return root;
    }
}
