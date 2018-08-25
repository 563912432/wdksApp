package com.example.wdks.newwdksapp.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page41PayRecordListViewAdapter;
import com.example.wdks.newwdksapp.data.PayRecord;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 订单记录
* */
public class Page41PayRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private View mBack;
    private List<PayRecord> mData;
    private ListView mListView;
    private Page41PayRecordListViewAdapter mListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page41_pay_record);
        initData();
        initView();

    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<PayRecord>();
        mData.add(new PayRecord("山东省会计从业资格无纸化考试模拟系统", "2016-04-14 09:23:04", "2016041409230752077", "50"));
        mData.add(new PayRecord("2016年中级会计职称无纸化考试模拟系统", "2015-09-15 08:59:36", "2015091508593611416", "40"));
        mData.add(new PayRecord("山东省会计从业资格无纸化考试模拟系统", "2016-04-14 09:23:04", "2016041409230752077", "50"));
        mData.add(new PayRecord("山东省会计从业资格无纸化考试模拟系统", "2016-04-14 09:23:04", "2016041409230752077", "50"));
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page41_back);   //返回按钮

        mListView = (ListView) findViewById(R.id.ks_page41_listView);   //主要内容
        mListViewAdapter = new Page41PayRecordListViewAdapter(this, mData);
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
            case R.id.ks_page41_back:
                finish();
                break;
        }
    }
}
