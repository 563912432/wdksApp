package com.example.wdks.newwdksapp.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

public class Page45ReChargeActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack;
    private EditText mEditText1, mEditText2;
    private ImageView mImageView1, mImageView2;
    private Button mButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page45_recharge);
        initView();
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page45_back);   //返回按钮
        mEditText1 = (EditText) findViewById(R.id.ks_page45_edt1);   //账户
        mEditText2 = (EditText) findViewById(R.id.ks_page45_edt2);   //密码
        mImageView1 = (ImageView) findViewById(R.id.ks_page45_iv1);   //删除账户
        mImageView2 = (ImageView) findViewById(R.id.ks_page45_iv2);   //删除密码
        mButton1 = (Button) findViewById(R.id.ks_page45_btn1);   //确定按钮

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);
        mImageView1.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
        mButton1.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page45_back:   //返回
                finish();
                break;
            case R.id.ks_page45_iv1:   //删除账户
                mEditText1.getText().clear();
                break;
            case R.id.ks_page45_iv2:   //删除密码
                mEditText2.getText().clear();
                break;
            case R.id.ks_page45_btn1:   //确定
                if (mEditText1.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入账户", Toast.LENGTH_SHORT).show();
                } else if (mEditText2.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "充值操作", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
