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
import com.example.wdks.newwdksapp.data.Address;
import com.example.wdks.newwdksapp.data.MyBuyCar;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0015.
 * 收货地址管理的ListView的适配器
 */

public class Page16AddressListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Address> mData;
    private MyClickListener mListener;
    private int mSelect = 0;

    public Page16AddressListViewAdapter(Context context, List<Address> mData) {
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
        return mData == null ? 0 : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.page16_address_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.ks_page16_listView_tv1);   //姓名
            viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.ks_page16_listView_tv2);   //电话
            viewHolder.mTextView3 = (TextView) convertView.findViewById(R.id.ks_page16_listView_tv3);   //地区
            viewHolder.mTextView4 = (TextView) convertView.findViewById(R.id.ks_page16_listView_tv4);   //地址

            viewHolder.mChose = (LinearLayout) convertView.findViewById(R.id.ks_page16_listView_line1);   //选择标志
            viewHolder.mEditor = (LinearLayout) convertView.findViewById(R.id.ks_page16_listView_line3);   //编辑
            viewHolder.mDelete = (LinearLayout) convertView.findViewById(R.id.ks_page16_listView_line2);   //删除

            viewHolder.mImageView1 = (ImageView) convertView.findViewById(R.id.ks_page16_listView_iv1);   //设置是否为默认的图标

            viewHolder.mChose.setOnClickListener(mOnClickListener);
            viewHolder.mEditor.setOnClickListener(mOnClickListener);
            viewHolder.mDelete.setOnClickListener(mOnClickListener);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTextView1.setText(mData.get(position).getName());
        viewHolder.mTextView2.setText(mData.get(position).getTel());
        viewHolder.mTextView3.setText(mData.get(position).getDistrict());
        viewHolder.mTextView4.setText(mData.get(position).getAddress());

        viewHolder.mChose.setTag(position);
        viewHolder.mEditor.setTag(position);
        viewHolder.mDelete.setTag(position);

        if (mSelect == position) {
            viewHolder.mImageView1.setBackgroundResource(R.drawable.ico_checked_on);
        } else {
            viewHolder.mImageView1.setBackgroundResource(R.drawable.ico_checked_off);
        }

        return convertView;
    }

    //创建内部类
    private class ViewHolder {
        private TextView mTextView1, mTextView2, mTextView3, mTextView4;
        private LinearLayout mChose, mEditor, mDelete;
        private ImageView mImageView1;
    }

    //实现点击事件
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = (int) v.getTag();
                switch (v.getId()) {
                    case R.id.ks_page16_listView_line1:
                        mListener.onChose(Page16AddressListViewAdapter.this, v, position);
                        break;
                    case R.id.ks_page16_listView_line3:
                        mListener.onEditor(Page16AddressListViewAdapter.this, v, position);
                        break;
                    case R.id.ks_page16_listView_line2:
                        mListener.onDelete(Page16AddressListViewAdapter.this, v, position);
                        break;
                }
            }
        }
    };

    public void changeSelected(int position) {
        if (position != mSelect) {
            mSelect = position;
            notifyDataSetChanged();
        }
    }

    //自定义接口
    public interface MyClickListener {
        void onChose(BaseAdapter adapter, View view, int position);

        void onEditor(BaseAdapter adapter, View view, int position);

        void onDelete(BaseAdapter adapter, View view, int position);
    }
}
