package com.example.wdks.newwdksapp.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyInitWindow;
import com.example.wdks.newwdksapp.tools.MyInitWindowLBlue;

/*
* 编辑地址页面(父级activity)
* */
public class Page46EditAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindowLBlue.initWindow(this);
        setContentView(R.layout.page46_edit_address);
        initView();
    }

    //初始化视图
    private void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ks_page46, new Page46EditAddressOneFragment())
                .commit();
    }

}
