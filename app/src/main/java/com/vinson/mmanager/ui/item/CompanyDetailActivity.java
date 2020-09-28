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
import com.mikepenz.iconics.view.IconicsImageView;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.home.SimpleFragmentPagerAdapter;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.model.Company;
import com.vinson.mmanager.model.lift.LiftInfo;
import com.vinson.mmanager.ui.main.CompanyBriefFragment;
import com.vinson.mmanager.ui.view.CustomViewPager;
import com.vinson.mmanager.utils.Constants;
import com.vinson.mmanager.utils.Utils;

import java.util.Arrays;

import butterknife.BindView;

import static com.vinson.mmanager.utils.Constants.AROUTER_PAGE_COMPANY_DETAIL;
import static com.vinson.mmanager.utils.Constants.AROUTER_PAGE_LIFT_DETAIL;

@Route(path = AROUTER_PAGE_COMPANY_DETAIL)
public class CompanyDetailActivity extends BaseActivity {
    public static final String EXTRA_COMPANY = "company.info";
    Company mCompany;

    @BindView(R.id.tv_full_name)
    MaterialTextView mFullName;
    @BindView(R.id.tv_alias)
    MaterialTextView mAlias;
    @BindView(R.id.tv_legal)
    MaterialTextView mLegal;
    @BindView(R.id.tv_phone)
    MaterialTextView mPhone;
    @BindView(R.id.tv_category)
    MaterialTextView mCategory;
    @BindView(R.id.tv_status)
    MaterialTextView mStatus;
    @BindView(R.id.tv_reg_code)
    MaterialTextView mRegCode;
    @BindView(R.id.tv_org_code)
    MaterialTextView mOrgCode;
    @BindView(R.id.tv_tax_code)
    MaterialTextView mTaxCode;
    @BindView(R.id.tv_credit_code)
    MaterialTextView mCreditCode;
    @BindView(R.id.tv_address)
    MaterialTextView mAddress;

    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_company_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        String str = getIntent().getStringExtra(EXTRA_COMPANY);
        if (str != null && !str.isEmpty()) {
            mCompany = mGson.fromJson(str, Company.class);
        }
        mFullName.setText(mCompany.fullName);
        mAlias.setText(mCompany.alias);
        mLegal.setText(mCompany.legalPerson);
        mPhone.setText(mCompany.phone);
        mCategory.setText(mCompany.category.categoryName);
        mStatus.setText(mCompany.status);
        mRegCode.setText(mCompany.regCode);
        mOrgCode.setText(mCompany.orgCode);
        mTaxCode.setText(mCompany.taxCode);
        mCreditCode.setText(mCompany.creditCode);
        mAddress.setText(mCompany.address);
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
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
