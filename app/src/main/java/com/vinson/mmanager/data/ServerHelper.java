package com.vinson.mmanager.data;

import com.google.gson.JsonObject;
import com.socks.library.KLog;
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

public class ServerHelper {

    private static OkHttpClient okHttpClient;
    private static ServerHelper INSTANCE;
    private LiftService mLiftService;

    public ServerHelper(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mLiftService = retrofit.create(LiftService.class);
    }

    public static ServerHelper getInstance() {
        if (INSTANCE == null)
            return new ServerHelper(Constants.BASE_URL);
        return INSTANCE;
    }

    public Call<BaseResponse<JsonObject>> getLiftList(int page, int pageSize) {
        return mLiftService.getLiftList(page, pageSize);
    }

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            Interceptor tokenInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    String token = Config.getToken();
                    UserInfo userInfo = Config.getUserInfo();
                    Request newRequest;
                    if (!token.isEmpty() && null != userInfo) {
                        newRequest = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("x-token", token)
                                .addHeader("x-user-id", Integer.toString(userInfo.getID()))
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
