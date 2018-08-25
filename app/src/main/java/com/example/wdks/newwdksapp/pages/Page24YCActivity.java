package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page24YCListViewAdapter;
import com.example.wdks.newwdksapp.data.Chapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

public class Page24YCActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {
    private View mBack;
    private List<Chapter> mData;
    private ListView mListView;
    private Page24YCListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page24_yc);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<Chapter>();
        mData.add(new Chapter("真题模拟一", "100", "（未完成）"));
        mData.add(new Chapter("真题模拟二", "100", "（未完成）"));
        mData.add(new Chapter("真题模拟三", "100", "（已完成）"));
        mData.add(new Chapter("真题模拟四", "100", "（未完成）"));
        mData.add(new Chapter("真题模拟五", "100", "（未完成）"));
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page24_back);   //返回按钮
        mListView = (ListView) findViewById(R.id.ks_page24_listView);   //内容
        mAdapter = new Page24YCListViewAdapter(this, mData);

        init();
    }

    //视图操作
    private void init() {
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mBack.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page24_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Page24YCActivity.this, Page31YCTestActivity.class));
    }
}
