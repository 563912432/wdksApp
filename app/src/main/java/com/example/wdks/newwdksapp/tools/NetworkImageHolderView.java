package com.example.wdks.newwdksapp.tools;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.wdks.newwdksapp.R;

/**
 * Created by Administrator on 2016/8/30 0030.
 * 加载网络图片的帮助类
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        //轮播一张图片，直接用代码创建
        mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        //mImageView.setImageResource(R.drawable.ic_default_adimage); //默认显示图片
        ImageLoaderHelper.getInstance().loadImage(data, mImageView);
    }
}
