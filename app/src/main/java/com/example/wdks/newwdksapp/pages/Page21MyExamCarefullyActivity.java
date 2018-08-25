package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page21ExamCarefullyEpListViewAdapter;
import com.example.wdks.newwdksapp.data.MyExam;
import com.example.wdks.newwdksapp.tools.MyInitWindow;
import com.example.wdks.newwdksapp.tools.MyInitWindowLBlue;

import java.util.ArrayList;
import java.util.List;

/*
* 题库精选页面
* */
public class Page21MyExamCarefullyActivity extends AppCompatActivity implements View.OnClickListener,
        ExpandableListView.OnChildClickListener {

    private View mBack;
    private ExpandableListView mExpandableListView;
    private Page21ExamCarefullyEpListViewAdapter mAdapter;
    private List<MyExam> mGroup;
    private List<List<MyExam>> mChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page21_exam_carefully);
        MyInitWindowLBlue.initWindow(this);
        initData();
        initView();

    }

    //初始化数据
    private void initData() {
        mGroup = new ArrayList<MyExam>();
        mGroup.add(new MyExam("题库更新说明"));
        mGroup.add(new MyExam("系统模块功能使用说明"));
        mGroup.add(new MyExam("考试大纲"));


        mChild = new ArrayList<List<MyExam>>();
        List<MyExam> list1 = new ArrayList<MyExam>();
        list1.add(new MyExam("测试一"));
        list1.add(new MyExam("测试二"));
        list1.add(new MyExam("测试三"));

        List<MyExam> list2 = new ArrayList<MyExam>();
        list2.add(new MyExam("测试四"));
        list2.add(new MyExam("测试五"));
        list2.add(new MyExam("测试六"));

        List<MyExam> list3 = new ArrayList<MyExam>();
        list3.add(new MyExam("测试七"));
        list3.add(new MyExam("测试八"));
        list3.add(new MyExam("测试九"));
        list3.add(new MyExam("测试十"));
        list3.add(new MyExam("测试十一"));
        list3.add(new MyExam("测试十二"));
        list3.add(new MyExam("测试十三"));

        mChild.add(list1);
        mChild.add(list2);
        mChild.add(list3);
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page21_back);   //返回按钮
        mExpandableListView = (ExpandableListView) findViewById(R.id.ks_page21_expandList);   //内容
        mAdapter = new Page21ExamCarefullyEpListViewAdapter(this, mGroup, mChild);
        mExpandableListView.setAdapter(mAdapter);
        mExpandableListView.expandGroup(0);
        mExpandableListView.setOnChildClickListener(this);
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
            case R.id.ks_page21_back:
                finish();
                break;
        }
    }

    //ExpandListView的子item的点击事件
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(this, "groupPosition" + groupPosition + "childPosition" + childPosition, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Page21MyExamCarefullyActivity.this, Page22CarefullyDetailsActivity.class));
        return false;
    }
}
