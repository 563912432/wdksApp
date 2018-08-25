package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.MyAsk;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31 0031.
 * 我的答疑页面的单选页面的listView适配器
 */

public class Page37MADetailsOneListViewAdapter extends BaseAdapter {

    private Context context;
    private List<MyAsk> mData;

    public Page37MADetailsOneListViewAdapter(Context context, List<MyAsk> mData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page37_ma_details_one_listview, null);
            viewHolder.mAskTitle = (TextView) convertView.findViewById(R.id.ks_page37_ma_details_one_listView_tv1);   //提问标题
            viewHolder.mAskTime = (TextView) convertView.findViewById(R.id.ks_page37_ma_details_one_listView_tv2);   //提问时间
            viewHolder.mAnswerTitle = (TextView) convertView.findViewById(R.id.ks_page37_ma_details_one_listView_tv3);   //回答标题
            viewHolder.mAnswerTime = (TextView) convertView.findViewById(R.id.ks_page37_ma_details_one_listView_tv4);   //回答时间

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mAskTitle.setText(mData.get(position).getAskTitle());
        viewHolder.mAskTime.setText(mData.get(position).getAskTime());
        viewHolder.mAnswerTitle.setText(mData.get(position).getAnswerTitle());
        viewHolder.mAnswerTime.setText(mData.get(position).getAnswerTime());

        return convertView;
    }

    private class ViewHolder {
        private TextView mAskTitle, mAskTime, mAnswerTitle, mAnswerTime;
    }
}
