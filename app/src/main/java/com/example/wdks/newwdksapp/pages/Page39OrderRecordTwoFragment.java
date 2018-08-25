package com.example.wdks.newwdksapp.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page39ORTwoListViewAdapter;
import com.example.wdks.newwdksapp.data.OrderRecord;

import java.util.ArrayList;
import java.util.List;

/*
* 订单记录管理页面二
* */
public class Page39OrderRecordTwoFragment extends Fragment implements View.OnClickListener,
        Page39ORTwoListViewAdapter.MyClickListener {

    private View view, mEmpty;
    private List<OrderRecord> mData;
    private ListView mListView;
    private Page39ORTwoListViewAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page39_order_record_two, container, false);
            initView();
        }
        return view;
    }


    //初始化数据
    private void initData() {
        mData = new ArrayList<OrderRecord>();
        mData.add(new OrderRecord("00000000000", "待付款", "https://img.alicdn.com/bao/uploaded/i4/TB1EmbaLVXXXXXmaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                "2016年初级会计职称无纸化考试系统", "20.00", "1", "2017最新版", "25.00", "5.00", "付款", "删除订单", "联系客服"));
        mData.add(new OrderRecord("00000000000", "交易成功", "https://img.alicdn.com/bao/uploaded/i3/TB1vF1vJVXXXXXaXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                "中级会计职称", "20.00", "1", "2017最新版", "25.00", "5.00", null, "删除订单", "联系客服"));
        mData.add(new OrderRecord("00000000000", "待付款", "https://img.alicdn.com/bao/uploaded/i4/TB1EmbaLVXXXXXmaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                "2016年初级会计职称无纸化考试系统", "20.00", "1", "2017最新版", "25.00", "5.00", "付款", "删除订单", "联系客服"));
    }

    //初始化视图
    private void initView() {
        //mListView = (ListView) view.findViewById(R.id.ks_page39_two_listView);
        mListView = (ListView) view.findViewById(R.id.ks_page39_two_listView);   //主要内容
        mAdapter = new Page39ORTwoListViewAdapter(getActivity(), mData);
        mListView.setAdapter(mAdapter);
        mEmpty = view.findViewById(R.id.ks_page39_two_empty);   //无订单记录的提示

        if (mData.size() == 0) {
            mListView.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        }

        init();
    }

    //视图操作
    private void init() {
        mAdapter.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page18_dialog_line1:
                Toast.makeText(getActivity(), "微信支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page18_dialog_line2:
                Toast.makeText(getActivity(), "支付宝支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page18_dialog_line3:
                Toast.makeText(getActivity(), "余额支付", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    //付款
    @Override
    public void onPayOff(BaseAdapter baseAdapter, View view, int position) {
        //Toast.makeText(this, "付款操作", Toast.LENGTH_SHORT).show();
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_payoff, null);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setView(view1);
        mBuilder.show();

        View mLine1 = view1.findViewById(R.id.ks_page18_dialog_line1);
        View mLine2 = view1.findViewById(R.id.ks_page18_dialog_line2);
        View mLine3 = view1.findViewById(R.id.ks_page18_dialog_line3);

        mLine1.setOnClickListener(this);
        mLine2.setOnClickListener(this);
        mLine3.setOnClickListener(this);
    }

    //删除
    @Override
    public void onDelete(BaseAdapter baseAdapter, View view, int position) {
        //Toast.makeText(this, "删除订单操作", Toast.LENGTH_SHORT).show();
        mData.remove(position);
        mAdapter.notifyDataSetChanged();
        if (mData.size() == 0) {
            mListView.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        }
    }

    //联系客服
    @Override
    public void onContact(BaseAdapter baseAdapter, View view, int position) {
        Toast.makeText(getActivity(), "联系客服操作", Toast.LENGTH_SHORT).show();
    }
}
