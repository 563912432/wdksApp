package com.example.wdks.newwdksapp.pages;


import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page2ExamListViewAdapter;
import com.example.wdks.newwdksapp.adapter.Page2ExamRecycleView1Adapter;
import com.example.wdks.newwdksapp.adapter.Page2ExamRecycleView2Adapter;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.tools.MyProgressDialogConn;
import com.example.wdks.newwdksapp.tools.MySwipeRefreshLayout;
import com.example.wdks.newwdksapp.volley.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 商城页的图书页
 */
public class Page2ShopExamFragment extends Fragment {
    private View view;
    private List<CoursesData> mData1, mData2, mData3;
    private RecyclerView mRecycleView1, mRecycleView2;
    private Page2ExamRecycleView1Adapter mRecycleView1Adapter;
    private Page2ExamRecycleView2Adapter mRecycleView2Adapter;
    private ListView mListView;
    private Page2ExamListViewAdapter mListViewAdapter;
    private MySwipeRefreshLayout mSwipeRefresh;
    private Dialog mDialog;
    private JsonObjectRequest mRequest1;
    private JSONArray mJSONArray;
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    //初始化数据
    private void initData() {
        mData1 = new ArrayList<>();
        mData1.add(new CoursesData("会计类"));
        mData1.add(new CoursesData("金融类"));
        mData1.add(new CoursesData("建筑类"));
        mData1.add(new CoursesData("医学类"));
        mData1.add(new CoursesData("教师类"));
        mData1.add(new CoursesData("计算机类"));

        mData2 = new ArrayList<>();
        mData2.add(new CoursesData("会计从业"));
        mData2.add(new CoursesData("初级会计职称"));
        mData2.add(new CoursesData("中级会计职称"));
        mData2.add(new CoursesData("会计实操"));

        mData3 = new ArrayList<>();
        mRequest1 = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //获取数据成功
                try {
                    mJSONArray = jsonObject.getJSONArray("data");
                    jsonObject = mJSONArray.getJSONObject(1);
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统", "在线包过系统 一对一答疑", "70.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统会计从业无纸化考试系统会计从业无纸化考试系统", "在线包过系统 一对一答疑在线包过系统 一对一答疑在线包过系统 一对一答疑",
                            "70.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统", "在线包过系统 一对一答疑", "70.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统", "在线包过系统 一对一答疑", jsonObject.getString("learner"), "30.00"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mDialog.dismiss();

                mListViewAdapter = new Page2ExamListViewAdapter(getActivity(), mData3);
                mListView.setAdapter(mListViewAdapter);
                mListViewAdapter.setOnClickListener(new ListOnClickListener1());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequest1.setTag("page2_exam_kc");
        MySingleton.getInstance(getActivity()).addToRequestQueue(mRequest1);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page2_shop_exam, container, false);
            initView();
        }
        return view;
    }

    //初始化view
    private void initView() {

        //加载数据之前弹出进度条对话框(自定义)
        MyProgressDialogConn dialog = new MyProgressDialogConn();
        mDialog = dialog.createLoader(getActivity());
        mDialog.show();

        //判断是否有网络连接
        isConn(getActivity());

        /*
        * 大分类一
        * */
        mRecycleView1 = (RecyclerView) view.findViewById(R.id.ks_page2_exam_recycleView1);
        //实现recycleView的显示方向
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycleView1.setLayoutManager(manager1);

        mRecycleView1Adapter = new Page2ExamRecycleView1Adapter(getActivity(), mData1);
        mRecycleView1.setAdapter(mRecycleView1Adapter);

        mRecycleView1Adapter.setOnItemClickListener(new ExamRecycleView1ItemClick());

        /*
        * 大分类二
        * */
        mRecycleView2 = (RecyclerView) view.findViewById(R.id.ks_page2_exam_recycleView2);
        //实现recycleView的显示方向
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycleView2.setLayoutManager(manager2);

        mRecycleView2Adapter = new Page2ExamRecycleView2Adapter(getActivity(), mData2);
        mRecycleView2.setAdapter(mRecycleView2Adapter);

        mRecycleView2Adapter.setOnItemClickListener(new ExamRecycleView2ItemClick1());
        /*
        * 刷新控件
        * */
        mSwipeRefresh = (MySwipeRefreshLayout) view.findViewById(R.id.ks_page2_exam_swRefresh);
        /*
        * 详细内容
        * */
        mListView = (ListView) view.findViewById(R.id.ks_page2_exam_listView);

        init();
    }

    //view操作
    private void init() {
        initMyRefresh();   //刷新控件的操作

    }

    //判断当前是否有网络连接
    private boolean isConn(Context context) {
        if (context != null) {
            ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo info = conManager.getActiveNetworkInfo();
            if (info == null) {
                Toast.makeText(getActivity(), "当前没有网络连接", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mDialog.dismiss();
                    }
                }).start();
            } else {
                return info.isAvailable();
            }
        }
        return false;
    }

    //刷新控件的操作
    private void initMyRefresh() {
        mSwipeRefresh.setProgressViewEndTarget(true, 0);   //高度
        mSwipeRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {

            }
        });
    }

    //大分类一的点击事件（关联大分类二）
    public class ExamRecycleView1ItemClick implements Page2ExamRecycleView1Adapter.OnItemClickListener {

        @Override
        public void onItemClick(View view, TextView textView, int position) {
            switch (position) {
                //更新mData2
                case 0:
                    mData2 = new ArrayList<>();
                    mData2.add(new CoursesData("会计从业"));
                    mData2.add(new CoursesData("初级会计职称"));
                    mData2.add(new CoursesData("中级会计职称"));
                    mData2.add(new CoursesData("会计实操"));
                    mRecycleView2Adapter = new Page2ExamRecycleView2Adapter(getActivity(), mData2);
                    mRecycleView2.setAdapter(mRecycleView2Adapter);
                    mRecycleView2Adapter.setOnItemClickListener(new ExamRecycleView2ItemClick1());
                    mRecycleView2Adapter.notifyDataSetChanged();

                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统", "在线包过系统 一对一答疑", "70.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统会计从业无纸化考试系统会计从业无纸化考试系统", "在线包过系统 一对一答疑在线包过系统 一对一答疑在线包过系统 一对一答疑",
                            "70.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统", "在线包过系统 一对一答疑", "70.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统", "在线包过系统 一对一答疑", "70.00", "30.00"));
                    mListViewAdapter = new Page2ExamListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener1());
                    mListViewAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    mData2 = new ArrayList<>();
                    mData2.add(new CoursesData("证券从业"));
                    mData2.add(new CoursesData("基金从业"));
                    mData2.add(new CoursesData("期贷从业"));
                    mData2.add(new CoursesData("银行从业"));
                    mRecycleView2Adapter = new Page2ExamRecycleView2Adapter(getActivity(), mData2);
                    mRecycleView2.setAdapter(mRecycleView2Adapter);
                    mRecycleView2Adapter.setOnItemClickListener(new ExamRecycleView2ItemClick2());
                    mRecycleView2Adapter.notifyDataSetChanged();

                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1eDEwNXXXXXaaaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券从业资格考试题库", "在线包过系统 一对一答疑", "60.00", "45.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1eDEwNXXXXXaaaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券从业资格考试题库证券从业资格考试题库证券从业资格考试题库证券从业资格考试题库",
                            "在线包过系统 一对一答疑", "60.00", "45.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1eDEwNXXXXXaaaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券从业资格考试题库", "在线包过系统 一对一答疑", "60.00", "45.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1eDEwNXXXXXaaaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券从业资格考试题库", "在线包过系统 一对一答疑", "60.00", "45.00"));

                    mListViewAdapter = new Page2ExamListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener5());
                    mListViewAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    //会计类的小分类
    private class ExamRecycleView2ItemClick1 implements Page2ExamRecycleView2Adapter.OnItemClickListener {
        @Override
        public void onItemClick(View view, TextView textView, int position) {
            switch (position) {
                case 0:   //会计从业
                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统", "在线包过系统 一对一答疑", "70.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统会计从业无纸化考试系统会计从业无纸化考试系统", "在线包过系统 一对一答疑在线包过系统 一对一答疑在线包过系统 一对一答疑",
                            "70.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统", "在线包过系统 一对一答疑", "70.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统", "在线包过系统 一对一答疑", "70.00", "30.00"));
                    mListViewAdapter = new Page2ExamListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener1());
                    mListViewAdapter.notifyDataSetChanged();
                    break;
                case 1:   //初级会计职称
                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i4/TB1EmbaLVXXXXXmaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "K2016年初级会计职称考试习题试卷题库会计师软件全套课件 ", "在线包过系统 手机平板学习 赠送绝密资料", "60.00", "20.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i4/TB1EmbaLVXXXXXmaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "K2016年初级会计职称考试习题试卷题库会计师软件全套课件 ", "在线包过系统 手机平板学习 赠送绝密资料", "60.00", "20.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i4/TB1EmbaLVXXXXXmaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "K2016年初级会计职称考试习题试卷题库会计师软件全套课件 ", "在线包过系统 手机平板学习 赠送绝密资料", "60.00", "20.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i4/TB1EmbaLVXXXXXmaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "K2016年初级会计职称考试习题试卷题库会计师软件全套课件 ", "在线包过系统 手机平板学习 赠送绝密资料", "60.00", "20.00"));

                    mListViewAdapter = new Page2ExamListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener2());
                    mListViewAdapter.notifyDataSetChanged();

                    break;
            }
        }
    }

    //金融类的小分类
    private class ExamRecycleView2ItemClick2 implements Page2ExamRecycleView2Adapter.OnItemClickListener {
        @Override
        public void onItemClick(View view, TextView textView, int position) {
            switch (position) {
                case 0:    //证券从业
                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1eDEwNXXXXXaaaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券从业资格考试题库", "在线包过系统 一对一答疑", "60.00", "45.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1eDEwNXXXXXaaaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券从业资格考试题库证券从业资格考试题库证券从业资格考试题库证券从业资格考试题库",
                            "在线包过系统 一对一答疑", "60.00", "45.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1eDEwNXXXXXaaaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券从业资格考试题库", "在线包过系统 一对一答疑", "60.00", "45.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1eDEwNXXXXXaaaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券从业资格考试题库", "在线包过系统 一对一答疑", "60.00", "45.00"));
                    mListViewAdapter = new Page2ExamListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener5());
                    mListViewAdapter.notifyDataSetChanged();
                    break;
                case 1:    //基金从业
                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1GvfmLVXXXXa3XFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "2016年证券投资基金从业考试题库软件章节练习试卷", "在线题库 无需发快递 超值赠品 ", "52.00", "15.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1GvfmLVXXXXa3XFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "2016年证券投资基金从业考试题库软件章节练习试卷2016年证券投资基金从业考试题库软件章节练习试卷",
                            "在线题库 无需发快递 超值赠品 ", "52.00", "15.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1GvfmLVXXXXa3XFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "2016年证券投资基金从业考试题库软件章节练习试卷", "在线题库 无需发快递 超值赠品 ", "52.00", "15.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1GvfmLVXXXXa3XFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "2016年证券投资基金从业考试题库软件章节练习试卷", "在线题库 无需发快递 超值赠品 ", "52.00", "15.00"));

                    mListViewAdapter = new Page2ExamListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener6());
                    mListViewAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    //会计从业的加入购物车
    private class ListOnClickListener1 implements Page2ExamListViewAdapter.MyClickListener {
        @Override
        public void onAddToCar(BaseAdapter adapter, View view, int position) {
            Toast.makeText(getActivity(), "会计从业题库" + position, Toast.LENGTH_SHORT).show();
        }
    }

    //初级会计职称的加入购物车
    private class ListOnClickListener2 implements Page2ExamListViewAdapter.MyClickListener {
        @Override
        public void onAddToCar(BaseAdapter adapter, View view, int position) {
            Toast.makeText(getActivity(), "初级会计职称题库" + position, Toast.LENGTH_SHORT).show();
        }
    }

    //证券从业的加入购物车
    private class ListOnClickListener5 implements Page2ExamListViewAdapter.MyClickListener {
        @Override
        public void onAddToCar(BaseAdapter adapter, View view, int position) {
            Toast.makeText(getActivity(), "证券从业题库" + position, Toast.LENGTH_SHORT).show();
        }
    }

    //基金从业的加入购物车
    private class ListOnClickListener6 implements Page2ExamListViewAdapter.MyClickListener {
        @Override
        public void onAddToCar(BaseAdapter adapter, View view, int position) {
            Toast.makeText(getActivity(), "基金从业题库" + position, Toast.LENGTH_SHORT).show();
        }
    }

}
