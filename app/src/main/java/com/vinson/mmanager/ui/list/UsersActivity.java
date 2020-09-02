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
import com.vinson.mmanager.adapter.UsersAdapter;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.data.ServerHelper;
import com.vinson.mmanager.model.login.UserInfo;
import com.vinson.mmanager.model.request.BaseListParams;
import com.vinson.mmanager.model.response.BaseResponse;
import com.vinson.mmanager.ui.view.CustomList;
import com.vinson.mmanager.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Route(path = Constants.AROUTER_PAGE_USERS)
public class UsersActivity extends BaseActivity {
    CustomList mCustomList;
    List<UserInfo> mUsers = new ArrayList<>();
    UsersAdapter mUsersAdapter;

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
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), mGson.toJson(listParams));
        ServerHelper.getInstance().getUserList(requestBody).enqueue(new Callback<BaseResponse<JsonObject>>() {
            @Override
            public void onResponse(Call<BaseResponse<JsonObject>> call,
                                   Response<BaseResponse<JsonObject>> response) {
                BaseResponse<JsonObject> body = response.body();
                if (body != null) {
                    JsonObject data = body.getData();
                    for (JsonElement element : data.getAsJsonArray("list")) {
                        mUsers.add(mGson.fromJson(element, UserInfo.class));
                    }
                    mUsersAdapter.setData(mUsers);
                    mSkeletonScreen.hide();
                } else {
                    KLog.w("get user list empty!");
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
        mCustomList = findViewById(R.id.rcv);
        mCustomList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mUsersAdapter = new UsersAdapter(null, this);
        mCustomList.setAdapter(mUsersAdapter);

        mSkeletonScreen = Skeleton.bind(mCustomList)
                .adapter(mUsersAdapter).load(R.layout.activity_data_list)
                .show();
        mHandler.sendEmptyMessage(MSG_FETCH_LIST_DATA);
    }


    @Override
    protected void initEvent() {
    }
}
