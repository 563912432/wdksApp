package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.Address;
import com.example.wdks.newwdksapp.data.Chapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0015.
 * 收货地址管理的ListView的适配器
 */

public class Page23ChapterListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Chapter> mData;
    private MyClickListener mListener;

    public Page23ChapterListViewAdapter(Context context, List<Chapter> mData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page23_chapter_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.ks_page23_listView_tv1);   //标题
            viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.ks_page23_listView_tv2);   //已经做了
            viewHolder.mTextView3 = (TextView) convertView.findViewById(R.id.ks_page23_listView_tv3);   //总共
            viewHolder.mTextView4 = (TextView) convertView.findViewById(R.id.ks_page23_listView_tv4);   //收藏
            viewHolder.mTextView5 = (TextView) convertView.findViewById(R.id.ks_page23_listView_tv5);   //错题

            viewHolder.mLine1 = (LinearLayout) convertView.findViewById(R.id.ks_page23_listView_line1);   //题目
            viewHolder.mLine2 = (LinearLayout) convertView.findViewById(R.id.ks_page23_listView_line2);   //收藏
            viewHolder.mLine3 = (LinearLayout) convertView.findViewById(R.id.ks_page23_listView_line3);   //错题
            viewHolder.mLine4 = (LinearLayout) convertView.findViewById(R.id.ks_page23_listView_line4);   //错题
            viewHolder.mLine5 = (LinearLayout) convertView.findViewById(R.id.ks_page23_listView_line5);   //错题

            viewHolder.mLine1.setOnClickListener(mOnClickListener);
            viewHolder.mLine2.setOnClickListener(mOnClickListener);
            viewHolder.mLine3.setOnClickListener(mOnClickListener);
            viewHolder.mLine4.setOnClickListener(mOnClickListener);
            viewHolder.mLine5.setOnClickListener(mOnClickListener);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTextView1.setText(mData.get(position).getTitle());
        viewHolder.mTextView2.setText(mData.get(position).getDone());
        viewHolder.mTextView3.setText(mData.get(position).getAll());
        viewHolder.mTextView4.setText(mData.get(position).getCollect());
        viewHolder.mTextView5.setText(mData.get(position).getWrong());

        viewHolder.mLine1.setTag(position);
        viewHolder.mLine2.setTag(position);
        viewHolder.mLine3.setTag(position);
        viewHolder.mLine4.setTag(position);
        viewHolder.mLine5.setTag(position);

        return convertView;
    }

    //创建内部类
    private class ViewHolder {
        private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5;
        private LinearLayout mLine1, mLine2, mLine3, mLine4, mLine5;
    }

    //实现点击事件
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = (int) v.getTag();
                switch (v.getId()) {
                    case R.id.ks_page23_listView_line1:
                        mListener.onExam(Page23ChapterListViewAdapter.this, v, position);
                        break;
                    case R.id.ks_page23_listView_line2:
                        mListener.onCollect(Page23ChapterListViewAdapter.this, v, position);
                        break;
                    case R.id.ks_page23_listView_line3:
                        mListener.onWrong(Page23ChapterListViewAdapter.this, v, position);
                        break;
                    case R.id.ks_page23_listView_line4:
                        mListener.onClear(Page23ChapterListViewAdapter.this, v, position);
                        break;
                    case R.id.ks_page23_listView_line5:
                        mListener.onGoon(Page23ChapterListViewAdapter.this, v, position);
                        break;
                }
            }
        }
    };

    //自定义接口
    public interface MyClickListener {
        void onExam(BaseAdapter adapter, View view, int position);

        void onCollect(BaseAdapter adapter, View view, int position);

        void onWrong(BaseAdapter adapter, View view, int position);

        void onClear(BaseAdapter adapter, View view, int position);

        void onGoon(BaseAdapter adapter, View view, int position);

    }
}
