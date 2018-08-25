package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.MyExam;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2 0009.
 * 视频播放的ExpandableListView的适配器
 */

public class Page44VideoPlayEpListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<MyExam> group;
    private List<List<MyExam>> child;
    //用来装载某个item是否被选中
    SparseBooleanArray selected;
    int old = -1;
    int parentPosition = -1;

    public Page44VideoPlayEpListViewAdapter(Context context, List<MyExam> group, List<List<MyExam>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
        selected = new SparseBooleanArray();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page44_video_play_expand_parent, null);
            viewHolder = new ViewHolder();
            viewHolder.viewHolder1_tv1 = (TextView) convertView.findViewById(R.id.ks_page44_expand_parent_tv1);

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
            convertView = LayoutInflater.from(context).inflate(R.layout.page44_video_play_expand_child, null);
            viewHolder = new ViewHolder();
            viewHolder.viewHolder2_iv1 = (ImageView) convertView.findViewById(R.id.ks_page44_expand_child_iv1);
            viewHolder.viewHolder2_tv1 = (TextView) convertView.findViewById(R.id.ks_page44_expand_child_tv1);
            viewHolder.viewHolder2_line = convertView.findViewById(R.id.ks_page44_expand_child_line1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //重点代码 判断此时的子item是否为选中项还要去判断大的item的位置
        if (selected.get(childPosition) && this.parentPosition == groupPosition) {
            viewHolder.viewHolder2_line.setBackgroundResource(R.color.whiteSmoke);
            viewHolder.viewHolder2_iv1.setImageResource(R.drawable.ico_pause);
            viewHolder.viewHolder2_tv1.setTextColor(Color.parseColor("#494949"));
        } else {
            viewHolder.viewHolder2_line.setBackgroundResource(R.color.white);
            viewHolder.viewHolder2_iv1.setImageResource(R.drawable.ico_play);
            viewHolder.viewHolder2_tv1.setTextColor(Color.parseColor("#696969"));
        }
        viewHolder.viewHolder2_tv1.setText(child.get(groupPosition).get(childPosition).getName());

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private class ViewHolder {
        private TextView viewHolder1_tv1;

        private TextView viewHolder2_tv1;
        private ImageView viewHolder2_iv1;
        private View viewHolder2_line;
    }

    public void setSelectedItem(int groupPosition, int selected) {
        this.parentPosition = groupPosition;
        if (old != -1) {
            this.selected.put(old, false);
        }
        this.selected.put(selected, true);
        old = selected;
    }
}
