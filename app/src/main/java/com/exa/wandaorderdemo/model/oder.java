package com.exa.wandaorderdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/5/12.
 */

public class oder implements Parcelable {
    private String order_number;
    private String custom_name;
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
