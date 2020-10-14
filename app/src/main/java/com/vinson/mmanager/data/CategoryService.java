package com.vinson.mmanager.data;

import com.google.gson.JsonObject;
import com.vinson.mmanager.model.response.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryService {

    @GET("/categories/getCategoriesList")
    Call<BaseResponse<JsonObject>> getCategoryList(@Query("page") int page, @Query("pageSize") int pageSize);

}
