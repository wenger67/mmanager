package com.vinson.mmanager.adapter.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.transition.Hold;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.view.IconicsImageView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.ui.HomeGridViewItem;

import java.util.List;

public class HomeGridViewAdapter extends ArrayAdapter<HomeGridViewItem> {

    Context context;
    int layoutRes;
    List<HomeGridViewItem> mDatas;

    public HomeGridViewAdapter(@NonNull Context context, int resource, @NonNull List<HomeGridViewItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutRes = resource;
        this.mDatas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutRes, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.tab_tv);
            viewHolder.icon = convertView.findViewById(R.id.tab_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HomeGridViewItem item = mDatas.get(position);
        viewHolder.textView.setText(item.getTitle());
        viewHolder.textView.setTextColor(context.getResources().getColor(R.color.md_black_1000));
        IconicsDrawable iconicsDrawable = new IconicsDrawable(context, item.getIcon());
        iconicsDrawable.sizeDp(18);
        iconicsDrawable.colorRes(android.R.color.black);
        viewHolder.icon.setIcon(iconicsDrawable);
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
        IconicsImageView icon;
    }
}
