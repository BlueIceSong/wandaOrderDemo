package com.exa.wandaorderdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.exa.wandaorderdemo.R;

/**
 * Created by Administrator on 2017/5/15.
 */

public class OpenBaidu extends Activity{
    WebView baidu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_baidu);
        baidu = (WebView) findViewById(R.id.baidu);
//        WebSettings webSettings = baidu.getSettings();
//        webSettings.setJavaScriptEnabled(true);
        baidu.loadUrl("http://www.baidu.com");
    }
}
