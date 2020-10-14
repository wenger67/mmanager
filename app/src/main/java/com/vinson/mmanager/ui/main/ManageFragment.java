package com.vinson.mmanager.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.home.SimpleFragmentPagerAdapter;
import com.vinson.mmanager.base.BaseFragment;
import com.vinson.mmanager.ui.FinishedRecordListFragment;
import com.vinson.mmanager.ui.ProcessRecordListFragment;
import com.vinson.mmanager.ui.UnStartRecordListFragment;
import com.vinson.mmanager.ui.view.CustomViewPager;
import com.vinson.mmanager.utils.Constants;

public class ManageFragment extends BaseFragment {
    SegmentTabLayout mTabs;
    CustomViewPager mPager;
    MaterialCardView mCreateLiftRecord;
    MaterialCardView mReportLiftTrouble;
    private String[] mTitles = new String[]{"待处理", "处理中", "已完结"};

    public ManageFragment() {
    }
    public ManageFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View root) {
        MaterialTextView title = root.findViewById(R.id.tv_tab_title);
        title.setText(R.string.tab_home_manage);

        mCreateLiftRecord = root.findViewById(R.id.cv_create_record);
        mReportLiftTrouble = root.findViewById(R.id.cv_create_report);

        mTabs = root.findViewById(R.id.tab_list);
        mPager = root.findViewById(R.id.vp_list);
        mPager.setCanScroll(true);
        // fix issue : use getChildFragmentManager() for viewpager item dismiss
        SimpleFragmentPagerAdapter adapter =
                new SimpleFragmentPagerAdapter(this.getChildFragmentManager(), getActivity(), 3);
        adapter.addFragment(new UnStartRecordListFragment(R.layout.fragment_list));
        adapter.addFragment(new ProcessRecordListFragment(R.layout.fragment_list));
        adapter.addFragment(new FinishedRecordListFragment(R.layout.fragment_list));

        mPager.setAdapter(adapter);
        mTabs.setTabData(mTitles);
    }

    @Override
    public void onResume() {
        super.onResume();
        KLog.d(mPager.getChildCount());
    }

    @Override
    protected void initEvent() {
        mTabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabs.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mCreateLiftRecord.setOnClickListener(v -> {
            ARouter.getInstance()
                    .build(Constants.AROUTER_PAGE_CREATE_LIFT_RECORD)
                    .navigation(mActivity);
        });

        mReportLiftTrouble.setOnClickListener(v -> {
            ARouter.getInstance()
                    .build(Constants.AROUTER_PAGE_CREATE_LIFT_TROUBLE)
                    .navigation(mActivity);
        });
    }
}
