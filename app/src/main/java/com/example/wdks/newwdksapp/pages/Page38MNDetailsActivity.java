package com.example.wdks.newwdksapp.pages;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page38MNDetailsViewPagerAdapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 我的笔记的详情页面
* */
public class Page38MNDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack, mCollection;
    private ImageView mImageView1, mLeftArrow, mRightArrow;
    private boolean TAG = true;
    private ViewPager mViewPager;
    private Page38MNDetailsViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mFragments;
    private Page38MNDetailsOneFragment mFragmentOne;
    private List<String> radio_questionType, radio_questionNum, radio_questionAll, radio_questionTitle, radio_answerA,
            radio_answerB, radio_answerC, radio_answerD, radio_rightAnswer, radio_your_answer, radio_questionExplain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page38_mn_details);
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

        radio_your_answer = new ArrayList<String>();
        radio_your_answer.add("A");
        radio_your_answer.add("A");
        radio_your_answer.add("A");

        radio_questionExplain = new ArrayList<String>();
        radio_questionExplain.add("题目一的答案解析");
        radio_questionExplain.add("题目二的答案解析");
        radio_questionExplain.add("题目三的答案解析");

    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page38_back);   //返回按钮
        mCollection = findViewById(R.id.ks_page38_collect);   //收藏按钮
        mImageView1 = (ImageView) findViewById(R.id.ks_page38_iv1);   //收藏图标
        mLeftArrow = (ImageView) findViewById(R.id.ks_page38_leftArrow);   //上一题
        mRightArrow = (ImageView) findViewById(R.id.ks_page38_rightArrow);   //下一题

        mViewPager = (ViewPager) findViewById(R.id.ks_page38_viewPager);   //答题的显示区域
        FragmentManager manager = getSupportFragmentManager();
        mFragments = new ArrayList<Fragment>();
        for (int i = 0; i < 3; i++) {
            mFragmentOne = Page38MNDetailsOneFragment.newInstance(radio_questionType, radio_questionNum, radio_questionAll,
                    radio_questionTitle, radio_answerA, radio_answerB, radio_answerC, radio_answerD, radio_rightAnswer,
                    radio_your_answer, radio_questionExplain, i);
            mFragments.add(mFragmentOne);
        }
        mViewPagerAdapter = new Page38MNDetailsViewPagerAdapter(manager, this, mFragments);
        mViewPager.setAdapter(mViewPagerAdapter);

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);
        mCollection.setOnClickListener(this);
        mLeftArrow.setOnClickListener(this);
        mRightArrow.setOnClickListener(this);
    }


    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page38_back:
                finish();
                break;
            case R.id.ks_page38_collect:
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
            case R.id.ks_page38_leftArrow:
                Toast.makeText(this, "上一题", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page38_rightArrow:
                Toast.makeText(this, "下一题", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
