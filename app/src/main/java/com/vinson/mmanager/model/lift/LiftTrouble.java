package com.vinson.mmanager.model.lift;

import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.login.UserInfo;
import com.vinson.mmanager.utils.Utils;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class LiftTrouble extends AbstractFlexibleItem<LiftTrouble.TroubleViewHolder> {
        int ID;
    String CreatedAt;
    String UpdatedAt;
    String DeletedAt;
    int liftId;
    LiftInfo lift;
    int fromCategoryId;
    Category fromCategory;
    String startTime;
    int startUserId;
    UserInfo startUser;
    String responseTime;
    int responseUserId;
    UserInfo responseUser;
    String sceneTime;
    int sceneUserId;
    UserInfo sceneUser;
    String fixTime;
    int fixUserId;
    UserInfo fixUser;
    int fixCategoryId;
    Category fixCategory;
    int reasonCategoryId;
    Category reasonCategory;
    String content;
    int progress;
    int recorderId;
    UserInfo recorder;
    String feedbackContent;
    int feedbackRate;

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
            name = itemView.findViewById(R.id.tv_key);
            code = itemView.findViewById(R.id.tv_name);
            timeCost = itemView.findViewById(R.id.tv_timecost);
        }
    }
}
