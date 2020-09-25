package com.vinson.mmanager.ui.item;

import android.os.Message;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.TimeUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.model.lift.LiftRecord;
import com.vinson.mmanager.utils.Constants;
import com.vinson.mmanager.utils.Utils;

import java.util.Arrays;

import butterknife.BindView;

@Route(path = Constants.AROUTER_PAGE_LIFT_RECORD_DETAIL)
public class LiftRecordDetailActivity extends BaseActivity {
    public static final String EXTRA_LIFT_RECORD = "lift.record";
    public static final int PROGRESS_CRAETED = 1;
    public static final int PROGRESS_STARTED = 2;
    public static final int PROGRESS_FINISHED = 3;

    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_lift_record_detail;
    }
    LiftRecord mLiftRecord;

    @BindView(R.id.tv_category)
    MaterialTextView mCategory;
    @BindView(R.id.tv_name)
    MaterialTextView mName;
    @BindView(R.id.tv_code)
    MaterialTextView mCode;
    @BindView(R.id.tv_create_time)
    MaterialTextView mCreateAt;

    @BindView(R.id.cv_start)
    MaterialCardView mStartCardView;
    @BindView(R.id.tv_start_user)
    MaterialTextView mStartUser;
    @BindView(R.id.tv_start_company)
    MaterialTextView mStartCompany;
    @BindView(R.id.tv_start_time)
    MaterialTextView mStartTime;

    @BindView(R.id.cv_end)
    MaterialCardView mEndCardView;
    @BindView(R.id.tv_media)
    MaterialTextView mMeida;
    @BindView(R.id.tv_content)
    MaterialTextView mContent;
    @BindView(R.id.tv_end_time)
    MaterialTextView mEndTime;

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
            mMeida.setText(Arrays.toString(mLiftRecord.medias));
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
            case PROGRESS_CRAETED:
                mBtnOps.setText("接单");
                break;
            case PROGRESS_STARTED:
                mBtnOps.setText("前往处理");
                break;
            case PROGRESS_FINISHED:
                mBtnOps.setText("提交");
                break;
            default:
        }
    }
}
