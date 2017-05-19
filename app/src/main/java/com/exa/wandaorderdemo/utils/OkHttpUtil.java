package com.exa.wandaorderdemo.utils;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Song on 2017/5/19.
 */

public class OkHttpUtil {
    public static void post(String address, okhttp3.Callback callback, Map<String, String> map) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        Request request;
        if (map == null) {
            request = new Request.Builder()
                    .url(address)
                    .build();
        } else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            FormBody body = builder.build();
            request = new Request.Builder()
                    .url(address)
                    .post(body)
                    .build();
        }
        client.newCall(request).enqueue(callback);
    }
}
