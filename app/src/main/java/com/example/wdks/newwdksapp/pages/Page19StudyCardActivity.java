package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

/*
* 学习卡页面
* */
public class Page19StudyCardActivity extends AppCompatActivity implements View.OnClickListener {
    private View mBack;
    private TextView mTextView;
    private Button mButton1, mButton2, mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page19_study_card);
        MyInitWindow.initWindow(this);
        initView();
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page19_back);   //返回按钮
        mTextView = (TextView) findViewById(R.id.ks_page19_tv2);   //余额显示
        mButton1 = (Button) findViewById(R.id.ks_page19_btn1);   //充值
        mButton2 = (Button) findViewById(R.id.ks_page19_btn2);   //充值记录
        mButton3 = (Button) findViewById(R.id.ks_page19_btn3);   //缴费记录

        init();
    }

    //视图操作
    private void init() {
        Intent intent = getIntent();
        String number = intent.getExtras().getString("number");
        mTextView.setText(number);

        mBack.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);

    }
    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page19_back:   //返回
                finish();
                break;
            case R.id.ks_page19_btn1:   //充值
                startActivity(new Intent(Page19StudyCardActivity.this, Page45ReChargeActivity.class));
                break;
            case R.id.ks_page19_btn2:   //充值记录
                startActivity(new Intent(Page19StudyCardActivity.this, Page40ReChargeRecordActivity.class));
                break;
            case R.id.ks_page19_btn3:   //缴费记录
                startActivity(new Intent(Page19StudyCardActivity.this, Page41PayRecordActivity.class));
                break;
        }
    }
}
