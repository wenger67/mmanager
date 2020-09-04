package com.vinson.mmanager.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.android.material.textview.MaterialTextView;
import com.socks.library.KLog;
import com.vinson.mmanager.App;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.home.SimpleFragmentPagerAdapter;
import com.vinson.mmanager.base.BaseFragment;
import com.vinson.mmanager.ui.view.CustomViewPager;

public class ManageFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ManageFragment() {
    }

    public ManageFragment(int contentLayoutId) {
        super(contentLayoutId);
    }
    SegmentTabLayout mTabs;
    CustomViewPager mPager;
    private String[] mTitles = new String[]{"WaitToDo", "Process", "Done"};

    @Override
    protected void initView(View root) {
        MaterialTextView textView = root.findViewById(R.id.tb_title);
        textView.setText("Manage");
        mTabs = root.findViewById(R.id.tab_list);
        mPager = root.findViewById(R.id.vp_list);
        mPager.setCanScroll(true);
        // fix issue : use getChildFragmentManager() for viewpager item dismiss
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this.getChildFragmentManager(), getActivity(), 3);
        adapter.addFragment(new ListFragment(R.layout.fragment_list));
        adapter.addFragment(new ListFragment(R.layout.fragment_list));
        adapter.addFragment(new ListFragment(R.layout.fragment_list));

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
    }
}
