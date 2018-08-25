package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page20GridViewAdapter;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.tools.MyGridView;
import com.example.wdks.newwdksapp.tools.MyInitWindow;
import com.example.wdks.newwdksapp.tools.MyLooperTextView;

import java.util.ArrayList;
import java.util.List;

/*
* 我的题库详情页面
* */
public class Page20MyExamActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private View mBack;
    private TextView mTitle;
    private List<CoursesData> mData;
    private MyGridView mGridView;
    private Page20GridViewAdapter mGridViewAdapter;
    private MyLooperTextView mLooperTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);   //蓝色状态栏
        setContentView(R.layout.page20_my_exam);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<CoursesData>();
        mData.add(new CoursesData("drawable://" + R.drawable.ico1, "考试精编"));
        mData.add(new CoursesData("drawable://" + R.drawable.ico2, "章节练习"));
        mData.add(new CoursesData("drawable://" + R.drawable.ico3, "预测试卷"));
        mData.add(new CoursesData("drawable://" + R.drawable.ico4, "我的收藏"));
        mData.add(new CoursesData("drawable://" + R.drawable.ico5, "我的错题"));
        mData.add(new CoursesData("drawable://" + R.drawable.ico6, "我的答疑"));
        mData.add(new CoursesData("drawable://" + R.drawable.ico7, "我的笔记"));
        mData.add(new CoursesData("drawable://" + R.drawable.ico8, "我的统计"));
        mData.add(new CoursesData("drawable://" + R.drawable.ico9, "考前密卷"));
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page20_back);   //返回按钮
        mTitle = (TextView) findViewById(R.id.ks_page20_title);   //标题
        mLooperTextView = (MyLooperTextView) findViewById(R.id.ks_page20_looperTextView);   //滚动标题
        mGridView = (MyGridView) findViewById(R.id.ks_page20_gridView);   //主要内容
        mGridViewAdapter = new Page20GridViewAdapter(this, mData);
        mGridView.setAdapter(mGridViewAdapter);

        init();
    }

    //视图操作
    private void init() {

        String name = getIntent().getExtras().getString("title");
        mTitle.setText(name);
        mBack.setOnClickListener(this);
        mLooperTextView.setTipList(generateTips());
        mGridView.setOnItemClickListener(this);
    }

    private List<String> generateTips() {
        List<String> tips = new ArrayList<>();
        tips.add("平凡的脚步也可以走完伟大的行程");
        tips.add("还能冲动，表示你还对生活有激情，总是冲动，表示你还不懂生活");
        tips.add("名言警句第三句名言警句第三句名言警句第三句名言警句第三句名言警句第三句名言警句第三句");
        return tips;
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page20_back:
                finish();
                break;
        }
    }

    //GridView的Item的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        switch (position) {
            case 0:
                startActivity(new Intent(Page20MyExamActivity.this, Page21MyExamCarefullyActivity.class));
                break;
            case 1:
                startActivity(new Intent(Page20MyExamActivity.this, Page23ChapterActivity.class));
                break;
            case 2:
                startActivity(new Intent(Page20MyExamActivity.this, Page24YCActivity.class));
                break;
            case 3:
                startActivity(new Intent(Page20MyExamActivity.this, Page25MyCollectionActivity.class));
                break;
            case 4:
                startActivity(new Intent(Page20MyExamActivity.this, Page26MyWrongActivity.class));
                break;
            case 5:
                startActivity(new Intent(Page20MyExamActivity.this, Page27MyAskActivity.class));
                break;
            case 6:
                startActivity(new Intent(Page20MyExamActivity.this, Page28MyNoteActivity.class));
                break;
        }
    }
}
