package com.vinson.mmanager.model.ws;

import com.google.gson.annotations.SerializedName;

public class WSEvent {
    @SerializedName("event")
    public String what;
    public String[] target;
    public String data;
}
