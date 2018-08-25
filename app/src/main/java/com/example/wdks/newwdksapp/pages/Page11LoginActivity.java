package com.example.wdks.newwdksapp.pages;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;
import com.example.wdks.newwdksapp.tools.MyInitWindow;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/*
* 登录界面
* */
public class Page11LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private View mBack;
    private TextView mTextView1, mTextView2;
    private ImageView mImageView1, mImageView2, mImageView3;
    private EditText mEditText1, mEditText2;
    private Button mButton1;
    private String mLogoUrl = "http://img.alicdn.com/bao/uploaded/a8/e1/TB1RSXXLXXXXXcGXXXXSutbFXXX.jpg_80x80.jpg";
    private String userName, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page11_login);
        MyInitWindow.initWindow(this);  //自定义状态栏颜色
        initView();
    }

    //初始化视图
    private void initView() {
        isConn(this);
        mBack = findViewById(R.id.ks_page11_back);   //返回按钮
        mTextView1 = (TextView) findViewById(R.id.ks_page11_tv1);   //注册按钮
        mImageView1 = (ImageView) findViewById(R.id.ks_page11_iv1);   //logo
        mEditText1 = (EditText) findViewById(R.id.ks_page11_edt1);   //用户名
        mImageView2 = (ImageView) findViewById(R.id.ks_page11_iv2);   //登录的X
        mEditText2 = (EditText) findViewById(R.id.ks_page11_edt2);   //密码
        mImageView3 = (ImageView) findViewById(R.id.ks_page11_iv3);   //密码后面的X
        mTextView2 = (TextView) findViewById(R.id.ks_page11_tv2);   //忘记密码
        mButton1 = (Button) findViewById(R.id.ks_page11_btn1);   //登录按钮

        init();
    }

    //视图操作
    private void init() {
        ImageLoaderHelper.getInstance().loadImage(mLogoUrl, mImageView1);
        mImageView2.setVisibility(View.GONE);
        mImageView3.setVisibility(View.GONE);

        mBack.setOnClickListener(this);
        mTextView1.setOnClickListener(this);
        mEditText1.addTextChangedListener(new EdiChangeListener());
        mEditText2.addTextChangedListener(new Ed2ChangeListener());
        mImageView2.setOnClickListener(this);
        mImageView3.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
        mButton1.setOnClickListener(this);
    }


    //账号行的监听内容变化（内容输入显示删除按钮，无内容隐藏删除按钮）
    private class EdiChangeListener implements TextWatcher {
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

    //密码行的监听内容变化（内容输入显示删除按钮，无内容隐藏删除按钮）
    private class Ed2ChangeListener implements TextWatcher {
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

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page11_back:
                finish();
                break;
            case R.id.ks_page11_tv1:   //注册
                //Toast.makeText(this, "跳转注册页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Page12RegisterActivity.class));
                break;
            case R.id.ks_page11_iv2:   //账号的全部删除
                mEditText1.getText().clear();
                break;
            case R.id.ks_page11_iv3:   //密码的全部删除
                mEditText2.getText().clear();
                break;
            case R.id.ks_page11_tv2:   //忘记密码的操作
                //Toast.makeText(this, "跳转到忘记密码的页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Page13PwdForgetActivity.class));
                break;
            case R.id.ks_page11_btn1:  //登录
                //登录按钮要做的处理
                userName = mEditText1.getText().toString();
                pwd = mEditText2.getText().toString();
                if (userName.length() == 0 || pwd.length() == 0) {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //todo 输入了用户名和密码(不是空值) 匹配 测试用户名为cs，测试密码为1
                    if (userName.equals("cs") && pwd.equals("1")) {
                        //默认从服务器获取到了匹配正确的用户名和密码
                        Toast.makeText(this, "用户名和密码输入正确", Toast.LENGTH_SHORT).show();

                        //把用户名和密码存入到内存中
                        SharedPreferences preferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("name", userName);
                        editor.putString("pwd", pwd);

                        editor.putBoolean("isFirstLogin", false);

                        editor.apply();
                        //让前一个页面直接从内存中取
                        finish();

                    } else {
                        Toast.makeText(this, "用户名和密码输入错误", Toast.LENGTH_SHORT).show();
                    }

                }
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
