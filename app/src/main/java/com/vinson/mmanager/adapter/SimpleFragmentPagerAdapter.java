package com.vinson.mmanager.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.vinson.mmanager.ui.main.PageFragment;

/**
 * Created by mxdl on 2017/12/6.
 */

public class SimpleFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context, int tabCount) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
