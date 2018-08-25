package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page44VideoPlayEpListViewAdapter;
import com.example.wdks.newwdksapp.data.MyExam;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/*
* 视频播放页面
* */
public class Page44VideoPlayActivity extends AppCompatActivity implements View.OnClickListener, ExpandableListView.OnChildClickListener {
    private JCVideoPlayerStandard mVideoPlayer;
    private String video_url = "http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv";
    private String video_url1 = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";
    private ExpandableListView mExpandableListView;
    private Page44VideoPlayEpListViewAdapter mEpListViewAdapter;
    private List<MyExam> mGroup;
    private List<List<MyExam>> mChild;
    private TextView mTitle;
    private String title;
    private View mBack, mDownLoad, mShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page44_video_play);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mGroup = new ArrayList<MyExam>();
        mGroup.add(new MyExam("第一章"));
        mGroup.add(new MyExam("第二章"));
        mGroup.add(new MyExam("第三章"));
        mGroup.add(new MyExam("第四章"));
        mGroup.add(new MyExam("第五章"));
        mGroup.add(new MyExam("第六章"));

        mChild = new ArrayList<List<MyExam>>();
        List<MyExam> list1 = new ArrayList<MyExam>();
        list1.add(new MyExam("第一节"));
        list1.add(new MyExam("第二节"));
        list1.add(new MyExam("第三节"));

        List<MyExam> list2 = new ArrayList<MyExam>();
        list2.add(new MyExam("第一节"));
        list2.add(new MyExam("第二节"));
        list2.add(new MyExam("第三节"));
        list2.add(new MyExam("第四节"));

        List<MyExam> list3 = new ArrayList<MyExam>();
        list3.add(new MyExam("第一节"));
        list3.add(new MyExam("第二节"));

        List<MyExam> list4 = new ArrayList<MyExam>();
        list4.add(new MyExam("第一节"));
        list4.add(new MyExam("第二节"));
        list4.add(new MyExam("第三节"));
        list4.add(new MyExam("第四节"));

        List<MyExam> list5 = new ArrayList<MyExam>();
        list5.add(new MyExam("第一节"));
        list5.add(new MyExam("第二节"));
        list5.add(new MyExam("第三节"));

        List<MyExam> list6 = new ArrayList<MyExam>();
        list6.add(new MyExam("第一节"));
        list6.add(new MyExam("第二节"));

        mChild.add(list1);
        mChild.add(list2);
        mChild.add(list3);
        mChild.add(list4);
        mChild.add(list5);
        mChild.add(list6);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    //初始化视图
    private void initView() {
        mVideoPlayer = (JCVideoPlayerStandard) findViewById(R.id.ks_page44_jcPlayer);   //视频播放器
        mTitle = (TextView) findViewById(R.id.ks_page44_title);   //大标题

        mExpandableListView = (ExpandableListView) findViewById(R.id.ks_page44_ExpandList);   //章节内容
        mEpListViewAdapter = new Page44VideoPlayEpListViewAdapter(this, mGroup, mChild);
        mExpandableListView.setAdapter(mEpListViewAdapter);

        mBack = findViewById(R.id.ks_page44_back);   //返回
        mDownLoad = findViewById(R.id.ks_page44_download);   //下载
        mShare = findViewById(R.id.ks_page44_share);   //分享

        init();
    }

    //视图操作
    private void init() {
        title = mGroup.get(0).getName() + " " + mChild.get(0).get(0).getName();
        Intent intent = getIntent();
        String s = intent.getExtras().getString("title");
        mTitle.setText(s);

        mVideoPlayer.setUp(video_url, JCVideoPlayer.SCREEN_LAYOUT_LIST, title);
        mVideoPlayer.startButton.performClick();   //自动播放
        mExpandableListView.setGroupIndicator(null);
        mExpandableListView.setOnChildClickListener(this);
        mExpandableListView.expandGroup(0);
        mEpListViewAdapter.setSelectedItem(0, 0);

        mBack.setOnClickListener(this);
        mDownLoad.setOnClickListener(this);
        mShare.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page44_back:
                finish();
                break;
            case R.id.ks_page44_download:
                Toast.makeText(this, "下载操作", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ks_page44_share:
                Toast.makeText(this, "分享操作", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    //ExpandableListView的子Item的点击事件
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

        mEpListViewAdapter.setSelectedItem(groupPosition, childPosition);
        mEpListViewAdapter.notifyDataSetChanged();

        switch (groupPosition) {
            case 0:   //父级的position
                switch (childPosition) {
                    case 0:   //子级的position
                        JCVideoPlayer.releaseAllVideos();
                        title = mGroup.get(groupPosition).getName() + " " + mChild.get(groupPosition).get(childPosition).getName();
                        mVideoPlayer.setUp(video_url, JCVideoPlayer.SCREEN_LAYOUT_LIST, title);
                        mVideoPlayer.startButton.performClick();
                        break;
                    case 1:
                        JCVideoPlayer.releaseAllVideos();
                        title = mGroup.get(groupPosition).getName() + " " + mChild.get(groupPosition).get(childPosition).getName();
                        mVideoPlayer.setUp(video_url1, JCVideoPlayer.SCREEN_LAYOUT_LIST, title);
                        mVideoPlayer.startButton.performClick();
                        break;
                }
                break;
            case 1:
                break;
        }
        return true;
    }

}
