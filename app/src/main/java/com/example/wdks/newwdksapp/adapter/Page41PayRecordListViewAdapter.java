package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.PayRecord;
import com.example.wdks.newwdksapp.data.Recharge;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1 0001.
 * 缴费记录的ListView适配器
 */

public class Page41PayRecordListViewAdapter extends BaseAdapter {
    private Context context;
    private List<PayRecord> mData;

    public Page41PayRecordListViewAdapter(Context context, List<PayRecord> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.page41_pay_record_listview, null);
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.ks_page41_pay_record_listView_tv1);   //购买标题
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.ks_page41_pay_record_listView_tv2);   //购买时间
            viewHolder.mNumber = (TextView) convertView.findViewById(R.id.ks_page41_pay_record_listView_tv3);   //购买单号
            viewHolder.mMoney = (TextView) convertView.findViewById(R.id.ks_page41_pay_record_listView_tv4);   //购买金额

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTitle.setText(mData.get(position).getTitle());
        viewHolder.mTime.setText(mData.get(position).getTime());
        viewHolder.mNumber.setText(mData.get(position).getNumber());
        viewHolder.mMoney.setText(mData.get(position).getMoney());

        return convertView;
    }

    private class ViewHolder {
        private TextView mTitle, mTime, mNumber, mMoney;
    }
}
