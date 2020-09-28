package com.vinson.mmanager.model.lift;

import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.FileUploadAndDownload;
import com.vinson.mmanager.model.base.DeletedAt;
import com.vinson.mmanager.model.login.UserInfo;
import com.vinson.mmanager.utils.Utils;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class LiftTrouble extends AbstractFlexibleItem<LiftTrouble.TroubleViewHolder> {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public DeletedAt DeletedAt;
    public int liftId;
    public LiftInfo lift;
    public int fromCategoryId;
    public Category fromCategory;
    public String startTime;
    public int startUserId;
    public UserInfo startUser;

    public String responseTime;
    public int responseUserId;
    public UserInfo responseUser;

    public String sceneTime;
    public int sceneUserId;
    public UserInfo sceneUser;

    public String fixTime;
    public int fixUserId;
    public UserInfo fixUser;
    public int fixCategoryId;
    public Category fixCategory;
    public int reasonCategoryId;
    public Category reasonCategory;

    public FileUploadAndDownload[] medias;
    public String content;

    public String feedbackContent;
    public int feedbackRate;
    public String feedbackTime;

    public int recorderId;
    public UserInfo recorder;

    public int progress;
    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_lift_trouble;
    }

    @Override
    public TroubleViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new TroubleViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, TroubleViewHolder holder, int position, List<Object> payloads) {
        holder.category.setText(fromCategory.categoryName);
        holder.name.setText(lift.nickName);
        holder.code.setText(lift.code);
        holder.timeCost.setText(TimeUtils.getTimeSpan(TimeUtils.string2Millis(responseTime,
                Utils.DATE_PATTERN), TimeUtils.string2Millis(startTime, Utils.DATE_PATTERN),
                TimeConstants.HOUR) + "小时");
    }

    static class TroubleViewHolder extends FlexibleViewHolder {
        MaterialTextView category, name, code, timeCost;

        public TroubleViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView,
                    adapter);
            category = itemView.findViewById(R.id.tv_category);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            timeCost = itemView.findViewById(R.id.tv_timecost);
        }
    }
}
