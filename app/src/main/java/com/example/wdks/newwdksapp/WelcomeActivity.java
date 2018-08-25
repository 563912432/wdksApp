package com.example.wdks.newwdksapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/*
* 欢迎页
* */
public class WelcomeActivity extends AppCompatActivity {
    private ImageView mImageView1;
    private String mImageUrl = "drawable://" + R.drawable.wel3;   //ImageLoader加载drawable下的图片
    private boolean isFirstIn = false;
    private static final int TIMER = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    private Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();    //自定义标题栏颜色
        setContentView(R.layout.activity_welcome);
        initView();             //初始化视图
    }

    //api 19后自定义状态栏颜色
    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.deepSkyBlue));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    //初始化视图
    private void initView() {
        mImageView1 = (ImageView) findViewById(R.id.ks_welcome_iv1);
        init();                 //视图操作
    }

    //视图操作
    private void init() {
        ImageLoaderHelper.getInstance().loadImage(mImageUrl, mImageView1);   //ImageLoader加载图片
        /*
        * 1- 首先获取preferences
        * 2- 把他获取到的值赋值给isFirstIn，第一次进入的时候确定是没有值的，我们给他赋值true
        * 3- 判断这个值，如果是false，让他跳转到Home（首页）
        * 4- 如果是true，把这个值存为false，并跳转到Guide（引导页），再次进入时，就会直接跳转到首页
        * */
        SharedPreferences preferences = getSharedPreferences("Guide", MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn", true);
        if (!isFirstIn) {
            //发送GO_HOME的信息；
            handle.sendEmptyMessageDelayed(GO_HOME, TIMER);
        } else {
            //第一次进入 进Guide页，赋值false
            handle.sendEmptyMessageDelayed(GO_GUIDE, TIMER);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("isFirstIn", false);
            edit.apply();
        }
    }

    private void goGuide() {
        startActivity(new Intent(this, GuideActivity.class));
        finish();
    }

    private void goHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
