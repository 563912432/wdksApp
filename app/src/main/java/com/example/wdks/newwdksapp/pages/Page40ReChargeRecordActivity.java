package com.example.wdks.newwdksapp.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page40ReChargeRecordListViewAdapter;
import com.example.wdks.newwdksapp.data.Recharge;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 充值记录页面
* */
public class Page40ReChargeRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack;
    private List<Recharge> mData;
    private ListView mListView;
    private Page40ReChargeRecordListViewAdapter mListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page40_recharge_record);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<Recharge>();
        mData.add(new Recharge("2016-11-01", "50.00", "SD66000000000"));
        mData.add(new Recharge("2016-10-31", "50.00", "SD66000000000"));
        mData.add(new Recharge("2016-10-21", "50.00", "SD66000000000"));
        mData.add(new Recharge("2016-10-11", "50.00", "SD66000000000"));
        mData.add(new Recharge("2016-11-01", "50.00", "SD66000000000"));
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page40_back);   //返回按钮

        mListView = (ListView) findViewById(R.id.ks_page40_listView);   //订单记录的主要内容
        mListViewAdapter = new Page40ReChargeRecordListViewAdapter(this, mData);
        mListView.setAdapter(mListViewAdapter);

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page40_back:
                finish();
                break;
        }
    }
}
