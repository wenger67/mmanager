package com.vinson.mmanager.ui.page;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.javafaker.Faker;
import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.MySpinnerAdapter;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.data.ServerHelper;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.annotation.ModuleType;
import com.vinson.mmanager.model.lift.LiftInfo;
import com.vinson.mmanager.model.login.UserInfo;
import com.vinson.mmanager.model.request.BaseListParams;
import com.vinson.mmanager.model.request.LiftRecordCreate;
import com.vinson.mmanager.model.response.BaseResponse;
import com.vinson.mmanager.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Route(path = Constants.AROUTER_PAGE_CREATE_LIFT_RECORD)
public class CreateRecordActivity extends BaseActivity {

    @BindView(R.id.sp_lift)
    AppCompatSpinner mLiftSpinner;
    @BindView(R.id.sp_category)
    AppCompatSpinner mCategorySpinner;
    @BindView(R.id.btn_submit)
    MaterialButton mBtnSubmit;

    MySpinnerAdapter mLiftAdapter;
    MySpinnerAdapter mCategoryAdapter;
    List<AbstractFlexibleItem> mLifts;
    List<AbstractFlexibleItem> mCategories;
    int mLiftSelectedId = -1;
    int mCategorySelectedId = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        mLiftAdapter = new MySpinnerAdapter(mLifts, this);
        mCategoryAdapter = new MySpinnerAdapter(mCategories, this);

        mTitle.setText(getResources().getString(R.string.title_create_record));

        mLiftSpinner.setAdapter(mLiftAdapter);
        mCategorySpinner.setAdapter(mCategoryAdapter);
        mHandler.sendEmptyMessage(MSG_FETCH_DATA_START);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mLiftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LiftInfo lift = (LiftInfo) mLiftAdapter.getItem(position);
                mLiftSelectedId = lift.ID;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category category = (Category) mCategoryAdapter.getItem(position);
                mCategorySelectedId = category.ID;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBtnSubmit.setOnClickListener(v -> {
            KLog.d(mLiftSelectedId + " ," + mCategorySelectedId);
            LiftRecordCreate liftRecordCreate = new LiftRecordCreate(mLiftSelectedId, mCategorySelectedId);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                    mGson.toJson(liftRecordCreate));
            ServerHelper.getInstance().createLiftRecord(requestBody).enqueue(new Callback<BaseResponse<JsonObject>>() {
                @Override
                @EverythingIsNonNull
                public void onResponse(Call<BaseResponse<JsonObject>> call,
                                       Response<BaseResponse<JsonObject>> response) {
                    BaseResponse<JsonObject> body = response.body();
                    if (body != null) {
                        JsonObject data = body.getData();
                        KLog.d(data.toString());
                        routeTo(Constants.AROUTER_PAGE_LIFT_RECORDS, CreateRecordActivity.this);
                    } else {
                        KLog.w("get data empty!");
                    }
                }

                @Override
                @EverythingIsNonNull
                public void onFailure(Call<BaseResponse<JsonObject>> call, Throwable t) {
                    KLog.d(t.getMessage());
                }
            });
        });
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
        CreateRecordActivity.this.finish();
    }

    @Override
    protected boolean handleMessage(Message message) {
        switch (message.what) {
            case MSG_FETCH_DATA_START:
                fetchData();
                break;
        }
        return false;
    }

    public void fetchData() {
        mLifts = new ArrayList<>(); // clear
        BaseListParams listParams = new BaseListParams(1, 999);
        ServerHelper.getInstance().getList(ModuleType.MODULE_LIFT_LIST, listParams.page,
                listParams.pageSize).enqueue(new Callback<BaseResponse<JsonObject>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<BaseResponse<JsonObject>> call,
                                   Response<BaseResponse<JsonObject>> response) {
                BaseResponse<JsonObject> body = response.body();
                if (body != null) {
                    JsonObject data = body.getData();
                    for (JsonElement element : data.getAsJsonArray("list")) {
                        LiftInfo liftInfo = mGson.fromJson(element, LiftInfo.class);
                        mLifts.add(liftInfo);
                    }
                    mLiftAdapter.setItems(mLifts);
                } else {
                    KLog.w("get lift list failed, response body is null");
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

        mCategories = new ArrayList<>(); // clear
        ServerHelper.getInstance().getList(ModuleType.MODULE_CATEGORY_LIST, listParams.page,
                listParams.pageSize).enqueue(new Callback<BaseResponse<JsonObject>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<BaseResponse<JsonObject>> call,
                                   Response<BaseResponse<JsonObject>> response) {
                BaseResponse<JsonObject> body = response.body();
                if (body != null) {
                    JsonObject data = body.getData();
                    for (JsonElement element : data.getAsJsonArray("list")) {
                        Category category = mGson.fromJson(element, Category.class);
                        mCategories.add(category);
                    }
                    mCategoryAdapter.setItems(mCategories);
                } else {
                    KLog.w("get lift list failed, response body is null");
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

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_create_lift_record;
    }
}
