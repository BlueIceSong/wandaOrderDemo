package com.exa.wandaorderdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.exa.wandaorderdemo.R;

/**
 * Created by Song on 2017/5/15.
 */

public class OrderInfoActivity extends Activity {
    private TextView customer_num;
    private TextView customer_name;
    private TextView order_num;
    private TextView palte_num;
    private TextView weight;
    private TextView price;
    private TextView notes;
    private TextView description;
    private TextView date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        initView();
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
}
