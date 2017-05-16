package com.exa.wandaorderdemo.model;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class OrderManager {

    public static List<Order> getOrders(Cursor cursor) {
        List<Order> list = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()){
                Order order = new Order();
                order.setId(cursor.getInt(cursor.getColumnIndex(Order.ID)));
                order.setCustomer_num(cursor.getString(cursor.getColumnIndex(Order.CUSTOMER_NUM)));
                order.setCustomer_name(cursor.getString(cursor.getColumnIndex(Order.CUSTOMER_NAME)));
                order.setOrder_num(cursor.getString(cursor.getColumnIndex(Order.ORDER_NUM)));
                order.setPalet_number(cursor.getString(cursor.getColumnIndex(Order.PALET_NUMBER)));
                order.setWeight(cursor.getDouble(cursor.getColumnIndex(Order.WEIGHT)));
                order.setPrice(cursor.getDouble(cursor.getColumnIndex(Order.PRICE)));
                order.setDate(cursor.getString(cursor.getColumnIndex(Order.DATE)));
                order.setNotes(cursor.getString(cursor.getColumnIndex(Order.NOTES)));
                order.setDescription(cursor.getString(cursor.getColumnIndex(Order.DESCRIPTION)));
                list.add(order);
            }
            cursor.close();
        }
        return list;
    }
}
