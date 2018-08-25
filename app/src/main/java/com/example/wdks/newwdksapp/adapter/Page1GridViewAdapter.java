package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;
import com.example.wdks.newwdksapp.tools.MyScreenHeight;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 * 首页GridView的适配器
 */
public class Page1GridViewAdapter extends BaseAdapter {
    private List<CoursesData> courses;
    private Context context;

    public Page1GridViewAdapter(Context context, List<CoursesData> courses) {
        this.context = context;
        this.courses = courses;

    }

    @Override
    public int getCount() {
        if (courses == null) {
            return 0;
        } else {
            return courses.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return courses.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page1_home_gridview, null);

            viewHolder.viewHolder_imageView = (ImageView) convertView.findViewById(R.id.ks_page1_gridView_iv1);
            viewHolder.viewHolder_textView = (TextView) convertView.findViewById(R.id.ks_page1_gridView_tv1);

            //4- 通过setTag将ViewHolder与view相关联
            convertView.setTag(viewHolder);
        } else {
            //5- 当不为空的时候直接通过getTag方法找到控件
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //6- 加载数据
        CoursesData data = courses.get(position);
        ImageLoaderHelper.getInstance().loadImage(data.getImageUrl(), viewHolder.viewHolder_imageView);
        viewHolder.viewHolder_textView.setText(data.getName());
        return convertView;
    }

    //1- 创建内部类ViewHolder
    private class ViewHolder {
        private ImageView viewHolder_imageView;
        private TextView viewHolder_textView;
    }
}
