package com.example.wdks.newwdksapp.pages;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.DisplayUtil;
import com.example.wdks.newwdksapp.tools.MyPopupWindow1Page29;
import com.example.wdks.newwdksapp.tools.MyPopupWindow2Page29;
import com.example.wdks.newwdksapp.tools.VerticalDrawerLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算分析题（二）页面
 */
public class Page29ChapterTestFiveFragment extends Fragment implements View.OnClickListener {

    private View view, mPush, mSpinner1, mSpinner2, mLine2, mResolve;
    private ImageView mArrow;
    private VerticalDrawerLayout mDrawer;
    private TextView mHead1, mHead2, mAnswer1, mAnswer2, mAnswer3, mExplain, mQuestionType, mQuestionNum, mQuestionAll, mQuestionTitle1,
            mQuestionTitle2, mRightAnswer, mYourAnswer, mQuestionExplain;
    private ArrayList<String> questionType, questionNum, questionAll, questionTitle1, questionTitle2, rightAnswer, questionExplain;
    private int flag;
    private MyPopupWindow1Page29 mPopupWindow1;
    private MyPopupWindow2Page29 mPopupWindow2;
    private EditText mEditText1, mEditText2;
    private Button mButton1, mButton2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

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
            view = inflater.inflate(R.layout.page29_chapter_test_five, container, false);
            initView();
        }
        return view;
    }

    //初始化视图
    private void initView() {
        mPush = view.findViewById(R.id.ks_page29_test_five_push);   //抽屉布局的操作
        mArrow = (ImageView) view.findViewById(R.id.ks_page29_test_five_iv1);   //箭头
        mDrawer = (VerticalDrawerLayout) view.findViewById(R.id.ks_page29_test_five_vertical);   //抽屉

        mSpinner1 = view.findViewById(R.id.ks_page29_test_five_content_spinner1);   //点击出现第一个下拉列表框
        mHead1 = (TextView) view.findViewById(R.id.ks_page29_test_five_content_spinner1_tv);   //显示数据

        mSpinner2 = view.findViewById(R.id.ks_page29_test_five_content_spinner2);   //点击出现第二个下拉列表框
        mHead2 = (TextView) view.findViewById(R.id.ks_page29_test_five_content_spinner2_tv);   //显示数据

        mEditText1 = (EditText) view.findViewById(R.id.ks_page29_test_five_content_et1);   //输入数字
        mButton1 = (Button) view.findViewById(R.id.ks_page29_test_five_content_btn1);   //确定答案按钮
        mAnswer1 = (TextView) view.findViewById(R.id.ks_page29_test_five_content_tv6);   //第一个答案
        mAnswer2 = (TextView) view.findViewById(R.id.ks_page29_test_five_content_tv7);   //第一个答案
        mAnswer3 = (TextView) view.findViewById(R.id.ks_page29_test_five_content_tv8);   //第一个答案
        mLine2 = view.findViewById(R.id.ks_page29_test_five_content_line2);   //显示我的答案

        mExplain = (TextView) view.findViewById(R.id.ks_page29_test_five_content_watch);   //查看解析
        mResolve = view.findViewById(R.id.ks_page29_test_five_content_resolve);   //显示答案解析

        //页面内容操作
        mQuestionType = (TextView) view.findViewById(R.id.ks_page29_test_five_content_tv1);   //题目类型
        mQuestionAll = (TextView) view.findViewById(R.id.ks_page29_test_five_content_tv2);   //总共有多少题
        mQuestionNum = (TextView) view.findViewById(R.id.ks_page29_test_five_content_tv3);   //当前是第几题
        mQuestionTitle1 = (TextView) view.findViewById(R.id.ks_page29_test_five_content_tv4);   //题干1
        mQuestionTitle2 = (TextView) view.findViewById(R.id.ks_page29_test_five_content_tv5);   //题干2
        mRightAnswer = (TextView) view.findViewById(R.id.ks_page29_test_five_content_rightAnswer);   //正确答案
        mYourAnswer = (TextView) view.findViewById(R.id.ks_page29_test_five_content_yourAnswer);   //你的答案
        mQuestionExplain = (TextView) view.findViewById(R.id.ks_page29_test_content_five_questionExplain);   //答案解析

        mEditText2 = (EditText) view.findViewById(R.id.ks_page29_test_five_content_et2);   //我的答疑
        mButton2 = (Button) view.findViewById(R.id.ks_page29_test_five_content_btn2);   //提交答疑

        init();
    }

    private void init() {
        mQuestionType.setText(questionType.get(flag));
        mQuestionAll.setText(questionAll.get(flag));
        mQuestionNum.setText(questionNum.get(flag));
        mQuestionTitle1.setText(questionTitle1.get(flag));
        mQuestionTitle2.setText(questionTitle2.get(flag));
        mRightAnswer.setText(rightAnswer.get(flag));
        mQuestionExplain.setText(questionExplain.get(flag));
        mYourAnswer.setText(mAnswer1.getText().toString() + mAnswer2.getText().toString() + mAnswer3.getText().toString());

        mPush.setOnClickListener(this);

        mDrawer.setDrawerListener(new VerticalDrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mArrow.setRotation(slideOffset * 180);
            }
        });

        mSpinner1.setOnClickListener(this);
        mSpinner2.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mExplain.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    //定义自己的fragment构造方法
    public static Page29ChapterTestFiveFragment newInstance(List<String> questionType, List<String> questionNum, List<String> questionAll,
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

        Page29ChapterTestFiveFragment page29TestFiveFragment = new Page29ChapterTestFiveFragment();
        page29TestFiveFragment.setArguments(bundle);

        return page29TestFiveFragment;
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page29_test_five_push:   //出现抽屉
                if (mDrawer.isDrawerOpen()) {
                    mDrawer.closeDrawer();
                } else {
                    mDrawer.openDrawerView();
                }
                break;
            case R.id.ks_page29_test_five_content_spinner1:   //出现下拉列表框
                if (mPopupWindow1 == null) {
                    ConsultClickListener1 paramOnItemClickListener = new ConsultClickListener1();
                    mPopupWindow1 = new MyPopupWindow1Page29(getActivity(), paramOnItemClickListener, DisplayUtil.dip2px(getActivity(), 59),
                            DisplayUtil.dip2px(getActivity(), 70));
                    //监听窗口的焦点事件，点击窗口外面则取消显示
                    mPopupWindow1.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                mPopupWindow1.dismiss();
                            }
                        }
                    });
                }
                //设置默认获取焦点
                mPopupWindow1.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                mPopupWindow1.showAsDropDown(mSpinner1, 0, 0);
                //如果窗口存在，则更新
                mPopupWindow1.update();
                break;
            case R.id.ks_page29_test_five_content_spinner2:   //出现第二个下拉列表框
                if (mPopupWindow2 == null) {
                    ConsultClickListener2 paramOnItemClickListener = new ConsultClickListener2();
                    mPopupWindow2 = new MyPopupWindow2Page29(getActivity(), paramOnItemClickListener, DisplayUtil.dip2px(getActivity(), 189),
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    //监听窗口的焦点事件，点击窗口外面则取消显示
                    mPopupWindow2.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                mPopupWindow2.dismiss();
                            }
                        }
                    });
                }
                //设置默认获取焦点
                mPopupWindow2.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                mPopupWindow2.showAsDropDown(mSpinner2, 0, 0);
                //如果窗口存在，则更新
                mPopupWindow2.update();
                break;
            case R.id.ks_page29_test_five_content_btn1:  //确定
                if (mEditText1.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "请将答案填写完整", Toast.LENGTH_SHORT).show();
                } else {
                    mAnswer1.setText(mHead1.getText().toString());
                    mAnswer2.setText(mHead2.getText().toString());
                    mAnswer3.setText(mEditText1.getText().toString());
                    mLine2.setVisibility(View.VISIBLE);

                    String yourAnswer = mAnswer1.getText().toString() + mAnswer2.getText().toString() + mAnswer3.getText().toString();
                    mYourAnswer.setText(yourAnswer);   //我的答案

                    //根据对错，调整你的答案的颜色
                    if (mRightAnswer.getText().toString().equals(yourAnswer)) {
                        mYourAnswer.setTextColor(getResources().getColor(R.color.green));

                    } else {
                        mYourAnswer.setTextColor(getResources().getColor(R.color.red));
                    }

                    //提交答案之后 让输入框失去焦点，让按钮不能再点击
                    mEditText1.setFocusable(false);
                    mButton1.setClickable(false);
                }
                break;
            case R.id.ks_page29_test_five_content_watch:   //查看解析
                mResolve.setVisibility(View.VISIBLE);
                break;
            case R.id.ks_page29_test_five_content_btn2:   //提交答疑
                if (mEditText2.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "请填写内容", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "我的答疑的内容是：" + mEditText2.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //第一个下拉列表的点击事件
    public class ConsultClickListener1 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView tv = (TextView) view.findViewById(R.id.ks_page29_test_five_spinner1_listView_tv);
            String s = tv.getText().toString();
            mHead1.setText(s);
        }
    }

    //第二个下拉列表的点击事件
    public class ConsultClickListener2 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView tv = (TextView) view.findViewById(R.id.ks_page29_test_five_spinner2_listView_tv);
            String s = tv.getText().toString();
            mHead2.setText(s);
        }
    }

}
