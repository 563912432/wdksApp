package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page23ChapterListViewAdapter;
import com.example.wdks.newwdksapp.data.Chapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 章节练习页面
* */
public class Page23ChapterActivity extends AppCompatActivity implements View.OnClickListener,
        Page23ChapterListViewAdapter.MyClickListener {
    private View mBack;
    private ListView mListView;
    private Page23ChapterListViewAdapter mListAdapter;
    private List<Chapter> mData;
    private int number;
    private static final int N = 1000;
    private TextView mTitle;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case N:
                    if (mTitle != null) {
                        mTitle.setText("章节练习(共" + String.valueOf(number) + "章)");
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page23_chapter);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<Chapter>();
        mData.add(new Chapter("第一章 会计电算化概述", "12", "100", "20", "32"));
        mData.add(new Chapter("第二章 会计软件的运行环境", "12", "100", "20", "32"));
        mData.add(new Chapter("第三章 会计软件的应用", "12", "100", "20", "32"));
        mData.add(new Chapter("第四章 电子表格软件在会计中的应用", "12", "100", "20", "32"));
        mData.add(new Chapter("第五章 会计电算化概述", "12", "100", "20", "32"));
        mData.add(new Chapter("第六章 会计电算化概述", "12", "100", "20", "32"));

        number = mData.size();
        mHandler.obtainMessage(N, number).sendToTarget();
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page23_back);   //返回按钮
        mTitle = (TextView) findViewById(R.id.ks_page23_title);   //标题+章节数
        mListView = (ListView) findViewById(R.id.ks_page23_listView);   //主要内容
        mListAdapter = new Page23ChapterListViewAdapter(this, mData);

        init();
    }

    //视图操作
    private void init() {

        mListAdapter.setOnClickListener(this);
        mListView.setAdapter(mListAdapter);
        mBack.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page23_back:
                finish();
                break;
        }
    }

    //已做题目
    @Override
    public void onExam(BaseAdapter adapter, View view, int position) {
        //Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }

    //收藏题目
    @Override
    public void onCollect(BaseAdapter adapter, View view, int position) {
        //Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }

    //错题数
    @Override
    public void onWrong(BaseAdapter adapter, View view, int position) {
        //Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }

    //清空重做
    @Override
    public void onClear(BaseAdapter adapter, View view, int position) {
        //Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        mData.get(position).setDone("0");
        mListAdapter.notifyDataSetChanged();
        Intent intent = new Intent(Page23ChapterActivity.this, Page29ChapterTestActivity.class);
        intent.putExtra("title", mData.get(position).getTitle());
        startActivity(intent);
    }

    //继续练习
    @Override
    public void onGoon(BaseAdapter adapter, View view, int position) {
        Intent intent = new Intent(Page23ChapterActivity.this, Page29ChapterTestActivity.class);
        intent.putExtra("title", mData.get(position).getTitle());
        startActivity(intent);
    }
}
