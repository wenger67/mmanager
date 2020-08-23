package com.vinson.mmanager.data;

import com.google.gson.JsonObject;
import com.vinson.mmanager.model.request.LoginParams;
import com.vinson.mmanager.model.response.BaseResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/base/login")
    Call<BaseResponse<JsonObject>> login(@Body RequestBody requestBody);
}
