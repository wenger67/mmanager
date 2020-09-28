package com.vinson.mmanager.base;

import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.Skeleton;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.material.textview.MaterialTextView;
import com.mikepenz.iconics.view.IconicsImageView;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.ProgressItem;
import com.vinson.mmanager.ui.view.CustomList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.FlexibleItemDecoration;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public abstract class BaseListActivity extends BaseActivity {
    protected static final int FETCH_DATA_FAILED_MESSAGE_DELAY = 500;
    protected CustomList mCustomList;
    protected List<AbstractFlexibleItem> mItems = new ArrayList<>();
    protected FlexibleAdapter<AbstractFlexibleItem> mAdapter;
    protected int curPage = 0;
    @BindView(R.id.tv_tab_title)
    protected MaterialTextView mTitle;
    int lastPos = 0;
    @BindView(R.id.ll_lose_network)
    LinearLayout mRetry;
    @BindView(R.id.btn_retry)
    IconicsImageView mBtnRetry;
    FlexibleAdapter.OnItemClickListener mItemClickListener = this::itemClick;

    @Override
    protected void initView() {
        super.initView();
        mCustomList = findViewById(R.id.rcv);
        mCustomList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mCustomList.addItemDecoration(new FlexibleItemDecoration(this).withOffset(1).withDefaultDivider());
        mCustomList.setHasFixedSize(true);
        mCustomList.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new FlexibleAdapter<>(null);
    }

    @Override
    protected void setToolbarTitle() {
        super.setToolbarTitle();
    }

    @Override
    protected boolean handleMessage(Message message) {
        switch (message.what) {
            case MSG_FETCH_DATA_FAILED:
                ViewAnimator.animate(mCustomList)
                        .alpha(1, 0).duration(500)
                        .onStop(() -> mRetry.setVisibility(View.VISIBLE))
                        .thenAnimate(mRetry)
                        .alpha(0, 1).duration(500).start()
                        .onStop(() -> {
                            mSkeletonScreen.hide();
                            mCustomList.setVisibility(View.GONE);
                        });
                break;
            case MSG_FETCH_DATA:
                fetchData();
                break;
        }
        return false;
    }

    public void fetchData() {
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBtnRetry.setOnClickListener(v -> {
            ViewAnimator.animate(mRetry)
                    .alpha(1, 0).duration(500)
                    .onStop(() -> {
                        mRetry.setVisibility(View.GONE);
                        mCustomList.setVisibility(View.VISIBLE);
                        mSkeletonScreen.show();
                    })
                    .thenAnimate(mCustomList)
                    .alpha(0, 1).duration(500).start()
                    .onStop(() -> mHandler.sendEmptyMessage(MSG_FETCH_DATA));
        });

        mSkeletonScreen = Skeleton.bind(mCustomList)
                .adapter(mAdapter)
                .load(R.layout.item_user_skeleton)
                .show();

        mAdapter.setEndlessScrollListener(new FlexibleAdapter.EndlessScrollListener() {
            @Override
            public void noMoreLoad(int newItemsSize) {
                KLog.d(newItemsSize);
            }

            @Override
            public void onLoadMore(int lastPosition, int currentPage) {
                KLog.d(lastPosition + ", " + currentPage);
                lastPos += lastPosition;
                curPage = currentPage;
                mHandler.sendEmptyMessage(MSG_FETCH_DATA);
            }


        }, new ProgressItem()).
                setEndlessPageSize(10).
                setLoadingMoreAtStartUp(true).
                setEndlessScrollThreshold(1).
                setTopEndless(false);

        mAdapter.addListener(mItemClickListener);
    }

    protected boolean itemClick(View view, int position) {
        return false;
    }
}
