package com.vinson.mmanager.model.login;

import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.vinson.mmanager.R;
import com.vinson.mmanager.model.Company;
import com.vinson.mmanager.utils.Utils;

import java.util.List;
import java.util.Objects;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class UserInfo extends AbstractFlexibleItem<UserInfo.UserViewHolder> {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public String DeletedAt;
    public String uuid;
    public String phoneNumber;
    public String realName;
    public String nickName;
    public String avatar;
    public int companyId;
    public Company company;
    public String address;
    public String authorityId;

    public UserInfo(String uuid, String phoneNumber,
                    String realName, String nickName, String avatar, int companyId,
                    Company company, String address, String authorityId) {
        this.uuid = uuid;
        this.phoneNumber = phoneNumber;
        this.realName = realName;
        this.nickName = nickName;
        this.avatar = avatar;
        this.companyId = companyId;
        this.company = company;
        this.address = address;
        this.authorityId = authorityId;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_user;
    }

    @Override
    public UserViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new UserViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, UserViewHolder holder,
                               int position, List<Object> payloads) {
        Glide.with(holder.itemView).load(avatar).into(holder.avatar);
        holder.name.setText(realName);
        holder.phone.setText(phoneNumber);
        holder.address.setText(address);
        holder.registTime.setText(TimeUtils.getFitTimeSpan(System.currentTimeMillis(),
                TimeUtils.string2Millis(CreatedAt, Utils.DATE_PATTERN), 3));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return authorityId.equals(userInfo.authorityId) &&
                Objects.equals(uuid, userInfo.uuid) &&
                phoneNumber.equals(userInfo.phoneNumber) &&
                realName.equals(userInfo.realName) &&
                Objects.equals(nickName, userInfo.nickName) &&
                Objects.equals(avatar, userInfo.avatar) &&
                Objects.equals(companyId, userInfo.companyId) &&
                Objects.equals(company, userInfo.company) &&
                Objects.equals(address, userInfo.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, phoneNumber, realName, nickName,
                avatar, companyId, company, address, authorityId);
    }

    @NonNull
    @Override
    public String toString() {
        return "UserInfo{" +
                "uuid='" + uuid + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", companyId=" + companyId +
                ", company=" + company +
                ", address='" + address + '\'' +
                ", authorityId='" + authorityId + '\'' +
                ", ID=" + ID +
                ", CreatedAt='" + CreatedAt + '\'' +
                ", UpdatedAt='" + UpdatedAt + '\'' +
                ", DeletedAt='" + DeletedAt + '\'' +
                '}';
    }

    static class UserViewHolder extends FlexibleViewHolder {
        ShapeableImageView avatar;
        MaterialTextView name, phone, address, registTime;

        public UserViewHolder(@NonNull View itemView, FlexibleAdapter adapter) {
            super(itemView, adapter);
            avatar = itemView.findViewById(R.id.iv_avatar);
            name = itemView.findViewById(R.id.tv_name);
            phone = itemView.findViewById(R.id.tv_phone);
            address = itemView.findViewById(R.id.tv_address);
            registTime = itemView.findViewById(R.id.tv_regist_time);
        }
    }
}
