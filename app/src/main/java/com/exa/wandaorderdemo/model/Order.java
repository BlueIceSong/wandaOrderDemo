package com.exa.wandaorderdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Song on 2017/5/12.
 */

public class Order implements Parcelable {
    public static final String TABLE_NAME = "order";

    public static final String ID = "id";
    public static final String CUSTOMER_NUM = "customer_num";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String ORDER_NUM = "order_num";
    public static final String PALET_NUMBER = "palet_number";
    public static final String WEIGHT = "weight";
    public static final String PRICE = "price";
    public static final String DATE = "date";
    public static final String NOTES = "notes";
    public static final String DESCRIPTION = "description";

    private int id;
    private String customer_num;
    private String customer_name;
    private String order_num;
    private String palet_number;
    private double weight;
    private double price;
    private String date;
    private String notes;
    private String description;

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public void setCustomer_num(String customer_num) {
        this.customer_num = customer_num;
    }

    public void setPalet_number(String palet_number) {
        this.palet_number = palet_number;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getOrder_num() {
        return order_num;
    }

    public String getCustomer_num() {
        return customer_num;
    }

    public String getPalet_number() {
        return palet_number;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.customer_num);
        dest.writeString(this.customer_name);
        dest.writeString(this.order_num);
        dest.writeString(this.palet_number);
        dest.writeDouble(this.weight);
        dest.writeDouble(this.price);
        dest.writeString(this.date);
        dest.writeString(this.notes);
        dest.writeString(this.description);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            Order order = new Order();
            order.id = source.readInt();
            order.customer_num = source.readString();
            order.customer_name = source.readString();
            order.order_num = source.readString();
            order.palet_number = source.readString();
            order.weight = source.readDouble();
            order.price = source.readDouble();
            order.date = source.readString();
            order.notes = source.readString();
            order.description = source.readString();
            return order;
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}