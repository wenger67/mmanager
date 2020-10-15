package com.vinson.mmanager.ui.page;

import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;
import com.socks.library.KLog;
import com.vinson.mmanager.R;
import com.vinson.mmanager.adapter.GridMediaAdapter;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.model.lift.LiftRecord;
import com.vinson.mmanager.utils.Constants;
import com.vinson.mmanager.utils.FullyGridLayoutManager;
import com.vinson.mmanager.utils.GlideEngine;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;

@Route(path = Constants.AROUTER_PAGE_FILL_RECORD_CONTENT)
public class FillRecordContentActivity extends BaseActivity {

    private static final int MAX_MEDIA_COUNT = 9;
    private static final int GRID_SPAN_COUNT = 3;
    @BindView(R.id.tv_lift_name)
    MaterialTextView mLiftName;
    @BindView(R.id.tv_category)
    MaterialTextView mCategoryName;
    LiftRecord mLiftRecord;
    @BindView(R.id.rc_media)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_submit)
    MaterialButton mSubmit;
    @BindView(R.id.et_content)
    TextInputEditText mContent;

    GridMediaAdapter mMediaAdapter;
    GridMediaAdapter.onAddPicClickListener mOnAddPicClickListener = () -> {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(MAX_MEDIA_COUNT - mMediaAdapter.getItemCount() + 1)
                .forResult(new MyResultCallback(mMediaAdapter));
    };

    @Override
    protected void initView() {
        super.initView();
        String str = getIntent().getStringExtra(EXTRA_LIFT_RECORD);
        if (str != null && !str.isEmpty()) {
            mLiftRecord = mGson.fromJson(str, LiftRecord.class);
        }
        mLiftName.setText(mLiftRecord.lift.nickName);
        mCategoryName.setText(mLiftRecord.category.categoryName);

        mTitle.setText(R.string.fill_record_title);

        FullyGridLayoutManager layoutManager = new FullyGridLayoutManager(this, GRID_SPAN_COUNT,
                GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(GRID_SPAN_COUNT, ScreenUtils.dip2px(this,
                8), false));
        mMediaAdapter = new GridMediaAdapter(this, mOnAddPicClickListener);
        mMediaAdapter.setSelectMax(9);
        mRecyclerView.setAdapter(mMediaAdapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mLiftRecord.content = s.toString();
            }
        });

        mSubmit.setOnClickListener(v -> {
            KLog.d(mLiftRecord.toString());
        });
    }

    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fill_record_content;
    }

    private static class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridMediaAdapter> mAdapterWeakReference;

        public MyResultCallback(GridMediaAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            for (LocalMedia media : result) {
                Log.i(TAG, "是否压缩:" + media.isCompressed());
                Log.i(TAG, "压缩:" + media.getCompressPath());
                Log.i(TAG, "原图:" + media.getPath());
                Log.i(TAG, "是否裁剪:" + media.isCut());
                Log.i(TAG, "裁剪:" + media.getCutPath());
                Log.i(TAG, "是否开启原图:" + media.isOriginal());
                Log.i(TAG, "原图路径:" + media.getOriginalPath());
                Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
                Log.i(TAG, "宽高: " + media.getWidth() + "x" + media.getHeight());
                Log.i(TAG, "Size: " + media.getSize());
                // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();
                //  方法获取一些额外的资源信息，如旋转角度、经纬度等信息
            }
            if (mAdapterWeakReference.get() != null) {
                mAdapterWeakReference.get().appendList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
        }

        @Override
        public void onCancel() {
            Log.i(TAG, "PictureSelector Cancel");
        }
    }
}
