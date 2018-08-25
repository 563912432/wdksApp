package com.example.wdks.newwdksapp.tools;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wdks.newwdksapp.R;

/**
 * Created by Administrator on 2016/9/1 0001.
 * 自定义加载网络时的弹出的圆形进度条对话框
 */
public class MyProgressDialogConn {
    //自定义进度条对话框
    public Dialog createLoader(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.prodialogconn, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.ks_ld_view);
        ImageView iv = (ImageView) v.findViewById(R.id.ks_ld_iv1);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.prodialoganim);
        iv.startAnimation(animation);

        Dialog loadingDialog = new Dialog(context, R.style.LoaderConn);

        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        return loadingDialog;
    }
}
