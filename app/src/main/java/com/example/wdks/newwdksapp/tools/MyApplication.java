package com.example.wdks.newwdksapp.tools;

import android.app.Application;

import com.example.wdks.newwdksapp.volley.MySingleton;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2016/8/29 0029.
 * 自定义Application
 * 初始化ImageLoader配置
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
    }

}
