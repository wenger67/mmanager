package com.vinson.mmanager.adapter.home;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.socks.library.KLog;
import com.vinson.mmanager.App;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

import static com.vinson.mmanager.ui.main.CompanyBriefFragment.EXTRA_ITEM_INFO;

/**
 * Created by mxdl on 2017/12/6.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private Context context;
    private List<Fragment> mFragments = new ArrayList<>();

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    public void addFragment(Fragment fragment, AbstractFlexibleItem item) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_ITEM_INFO, App.getInstance().mGson.toJson(item));
        fragment.setArguments(bundle);
        mFragments.add(fragment);
    }

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context, int tabCount) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
