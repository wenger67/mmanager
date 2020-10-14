package com.vinson.mmanager.data;

import com.google.gson.JsonObject;
import com.vinson.mmanager.model.response.BaseResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface LiftService {

    @GET("/lift/getLiftList")
    Call<BaseResponse<JsonObject>> getLiftList(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("/liftChange/getLiftChangeList")
    Call<BaseResponse<JsonObject>> getLiftChangeList(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("/liftRecord/getLiftRecordList")
    Call<BaseResponse<JsonObject>> getLiftRecordList(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("/liftTrouble/getLiftTroubleList")
    Call<BaseResponse<JsonObject>> getLiftTroubleList(@Query("page") int page, @Query("pageSize") int pageSize);

    @POST("/liftRecord/createLiftRecord")
    Call<BaseResponse<JsonObject>> createLiftRecord(@Body RequestBody body);

    @PUT("/liftRecord/updateLiftRecord")
    Call<BaseResponse<JsonObject>> updateLiftRecord(@Body RequestBody body);
}
