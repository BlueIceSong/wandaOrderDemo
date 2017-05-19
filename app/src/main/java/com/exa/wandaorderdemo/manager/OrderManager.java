package com.exa.wandaorderdemo.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.exa.wandaorderdemo.helper.DBHelper;
import com.exa.wandaorderdemo.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Song on 2017/5/16.
 */

public class OrderManager {
    private static DBHelper mHelper;
    private static OrderManager manager;
    private SQLiteDatabase db;
    private Cursor cursor;
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

    /**
     * 获取全部合同
     * @return
     */
    public List<Order> getOrders() {
        cursor = db.query("\""+Order.TABLE_NAME+"\"", null, null, null, null, null, null);
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

    /**
     * 获取相同客户的合同列表
     * @return
     */
    public List<Order> getOrderListByCustomer(String customer_name){
        List<Order> list = new ArrayList<>();
//        cursor = db.query("\""+Order.TABLE_NAME+"\"", null, null, null, null, null, null);
        String sql = "select * from \""+Order.TABLE_NAME+"\" where "+Order.CUSTOMER_NAME+" =?";
        cursor = db.rawQuery(sql,new String[]{customer_name});
        if (cursor != null){
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

    /**
     * 根据客户获得车牌号
     * @param customer
     * @return
     */
    public List<String> getPlatenumByCustomer(String customer) {
        List<String> list = new ArrayList<>();
        List<Order> orders = getOrderListByCustomer(customer);
        for (int i = 0;i < orders.size(); i++){
            list.add(orders.get(i).getPalet_number());
        }
        return list;
    }

    /**
     * 获取全部客户编号
     * @param orders
     * @return
     */
    public List<String> getCustomerList(List<Order> orders){
        List<String> list = new ArrayList<>();
        for(int i = 0;i < orders.size() ;i++){
            String num = orders.get(i).getCustomer_num();
            list.add(num);
        }
        return list;
    }
}
