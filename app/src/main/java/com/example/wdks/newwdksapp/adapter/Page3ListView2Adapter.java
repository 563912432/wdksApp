package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.NewsData;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/6/15 0015.
 * 资讯页的官方资讯的ListView适配器
 * ImageLoader
 */
public class Page3ListView2Adapter extends BaseAdapter {

    private Context context;
    private List<NewsData> news;
    private String url = "http://img.alicdn.com/bao/uploaded/a8/e1/TB1RSXXLXXXXXcGXXXXSutbFXXX.jpg_80x80.jpg";

    public Page3ListView2Adapter(Context context, List<NewsData> news) {
        this.context = context;
        this.news = news;
    }

    @Override
    public int getCount() {
        if (news == null) {
            return 0;
        } else {
            return news.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page3_news_listview2, null);
            ImageView logo = (ImageView) convertView.findViewById(R.id.ks_page3_listView2_logo);

            ImageLoaderHelper.getInstance().loadImage(url, logo);

            viewHolder.title = (TextView) convertView.findViewById(R.id.ks_page3_listView2_tv1);
            viewHolder.author = (TextView) convertView.findViewById(R.id.ks_page3_listView2_tv2);
            viewHolder.time = (TextView) convertView.findViewById(R.id.ks_page3_listView2_tv3);
            viewHolder.ivURl1 = (ImageView) convertView.findViewById(R.id.ks_page3_listView2_iv1);
            viewHolder.ivURl2 = (ImageView) convertView.findViewById(R.id.ks_page3_listView2_iv2);
            viewHolder.ivURl3 = (ImageView) convertView.findViewById(R.id.ks_page3_listView2_iv3);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //数据绑定
        NewsData cor = news.get(position);

        viewHolder.title.setText(cor.getTitle());
        viewHolder.author.setText(cor.getAuthor());
        viewHolder.time.setText(cor.getTime());
        ImageLoaderHelper.getInstance().loadImage(cor.getImageUrl1(), viewHolder.ivURl1);
        ImageLoaderHelper.getInstance().loadImage(cor.getImageUrl2(), viewHolder.ivURl2);
        ImageLoaderHelper.getInstance().loadImage(cor.getImageUrl3(), viewHolder.ivURl3);

        return convertView;
    }

    private class ViewHolder {
        private TextView title;
        private TextView author;
        private TextView time;
        private ImageView ivURl1;
        private ImageView ivURl2;
        private ImageView ivURl3;
    }
}

