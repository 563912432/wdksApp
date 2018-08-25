package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.HomeVideoData;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28 0028.
 * 首页免费视频RecycleView的适配器
 */

public class Page1VideoRecycleViewAdapter extends RecyclerView.Adapter<Page1VideoRecycleViewAdapter.ViewHolder> {
    //ItemClick的回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private Context context;
    private List<HomeVideoData> mData;

    public Page1VideoRecycleViewAdapter(Context context, List<HomeVideoData> mData) {
        this.context = context;
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        ImageView mImageView1;
        TextView mTextView1;
        TextView mTextView2;
        TextView mTextView3;
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        } else {
            return mData.size();
        }
    }

    //创建ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.page1_home_video_recycleview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mImageView1 = (ImageView) view.findViewById(R.id.ks_page1_video_recycleView_iv1);
        viewHolder.mTextView1 = (TextView) view.findViewById(R.id.ks_page1_video_recycleView_tv1);
        viewHolder.mTextView2 = (TextView) view.findViewById(R.id.ks_page1_video_recycleView_tv2);
        viewHolder.mTextView3 = (TextView) view.findViewById(R.id.ks_page1_video_recycleView_tv3);

        return viewHolder;
    }

    //绑定值
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ImageLoaderHelper.getInstance().loadImage(mData.get(position).getImageUrl(), holder.mImageView1);

        holder.mTextView1.setText(mData.get(position).getTitle());
        holder.mTextView2.setText(mData.get(position).getTeacher());
        holder.mTextView3.setText(mData.get(position).getPrice());

        //如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

}
