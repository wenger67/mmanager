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
import com.vinson.mmanager.model.ProgressItem;
import com.vinson.mmanager.model.login.UserInfo;
import com.vinson.mmanager.model.request.BaseListParams;
import com.vinson.mmanager.model.response.BaseResponse;
import com.vinson.mmanager.ui.view.CustomList;
import com.vinson.mmanager.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.FlexibleItemDecoration;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Route(path = Constants.AROUTER_PAGE_USERS)
public class UsersActivity extends BaseActivity {
    CustomList mCustomList;
    List<AbstractFlexibleItem> mUsers = new ArrayList<>();
    FlexibleAdapter<AbstractFlexibleItem> mUsersAdapter;
    int lastPos = 0;
    int curPage = 0;

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
        mUsers  = new ArrayList<>(); // clear
        BaseListParams listParams = new BaseListParams( curPage + 1, 10);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                mGson.toJson(listParams));
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
                    mUsersAdapter.onLoadMoreComplete(mUsers, 500);
                    KLog.d("EndlessCurrentPage=" + mUsersAdapter.getEndlessCurrentPage());
                    KLog.d("EndlessPageSize=" + mUsersAdapter.getEndlessPageSize());
                    KLog.d("EndlessTargetCount=" + mUsersAdapter.getEndlessTargetCount());

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
        super.initView();
        mCustomList = findViewById(R.id.rcv);
        mCustomList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mCustomList.addItemDecoration(new FlexibleItemDecoration(this).withOffset(1).withDefaultDivider());

        mUsersAdapter = new FlexibleAdapter<>(null);
        mCustomList.setAdapter(mUsersAdapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mSkeletonScreen = Skeleton.bind(mCustomList)
                .adapter(mUsersAdapter).load(R.layout.activity_data_list_skeleton)
                .show();

        mUsersAdapter.setEndlessScrollListener(new FlexibleAdapter.EndlessScrollListener() {
            @Override
            public void noMoreLoad(int newItemsSize) {
                KLog.d(newItemsSize);
            }

            @Override
            public void onLoadMore(int lastPosition, int currentPage) {
                KLog.d(lastPosition + ", " + currentPage);
                lastPos += lastPosition;
                curPage = currentPage;
                mHandler.sendEmptyMessage(MSG_FETCH_LIST_DATA);
            }


        }, new ProgressItem()).
                setEndlessPageSize(10).
                setLoadingMoreAtStartUp(true).
                setEndlessScrollThreshold(1);
    }


}
