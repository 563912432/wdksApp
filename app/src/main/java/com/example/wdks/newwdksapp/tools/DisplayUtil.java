package com.example.wdks.newwdksapp.tools;

import android.content.Context;

/**
 * Created by Administrator on 2016/10/8 0008.
 * px和dp之间的转换
 */

public class DisplayUtil {
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
