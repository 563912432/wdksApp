package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 * 搜搜页面的搜索历史的listView的适配器
 */

public class Page6ListViewAdapter extends BaseAdapter {

    private Context context;
    private List searchRecordsList;
    private MyClickListener mListener;


    public Page6ListViewAdapter(Context context, List searchRecordsList) {
        this.context = context;
        this.searchRecordsList = searchRecordsList;
    }

    public void setOnClickListener(MyClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return searchRecordsList == null ? 0 : searchRecordsList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchRecordsList.size() == 0 ? null : searchRecordsList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page6_listview, null);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.ks_page6_listView_tv1);
            viewHolder.mImageView1 = (ImageView) convertView.findViewById(R.id.ks_page6_listView_iv1);

            viewHolder.mImageView2 = (ImageView) convertView.findViewById(R.id.ks_page6_listView_iv2);
            viewHolder.mRecord = (LinearLayout) convertView.findViewById(R.id.ks_page6_listView_record);

            viewHolder.mRecord.setOnClickListener(mOnClickListener);
            viewHolder.mImageView2.setOnClickListener(mOnClickListener);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String content = (String) searchRecordsList.get(position);
        viewHolder.mTextView.setText(content);
        ImageLoaderHelper.getInstance().loadImage("drawable://" + R.drawable.ico_history_record, viewHolder.mImageView1);
        ImageLoaderHelper.getInstance().loadImage("drawable://" + R.drawable.ico_delete_grey, viewHolder.mImageView2);

        //设置position
        viewHolder.mRecord.setTag(position);
        viewHolder.mImageView2.setTag(position);

        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private ImageView mImageView1, mImageView2;
        private LinearLayout mRecord;
    }

    public View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = (int) v.getTag();
                switch (v.getId()) {
                    case R.id.ks_page6_listView_record:
                        mListener.onClickToRecord(Page6ListViewAdapter.this, v, position);
                        break;
                    case R.id.ks_page6_listView_iv2:
                        mListener.onClickDeleteRecord(Page6ListViewAdapter.this, v, position);
                        break;
                }
            }
        }
    };

    //自定义内部接口，实现点击事件
    public interface MyClickListener {
        void onClickToRecord(BaseAdapter adapter, View view, int position);

        void onClickDeleteRecord(BaseAdapter adapter, View view, int position);
    }
}
