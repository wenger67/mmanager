package com.vinson.mmanager.model;

public class Region {
        int ID;
    String CreatedAt;
    String UpdatedAt;
    String DeletedAt;
    String province;
    String city;
    String district;

    public Region(int ID, String province, String city, String district) {
        this.ID = ID;
        this.province = province;
        this.city = city;
        this.district = district;
    }
}
