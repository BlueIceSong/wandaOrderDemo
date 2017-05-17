package com.exa.wandaorderdemo.viewlistener;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.exa.wandaorderdemo.manager.OrderManager;
import com.exa.wandaorderdemo.model.Order;
import com.exa.wandaorderdemo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2017/5/17.
 */

public class OrderListener {

    public static void showCustomerNum(Context context,List<Order> orders, ListView listView){
        List<String> list = OrderManager.getCustomerList(orders);
        listView.setAdapter(new ArrayAdapter<>(context,
                android.R.layout.simple_expandable_list_item_1, list));
        ToastUtil.showToast(context,"listView===>"+list.size());
    }
}
