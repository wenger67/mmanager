package com.vinson.mmanager.data;

import android.annotation.SuppressLint;

import com.google.gson.JsonObject;
import com.vinson.mmanager.model.annotation.ModuleType;
import com.vinson.mmanager.model.login.UserInfo;
import com.vinson.mmanager.model.response.BaseResponse;
import com.vinson.mmanager.tools.Config;
import com.vinson.mmanager.utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerHelper {

    private static OkHttpClient okHttpClient;
    private static ServerHelper INSTANCE;
    private LiftService mLiftService;
    private CategoryService mCategoryService;
    private DeviceService mDeviceService;
    private CompanyService mCompanyService;

    public ServerHelper(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mLiftService = retrofit.create(LiftService.class);
        mCategoryService = retrofit.create(CategoryService.class);
        mDeviceService = retrofit.create(DeviceService.class);
        mCompanyService = retrofit.create(CompanyService.class);
    }

    public static ServerHelper getInstance() {
        if (INSTANCE == null)
            return new ServerHelper(Constants.BASE_URL);
        return INSTANCE;
    }

    @SuppressLint("SwitchIntDef")
    public Call<BaseResponse<JsonObject>> getList(RequestBody body, @ModuleType int type) {
        switch (type) {
            case ModuleType.MODULE_HOME_HEADER_USERS:
                return mCompanyService.getUserList(body);
        }
        return null;
    }

    public Call<BaseResponse<JsonObject>> createLiftRecord(RequestBody body) {
        return mLiftService.createLiftRecord(body);
    }

    public Call<BaseResponse<JsonObject>> updateLiftRecord(RequestBody body) {
        return mLiftService.updateLiftRecord(body);
    }

    @SuppressLint("SwitchIntDef")
    public Call<BaseResponse<JsonObject>> getList(@ModuleType int type, int... params) {
        int page = params[0];
        int pageSize = params[1];
        switch (type) {
            case ModuleType.MODULE_LIFT_LIST:
                return mLiftService.getLiftList(page, pageSize);
            case ModuleType.MODULE_LIFT_CHANGES:
                return mLiftService.getLiftChangeList(page, pageSize);
            case ModuleType.MODULE_LIFT_RECORDS:
                return mLiftService.getLiftRecordList(page, pageSize);
            case ModuleType.MODULE_LIFT_TROUBLE:
                return mLiftService.getLiftTroubleList(page, pageSize);
            case ModuleType.MODULE_DEVICE_LIST:
                return mDeviceService.getAdDeviceList(page, pageSize);
            case ModuleType.MODULE_DEVICE_CONFIG:
                return mDeviceService.getAdDeviceConfigList(page, pageSize);
            case ModuleType.MODULE_DEVICE_DATA:
                return mDeviceService.getAdDeviceDataList(page, pageSize);
            case ModuleType.MODULE_DEVICE_EVENT:
                return mDeviceService.getAdDeviceEventList(page, pageSize);
            case ModuleType.MODULE_COMPANY_LIST:
                return mCompanyService.getCompanyList(page, pageSize);
            case ModuleType.MODULE_CATEGORY_LIST:
                return mCategoryService.getCategoryList(page, pageSize);
        }
        return null;
    }

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            Interceptor tokenInterceptor = new Interceptor() {
                @Override
                @EverythingIsNonNull
                public Response intercept(Chain chain) throws IOException {
                    String token = Config.getToken();
                    UserInfo userInfo = Config.getUserInfo();
                    Request newRequest;
                    if (!token.isEmpty() && null != userInfo) {
                        newRequest = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("x-token", token)
                                .addHeader("x-user-id", Integer.toString(userInfo.ID))
                                .build();
                    } else newRequest = chain.request();

                    return chain.proceed(newRequest);
                }
            };
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .addInterceptor(tokenInterceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }
}
