package com.exa.wandaorderdemo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.exa.wandaorderdemo.R;
import com.exa.wandaorderdemo.provider.SqlHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    private static final int REQUEST_CODE = 2017;
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
    private void getPermission(){
        int dd = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        Toast.makeText(this,"sssss"+dd,Toast.LENGTH_SHORT).show();
        if (dd != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            } else {

            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
                getPermission();
//                new Thread(net).start();
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
