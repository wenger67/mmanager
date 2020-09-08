package com.vinson.mmanager.ui.main;

import android.view.View;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.App;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseFragment;
import com.vinson.mmanager.model.Company;

public class CompanyBriefFragment extends BaseFragment {
    public static final String EXTRA_ITEM_INFO = "item.info";
    MaterialTextView mName, mCode, mPerson, mPhone, mAddress;
    public CompanyBriefFragment() {
        super();
    }

    public CompanyBriefFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    Company mCompany;

    @Override
    protected void initView(View root) {
        if (getArguments() != null) {
            String str = getArguments().getString(EXTRA_ITEM_INFO);
            mCompany = App.getInstance().mGson.fromJson(str, Company.class);
        }

        mName = root.findViewById(R.id.tv_name);
        mCode = root.findViewById(R.id.tv_code);
        mPerson = root.findViewById(R.id.tv_person);
        mPhone = root.findViewById(R.id.tv_phone);
        mAddress = root.findViewById(R.id.tv_address);

        mName.setText(mCompany.fullName);
        mCode.setText(mCompany.creditCode);
        mPerson.setText(mCompany.legalPerson);
        mPhone.setText(mCompany.phone);
        mAddress.setText(mCompany.address);
    }

    @Override
    protected void initEvent() {

    }
}
