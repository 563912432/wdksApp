package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.CoursesData;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27 0027.
 * 计算分析题的二层ListView的适配器
 */

public class Page29TestFiveListView2Adapter extends BaseAdapter {
    private Context context;
    private List<CoursesData> mData;

    public Page29TestFiveListView2Adapter(Context context, List<CoursesData> mData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page29_chapter_test_five_spinner2_listview, null);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.ks_page29_test_five_spinner2_listView_tv);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTextView.setText(mData.get(position).getName());

        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
    }
}
