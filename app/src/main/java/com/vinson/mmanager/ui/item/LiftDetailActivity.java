package com.vinson.mmanager.ui.item;

import android.os.Bundle;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.android.material.textview.MaterialTextView;
import com.mikepenz.iconics.view.IconicsImageButton;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.home.SimpleFragmentPagerAdapter;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.model.lift.LiftInfo;
import com.vinson.mmanager.ui.main.CompanyBriefFragment;
import com.vinson.mmanager.ui.view.CustomViewPager;
import com.vinson.mmanager.utils.Constants;
import com.vinson.mmanager.utils.Utils;

import java.util.Arrays;

import static com.vinson.mmanager.utils.Constants.AROUTER_PAGE_LIFT_DETAIL;

@Route(path = AROUTER_PAGE_LIFT_DETAIL)
public class LiftDetailActivity extends BaseActivity {
    public static final String EXTRA_LIFT_INFO = "lift.info";
    LiftInfo mLiftInfo;
    MaterialTextView mName, mCode;
    MaterialTextView mUptime, mChecktime, mModel, mOnline;
    MaterialTextView mAddress;
    IconicsImageButton mVideoCall;
    MapView mMapView;
    AMap mAMap;
    SegmentTabLayout mTabLayout;
    CustomViewPager mViewPager;
    String[] mTitles = new String[]{"Installer", "Maintainer", "Checker", "Owner"};

    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_lift_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        String str = getIntent().getStringExtra(EXTRA_LIFT_INFO);
        if (str != null && !str.isEmpty()) {
            mLiftInfo = mGson.fromJson(str, LiftInfo.class);
        }

        mName = findViewById(R.id.tv_category);
        mName.setText(mLiftInfo.nickName);

        mCode = findViewById(R.id.tv_name);
        mCode.setText(mLiftInfo.code);

        mUptime = findViewById(R.id.tv_code);
        mUptime.setText(TimeUtils.getTimeSpanByNow(TimeUtils.string2Millis(mLiftInfo.installTime,
                Utils.DATE_PATTERN), TimeConstants.DAY) + " D");

        mChecktime = findViewById(R.id.tv_create_time);
        mChecktime.setText(TimeUtils.getTimeSpanByNow(TimeUtils.string2Millis(mLiftInfo.checkTime
                , Utils.DATE_PATTERN), TimeConstants.DAY) + " D");

        mModel = findViewById(R.id.tv_model);
        mModel.setText(mLiftInfo.liftModel.brand + "-" + mLiftInfo.liftModel.modal);

        mOnline = findViewById(R.id.tv_online);
//        mOnline.setText(mLiftInfo.mDevice.online ? "Online" : "Offline");
//        mOnline.setTextColor(mLiftInfo.mDevice.online ?
//                App.getInstance().color(R.color.md_green_500) :
//                App.getInstance().color(R.color.md_yellow_500));

        mAddress = findViewById(R.id.tv_address);
        mAddress.setText(mLiftInfo.address.addressName + " | " + mLiftInfo.building + " | " + mLiftInfo.cell);

        mMapView = findViewById(R.id.mv_map);
        mMapView.onCreate(mBundle); // create map
        mAMap = mMapView.getMap();


        mTabLayout = findViewById(R.id.tab_list);
        mTabLayout.setTabData(mTitles);

        mViewPager = findViewById(R.id.vp_list);
        mViewPager.setCanScroll(true);
        SimpleFragmentPagerAdapter adapter =
                new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, 4);
        adapter.addFragment(new CompanyBriefFragment(R.layout.fragment_company_brief),
                mLiftInfo.installer);
        adapter.addFragment(new CompanyBriefFragment(R.layout.fragment_company_brief),
                mLiftInfo.maintainer);
        adapter.addFragment(new CompanyBriefFragment(R.layout.fragment_company_brief),
                mLiftInfo.checker);
        adapter.addFragment(new CompanyBriefFragment(R.layout.fragment_company_brief),
                mLiftInfo.owner);
        mViewPager.setAdapter(adapter);

        mVideoCall = findViewById(R.id.btn_video);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        // set map center
        KLog.d(mLiftInfo.address);
        String[] location = mLiftInfo.address.location.trim().split(",");
        KLog.d(Arrays.toString(location));
        LatLng pos = new LatLng(Double.parseDouble(location[1]),
                Double.parseDouble(location[0]));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(
                new CameraPosition(pos, 16, 0, 0)
        );
        mAMap.animateCamera(cameraUpdate, 2000, new AMap.CancelableCallback() {
            @Override
            public void onFinish() {
                KLog.d();
            }

            @Override
            public void onCancel() {
                KLog.d();
            }
        });
        Marker marker = mAMap.addMarker(new MarkerOptions().position(pos).
                title(mLiftInfo.nickName).draggable(false)
                .visible(true).anchor(0.5f, 0.5f));
        marker.showInfoWindow();

        // set viewpager event
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // request video call
        mVideoCall.setOnClickListener(v -> ARouter.getInstance().
                build(Constants.AROUTE_PAGE_VIDEO_ROOM).
                withObject(EXTRA_LIFT_INFO, mLiftInfo).navigation(this, this));
    }
}
