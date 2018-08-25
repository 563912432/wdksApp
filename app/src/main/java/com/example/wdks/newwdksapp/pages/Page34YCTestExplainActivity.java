package com.example.wdks.newwdksapp.pages;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page34YCTestExplainViewPagerAdapter;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/*
* 预测试卷的答案解析的界面
* */
public class Page34YCTestExplainActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack, mCard, mCollect, mShare;
    private TextView mTitle;
    private Boolean TAG = true;
    private ImageView mImageView1, mLeftArrow, mRightArrow;
    private ViewPager mViewPager;
    private Page34YCTestExplainViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mFragments;
    private Page34YCTestExplainOneFragment mFragmentOne;
    private List<String> radio_questionType, radio_questionNum, radio_questionAll, radio_questionTitle, radio_answerA,
            radio_answerB, radio_answerC, radio_answerD, radio_rightAnswer, radio_your_answer, radio_questionExplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyInitWindow.initWindow(this);
        setContentView(R.layout.page34_yc_test_explain);
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
        mBack = findViewById(R.id.ks_page34_back);   //返回按钮
        mCard = findViewById(R.id.ks_page34_card);   //答题卡
        mCollect = findViewById(R.id.ks_page34_collect);   //收藏
        mShare = findViewById(R.id.ks_page34_share);   //分享
        mTitle = (TextView) findViewById(R.id.ks_page34_tv1);   //标题
        mImageView1 = (ImageView) findViewById(R.id.ks_page34_iv1);   //收藏按钮的图片
        mLeftArrow = (ImageView) findViewById(R.id.ks_page34_leftArrow);   //上一题
        mRightArrow = (ImageView) findViewById(R.id.ks_page34_rightArrow);   //下一题
        mViewPager = (ViewPager) findViewById(R.id.ks_page34_viewPager);   //内容

        mFragments = new ArrayList<Fragment>();
        //Fragment的初始化
        //单选题
        for (int i = 0; i < 3; i++) {
            mFragmentOne = Page34YCTestExplainOneFragment.newInstance(radio_questionType, radio_questionNum, radio_questionAll,
                    radio_questionTitle, radio_answerA, radio_answerB, radio_answerC, radio_answerD,
                    radio_rightAnswer, radio_your_answer, radio_questionExplain, i);

            mFragments.add(mFragmentOne);
        }
        FragmentManager manager = getSupportFragmentManager();
        mViewPagerAdapter = new Page34YCTestExplainViewPagerAdapter(manager, this, mFragments);
        mViewPager.setAdapter(mViewPagerAdapter);

        init();
    }

    //视图操作
    private void init() {
        mTitle.setText("预测试卷");

        mBack.setOnClickListener(this);
        mCard.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mLeftArrow.setOnClickListener(this);
        mRightArrow.setOnClickListener(this);

    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page34_back:
                finish();
                break;
            case R.id.ks_page34_card:
                Toast.makeText(this, "进入错题解析的答题卡页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page34_collect:
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
            case R.id.ks_page34_leftArrow:   //上一题
                Toast.makeText(this, "上一题的操作", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page34_rightArrow:   //下一题
                Toast.makeText(this, "下一题的操作", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page33_share:   //分享
                Toast.makeText(this, "分享的操作", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
