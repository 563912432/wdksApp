package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.MyExam;
import com.example.wdks.newwdksapp.data.MyVideo;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 * 个人中心页面的视频记录ExpandListView的适配器
 */

public class Page4MemberVideoExpandListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<MyVideo> group;
    private List<List<MyVideo>> child;
    private MyClickListener mListener;


    public Page4MemberVideoExpandListViewAdapter(Context context, List<MyVideo> group, List<List<MyVideo>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
    }

    public void setOnClickListener(MyClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getGroupCount() {
        return group == null ? 0 : group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition) == null ? 0 : child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.page4_member_expand2_parent, null);
            viewHolder = new ViewHolder();
            viewHolder.viewHolder1_tv1 = (TextView) convertView.findViewById(R.id.ks_page4_expand2_p_tv1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.viewHolder1_tv1.setText(group.get(groupPosition).getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.page4_member_expand2_child, null);
            viewHolder = new ViewHolder();
            viewHolder.viewHolder2_iv1 = (ImageView) convertView.findViewById(R.id.ks_page4_expandList2_c_iv1);
            viewHolder.viewHolder2_tv1 = (TextView) convertView.findViewById(R.id.ks_page4_expandList2_c_tv1);
            viewHolder.viewHolder2_tv2 = (TextView) convertView.findViewById(R.id.ks_page4_expandList2_c_tv2);
            viewHolder.viewHolder2_tv3 = (TextView) convertView.findViewById(R.id.ks_page4_expandList2_c_tv3);
            viewHolder.viewHolder2_tv4 = (TextView) convertView.findViewById(R.id.ks_page4_expandList2_c_tv4);
            viewHolder.viewHolder2_tv5 = (TextView) convertView.findViewById(R.id.ks_page4_expandList2_c_tv5);

            viewHolder.goon = convertView.findViewById(R.id.ks_page4_expandList2_c_goon);
            viewHolder.goon.setOnClickListener(mOnClickListener);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoaderHelper.getInstance().loadImage(child.get(groupPosition).get(childPosition).getImageUrl(), viewHolder.viewHolder2_iv1);
        viewHolder.viewHolder2_tv1.setText(child.get(groupPosition).get(childPosition).getName());
        viewHolder.viewHolder2_tv2.setText(child.get(groupPosition).get(childPosition).getWatched());
        viewHolder.viewHolder2_tv3.setText(child.get(groupPosition).get(childPosition).getContent());
        viewHolder.viewHolder2_tv4.setText(child.get(groupPosition).get(childPosition).getTime());
        viewHolder.viewHolder2_tv5.setText(child.get(groupPosition).get(childPosition).getState());

        //设置position
        viewHolder.goon.setTag(childPosition);

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ViewHolder {
        private TextView viewHolder1_tv1;

        private ImageView viewHolder2_iv1;
        private TextView viewHolder2_tv1, viewHolder2_tv2, viewHolder2_tv3, viewHolder2_tv4, viewHolder2_tv5;

        private View goon;
    }

    //实现点击事件
    public View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = (int) v.getTag();
                switch (v.getId()) {
                    case R.id.ks_page4_expandList2_c_goon:
                        mListener.onWatch(Page4MemberVideoExpandListViewAdapter.this, v, position);
                        break;
                }
            }
        }
    };

    //自定义接口
    public interface MyClickListener {
        public void onWatch(BaseExpandableListAdapter adapter, View view, int position);
    }
}
