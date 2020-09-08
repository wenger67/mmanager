package com.vinson.mmanager.base;

import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.Skeleton;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.ProgressItem;
import com.vinson.mmanager.ui.view.CustomList;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.FlexibleItemDecoration;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public abstract class BaseListActivity extends BaseActivity {
    protected CustomList mCustomList;
    protected List<AbstractFlexibleItem> mItems = new ArrayList<>();
    protected FlexibleAdapter<AbstractFlexibleItem> mAdapter;
    int lastPos = 0;
    protected int curPage = 0;

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
    protected void initEvent() {
        super.initEvent();

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
                mHandler.sendEmptyMessage(MSG_FETCH_LIST_DATA);
            }


        }, new ProgressItem()).
                setEndlessPageSize(10).
                setLoadingMoreAtStartUp(true).
                setEndlessScrollThreshold(1).
                setTopEndless(false);

        mAdapter.addListener(mItemClickListener);
    }

    FlexibleAdapter.OnItemClickListener mItemClickListener = this::itemClick;
    protected boolean itemClick(View view, int position){return false;}
}
