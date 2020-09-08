package com.vinson.mmanager.model.lift;

import android.view.View;
import androidx.annotation.NonNull;
import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class LiftChange extends AbstractFlexibleItem<LiftChange.MyViewHolder> {
    int ID;
    String CreatedAt;
    String UpdatedAt;
    String DeletedAt;
    int liftId;
    LiftInfo lift;
    String content;

    public LiftChange(int liftId, LiftInfo lift, String content) {
        this.liftId = liftId;
        this.lift = lift;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_lift_change;
    }

    @Override
    public MyViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new MyViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, MyViewHolder holder,
                               int position, List<Object> payloads) {
        holder.name.setText(lift.nickName);
        holder.code.setText(lift.code);
        holder.content.setText(content);
    }

    static class MyViewHolder extends FlexibleViewHolder {
        MaterialTextView name, code, content;

        public MyViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            name = itemView.findViewById(R.id.tv_key);
            code = itemView.findViewById(R.id.tv_code);
            content = itemView.findViewById(R.id.tv_content);
        }
    }
}
