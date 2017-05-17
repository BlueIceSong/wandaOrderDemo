package com.exa.wandaorderdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.exa.wandaorderdemo.R;
import com.exa.wandaorderdemo.helper.SqlHelper;

public class MainActivity extends Activity implements View.OnClickListener{
    private static final int REQUEST_CODE_SUCCESS = 2017;
    private static final int REQUEST_CODE_FAILED= 2016;
    private SqlHelper sh = new SqlHelper("192.168.0.33","TestDB","sa","123");
    private Context context;
    private ListView lv;
//    private Button bt;
    private Button openLogin;
    private Button openBaidu;

//    public Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
//        bt = (Button) findViewById(R.id.bt_test);
        openLogin = (Button) findViewById(R.id.openlogin);
        openBaidu = (Button) findViewById(R.id.openBaidu);
//        bt.setOnClickListener(this);
        openLogin.setOnClickListener(this);
        openBaidu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()){
//            case R.id.bt_test:
//                new Thread(net).start();
//                break;
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

//    Runnable net = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                sh.testConnection(context);
//                handler.sendEmptyMessage(REQUEST_CODE_SUCCESS);
//            } catch (SQLException e) {
//                handler.sendEmptyMessage(REQUEST_CODE_FAILED);
//                e.printStackTrace();
//            }
//        }
//    };
}
