package com.exa.wandaorderdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.exa.wandaorderdemo.R;
import com.exa.wandaorderdemo.provider.SqlHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    private SqlHelper sh = new SqlHelper("192.168.0.33","TestDB","sa","123");
    private Context context;
    private ListView lv;
    private Button bt;
    private Button openLogin;
    private Button openBaidu;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        bt = (Button) findViewById(R.id.bt_test);
        openLogin = (Button) findViewById(R.id.openlogin);
        openBaidu = (Button) findViewById(R.id.openBaidu);
        bt.setOnClickListener(this);
        openLogin.setOnClickListener(this);
        openBaidu.setOnClickListener(this);
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
        Intent i = new Intent();
        switch (v.getId()){
            case R.id.bt_test:
                new Thread(net).start();
                break;
            case R.id.openlogin:
                i.setClass(this,LoginActivity.class);
                startActivity(i);
                break;
            case R.id.openBaidu:
                i.setClass(this,OpenBaidu.class);
                startActivity(i);
                break;
            default: break;
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
