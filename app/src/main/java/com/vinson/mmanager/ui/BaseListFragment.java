package com.vinson.mmanager.ui;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.material.textview.MaterialTextView;
import com.mikepenz.iconics.view.IconicsImageView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.MyListAdapter;
import com.vinson.mmanager.base.BaseFragment;
import com.vinson.mmanager.model.lift.LiftRecord;
import com.vinson.mmanager.utils.Constants;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public class BaseListFragment extends BaseFragment {
    public static final int MSG_FETCH_DATA_START = 100;
    public static final int MSG_FETCH_DATA_FAILED = 101;
    public static final int MSG_FETCH_DATA_SUCCESS = 102;
    protected static final int FETCH_DATA_FAILED_MESSAGE_DELAY = 500;
    protected LinearLayout mRetry;
    protected IconicsImageView mBtnRetry;
    protected LinearLayout mLoading;
    protected AVLoadingIndicatorView mBtnLoading;
    protected ListView mListView;
    protected MaterialTextView mMore;
    protected MyListAdapter mAdapter;

    protected List<AbstractFlexibleItem> mItems = new ArrayList<>();

    protected Handler mHandler = new Handler(this::handleMessage);

    public BaseListFragment(int layoutResId) {
        super(layoutResId);
    }

    public BaseListFragment() {

    }

    @Override
    protected void initView(View root) {
        mRetry = root.findViewById(R.id.ll_lose_network);
        mBtnRetry = root.findViewById(R.id.btn_retry);
        mLoading = root.findViewById(R.id.ll_loading);
        mBtnLoading = root.findViewById(R.id.avi_loading);

        mMore = root.findViewById(R.id.tv_more);
        mListView = root.findViewById(R.id.lv);
        mAdapter = new MyListAdapter(new ArrayList<>(), mActivity);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }

    protected boolean handleMessage(Message message) {
        switch (message.what) {
            case MSG_FETCH_DATA_FAILED:
                ViewAnimator.animate(mLoading)
                        .alpha(1, 0).duration(500)
                        .onStop(() -> {
                            mRetry.setVisibility(View.VISIBLE);
                            mListView.setVisibility(View.GONE);
                            mMore.setVisibility(View.GONE);
                            mLoading.setVisibility(View.GONE);
                            mBtnLoading.hide();
                        })
                        .thenAnimate(mRetry)
                        .alpha(0, 1).duration(500).start();
                break;
            case MSG_FETCH_DATA_SUCCESS:
                ViewAnimator.animate(mLoading)
                        .alpha(1, 0).duration(500)
                        .onStop(() -> {
                            mLoading.setVisibility(View.GONE);
                            mBtnLoading.hide();
                            mRetry.setVisibility(View.GONE);
                            mListView.setVisibility(View.VISIBLE);
                            mMore.setVisibility(View.VISIBLE);
                        })
                        .thenAnimate(mListView)
                        .alpha(0, 1).duration(500).start();
                break;
            case MSG_FETCH_DATA_START:
                fetchData();
                break;
        }
        return false;
    }

    public void fetchData() {
    }

    @Override
    protected void initEvent() {
        mBtnRetry.setOnClickListener(v -> {
            ViewAnimator.animate(mRetry)
                    .alpha(1, 0).duration(500)
                    .onStop(() -> {
                        mLoading.setVisibility(View.VISIBLE);
                        mBtnLoading.show();
                        mRetry.setVisibility(View.GONE);
                        mListView.setVisibility(View.GONE);
                        mMore.setVisibility(View.GONE);
                    })
                    .thenAnimate(mLoading)
                    .alpha(0, 1).duration(500).start()
                    .onStop(() -> mHandler.sendEmptyMessage(MSG_FETCH_DATA_START));
        });

        mMore.setOnClickListener(v -> {
            ARouter.getInstance()
                    .build(Constants.AROUTER_PAGE_LIFT_RECORDS)
                    .navigation(mActivity);
        });
    }
}
