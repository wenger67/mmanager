package com.vinson.mmanager.ui.login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.data.ServerHelper;
import com.vinson.mmanager.data.ServerHelper2;
import com.vinson.mmanager.model.login.Captcha;
import com.vinson.mmanager.model.login.UserInfo;
import com.vinson.mmanager.model.request.LoginParams;
import com.vinson.mmanager.model.response.BaseResponse;
import com.vinson.mmanager.tools.Config;
import com.vinson.mmanager.utils.Constants;

import org.json.JSONObject;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Route(path = Constants.AROUTER_PAGE_LOGIN)
public class LoginActivity extends BaseActivity implements Handler.Callback {
    public static final String TAG = LoginActivity.class.getSimpleName();
    private static final int MSG_LOGIN_CAPTCHA = 1;
    private static final int MGS_LAUNCH_MAIN = 101;
    TextInputEditText mPhoneNumber, mPassword, mCapchaView;
    FloatingActionButton mFab;
    MaterialButton mEnter;
    ShapeableImageView mImageCaptcha;
    Captcha mCaptcha;
    LoginParams mLoginParams = new LoginParams();
    Handler mHandler = new Handler(Looper.getMainLooper(), this);
    Gson mGson = new Gson();

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case MSG_LOGIN_CAPTCHA:
                fillCaptcha();
                break;
            case MGS_LAUNCH_MAIN:
                launchMain();
                break;
            default:
                break;
        }
        return false;
    }

    private void launchMain() {
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
        routeTo(Constants.AROUTER_PAGE_MAIN, optionsCompat.toBundle(), LoginActivity.this);
    }

    private void fillCaptcha() {
        ServerHelper2.getInstance().captcha().enqueue(new Callback<BaseResponse<Captcha>>() {
            @Override
            public void onResponse(Call<BaseResponse<Captcha>> call,
                                   Response<BaseResponse<Captcha>> response) {
                BaseResponse<Captcha> body = response.body();
                if (body == null) {
                    KLog.w("can't get captcha image");
                    // fill mImageCaptcha with resource
                } else {
                    KLog.d(body.getData().toString());
                    mCaptcha = body.getData();
                    mLoginParams.setCaptchaId(mCaptcha.getCaptchaId());
                    Glide.with(LoginActivity.this).load(mCaptcha.getPicPath()).into(mImageCaptcha);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Captcha>> call, Throwable t) {
                mLoginParams.setCaptchaId(null);
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mPhoneNumber = findViewById(R.id.et_phone);
        mPassword = findViewById(R.id.et_pass);
        mFab = findViewById(R.id.fab);
        mEnter = findViewById(R.id.btn_enter);
        mImageCaptcha = findViewById(R.id.iv_captcha);
        mCapchaView = findViewById(R.id.et_captcha);
        mHandler.sendEmptyMessage(MSG_LOGIN_CAPTCHA);
    }

    @Override
    protected void initEvent() {
        mEnter.setOnClickListener(v -> {
            String verify = mLoginParams.verify();
            if (verify != null) {
                Toasty.error(LoginActivity.this, verify, Toast.LENGTH_LONG, true).show();
            } else {
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), mGson.toJson(mLoginParams));
                ServerHelper2.getInstance().login(requestBody).enqueue(new Callback<BaseResponse<JsonObject>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<JsonObject>> call,
                                           Response<BaseResponse<JsonObject>> response) {
                        BaseResponse<JsonObject> body = response.body();
                        if (body != null) {
                            JsonObject data = body.getData();
                            JsonObject userJson = data.get("user").getAsJsonObject();
                            UserInfo userInfo = mGson.fromJson(userJson, UserInfo.class);
                            String token = data.get("token").toString();
                            String expires = data.get("expiresAt").toString();
                            Config.setUserInfo(userInfo);
                            Config.setToken(token.replace("\"", ""));
                            Config.setTokenExpire(expires);
                            mHandler.sendEmptyMessage(MGS_LAUNCH_MAIN);
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<JsonObject>> call, Throwable t) {
                        Toasty.error(LoginActivity.this, "登陆失败:" + t.getMessage(),
                                Toast.LENGTH_LONG, true).show();
                    }
                });
            }
        });

        mImageCaptcha.setOnClickListener(v -> mHandler.sendEmptyMessage(MSG_LOGIN_CAPTCHA));

        mPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mLoginParams.setPhoneNumber(s.toString());
            }
        });

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mLoginParams.setPassword(s.toString());
            }
        });

        mCapchaView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mLoginParams.setCaptcha(s.toString());
            }
        });

        mFab.setOnClickListener(v -> {
            getWindow().setExitTransition(null);
            getWindow().setEnterTransition(null);
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this
                            , mFab, mFab.getTransitionName());
//            routeTo(Constants.AROUTER_PAGE_REGISTER, options.toBundle(), LoginActivity.this);
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class),
                    options.toBundle());
        });
    }

    @Override
    public void onArrival(Postcard postcard) {
        super.onArrival(postcard);
        LoginActivity.this.finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mFab.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFab.setVisibility(View.VISIBLE);
    }
}
