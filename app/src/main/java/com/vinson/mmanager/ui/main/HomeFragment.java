package com.vinson.mmanager.ui.main;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.typeface.IIcon;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.home.HomeGridViewAdapter;
import com.vinson.mmanager.adapter.home.HomeHeaderGridViewAdapter;
import com.vinson.mmanager.adapter.home.HomeMarqueeAdapter;
import com.vinson.mmanager.model.annotation.ModuleType;
import com.vinson.mmanager.model.ui.ListParams;
import com.vinson.mmanager.model.ui.HomeGridViewItem;
import com.vinson.mmanager.base.BaseFragment;
import com.vinson.mmanager.widget.marquee.XMarqueeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    GridView mGridView;
    GridView mHeaderGridView;
    HomeGridViewAdapter mGridAdapter;
    HomeHeaderGridViewAdapter mHeaderGridViewAdapter;

    XMarqueeView mMarqueeView;

    public HomeFragment() {
    }

    public HomeFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View root) {
        mHeaderGridView = root.findViewById(R.id.gv_main_home_header);
        mHeaderGridViewAdapter =
                new HomeHeaderGridViewAdapter(Objects.requireNonNull(getActivity()),
                        R.layout.item_home_header_gridview, getHeaderData());
        mHeaderGridView.setAdapter(mHeaderGridViewAdapter);

        mGridView = root.findViewById(R.id.gv_main_home);
        mGridAdapter = new HomeGridViewAdapter(getActivity(), R.layout.item_home_header_gridview,
                getData());
        mGridView.setAdapter(mGridAdapter);

        mMarqueeView = root.findViewById(R.id.mv_news);
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            mData.add("这是跑马灯内容" + (i + 1));
        }
        HomeMarqueeAdapter adapter = new HomeMarqueeAdapter(mData,getActivity());
        mMarqueeView.setAdapter(adapter);
    }

    private List<HomeGridViewItem> getHeaderData() {
        IIcon[] icons = new IIcon[]{CommunityMaterial.Icon2.cmd_scanner,
                CommunityMaterial.Icon2.cmd_office_building, CommunityMaterial.Icon2.cmd_nature_people,
                CommunityMaterial.Icon2.cmd_help};
        String[] titles = new String[]{"Scan", "Lifts", "Users", "Help"};
        int[] type = new int[]{ModuleType.MODULE_HOME_HEADER_CAMERA, ModuleType.MODULE_LIFT_LIST,
        ModuleType.MODULE_HOME_HEADER_USERS, ModuleType.MODULE_HOME_HEADER_HELP};
        List<HomeGridViewItem> items = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            items.add(new HomeGridViewItem(icons[i], titles[i], type[i]));
        }
        return items;
    }

    private List<HomeGridViewItem> getData() {
        List<HomeGridViewItem> items = new ArrayList<>();
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_hospital_building, "Lifts", ModuleType.MODULE_LIFT_LIST));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_table_edit, "Changes", ModuleType.MODULE_LIFT_CHANGES));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_view_list, "Records", ModuleType.MODULE_LIFT_RECORDS));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_help_circle, "Troubles", ModuleType.MODULE_LIFT_TROUBLE));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_nature_people, "Users", ModuleType.MODULE_HOME_HEADER_USERS));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_power_plug, "Events", ModuleType.MODULE_DEVICE_EVENT));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_pokeball, "Devices", ModuleType.MODULE_DEVICE_LIST));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_magnet_on, "Datas", ModuleType.MODULE_DEVICE_DATA));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_palette_advanced, "Configs", ModuleType.MODULE_DEVICE_CONFIG));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_karate, "Companys", ModuleType.MODULE_COMPANY_LIST));
        items.add(new HomeGridViewItem(CommunityMaterial.Icon2.cmd_select_all, "ALL", ModuleType.MODULE_BUTTON_ALL));
        return items;
    }

    @Override
    protected void initEvent() {
        mHeaderGridView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    // goto camera activity
                    break;
                case 1:
                    // goto lift list activity
                    Message message = new Message();
                    message.what = MainActivity.MSG_LAUNCH_LIFT_LIST;
                    message.obj = new ListParams(ModuleType.MODULE_LIFT_LIST);
                    mHandler.sendMessage(message);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        });

        mGridView.setOnItemClickListener(((parent, view, position, id) -> {
            HomeGridViewItem item = mGridAdapter.getItem(position);
            if (item != null) {
                switch (item.getType()) {
                    case ModuleType.MODULE_LIFT_LIST:
                        mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_LIFT_LIST);
                        break;
                    case ModuleType.MODULE_LIFT_CHANGES:
                        mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_LIFT_CHANGES);
                        break;
                    case ModuleType.MODULE_LIFT_RECORDS:
                        mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_LIFT_RECORDS);
                        break;
                    case ModuleType.MODULE_LIFT_TROUBLE:
                        mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_LIFT_TROUBLES);
                        break;
                    case ModuleType.MODULE_HOME_HEADER_USERS:
                        mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_USERS);
                        break;
                    case ModuleType.MODULE_DEVICE_LIST:
                        mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_DEVICE_LIST);
                        break;
                    case ModuleType.MODULE_DEVICE_EVENT:
                        mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_DEVICE_EVENT);
                        break;
                    case ModuleType.MODULE_DEVICE_DATA:
                        mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_DEVICE_DATA);
                        break;
                    case ModuleType.MODULE_DEVICE_CONFIG:
                        mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_DEVICE_CONFIG);
                        break;
                    case ModuleType.MODULE_COMPANY_LIST:
                        mHandler.sendEmptyMessage(MainActivity.MSG_LAUNCH_COMPANY_LIST);
                        break;
                }
            }
        }));
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
