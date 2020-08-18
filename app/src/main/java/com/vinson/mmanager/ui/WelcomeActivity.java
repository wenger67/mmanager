package com.vinson.mmanager.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.tools.Config;
import com.vinson.mmanager.utils.Constants;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constants.AROUTER_PAGE_WELCOME)
public class WelcomeActivity extends BaseActivity {
    private static final String TAG = WelcomeActivity.class.getSimpleName();

    private final ViewGroup.LayoutParams wrapContent = new ViewGroup.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    ViewPager mViewPager;
    LinearLayoutCompat mIndicatorContainer;
    AppCompatImageView mEnter;
    ImageView[] mIndicators;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TAG);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.vp);
        mIndicatorContainer = findViewById(R.id.ll_indicator);
        mEnter = findViewById(R.id.img_enter);
    }

    @Override
    protected void initEvent() {
        mViewPager.addOnPageChangeListener(new PageChangeListener());
        mViewPager.setAdapter(new MyPagerAdapter(this));
        mEnter.setOnClickListener(v -> {
            // save first launch flag
            Config.setFirstLaunch(false);
            routeTo(Constants.AROUTER_PAGE_LOGIN, WelcomeActivity.this);
        });
    }

    @Override
    public void onArrival(Postcard postcard) {
        super.onArrival(postcard);
        WelcomeActivity.this.finish();
    }

    void initIndicator(int size) {
        mIndicators = new ImageView[size];
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(wrapContent);
            imageView.setImageResource(R.drawable.ic_welcome_indicator);
            imageView.setEnabled(false);
            imageView.setPadding(10, 5, 10, 5);
            mIndicators[i] = imageView;
            mIndicators[i].setTag(i);
            mIndicatorContainer.addView(imageView);
        }
        if (mIndicators.length > 0) mIndicators[0].setEnabled(true);
    }

    void setIndicatorIndex(int position) {
        if (position < 0 || mIndicators == null || position > mIndicators.length - 1) return;
        for (int i = 0; i < mIndicators.length; i++) {
            mIndicators[i].setEnabled(i == position);
        }
        if (position == mIndicators.length - 1) {
            mEnter.setVisibility(View.VISIBLE);
            mIndicatorContainer.setVisibility(View.GONE);
        } else {
            mEnter.setVisibility(View.GONE);
            mIndicatorContainer.setVisibility(View.VISIBLE);
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        private final int[] GUIDES = new int[]{R.mipmap.img_welcome_guide_1, R.mipmap.img_welcome_guide_2, R.mipmap.img_welcome_guide_3};
        private List<ImageView> imgs;

        public MyPagerAdapter(Context context) {
            this.imgs = new ArrayList<>();
            for (int id : GUIDES) {
                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(wrapContent);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(id);
                this.imgs.add(imageView);
            }
            initIndicator(this.imgs.size());
        }

        @Override
        public int getCount() {
            return this.imgs.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(this.imgs.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(this.imgs.get(position));
            return this.imgs.get(position);
        }
    }

    class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setIndicatorIndex(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
