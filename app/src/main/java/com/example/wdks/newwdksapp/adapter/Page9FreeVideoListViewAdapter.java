package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.data.HomeVideoData;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0031.
 * 视频课程详情页的ListView适配器
 */
public class Page9FreeVideoListViewAdapter extends BaseAdapter {
    private Context context;
    private List<HomeVideoData> data;

    public Page9FreeVideoListViewAdapter(Context context, List<HomeVideoData> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //2- 定义ViewHolder
        ViewHolder viewHolder;
        if (convertView == null) {
            //3- 实例化ViewHolder 并且适配ViewHolder中的参数
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.page9_free_video_listview, null);


            viewHolder.image = (ImageView) convertView.findViewById(R.id.ks_page9_free_video_listView_iv1);
            viewHolder.title = (TextView) convertView.findViewById(R.id.ks_page9_free_video_listView_tv1);
            viewHolder.teacher = (TextView) convertView.findViewById(R.id.ks_page9_free_video_listView_tv2);
            viewHolder.price = (TextView) convertView.findViewById(R.id.ks_page9_free_video_listView_tv3);

            //4- 通过setTag将ViewHolder与view相关联
            convertView.setTag(viewHolder);
        } else {
            //5- 当不为空的时候直接通过getTag方法找到控件
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //6- 加载数据
        HomeVideoData cor = data.get(position);
        ImageLoaderHelper.getInstance().loadImage(cor.getImageUrl(), viewHolder.image);
        viewHolder.title.setText(cor.getTitle());
        viewHolder.teacher.setText(cor.getTeacher());
        viewHolder.price.setText(cor.getPrice());
        
        return convertView;
    }

    //1- 创建内部类ViewHolder
    private class ViewHolder {
        private ImageView image;
        private TextView title, teacher, price;

    }

}