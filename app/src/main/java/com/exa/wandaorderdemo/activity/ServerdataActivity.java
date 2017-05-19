package com.exa.wandaorderdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.exa.wandaorderdemo.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/19.
 */

public class ServerdataActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataByHttp();
    }

    public void getDataByHttp(){
        String address = "http://192.168.0.33:8080/orderServerTest/testData";
        OkHttpUtil.post(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("JSON=====>",response.body().string());
            }
        }, null);
    }

}
