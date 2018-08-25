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
import com.example.wdks.newwdksapp.adapter.Page2BookListViewAdapter;
import com.example.wdks.newwdksapp.adapter.Page2BookRecycleView1Adapter;
import com.example.wdks.newwdksapp.adapter.Page2BookRecycleView2Adapter;
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
public class Page2ShopBookFragment extends Fragment {
    private View view;
    private List<CoursesData> mData1, mData2, mData3;
    private RecyclerView mRecycleView1, mRecycleView2;
    private Page2BookRecycleView1Adapter mRecycleView1Adapter;
    private Page2BookRecycleView2Adapter mRecycleView2Adapter;
    private ListView mListView;
    private Page2BookListViewAdapter mListViewAdapter;
    private MySwipeRefreshLayout mSwipeRefresh;
    private Dialog mDialog;
    private JsonObjectRequest mRequest1;
    private JSONArray mJSONArray;
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";
    private JsonObjectRequest mRequestCeShi;

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
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1xnTIKVXXXXaRapXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计电算化", "赠送在线考试软件 价值80元", "40.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1czD.KVXXXXX_XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选",
                            "赠送在线考试软件 价值80元赠送在线考试软件 价值80元赠送在线考试软件 价值80元", "40.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1xnTIKVXXXXaRapXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计电算化", "赠送在线考试软件 价值80元", null, "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1czD.KVXXXXX_XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选",
                            "赠送在线考试软件 价值80元赠送在线考试软件 价值80元赠送在线考试软件 价值80元", jsonObject.getString("learner"), "30.00"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mListViewAdapter = new Page2BookListViewAdapter(getActivity(), mData3);
                mListView.setAdapter(mListViewAdapter);
                mListViewAdapter.setOnClickListener(new ListOnClickListener1());
                mSwipeRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
                    @Override
                    public void onLoad() {
                        mSwipeRefresh.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < 4; i++) {
                                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1xnTIKVXXXXaRapXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                                            "会计电算化", "测试上拉加载", "40.00", "30.00"));
                                }
                                mSwipeRefresh.setLoading(false);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mListViewAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }, 200);
                    }
                });

                mDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequest1.setTag("page2_book_kc");
        MySingleton.getInstance(getActivity()).addToRequestQueue(mRequest1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page2_shop_book, container, false);
            initView();
        }
        return view;
    }

    //初始化view
    private void initView() {
        //判断是否有网络连接
        isConn(getActivity());

        //加载数据之前弹出进度条对话框(自定义)
        MyProgressDialogConn dialog = new MyProgressDialogConn();
        mDialog = dialog.createLoader(getActivity());
        mDialog.show();

        /*
        * 大分类一
        * */
        mRecycleView1 = (RecyclerView) view.findViewById(R.id.ks_page2_book_recycleView1);
        //实现recycleView的显示方向
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycleView1.setLayoutManager(manager1);

        mRecycleView1Adapter = new Page2BookRecycleView1Adapter(getActivity(), mData1);
        mRecycleView1.setAdapter(mRecycleView1Adapter);

        mRecycleView1Adapter.setOnItemClickListener(new BookRecycleView1ItemClick());

        /*
        * 大分类二
        * */
        mRecycleView2 = (RecyclerView) view.findViewById(R.id.ks_page2_book_recycleView2);
        //实现recycleView的显示方向
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycleView2.setLayoutManager(manager2);

        mRecycleView2Adapter = new Page2BookRecycleView2Adapter(getActivity(), mData2);
        mRecycleView2.setAdapter(mRecycleView2Adapter);

        mRecycleView2Adapter.setOnItemClickListener(new BookRecycleView2ItemClick1());
        /*
        * 刷新控件
        * */
        mSwipeRefresh = (MySwipeRefreshLayout) view.findViewById(R.id.ks_page2_book_swRefresh);
        /*
        * 详细内容
        * */
        mListView = (ListView) view.findViewById(R.id.ks_page2_book_listView);

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
    }

    //大分类一的点击事件（关联大分类二）
    public class BookRecycleView1ItemClick implements Page2BookRecycleView1Adapter.OnItemClickListener {

        @Override
        public void onItemClick(View view, TextView textView, int position) {
            switch (position) {
                //更新mData2
                case 0:   //会计类
                    mData2 = new ArrayList<>();
                    mData2.add(new CoursesData("会计从业"));
                    mData2.add(new CoursesData("初级会计职称"));
                    mData2.add(new CoursesData("中级会计职称"));
                    mData2.add(new CoursesData("会计实操"));
                    mRecycleView2Adapter = new Page2BookRecycleView2Adapter(getActivity(), mData2);
                    mRecycleView2.setAdapter(mRecycleView2Adapter);
                    mRecycleView2Adapter.setOnItemClickListener(new BookRecycleView2ItemClick1());
                    mRecycleView2Adapter.notifyDataSetChanged();

                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1xnTIKVXXXXaRapXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计电算化", "赠送在线考试软件 价值80元", "40.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1czD.KVXXXXX_XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选",
                            "赠送在线考试软件 价值80元赠送在线考试软件 价值80元赠送在线考试软件 价值80元", "40.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1xnTIKVXXXXaRapXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计电算化", "赠送在线考试软件 价值80元", null, "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1czD.KVXXXXX_XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选",
                            "赠送在线考试软件 价值80元赠送在线考试软件 价值80元赠送在线考试软件 价值80元", "40.00", "30.00"));
                    mListViewAdapter = new Page2BookListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener1());
                    mListViewAdapter.notifyDataSetChanged();
                    mSwipeRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
                        @Override
                        public void onLoad() {
                            mSwipeRefresh.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < 4; i++) {
                                        mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1xnTIKVXXXXaRapXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                                                "会计电算化", "测试上拉加载", "40.00", "30.00"));
                                    }
                                    mSwipeRefresh.setLoading(false);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mListViewAdapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }, 200);
                        }
                    });
                    break;
                case 1:   //金融类
                    mData2 = new ArrayList<>();
                    mData2.add(new CoursesData("证券从业"));
                    mData2.add(new CoursesData("基金从业"));
                    mData2.add(new CoursesData("期贷从业"));
                    mData2.add(new CoursesData("银行从业"));
                    mRecycleView2Adapter = new Page2BookRecycleView2Adapter(getActivity(), mData2);
                    mRecycleView2.setAdapter(mRecycleView2Adapter);
                    mRecycleView2Adapter.setOnItemClickListener(new BookRecycleView2ItemClick2());
                    mRecycleView2Adapter.notifyDataSetChanged();

                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB17ZNOKVXXXXccXFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "金融市场基础知识证券市场基本法律法规试卷", "真题 通关秘籍 2016新版", "60.00", "25.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1LSQ8KFXXXXbSXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券市场基本法律法规 金融市场基础知识", "两本教材 两本真题册子 配送在线题库 赠送视频 ", "112.00", "32.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB17ZNOKVXXXXccXFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "金融市场基础知识证券市场基本法律法规试卷", "真题 通关秘籍 2016新版", "60.00", "25.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1LSQ8KFXXXXbSXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券市场基本法律法规 金融市场基础知识", "两本教材 两本真题册子 配送在线题库 赠送视频 ", "112.00", "32.00"));
                    mListViewAdapter = new Page2BookListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener5());
                    mListViewAdapter.notifyDataSetChanged();
                    mSwipeRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
                        @Override
                        public void onLoad() {
                            mSwipeRefresh.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < 4; i++) {
                                        mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB17ZNOKVXXXXccXFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                                                "金融市场基础知识证券市场基本法律法规试卷", "测试上拉加载", "60.00", "25.00"));
                                    }
                                    mSwipeRefresh.setLoading(false);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mListViewAdapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }, 200);
                        }
                    });
                    break;
            }
        }
    }

    //会计类的小分类
    private class BookRecycleView2ItemClick1 implements Page2BookRecycleView2Adapter.OnItemClickListener {
        @Override
        public void onItemClick(View view, TextView textView, int position) {
            switch (position) {
                case 0:   //会计从业
                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1xnTIKVXXXXaRapXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计电算化", "赠送在线考试软件 价值80元", "40.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1czD.KVXXXXX_XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选",
                            "赠送在线考试软件 价值80元赠送在线考试软件 价值80元赠送在线考试软件 价值80元", "40.00", "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1xnTIKVXXXXaRapXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计电算化", "赠送在线考试软件 价值80元", null, "30.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1czD.KVXXXXX_XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选",
                            "赠送在线考试软件 价值80元赠送在线考试软件 价值80元赠送在线考试软件 价值80元", "40.00", "30.00"));
                    mListViewAdapter = new Page2BookListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener1());
                    mListViewAdapter.notifyDataSetChanged();
                    mSwipeRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
                        @Override
                        public void onLoad() {
                            mSwipeRefresh.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mRequestCeShi = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject jsonObject) {
                                            //获取数据成功
                                            try {
                                                mJSONArray = jsonObject.getJSONArray("data");
                                                for (int i = 0; i < 4; i++) {
                                                    jsonObject = mJSONArray.getJSONObject(i);
                                                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1czD.KVXXXXX_XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                                                            "会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选",
                                                            "测试上拉加载", jsonObject.getString("learner"), "30.00"));
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            mSwipeRefresh.setLoading(false);
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mListViewAdapter.notifyDataSetChanged();
                                                }
                                            });
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    });
                                    mRequestCeShi.setTag("page2_book_ceShi");
                                    MySingleton.getInstance(getActivity()).addToRequestQueue(mRequestCeShi);
                                }
                            }, 200);
                        }
                    });
                    break;
                case 1:   //初级会计职称
                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1fW11NXXXXXX9XXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "初级会计实务 经济法基础", "备考2017 初级会计职称 2016真题", "60.00", "19.90"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1fW11NXXXXXX9XXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "初级会计实务 经济法基础", "备考2017 初级会计职称 2016真题", null, "19.90"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1fW11NXXXXXX9XXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "初级会计实务 经济法基础", "备考2017 初级会计职称 2016真题", "60.00", "19.90"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1fW11NXXXXXX9XXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "初级会计实务 经济法基础", "备考2017 初级会计职称 2016真题", "60.00", "19.90"));

                    mListViewAdapter = new Page2BookListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener2());
                    mListViewAdapter.notifyDataSetChanged();
                    mSwipeRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
                        @Override
                        public void onLoad() {
                            mSwipeRefresh.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < 4; i++) {
                                        mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1fW11NXXXXXX9XXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                                                "初级会计实务 经济法基础", "测试上拉加载", "60.00", "19.90"));
                                    }
                                    mSwipeRefresh.setLoading(false);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mListViewAdapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }, 200);
                        }
                    });
                    break;
            }
        }
    }

    //金融类的小分类
    private class BookRecycleView2ItemClick2 implements Page2BookRecycleView2Adapter.OnItemClickListener {
        @Override
        public void onItemClick(View view, TextView textView, int position) {
            switch (position) {
                case 0:    //证券从业
                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB17ZNOKVXXXXccXFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "金融市场基础知识证券市场基本法律法规试卷", "真题 通关秘籍 2016新版", "60.00", "25.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1LSQ8KFXXXXbSXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券市场基本法律法规 金融市场基础知识", "两本教材 两本真题册子 配送在线题库 赠送视频 ", "112.00", "32.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB17ZNOKVXXXXccXFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "金融市场基础知识证券市场基本法律法规试卷", "真题 通关秘籍 2016新版", "60.00", "25.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1LSQ8KFXXXXbSXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券市场基本法律法规 金融市场基础知识", "两本教材 两本真题册子 配送在线题库 赠送视频 ", "112.00", "32.00"));
                    mListViewAdapter = new Page2BookListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener5());
                    mListViewAdapter.notifyDataSetChanged();
                    mSwipeRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
                        @Override
                        public void onLoad() {
                            mSwipeRefresh.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < 4; i++) {
                                        mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB17ZNOKVXXXXccXFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                                                "金融市场基础知识证券市场基本法律法规试卷", "测试上拉加载", "60.00", "25.00"));
                                    }
                                    mSwipeRefresh.setLoading(false);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mListViewAdapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }, 200);
                        }
                    });
                    break;
                case 1:    //基金从业
                    mData3 = new ArrayList<>();
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1dEPONpXXXXbkXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券投资基金基础知识", "2016新版 单本教材 +单本真题 +单科题库 ", "52.00", "15.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1m.HfLXXXXXbaaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "基金法律法规 职业道德与业务规范 证券投资基金基础知识", "基金从业 2016新版 2本试卷", "60.00", "25.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1dEPONpXXXXbkXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "证券投资基金基础知识", "2016新版 单本教材 +单本真题 +单科题库 ", "52.00", "15.00"));
                    mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1m.HfLXXXXXbaaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "基金法律法规 职业道德与业务规范 证券投资基金基础知识", "基金从业 2016新版 2本试卷", "60.00", "25.00"));
                    mListViewAdapter = new Page2BookListViewAdapter(getActivity(), mData3);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.setOnClickListener(new ListOnClickListener6());
                    mListViewAdapter.notifyDataSetChanged();
                    mSwipeRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
                        @Override
                        public void onLoad() {
                            mSwipeRefresh.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < 4; i++) {
                                        mData3.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1dEPONpXXXXbkXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                                                "证券投资基金基础知识", "测试上拉加载", "52.00", "15.00"));
                                    }
                                    mSwipeRefresh.setLoading(false);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mListViewAdapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }, 200);
                        }
                    });
                    break;
            }
        }
    }

    //会计从业的加入购物车
    private class ListOnClickListener1 implements Page2BookListViewAdapter.MyClickListener {
        @Override
        public void onAddToCar(BaseAdapter adapter, View view, int position) {
            Toast.makeText(getActivity(), "会计从业图书" + position, Toast.LENGTH_SHORT).show();
        }
    }

    //初级会计职称的加入购物车
    private class ListOnClickListener2 implements Page2BookListViewAdapter.MyClickListener {
        @Override
        public void onAddToCar(BaseAdapter adapter, View view, int position) {
            Toast.makeText(getActivity(), "初级会计职称图书" + position, Toast.LENGTH_SHORT).show();
        }
    }

    //证券从业的加入购物车
    private class ListOnClickListener5 implements Page2BookListViewAdapter.MyClickListener {
        @Override
        public void onAddToCar(BaseAdapter adapter, View view, int position) {
            Toast.makeText(getActivity(), "证券从业图书" + position, Toast.LENGTH_SHORT).show();
        }
    }

    //基金从业的加入购物车
    private class ListOnClickListener6 implements Page2BookListViewAdapter.MyClickListener {
        @Override
        public void onAddToCar(BaseAdapter adapter, View view, int position) {
            Toast.makeText(getActivity(), "基金从业图书" + position, Toast.LENGTH_SHORT).show();
        }
    }

}
