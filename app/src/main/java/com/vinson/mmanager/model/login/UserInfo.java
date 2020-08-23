package com.vinson.mmanager.model.login;

import com.vinson.mmanager.model.Company;

import java.util.Objects;

public class UserInfo {
    int ID;
    String CreatedAt;
    String UpdatedAt;
    String uuid;
    String phoneNumber;
    String realName;
    String nickName;
    String avatar;
    String companyId;
    Company company;
    String address;
    int authorityId;

    public UserInfo(int ID, String createdAt, String updatedAt, String uuid, String phoneNumber,
                    String realName, String nickName, String avatar, String companyId,
                    Company company, String address, int authorityId) {
        this.ID = ID;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return ID == userInfo.ID &&
                authorityId == userInfo.authorityId &&
                Objects.equals(CreatedAt, userInfo.CreatedAt) &&
                Objects.equals(UpdatedAt, userInfo.UpdatedAt) &&
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
        return Objects.hash(ID, CreatedAt, UpdatedAt, uuid, phoneNumber, realName, nickName,
                avatar, companyId, company, address, authorityId);
    }
}
