package com.vinson.mmanager.model.lift;

import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.Address;
import com.vinson.mmanager.model.Category;
import com.vinson.mmanager.model.Company;
import com.vinson.mmanager.model.device.Device;
import com.vinson.mmanager.utils.Utils;

import java.util.List;
import java.util.Objects;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class LiftInfo extends AbstractFlexibleItem<LiftInfo.LiftViewHolder> {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public String DeletedAt;
    public String nickName;
    public String code;
    public Company installer;
    public Company maintainer;
    public Company checker;
    public Company owner;
    public String factoryTime;
    public String installTime;
    public String checkTime;
    public LiftModel liftModel;
    public Category category;
    public Address address;
    public String location;
    public int floorCount;
    public String building;
    public int cell;
    public Device mDevice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiftInfo liftInfo = (LiftInfo) o;
        return ID == liftInfo.ID &&
                nickName.equals(liftInfo.nickName) &&
                code.equals(liftInfo.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, nickName, code);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_lift;
    }

    @Override
    public LiftViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new LiftViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, LiftViewHolder holder,
                               int position, List<Object> payloads) {
        holder.name.setText(nickName);
        holder.code.setText(code);
        holder.address.setText(address.addressName + building + "栋" + cell + "单元");
        long uptimeSum = TimeUtils.string2Millis(installTime, Utils.DATE_PATTERN);
        long checktimeSum = TimeUtils.string2Millis(checkTime, Utils.DATE_PATTERN);
        holder.uptime.setText(TimeUtils.getTimeSpanByNow(uptimeSum, TimeConstants.HOUR) + " hours");
        holder.checkTime.setText(TimeUtils.getFitTimeSpanByNow(checktimeSum, 1));
//        holder.online.setText(info.getDevice().isOnline() ? "Online":"Offline");
    }

    static class LiftViewHolder extends FlexibleViewHolder {
        MaterialTextView name, code, address, uptime, checkTime, online;

        public LiftViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            name = itemView.findViewById(R.id.tv_key);
            code = itemView.findViewById(R.id.tv_code);
            address = itemView.findViewById(R.id.tv_address);
            uptime = itemView.findViewById(R.id.tv_uptime);
            checkTime = itemView.findViewById(R.id.tv_checktime);
            online = itemView.findViewById(R.id.tv_online);
        }
    }

    @Override
    public String toString() {
        return "LiftInfo{" +
                "ID=" + ID +
                ", CreatedAt='" + CreatedAt + '\'' +
                ", UpdatedAt='" + UpdatedAt + '\'' +
                ", DeletedAt='" + DeletedAt + '\'' +
                ", nickName='" + nickName + '\'' +
                ", code='" + code + '\'' +
                ", installer=" + installer +
                ", maintainer=" + maintainer +
                ", checker=" + checker +
                ", owner=" + owner +
                ", factoryTime='" + factoryTime + '\'' +
                ", installTime='" + installTime + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", liftModel=" + liftModel +
                ", category=" + category +
                ", address=" + address +
                ", location='" + location + '\'' +
                ", floorCount=" + floorCount +
                ", building='" + building + '\'' +
                ", cell=" + cell +
                ", mDevice=" + mDevice +
                '}';
    }
}
