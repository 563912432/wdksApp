package com.example.wdks.newwdksapp.tools;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;

import com.example.wdks.newwdksapp.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by Administrator on 2016/10/15 0015.
 * api 19后自定义状态栏颜色
 */

public class MyInitWindowGray {
    //api 19后自定义状态栏颜色  浅灰色
    @TargetApi(19)
    public static void initWindow(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintColor(activity.getResources().getColor(R.color.light_grey));
            tintManager.setStatusBarTintEnabled(true);
        }
    }
}
