package com.example.wdks.newwdksapp.pages;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyInitWindowGray;

/*
* 订单管理
* */
public class Page39OrderRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack;
    private TextView mBook, mExam;
    private Page39OrderRecordOneFragment mFragmentOne;
    private Page39OrderRecordTwoFragment mFragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindowGray.initWindow(this);
        setContentView(R.layout.page39_order_record);
        initView();
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page39_back);   //返回按钮
        mBook = (TextView) findViewById(R.id.ks_page39_book);   //图书
        mExam = (TextView) findViewById(R.id.ks_page39_exam);   //题库

        mFragmentOne = new Page39OrderRecordOneFragment();
        mFragmentTwo = new Page39OrderRecordTwoFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.ks_page39_content, mFragmentOne)
                .commit();

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);
        mBook.setOnClickListener(this);
        mExam.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page39_back:   //返回
                finish();
                break;
            case R.id.ks_page39_book:   //图书
                mBook.setTextColor(getResources().getColor(R.color.white));
                mBook.setBackgroundResource(R.color.darkGray);
                mExam.setTextColor(getResources().getColor(R.color.dimGray));
                mExam.setBackgroundResource(R.color.whiteSmoke);
                //页面切换
                if (mFragmentOne.isHidden()) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(mFragmentTwo)
                            .show(mFragmentOne)
                            .commit();
                }


                break;
            case R.id.ks_page39_exam:   //题库
                mExam.setTextColor(getResources().getColor(R.color.white));
                mExam.setBackgroundResource(R.color.darkGray);
                mBook.setTextColor(getResources().getColor(R.color.dimGray));
                mBook.setBackgroundResource(R.color.whiteSmoke);

                //页面切换
                switchFragment(mFragmentOne, mFragmentTwo);
                break;
        }
    }

    //图书和题库之间的切换
    private void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            transaction.hide(from)
                    .add(R.id.ks_page39_content, to)
                    .commit();
        } else {
            transaction.hide(from)
                    .show(to)
                    .commit();
        }
    }
}
