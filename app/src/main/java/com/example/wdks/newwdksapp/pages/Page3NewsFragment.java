package com.example.wdks.newwdksapp.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wdks.newwdksapp.R;
/*
* 资讯的总分类页
* */
public class Page3NewsFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button mButton1, mButton2;
    private Fragment mLatestFragment, mOfficialFragment;

    //初始化数据
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //初始化视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page3_news, container, false);
            initView();
        }
        return view;
    }

    //初始化视图
    private void initView() {
        mButton1 = (Button) view.findViewById(R.id.ks_page3_btn1);   //最新资讯
        mButton2 = (Button) view.findViewById(R.id.ks_page3_btn2);   //官方资讯
        //页面切换
        mLatestFragment = new Page3NewsLatestFragment();
        mOfficialFragment = new Page3NewsOfficialFragment();

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.ks_page3_frameLayout, mLatestFragment)
                .commit();

        init();
    }

    //视图操作
    private void init() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    //按钮切换的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page3_btn1:
                //按钮样式改变
                mButton1.setBackgroundResource(R.drawable.btn_news_shape1);
                mButton1.setTextColor(getResources().getColor(R.color.deepSkyBlue1));
                mButton2.setBackgroundResource(R.drawable.btn_news_shape2);
                mButton2.setTextColor(getResources().getColor(R.color.white));
                //页面切换
                if (mLatestFragment.isHidden()) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .hide(mOfficialFragment)
                            .show(mLatestFragment)
                            .commit();
                }

                break;
            case R.id.ks_page3_btn2:
                mButton1.setBackgroundResource(R.drawable.btn_news_shape3);
                mButton1.setTextColor(getResources().getColor(R.color.white));
                mButton2.setBackgroundResource(R.drawable.btn_news_shape4);
                mButton2.setTextColor(getResources().getColor(R.color.deepSkyBlue1));

                //页面切换
                switchFragment(mLatestFragment, mOfficialFragment);
                break;
        }
    }

    //最新资讯和官方资讯页面之间的切换
    private void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            transaction.hide(from)
                    .add(R.id.ks_page3_frameLayout, to)
                    .commit();
        } else {
            transaction.hide(from)
                    .show(to)
                    .commit();
        }
    }
}