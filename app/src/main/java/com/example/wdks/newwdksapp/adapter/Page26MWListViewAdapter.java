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
 * 我的错题页面的ListView的适配器
 */

public class Page26MWListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Chapter> mData;

    public Page26MWListViewAdapter(Context context, List<Chapter> mData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page26_my_wrong_listview, null);
            viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.ks_page26_listView_tv1);   //title
            viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.ks_page26_listView_tv3);   //题目总数


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView1.setText(mData.get(position).getTitle());
        viewHolder.mTextView2.setText(mData.get(position).getAll());

        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView1, mTextView2;
    }
}
