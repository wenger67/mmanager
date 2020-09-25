package com.vinson.mmanager.model;

import com.vinson.mmanager.model.base.DeletedAt;

public class Address {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public DeletedAt DeletedAt;
    public Region region;
    public String addressName;
    public String location;
    public int userAmount;

    public Address(int ID, Region region, String addressName, String location, int userAmount) {
        this.ID = ID;
        this.region = region;
        this.addressName = addressName;
        this.location = location;
        this.userAmount = userAmount;
    }

    @Override
    public String toString() {
        return "Address{" +
                "region=" + region +
                ", addressName='" + addressName + '\'' +
                ", location='" + location + '\'' +
                ", userAmount=" + userAmount +
                '}';
    }
}
