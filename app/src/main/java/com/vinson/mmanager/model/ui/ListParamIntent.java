package com.vinson.mmanager.model.ui;


import android.os.Parcel;
import android.os.Parcelable;

public class ListParamIntent implements Parcelable {
    @DataListAnnotation.DataType
    public int dataType;

    public ListParamIntent() {
    }

    protected ListParamIntent(Parcel in) {
        dataType = in.readInt();
    }

    public static final Creator<ListParamIntent> CREATOR = new Creator<ListParamIntent>() {
        @Override
        public ListParamIntent createFromParcel(Parcel in) {
            return new ListParamIntent(in);
        }

        @Override
        public ListParamIntent[] newArray(int size) {
            return new ListParamIntent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(dataType);
    }
}
