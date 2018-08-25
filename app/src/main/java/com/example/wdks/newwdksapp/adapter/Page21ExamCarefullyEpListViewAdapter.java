package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.MyExam;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 * 我的题库的考试精编页面的ExpandableListView的适配器
 */

public class Page21ExamCarefullyEpListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<MyExam> group;
    private List<List<MyExam>> child;

    public Page21ExamCarefullyEpListViewAdapter(Context context, List<MyExam> group, List<List<MyExam>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page21_expand_parent, null);
            viewHolder = new ViewHolder();
            viewHolder.viewHolder1_tv1 = (TextView) convertView.findViewById(R.id.ks_page21_expand_parent_tv1);

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
            convertView = LayoutInflater.from(context).inflate(R.layout.page21_expand_child, null);
            viewHolder = new ViewHolder();
            viewHolder.viewHolder2_tv1 = (TextView) convertView.findViewById(R.id.ks_page21_expand_child_tv1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
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
    }
}
