package com.vinson.mmanager.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.widget.marquee.XMarqueeView;
import com.vinson.mmanager.widget.marquee.XMarqueeViewAdapter;

import java.util.List;

public class HomeMarqueeAdapter extends XMarqueeViewAdapter<String> {

    List<String> mData;
    Context mContext;

    public HomeMarqueeAdapter(List<String> datas, Context context) {
        super(datas);
        this.mData = datas;
        this.mContext = context;
    }

    @Override
    public View onCreateView(XMarqueeView parent) {
        return LayoutInflater.from(mContext).inflate(R.layout.item_home_marquee, null);
    }

    @Override
    public void onBindView(View parent, View view, int position) {
        MaterialTextView textView = view.findViewById(R.id.tv_marquee);
        textView.setText(mData.get(position));
    }
}
