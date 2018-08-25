package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page33TestResultGridViewAdapter;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 预测试卷的查看结果页
* */
public class Page33YCTestResultActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {
    private View mBack, mShare;
    private TextView mTextView1, mTextView2, mTextView3;
    private GridView mGridView;
    private Page33TestResultGridViewAdapter mGridViewAdapter;
    private List<CoursesData> mData;
    private Button mButton1, mButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page33_yc_test_result);
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
        mBack = findViewById(R.id.ks_page33_back);   //返回按钮
        mShare = findViewById(R.id.ks_page33_share);   //分享按钮
        mTextView1 = (TextView) findViewById(R.id.ks_page33_tv2);   //题目类型
        mTextView2 = (TextView) findViewById(R.id.ks_page33_tv3);   //答对题目数量
        mTextView3 = (TextView) findViewById(R.id.ks_page33_tv5);   //题目总数
        mGridView = (GridView) findViewById(R.id.ks_page33_gridView);   //答题卡
        mGridViewAdapter = new Page33TestResultGridViewAdapter(this, mData);   //适配器
        mGridView.setAdapter(mGridViewAdapter);

        mButton1 = (Button) findViewById(R.id.ks_page33_btn1);   //错题解析
        mButton2 = (Button) findViewById(R.id.ks_page33_btn2);   //全部解析

        init();
    }

    //视图操作
    private void init() {

        mTextView1.setText("预测试卷");
        mTextView2.setText(String.valueOf(20));
        mTextView3.setText(String.valueOf(20));

        mBack.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);

        mGridView.setOnItemClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page33_back:
                finish();
                break;
            case R.id.ks_page33_share:
                Toast.makeText(this, "分享功能", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page33_btn1:
                //Toast.makeText(this, "跳转错题解析页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Page33YCTestResultActivity.this, Page34YCTestExplainActivity.class));
                break;
            case R.id.ks_page33_btn2:
                Toast.makeText(this, "跳转全部解析页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //gridView的Item的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }
}
