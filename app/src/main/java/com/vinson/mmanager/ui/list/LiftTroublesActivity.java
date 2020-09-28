package com.vinson.mmanager.ui.list;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseListActivity;
import com.vinson.mmanager.data.ServerHelper;
import com.vinson.mmanager.model.annotation.ModuleType;
import com.vinson.mmanager.model.lift.LiftTrouble;
import com.vinson.mmanager.model.request.BaseListParams;
import com.vinson.mmanager.model.response.BaseResponse;
import com.vinson.mmanager.utils.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import static com.vinson.mmanager.ui.item.LiftTroubleDetailActivity.EXTRA_LIFT_TROUBLE;

@Route(path = Constants.AROUTER_PAGE_LIFT_TROUBLES)
public class LiftTroublesActivity extends BaseListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void fetchData() {
        super.fetchData();
        mItems = new ArrayList<>(); // clear
        BaseListParams listParams = new BaseListParams(curPage + 1, 10);
        ServerHelper.getInstance().getList(ModuleType.MODULE_LIFT_TROUBLE, listParams.page,
                listParams.pageSize).enqueue(new Callback<BaseResponse<JsonObject>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<BaseResponse<JsonObject>> call,
                                   Response<BaseResponse<JsonObject>> response) {
                BaseResponse<JsonObject> body = response.body();
                if (body != null) {
                    JsonObject data = body.getData();
                    for (JsonElement element : data.getAsJsonArray("list")) {
                        mItems.add(mGson.fromJson(element, LiftTrouble.class));
                    }
                    mAdapter.onLoadMoreComplete(mItems, 3000L);
                    if (curPage == 0) mSkeletonScreen.hide();
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<BaseResponse<JsonObject>> call, Throwable t) {
                KLog.d(t.getMessage());
                mHandler.sendEmptyMessageDelayed(MSG_FETCH_DATA_FAILED, FETCH_DATA_FAILED_MESSAGE_DELAY);
            }
        });
    }
    @Override
    protected void setToolbarTitle() {
        mMaterialToolbar.setTitle("故障列表");
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
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected boolean itemClick(View view, int position) {
        LiftTrouble liftTrouble = (LiftTrouble) mAdapter.getItem(position);
        ARouter.getInstance()
                .build(Constants.AROUTER_PAGE_LIFT_TROUBLE_DETAIL)
                .withObject(EXTRA_LIFT_TROUBLE, liftTrouble)
                .navigation(this, this);
        return true;
    }
}
