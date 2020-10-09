package com.vinson.mmanager.ui;

import android.view.View;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.socks.library.KLog;
import com.vinson.mmanager.data.ServerHelper;
import com.vinson.mmanager.model.annotation.ModuleType;
import com.vinson.mmanager.model.lift.LiftRecord;
import com.vinson.mmanager.model.request.BaseListParams;
import com.vinson.mmanager.model.response.BaseResponse;

import java.util.ArrayList;

import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnStartRecordListFragment extends BaseListFragment {

    public UnStartRecordListFragment() {
        super();
    }

    public UnStartRecordListFragment(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void fetchData() {
        super.fetchData();
        mItems = new ArrayList<>(); // clear
        BaseListParams listParams = new BaseListParams(1, 3);
        ServerHelper.getInstance().getList(ModuleType.MODULE_LIFT_RECORDS, listParams.page,
                listParams.pageSize).enqueue(new Callback<BaseResponse<JsonObject>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<BaseResponse<JsonObject>> call,
                                   Response<BaseResponse<JsonObject>> response) {
                BaseResponse<JsonObject> body = response.body();
                if (body != null) {
                    JsonObject data = body.getData();
                    for (JsonElement element : data.getAsJsonArray("list")) {
                        mItems.add(mGson.fromJson(element, LiftRecord.class));
                    }
                    mAdapter.setItems(mItems);
                    mHandler.sendEmptyMessage(MSG_FETCH_DATA_SUCCESS);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<BaseResponse<JsonObject>> call, Throwable t) {
                KLog.d(t.getMessage());
                mHandler.sendEmptyMessageDelayed(MSG_FETCH_DATA_FAILED,
                        FETCH_DATA_FAILED_MESSAGE_DELAY);
            }
        });
    }
}
