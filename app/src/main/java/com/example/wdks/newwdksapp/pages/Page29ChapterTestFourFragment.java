package com.example.wdks.newwdksapp.pages;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.VerticalDrawerLayout;

import java.util.ArrayList;
import java.util.List;

/*
* 计算分析题（一）页面
* */
public class Page29ChapterTestFourFragment extends Fragment implements View.OnClickListener {
    private View view, mPush, mResolve;
    private VerticalDrawerLayout mDrawerLayout;
    private ImageView mArrow;
    private EditText mEditText1, mEditText2;
    private Button mButton1, mButton2;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6, mTextView7,
            mTextView8, mTextView9;
    private ArrayList<String> questionType, questionNum, questionAll, questionTitle1, questionTitle2,
            rightAnswer, questionExplain;
    private int flag;

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
        rightAnswer = new ArrayList<String>();
        questionExplain = new ArrayList<String>();

        Bundle bundle = getArguments();
        if (bundle != null) {
            questionType = bundle.getStringArrayList("questionType");
            questionAll = bundle.getStringArrayList("questionAll");
            questionNum = bundle.getStringArrayList("questionNum");
            questionTitle1 = bundle.getStringArrayList("questionTitle1");
            questionTitle2 = bundle.getStringArrayList("questionTitle2");
            rightAnswer = bundle.getStringArrayList("rightAnswer");
            questionExplain = bundle.getStringArrayList("questionExplain");

            flag = bundle.getInt("flag");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.page29_chapter_test_four, container, false);
            initView();
        }
        return view;
    }

    //初始化视图
    private void initView() {
        mDrawerLayout = (VerticalDrawerLayout) view.findViewById(R.id.ks_page29_test_four_vertical);   //总的抽屉布局
        mArrow = (ImageView) view.findViewById(R.id.ks_page29_test_four_iv1);   //下拉箭头
        mPush = view.findViewById(R.id.ks_page29_test_four_push);   //总体下拉界面

        //主要内容
        mTextView1 = (TextView) view.findViewById(R.id.ks_page29_test_four_content_tv1);   //题目类型
        mTextView2 = (TextView) view.findViewById(R.id.ks_page29_test_four_content_tv2);   //总的题目
        mTextView3 = (TextView) view.findViewById(R.id.ks_page29_test_four_content_tv3);   //当前题目
        mTextView4 = (TextView) view.findViewById(R.id.ks_page29_test_four_content_tv4);   //题目要求
        mTextView5 = (TextView) view.findViewById(R.id.ks_page29_test_four_content_tv5);   //问题题干

        mEditText1 = (EditText) view.findViewById(R.id.ks_page29_test_four_content_et1);   //输入框
        mButton1 = (Button) view.findViewById(R.id.ks_page29_test_four_content_btn1);   //提交答案

        mResolve = view.findViewById(R.id.ks_page29_test_four_content_resolve);   //答案的显示和隐藏

        mTextView6 = (TextView) view.findViewById(R.id.ks_page29_test_four_content_tv6);   //正确答案
        mTextView7 = (TextView) view.findViewById(R.id.ks_page29_test_four_content_tv7);   //你的答案
        mTextView8 = (TextView) view.findViewById(R.id.ks_page29_test_four_content_tv8);   //答案解析

        mEditText2 = (EditText) view.findViewById(R.id.ks_page29_test_four_content_et2);   //提问内容
        mButton2 = (Button) view.findViewById(R.id.ks_page29_test_four_content_btn2);   //提交我的提问
        mTextView9 = (TextView) view.findViewById(R.id.ks_page29_test_four_content_tv9);  //编辑笔记

        init();
    }

    //视图操作
    private void init() {

        mTextView1.setText(questionType.get(flag));
        mTextView2.setText(questionAll.get(flag));
        mTextView3.setText(questionNum.get(flag));
        mTextView4.setText(questionTitle1.get(flag));
        mTextView5.setText(questionTitle2.get(flag));
        mTextView6.setText(rightAnswer.get(flag));
        mTextView8.setText(questionExplain.get(flag));

        mPush.setOnClickListener(this);

        //动态改变下拉箭头的方向
        mDrawerLayout.setDrawerListener(new VerticalDrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mArrow.setRotation(slideOffset * 180);
            }
        });

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mTextView9.setOnClickListener(this);
    }

    //定义自己的fragment构造方法
    public static Page29ChapterTestFourFragment newInstance(List<String> questionType, List<String> questionNum, List<String> questionAll,
                                                            List<String> questionTitle1, List<String> questionTitle2, List<String> rightAnswer,
                                                            List<String> questionExplain, int flag) {

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("questionType", (ArrayList<String>) questionType);
        bundle.putStringArrayList("questionNum", (ArrayList<String>) questionNum);
        bundle.putStringArrayList("questionAll", (ArrayList<String>) questionAll);
        bundle.putStringArrayList("questionTitle1", (ArrayList<String>) questionTitle1);
        bundle.putStringArrayList("questionTitle2", (ArrayList<String>) questionTitle2);
        bundle.putStringArrayList("rightAnswer", (ArrayList<String>) rightAnswer);
        bundle.putStringArrayList("questionExplain", (ArrayList<String>) questionExplain);

        bundle.putInt("flag", flag);

        Page29ChapterTestFourFragment page29TestFourFragment = new Page29ChapterTestFourFragment();
        page29TestFourFragment.setArguments(bundle);

        return page29TestFourFragment;
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page29_test_four_push:   //点击打开和关闭抽屉布局
                if (mDrawerLayout.isDrawerOpen()) {
                    mDrawerLayout.closeDrawer();
                } else {
                    mDrawerLayout.openDrawerView();
                }
                break;
            case R.id.ks_page29_test_four_content_btn1:   //提交答案

                String s = mEditText1.getText().toString();
                if (s.equals("")) {
                    Toast.makeText(getActivity(), "请输入答案", Toast.LENGTH_SHORT).show();
                } else {
                    mTextView7.setText(s);

                    if (mTextView6.getText().toString().equals(s)) {
                        mTextView7.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        mTextView7.setTextColor(getResources().getColor(R.color.red));
                    }
                    mResolve.setVisibility(View.VISIBLE);
                    mEditText1.setFocusable(false);   //失去焦点
                    mButton1.setClickable(false);   //取消点击事件
                }
                break;
            case R.id.ks_page29_test_four_content_btn2:   //提问
                if (mEditText2.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "我提问的内容是" + mEditText2.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ks_page29_test_four_content_tv9:   //编辑笔记
                Toast.makeText(getActivity(), "编辑笔记", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
