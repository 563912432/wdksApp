package com.example.wdks.newwdksapp.tools;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.wdks.newwdksapp.R;

/**
 * Created by Administrator on 2016/10/8 0008.
 * 自定义下拉菜单
 * 里面提供了一个构造方法，包含四个参数，第一个参数是上下文的activity，第二个是菜单的点击事件，从外边传递进来的，要绑定给每一行的菜单，
 * 具体的事件实现当然要写在activity中，后面两个分别是弹出窗口的宽度和高度。里面还包含了一个动画样式，窗口打开和关闭时出现动画的样式。
 */

public class MyPopupWindowPage10 extends PopupWindow {
    private View mainView;
    private LinearLayout phone, qq;
    private int popHeight;
    private int popWidth;

    public MyPopupWindowPage10(Context context, View.OnClickListener paramOnClickListener,
                               int paramInt1, int paramInt2) {
        super(context);
        //窗口布局
        mainView = LayoutInflater.from(context).inflate(R.layout.page2_pop_consult, null);
        //电话咨询
        phone = (LinearLayout) mainView.findViewById(R.id.ks_page2_pop_phone);
        //qq咨询
        qq = (LinearLayout) mainView.findViewById(R.id.ks_page2_pop_qq);
        //设置每个子布局的事件监听器
        if (paramOnClickListener != null) {
            phone.setOnClickListener(paramOnClickListener);
            qq.setOnClickListener(paramOnClickListener);
        }
        setContentView(mainView);
        //设置宽度
        setWidth(paramInt1);
        //设置高度
        setHeight(paramInt2);
        //设置显示隐藏动画
        setAnimationStyle(R.style.AnimTools_page10);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));

        //获取自身的长宽高
        mainView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popHeight = mainView.getMeasuredHeight();
        popWidth = mainView.getMeasuredWidth();

    }

    /**
     * 设置显示在v上方(以v的左边距为开始位置)
     *
     * @param v
     */
    public void showUp(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //在控件上方显示
        showAtLocation(v, Gravity.NO_GRAVITY, (location[0]) - popWidth / 2, location[1] - popHeight);
    }

    /**
     * 设置显示在v上方（以v的中心位置为开始位置）
     *
     * @param v
     */
    public void showUp2(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //在控件上方显示
        showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popWidth / 2, location[1] - popHeight);
    }
}
