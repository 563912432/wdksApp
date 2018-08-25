package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.pages.Page2ShopBookFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30 0030.
 * 商城页图书页面的大分类的recycleView的适配器
 */

public class Page2BookRecycleView1Adapter extends RecyclerView.Adapter<Page2BookRecycleView1Adapter.ViewHolder> {

    private OnItemClickListener mOnItemClickListener = null;

    private LayoutInflater mInflater;
    private List<CoursesData> mData;

    private List<Boolean> isClicks;//控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色

    public Page2BookRecycleView1Adapter(Context context, List<CoursesData> data) {
        mInflater = LayoutInflater.from(context);
        mData = data;
        isClicks = new ArrayList<>();
        isClicks.add(0,true);
        for (int i = 1; i < mData.size(); i++) {
            isClicks.add(false);
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = mInflater.inflate(R.layout.page2_shop_book_recycleview1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mTextView1 = (TextView) view.findViewById(R.id.ks_page2_book_recycleView1_tv1);
        return viewHolder;
    }

    //绑定ViewHolder
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mTextView1.setText(mData.get(position).getName());
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(holder.mTextView1);
        //设置字体颜色的改变
        if (isClicks.get(position)) {
            holder.mTextView1.setBackgroundColor(Color.parseColor("#00BFFF"));
            holder.mTextView1.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.mTextView1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.mTextView1.setTextColor(Color.parseColor("#494949"));
        }

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < isClicks.size(); i++) {
                        isClicks.set(i, false);     //默认为false
                    }
                    isClicks.set(position, true);   //点击时选中为true
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(holder.itemView, holder.mTextView1, position);
                }
            });
        }
    }

    //自定义ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView mTextView1;
    }

    //定义内部接口
    public interface OnItemClickListener {
        void onItemClick(View view, TextView textView, int position);
    }
}