package com.exa.wandaorderdemo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exa.wandaorderdemo.R;
import com.exa.wandaorderdemo.utils.OkHttpUtil;
import com.exa.wandaorderdemo.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Song on 2017/5/12.
 */

public class LoginActivity extends Activity {
    private static final int REQUEST_CODE = 1;
    private static final int USER_LOGIN = 2017;
    private EditText username;
    private EditText password;
    private Button login;

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == USER_LOGIN){
                String response = (String) msg.obj;
                if(response.equals("true")) {
                    startOrderActivity();
                } else {
                    ToastUtil.showToast(LoginActivity.this,"用户名或密码错误");
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        login();
        getPermission();
    }
    private void getPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);
        }
    }
    public void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
    }

    public void login() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startOrderActivity();   //测试 不用登录 直接进入
                String name = username.getText().toString().trim();
                String pw = password.getText().toString().trim();
                String address = "http://192.168.0.33:8080/orderServerTest/testLogin";
                Map<String,String> map = new HashMap<>();
                map.put("ID",name);
                map.put("PW",pw);
                OkHttpUtil.post(address, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        ToastUtil.showToast(LoginActivity.this,"服务器连接异常");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String re = response.body().string();
                        Log.v("response========>",re);
                        Message message = new Message();
                        message.what = USER_LOGIN;
                        message.obj = re;
                        handler.sendMessage(message);
                    }
                },map);
            }
        });
    }

    private void startOrderActivity() {
        Intent intent = new Intent(getApplicationContext(),OrderInfoActivity.class);
        startActivity(intent);
    }

}
