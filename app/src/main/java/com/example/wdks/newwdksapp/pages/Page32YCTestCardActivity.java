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
import com.example.wdks.newwdksapp.adapter.Page32TestCardGridViewAdapter;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 预测试卷的答题卡
* */
public class Page32YCTestCardActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private View mBack;
    private GridView mGridView;
    private Page32TestCardGridViewAdapter mGridViewAdapter;
    private List<CoursesData> mData;
    private TextView mTextView1, mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page32_yc_test_card);
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
        mBack = findViewById(R.id.ks_page32_back);   //返回按钮
        mGridView = (GridView) findViewById(R.id.ks_page32_gridView);   //主要内容太
        mGridViewAdapter = new Page32TestCardGridViewAdapter(this, mData);
        mGridView.setAdapter(mGridViewAdapter);

        mTextView1 = (TextView) findViewById(R.id.ks_page32_tv1);   //题目类型
        mTextView2 = (TextView) findViewById(R.id.ks_page32_tv2);   //交卷并查看结果

        init();
    }

    //视图操作
    private void init() {
        mTextView1.setText("预测试卷");
        mBack.setOnClickListener(this);
        mGridView.setOnItemClickListener(this);
        mTextView2.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page32_back:
                finish();
                break;
            case R.id.ks_page32_tv2:
                startActivity(new Intent(this, Page33YCTestResultActivity.class));
                break;
        }

    }

    //gridView的Item的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Page32YCTestCardActivity.this, Page31YCTestActivity.class));
    }
}
