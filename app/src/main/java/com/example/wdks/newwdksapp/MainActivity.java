package com.example.wdks.newwdksapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.tools.MyTabFooterDAO;
import com.example.wdks.newwdksapp.volley.MySingleton;

import java.util.ArrayList;


/*
* 主页
* */
public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {
    private FragmentTabHost mTabHost;
    private long mExitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initView();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //停掉volley的网络请求
        MySingleton.getInstance(this).cancelPendingRequests("page1_exam1");
        MySingleton.getInstance(this).cancelPendingRequests("page1_book1");
        MySingleton.getInstance(this).cancelPendingRequests("page2_book_kc");
        MySingleton.getInstance(this).cancelPendingRequests("page2_exam_kc");
        MySingleton.getInstance(this).cancelPendingRequests("page2_book_ceShi");
        MySingleton.getInstance(this).cancelPendingRequests("page3_listView1");
        MySingleton.getInstance(this).cancelPendingRequests("page3_listView2");
    }

    //初始化视图
    private void initView() {
        mTabHost = (FragmentTabHost) super.findViewById(android.R.id.tabhost);
        init();
    }

    //视图操作
    private void init() {
        /*setup() 初始化函数（必须在addTab之前调用）
        TabHost  包含两个子元素：
        1.Tab标签容器TabWidget（@android:id/tabs）
        2.Tab内容容器FrameLayout（@android:id/tabcontent）*/
        mTabHost.setup(this, super.getSupportFragmentManager(), R.id.ks_main_content_fragment);
        mTabHost.getTabWidget().setDividerDrawable(null);
        initTab();
        mTabHost.setOnTabChangedListener(this);

    }

    private void initTab() {
        String tabs[] = MyTabFooterDAO.getTabsTxt();
        for (int i = 0; i < tabs.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec, MyTabFooterDAO.getFragments()[i], null);
            mTabHost.setTag(i);
        }
    }

    private View getTabView(int idx) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main_footertabs, null);
        ((TextView) view.findViewById(R.id.ks_footer_tv)).setText(MyTabFooterDAO.getTabsTxt()[idx]);
        if (idx == 0) {
            ((TextView) view.findViewById(R.id.ks_footer_tv)).setTextColor(getResources().getColor(R.color.darkGray));
            ((ImageView) view.findViewById(R.id.ks_footer_iv)).setImageResource(MyTabFooterDAO.getTabsImgLight()[idx]);
        } else {
            ((ImageView) view.findViewById(R.id.ks_footer_iv)).setImageResource(MyTabFooterDAO.getTabsImg()[idx]);
        }
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        updateTab();
    }

    private void updateTab() {
        TabWidget tabW = mTabHost.getTabWidget();
        for (int i = 0; i < tabW.getChildCount(); i++) {
            View view = tabW.getChildTabViewAt(i);
            ImageView iv = (ImageView) view.findViewById(R.id.ks_footer_iv);
            if (i == mTabHost.getCurrentTab()) {
                ((TextView) view.findViewById(R.id.ks_footer_tv)).setTextColor(getResources().getColor(R.color.deepSkyBlue));
                iv.setImageResource(MyTabFooterDAO.getTabsImgLight()[i]);
            } else {
                ((TextView) view.findViewById(R.id.ks_footer_tv)).setTextColor(getResources().getColor(R.color.darkGray));
                iv.setImageResource(MyTabFooterDAO.getTabsImg()[i]);
            }
        }
    }

    //点击两次退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                // 如果两次按键时间间隔大于2000毫秒，则不退出
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();// 更新mExitTime
            } else {
                finish();
                System.exit(0);// 否则退出程序
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
