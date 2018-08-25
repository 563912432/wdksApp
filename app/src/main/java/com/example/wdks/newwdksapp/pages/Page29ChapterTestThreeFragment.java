package com.example.wdks.newwdksapp.pages;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;

import java.util.ArrayList;
import java.util.List;

/*
* 判断页面
* */
public class Page29ChapterTestThreeFragment extends Fragment implements View.OnClickListener {
    private View view, mResolve;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6,
            mTextView7, mTextView8, mWatch;
    private RadioGroup mRadioGroup1;
    private RadioButton mRB1, mRB2, mRBChecked;
    private ArrayList<String> questionType, questionNum, questionAll, questionTitle, answerA, answerB,
            rightAnswer, questionExplain;
    private int flag;
    private String s;
    private EditText mEditText1;
    private Button mButton1;

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
            rightAnswer = bundle.getStringArrayList("rightAnswer");
            questionExplain = bundle.getStringArrayList("questionExplain");

            flag = bundle.getInt("flag");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.page29_chapter_test_three, container, false);
            initView();
        }
        return view;
    }

    //初始化视图
    private void initView() {
        mTextView1 = (TextView) view.findViewById(R.id.ks_page29_test_three_tv1);   //题目类型
        mTextView2 = (TextView) view.findViewById(R.id.ks_page29_test_three_tv2);   //题目总数
        mTextView3 = (TextView) view.findViewById(R.id.ks_page29_test_three_tv3);   //题号
        mTextView4 = (TextView) view.findViewById(R.id.ks_page29_test_three_tv4);   //题干
        mRB1 = (RadioButton) view.findViewById(R.id.ks_page29_test_three_rb1);   //正确
        mRB2 = (RadioButton) view.findViewById(R.id.ks_page29_test_three_rb2);   //错误
        mTextView5 = (TextView) view.findViewById(R.id.ks_page29_test_three_tv5);   //正确答案
        mTextView6 = (TextView) view.findViewById(R.id.ks_page29_test_three_tv6);   //你的答案
        mTextView7 = (TextView) view.findViewById(R.id.ks_page29_test_three_tv7);   //答案解析
        mTextView8 = (TextView) view.findViewById(R.id.ks_page29_test_three_tv8);   //编辑笔记

        mWatch = (TextView) view.findViewById(R.id.ks_page29_three_watch);   //查看解析
        mResolve = view.findViewById(R.id.ks_page29_test_three_resolve);   //答案解析的内容

        mRadioGroup1 = (RadioGroup) view.findViewById(R.id.ks_page29_test_three_radioGroup);   //radioGroup

        mEditText1 = (EditText) view.findViewById(R.id.ks_page29_test_three_et);   //我的答疑内容
        mButton1 = (Button) view.findViewById(R.id.ks_page29_test_three_btn);   //提交提问

        init();
    }

    //视图操作
    private void init() {
        mTextView1.setText(questionType.get(flag));
        mTextView2.setText(questionAll.get(flag));
        mTextView3.setText(questionNum.get(flag));
        mTextView4.setText(questionTitle.get(flag));
        mRB1.setText(answerA.get(flag));
        mRB2.setText(answerB.get(flag));
        mTextView5.setText(rightAnswer.get(flag));
        mTextView7.setText(questionExplain.get(flag));

        //获取RadioButton选中的值
        mRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectRadioBtn();
            }
        });

        mTextView8.setOnClickListener(this);
        mWatch.setOnClickListener(this);
        mButton1.setOnClickListener(this);
    }

    private void selectRadioBtn() {
        mRBChecked = (RadioButton) view.findViewById(mRadioGroup1.getCheckedRadioButtonId());
        s = mRBChecked.getText().toString();   //取参
    }


    //自己定义fragment的构造方法
    public static Page29ChapterTestThreeFragment newInstance(List<String> questionType, List<String> questionNum, List<String> questionAll,
                                                             List<String> questionTitle, List<String> answerA, List<String> answerB,
                                                             List<String> rightAnswer, List<String> questionExplain, int flag) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("questionType", (ArrayList<String>) questionType);
        bundle.putStringArrayList("questionNum", (ArrayList<String>) questionNum);
        bundle.putStringArrayList("questionAll", (ArrayList<String>) questionAll);
        bundle.putStringArrayList("questionTitle", (ArrayList<String>) questionTitle);
        bundle.putStringArrayList("answerA", (ArrayList<String>) answerA);
        bundle.putStringArrayList("answerB", (ArrayList<String>) answerB);
        bundle.putStringArrayList("rightAnswer", (ArrayList<String>) rightAnswer);
        bundle.putStringArrayList("questionExplain", (ArrayList<String>) questionExplain);

        bundle.putInt("flag", flag);

        Page29ChapterTestThreeFragment page29TestThreeFragment = new Page29ChapterTestThreeFragment();
        page29TestThreeFragment.setArguments(bundle);

        return page29TestThreeFragment;
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page29_test_three_tv8:
                Toast.makeText(getActivity(), "编辑笔记" + flag, Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page29_three_watch:   //查看解析
                mTextView6.setText(s);
                if (mTextView6.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "请选择答案", Toast.LENGTH_SHORT).show();
                } else {
                    mResolve.setVisibility(View.VISIBLE);

                    if (mTextView5.getText().toString().equals(s)) {
                        mTextView6.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        mTextView6.setTextColor(getResources().getColor(R.color.red));
                    }
                    mWatch.setClickable(false);
                    mRB1.setClickable(false);
                    mRB2.setClickable(false);
                }
                break;
            case R.id.ks_page29_test_three_btn:   //提交我的提问
                if (mEditText1.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "我的提问的内容是" + mEditText1.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
