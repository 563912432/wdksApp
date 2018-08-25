package com.example.wdks.newwdksapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31 0024.
 * 我的答疑答题页面的ViewPager的适配器
 */

public class Page37MADetailsViewPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<Fragment> mFragments;

    public Page37MADetailsViewPagerAdapter(FragmentManager fm, Context context, List<Fragment> mFragments) {

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
