package com.example.wdks.newwdksapp.tools;

import com.example.wdks.newwdksapp.pages.Page1HomeFragment;
import com.example.wdks.newwdksapp.pages.Page2ShopFragment;
import com.example.wdks.newwdksapp.pages.Page3NewsFragment;
import com.example.wdks.newwdksapp.pages.Page4MemberFragment;
import com.example.wdks.newwdksapp.R;

/**
 * Created by Administrator on 2016/8/29 0029.
 * 工具类:放置底部tab里面的图标和文字，正文的Fragment
 */
public class MyTabFooterDAO {
    public static String[] getTabsTxt() {
        String[] tabs = {"首页", "商城", "资讯", "我的"};
        return tabs;
    }

    public static int[] getTabsImg() {
        int[] ids = {R.drawable.ico_home_off, R.drawable.ico_shop_off, R.drawable.ico_news_off, R.drawable.ico_my_off};
        return ids;
    }

    public static int[] getTabsImgLight() {
        int[] ids = {R.drawable.ico_home_on, R.drawable.ico_shop_on, R.drawable.ico_news_on, R.drawable.ico_my_on};
        return ids;
    }

    public static Class[] getFragments() {
        Class[] clz = {Page1HomeFragment.class, Page2ShopFragment.class, Page3NewsFragment.class, Page4MemberFragment.class};
        return clz;
    }
}
