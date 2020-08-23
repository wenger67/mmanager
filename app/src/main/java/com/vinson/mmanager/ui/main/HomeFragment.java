package com.vinson.mmanager.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.typeface.IIcon;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.HomeGridViewAdapter;
import com.vinson.mmanager.adapter.HomeHeaderGridViewAdapter;
import com.vinson.mmanager.model.ui.DataListAnnotation;
import com.vinson.mmanager.model.ui.ListParamIntent;
import com.vinson.mmanager.model.ui.HomeGridViewItem;
import com.vinson.mmanager.ui.login.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    GridView mGridView;
    GridView mHeaderGridView;
    HomeGridViewAdapter mGridAdapter;
    HomeHeaderGridViewAdapter mHeaderGridViewAdapter;
    Handler mHandler;

    public HomeFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = ((MainActivity) Objects.requireNonNull(getActivity())).getHandler();
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
    }

    private List<HomeGridViewItem> getHeaderData() {
        IIcon[] icons = new IIcon[]{CommunityMaterial.Icon2.cmd_scanner,
                CommunityMaterial.Icon2.cmd_office_building, CommunityMaterial.Icon2.cmd_office,
                CommunityMaterial.Icon2.cmd_help};
        String[] titles = new String[]{"Scan", "Building", "Office", "Help"};
        List<HomeGridViewItem> items = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            items.add(new HomeGridViewItem(icons[i], titles[i]));
        }
        return items;
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
        mHeaderGridView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    // goto camera activity
                    break;
                case 1:
                    // goto lift list activity
                    ListParamIntent listParamIntent = new ListParamIntent();
                    listParamIntent.dataType = DataListAnnotation.LIFT;

                    Message message = new Message();
                    message.what = MainActivity.MSG_LAUNCH_DATA_LIST;
                    message.obj = listParamIntent;
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
