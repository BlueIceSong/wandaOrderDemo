package com.exa.wandaorderdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/5/12.
 */

public class order implements Parcelable {
    private String order_number;
    private String custom_name;
    private String palte_number;

    public String getOrder_number() {
        return order_number;
    }

    public String getCustom_name() {
        return custom_name;
    }

    public String getPalte_number() {
        return palte_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public void setCustom_name(String custom_name) {
        this.custom_name = custom_name;
    }

    public void setPalte_number(String palte_number) {
        this.palte_number = palte_number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
