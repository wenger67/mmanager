package com.vinson.mmanager.ui.item;

import android.os.Message;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.BuildConfig;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.model.FileUploadAndDownload;
import com.vinson.mmanager.model.lift.LiftTrouble;
import com.vinson.mmanager.utils.Constants;
import com.vinson.mmanager.utils.Utils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

@Route(path = Constants.AROUTER_PAGE_LIFT_TROUBLE_DETAIL)
public class LiftTroubleDetailActivity extends BaseActivity {
    public static final String EXTRA_LIFT_TROUBLE = "lift.trouble";
    public static final int PROGRESS_CREATED = 1;
    public static final int PROGRESS_RESPONSE = 2;
    public static final int PROGRESS_SCENE = 3;
    public static final int PROGRESS_FIX = 4;
    public static final int PROGRESS_FEEDBACK = 5;
    public static final int PROGRESS_REVIEW = 6;
    LiftTrouble mLiftTrouble;
    // progress 1:created
    @BindView(R.id.tv_category)
    MaterialTextView mTroubleCategory;
    @BindView(R.id.tv_name)
    MaterialTextView mLiftName;
    @BindView(R.id.tv_code)
    MaterialTextView mLiftCode;
    @BindView(R.id.tv_start_user)
    MaterialTextView mStartUser;
    @BindView(R.id.tv_start_company)
    MaterialTextView mStartCompany;
    @BindView(R.id.tv_start_time)
    MaterialTextView mStartTime;

    // progress 2: response
    @BindView(R.id.cv_response)
    MaterialCardView mCardViewResponse;
    @BindView(R.id.tv_response_user)
    MaterialTextView mResponseUser;
    @BindView(R.id.tv_response_company)
    MaterialTextView mResponseCompany;
    @BindView(R.id.tv_response_time)
    MaterialTextView mResponseTime;

    // progress 3:  scene
    @BindView(R.id.cv_scene)
    MaterialCardView mCardViewScene;
    @BindView(R.id.tv_scene_user)
    MaterialTextView mSceneUser;
    @BindView(R.id.tv_scene_company)
    MaterialTextView mSceneCompany;
    @BindView(R.id.tv_scene_time)
    MaterialTextView mSceneTime;

    // progress 4: fix
    @BindView(R.id.cv_fix)
    MaterialCardView mCardViewFix;
    @BindView(R.id.tv_fix_category)
    MaterialTextView mFixCategory;
    @BindView(R.id.tv_reason_category)
    MaterialTextView mReasonCategory;
    @BindView(R.id.tv_fix_user)
    MaterialTextView mFixUser;
    @BindView(R.id.tv_fix_time)
    MaterialTextView mFixTime;


    // medias
    @BindView(R.id.cv_media)
    MaterialCardView mCardViewMedia;
    @BindView(R.id.banner_media)
    Banner<String, BannerImageAdapter<String>> mBannerMedias;
    @BindView(R.id.tv_content)
    MaterialTextView mContent;

    // progress 5: feedback
    @BindView(R.id.cv_feedback)
    MaterialCardView mCardViewFeedback;
    @BindView(R.id.tv_feedback_content)
    MaterialTextView mFeedbackContent;
    @BindView(R.id.tv_feedback_rate)
    MaterialTextView mFeedbackRate;
    @BindView(R.id.tv_feedback_time)
    MaterialTextView mFeedbackTime;

    // progress 6:review
    @BindView(R.id.cv_review)
    MaterialCardView mCardViewReview;
    @BindView(R.id.tv_recorder)
    MaterialTextView mRecorder;
    @BindView(R.id.tv_recorder_company)
    MaterialTextView mRecorderCompany;
    @BindView(R.id.tv_review_time)
    MaterialTextView mReviewTime;

    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_lift_trouble_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        String str = getIntent().getStringExtra(EXTRA_LIFT_TROUBLE);
        if (str != null && !str.isEmpty()) {
            mLiftTrouble = mGson.fromJson(str, LiftTrouble.class);
        }

