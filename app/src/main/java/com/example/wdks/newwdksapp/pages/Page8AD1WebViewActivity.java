package com.example.wdks.newwdksapp.pages;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;
import com.example.wdks.newwdksapp.tools.MyInitWindow;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/*
* 轮播广告的详情页
* */
public class Page8AD1WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;
    private View mBack;
    private WebSettings mWebSettings;
    private String url = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);   //自定义状态栏颜色
        setContentView(R.layout.page8_adweb_view);
        initView();
    }

    //初始化视图
    private void initView() {

        //判断是否有网络连接
        isConn(this);

        //返回键
        mBack = findViewById(R.id.ks_page8_back);
        //WebView
        mWebView = (WebView) findViewById(R.id.ks_page8_webView);

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);

        initWebView();    //WebView操作

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        //WebView加载web资源
        mWebView.loadUrl(url);

        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);   //启用支持JavaScript
        mWebSettings.setLoadsImagesAutomatically(true);   //自动加载图片

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page8_back:
                finish();
                break;
        }
    }

    //判断当前是否有网络连接
    private boolean isConn(Context context) {
        if (context != null) {
            ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo info = conManager.getActiveNetworkInfo();
            if (info == null) {
                Toast.makeText(this, "当前没有网络连接", Toast.LENGTH_SHORT).show();
            } else {
                return info.isAvailable();
            }
        }
        return false;
    }

    //改写返回键的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //取到返回键，如果能返回，就让他返回上一页面
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
