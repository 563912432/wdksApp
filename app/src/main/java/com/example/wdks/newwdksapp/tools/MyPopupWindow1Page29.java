package com.example.wdks.newwdksapp.tools;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.adapter.Page29TestFiveListView1Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/28 0008.
 * 自定义下拉菜单
 * 里面提供了一个构造方法，包含四个参数，第一个参数是上下文的activity，第二个是菜单的点击事件，从外边传递进来的，要绑定给每一行的菜单，
 * 具体的事件实现当然要写在activity中，后面两个分别是弹出窗口的宽度和高度。里面还包含了一个动画样式，窗口打开和关闭时出现动画的样式。
 * 第一层选择的弹窗
 */

public class MyPopupWindow1Page29 extends PopupWindow {
    private View mainView;
    private ListView mListView;
    private List<CoursesData> mData;
    private Page29TestFiveListView1Adapter mAdapter;

    public MyPopupWindow1Page29(Context context, AdapterView.OnItemClickListener paramOnItemClickListener,
                                int paramInt1, int paramInt2) {
        super(context);
        //窗口布局
        mainView = LayoutInflater.from(context).inflate(R.layout.page29_chapter_test_five_spinner1, null);

        mData = new ArrayList<CoursesData>();
        mData.add(new CoursesData("借"));
        mData.add(new CoursesData("贷"));

        mListView = (ListView) mainView.findViewById(R.id.ks_page29_test_five_spinner1_listView);
        mAdapter = new Page29TestFiveListView1Adapter(context, mData);
        mListView.setAdapter(mAdapter);

        //设置每个子布局的事件监听器
        if (paramOnItemClickListener != null) {
            mListView.setOnItemClickListener(paramOnItemClickListener);
        }
        setContentView(mainView);
        //设置宽度
        setWidth(paramInt1);
        //设置高度
        setHeight(paramInt2);
        //设置显示隐藏动画
        setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
    }
}
