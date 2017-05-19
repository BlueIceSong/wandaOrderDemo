package com.exa.wandaorderdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.exa.wandaorderdemo.R;
import com.exa.wandaorderdemo.model.Order;
import com.exa.wandaorderdemo.manager.OrderManager;
import com.exa.wandaorderdemo.view.ListviewDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Song on 2017/5/15
 */

public class OrderInfoActivity extends Activity implements View.OnClickListener{
    private Context context;
    private List<Order> list = new ArrayList<>();
    private OrderManager manager;

    @BindView(R.id.customer_num) TextView customer_num;
    @BindView(R.id.customer_name) TextView customer_name;
    @BindView(R.id.order_num) TextView order_num;
    @BindView(R.id.palet_number) TextView palet_number;
    @BindView(R.id.weight) TextView weight;
    @BindView(R.id.price) TextView price;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.date) TextView date;

    @BindView(R.id.re_server) TextView sever;
    @OnClick(R.id.re_server)
    void startServer(){
        Intent i = new Intent(this,ServerdataActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);
        context = this;
        manager = OrderManager.getInstance(context);
        ButterKnife.bind(this);
        getDate();  //获得全部数据
        initClickListener();
        setDefaultDate(); //设置默认数据
    }

    public void initClickListener() {
        customer_num.setOnClickListener(this);
        customer_name.setOnClickListener(this);
        order_num.setOnClickListener(this);
        palet_number.setOnClickListener(this);
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
        palet_number.setText(list.get(0).getPalet_number());
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
                dialog(customer_num);
                break;
            case R.id.customer_name:
                break;
            case R.id.palet_number:
                dialog(palet_number);
                break;
            default:break;
        }
    }

    public void getDate() {
        list = manager.getOrders();
        Toast.makeText(this,"list===>"+list.size(),Toast.LENGTH_SHORT).show();
    }

    private void dialog(View id) {
        final ListviewDialog ldialog = new ListviewDialog(this);
        if (id == customer_num) {
            ldialog.setListView(manager.getCustomerList(manager.getOrders()));
        } else if (id == palet_number){
            ldialog.setListView(manager.getPlatenumByCustomer(customer_name.getText().toString()));
        }
        ldialog.setCancelOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ldialog.cancel();
            }
        });
        ldialog.setConfirmOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ldialog.cancel();
            }
        });
        ldialog.show();
    }

}
