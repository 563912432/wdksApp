package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page28MNListViewAdapter;
import com.example.wdks.newwdksapp.data.Chapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 我的笔记页面
* */
public class Page28MyNoteActivity extends AppCompatActivity implements View.OnClickListener,
        Page28MNListViewAdapter.MyClickListener {
    private View mBack;
    private List<Chapter> mData;
    private ListView mListView;
    private Page28MNListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page28_my_note);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<Chapter>();
        mData.add(new Chapter("章节练习 第一章", "1"));
        mData.add(new Chapter("章节练习 第三章", "12"));
        mData.add(new Chapter("章节练习 第三章", "22"));
        mData.add(new Chapter("预测试卷（一）", "2"));
        mData.add(new Chapter("预测试卷（一）", "12"));
        mData.add(new Chapter("预测试卷（一）", "20"));
        mData.add(new Chapter("预测试卷（二）", "2"));
        mData.add(new Chapter("预测试卷（二）", "12"));
        mData.add(new Chapter("预测试卷（三）", "20"));

    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page28_back);   //返回按钮
        mListView = (ListView) findViewById(R.id.ks_page28_listView);   //内容
        mAdapter = new Page28MNListViewAdapter(this, mData);

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);

        mListView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page28_back:
                finish();
                break;
        }
    }

    //listView的删除操作
    @Override
    public void onDelete(BaseAdapter baseAdapter, View view, int position) {
        Toast.makeText(this, "删除" + position, Toast.LENGTH_SHORT).show();
    }

    //listView的查看操作
    @Override
    public void onWatch(BaseAdapter baseAdapter, View view, int position) {
        //Toast.makeText(this, "查看" + position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Page38MNDetailsActivity.class));
    }
}
