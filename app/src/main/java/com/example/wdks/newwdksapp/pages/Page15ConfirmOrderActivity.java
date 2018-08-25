package com.example.wdks.newwdksapp.pages;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page15ListViewAdapter;
import com.example.wdks.newwdksapp.data.Order;
import com.example.wdks.newwdksapp.tools.MyInitWindowGray;
import com.example.wdks.newwdksapp.tools.MyProgressDialogConn;
import com.example.wdks.newwdksapp.volley.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/*
* 确认订单页面
* */
public class Page15ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private View mBack, mAddress, view1, mEmpty, mNotEmpty;
    private Dialog mDialog;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6,
            mTextView7, mTextView8, mTextView9;
    private ListView mListView;
    private List<Order> mOrder;
    private Page15ListViewAdapter mListViewAdapter;
    private Button mButton1;
    private JsonObjectRequest mRequest;
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";
    private double d_goodsPrice, d_freight, d_sumNumber, d_allPrice;
    private String s_goodsPrice, s_freight, s_sumNumber, s_allPrice;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mAlertDialog;
    private static final int M = 100;
    private static final int N = 101;

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case M:
                    DecimalFormat df = new DecimalFormat("#####0.00");
                    /*
                    * 商品价格
                    * */
                    s_goodsPrice = df.format(d_goodsPrice);
                    if (mTextView4 != null) {
                        mTextView4.setText(s_goodsPrice);
                    }
                    //运费
                    if (mTextView5 != null) {
                        mTextView5.setText("15.00");
                    }
                    //总金额
                    d_freight = 15.00;
                    d_allPrice = d_goodsPrice + d_freight;
                    s_allPrice = df.format(d_allPrice);
                    if (mTextView8 != null) {
                        mTextView8.setText(s_allPrice);
                    }
                    break;
                case N:
                    if (mTextView7 != null) {
                        mTextView7.setText(s_sumNumber);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page15_confirm_order);
        MyInitWindowGray.initWindow(this);   //api 19后自定义状态栏颜色  浅灰色
        initData();
        initView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MySingleton.getInstance(this).cancelPendingRequests("page15_listView");
    }

    //初始化数据
    private void initData() {
        isConn(this);

        mOrder = new ArrayList<Order>();
        mRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    response = jsonArray.getJSONObject(1);
                    mOrder.add(new Order("https://img.alicdn.com/bao/uploaded/i3/TB1vF1vJVXXXXXaXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "中级会计职称", response.getString("id"), "30.00"));
                    mOrder.add(new Order("https://img.alicdn.com/bao/uploaded/i4/TB1EmbaLVXXXXXmaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "2016年初级会计职称无纸化考试系统", "4", "15.00"));
                    mOrder.add(new Order("https://img.alicdn.com/bao/uploaded/i2/TB1m.VfJVXXXXcHXpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "全国银行从业资格无纸化考试专用系统", "1", "20.00"));
                    mOrder.add(new Order("https://img.alicdn.com/bao/uploaded/i2/TB1J.ppKVXXXXc8XFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"
                            , "临床执业助理医师", "1", "10.00"));
                    mOrder.add(new Order("https://img.alicdn.com/bao/uploaded/i3/TB1vF1vJVXXXXXaXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                            "中级会计职称", "1", "30.00"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //商品金额的计算
                d_goodsPrice = 0;
                for (int i = 0; i < mOrder.size(); i++) {
                    String s = mOrder.get(i).getPrice();
                    double f = Double.parseDouble(s);
                    d_goodsPrice = d_goodsPrice + f;
                }
                mHandle.obtainMessage(M, d_goodsPrice).sendToTarget();

                //获取商品的件数
                s_sumNumber = String.valueOf(mOrder.size());
                mHandle.obtainMessage(N, s_sumNumber).sendToTarget();

                mDialog.dismiss();
                mListViewAdapter = new Page15ListViewAdapter(Page15ConfirmOrderActivity.this, mOrder);
                if (mListView != null) {
                    mListView.setAdapter(mListViewAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequest.setTag("page15_listView");
        MySingleton.getInstance(this).addToRequestQueue(mRequest);
    }

    //初始化视图
    private void initView() {
        //先弹出对话框
        MyProgressDialogConn dialog = new MyProgressDialogConn();
        mDialog = dialog.createLoader(this);
        mDialog.show();

        mBack = findViewById(R.id.ks_page15_back);   //返回按钮
        mAddress = findViewById(R.id.ks_page15_address);   //更改地址
        mNotEmpty = findViewById(R.id.ks_page15_line_notEmpty);   //地址不为空显示的界面
        mEmpty = findViewById(R.id.ks_page15_line_empty);   //地址为空显示的界面
        mTextView1 = (TextView) findViewById(R.id.ks_page15_tv1);   //姓名
        mTextView2 = (TextView) findViewById(R.id.ks_page15_tv2);   //联系方式
        mTextView3 = (TextView) findViewById(R.id.ks_page15_tv3);   //联系地区
        mTextView9 = (TextView) findViewById(R.id.ks_page15_tv9);   //联系详细地址
        mTextView4 = (TextView) findViewById(R.id.ks_page15_tv4);   //商品金额
        mTextView5 = (TextView) findViewById(R.id.ks_page15_tv5);   //运费
        mTextView6 = (TextView) findViewById(R.id.ks_page15_tv6);   //配送方式
        mTextView7 = (TextView) findViewById(R.id.ks_page15_tv7);   //商品总数
        mTextView8 = (TextView) findViewById(R.id.ks_page15_tv8);   //总金额
        mButton1 = (Button) findViewById(R.id.ks_page15_btn1);   //立即购买

        mListView = (ListView) findViewById(R.id.ks_page15_listView);   //商品信息

        init();
    }

    //视图操作
    private void init() {
        //地址操作
        String name = mTextView1.getText().toString();   //姓名
        String tel = mTextView2.getText().toString();   //联系方式
        String district = mTextView3.getText().toString();   //联系地区
        String address = mTextView9.getText().toString();   //联系详细地址

        if (name.equals("") && tel.equals("") && district.equals("") && address.equals("")) {
            mEmpty.setVisibility(View.VISIBLE);
            mNotEmpty.setVisibility(View.GONE);
        } else {
            mNotEmpty.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);
        }

        mBack.setOnClickListener(this);
        mAddress.setOnClickListener(this);
        mButton1.setOnClickListener(this);
    }


    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page15_back:   //返回按钮
                finish();
                break;
            case R.id.ks_page15_address:   //跳转页面，选择地址完成之后带地址内容的返回(带返回值的页面跳转)
                Intent intent = new Intent(Page15ConfirmOrderActivity.this, Page16AddressActivity.class);
                startActivityForResult(intent, 1);


                break;
            case R.id.ks_page15_btn1:
                /*
                * 支付操作(跳出对话框选择支付方式)
                * 首先要判断收货地址是否为空
                * */
                if (mEmpty.getVisibility() == View.VISIBLE) {
                    Toast.makeText(this, "请选择收货地址!", Toast.LENGTH_SHORT).show();
                } else {
                    //对话框支付
                    view1 = LayoutInflater.from(this).inflate(R.layout.dialog_payoff, null);
                    mBuilder = new AlertDialog.Builder(Page15ConfirmOrderActivity.this);
                    mBuilder.setView(view1);
                    mAlertDialog = mBuilder.show();

                    View mLine1 = view1.findViewById(R.id.ks_page18_dialog_line1);
                    View mLine2 = view1.findViewById(R.id.ks_page18_dialog_line2);
                    View mLine3 = view1.findViewById(R.id.ks_page18_dialog_line3);

                    mLine1.setOnClickListener(this);
                    mLine2.setOnClickListener(this);
                    mLine3.setOnClickListener(this);
                }
                break;
            case R.id.ks_page18_dialog_line1:
                Toast.makeText(this, "微信支付", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Page15ConfirmOrderActivity.this, Page47PaySuccessActivity.class));
                break;
            case R.id.ks_page18_dialog_line2:
                Toast.makeText(this, "支付宝支付", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Page15ConfirmOrderActivity.this, Page48PayFailActivity.class));
                break;
            case R.id.ks_page18_dialog_line3:
                Toast.makeText(this, "余额支付", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /**
     * 设置跳转  接受返回数据
     *
     * @param requestCode 请求码
     * @param resultCode  返回码
     * @param data        返回数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  如果请求码与返回码等于预期设置的值  则进行后续操作
        if (requestCode == 1 && resultCode == 2) {
            //获取返回的数据
            String backName = data.getExtras().getString("backName");
            String backTel = data.getExtras().getString("backTel");
            String backDistrict = data.getExtras().getString("backDistrict");
            String backAddress = data.getExtras().getString("backAddress");

            mTextView1.setText(backName);
            mTextView2.setText(backTel);
            mTextView3.setText(backDistrict);
            mTextView9.setText(backAddress);

            //显示添加的地址
            mNotEmpty.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);
        }
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
