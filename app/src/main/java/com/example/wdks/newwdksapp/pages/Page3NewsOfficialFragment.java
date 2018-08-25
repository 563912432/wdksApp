package com.example.wdks.newwdksapp.pages;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page3ListView2Adapter;
import com.example.wdks.newwdksapp.data.NewsData;
import com.example.wdks.newwdksapp.tools.MySwipeRefreshLayout;
import com.example.wdks.newwdksapp.volley.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 资讯页的官方资讯页
 */
public class Page3NewsOfficialFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View view;
    private MySwipeRefreshLayout mSwipeRefresh;
    private List<NewsData> mData;
    private ListView mListView;
    private Page3ListView2Adapter mListViewAdapter;
    private JsonObjectRequest mRequest;
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";
    private String now;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page3_news_official, container, false);
            initView();
        }
        return view;
    }

    private void initData() {
        now();

        mData = new ArrayList<NewsData>();
        mRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < 10; i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        mData.add(new NewsData(
                                jsonObject.getString("name"),
                                jsonObject.getString("id"),
                                now));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (mListView != null) {
                    mListViewAdapter = new Page3ListView2Adapter(getActivity(), mData);
                    mListView.setAdapter(mListViewAdapter);
                }
                if (mSwipeRefresh != null) {
                    mSwipeRefresh.setRefreshing(false);
                }
            }
        }, new Response.ErrorListener() {      //错误提示
            @Override
            public void onErrorResponse(VolleyError error) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mSwipeRefresh != null) {
                                    mSwipeRefresh.setRefreshing(false);
                                }
                                Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
        mRequest.setTag("page3_listView2");
        MySingleton.getInstance(getActivity()).addToRequestQueue(mRequest);
    }

    //获取当前时间
    private void now() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        now = year + "-" + month + "-" + day;
    }


    //初始化视图
    private void initView() {
        mSwipeRefresh = (MySwipeRefreshLayout) view.findViewById(R.id.ks_page3_official_swRefresh);
        mListView = (ListView) view.findViewById(R.id.ks_page3_official_listView);
        mListView.setOnItemClickListener(this);

        init();
    }

    //视图操作
    private void init() {
        initSwipeRefresh();   //下拉刷新操作
    }

    private void initSwipeRefresh() {
        mSwipeRefresh.setColorSchemeResources(R.color.deepSkyBlue, R.color.limeGreen, R.color.orange, R.color.red);  //颜色
        mSwipeRefresh.setSize(SwipeRefreshLayout.DEFAULT);  //大小
        mSwipeRefresh.setProgressViewEndTarget(true, 150);  //高度
        //首次进入页面，自动刷新，获取数据
        mSwipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(true);
            }
        });
        //下拉刷新 获取到正确数据，刷新按钮自动消失
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefresh.setRefreshing(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mSwipeRefresh.setRefreshing(false);
                                if (mListViewAdapter != null) {
                                    mListViewAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
        //上拉加载
        mSwipeRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                mSwipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            mData.add(new NewsData("新闻测试无图上拉加载", "某某某", now));
                        }
                        mSwipeRefresh.setLoading(false);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mListViewAdapter != null) {
                                    mListViewAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                }, 200);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), Page49NewsDetailsWebViewActivity.class));
    }
}
