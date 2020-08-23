package com.vinson.mmanager.data;

import com.google.gson.JsonObject;
import com.vinson.mmanager.model.response.BaseResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LiftService {

    @GET("/lift/getLiftList")
    Call<BaseResponse<JsonObject>> getLiftList(@Query("page") int page, @Query("pageSize") int pageSize);
}
