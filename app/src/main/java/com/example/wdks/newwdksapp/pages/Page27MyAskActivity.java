package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page27MAListViewAdapter;
import com.example.wdks.newwdksapp.data.Chapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 我的答疑页面
* */
public class Page27MyAskActivity extends AppCompatActivity implements View.OnClickListener,
        Page27MAListViewAdapter.MyClickListener {

    private View mBack;
    private List<Chapter> mData;
    private ListView mListView;
    private Page27MAListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page27_my_ask);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<Chapter>();
        mData.add(new Chapter("章节练习", "1"));
        mData.add(new Chapter("章节练习", "3"));
        mData.add(new Chapter("章节练习", "10"));
        mData.add(new Chapter("章节练习", "20"));
        mData.add(new Chapter("预测试卷", "10"));
        mData.add(new Chapter("预测试卷", "15"));
        mData.add(new Chapter("预测试卷", "20"));
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page27_back);   //返回按钮
        mListView = (ListView) findViewById(R.id.ks_page27_listView);   //主要内容
        mAdapter = new Page27MAListViewAdapter(this, mData);

        init();
    }

    //视图操作
    private void init() {
        mListView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);

        mBack.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page27_back:
                finish();
                break;
        }
    }

    @Override
    public void onWatch(BaseAdapter baseAdapter, View view, int position) {
        //Toast.makeText(this, "查看" + position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Page37MADetailsActivity.class));
    }

    @Override
    public void onDelete(BaseAdapter baseAdapter, View view, int position) {
        Toast.makeText(this, "删除" + position, Toast.LENGTH_SHORT).show();
    }
}
