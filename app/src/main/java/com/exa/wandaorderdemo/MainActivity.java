package com.exa.wandaorderdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.exa.wandaorderdemo.adapter.OrderAdapter;
import com.exa.wandaorderdemo.model.Order;
import com.exa.wandaorderdemo.provider.SqlHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener{
    private SqlHelper sh = new SqlHelper("192.168.0.33","TestDB","sa","123");
    private Context context;
    private ListView lv;
    private Button bt;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String jsonresult = msg.obj.toString();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Order>>(){

            }.getType();
            List<Order> orders = gson.fromJson(jsonresult,type);
            List mOrders = new ArrayList<>();
            Map<String, String> title = new HashMap<String, String>();
            title.put("order_number","订单编码");
            title.put("customer_name","客户名称");
            mOrders.add(title);

            for (Order order : orders) {
                HashMap<String, String> hmOrders = new HashMap<String, String>();
                hmOrders.put("customer_name",order.customer_name);
                hmOrders.put("order_number",order.order_number);
                mOrders.add(hmOrders);
            }
            OrderAdapter oa = new OrderAdapter(getApplicationContext(),mOrders);
            lv.setAdapter(oa);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        bt = (Button) findViewById(R.id.bt_test);
        bt.setOnClickListener(this);
    }

    public void getMsg() {

    }

    public String insert () {
        String sql = "insert into order (id,ordernumber,customername) values(?,?,?)";
        List<Object> params = new ArrayList<Object>();
        int count = sh.ExecuteNonQuery(sql,params);
        if(count == 1) {
            return "success";
        }else {
            return "failed";
        }
    }

    public String update() {
        String sql = "update order set ordernumber = ? , customername = ? where id = ?";
        return  "failed";
    }

    public String select() {
        String sql = "select * from order";
        String jsonResult = "";
        return  jsonResult;
    }

    @Override
    public void onClick(View v) {
        if(v == bt){
           new Thread(net).start();
        }
    }

    Runnable net = new Runnable() {
        @Override
        public void run() {
            try {
                sh.testConnection(context);
                handler.sendEmptyMessage(2017);
            } catch (SQLException e) {
                handler.sendEmptyMessage(2016);
                e.printStackTrace();
            }
        }
    };
}
