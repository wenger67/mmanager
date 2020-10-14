package com.vinson.mmanager.model.lift;

import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.FileUploadAndDownload;
import com.vinson.mmanager.model.annotation.RecordProgress;
import com.vinson.mmanager.model.base.TimeWrapper;
import com.vinson.mmanager.model.login.UserInfo;
import com.vinson.mmanager.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class LiftRecord extends AbstractFlexibleItem<LiftRecord.RecordViewHolder> {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public TimeWrapper TimeWrapper;
    public int liftId;
    public LiftInfo lift;
    public int categoryId;
    public Category category;
    public FileUploadAndDownload[] medias;
    public String content;
    public String startTime;
    public String endTime;
    public int workerId;
    public UserInfo worker;
    public int recorderId;
    public UserInfo recorder;
    public int progress;

    @Override
    public String toString() {
        return "LiftRecord{" +
                "ID=" + ID +
                ", CreatedAt='" + CreatedAt + '\'' +
                ", UpdatedAt='" + UpdatedAt + '\'' +
                ", DeletedAt=" + TimeWrapper +
                ", liftId=" + liftId +
                ", lift=" + lift +
                ", categoryId=" + categoryId +
                ", category=" + category +
                ", medias=" + Arrays.toString(medias) +
                ", content='" + content + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", workerId=" + workerId +
                ", worker=" + worker +
                ", recorderId=" + recorderId +
                ", recorder=" + recorder +
                ", progress=" + progress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiftRecord that = (LiftRecord) o;
        return ID == that.ID &&
                liftId == that.liftId &&
                categoryId == that.categoryId &&
                workerId == that.workerId &&
                recorderId == that.recorderId &&
                Objects.equals(CreatedAt, that.CreatedAt) &&
                Objects.equals(UpdatedAt, that.UpdatedAt) &&
                Objects.equals(TimeWrapper, that.TimeWrapper) &&
                Objects.equals(lift, that.lift) &&
                Objects.equals(category, that.category) &&
                Objects.equals(content, that.content) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(worker, that.worker) &&
                Objects.equals(recorder, that.recorder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, CreatedAt, UpdatedAt, TimeWrapper, liftId, lift, categoryId,
                category, content, startTime, endTime, workerId, worker, recorderId, recorder);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_lift_record;
    }

    @Override
    public RecordViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new RecordViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, RecordViewHolder holder,
                               int position, List<Object> payloads) {
        holder.category.setText(category.categoryName);
        holder.name.setText(lift.nickName);
        holder.code.setText(lift.code);
        holder.timeCost.setText(TimeUtils.getTimeSpan(TimeUtils.string2Millis(endTime,
                Utils.DATE_PATTERN), TimeUtils.string2Millis(startTime, Utils.DATE_PATTERN),
                TimeConstants.HOUR) + "小时");
        switch (progress) {
            case RecordProgress.PROGRESS_CREATED:
                holder.progress.setText(R.string.record_progress_under_start);
                break;
            case RecordProgress.PROGRESS_STARTED:
                holder.progress.setText(R.string.record_progress_under_process);
                break;
            case RecordProgress.PROGRESS_REVIEWED:
                holder.progress.setText(R.string.record_progress_under_review);
                break;
            case RecordProgress.PROGRESS_FINISHED:
                holder.progress.setText(R.string.record_progress_finished);
                break;
            default:
                holder.progress.setText(progress);
        }
    }

    static class RecordViewHolder extends FlexibleViewHolder {
        MaterialTextView category, name, code, timeCost, progress;

        public RecordViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            category = itemView.findViewById(R.id.tv_category);
            name = itemView.findViewById(R.id.tv_name);
            code = itemView.findViewById(R.id.tv_code);
            timeCost = itemView.findViewById(R.id.tv_timecost);
            progress = itemView.findViewById(R.id.tv_progress);
        }
    }
}
