package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page29TestViewPagerAdapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.util.ArrayList;
import java.util.List;

/*
* 答题页面
* */
public class Page29ChapterTestActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private View mBack, mCard, mCollect;
    private ImageView mImageView1, mPre, mNext;
    private TextView mTextView1;
    private boolean TAG = true;
    private Chronometer mChronometer;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private Page29TestViewPagerAdapter mAdapter;
    private Page29ChapterTestOneFragment mFragmentOne;
    private Page29ChapterTestTwoFragment mFragmentTwo;
    private Page29ChapterTestThreeFragment mFragmentThree;
    private Page29ChapterTestFourFragment mFragmentFour;
    private Page29ChapterTestFiveFragment mFragmentFive;
    private Page29ChapterTestSixFragment mFragmentSix;
    private int number;
    private List<String> radio_questionType, radio_questionNum, radio_questionAll, radio_questionTitle, radio_answerA,
            radio_answerB, radio_answerC, radio_answerD, radio_rightAnswer, radio_questionExplain;
    private List<String> check_questionType, check_questionNum, check_questionAll, check_questionTitle, check_answerA,
            check_answerB, check_answerC, check_answerD, check_answerE, check_rightAnswer, check_questionExplain;
    private List<String> judge_questionType, judge_questionNum, judge_questionAll, judge_questionTitle, judge_right,
            judge_wrong, judge_rightAnswer, judge_questionExplain;
    private List<String> calculate1_questionType, calculate1_questionNum, calculate1_questionAll, calculate1_questionTitle1,
            calculate1_questionTitle2, calculate1_rightAnswer, calculate1_questionExplain;
    private List<String> calculate2_questionType, calculate2_questionNum, calculate2_questionAll, calculate2_questionTitle1,
            calculate2_questionTitle2, calculate2_rightAnswer, calculate2_questionExplain;
    private List<String> analyse_questionType, analyse_questionNum, analyse_questionAll, analyse_questionTitle1, analyse_questionTitle2,
            analyse_answerA, analyse_answerB, analyse_answerC, analyse_answerD, analyse_rightAnswer, analyse_questionExplain;
    private int a;
    private double b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page29_chapter_test);
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

        check_questionType = new ArrayList<String>();
        check_questionType.add("多选");
        check_questionType.add("多选");
        check_questionType.add("多选");

        check_answerE = new ArrayList<String>();
        check_answerE.add("选项E的内容");
        check_answerE.add("选项E的内容");
        check_answerE.add("选项E的内容");

        check_rightAnswer = new ArrayList<String>();
        check_rightAnswer.add("AB");
        check_rightAnswer.add("ABC");
        check_rightAnswer.add("ABCD");

        judge_questionType = new ArrayList<String>();
        judge_questionType.add("判断");
        judge_questionType.add("判断");
        judge_questionType.add("判断");

        judge_right = new ArrayList<String>();
        judge_right.add("正确");
        judge_right.add("正确");
        judge_right.add("正确");

        judge_wrong = new ArrayList<String>();
        judge_wrong.add("错误");
        judge_wrong.add("错误");
        judge_wrong.add("错误");

        judge_rightAnswer = new ArrayList<String>();
        judge_rightAnswer.add("正确");
        judge_rightAnswer.add("错误");
        judge_rightAnswer.add("正确");


        calculate1_questionType = new ArrayList<String>();
        calculate1_questionType.add("计算分析题(一)");
        calculate1_questionType.add("计算分析题(一)");
        calculate1_questionType.add("计算分析题(一)");

        calculate1_questionTitle1 = new ArrayList<String>();
        calculate1_questionTitle1.add("要求：请阅读以上材料回答下列问题");
        calculate1_questionTitle1.add("要求：请阅读以上材料回答下列问题");
        calculate1_questionTitle1.add("要求：请阅读以上材料回答下列问题");

        calculate1_questionTitle2 = new ArrayList<String>();
        calculate1_questionTitle2.add("请填写括号一要填写的内容");
        calculate1_questionTitle2.add("请填写括号二要填写的内容");
        calculate1_questionTitle2.add("请填写括号三要填写的内容");

        calculate1_rightAnswer = new ArrayList<String>();
        calculate1_rightAnswer.add("1024");
        calculate1_rightAnswer.add("2048");
        calculate1_rightAnswer.add("3072");

        calculate2_questionType = new ArrayList<String>();
        calculate2_questionType.add("计算分析题(二)");
        calculate2_questionType.add("计算分析题(二)");
        calculate2_questionType.add("计算分析题(二)");

        calculate2_questionTitle1 = new ArrayList<String>();
        calculate2_questionTitle1.add("要求：根据以上材料回答下列问题");
        calculate2_questionTitle1.add("要求：根据以上材料回答下列问题");
        calculate2_questionTitle1.add("要求：根据以上材料回答下列问题");

        calculate2_questionTitle2 = new ArrayList<String>();
        calculate2_questionTitle2.add("(1)根据上述材料，编制第一笔经济业务的会计分录");
        calculate2_questionTitle2.add("(2)根据上述材料，编制第二笔经济业务的会计分录");
        calculate2_questionTitle2.add("(3)根据上述材料，编制第三笔经济业务的会计分录");

        calculate2_rightAnswer = new ArrayList<String>();
        calculate2_rightAnswer.add("借库存现金1000");
        calculate2_rightAnswer.add("借库存现金1000");
        calculate2_rightAnswer.add("借库存现金1000");

        analyse_questionType = new ArrayList<String>();
        analyse_questionType.add("综合分析题");
        analyse_questionType.add("综合分析题");
        analyse_questionType.add("综合分析题");

        analyse_questionTitle1 = new ArrayList<String>();
        analyse_questionTitle1.add("要求：根据以上材料回答下列问题");
        analyse_questionTitle1.add("要求：根据以上材料回答下列问题");
        analyse_questionTitle1.add("要求：根据以上材料回答下列问题");

        analyse_questionTitle2 = new ArrayList<String>();
        analyse_questionTitle2.add("(1)李某、王某违反了会计职业道德中的( )要求");
        analyse_questionTitle2.add("(2)题目二的题干内容");
        analyse_questionTitle2.add("(3)题目三的题干内容");

    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page29_back);   //返回按钮
        mCard = findViewById(R.id.ks_page29_card);   //答题卡
        mTextView1 = (TextView) findViewById(R.id.ks_page29_tv1);   //标题
        mCollect = findViewById(R.id.ks_page29_collect);   //收藏按钮的父件
        mImageView1 = (ImageView) findViewById(R.id.ks_page29_iv1);   //收藏按钮
        mChronometer = (Chronometer) findViewById(R.id.ks_page29_chronometer);   //倒计时按钮
        mPre = (ImageView) findViewById(R.id.ks_page29_leftArrow);   //上一题
        mNext = (ImageView) findViewById(R.id.ks_page29_rightArrow);   //下一题

        mViewPager = (ViewPager) findViewById(R.id.ks_page29_viewPager);   //viewPager

        mFragments = new ArrayList<Fragment>();
        //fragment的初始化
        //单选题
        for (int i = 0; i < 3; i++) {
            mFragmentOne = Page29ChapterTestOneFragment.newInstance(radio_questionType, radio_questionNum, radio_questionAll,
                    radio_questionTitle, radio_answerA, radio_answerB, radio_answerC, radio_answerD,
                    radio_rightAnswer, radio_questionExplain, i);

            mFragments.add(mFragmentOne);
        }
        //多选题
        for (int i = 0; i < 3; i++) {
            mFragmentTwo = Page29ChapterTestTwoFragment.newInstance(check_questionType, radio_questionNum, radio_questionAll,
                    radio_questionTitle, radio_answerA, radio_answerB, radio_answerC, radio_answerD, check_answerE,
                    check_rightAnswer, radio_questionExplain, i);
            mFragments.add(mFragmentTwo);
        }
        //判断题
        for (int i = 0; i < 3; i++) {
            mFragmentThree = Page29ChapterTestThreeFragment.newInstance(judge_questionType, radio_questionNum, radio_questionAll,
                    radio_questionTitle, judge_right, judge_wrong, judge_rightAnswer, radio_questionExplain, i);
            mFragments.add(mFragmentThree);
        }

        //计算分析题
        for (int i = 0; i < 3; i++) {
            mFragmentFour = Page29ChapterTestFourFragment.newInstance(calculate1_questionType, radio_questionNum, radio_questionAll,
                    calculate1_questionTitle1, calculate1_questionTitle2, calculate1_rightAnswer, radio_questionExplain, i);
            mFragments.add(mFragmentFour);
        }
        for (int i = 0; i < 3; i++) {
            mFragmentFive = Page29ChapterTestFiveFragment.newInstance(calculate2_questionType, radio_questionNum, radio_questionAll,
                    calculate2_questionTitle1, calculate2_questionTitle2, calculate2_rightAnswer, radio_questionExplain, i);
            mFragments.add(mFragmentFive);
        }
        for (int i = 0; i < 3; i++) {
            mFragmentSix = Page29ChapterTestSixFragment.newInstance(analyse_questionType, radio_questionNum, radio_questionAll,
                    analyse_questionTitle1, analyse_questionTitle2, radio_answerA, radio_answerB, radio_answerC, radio_answerD,
                    check_rightAnswer, radio_questionExplain, i);
            mFragments.add(mFragmentSix);
        }

        FragmentManager manager = getSupportFragmentManager();

        mAdapter = new Page29TestViewPagerAdapter(manager, this, mFragments);

        init();
    }

    //视图操作
    private void init() {

        String title = getIntent().getExtras().getString("title");
        mTextView1.setText(title);

        mChronometer.start();

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
        number = mViewPager.getCurrentItem();
        Toast.makeText(this, "number= " + number, Toast.LENGTH_SHORT).show();

        mBack.setOnClickListener(this);
        mCard.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mPre.setOnClickListener(this);
        mNext.setOnClickListener(this);

    }


    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page29_back:
                finish();
                break;
            case R.id.ks_page29_card:
                //Toast.makeText(this, "进入答题卡页面", Toast.LENGTH_SHORT).show();
                //Todo
                startActivity(new Intent(Page29ChapterTestActivity.this, Page30ChapterTestCardActivity.class));
                break;
            case R.id.ks_page29_collect:
                if (TAG) {   //收藏
                    mImageView1.setImageResource(R.drawable.ico_collect_on);
                    Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                    TAG = false;
                } else {   //取消收藏
                    mImageView1.setImageResource(R.drawable.ico_collect_off);
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                    TAG = true;
                }
                break;
            case R.id.ks_page29_leftArrow:
                if (number <= 0) {
                    Toast.makeText(this, "当前是第一题", Toast.LENGTH_SHORT).show();
                } else {
                    number = number - 1;
                    mViewPager.setCurrentItem(number);
                }
                if (number < 1) {
                    mPre.setImageResource(R.drawable.ico_left_arrow_gray);
                }
                mNext.setImageResource(R.drawable.ico_right_arrow_blue);
                break;
            case R.id.ks_page29_rightArrow:
                if (number >= mFragments.size() - 1) {
                    Toast.makeText(this, "当前是最后一题", Toast.LENGTH_SHORT).show();
                } else {
                    number = number + 1;
                    mViewPager.setCurrentItem(number);
                }
                if (number > mFragments.size() - 2) {
                    mNext.setImageResource(R.drawable.ico_right_arrow_gray);
                }
                mPre.setImageResource(R.drawable.ico_left_arrow_blue);
                break;
        }
    }

    //ViewPager的change适配器
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        number = mViewPager.getCurrentItem();
        if (number == 0) {
            mPre.setImageResource(R.drawable.ico_left_arrow_gray);
        } else {
            mPre.setImageResource(R.drawable.ico_left_arrow_blue);
        }
        if (number == mFragments.size() - 1) {
            mNext.setImageResource(R.drawable.ico_right_arrow_gray);
        } else {
            mNext.setImageResource(R.drawable.ico_right_arrow_blue);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
     /*   Log.e("TAG", "state" + state);
        if (number == 0) {
            if (state == 0 && a == 0 && b == 0.00) {
                Toast.makeText(this, "测试不能在往前滑动", Toast.LENGTH_SHORT).show();
            }
        }*/

    }
}
