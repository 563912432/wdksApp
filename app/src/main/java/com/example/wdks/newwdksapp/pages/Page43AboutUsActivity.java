package com.example.wdks.newwdksapp.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import de.hdodenhof.circleimageview.CircleImageView;

/*
* 关于我们
* */
public class Page43AboutUsActivity extends AppCompatActivity implements View.OnClickListener {
    private View mBack;
    private TextView mTextView1, mTextView2, mTextView3;
    private String mLogoUrl = "http://img.alicdn.com/bao/uploaded/a8/e1/TB1RSXXLXXXXXcGXXXXSutbFXXX.jpg_80x80.jpg";
    private CircleImageView mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page43_about_us);
        initView();
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page43_back);   //返回按钮
        mLogo = (CircleImageView) findViewById(R.id.ks_page43_iv1);   //logo
        mTextView1 = (TextView) findViewById(R.id.ks_page43_tv2);   //主要内容
        mTextView2 = (TextView) findViewById(R.id.ks_page43_tv3);   //公司网址
        mTextView3 = (TextView) findViewById(R.id.ks_page43_tv4);   //联系电话

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);

        ImageLoaderHelper.getInstance().loadImage(mLogoUrl, mLogo);
        mTextView1.setText("    测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据");
        mTextView2.setText("问云公司官方网站:http://www.");
        mTextView3.setText("服务热线:000000000");
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page43_back:
                finish();
                break;
        }
    }
}
