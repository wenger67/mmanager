package com.vinson.mmanager.ui.item;

import android.os.Message;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.JsonObject;
import com.socks.library.KLog;
import com.vinson.mmanager.BuildConfig;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.data.ServerHelper;
import com.vinson.mmanager.model.FileUploadAndDownload;
import com.vinson.mmanager.model.lift.LiftRecord;
import com.vinson.mmanager.model.response.BaseResponse;
import com.vinson.mmanager.tools.Config;
import com.vinson.mmanager.ui.page.CreateRecordActivity;
import com.vinson.mmanager.utils.Constants;
import com.vinson.mmanager.utils.Utils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.vinson.mmanager.model.annotation.RecordProgress.PROGRESS_CREATED;
import static com.vinson.mmanager.model.annotation.RecordProgress.PROGRESS_FINISHED;
import static com.vinson.mmanager.model.annotation.RecordProgress.PROGRESS_REVIEWED;
import static com.vinson.mmanager.model.annotation.RecordProgress.PROGRESS_STARTED;

@Route(path = Constants.AROUTER_PAGE_LIFT_RECORD_DETAIL)
public class LiftRecordDetailActivity extends BaseActivity {
    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_lift_record_detail;
    }
    LiftRecord mLiftRecord;
    // created
    @BindView(R.id.tv_category)
    MaterialTextView mCategory;
    @BindView(R.id.tv_code)
    MaterialTextView mName;
    @BindView(R.id.tv_uptime)
    MaterialTextView mCode;
    @BindView(R.id.tv_create_time)
    MaterialTextView mCreateAt;
    // start
    @BindView(R.id.cv_start)
    MaterialCardView mStartCardView;
    @BindView(R.id.tv_start_user)
    MaterialTextView mStartUser;
    @BindView(R.id.tv_start_company)
    MaterialTextView mStartCompany;
    @BindView(R.id.tv_start_time)
    MaterialTextView mStartTime;
    // end
    @BindView(R.id.cv_end)
    MaterialCardView mEndCardView;
    @BindView(R.id.banner_media)
    Banner<String, BannerImageAdapter<String>> mBanner;
    @BindView(R.id.tv_content)
    MaterialTextView mContent;
    @BindView(R.id.tv_end_time)
    MaterialTextView mEndTime;
    // review
    @BindView(R.id.cv_review)
    MaterialCardView mReviewCardView;
    @BindView(R.id.tv_recorder)
    MaterialTextView mRecorder;
    @BindView(R.id.tv_recorder_company)
    MaterialTextView mRecorderCompany;
    @BindView(R.id.tv_review_time)
    MaterialTextView mReviewTime;

    @BindView(R.id.btn_ops)
    MaterialButton mBtnOps;
    @Override
    protected void initView() {
        super.initView();
        String str = getIntent().getStringExtra(EXTRA_LIFT_RECORD);
        if (str != null && !str.isEmpty()) {
            mLiftRecord = mGson.fromJson(str, LiftRecord.class);
        }

        mCategory.setText(mLiftRecord.category.categoryName);
        mName.setText(mLiftRecord.lift.nickName);
        mCode.setText(mLiftRecord.lift.code);
        mCreateAt.setText(TimeUtils.string2Date(mLiftRecord.CreatedAt, Utils.DATE_PATTERN).toString());

        if (mLiftRecord.progress >= 2) {
            mStartUser.setText(mLiftRecord.worker.realName);
            mStartCompany.setText(mLiftRecord.worker.address);
            mStartTime.setText(TimeUtils.string2Date(mLiftRecord.startTime, Utils.DATE_PATTERN).toString());
        } else {
            mStartCardView.setVisibility(View.GONE);
        }

        if (mLiftRecord.progress >= 3) {
            List<String> images = new ArrayList<>();
            for (FileUploadAndDownload file: mLiftRecord.medias) {
                images.add(file.url);
            }
            KLog.d(Arrays.toString(new List[]{images}));
            mBanner.setAdapter(new BannerImageAdapter<String>(images) {
                @Override
                public void onBindView(BannerImageHolder holder, String data, int position,
                                       int size) {
                    Glide.with(holder.itemView)
                            .load(data.replace("127.0.0.1:8888", BuildConfig.BaseUrl))
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(5)))
                            .into(holder.imageView);
                }
            })
            .addBannerLifecycleObserver(this)
            .setIndicator(new CircleIndicator(this));
            mContent.setText(mLiftRecord.content);
            mEndTime.setText(TimeUtils.string2Date(mLiftRecord.endTime, Utils.DATE_PATTERN).toString());
        } else {
            mEndCardView.setVisibility(View.GONE);
        }

        if (mLiftRecord.progress == 4) {
            mRecorder.setText(mLiftRecord.recorder.realName);
            mRecorderCompany.setText(mLiftRecord.recorder.address);
            mReviewTime.setText(TimeUtils.string2Date(mLiftRecord.UpdatedAt, Utils.DATE_PATTERN).toString());
        } else {
            mReviewCardView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        switch (mLiftRecord.progress) {
            case PROGRESS_CREATED:
                mBtnOps.setText("开始");
                mBtnOps.setOnClickListener(v -> {
                    mLiftRecord.workerId = Config.getUserInfo().ID;
                    RequestBody body = RequestBody.create(MediaType.parse("application/json"), mGson.toJson(mLiftRecord));
                    ServerHelper.getInstance().updateLiftRecord(body).enqueue(new Callback<BaseResponse<JsonObject>>() {
                        @Override
                        @EverythingIsNonNull
                        public void onResponse(Call<BaseResponse<JsonObject>> call,
                                               Response<BaseResponse<JsonObject>> response) {
                            BaseResponse<JsonObject> body = response.body();
                            if (body != null) {
                                KLog.d(body.getMsg());
                                routeTo(Constants.AROUTER_PAGE_LIFT_RECORDS, LiftRecordDetailActivity.this);
                            } else {
                                KLog.w("get data empty!");
                            }
                        }

                        @Override
                        @EverythingIsNonNull
                        public void onFailure(Call<BaseResponse<JsonObject>> call, Throwable t) {
                            KLog.d(t.getMessage());
                        }
                    });
                });
                break;
            case PROGRESS_STARTED:
                mBtnOps.setText("前往处理");
                mBtnOps.setOnClickListener(v ->
                        ARouter.getInstance()
                                .build(Constants.AROUTER_PAGE_FILL_RECORD_CONTENT)
                                .withObject(EXTRA_LIFT_RECORD, mLiftRecord)
                                .navigation(LiftRecordDetailActivity.this)
                );
                break;
            case PROGRESS_REVIEWED:
                mBtnOps.setText("审核");
                mBtnOps.setOnClickListener(v -> {
                    mLiftRecord.recorderId = Config.getUserInfo().ID;
                    RequestBody body = RequestBody.create(MediaType.parse("application/json"), mGson.toJson(mLiftRecord));
                    ServerHelper.getInstance().updateLiftRecord(body).enqueue(new Callback<BaseResponse<JsonObject>>() {
                        @Override
                        @EverythingIsNonNull
                        public void onResponse(Call<BaseResponse<JsonObject>> call,
                                               Response<BaseResponse<JsonObject>> response) {
                            BaseResponse<JsonObject> body = response.body();
                            if (body != null) {
                                KLog.d(body.getMsg());
                                routeTo(Constants.AROUTER_PAGE_LIFT_RECORDS, LiftRecordDetailActivity.this);
                            } else {
                                KLog.w("get data empty!");
                            }
                        }

                        @Override
                        @EverythingIsNonNull
                        public void onFailure(Call<BaseResponse<JsonObject>> call, Throwable t) {
                            KLog.d(t.getMessage());
                        }
                    });
                });
                break;
            case PROGRESS_FINISHED:
                mBtnOps.setText(R.string.record_progress_finished);
            default:
        }
    }

    @Override
    public void onArrival(Postcard postcard) {
        super.onArrival(postcard);
        LiftRecordDetailActivity.this.finish();
    }
}
