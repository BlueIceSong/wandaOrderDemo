package com.exa.wandaorderdemo.model;

/**
 * Created by Song on 2017/5/12.
 */

public class Order {
    public int id;
    public String order_number;
    public String order_name;
    public String customer_name;
    public String palet_number;

    public int getId() {
        return id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public String getOrder_name() {
        return order_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getPalet_number() {
        return palet_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setPalet_number(String palet_number) {
        this.palet_number = palet_number;
    }
}
