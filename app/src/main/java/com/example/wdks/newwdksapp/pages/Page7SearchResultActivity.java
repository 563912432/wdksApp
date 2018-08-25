package com.example.wdks.newwdksapp.pages;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page7SearchResultListViewAdapter;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.tools.MyProgressDialogConn;
import com.example.wdks.newwdksapp.tools.MySwipeRefreshLayout;
import com.example.wdks.newwdksapp.volley.MySingleton;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.readystatesoftware.viewbadger.BadgeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/*
* 搜索结果页面
* */
public class Page7SearchResultActivity extends AppCompatActivity implements View.OnClickListener,
        Page7SearchResultListViewAdapter.MyClickListener {

    private TextView mTextView1;
    private ImageView mImageView1;
    private LinearLayout mBuyCar;
    private BadgeView mBadgeView;
    private Dialog mDialog;
    private List<CoursesData> mData;
    private MySwipeRefreshLayout mRefresh;
    private ListView mListView;
    private Page7SearchResultListViewAdapter mListViewAdapter;
    private JsonObjectRequest mRequest;
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";
    private String number = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page7_search_result);
        initWindow();   //自定义状态栏颜色
        initData();
        initView();
    }

    //api 19后自定义状态栏颜色
    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.lightSkyBlue));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    //停掉网络请求
    @Override
    protected void onStop() {
        super.onStop();
        MySingleton.getInstance(this).cancelPendingRequests("page7_result");
    }

    //初始化数据   volley请求
    private void initData() {
        isConn(this);
        mData = new ArrayList<CoursesData>();
        mRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //获取数据正确
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    jsonObject = jsonArray.getJSONObject(2);
                    mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1xnTIKVXXXXaRapXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计电算化", "考证必选 赠送在线考试题库", "70.00", "30.00"));
                    mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计从业无纸化考试系统", "在线包过系统 一对一答疑", jsonObject.getString("learner"), "30.00"));
                    mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1czD.KVXXXXX_XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选会计基础 2017年最新版 考证必选",
                            "赠送在线考试软件 价值80元赠送在线考试软件 价值80元赠送在线考试软件 价值80元", null, "30.00"));
                    mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB17ZNOKVXXXXccXFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "金融市场基础知识证券市场基本法律法规试卷", "真题 通关秘籍 2016新版", "60.00", "25.00"));
                    mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1GvfmLVXXXXa3XFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "2016年证券投资基金从业考试题库软件章节练习试卷", null, "52.00", "15.00"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (mDialog != null) {
                    mDialog.dismiss();
                }
                mListViewAdapter = new Page7SearchResultListViewAdapter(Page7SearchResultActivity.this, mData);
                if (mListView != null) {
                    mListView.setAdapter(mListViewAdapter);
                }
                mListViewAdapter.setOnClickListener(Page7SearchResultActivity.this);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //获取数据错误

            }
        });
        mRequest.setTag("page7_result");
        MySingleton.getInstance(Page7SearchResultActivity.this).addToRequestQueue(mRequest);
    }

    //初始化视图
    private void initView() {
        //先弹出对话框
        MyProgressDialogConn dialog = new MyProgressDialogConn();
        mDialog = dialog.createLoader(this);
        mDialog.show();

        mTextView1 = (TextView) findViewById(R.id.ks_page7_tv1);   //搜索结果文字显示
        mImageView1 = (ImageView) findViewById(R.id.ks_page7_iv1);   //返回按钮
        mBuyCar = (LinearLayout) findViewById(R.id.ks_page7_buy_car); //购物车的组合按钮
        mBadgeView = (BadgeView) findViewById(R.id.ks_page7_badgeView);   //数字标签
        mRefresh = (MySwipeRefreshLayout) findViewById(R.id.ks_page7_swRefresh);  //刷新控件
        mListView = (ListView) findViewById(R.id.ks_page7_listView);   //结果显示

        init();
    }

    //视图操作
    private void init() {
        //改变中间位置
        Intent intent = getIntent();
        String s = intent.getExtras().getString("name");
        mTextView1.setText(s);
        //数字标签操作
        mBadgeView.setText(number);
        mBadgeView.setTextSize(11);
        mBadgeView.setTypeface(Typeface.MONOSPACE);
        //刷新按钮操作
        initSwRefresh();
        mImageView1.setOnClickListener(this);
        mBuyCar.setOnClickListener(this);
    }

    private void initSwRefresh() {
        mRefresh.setProgressViewEndTarget(true, 0);   //高度
        mRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                mRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1xnTIKVXXXXaRapXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                                    "会计电算化", "测试上拉加载", "40.00", "30.00"));
                        }
                        mRefresh.setLoading(false);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListViewAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }, 200);
            }
        });
    }

    //判断当前是否有网络连接
    private boolean isConn(Context context) {
        if (context != null) {
            ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo info = conManager.getActiveNetworkInfo();
            if (info == null) {
                Toast.makeText(this, "当前没有网络连接", Toast.LENGTH_SHORT).show();
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

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page7_iv1:
                finish();
                break;
            case R.id.ks_page7_buy_car:
                Toast.makeText(this, "进入购物车页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //添加到购物车按钮的点击事件
    @Override
    public void onAddToCar(BaseAdapter adapter, View view, int position) {
        Toast.makeText(this, "添加到购物车" + position, Toast.LENGTH_SHORT).show();
        int i = Integer.parseInt(number) + 1;
        number = String.valueOf(i);

        mBadgeView.setText(number);
    }
}
