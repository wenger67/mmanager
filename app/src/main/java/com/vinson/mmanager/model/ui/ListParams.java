package com.vinson.mmanager.model.ui;


import android.os.Parcel;
import android.os.Parcelable;

import com.vinson.mmanager.model.annotation.ModuleType;

public class ListParams implements Parcelable {
    public static final Creator<ListParams> CREATOR = new Creator<ListParams>() {
        @Override
        public ListParams createFromParcel(Parcel in) {
            return new ListParams(in);
        }

        @Override
        public ListParams[] newArray(int size) {
            return new ListParams[size];
        }
    };
    @ModuleType
    public int dataType;

    public ListParams(int dataType) {
        this.dataType = dataType;
    }

    protected ListParams(Parcel in) {
        dataType = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(dataType);
    }
}
