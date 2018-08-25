package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/29 0024.
 * 预测试卷答题页面的ViewPager的适配器
 */

public class Page31YCTestViewPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<Fragment> mFragments;

    public Page31YCTestViewPagerAdapter(FragmentManager fm, Context context, List<Fragment> mFragments) {

        super(fm);
        this.context = context;
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }
}
