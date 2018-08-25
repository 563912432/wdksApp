package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.Chapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/22 0022.
 * 预测试卷的listView的适配器
 */

public class Page24YCListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Chapter> mData;

    public Page24YCListViewAdapter(Context context, List<Chapter> mData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page24_yc_listview, null);
            viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.ks_page24_listView_tv1);   //title
            viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.ks_page24_listView_tv3);   //题目总数
            viewHolder.mTextView3 = (TextView) convertView.findViewById(R.id.ks_page24_listView_tv5);   //答题状态

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView1.setText(mData.get(position).getTitle());
        viewHolder.mTextView2.setText(mData.get(position).getAll());
        viewHolder.mTextView3.setText(mData.get(position).getIsFinish());

        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView1, mTextView2, mTextView3;
    }
}
