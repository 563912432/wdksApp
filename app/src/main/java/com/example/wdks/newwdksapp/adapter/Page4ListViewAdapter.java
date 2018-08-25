package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.MyExam;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 * 会员中心的listView的适配器
 */

public class Page4ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<MyExam> mData;

    public Page4ListViewAdapter(Context context, List<MyExam> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page4_member_listview, null);
            viewHolder.mImageView1 = (ImageView) convertView.findViewById(R.id.ks_page4_listView_iv1);
            viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.ks_page4_listView_tv1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoaderHelper.getInstance().loadImage(mData.get(position).getImageUrl(), viewHolder.mImageView1);
        viewHolder.mTextView1.setText(mData.get(position).getName());

        return convertView;
    }

    private class ViewHolder {
        private ImageView mImageView1;
        private TextView mTextView1;
    }
}
