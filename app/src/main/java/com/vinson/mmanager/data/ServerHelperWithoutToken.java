package com.vinson.mmanager.data;

import com.google.gson.JsonObject;
import com.vinson.mmanager.model.login.Captcha;
import com.vinson.mmanager.model.response.BaseResponse;
import com.vinson.mmanager.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerHelperWithoutToken {

    private static OkHttpClient okHttpClient;
    private static ServerHelperWithoutToken INSTANCE;
    private CaptchaService mCaptchaService;
    private LoginService mLoginService;

    public ServerHelperWithoutToken(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mCaptchaService = retrofit.create(CaptchaService.class);
        mLoginService = retrofit.create(LoginService.class);
    }

    public static ServerHelperWithoutToken getInstance() {
        if (INSTANCE == null)
            return new ServerHelperWithoutToken(Constants.BASE_URL);
        return INSTANCE;
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
