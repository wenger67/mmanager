package com.vinson.mmanager.ui.login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.utils.Constants;

@Route(path = Constants.AROUTER_PAGE_LOGIN)
public class LoginActivity extends BaseActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();
    TextInputEditText mPhoneNumber, mPassword;
    FloatingActionButton mFab;
    MaterialButton mEnter;

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
    }

    @Override
    protected void initEvent() {
        mEnter.setOnClickListener(v -> {
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
            routeTo(Constants.AROUTER_PAGE_MAIN, optionsCompat.toBundle(), LoginActivity.this);
        });

        mFab.setOnClickListener(v -> {
            getWindow().setExitTransition(null);
            getWindow().setEnterTransition(null);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, mFab, mFab.getTransitionName());
//            routeTo(Constants.AROUTER_PAGE_REGISTER, options.toBundle(), LoginActivity.this);
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class), options.toBundle());
        });
    }

    @Override
    public void onArrival(Postcard postcard) {
        super.onArrival(postcard);
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
