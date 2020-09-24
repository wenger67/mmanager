package com.vinson.mmanager.model.base;

import com.google.gson.annotations.SerializedName;

public class DeletedAt {
    @SerializedName("Time")
    public String time;
    @SerializedName("Valid")
    public boolean valid;

    @Override
    public String toString() {
        return "DeletedAt{" +
                "time='" + time + '\'' +
                ", valid=" + valid +
                '}';
    }
}
