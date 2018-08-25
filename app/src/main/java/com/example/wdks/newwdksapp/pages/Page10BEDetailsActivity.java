package com.example.wdks.newwdksapp.pages;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page10BeDetailsListViewAdapter;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.tools.DisplayUtil;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;
import com.example.wdks.newwdksapp.tools.MyPopupWindow;
import com.example.wdks.newwdksapp.tools.MyPopupWindowPage10;
import com.example.wdks.newwdksapp.tools.MySwipeRefreshLayout;
import com.example.wdks.newwdksapp.volley.MySingleton;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.readystatesoftware.viewbadger.BadgeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Page10BEDetailsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView mImageView1;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mPrice;
    private View mAdd, mLine, mBtn1, mBtn2, mBtn3;
    private List<CoursesData> mData;
    private ListView mListView;
    private Page10BeDetailsListViewAdapter mListViewAdapter;
    private MySwipeRefreshLayout mSwRefresh;
    private BadgeView mBadgeView;
    private MyPopupWindowPage10 mPopupWindow;
    private JsonObjectRequest mRequest;
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page10_be_details);
        initWindow();   //自定义状态栏颜色
        initData();
        initView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MySingleton.getInstance(this).cancelPendingRequests("page10_content");
    }

    //api 19后自定义状态栏颜色
    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.light_grey));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    //初始化数据
    private void initData() {
        isConn(this);

        mData = new ArrayList<CoursesData>();
        mRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    jsonObject = jsonArray.getJSONObject(1);
                    mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i4/TB1EmbaLVXXXXXmaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "2016年初级会计职称无纸化考试系统", "50.00", "30.00"));
                    mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1nLrvJFXXXXXFXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "全国执业药师资格考试无纸化考试专用系统", "通关秘籍 2016最新版", jsonObject.getString("learner"), "30.00"));
                    mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1fQesJFXXXXXHaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "市政公用工程管理与实务实务", "90.00", "10.00"));
                    mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1vF1vJVXXXXXaXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "中级会计职称", "100.00", jsonObject.getString("learner")));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (mListViewAdapter == null) {
                    mListViewAdapter = new Page10BeDetailsListViewAdapter(Page10BEDetailsActivity.this, mData);
                } else {
                    mListViewAdapter.notifyDataSetChanged();
                }
                if (mListView != null) {
                    mListView.setAdapter(mListViewAdapter);
                }
                mListViewAdapter.setOnClickListener(new Page10BeDetailsListViewAdapter.MyClickListener() {
                    @Override
                    public void onAddToCar(BaseAdapter adapter, View view, int position) {
                        //Toast.makeText(Page10BEDetailsActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                        number = mBadgeView.getText().toString();
                        int i = Integer.parseInt(number) + 1;
                        number = String.valueOf(i);
                        mBadgeView.setText(number);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequest.setTag("page10_content");
        MySingleton.getInstance(this).addToRequestQueue(mRequest);
    }

    //初始化视图
    private void initView() {

        mImageView1 = (ImageView) findViewById(R.id.ks_page10_iv1);
        mTextView1 = (TextView) findViewById(R.id.ks_page10_tv1);
        mTextView2 = (TextView) findViewById(R.id.ks_page10_tv2);
        mTextView3 = (TextView) findViewById(R.id.ks_page10_tv3);    //原价
        mTextView4 = (TextView) findViewById(R.id.ks_page10_tv4);
        mPrice = (TextView) findViewById(R.id.ks_page10_price);    //原价的“￥”
        mLine = findViewById(R.id.ks_page10_view);   //原价的“—”
        mAdd = findViewById(R.id.ks_page10_add);   //加入购物车

        mSwRefresh = (MySwipeRefreshLayout) findViewById(R.id.ks_page10_swRefresh);   //刷新控件
        mListView = (ListView) findViewById(R.id.ks_page10_listView);   //推荐内容
        mBtn1 = findViewById(R.id.ks_page10_bottom_btn1);   //返回按钮
        mBtn2 = findViewById(R.id.ks_page10_bottom_btn2);   //购物车
        mBtn3 = findViewById(R.id.ks_page10_bottom_btn3);   //咨询客服
        mBadgeView = (BadgeView) findViewById(R.id.ks_page10_badgeView);   //数字标签

        init();
    }

    //视图操作
    private void init() {
        Intent intent = getIntent();
        String image = intent.getExtras().getString("image");
        String name = intent.getExtras().getString("name");
        String content = intent.getExtras().getString("content");
        String price1 = intent.getExtras().getString("price1");
        String price2 = intent.getExtras().getString("price2");

        ImageLoaderHelper.getInstance().loadImage(image, mImageView1);
        mTextView1.setText(name);
        mTextView2.setText(content);
        mTextView3.setText(price1);
        mTextView4.setText(price2);

        if (mTextView3.getText().toString().equals("")) {
            mPrice.setVisibility(View.GONE);
            mLine.setVisibility(View.GONE);
        }

        mAdd.setOnClickListener(this);
        mBadgeView.setTextSize(11);
        mBadgeView.setText("0");
        mBadgeView.setTypeface(Typeface.MONOSPACE);

        initSwRefresh();
        mListView.setOnItemClickListener(this);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
    }

    private void initSwRefresh() {
        mSwRefresh.setProgressViewEndTarget(false, 0);
        mSwRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                mSwRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            mData.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1nLrvJFXXXXXFXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                                    "全国执业药师资格考试无纸化考试专用系统", "通关秘籍 2016最新版", "40.00", "30.00"));
                        }
                        mSwRefresh.setLoading(false);
                        runOnUiThread(new Runnable() {
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

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page10_add:   //加入购物车
                number = mBadgeView.getText().toString();
                int i = Integer.parseInt(number) + 1;
                number = String.valueOf(i);
                mBadgeView.setText(number);
                break;
            case R.id.ks_page10_bottom_btn1:
                finish();
                break;
            case R.id.ks_page10_bottom_btn2:
                //Toast.makeText(this, "跳转至购物车页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Page10BEDetailsActivity.this, Page14BuyCarActivity.class));
                break;
            case R.id.ks_page10_bottom_btn3:
                //todo 咨询的点击事件
                if (mPopupWindow == null) {
                    ConsultClickListener paramOnClickListener = new ConsultClickListener();
                    mPopupWindow = new MyPopupWindowPage10(this, paramOnClickListener, DisplayUtil.dip2px(this, 120),
                            DisplayUtil.dip2px(this, 81));
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
                //mPopupWindow.showAsDropDown(mBtn3, 0, 30);
                mPopupWindow.showUp2(mBtn3);
                //如果窗口存在，则更新
                mPopupWindow.update();
                break;
        }

    }

    //咨询的电话咨询和qq咨询的点击事件
    public class ConsultClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ks_page2_pop_phone:
                    //Toast.makeText(Page10BEDetailsActivity.this, "电话咨询", Toast.LENGTH_SHORT).show();
                    //联系客服(调取本机跳转到拨号页面)
                    Uri uri = Uri.parse("tel:10086");
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                    break;
                case R.id.ks_page2_pop_qq:
                    Toast.makeText(Page10BEDetailsActivity.this, "qq咨询", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    //ListView的Item点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*Intent intent = new Intent(this, Page10BEDetailsActivity.class);
        intent.putExtra("image", mData.get(position).getImageUrl());
        intent.putExtra("name", mData.get(position).getTitle());
        intent.putExtra("content", mData.get(position).getContent());
        intent.putExtra("price1", mData.get(position).getPrice1());
        intent.putExtra("price2", mData.get(position).getPrice2());

        startActivity(intent);*/
    }

    //判断当前是否有网络连接
    private boolean isConn(Context context) {
        if (context != null) {
            ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo info = conManager.getActiveNetworkInfo();
            if (info == null) {
                Toast.makeText(this, "当前没有网络连接", Toast.LENGTH_SHORT).show();
            } else {
                return info.isAvailable();
            }
        }
        return false;
    }

}
