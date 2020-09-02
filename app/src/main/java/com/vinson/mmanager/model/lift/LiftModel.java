package com.vinson.mmanager.model.lift;

import com.vinson.mmanager.model.BaseModel;
import com.vinson.mmanager.model.Company;

public class LiftModel extends BaseModel {
    private Company company;
    private String brand;
    private String modal;
    private int load;

    public LiftModel(Company company, String brand, String modal, int load) {
        this.company = company;
        this.brand = brand;
        this.modal = modal;
        this.load = load;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }
}
