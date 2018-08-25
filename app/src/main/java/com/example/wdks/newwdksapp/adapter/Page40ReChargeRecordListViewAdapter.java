package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.Recharge;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1 0001.
 * 订单记录的ListView适配器
 */

public class Page40ReChargeRecordListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Recharge> mData;

    public Page40ReChargeRecordListViewAdapter(Context context, List<Recharge> mData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page40_recharge_record_listview, null);
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.ks_page40_recharge_record_listView_tv1);   //充值时间
            viewHolder.mMoney = (TextView) convertView.findViewById(R.id.ks_page40_recharge_record_listView_tv2);   //充值金额
            viewHolder.mNumber = (TextView) convertView.findViewById(R.id.ks_page40_recharge_record_listView_tv3);   //充值卡号

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTime.setText(mData.get(position).getTime());
        viewHolder.mMoney.setText(mData.get(position).getMoney());
        viewHolder.mNumber.setText(mData.get(position).getNumber());

        return convertView;
    }

    private class ViewHolder {
        private TextView mTime, mMoney, mNumber;
    }
}
