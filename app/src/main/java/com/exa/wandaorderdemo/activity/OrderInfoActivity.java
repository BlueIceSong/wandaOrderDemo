package com.exa.wandaorderdemo.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exa.wandaorderdemo.R;
import com.exa.wandaorderdemo.model.Order;
import com.exa.wandaorderdemo.manager.OrderManager;
import com.exa.wandaorderdemo.helper.DBHelper;
import com.exa.wandaorderdemo.utils.ToastUtil;
import com.exa.wandaorderdemo.viewlistener.OrderListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2017/5/15.
 */

public class OrderInfoActivity extends Activity implements View.OnClickListener{
    private Context context;
    private List<Order> list = new ArrayList<>();
    private OrderManager manager;
    private TextView customer_num;
    private TextView customer_name;
    private TextView order_num;
    private TextView palte_num;
    private TextView weight;
    private TextView price;
    private TextView notes;
    private TextView description;
    private TextView date;
    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);
        context = this;
        manager = OrderManager.getInstance(context);
        getDate();  //获得全部数据
        initView();
        initClickListener();
        setDefaultDate(); //设置默认数据
    }

    private void initView() {
        customer_num = (TextView) findViewById(R.id.customer_num);
        customer_name = (TextView) findViewById(R.id.customer_name);
        order_num = (TextView) findViewById(R.id.order_num);
        palte_num = (TextView) findViewById(R.id.palet_number);
        weight = (TextView) findViewById(R.id.weight);
        price = (TextView) findViewById(R.id.price);
        notes = (TextView) findViewById(R.id.notes);
        description = (TextView) findViewById(R.id.description);
        date = (TextView) findViewById(R.id.date);

    }

    public void initClickListener() {
        customer_num.setOnClickListener(this);
        customer_name.setOnClickListener(this);
        order_num.setOnClickListener(this);
        palte_num.setOnClickListener(this);
        weight.setOnClickListener(this);
        price.setOnClickListener(this);
        notes.setOnClickListener(this);
        description.setOnClickListener(this);
        date.setOnClickListener(this);
    }

    private void setDefaultDate() {
        customer_num.setText(list.get(0).getCustomer_num());
        customer_name.setText(list.get(0).getCustomer_name());
        order_num.setText(list.get(0).getOrder_num());
        palte_num.setText(list.get(0).getPalet_number());
        weight.setText(String.valueOf(list.get(0).getWeight()));
        price.setText(String.valueOf(list.get(0).getPrice()));
        notes.setText(list.get(0).getNotes());
        description.setText(list.get(0).getDescription());
        date.setText(list.get(0).getDate());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customer_num:
//                OrderListener.showCustomerNum(context,list,lv);
                showListDialog();
                break;
            case R.id.customer_name:
                break;
            default:break;
        }
    }

    private void showListDialog(){
        LinearLayout linearLayoutMain = new LinearLayout(this);//自定义一个布局文件
        linearLayoutMain.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ListView listView = new ListView(this);//this为获取当前的上下文
        Button btr = new Button(this);
        Button btl = new Button(this);
        listView.setFadingEdgeLength(0);
        List<String> data = OrderManager.getCustomerList(manager.getOrders());
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                data));
        linearLayoutMain.addView(listView);
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("customer").setView(linearLayoutMain).create();//在这里把写好的这个listview的布局加载dialog中
        dialog.setButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
//        dialog.setButton("cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
        dialog.setCanceledOnTouchOutside(false);//使除了dialog以外的地方不能被点击
        dialog.show();
    }

    public void getDate() {
        list = manager.getOrders();
        Toast.makeText(this,"list===>"+list.size(),Toast.LENGTH_SHORT).show();
    }


}
