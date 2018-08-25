package com.example.wdks.newwdksapp.pages;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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
import com.example.wdks.newwdksapp.adapter.Page9FreeVideoListViewAdapter;
import com.example.wdks.newwdksapp.data.HomeVideoData;
import com.example.wdks.newwdksapp.tools.MyInitWindow;
import com.example.wdks.newwdksapp.tools.MyProgressDialogConn;
import com.example.wdks.newwdksapp.tools.MySwipeRefreshLayout;
import com.example.wdks.newwdksapp.volley.MySingleton;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Page9FreeVideoActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private LinearLayout mBack;
    private TextView mTextView1;
    private List<HomeVideoData> mData;
    private ListView mListView;
    private MySwipeRefreshLayout mSwRefresh;
    private Page9FreeVideoListViewAdapter mPage9ListViewAdapter;
    private Dialog mDialog;
    private JsonObjectRequest mRequest;
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page9_free_video);
        MyInitWindow.initWindow(this);  //自定义状态栏颜色
        initData();
        initView();

    }

    @Override
    protected void onStop() {
        super.onStop();
        MySingleton.getInstance(this).cancelPendingRequests("page9_video");
    }

    //初始化数据
    private void initData() {
        isConn(this);
        mData = new ArrayList<HomeVideoData>();
        mRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    jsonObject = jsonArray.getJSONObject(1);
                    mData.add(new HomeVideoData("http://a1.jikexueyuan.com/home/201604/11/6e01/570b17fe71605.jpg", "会计基础",
                            "某某某", "免费"));
                    mData.add(new HomeVideoData("http://a1.jikexueyuan.com/home/201605/10/bb41/5731437213380.jpg", "会计电算化",
                            jsonObject.getString("learner"), "免费"));
                    mData.add(new HomeVideoData("http://a1.jikexueyuan.com/home/201605/09/2497/572fedc688602.jpg", "财经法规与会计职业道德",
                            "某某某", "免费"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mPage9ListViewAdapter = new Page9FreeVideoListViewAdapter(Page9FreeVideoActivity.this, mData);
                mListView.setAdapter(mPage9ListViewAdapter);

                mDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequest.setTag("page9_video");
        MySingleton.getInstance(this).addToRequestQueue(mRequest);
    }

    //初始化视图
    private void initView() {
        //先弹出对话框
        MyProgressDialogConn dialog = new MyProgressDialogConn();
        mDialog = dialog.createLoader(this);
        mDialog.show();

        mBack = (LinearLayout) findViewById(R.id.ks_page9_back);   //后退按钮
        mTextView1 = (TextView) findViewById(R.id.ks_page9_tv1);   //中间标题

        mSwRefresh = (MySwipeRefreshLayout) findViewById(R.id.ks_page9_free_video_swRefresh);  //刷新控件
        mListView = (ListView) findViewById(R.id.ks_page9_free_video_listView); //内容显示


        init();
    }

    //视图操作
    private void init() {
        Intent intent = getIntent();
        String s = intent.getExtras().getString("name");
        mTextView1.setText(s);

        mBack.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
        initSwRefresh();

    }

    //刷新控件操作
    private void initSwRefresh() {
        mSwRefresh.setProgressViewEndTarget(false, 0);
     /*   mSwRefresh.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                mSwRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 200);
            }
        });*/
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page9_back:
                finish();
                break;
        }
    }

    //listView的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Page44VideoPlayActivity.class);
        intent.putExtra("title", mData.get(position).getTitle());
        startActivity(intent);
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
}
