package com.example.wdks.newwdksapp.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyInitWindowLBlue;

/*
* 新增收货地址页面
* */
public class Page17NewAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page17_new_add_address);
        MyInitWindowLBlue.initWindow(this);   //api 19后自定义状态栏颜色  浅蓝色
        initView();
    }

    //初始化视图
    private void initView() {

        getSupportFragmentManager().beginTransaction()
                .add(R.id.ks_page17, new Page17AddressMainFragment())
                .commit();
    }
}
