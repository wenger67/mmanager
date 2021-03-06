package com.vinson.mmanager.model;

import com.vinson.mmanager.model.base.TimeWrapper;

public class Region {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public TimeWrapper TimeWrapper;
    public String province;
    public String city;
    public String district;

    public Region(int ID, String province, String city, String district) {
        this.ID = ID;
        this.province = province;
        this.city = city;
        this.district = district;
    }
}
