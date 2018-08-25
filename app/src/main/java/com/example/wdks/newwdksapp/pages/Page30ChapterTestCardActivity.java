package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page30TestCardGridViewAdapter;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 答题卡页面
* */
public class Page30ChapterTestCardActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {
    private View mBack;
    private TextView mTextView;
    private GridView mGridView;
    private Page30TestCardGridViewAdapter mCardGridViewAdapter;
    private List<CoursesData> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page30_chapter_test_card);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<CoursesData>();
        for (int i = 0; i < 20; i++) {
            mData.add(new CoursesData(String.valueOf(i + 1)));
        }
    }


    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page30_back);   //返回按钮
        mTextView = (TextView) findViewById(R.id.ks_page30_tv1);   //题目类型
        mGridView = (GridView) findViewById(R.id.ks_page30_gridView);   //主要内容
        mCardGridViewAdapter = new Page30TestCardGridViewAdapter(Page30ChapterTestCardActivity.this, mData);
        mGridView.setAdapter(mCardGridViewAdapter);
        mGridView.setOnItemClickListener(this);

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);

        mTextView.setText("章节练习");

        mGridView.setOnItemClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page30_back:
                finish();
                break;
        }

    }

    //gridView的item的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        finish();
    }
}
