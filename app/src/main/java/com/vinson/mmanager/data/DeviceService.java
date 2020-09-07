package com.vinson.mmanager.data;

import com.google.gson.JsonObject;
import com.vinson.mmanager.model.response.BaseResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeviceService {

    @GET("/adDevice/getAdDeviceList")
    Call<BaseResponse<JsonObject>> getAdDeviceList(@Query("page")int page, @Query("pageSize") int pageSize);

    @GET("/adDeviceData/getAdDeviceDataList")
    Call<BaseResponse<JsonObject>> getAdDeviceDataList(@Query("page")int page, @Query("pageSize") int pageSize);

    @GET("/adDeviceEvent/getAdDeviceEventList")
    Call<BaseResponse<JsonObject>> getAdDeviceEventList(@Query("page")int page, @Query("pageSize") int pageSize);

    @GET("/adDeviceConfig/getAdDeviceConfigList")
    Call<BaseResponse<JsonObject>> getAdDeviceConfigList(@Query("page")int page, @Query("pageSize") int pageSize);
}
