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
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;
import com.example.wdks.newwdksapp.tools.MyScreenHeight;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1 0001.
 * 首页推书推荐的GridView的适配器
 */
public class Page1BookGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<CoursesData> data;

    public Page1BookGridViewAdapter(Context context, List<CoursesData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
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
            //3- 实例化ViewHolder，并且适配ViewHolder中的参数
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.page1_home_book_gridview, null);

       /*     //动态设置gridView的高度
            LinearLayout gridView = (LinearLayout) convertView.findViewById(R.id.ks_page1_book_gridView_grid);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) gridView.getLayoutParams();
            MyScreenHeight mHeight = new MyScreenHeight();
            layoutParams.height = mHeight.initHeight(context) / 5;
            gridView.setLayoutParams(layoutParams);*/

            viewHolder.image = (ImageView) convertView.findViewById(R.id.ks_page1_book_gridView_iv1);
            viewHolder.title = (TextView) convertView.findViewById(R.id.ks_page1_book_gridView_tv1);
            viewHolder.price1 = (TextView) convertView.findViewById(R.id.ks_page1_book_gridView_tv2);
            viewHolder.price2 = (TextView) convertView.findViewById(R.id.ks_page1_book_gridView_tv3);

            viewHolder.icon = (TextView) convertView.findViewById(R.id.ks_page1_book_gridView_price);
            viewHolder.line = convertView.findViewById(R.id.ks_page1_book_gridView_view);

            //4- 通过setTag将ViewHolder与view相关联
            convertView.setTag(viewHolder);
        } else {
            //5- 当不为空的时候直接通过getTag方法找到控件
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //6- 加载数据
        CoursesData cor = data.get(position);
        ImageLoaderHelper.getInstance().loadImage(cor.getImageUrl(), viewHolder.image);
        viewHolder.title.setText(cor.getTitle());
        viewHolder.price1.setText(cor.getPrice1());
        viewHolder.price2.setText(cor.getPrice2());

        //促销价为空的时候隐藏￥和——
        if (cor.getPrice1() == null) {
            viewHolder.icon.setVisibility(View.GONE);
            viewHolder.line.setVisibility(View.GONE);
        }
        return convertView;
    }

    //1- 定义内部类ViewHolder
    private class ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView price1;
        private TextView price2;

        private TextView icon;
        private View line;
    }
}
