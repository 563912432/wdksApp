package com.example.wdks.newwdksapp.pages;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyInitWindow;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.wdks.newwdksapp.R.id.cancel_action;
import static com.example.wdks.newwdksapp.R.id.ks_page12_back;

/*
* 注册页面
* */
public class Page12RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack;
    private EditText mEditText1, mEditText2, mEditText3, mEditText4;
    private ImageView mImageView1, mImageView2, mImageView3, mImageView4;
    private Button mButton1, mButton2;
    private TextView mTextView1;
    private String s1, s2, s3, s4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page12_register);
        MyInitWindow.initWindow(this);   //自定义状态栏颜色
        initView();
    }

    //初始化视图
    private void initView() {
        isConn(this);
        mBack = findViewById(R.id.ks_page12_back);   //返回按钮
        mEditText1 = (EditText) findViewById(R.id.ks_page12_edt1);   //手机号
        mEditText2 = (EditText) findViewById(R.id.ks_page12_edt2);   //手机验证码
        mEditText3 = (EditText) findViewById(R.id.ks_page12_edt3);   //密码
        mEditText4 = (EditText) findViewById(R.id.ks_page12_edt4);   //确认密码
        mImageView1 = (ImageView) findViewById(R.id.ks_page12_iv1);   //x手机号
        mImageView2 = (ImageView) findViewById(R.id.ks_page12_iv2);   //x手机验证码
        mImageView3 = (ImageView) findViewById(R.id.ks_page12_iv3);   //x密码
        mImageView4 = (ImageView) findViewById(R.id.ks_page12_iv4);   //x确认密码
        mButton1 = (Button) findViewById(R.id.ks_page12_btn1);   //获取验证码
        mButton2 = (Button) findViewById(R.id.ks_page12_btn2);   //注册按钮
        mTextView1 = (TextView) findViewById(R.id.ks_page12_tv1);   //服务条款

        init();
    }

    //视图操作
    private void init() {
        mImageView1.setVisibility(View.GONE);
        mImageView2.setVisibility(View.GONE);
        mImageView3.setVisibility(View.GONE);
        mImageView4.setVisibility(View.GONE);
        initTextView();   //服务条款

     /*   s1 = mEditText1.getText().toString();
        s2 = mEditText2.getText().toString();
        s3 = mEditText3.getText().toString();
        s4 = mEditText4.getText().toString();
        if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")) {
            mButton1.setBackgroundResource(R.drawable.btn_login_shape1);
        } else {
            mButton1.setBackgroundResource(R.drawable.btn_login_shape2);
        }*/

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
            case R.id.ks_page12_back:   //返回按钮
                finish();
                break;
            case R.id.ks_page12_iv1:   //x手机号
                mEditText1.getText().clear();
                break;
            case R.id.ks_page12_iv2:   //x手机验证码
                mEditText2.getText().clear();
                break;
            case R.id.ks_page12_iv3:   //x密码
                mEditText3.getText().clear();
                break;
            case R.id.ks_page12_iv4:   //x确认密码
                mEditText4.getText().clear();
            case R.id.ks_page12_btn1:
                Toast.makeText(this, "获取验证码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page12_btn2:   //注册按钮
                s1 = mEditText1.getText().toString();
                s2 = mEditText2.getText().toString();
                s3 = mEditText3.getText().toString();
                s4 = mEditText4.getText().toString();
                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")) {
                    Toast.makeText(this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //TextView链接
    private void initTextView() {
        String htmlLinkText = "<a href=\"http://www.baidu.com\">《服务条款》</a>";
        mTextView1.setText(Html.fromHtml(htmlLinkText));
        mTextView1.setMovementMethod(LinkMovementMethod.getInstance());
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
