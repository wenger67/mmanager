package com.vinson.mmanager.model.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vinson.mmanager.model.annotation.RecordProgress.PROGRESS_CREATED;
import static com.vinson.mmanager.model.annotation.RecordProgress.PROGRESS_FINISHED;
import static com.vinson.mmanager.model.annotation.RecordProgress.PROGRESS_REVIEWED;
import static com.vinson.mmanager.model.annotation.RecordProgress.PROGRESS_STARTED;

@IntDef({PROGRESS_CREATED, PROGRESS_STARTED, PROGRESS_REVIEWED, PROGRESS_FINISHED})
@Retention(RetentionPolicy.SOURCE)
public @interface RecordProgress {
    int PROGRESS_CREATED = 1; //已创建, 待开始
    int PROGRESS_STARTED = 2; // 已开始，待处理
    int PROGRESS_REVIEWED = 3; // 已处理，待审核
    int PROGRESS_FINISHED = 4; // finish
}
