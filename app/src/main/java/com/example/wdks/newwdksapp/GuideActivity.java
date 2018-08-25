package com.example.wdks.newwdksapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wdks.newwdksapp.adapter.GuideViewpagerAdapter;
import com.example.wdks.newwdksapp.pages.Page11LoginActivity;
import com.example.wdks.newwdksapp.pages.Page12RegisterActivity;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ViewPager mViewPager;
    private GuideViewpagerAdapter mAdapter;
    private List<View> views;
    private ImageView[] dots;
    private int[] ids = {R.id.ks_guide_iv1, R.id.ks_guide_iv2, R.id.ks_guide_iv3};
    private String[] mImageUrl = {"drawable://" + R.drawable.wel1, "drawable://"
            + R.drawable.wel2, "drawable://" + R.drawable.wel3};   //ImageLoader加载drawable下的图片


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();    //自定义标题栏颜色
        setContentView(R.layout.activity_guide);
        initView();
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
        mViewPager = (ViewPager) findViewById(R.id.ks_guide_viewPager);
        init();
    }

    //视图操作
    private void init() {
        //实现ViewPager
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.guide_one, null));
        views.add(inflater.inflate(R.layout.guide_two, null));
        views.add(inflater.inflate(R.layout.guide_three, null));

        mAdapter = new GuideViewpagerAdapter(this, views);
        mViewPager.setAdapter(mAdapter);

        //初始化指示器
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
        mViewPager.addOnPageChangeListener(this);   //指示器跟随viewPager的动态移动

        //ImageLoader加载图片
        mImageView1 = (ImageView) views.get(0).findViewById(R.id.ks_guide_viewPager_iv1);
        ImageLoaderHelper.getInstance().loadImage(mImageUrl[0], mImageView1);
        mImageView2 = (ImageView) views.get(1).findViewById(R.id.ks_guide_viewPager_iv2);
        ImageLoaderHelper.getInstance().loadImage(mImageUrl[1], mImageView2);
        mImageView3 = (ImageView) views.get(2).findViewById(R.id.ks_guide_viewPager_iv3);
        ImageLoaderHelper.getInstance().loadImage(mImageUrl[2], mImageView3);

        mButton1 = (Button) views.get(2).findViewById(R.id.ks_guide_btn1);  //随便逛逛
        mButton2 = (Button) views.get(2).findViewById(R.id.ks_guide_btn2);  //登录
        mButton3 = (Button) views.get(2).findViewById(R.id.ks_guide_btn3);  //注册

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_guide_btn1:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.ks_guide_btn2:
                //TODO 跳转至登录页面
                startActivity(new Intent(this, Page11LoginActivity.class));
                finish();
                break;
            case R.id.ks_guide_btn3:
                //TODO 跳转至注册页面
                startActivity(new Intent(this, Page12RegisterActivity.class));
                finish();
                break;
        }
    }

    //指示器的动态移动
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < ids.length; i++) {
            if (position == i) {
                dots[i].setImageResource(R.drawable.ico_page_indicator_focused);
            } else {
                dots[i].setImageResource(R.drawable.ico_page_indicator);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
