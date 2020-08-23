package com.vinson.mmanager.data;

import com.vinson.mmanager.model.login.Captcha;
import com.vinson.mmanager.model.response.BaseResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

public interface CaptchaService {

    @POST("/base/captcha")
    Call<BaseResponse<Captcha>> captcha();
}
