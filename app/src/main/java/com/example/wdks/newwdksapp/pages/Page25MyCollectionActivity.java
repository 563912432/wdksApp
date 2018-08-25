package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page25MCListViewAdapter;
import com.example.wdks.newwdksapp.data.Chapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

public class Page25MyCollectionActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private View mBack;
    private List<Chapter> mData;
    private ListView mListView;
    private Page25MCListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);   //api19后自定义颜色
        setContentView(R.layout.page25_my_collection);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<Chapter>();
        mData.add(new Chapter("章节练习", "100"));
        mData.add(new Chapter("模拟预测", "100"));
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page25_back);   //返回按钮
        mListView = (ListView) findViewById(R.id.ks_page25_listView);   //主要内容
        mAdapter = new Page25MCListViewAdapter(this, mData);

        init();
    }

    //视图操作
    private void init() {
        mListView.setAdapter(mAdapter);
        mBack.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page25_back:
                finish();
                break;
        }

    }

    //listView的Item的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, Page35MCDetailsActivity.class));
    }
}
