package com.exa.wandaorderdemo.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.exa.wandaorderdemo.helper.DBHelper;
import com.exa.wandaorderdemo.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2017/5/16.
 */

public class OrderManager {
    private static DBHelper mHelper;
    private static OrderManager manager;
    private SQLiteDatabase db;
    private Context context;

    public OrderManager (Context context){
        mHelper = DBHelper.getInstance(context);
        this.context = context;
        db = mHelper.createDB(context);
    }

    public static OrderManager getInstance(Context context){
        if (manager == null) {
            manager = new OrderManager(context);
        }
        return  manager;
    }

    public List<Order> getOrders() {
        Cursor cursor = db.query("\""+Order.TABLE_NAME+"\"", null, null, null, null, null, null);
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

    public static List<String> getCustomerList(List<Order> orders){
        List<String> list = new ArrayList<>();
        for(int i = 0;i < orders.size() ;i++){
            String num = orders.get(i).getCustomer_num();
            list.add(num);
        }
        return list;
    }
}
