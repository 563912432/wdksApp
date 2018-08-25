package com.example.wdks.newwdksapp.pages;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.VerticalDrawerLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 案例分析题页面
 */
public class Page29ChapterTestSixFragment extends Fragment implements View.OnClickListener {
    private View view, mPush, mShowAnswer, mShowExplain;
    private ImageView mArrow;
    private VerticalDrawerLayout mDrawer;
    private TextView mQuestionType, mQuestionNum, mQuestionAll, mQuestionTitle1, mQuestionTitle2, mRightAnswer, mYourAnswer, mQuestionExplain, mWatch;
    private ArrayList<String> questionType, questionNum, questionAll, questionTitle1, questionTitle2, answerA, answerB, answerC,
            answerD, rightAnswer, questionExplain;
    private CheckBox mAnswerA, mAnswerB, mAnswerC, mAnswerD;
    private int flag;
    private Button mConfirm, mSubmit;
    private EditText mEditText1;
    private String string_Answer = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    //初始化数据
    private void initData() {
        questionType = new ArrayList<String>();
        questionNum = new ArrayList<String>();
        questionAll = new ArrayList<String>();
        questionTitle1 = new ArrayList<String>();
        questionTitle2 = new ArrayList<String>();
        answerA = new ArrayList<String>();
        answerB = new ArrayList<String>();
        answerC = new ArrayList<String>();
        answerD = new ArrayList<String>();
        rightAnswer = new ArrayList<String>();
        questionExplain = new ArrayList<String>();

        Bundle bundle = getArguments();
        if (bundle != null) {
            questionType = bundle.getStringArrayList("questionType");
            questionAll = bundle.getStringArrayList("questionAll");
            questionNum = bundle.getStringArrayList("questionNum");
            questionTitle1 = bundle.getStringArrayList("questionTitle1");
            questionTitle2 = bundle.getStringArrayList("questionTitle2");
            answerA = bundle.getStringArrayList("answerA");
            answerB = bundle.getStringArrayList("answerB");
            answerC = bundle.getStringArrayList("answerC");
            answerD = bundle.getStringArrayList("answerD");
            rightAnswer = bundle.getStringArrayList("rightAnswer");
            questionExplain = bundle.getStringArrayList("questionExplain");

            flag = bundle.getInt("flag");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.page29_chapter_test_six, container, false);
            initView();
        }
        return view;
    }

    //初始化视图
    private void initView() {
        mPush = view.findViewById(R.id.ks_page29_test_six_push);   //出现和隐藏抽屉
        mArrow = (ImageView) view.findViewById(R.id.ks_page29_test_six_iv1);   //向下箭头
        mDrawer = (VerticalDrawerLayout) view.findViewById(R.id.ks_page29_test_six_verticalDrawer);   //向下抽屉

        //页面内容操作
        mQuestionType = (TextView) view.findViewById(R.id.ks_page29_test_six_content_tv1);   //题目类型
        mQuestionAll = (TextView) view.findViewById(R.id.ks_page29_test_six_content_tv2);   //总共有多少题
        mQuestionNum = (TextView) view.findViewById(R.id.ks_page29_test_six_content_tv3);   //当前是第几题
        mQuestionTitle1 = (TextView) view.findViewById(R.id.ks_page29_test_six_content_tv4);   //题干1
        mQuestionTitle2 = (TextView) view.findViewById(R.id.ks_page29_test_six_content_tv5);   //题干2
        mAnswerA = (CheckBox) view.findViewById(R.id.ks_page29_test_six_cb1);   //答案A
        mAnswerB = (CheckBox) view.findViewById(R.id.ks_page29_test_six_cb2);   //答案B
        mAnswerC = (CheckBox) view.findViewById(R.id.ks_page29_test_six_cb3);   //答案C
        mAnswerD = (CheckBox) view.findViewById(R.id.ks_page29_test_six_cb4);   //答案D
        mRightAnswer = (TextView) view.findViewById(R.id.ks_page29_test_six_content_rightAnswer);   //正确答案
        mYourAnswer = (TextView) view.findViewById(R.id.ks_page29_test_six_content_yourAnswer);   //你的答案
        mQuestionExplain = (TextView) view.findViewById(R.id.ks_page29_test_content_six_questionExplain);   //答案解析

        mConfirm = (Button) view.findViewById(R.id.ks_page29_test_six_content_btn1);   //提交答案
        mShowAnswer = view.findViewById(R.id.ks_page29_test_six_content_showAnswer);   //显示答案
        mWatch = (TextView) view.findViewById(R.id.ks_page29_test_six_content_watch);   //查看解析
        mShowExplain = view.findViewById(R.id.ks_page29_test_six_content_showExplain);   //显示解析

        mEditText1 = (EditText) view.findViewById(R.id.ks_page29_test_six_content_et1);   //我的答疑内容
        mSubmit = (Button) view.findViewById(R.id.ks_page29_test_six_content_btn2);   //提交我的答疑

        init();
    }

    //视图操作
    private void init() {
        mPush.setOnClickListener(this);
        mDrawer.setDrawerListener(new VerticalDrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mArrow.setRotation(slideOffset * 180);
            }
        });
        mQuestionType.setText(questionType.get(flag));
        mQuestionAll.setText(questionAll.get(flag));
        mQuestionNum.setText(questionNum.get(flag));
        mQuestionTitle1.setText(questionTitle1.get(flag));
        mQuestionTitle2.setText(questionTitle2.get(flag));
        mAnswerA.setText(answerA.get(flag));
        mAnswerB.setText(answerB.get(flag));
        mAnswerC.setText(answerC.get(flag));
        mAnswerD.setText(answerD.get(flag));
        mRightAnswer.setText(rightAnswer.get(flag));
        //todo 你的答案要自己获取 mYourAnswer
        mQuestionExplain.setText(questionExplain.get(flag));

        mConfirm.setOnClickListener(this);
        mWatch.setOnClickListener(this);
        mSubmit.setOnClickListener(this);

    }

    //自定义fragment的构造方法
    public static Page29ChapterTestSixFragment newInstance(List<String> questionType, List<String> questionNum, List<String> questionAll,
                                                           List<String> questionTitle1, List<String> questionTitle2, List<String> answerA,
                                                           List<String> answerB, List<String> answerC, List<String> answerD,
                                                           List<String> rightAnswer, List<String> questionExplain, int flag) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("questionType", (ArrayList<String>) questionType);
        bundle.putStringArrayList("questionNum", (ArrayList<String>) questionNum);
        bundle.putStringArrayList("questionAll", (ArrayList<String>) questionAll);
        bundle.putStringArrayList("questionTitle1", (ArrayList<String>) questionTitle1);
        bundle.putStringArrayList("questionTitle2", (ArrayList<String>) questionTitle2);
        bundle.putStringArrayList("answerA", (ArrayList<String>) answerA);
        bundle.putStringArrayList("answerB", (ArrayList<String>) answerB);
        bundle.putStringArrayList("answerC", (ArrayList<String>) answerC);
        bundle.putStringArrayList("answerD", (ArrayList<String>) answerD);
        bundle.putStringArrayList("rightAnswer", (ArrayList<String>) rightAnswer);
        bundle.putStringArrayList("questionExplain", (ArrayList<String>) questionExplain);

        bundle.putInt("flag", flag);
        Page29ChapterTestSixFragment page29TestSixFragment = new Page29ChapterTestSixFragment();
        page29TestSixFragment.setArguments(bundle);

        return page29TestSixFragment;
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page29_test_six_push:   //控制抽屉的显示与隐藏
                if (mDrawer.isDrawerOpen()) {
                    mDrawer.closeDrawer();
                } else {
                    mDrawer.openDrawerView();
                }
                break;
            case R.id.ks_page29_test_six_content_btn1:   //显示答案
                if (mAnswerA.isChecked()) {
                    string_Answer = string_Answer + "A";
                }
                if (mAnswerB.isChecked()) {
                    string_Answer = string_Answer + "B";
                }
                if (mAnswerC.isChecked()) {
                    string_Answer = string_Answer + "C";
                }
                if (mAnswerD.isChecked()) {
                    string_Answer = string_Answer + "D";
                }

                if (string_Answer.equals("")) {
                    Toast.makeText(getActivity(), "请选择选项", Toast.LENGTH_SHORT).show();
                } else {
                    mShowAnswer.setVisibility(View.VISIBLE);
                    mYourAnswer.setText(string_Answer);

                    if (mRightAnswer.getText().toString().equals(string_Answer)) {
                        mYourAnswer.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        mYourAnswer.setTextColor(getResources().getColor(R.color.red));
                    }

                    mConfirm.setClickable(false);
                    mAnswerA.setClickable(false);
                    mAnswerB.setClickable(false);
                    mAnswerC.setClickable(false);
                    mAnswerD.setClickable(false);
                }

                break;
            case R.id.ks_page29_test_six_content_watch:   //显示答案解析
                mShowExplain.setVisibility(View.VISIBLE);
                break;
            case R.id.ks_page29_test_six_content_btn2:   //提交我的答疑
                if (mEditText1.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "请填写内容", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "我的提问内容是：" + mEditText1.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
