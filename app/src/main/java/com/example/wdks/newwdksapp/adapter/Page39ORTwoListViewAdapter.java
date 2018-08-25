package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.OrderRecord;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31 0031.
 * 订单记录的listView的适配器
 */

public class Page39ORTwoListViewAdapter extends BaseAdapter {
    private Context context;
    private List<OrderRecord> mData;
    private MyClickListener mListener;

    public Page39ORTwoListViewAdapter(Context context, List<OrderRecord> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void setOnClickListener(MyClickListener mListener) {
        this.mListener = mListener;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page39_order_record_two_listview, null);
            viewHolder.mOrderNum = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv1);   //订单号
            viewHolder.mOrderState = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv2);   //订单状态
            viewHolder.mImageView1 = (ImageView) convertView.findViewById(R.id.ks_page39_two_listView_iv1);   //图片
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv3);   //标题
            viewHolder.mPrice = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv4);   //单价
            viewHolder.mNumbers = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv5);   //数量
            viewHolder.mContent = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv6);   //备注
            viewHolder.mAddAll = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv7);   //总价
            viewHolder.mFreight = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv8);   //运费
            viewHolder.mPayOff = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv9);   //付款
            viewHolder.mDelete = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv10);   //删除订单
            viewHolder.mContact = (TextView) convertView.findViewById(R.id.ks_page39_two_listView_tv11);   //联系客服

            viewHolder.mPayOff.setOnClickListener(mOnClickListener);
            viewHolder.mDelete.setOnClickListener(mOnClickListener);
            viewHolder.mContact.setOnClickListener(mOnClickListener);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // viewHolder.mOrderNum.setText(mData.get(position).getOrderNum());
        viewHolder.mOrderNum.setText(mData.get(position).getOrderNum());
        viewHolder.mOrderState.setText(mData.get(position).getOrderState());
        viewHolder.mTitle.setText(mData.get(position).getTitle());
        viewHolder.mPrice.setText(mData.get(position).getPrice());
        viewHolder.mNumbers.setText(mData.get(position).getNumbers());
        viewHolder.mContent.setText(mData.get(position).getContent());
        viewHolder.mAddAll.setText(mData.get(position).getAddAll());
        viewHolder.mFreight.setText(mData.get(position).getFreight());
        viewHolder.mPayOff.setText(mData.get(position).getPay());
        viewHolder.mDelete.setText(mData.get(position).getDelete());
        viewHolder.mContact.setText(mData.get(position).getContact());
        ImageLoaderHelper.getInstance().loadImage(mData.get(position).getImageUrl(), viewHolder.mImageView1);

        if (viewHolder.mOrderState.getText().toString().equals("交易成功")) {
            viewHolder.mPayOff.setVisibility(View.GONE);
            viewHolder.mOrderState.setTextColor(Color.parseColor("#008000"));
        } else {
            viewHolder.mOrderState.setTextColor(Color.parseColor("#FF0000"));
        }

        viewHolder.mPayOff.setTag(position);
        viewHolder.mDelete.setTag(position);
        viewHolder.mContact.setTag(position);

        return convertView;
    }

    private class ViewHolder {
        private TextView mOrderNum, mOrderState, mTitle, mPrice, mNumbers, mContent, mAddAll,
                mFreight, mPayOff, mDelete, mContact;
        private ImageView mImageView1;
    }

    //实现点击事件
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = (int) v.getTag();
                switch (v.getId()) {
                    case R.id.ks_page39_two_listView_tv9:   //付款
                        mListener.onPayOff(Page39ORTwoListViewAdapter.this, v, position);
                        break;
                    case R.id.ks_page39_two_listView_tv10:   //删除订单
                        mListener.onDelete(Page39ORTwoListViewAdapter.this, v, position);
                        break;
                    case R.id.ks_page39_two_listView_tv11:   //联系客服
                        mListener.onContact(Page39ORTwoListViewAdapter.this, v, position);
                        break;
                }
            }
        }
    };

    //自定义接口
    public interface MyClickListener {
        void onPayOff(BaseAdapter baseAdapter, View view, int position);

        void onDelete(BaseAdapter baseAdapter, View view, int position);

        void onContact(BaseAdapter baseAdapter, View view, int position);
    }
}
