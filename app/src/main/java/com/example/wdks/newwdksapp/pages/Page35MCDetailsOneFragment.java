package com.example.wdks.newwdksapp.pages;

import android.graphics.drawable.Drawable;
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
* 我的收藏的单选页面
* */
public class Page35MCDetailsOneFragment extends Fragment implements View.OnClickListener {

    private View view, mResolve;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6,
            mTextView7, mTextView8, mWatch;
    private RadioGroup mRadioGroup;
    private RadioButton mRB1, mRB2, mRB3, mRB4;
    private ArrayList<String> questionType, questionNum, questionAll, questionTitle, answerA, answerB, answerC, answerD,
            rightAnswer, yourAnswer, questionExplain;
    private String s = "";
    private int flag;
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
        answerC = new ArrayList<String>();
        answerD = new ArrayList<String>();
        rightAnswer = new ArrayList<String>();
        yourAnswer = new ArrayList<String>();
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
            rightAnswer = bundle.getStringArrayList("rightAnswer");
            yourAnswer = bundle.getStringArrayList("yourAnswer");
            questionExplain = bundle.getStringArrayList("questionExplain");

            flag = bundle.getInt("flag");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page35_mc_details_one, container, false);
            initView();
        }
        return view;
    }

    //初始化视图
    private void initView() {
        mTextView1 = (TextView) view.findViewById(R.id.ks_page35_mc_details_one_tv1);   //题目类型
        mTextView2 = (TextView) view.findViewById(R.id.ks_page35_mc_details_one_tv2);   //题目总数
        mTextView3 = (TextView) view.findViewById(R.id.ks_page35_mc_details_one_tv3);   //题号
        mTextView4 = (TextView) view.findViewById(R.id.ks_page35_mc_details_one_tv4);   //题干
        mRadioGroup = (RadioGroup) view.findViewById(R.id.ks_page35_mc_details_one_radioGroup);   //radioGroup
        mRB1 = (RadioButton) view.findViewById(R.id.ks_page35_mc_details_one_rb1);   //选项A
        mRB2 = (RadioButton) view.findViewById(R.id.ks_page35_mc_details_one_rb2);   //选项B
        mRB3 = (RadioButton) view.findViewById(R.id.ks_page35_mc_details_one_rb3);   //选项C
        mRB4 = (RadioButton) view.findViewById(R.id.ks_page35_mc_details_one_rb4);   //选项D
        mTextView5 = (TextView) view.findViewById(R.id.ks_page35_mc_details_one_tv5);   //正确答案
        mTextView6 = (TextView) view.findViewById(R.id.ks_page35_mc_details_one_tv6);   //你的答案
        mTextView7 = (TextView) view.findViewById(R.id.ks_page35_mc_details_one_tv7);   //答案解析
        mTextView8 = (TextView) view.findViewById(R.id.ks_page35_mc_details_one_tv8);   //编辑笔记

        //mWatch = (TextView) view.findViewById(R.id.ks_page35_mc_details_one_watch);   //查看解析
        mResolve = view.findViewById(R.id.ks_page35_mc_details_one_resolve);   //答案解析的内容
        mEditText1 = (EditText) view.findViewById(R.id.ks_page35_mc_details_one_et);   //提问内容
        mButton1 = (Button) view.findViewById(R.id.ks_page35_mc_details_one_btn1);   //提交提问

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
        mRB3.setText(answerC.get(flag));
        mRB4.setText(answerD.get(flag));
        mTextView5.setText(rightAnswer.get(flag));
        mTextView6.setText(yourAnswer.get(flag));
        mTextView7.setText(questionExplain.get(flag));

        //todo 很关键的代码操作
        Drawable b = getResources().getDrawable(R.drawable.ico_b_right);
        mRB2.setCompoundDrawablesWithIntrinsicBounds(b, null, null, null);

        Drawable d = getResources().getDrawable(R.drawable.ico_d_wrong);
        mRB4.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);

        mRB1.setClickable(false);
        mRB2.setClickable(false);
        mRB3.setClickable(false);
        mRB4.setClickable(false);

        mButton1.setOnClickListener(this);
        mTextView8.setOnClickListener(this);
    }

    //自己定义fragment的构造方法
    public static Page35MCDetailsOneFragment newInstance(List<String> questionType, List<String> questionNum, List<String> questionAll,
                                                         List<String> questionTitle, List<String> answerA, List<String> answerB,
                                                         List<String> answerC, List<String> answerD, List<String> rightAnswer,
                                                         List<String> yourAnswer, List<String> questionExplain, int flag) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("questionType", (ArrayList<String>) questionType);
        bundle.putStringArrayList("questionNum", (ArrayList<String>) questionNum);
        bundle.putStringArrayList("questionAll", (ArrayList<String>) questionAll);
        bundle.putStringArrayList("questionTitle", (ArrayList<String>) questionTitle);
        bundle.putStringArrayList("answerA", (ArrayList<String>) answerA);
        bundle.putStringArrayList("answerB", (ArrayList<String>) answerB);
        bundle.putStringArrayList("answerC", (ArrayList<String>) answerC);
        bundle.putStringArrayList("answerD", (ArrayList<String>) answerD);
        bundle.putStringArrayList("rightAnswer", (ArrayList<String>) rightAnswer);
        bundle.putStringArrayList("yourAnswer", (ArrayList<String>) yourAnswer);
        bundle.putStringArrayList("questionExplain", (ArrayList<String>) questionExplain);

        bundle.putInt("flag", flag);

        Page35MCDetailsOneFragment page34YCTestExplainOneFragment = new Page35MCDetailsOneFragment();
        page34YCTestExplainOneFragment.setArguments(bundle);

        return page34YCTestExplainOneFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page35_mc_details_one_tv8:
                Toast.makeText(getActivity(), "编辑笔记" + flag, Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page35_mc_details_one_btn1:
                if (mEditText1.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "我的提问内容是" + mEditText1.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
