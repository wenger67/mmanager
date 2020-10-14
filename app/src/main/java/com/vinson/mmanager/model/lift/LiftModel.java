package com.vinson.mmanager.model.lift;

import com.vinson.mmanager.model.Company;
import com.vinson.mmanager.model.base.TimeWrapper;

public class LiftModel {
    public Company company;
    public String brand;
    public String modal;
    public int load;
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public TimeWrapper TimeWrapper;

    public LiftModel(Company company, String brand, String modal, int load) {
        this.company = company;
        this.brand = brand;
        this.modal = modal;
        this.load = load;
    }
}
