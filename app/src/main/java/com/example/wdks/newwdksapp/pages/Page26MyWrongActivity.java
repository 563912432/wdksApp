package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page26MWListViewAdapter;
import com.example.wdks.newwdksapp.data.Chapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 我的错题页面
* */
public class Page26MyWrongActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private View mBack;
    private List<Chapter> mData;
    private ListView mListView;
    private Page26MWListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page26_my_wrong);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<Chapter>();
        mData.add(new Chapter("章节练习", "30"));
        mData.add(new Chapter("预测试卷", "40"));
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page26_back);   //返回按钮
        mListView = (ListView) findViewById(R.id.ks_page26_listView);   //主要内容
        mAdapter = new Page26MWListViewAdapter(Page26MyWrongActivity.this, mData);   //适配器

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
            case R.id.ks_page26_back:
                finish();
                break;
        }
    }

    //ListView的Item点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Page36MWDetailsActivity.class));
    }
}
