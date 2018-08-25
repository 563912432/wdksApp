package com.example.wdks.newwdksapp.pages;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page36MWDetailsViewPagerAdapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 我的错题页面的详情页
* */
public class Page36MWDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack, mDelete, mCollect;
    private ImageView mImageView1, mLeftArrow, mRightArrow;
    private boolean TAG = true;
    private ViewPager mViewPager;
    private Page36MWDetailsViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mFragments;
    private Page36MWDetailsTestOneFragment mFragmentOne;
    private List<String> radio_questionType, radio_questionNum, radio_questionAll, radio_questionTitle, radio_answerA,
            radio_answerB, radio_answerC, radio_answerD, radio_rightAnswer, radio_your_answer, radio_questionExplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page36_mw_details);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        radio_questionType = new ArrayList<String>();
        radio_questionType.add("单选");
        radio_questionType.add("单选");
        radio_questionType.add("单选");

        radio_questionNum = new ArrayList<String>();
        radio_questionNum.add("1");
        radio_questionNum.add("2");
        radio_questionNum.add("3");

        radio_questionAll = new ArrayList<String>();
        radio_questionAll.add("15");
        radio_questionAll.add("15");
        radio_questionAll.add("15");

        radio_questionTitle = new ArrayList<String>();
        radio_questionTitle.add("第一题的题目");
        radio_questionTitle.add("第二题的题目");
        radio_questionTitle.add("第三题的题目");

        radio_answerA = new ArrayList<String>();
        radio_answerA.add("第一题的选项A");
        radio_answerA.add("第二题的选项A");
        radio_answerA.add("第三题的选项A");

        radio_answerB = new ArrayList<String>();
        radio_answerB.add("第一题的选项B");
        radio_answerB.add("第二题的选项B");
        radio_answerB.add("第三题的选项B");

        radio_answerC = new ArrayList<String>();
        radio_answerC.add("第一题的选项C");
        radio_answerC.add("第二题的选项C");
        radio_answerC.add("第三题的选项C");

        radio_answerD = new ArrayList<String>();
        radio_answerD.add("第一题的选项D");
        radio_answerD.add("第二题的选项D");
        radio_answerD.add("第三题的选项D");

        radio_rightAnswer = new ArrayList<String>();
        radio_rightAnswer.add("A");
        radio_rightAnswer.add("B");
        radio_rightAnswer.add("C");

        radio_questionExplain = new ArrayList<String>();
        radio_questionExplain.add("题目一的答案解析");
        radio_questionExplain.add("题目二的答案解析");
        radio_questionExplain.add("题目三的答案解析");
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page36_back);   //返回按钮
        mDelete = findViewById(R.id.ks_page36_delete);   //删除按钮
        mCollect = findViewById(R.id.ks_page36_collect);   //收藏按钮
        mImageView1 = (ImageView) findViewById(R.id.ks_page36_iv1);   //收藏按钮的动态改变
        mLeftArrow = (ImageView) findViewById(R.id.ks_page36_leftArrow);   //上一题
        mRightArrow = (ImageView) findViewById(R.id.ks_page36_rightArrow);   //下一题
        mViewPager = (ViewPager) findViewById(R.id.ks_page36_viewPager);   //答题页面（主要内容）

        mFragments = new ArrayList<Fragment>();
        for (int i = 0; i < 3; i++) {
            mFragmentOne = Page36MWDetailsTestOneFragment.newInstance(radio_questionType, radio_questionNum,
                    radio_questionAll, radio_questionTitle, radio_answerA, radio_answerB, radio_answerC,
                    radio_answerD, radio_rightAnswer, radio_questionExplain, i);
            mFragments.add(mFragmentOne);
        }

        FragmentManager manager = getSupportFragmentManager();

        mViewPagerAdapter = new Page36MWDetailsViewPagerAdapter(manager, this, mFragments);

        mViewPager.setAdapter(mViewPagerAdapter);

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mLeftArrow.setOnClickListener(this);
        mRightArrow.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page36_back:
                finish();
                break;
            case R.id.ks_page36_delete:
                Toast.makeText(this, "自主将此题从我的收藏中删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page36_collect:
                if (TAG) {
                    mImageView1.setImageResource(R.drawable.ico_collect_on);
                    Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                    TAG = false;
                } else {
                    mImageView1.setImageResource(R.drawable.ico_collect_off);
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                    TAG = true;
                }
                break;
            case R.id.ks_page36_leftArrow:
                Toast.makeText(this, "上一题", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page36_rightArrow:
                Toast.makeText(this, "下一题", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
