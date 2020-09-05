package com.vinson.mmanager.ui.list;

import android.os.Bundle;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.ethanhua.skeleton.Skeleton;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.data.ServerHelper;
import com.vinson.mmanager.model.device.Device;
import com.vinson.mmanager.model.request.BaseListParams;
import com.vinson.mmanager.model.response.BaseResponse;
import com.vinson.mmanager.ui.view.CustomList;
import com.vinson.mmanager.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Route(path = Constants.AROUTER_PAGE_DEVICE_LIST)
public class DevicesActivity extends BaseActivity {
    CustomList mCustomList;
    List<Device> mDevices = new ArrayList<>();
    FlexibleAdapter<Device> mDevicesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean handleMessage(Message message) {
        switch (message.what) {
            case MSG_FETCH_LIST_DATA:
                fetchData();
                break;
            default:
                break;
        }
        return false;
    }

    private void fetchData() {
        BaseListParams listParams = new BaseListParams();
        ServerHelper.getInstance().getAdDeviceList(listParams.getPage(), listParams.getPageSize()).enqueue(new Callback<BaseResponse<JsonObject>>() {
            @Override
            public void onResponse(Call<BaseResponse<JsonObject>> call,
                                   Response<BaseResponse<JsonObject>> response) {
                BaseResponse<JsonObject> body = response.body();
                if (body != null) {
                    JsonObject data = body.getData();
                    for (JsonElement element : data.getAsJsonArray("list")) {
                        mDevices.add(mGson.fromJson(element, Device.class));
                    }
                    mDevicesAdapter.addItems(0, mDevices);
                    KLog.d(mDevices.size());
                    mSkeletonScreen.hide();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<JsonObject>> call, Throwable t) {
                KLog.d(t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onFound(Postcard postcard) {
        super.onFound(postcard);
    }

    @Override
    public void onLost(Postcard postcard) {
        super.onLost(postcard);
    }

    @Override
    public void onArrival(Postcard postcard) {
        super.onArrival(postcard);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_data_list;
    }

    @Override
    protected void initView() {
        super.initView();
        mCustomList = findViewById(R.id.rcv);
        mCustomList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mDevicesAdapter = new FlexibleAdapter<>(null, this);
        mCustomList.setAdapter(mDevicesAdapter);

        mSkeletonScreen = Skeleton.bind(mCustomList)
                .adapter(mDevicesAdapter).load(R.layout.activity_data_list_skeleton)
                .show();
        mHandler.sendEmptyMessage(MSG_FETCH_LIST_DATA);
    }


    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
