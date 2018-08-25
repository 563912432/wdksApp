package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.Order;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 * 确认订单的listView的适配器
 */

public class Page15ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Order> mData;

    public Page15ListViewAdapter(Context context, List<Order> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? 0 : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData == null ? 0 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.page15_confirm_order_listview, null);
            viewHolder.mImageView1 = (ImageView) convertView.findViewById(R.id.ks_page15_listView_iv1);   //图片
            viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.ks_page15_listView_tv1);   //标题
            viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.ks_page15_listView_tv2);   //数量
            viewHolder.mTextView3 = (TextView) convertView.findViewById(R.id.ks_page15_listView_tv3);   //价格小计

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageLoaderHelper.getInstance().loadImage(mData.get(position).getImageUrl(), viewHolder.mImageView1);
        viewHolder.mTextView1.setText(mData.get(position).getName());
        viewHolder.mTextView2.setText(mData.get(position).getNumber());
        viewHolder.mTextView3.setText(mData.get(position).getPrice());

        return convertView;
    }

    private class ViewHolder {
        private ImageView mImageView1;
        private TextView mTextView1, mTextView2, mTextView3;
    }
}
