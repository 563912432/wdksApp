package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wdks.newwdksapp.MainActivity;
import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyInitWindowGray;

/*
* 支付成功页面
* */
public class Page47PaySuccessActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1, mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindowGray.initWindow(this);
        setContentView(R.layout.page47_pay_success);
        initView();
    }

    //初始化视图
    private void initView() {
        mButton1 = (Button) findViewById(R.id.kd_page47_btn1);   //查看订单
        mButton2 = (Button) findViewById(R.id.kd_page47_btn2);   //返回首页

        init();
    }

    //视图操作
    private void init() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kd_page47_btn1:
                //startActivity(new Intent(Page47PaySuccessActivity.this, Page39OrderRecordOneFragment.class));
                break;
            case R.id.kd_page47_btn2:
                startActivity(new Intent(Page47PaySuccessActivity.this, MainActivity.class));
                break;
        }

    }
}
