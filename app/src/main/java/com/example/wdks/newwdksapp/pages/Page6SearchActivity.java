package com.example.wdks.newwdksapp.pages;


import android.annotation.TargetApi;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.DataBase.RecordDAO;
import com.example.wdks.newwdksapp.DataBase.RecordDBHelper;
import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page6ListViewAdapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 搜索页面
 */
public class Page6SearchActivity extends AppCompatActivity implements View.OnClickListener, Page6ListViewAdapter.MyClickListener {

    private ImageView mImageView1, mImageView2;
    private EditText mEditText;
    private Drawable mDrawable;
    private TagFlowLayout mTagFlow;
    private String[] mTag = {"中级会计职称", "会计从业考试", "初级会计职称", "财经法规与会计职业道德", "会计电算化", "会计基础"};
    private ListView mRecordListView;
    private Page6ListViewAdapter mRecordListAdapter;
    private List mRecordsListData, mTempListData;
    private RecordDAO mRecordDAO;
    private TextView mClearAll;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);   //自定义标题栏颜色
        setContentView(R.layout.page6_search);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mRecordDAO = new RecordDAO(this);
        mRecordsListData = new ArrayList();
        //mRecordsListData.addAll(mRecordDAO.getRecordsList());
        mTempListData = new ArrayList();
        mTempListData.addAll(mRecordDAO.getRecordsList());

        reversedList();
        //Collections.reverse(mRecordsListData);
        Log.e("TAG", "" + mRecordsListData);
        //第一次进入判断数据库中是否有历史记录，没有则不显示
        checkRecordsSize();

    }

    //初始化数据
    private void initView() {

        mImageView1 = (ImageView) findViewById(R.id.ks_page6_iv1);   //返回
        mImageView2 = (ImageView) findViewById(R.id.ks_page6_iv2);   //搜索
        mEditText = (EditText) findViewById(R.id.id_page6_edit);     //搜索框
        mTagFlow = (TagFlowLayout) findViewById(R.id.ks_page6_tagFlow); //热门标签
        mRecordListView = (ListView) findViewById(R.id.id_page6_listView);   //历史纪录
        mRecordListAdapter = new Page6ListViewAdapter(Page6SearchActivity.this, mRecordsListData);
        mRecordListView.setAdapter(mRecordListAdapter);
        mRecordListAdapter.setOnClickListener(this);

        init();   //视图操作
    }

    private void init() {
        initEditText();
        initTagFlow();

        mImageView1.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
        mClearAll.setOnClickListener(this);
    }

    //热门标签适配数据
    private void initTagFlow() {
        //适配数据
        mTagFlow.setAdapter(new TagAdapter<String>(mTag) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(Page6SearchActivity.this).inflate(R.layout.page6_tagflow_textview,
                        mTagFlow, false);
                tv.setText(s);
                return tv;
            }
        });
        //点击事件
        mTagFlow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                TextView tv = (TextView) view.findViewById(R.id.ks_page6_tagFlow_tv1);
                //mEditText.setText(tv.getText());

                Intent intent = new Intent(Page6SearchActivity.this, Page7SearchResultActivity.class);
                intent.putExtra("name", tv.getText());
                startActivity(intent);
                return true;
            }
        });
    }

    //搜索框的操作
    private void initEditText() {
        //修改左边搜索按钮的样式
        mDrawable = getResources().getDrawable(R.drawable.ico_search_gray);
        mDrawable.setBounds(0, 0, 90, 90);
        mEditText.setCompoundDrawables(mDrawable, null, null, null);
        //键盘上的搜索键
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    toSearch();
                }
                return false;
            }
        });
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page6_iv1:   //返回按钮
                finish();
                break;
            case R.id.ks_page6_iv2:   //搜索按钮
                toSearch();
                break;
            case R.id.id_page6_tv1:   //全部清除
                mTempListData.clear();
                reversedList();
                //mRecordsListData.clear();
                mRecordDAO.deleteAllRecords();
                mRecordListAdapter.notifyDataSetChanged();
                mClearAll.setText("暂无历史纪录");
                break;
        }
    }

    //ListView的视图的item的点击事件(跳转记录和删除一条记录)
    @Override
    public void onClickToRecord(BaseAdapter adapter, View view, int position) {

        String s = (String) mRecordsListData.get(position);

        Intent intent = new Intent(Page6SearchActivity.this, Page7SearchResultActivity.class);
        intent.putExtra("name", s);
        startActivity(intent);
    }

    @Override
    public void onClickDeleteRecord(BaseAdapter adapter, View view, int position) {

        String s = (String) mRecordsListData.get(position);
        mRecordDAO.deleteOneRecord(s);
        mRecordsListData.remove(position);
        mTempListData.remove(position);
        mRecordListAdapter.notifyDataSetChanged();
        mEditText.setText(null);
        checkRecordsSize();
    }

    //搜索功能
    private void toSearch() {
        if (mEditText.getText().toString().length() > 0) {
            String record = mEditText.getText().toString();
            //判断数据库中是否存在该记录
            if (!mRecordDAO.isHasRecord(record)) {
                mTempListData.add(record);
            }
            //将搜索记录保存在数据库中
            mRecordDAO.addRecords(record);
            reversedList();
            //Collections.reverse(mRecordsListData);
            checkRecordsSize();
            mRecordListAdapter.notifyDataSetChanged();
            mRecordListAdapter.setOnClickListener(Page6SearchActivity.this);

            Intent intent = new Intent(Page6SearchActivity.this, Page7SearchResultActivity.class);
            intent.putExtra("name", record);
            startActivity(intent);
        } else {
            Toast.makeText(Page6SearchActivity.this, "搜索记录不能为空！", Toast.LENGTH_SHORT).show();
        }
    }

    //第一次进入判断数据库中是否有历史记录
    private void checkRecordsSize() {
        mClearAll = (TextView) findViewById(R.id.id_page6_tv1);   //清空历史纪录按钮
        if (mRecordsListData.size() == 0) {
            mClearAll.setText("暂无历史纪录");
        } else {
            mClearAll.setText("清空历史纪录");
        }
    }

    //颠倒list顺序，用户输入的信息会从上依次往下显示
    private void reversedList() {
        mRecordsListData.clear();
        for (int i = mTempListData.size() - 1; i >= 0; i--) {
            mRecordsListData.add(mTempListData.get(i));
        }
    }

}
