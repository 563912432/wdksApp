package com.example.wdks.newwdksapp.tools;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2016/9/20 0020.
 * 获取屏幕高度
 */
public class MyScreenHeight {
    int mHeight;

    public int initHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        mHeight = dm.heightPixels;
        return mHeight;
    }
}
