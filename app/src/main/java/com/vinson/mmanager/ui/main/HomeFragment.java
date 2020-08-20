package com.vinson.mmanager.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textview.MaterialTextView;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.view.IconicsImageView;
import com.vinson.mmanager.App;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.HomeGridViewAdapter;
import com.vinson.mmanager.model.ui.HomeGridViewItem;
import com.vinson.mmanager.ui.login.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    TabLayout mTabLayout;
    GridView mGridView;
    HomeGridViewAdapter mGridAdapter;

    public HomeFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View root) {
        mTabLayout = root.findViewById(R.id.tl_main_home);

        IIcon[] icons = new IIcon[]{CommunityMaterial.Icon2.cmd_scanner,
                CommunityMaterial.Icon2.cmd_office_building, CommunityMaterial.Icon2.cmd_office,
                CommunityMaterial.Icon2.cmd_help};
        String[] titles = new String[]{"Scan", "Building", "Office", "Help"};
        for (int i = 0; i < 4; i++) {
            TabLayout.Tab tab = mTabLayout.newTab();

            View inflate = View.inflate(getActivity(), R.layout.view_tablayout_tab, null);
            IconicsImageView tabImg = inflate.findViewById(R.id.tab_img);
            IconicsDrawable drawable = new IconicsDrawable(App.getInstance(), icons[i]);
            drawable.colorListRes(R.color.selector_color_main_home_tab);
            drawable.sizeDp(24);
            tabImg.setIcon(drawable);
            MaterialTextView textView = inflate.findViewById(R.id.tab_tv);
            textView.setTextColor(ContextCompat.getColorStateList(Objects.requireNonNull(getActivity()), R.color.selector_color_main_home_tab));
            textView.setText(titles[i]);

            tab.setCustomView(inflate);
            mTabLayout.addTab(tab);
        }

        mGridView = root.findViewById(R.id.gv_main_home);
        mGridAdapter = new HomeGridViewAdapter(getActivity(), R.layout.view_tablayout_tab, getData());
        mGridView.setAdapter(mGridAdapter);
    }

    private List<HomeGridViewItem> getData() {
        List<HomeGridViewItem> items = new ArrayList<>();
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Light"));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_select_all, "ALL"));
        return items;
    }

    @Override
    protected void initEvent() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;
        initView(view);
        initEvent();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
