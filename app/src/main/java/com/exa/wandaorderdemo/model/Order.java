package com.exa.wandaorderdemo.model;

/**
 * Created by Song on 2017/5/12.
 */

public class Order {
    public int id;
    public String customer_name;
    public String order_num;
    public String customer_num;
    public String palet_number;
    public double weight;
    public double price;
    public String date;
    public String notes;
    public String description;

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
}