        mTroubleCategory.setText(mLiftTrouble.fromCategory.categoryName);
        mLiftName.setText(mLiftTrouble.lift.nickName);
        mLiftCode.setText(mLiftTrouble.lift.code);
        mStartUser.setText(mLiftTrouble.startUser.realName);
        mStartCompany.setText(mLiftTrouble.startUser.address);
        mStartTime.setText(TimeUtils.string2Date(mLiftTrouble.startTime, Utils.DATE_PATTERN).toString());

        if (mLiftTrouble.progress >= 2) {
            mCardViewResponse.setVisibility(View.VISIBLE);
            mResponseUser.setText(mLiftTrouble.responseUser.realName);
            mResponseCompany.setText(mLiftTrouble.responseUser.address);
            mResponseTime.setText(TimeUtils.string2Date(mLiftTrouble.responseTime,
                    Utils.DATE_PATTERN).toString());
        } else {
            mCardViewResponse.setVisibility(View.GONE);
        }

        if (mLiftTrouble.progress >= 3) {
            mCardViewScene.setVisibility(View.VISIBLE);
            mSceneUser.setText(mLiftTrouble.sceneUser.realName);
            mSceneCompany.setText(mLiftTrouble.sceneUser.address);
            mSceneTime.setText(TimeUtils.string2Date(mLiftTrouble.sceneTime, Utils.DATE_PATTERN).toString());
        } else {
            mCardViewScene.setVisibility(View.GONE);
        }

        if (mLiftTrouble.progress >= 4) {
            mCardViewFix.setVisibility(View.VISIBLE);
            mFixCategory.setText(mLiftTrouble.fixCategory.categoryName);
            mReasonCategory.setText(mLiftTrouble.reasonCategory.categoryName);
            mFixUser.setText(mLiftTrouble.fixUser.realName);
            mFixTime.setText(TimeUtils.string2Date(mLiftTrouble.fixTime, Utils.DATE_PATTERN).toString());
        } else {
            mCardViewFix.setVisibility(View.GONE);
        }

        if (mLiftTrouble.progress >= 5) {
            mCardViewFeedback.setVisibility(View.VISIBLE);
            mFeedbackContent.setText(mLiftTrouble.feedbackContent);
            mFeedbackRate.setText(mLiftTrouble.feedbackRate + "");
            if (mLiftTrouble.feedbackTime != null)mFeedbackTime.setText(TimeUtils.string2Date(mLiftTrouble.feedbackTime,
                    Utils.DATE_PATTERN).toString());
            else mFeedbackTime.setText(new Date().toString());
        } else {
            mCardViewFeedback.setVisibility(View.GONE);
        }

        if (mLiftTrouble.progress >= 6) {
            mCardViewReview.setVisibility(View.VISIBLE);
            mRecorder.setText(mLiftTrouble.recorder.realName);
            mRecorderCompany.setText(mLiftTrouble.recorder.address);
            mReviewTime.setText(TimeUtils.string2Date(mLiftTrouble.UpdatedAt, Utils.DATE_PATTERN).toString());
        } else {
            mCardViewReview.setVisibility(View.GONE);
        }

        if (mLiftTrouble.medias.length > 0) {
            mBannerMedias.setVisibility(View.VISIBLE);
            List<String> images = new ArrayList<>();
            for (FileUploadAndDownload file : mLiftTrouble.medias) {
                images.add(file.url);
            }
            mBannerMedias.setAdapter(new BannerImageAdapter<String>(images) {
                @Override
                public void onBindView(BannerImageHolder holder, String data, int position,
                                       int size) {
                    Glide.with(holder.itemView)
                            .load(data.replace("127.0.0.1:8888", BuildConfig.BaseUrl))
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(5)))
                            .into(holder.imageView);
                }
            }).addBannerLifecycleObserver(this).setIndicator(new CircleIndicator(this));
        } else {
            mBannerMedias.setVisibility(View.GONE);
        }

        if (mLiftTrouble.content.isEmpty()) {
            mContent.setVisibility(View.GONE);
        } else {
            mContent.setVisibility(View.VISIBLE);
            mContent.setText(mLiftTrouble.content);
        }

        if (mLiftTrouble.medias.length == 0 && mLiftTrouble.content.isEmpty()) {
            mCardViewMedia.setVisibility(View.GONE);
        } else {
            mCardViewMedia.setVisibility(View.VISIBLE);
        }

    }
}
