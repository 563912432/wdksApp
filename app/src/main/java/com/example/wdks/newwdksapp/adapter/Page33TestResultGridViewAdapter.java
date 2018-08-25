package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.CoursesData;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25 0025.
 * 预测试卷查看结果页面的gridView的适配器
 */

public class Page33TestResultGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<CoursesData> mData;

    public Page33TestResultGridViewAdapter(Context context, List<CoursesData> mData) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.page33_yc_test_result_gridview, null);
            viewHolder.mButton = (Button) convertView.findViewById(R.id.ks_page33_yc_test_result_gridView_btn1);
            if (position == 2 || position == 10 || position == 12 || position == 17) {
                viewHolder.mButton.setBackgroundResource(R.drawable.ico_circle_red);
            }

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mButton.setText(mData.get(position).getName());

        return convertView;
    }

    private class ViewHolder {
        private Button mButton;
    }
}
