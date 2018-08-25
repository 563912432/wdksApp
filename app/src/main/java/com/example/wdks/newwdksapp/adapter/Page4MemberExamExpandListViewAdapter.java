package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.MyExam;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;

import java.util.List;

import static com.example.wdks.newwdksapp.R.layout.page4_member_expand1_parent;

/**
 * Created by Administrator on 2016/10/9 0009.
 * 个人中心页面的我的题库ExpandListView的适配器
 */

public class Page4MemberExamExpandListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<MyExam> group;
    private List<List<MyExam>> child;

    public Page4MemberExamExpandListViewAdapter(Context context, List<MyExam> group, List<List<MyExam>> child) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.page4_member_expand1_parent, null);
            viewHolder = new ViewHolder();
            viewHolder.viewHolder1_tv1 = (TextView) convertView.findViewById(R.id.ks_page4_expand1_p_tv1);

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
            convertView = LayoutInflater.from(context).inflate(R.layout.page4_member_expand1_child, null);
            viewHolder = new ViewHolder();
            viewHolder.viewHolder2_iv1 = (ImageView) convertView.findViewById(R.id.ks_page4_expandList1_c_iv1);
            viewHolder.viewHolder2_tv1 = (TextView) convertView.findViewById(R.id.ks_page4_expandList1_c_tv1);
            viewHolder.viewHolder2_tv2 = (TextView) convertView.findViewById(R.id.ks_page4_expandList1_c_tv2);
            viewHolder.viewHolder2_tv3 = (TextView) convertView.findViewById(R.id.ks_page4_expandList1_c_tv3);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoaderHelper.getInstance().loadImage(child.get(groupPosition).get(childPosition).getImageUrl(), viewHolder.viewHolder2_iv1);
        viewHolder.viewHolder2_tv1.setText(child.get(groupPosition).get(childPosition).getName());
        viewHolder.viewHolder2_tv2.setText(child.get(groupPosition).get(childPosition).getContent());
        viewHolder.viewHolder2_tv3.setText(child.get(groupPosition).get(childPosition).getTime());

        if (child.get(groupPosition).get(childPosition).getTime() == null) {
            viewHolder.viewHolder2_tv2.setTextColor(Color.parseColor("#FF0000"));
        } else {
            viewHolder.viewHolder2_tv2.setTextColor(Color.parseColor("#FFA500"));
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ViewHolder {
        private TextView viewHolder1_tv1;

        private ImageView viewHolder2_iv1;
        private TextView viewHolder2_tv1, viewHolder2_tv2, viewHolder2_tv3;
    }
}
