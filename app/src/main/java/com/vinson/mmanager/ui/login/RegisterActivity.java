package com.vinson.mmanager.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.utils.Constants;

@Route(path = Constants.AROUTER_PAGE_REGISTER)
public class RegisterActivity extends BaseActivity {
    public static final String TAG = RegisterActivity.class.getSimpleName();
    TextInputEditText mPhoneNumber, mPassword, mRepass;
    FloatingActionButton mFab;
    MaterialButton mEnter;
    MaterialCardView mCvRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ShowEnterAnimation();
        super.onCreate(savedInstanceState);
        setTitle(TAG);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mPhoneNumber = findViewById(R.id.et_phone);
        mPassword = findViewById(R.id.et_pass);
        mRepass = findViewById(R.id.et_repass);
        mFab = findViewById(R.id.fab);
        mEnter = findViewById(R.id.btn_enter);
        mCvRegister = findViewById(R.id.cv_register);
    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                mCvRegister.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(mCvRegister, mCvRegister.getWidth() / 2, 0, mFab.getWidth() / 2, mCvRegister.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mCvRegister.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(mCvRegister, mCvRegister.getWidth() / 2, 0, mCvRegister.getHeight(), mFab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCvRegister.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                mFab.setImageResource(R.mipmap.login_plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    @Override
    protected void initEvent() {
        mEnter.setOnClickListener(v -> {
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(RegisterActivity.this);
            routeTo(Constants.AROUTER_PAGE_MAIN, optionsCompat.toBundle(), RegisterActivity.this);
        });

        mFab.setOnClickListener(v -> {
            animateRevealClose();
        });
    }

    @Override
    public void onArrival(Postcard postcard) {
        super.onArrival(postcard);
        RegisterActivity.this.finish();
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
