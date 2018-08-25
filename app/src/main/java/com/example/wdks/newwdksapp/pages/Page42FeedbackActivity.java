package com.example.wdks.newwdksapp.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

/*
* 问题反馈页面
* */
public class Page42FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack;
    private EditText mEditText1, mEditText2;
    private Button mButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page42_feedback);
        initView();
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page42_back);   //返回按钮
        mEditText1 = (EditText) findViewById(R.id.ks_page42_et1);   //反馈意见
        mEditText2 = (EditText) findViewById(R.id.ks_page42_et2);   //联系方式
        mButton1 = (Button) findViewById(R.id.ks_page42_btn1);   //提交

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);
        mButton1.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page42_back:
                finish();
                break;
            case R.id.ks_page42_btn1:
                if (mEditText1.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写内容", Toast.LENGTH_SHORT).show();
                } else if (mEditText2.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写联系方式", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "反馈的内容是：" + mEditText1.getText().toString() + "联系方式是:" + mEditText2.getText().toString(),
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
