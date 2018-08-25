package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.Chapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/22 0022.
 * 我的笔记页面的ListView的适配器
 */

public class Page28MNListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Chapter> mData;
    private MyClickListener mListener;

    public Page28MNListViewAdapter(Context context, List<Chapter> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void setOnClickListener(MyClickListener listener) {
        mListener = listener;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page28_my_note_listview, null);
            viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.ks_page28_listView_tv1);   //title
            viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.ks_page28_listView_tv3);   //第几题

            viewHolder.mImageView1 = (ImageView) convertView.findViewById(R.id.ks_page28_listView_iv1);   //删除按钮
            viewHolder.mImageView2 = (ImageView) convertView.findViewById(R.id.ks_page28_listView_iv2);   //查看按钮

            viewHolder.mImageView1.setOnClickListener(mOnClickListener);
            viewHolder.mImageView2.setOnClickListener(mOnClickListener);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView1.setText(mData.get(position).getTitle());
        viewHolder.mTextView2.setText(mData.get(position).getAll());

        viewHolder.mImageView1.setTag(position);
        viewHolder.mImageView2.setTag(position);

        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView1, mTextView2;
        private ImageView mImageView1, mImageView2;
    }

    //实现点击事件
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = (int) v.getTag();
                switch (v.getId()) {
                    case R.id.ks_page28_listView_iv1:
                        mListener.onDelete(Page28MNListViewAdapter.this, v, position);
                        break;
                    case R.id.ks_page28_listView_iv2:
                        mListener.onWatch(Page28MNListViewAdapter.this, v, position);
                        break;
                }
            }
        }
    };

    //自定义接口
    public interface MyClickListener {
        void onDelete(BaseAdapter baseAdapter, View view, int position);

        void onWatch(BaseAdapter baseAdapter, View view, int position);
    }

}
