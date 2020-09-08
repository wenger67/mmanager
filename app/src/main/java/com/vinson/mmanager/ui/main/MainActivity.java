package com.vinson.mmanager.ui.main;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textview.MaterialTextView;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.view.IconicsImageView;
import com.vinson.mmanager.App;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.home.SimpleFragmentPagerAdapter;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.model.ui.ListParams;
import com.vinson.mmanager.services.WSService;
import com.vinson.mmanager.ui.view.CustomViewPager;
import com.vinson.mmanager.utils.Constants;

@Route(path = Constants.AROUTER_PAGE_MAIN)
public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    CustomViewPager mViewPager;
    TabLayout mBottomMenus;
    private SimpleFragmentPagerAdapter mPagerAdapter;
    private IIcon[] icons = new IIcon[]{CommunityMaterial.Icon2.cmd_home,
            CommunityMaterial.Icon2.cmd_hand_peace, CommunityMaterial.Icon2.cmd_message,
            CommunityMaterial.Icon2.cmd_home_account};
    private String[] titles = new String[]{"Home", "Manage", "Message", "About"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        startService(new Intent(this, WSService.class));

        mBottomMenus = findViewById(R.id.tl);
        mBottomMenus.setTabMode(TabLayout.MODE_FIXED);

        for (int i = 0; i < 4; i++) {
            TabLayout.Tab homeTab = mBottomMenus.newTab();

            View inflate = View.inflate(this, R.layout.item_home_header_gridview, null);
            IconicsImageView homeTabImg = inflate.findViewById(R.id.tab_img);
            IconicsDrawable drawable = new IconicsDrawable(this, icons[i]);
            drawable.colorListRes(R.color.selector_color_main_tab);
            drawable.sizeDp(24);
            homeTabImg.setIcon(drawable);
            MaterialTextView textView = inflate.findViewById(R.id.tab_tv);
            textView.setTextColor(ContextCompat.getColorStateList(this,
                    R.color.selector_color_main_tab));
            textView.setText(titles[i]);

            homeTab.setCustomView(inflate);
            mBottomMenus.addTab(homeTab);
        }

        mPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this,
                mBottomMenus.getTabCount());
        mPagerAdapter.addFragment(new HomeFragment(R.layout.fragment_main_home));
        mPagerAdapter.addFragment(new ManageFragment(R.layout.fragment_main_manage));
        mPagerAdapter.addFragment(new MessageFragment(R.layout.fragment_main_message));
        mPagerAdapter.addFragment(new AboutFragment(R.layout.fragment_main_about));

        mViewPager = findViewById(R.id.vp);
        mViewPager.setCanScroll(false); // disable horizontal scroll
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    protected void initEvent() {
        mBottomMenus.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomMenus.setScrollPosition(position, 0f, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected boolean handleMessage(Message message) {
        if (isFinishing() || isDestroyed()) return true;
        switch (message.what) {
            case MSG_NETWORK_CHANGE:
                break;
            case MSG_LAUNCH_LIFT_LIST:
                ListParams listParams = (ListParams) message.obj;
                routeTo(Constants.AROUTER_PAGE_LIFT_LIST, Constants.DATA_LIST_PARAM, listParams,
                        MainActivity.this);
                break;
            case MSG_LAUNCH_LOGIN:
                routeTo(Constants.AROUTER_PAGE_LOGIN, MainActivity.this);
                break;
            case MSG_LAUNCH_LIFT_CHANGES:
                routeTo(Constants.AROUTER_PAGE_LIFT_CHANGES, MainActivity.this);
                break;
            case MSG_LAUNCH_LIFT_RECORDS:
                routeTo(Constants.AROUTER_PAGE_LIFT_RECORDS, MainActivity.this);
                break;
            case MSG_LAUNCH_LIFT_TROUBLES:
                routeTo(Constants.AROUTER_PAGE_LIFT_TROUBLES, MainActivity.this);
                break;
            case MSG_LAUNCH_USERS:
                routeTo(Constants.AROUTER_PAGE_USERS, MainActivity.this);
                break;
            case MSG_LAUNCH_DEVICE_LIST:
                routeTo(Constants.AROUTER_PAGE_DEVICE_LIST, MainActivity.this);
                break;
            case MSG_LAUNCH_DEVICE_EVENT:
                routeTo(Constants.AROUTER_PAGE_DEVICE_EVENT, MainActivity.this);
                break;
            case MSG_LAUNCH_DEVICE_DATA:
                routeTo(Constants.AROUTER_PAGE_DEVICE_DATA, MainActivity.this);
                break;
            case MSG_LAUNCH_DEVICE_CONFIG:
                routeTo(Constants.AROUTER_PAGE_DEVICE_CONFIG, MainActivity.this);
                break;
            case MSG_LAUNCH_COMPANY_LIST:
                routeTo(Constants.AROUTER_PAGE_COMPANY_LIST, MainActivity.this);
                break;
        }
        return false;

    }

    @Override
    public void networkChanged(boolean connected) {
        super.networkChanged(connected);
        if (connected) {
            App.getInstance().showToast("Network connected");
        } else {
            App.getInstance().showToast("Network disconnected");
        }
    }
}