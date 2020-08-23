package com.vinson.mmanager.data;

import com.google.gson.JsonObject;
import com.vinson.mmanager.model.login.Captcha;
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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerHelper2 {

    private static OkHttpClient okHttpClient;
    private static ServerHelper2 INSTANCE;
    private DeviceService deviceService;
    private CaptchaService mCaptchaService;
    private LoginService mLoginService;

    public ServerHelper2(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        deviceService = retrofit.create(DeviceService.class);
        mCaptchaService = retrofit.create(CaptchaService.class);
        mLoginService = retrofit.create(LoginService.class);
    }

    public static ServerHelper2 getInstance() {
        if (INSTANCE == null)
            return new ServerHelper2(Constants.BASE_URL);
        return INSTANCE;
    }

    public Call<ResponseBody> getDevice(int deviceId) {
        return deviceService.findDevice(deviceId);
    }

    public Call<BaseResponse<Captcha>> captcha() {
        return mCaptchaService.captcha();
    }

    public Call<BaseResponse<JsonObject>> login(RequestBody requestBody) {
        return mLoginService.login(requestBody);
    }

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }

}
