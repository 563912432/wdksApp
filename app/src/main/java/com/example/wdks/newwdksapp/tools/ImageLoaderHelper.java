package com.example.wdks.newwdksapp.tools;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2016/8/29 0029.
 * ImageLoader帮助类(Image的具体配置)
 */
public class ImageLoaderHelper {
    private static ImageLoaderHelper imageLoaderHelper;
    private ImageLoader imageLoader;

    //显示图片的设置
    private DisplayImageOptions options;

    //实例化ImageLoaderHelper
    public static ImageLoaderHelper getInstance() {
        if (imageLoaderHelper == null) {
            imageLoaderHelper = new ImageLoaderHelper();
        }
        return imageLoaderHelper;
    }

    //构造方法
    public ImageLoaderHelper() {
        init();
    }

    //显示图片的配置
    private void init() {
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader = ImageLoader.getInstance();
    }

    //加载图片
    public void loadImage(String imageUrl, ImageView imageView) {
        imageLoader.displayImage(imageUrl, imageView, options);
    }
}
