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
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 * 商城页的图书ListView适配器
 */
public class Page2BookListViewAdapter extends BaseAdapter {
    private Context context;
    private List<CoursesData> data;
    private MyClickListener mListener;

    public Page2BookListViewAdapter(Context context, List<CoursesData> data) {
        this.context = context;
        this.data = data;
    }

    public void setOnClickListener(MyClickListener listener) {
        mListener = listener;
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
            convertView = inflater.inflate(R.layout.page2_shop_book_listview, null);

     /*       //动态设置ListView的高度
            LinearLayout listView = (LinearLayout) convertView.findViewById(R.id.ks_page1_video_list);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) listView.getLayoutParams();
            MyScreenHeight mHeight = new MyScreenHeight();
            layoutParams.height = mHeight.initHeight(context) / 5;
            listView.setLayoutParams(layoutParams);*/

            viewHolder.image = (ImageView) convertView.findViewById(R.id.ks_page2_book_listView_iv1);
            viewHolder.title = (TextView) convertView.findViewById(R.id.ks_page2_book_listView_tv1);
            viewHolder.content = (TextView) convertView.findViewById(R.id.ks_page2_book_listView_tv2);
            viewHolder.price1 = (TextView) convertView.findViewById(R.id.ks_page2_book_listView_tv3);
            viewHolder.price2 = (TextView) convertView.findViewById(R.id.ks_page2_book_listView_tv4);

            viewHolder.price3 = (TextView) convertView.findViewById(R.id.ks_page2_book_listView_price);
            viewHolder.line = convertView.findViewById(R.id.ks_page2_book_listView_view);

            viewHolder.add = convertView.findViewById(R.id.ks_page2_book_listView_add);
            viewHolder.add.setOnClickListener(mOnClickListener);
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
        viewHolder.content.setText(cor.getContent());
        viewHolder.price1.setText(cor.getPrice1());
        viewHolder.price2.setText(cor.getPrice2());

        if (cor.getPrice1() == null) {
            viewHolder.price3.setVisibility(View.GONE);
            viewHolder.line.setVisibility(View.GONE);
        }

        //设置position
        viewHolder.add.setTag(position);

        return convertView;
    }

    //1- 创建内部类ViewHolder
    private class ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView content;
        private TextView price1;
        private TextView price2;

        private TextView price3;
        private View line;
        private View add;
    }

    //实现点击事件
    public View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = (int) v.getTag();
                switch (v.getId()) {
                    case R.id.ks_page2_book_listView_add:
                        mListener.onAddToCar(Page2BookListViewAdapter.this, v, position);
                        break;
                }
            }
        }
    };

    //自定义接口
    public interface MyClickListener {
        public void onAddToCar(BaseAdapter adapter, View view, int position);
    }

}