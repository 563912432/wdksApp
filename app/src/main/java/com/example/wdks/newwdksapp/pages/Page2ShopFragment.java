package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.DisplayUtil;
import com.example.wdks.newwdksapp.tools.MyPopupWindow;
import com.readystatesoftware.viewbadger.BadgeView;


/**
 * 图书总分类页
 */
public class Page2ShopFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView mTextView1, mTextView2;
    private Fragment mBookFragment, mExamFragment;
    private ImageView mLine, mImageView1;
    private int mScreenOneThree;
    private MyPopupWindow mPopupWindow;
    private BadgeView mBadgeView;
    private RelativeLayout mBuyCar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page2_shop, container, false);
            initView();
        }
        return view;
    }

    //初始化数据
    private void initData() {
    }

    //初始化视图
    private void initView() {
        //顶部两个按钮
        mTextView1 = (TextView) view.findViewById(R.id.ks_page2_tv1);
        mTextView2 = (TextView) view.findViewById(R.id.ks_page2_tv2);
        mBookFragment = new Page2ShopBookFragment();
        mExamFragment = new Page2ShopExamFragment();

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.ks_page2_frameLayout, mBookFragment)
                .commit();

        //指示线
        mLine = (ImageView) view.findViewById(R.id.ks_page2_line);

        //右边两个图标
        mBuyCar = (RelativeLayout) view.findViewById(R.id.ks_page2_buy_car); //购物车
        mImageView1 = (ImageView) view.findViewById(R.id.ks_page2_iv1);   //咨询

        mBadgeView = (BadgeView) view.findViewById(R.id.ks_page2_badgeView); //数字提醒
        mBadgeView.setText("1");
        mBadgeView.setTextSize(11);
        //mBadgeView.setTypeface(Typeface.create("微软雅黑", Typeface.NORMAL));
        mBadgeView.setTypeface(Typeface.MONOSPACE);
        init();  //视图操作
    }

    //视图操作
    private void init() {
        //指示线的变化
        initTabLine();

        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
        mImageView1.setOnClickListener(this);
        mBuyCar.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {

        LinearLayout.LayoutParams mLP = (LinearLayout.LayoutParams) mLine.getLayoutParams();

        switch (v.getId()) {
            case R.id.ks_page2_tv1:
                //文字颜色改变
                mTextView1.setTextColor(getResources().getColor(R.color.deepSkyBlue));
                mTextView2.setTextColor(getResources().getColor(R.color.greyBlack));
                //指示器切换
                mLP.leftMargin = mScreenOneThree;
                mLine.setLayoutParams(mLP);
                //页面切换
                if (mBookFragment.isHidden()) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .hide(mExamFragment)
                            .show(mBookFragment)
                            .commit();
                }
                break;
            case R.id.ks_page2_tv2:
                //文字颜色改变
                mTextView1.setTextColor(getResources().getColor(R.color.greyBlack));
                mTextView2.setTextColor(getResources().getColor(R.color.deepSkyBlue));
                //指示器切换
                mLP.leftMargin = mScreenOneThree + mScreenOneThree / 2;
                mLine.setLayoutParams(mLP);
                //页面切换
                switchFragment(mBookFragment, mExamFragment);
                break;
            case R.id.ks_page2_iv1:
                //todo 咨询的点击事件
                if (mPopupWindow == null) {
                    ConsultClickListener paramOnClickListener = new ConsultClickListener();
                    mPopupWindow = new MyPopupWindow(getActivity(), paramOnClickListener, DisplayUtil.dip2px(getActivity(), 115),
                            DisplayUtil.dip2px(getActivity(), 80));
                    //监听窗口的焦点事件，点击窗口外面则取消显示
                    mPopupWindow.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                mPopupWindow.dismiss();
                            }
                        }
                    });
                }
                //设置默认获取焦点
                mPopupWindow.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                mPopupWindow.showAsDropDown(mImageView1, 0, 30);
                //如果窗口存在，则更新
                mPopupWindow.update();
                break;
            case R.id.ks_page2_buy_car:
                //Toast.makeText(getActivity(), "进入购物车页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Page14BuyCarActivity.class));
                break;
        }
    }

    //咨询的电话咨询和qq咨询的点击事件
    public class ConsultClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ks_page2_pop_phone:
                    //Toast.makeText(getActivity(), "电话咨询", Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse("tel:10086");
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                    break;
                case R.id.ks_page2_pop_qq:
                    Toast.makeText(getActivity(), "qq咨询", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    //图书和题库页面之间的切换
    private void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            transaction.hide(from)
                    .add(R.id.ks_page2_frameLayout, to)
                    .commit();
        } else {
            transaction.hide(from)
                    .show(to)
                    .commit();
        }
    }

    //设置指示线的宽度
    private void initTabLine() {
        //获取屏幕宽度1/3
        Display mDisplay = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics mMetrics = new DisplayMetrics();
        mDisplay.getMetrics(mMetrics);

        mScreenOneThree = mMetrics.widthPixels / 3;

        //设置指示器宽度为1/6
        LinearLayout.LayoutParams mLP = (LinearLayout.LayoutParams) mLine.getLayoutParams();
        mLP.width = mScreenOneThree / 2;

        mLP.leftMargin = mScreenOneThree;

        mLine.setLayoutParams(mLP);

    }

}
