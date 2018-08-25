package com.example.wdks.newwdksapp.pages;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyInitWindow;


/*
* 修改密码页面
* */
public class Page13PwdForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack;
    private EditText mEditText1, mEditText2, mEditText3, mEditText4;
    private ImageView mImageView1, mImageView2, mImageView3, mImageView4;
    private Button mButton1, mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page13_pwd_forget);
        MyInitWindow.initWindow(this);   //自定义状态栏颜色
        initView();
    }

    //初始化视图
    private void initView() {
        isConn(this);
        mBack = findViewById(R.id.ks_page13_back);   //返回按钮
        mEditText1 = (EditText) findViewById(R.id.ks_page13_edt1);   //手机号
        mEditText2 = (EditText) findViewById(R.id.ks_page13_edt2);   //手机验证码
        mEditText3 = (EditText) findViewById(R.id.ks_page13_edt3);   //密码
        mEditText4 = (EditText) findViewById(R.id.ks_page13_edt4);   //确认密码
        mImageView1 = (ImageView) findViewById(R.id.ks_page13_iv1);   //x手机号
        mImageView2 = (ImageView) findViewById(R.id.ks_page13_iv2);   //x手机验证码
        mImageView3 = (ImageView) findViewById(R.id.ks_page13_iv3);   //x密码
        mImageView4 = (ImageView) findViewById(R.id.ks_page13_iv4);   //x确认密码
        mButton1 = (Button) findViewById(R.id.ks_page13_btn1);   //获取验证码
        mButton2 = (Button) findViewById(R.id.ks_page13_btn2);   //注册按钮

        init();
    }

    //视图操作
    private void init() {
        mImageView1.setVisibility(View.GONE);
        mImageView2.setVisibility(View.GONE);
        mImageView3.setVisibility(View.GONE);
        mImageView4.setVisibility(View.GONE);

        mBack.setOnClickListener(this);
        mImageView1.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
        mImageView3.setOnClickListener(this);
        mImageView4.setOnClickListener(this);

        mEditText1.addTextChangedListener(new Ed1ChangeListener());
        mEditText2.addTextChangedListener(new Ed2ChangeListener());
        mEditText3.addTextChangedListener(new Ed3ChangeListener());
        mEditText4.addTextChangedListener(new Ed4ChangeListener());

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);

    }

    //监听手机号的内容改变
    private class Ed1ChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                mImageView1.setVisibility(View.VISIBLE);
            } else {
                mImageView1.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    //验证码内容的监听变化
    private class Ed2ChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                mImageView2.setVisibility(View.VISIBLE);
            } else {
                mImageView2.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    //监听密码的内容改变
    private class Ed3ChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                mImageView3.setVisibility(View.VISIBLE);
            } else {
                mImageView3.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    //确认密码内容的监听变化
    private class Ed4ChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                mImageView4.setVisibility(View.VISIBLE);
            } else {
                mImageView4.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page13_back:   //返回按钮
                finish();
                break;
            case R.id.ks_page13_iv1:   //x手机号
                mEditText1.getText().clear();
                break;
            case R.id.ks_page13_iv2:   //x手机验证码
                mEditText2.getText().clear();
                break;
            case R.id.ks_page13_iv3:   //x密码
                mEditText3.getText().clear();
                break;
            case R.id.ks_page13_iv4:   //x确认密码
                mEditText4.getText().clear();
                break;
            case R.id.ks_page13_btn1:
                Toast.makeText(this, "获取验证码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page13_btn2:   //注册按钮
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
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
}
