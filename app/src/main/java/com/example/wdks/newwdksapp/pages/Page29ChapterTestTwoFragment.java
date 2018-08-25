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
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;

import java.util.ArrayList;
import java.util.List;

/*
* 多选页面
* */
public class Page29ChapterTestTwoFragment extends Fragment implements View.OnClickListener {
    private View view, mShowAnswer, mShowExplain;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6,
            mTextView7, mTextView8, mWatch;
    private CheckBox mCB1, mCB2, mCB3, mCB4, mCB5;
    private Button mButton1, mButton2;
    private EditText mEditText1;
    private ArrayList<String> questionType, questionNum, questionAll, questionTitle, answerA, answerB, answerC, answerD,
            answerE, rightAnswer, questionExplain;
    private int flag;
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
        questionTitle = new ArrayList<String>();
        answerA = new ArrayList<String>();
        answerB = new ArrayList<String>();
        answerC = new ArrayList<String>();
        answerD = new ArrayList<String>();
        answerE = new ArrayList<String>();
        rightAnswer = new ArrayList<String>();
        questionExplain = new ArrayList<String>();

        Bundle bundle = getArguments();
        if (bundle != null) {
            questionType = bundle.getStringArrayList("questionType");
            questionAll = bundle.getStringArrayList("questionAll");
            questionNum = bundle.getStringArrayList("questionNum");
            questionTitle = bundle.getStringArrayList("questionTitle");
            answerA = bundle.getStringArrayList("answerA");
            answerB = bundle.getStringArrayList("answerB");
            answerC = bundle.getStringArrayList("answerC");
            answerD = bundle.getStringArrayList("answerD");
            answerE = bundle.getStringArrayList("answerE");
            rightAnswer = bundle.getStringArrayList("rightAnswer");
            questionExplain = bundle.getStringArrayList("questionExplain");

            flag = bundle.getInt("flag");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page29_chapter_test_two, container, false);
            initView();
        }
        return view;
    }

    //初始化视图
    private void initView() {
        mTextView1 = (TextView) view.findViewById(R.id.ks_page29_test_two_tv1);   //题目类型
        mTextView2 = (TextView) view.findViewById(R.id.ks_page29_test_two_tv2);   //题目总数
        mTextView3 = (TextView) view.findViewById(R.id.ks_page29_test_two_tv3);   //题号
        mTextView4 = (TextView) view.findViewById(R.id.ks_page29_test_two_tv4);   //题干
        mCB1 = (CheckBox) view.findViewById(R.id.ks_page29_test_two_cb1);   //选项A
        mCB2 = (CheckBox) view.findViewById(R.id.ks_page29_test_two_cb2);    //选项B
        mCB3 = (CheckBox) view.findViewById(R.id.ks_page29_test_two_cb3);    //选项C
        mCB4 = (CheckBox) view.findViewById(R.id.ks_page29_test_two_cb4);    //选项D
        mCB5 = (CheckBox) view.findViewById(R.id.ks_page29_test_two_cb5);    //选项E
        mTextView5 = (TextView) view.findViewById(R.id.ks_page29_test_two_tv5);   //正确答案
        mTextView6 = (TextView) view.findViewById(R.id.ks_page29_test_two_tv6);   //你的答案
        mTextView7 = (TextView) view.findViewById(R.id.ks_page29_test_two_tv7);   //答案解析
        mTextView8 = (TextView) view.findViewById(R.id.ks_page29_test_two_tv8);   //编辑笔记

        mButton1 = (Button) view.findViewById(R.id.ks_page29_test_two_btn1);   //提交答案
        mShowAnswer = view.findViewById(R.id.ks_page29_test_two_showAnswer);   //查看答案的内容
        mWatch = (TextView) view.findViewById(R.id.ks_page29_two_watch);   //查看解析
        mShowExplain = view.findViewById(R.id.ks_page29_test_two_showExplain);  //解析的内容
        mEditText1 = (EditText) view.findViewById(R.id.ks_page29_test_two_et);   //答疑内容
        mButton2 = (Button) view.findViewById(R.id.ks_page29_test_two_btn2);   //提交答疑

        init();
    }

    //视图操作
    private void init() {
        mTextView1.setText(questionType.get(flag));
        mTextView2.setText(questionAll.get(flag));
        mTextView3.setText(questionNum.get(flag));
        mTextView4.setText(questionTitle.get(flag));
        mCB1.setText(answerA.get(flag));
        mCB2.setText(answerB.get(flag));
        mCB3.setText(answerC.get(flag));
        mCB4.setText(answerD.get(flag));
        mCB5.setText(answerE.get(flag));
        mTextView5.setText(rightAnswer.get(flag));
        mTextView7.setText(questionExplain.get(flag));

        mButton1.setOnClickListener(this);
        mWatch.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mTextView8.setOnClickListener(this);
    }

    //自己定义fragment的构造方法
    public static Page29ChapterTestTwoFragment newInstance(List<String> questionType, List<String> questionNum, List<String> questionAll,
                                                           List<String> questionTitle, List<String> answerA, List<String> answerB,
                                                           List<String> answerC, List<String> answerD, List<String> answerE, List<String> rightAnswer,
                                                           List<String> questionExplain, int flag) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("questionType", (ArrayList<String>) questionType);
        bundle.putStringArrayList("questionNum", (ArrayList<String>) questionNum);
        bundle.putStringArrayList("questionAll", (ArrayList<String>) questionAll);
        bundle.putStringArrayList("questionTitle", (ArrayList<String>) questionTitle);
        bundle.putStringArrayList("answerA", (ArrayList<String>) answerA);
        bundle.putStringArrayList("answerB", (ArrayList<String>) answerB);
        bundle.putStringArrayList("answerC", (ArrayList<String>) answerC);
        bundle.putStringArrayList("answerD", (ArrayList<String>) answerD);
        bundle.putStringArrayList("answerE", (ArrayList<String>) answerE);
        bundle.putStringArrayList("rightAnswer", (ArrayList<String>) rightAnswer);
        bundle.putStringArrayList("questionExplain", (ArrayList<String>) questionExplain);

        bundle.putInt("flag", flag);

        Page29ChapterTestTwoFragment page29TestTwoFragment = new Page29ChapterTestTwoFragment();
        page29TestTwoFragment.setArguments(bundle);

        return page29TestTwoFragment;
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page29_test_two_btn1:   //提交答案
                if (mCB1.isChecked()) {
                    string_Answer = string_Answer + "A";
                }
                if (mCB2.isChecked()) {
                    string_Answer = string_Answer + "B";
                }
                if (mCB3.isChecked()) {
                    string_Answer = string_Answer + "C";
                }
                if (mCB4.isChecked()) {
                    string_Answer = string_Answer + "D";
                }
                if (mCB5.isChecked()) {
                    string_Answer = string_Answer + "E";
                }

                if (string_Answer.equals("")) {
                    Toast.makeText(getActivity(), "请选择选项", Toast.LENGTH_SHORT).show();
                } else {
                    mShowAnswer.setVisibility(View.VISIBLE);
                    mTextView6.setText(string_Answer);

                    if (mTextView5.getText().toString().equals(string_Answer)) {
                        mTextView6.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        mTextView6.setTextColor(getResources().getColor(R.color.red));
                    }
                    mButton1.setClickable(false);
                    mCB1.setClickable(false);
                    mCB2.setClickable(false);
                    mCB3.setClickable(false);
                    mCB4.setClickable(false);
                    mCB5.setClickable(false);
                }
                break;
            case R.id.ks_page29_two_watch:
                mShowExplain.setVisibility(View.VISIBLE);
                break;
            case R.id.ks_page29_test_two_btn2:
                if (mEditText1.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "我的提问内容是：" + mEditText1.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ks_page29_test_two_tv8:
                Toast.makeText(getActivity(), "编辑笔记" + flag, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
