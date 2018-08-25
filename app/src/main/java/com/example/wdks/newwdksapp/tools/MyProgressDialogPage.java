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
 * Created by Administrator on 2016/9/5 0005.
 * 自定义的圆形进度条对话框实现类（透明）
 */
public class MyProgressDialogPage {
    //自定义进度条对话框
    public Dialog createLoader(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.prodialog, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.id_ld_view);
        ImageView iv = (ImageView) v.findViewById(R.id.id_ld_iv1);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.prodialoganim);
        iv.startAnimation(animation);

        Dialog loadingDialog = new Dialog(context, R.style.Loader);

        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        return loadingDialog;
    }
}
