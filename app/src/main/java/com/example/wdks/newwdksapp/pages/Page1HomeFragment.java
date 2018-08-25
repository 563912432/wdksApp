package com.example.wdks.newwdksapp.pages;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page1BookGridViewAdapter;
import com.example.wdks.newwdksapp.adapter.Page1GridViewAdapter;
import com.example.wdks.newwdksapp.adapter.Page1ExamListViewAdapter;
import com.example.wdks.newwdksapp.adapter.Page1VideoRecycleViewAdapter;
import com.example.wdks.newwdksapp.data.CoursesData;
import com.example.wdks.newwdksapp.data.HomeVideoData;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;
import com.example.wdks.newwdksapp.tools.MyGridView;
import com.example.wdks.newwdksapp.tools.MyListView;
import com.example.wdks.newwdksapp.tools.MyScrollView;
import com.example.wdks.newwdksapp.tools.MySwipeRefreshLayout;
import com.example.wdks.newwdksapp.tools.MyTabFooterDAO;
import com.example.wdks.newwdksapp.tools.NetworkImageHolderView;
import com.example.wdks.newwdksapp.volley.MySingleton;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class Page1HomeFragment extends Fragment implements View.OnClickListener, MyScrollView.ScrollViewListener,
        OnItemClickListener, AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {
    private View view;
    private LinearLayout mLinearLayout, mButton1, mButton2, mButton3, mSearch, mButton5, mButton6;
    private Button mButton4;
    private Drawable mDrawable;
    private ImageView mImageView1, mImageView2, mImageView3, mImageView4, mImageView5;
    private MyScrollView mScrollView;
    private ConvenientBanner mConvenientBanner;
    private List<String> mNetworkImages;
    private List<HomeVideoData> mVideoData;
    private RecyclerView mVideoRecycleView;
    private Page1VideoRecycleViewAdapter mRecycleViewAdapter;
    private List<CoursesData> mCourse;
    private MyGridView mGridView, mBookGridView;
    private Page1GridViewAdapter mGridViewAdapter;
    private RadioGroup mRadioGroup1, mRadioGroup2;
    private RadioButton mRadioButton1, mRadioButton2;
    private int mScreenOneFive;
    private ImageLoader mImageLoader;
    private List<CoursesData> mList1Course, mList2Course, mList3Course, mList4Course, mList5Course;
    private MyListView mExamListView;
    private Page1ExamListViewAdapter mExamListViewAdapter;
    private List<CoursesData> mGrid1Course, mGrid2Course, mGrid3Course, mGrid4Course, mGrid5Course;
    private Page1BookGridViewAdapter mBookGridViewAdapter;
    private ConnectivityManager mConManager;
    private NetworkInfo mInfo;
    private MySwipeRefreshLayout mRefreshLayout;
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";
    private JsonObjectRequest mListRequest1, mListRequest2, mListRequest3, mGridRequest1;
    private FragmentTabHost mTabHost;
    private Fragment mPage1Fragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        mInfo = mConManager.getActiveNetworkInfo();
        if (mInfo == null) {
            //没有网络连接
            getFragmentManager().beginTransaction()
                    .replace(R.id.ks_page1, new Page5ConnP1Fragment())
                    .commit();
        } else {
            intiData();
        }
    }

    private void intiData() {
        initConvenientBannerData(); //初始化轮播图的数据
        initGridViewData();         //初始化GridView数据
        initVideoRecycleViewData(); //初始化视频推荐的RecycleView数据
        initBookGridViewData();    //初始化图书推荐的GridView数据
        initExamListViewData();   //初始化题库推荐的ListView数据
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page1_home, container, false);
            initView();
        }
        return view;
    }

    //开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        mConvenientBanner.startTurning(3000);
    }

    //停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
    }

    //初始化轮播图数据来源
    private void initConvenientBannerData() {
        mNetworkImages = new ArrayList<String>();
        mNetworkImages.add("http://img.mukewang.com/547d5a45000156f406000338.jpg");
        mNetworkImages.add("http://img.mukewang.com/54780ea90001f3b406000338.jpg");
        mNetworkImages.add("http://img.mukewang.com/551916790001125706000338.jpg");
        mNetworkImages.add("http://img.mukewang.com/5523711700016d1606000338.jpg");
        mNetworkImages.add("http://img.mukewang.com/551de0570001134f06000338.jpg");

    }

    //初始化GridView数据来源
    private void initGridViewData() {
        mCourse = new ArrayList<CoursesData>();
        mCourse.add(new CoursesData("drawable://" + R.drawable.ico_course1, "会计类"));
        mCourse.add(new CoursesData("drawable://" + R.drawable.ico_course2, "金融类"));
        mCourse.add(new CoursesData("drawable://" + R.drawable.ico_course3, "建筑类"));
        mCourse.add(new CoursesData("drawable://" + R.drawable.ico_course4, "医学类"));
        mCourse.add(new CoursesData("drawable://" + R.drawable.ico_course5, "教师类"));
        mCourse.add(new CoursesData("drawable://" + R.drawable.ico_course6, "计算机类"));
        mCourse.add(new CoursesData("drawable://" + R.drawable.ico_course8, "免费视频"));
        mCourse.add(new CoursesData("drawable://" + R.drawable.ico_course7, "全部"));
    }

    //初始化视频推荐的RecycleView数据
    private void initVideoRecycleViewData() {
        mVideoData = new ArrayList<HomeVideoData>();
        mVideoData.add(new HomeVideoData("http://a1.jikexueyuan.com/home/201604/11/6e01/570b17fe71605.jpg",
                "会计基础", "某某某", "免费"));
        mVideoData.add(new HomeVideoData("http://a1.jikexueyuan.com/home/201605/10/bb41/5731437213380.jpg",
                "会计电算化", "某某某", "免费"));
        mVideoData.add(new HomeVideoData("http://a1.jikexueyuan.com/home/201605/09/2497/572fedc688602.jpg",
                "财经法规与会计职业道德", "某某某", "免费"));
    }

    //初始化图书推荐的GridView数据
    private void initBookGridViewData() {
        //todo 测试图书-会计类
        mGrid1Course = new ArrayList<CoursesData>();
        mGridRequest1 = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < 4; i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        mGrid1Course.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1vF1vJVXXXXXaXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"
                                , "中级会计职称", "100.00", jsonObject.getString("learner")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (mBookGridView != null) {
                    mBookGridViewAdapter = new Page1BookGridViewAdapter(getActivity(), mGrid1Course);
                    mBookGridView.setAdapter(mBookGridViewAdapter);  //改变内容数据
                }
                if (mLinearLayout != null) {
                    mLinearLayout.setVisibility(View.VISIBLE);
                }
                if (mScrollView != null) {
                    mScrollView.setVisibility(View.VISIBLE);
                }
                if (mRefreshLayout != null) {
                    mRefreshLayout.setRefreshing(false);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mRefreshLayout.setRefreshing(false);
                                Toast.makeText(getActivity(), "连接超时", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
        mGridRequest1.setTag("page_book1");
        MySingleton.getInstance(getActivity()).addToRequestQueue(mGridRequest1);

        mGrid2Course = new ArrayList<CoursesData>();
        for (int i = 0; i < 4; i++) {
            mGrid2Course.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1V.GaJFXXXXa.XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"
                    , "个人贷款", null, "10.00"));
        }
        mGrid3Course = new ArrayList<CoursesData>();
        for (int i = 0; i < 4; i++) {
            mGrid3Course.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1fQesJFXXXXXHaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"
                    , "市政公用工程管理与实务实务", "90.00", "10.00"));
        }
        mGrid4Course = new ArrayList<CoursesData>();
        for (int i = 0; i < 4; i++) {
            mGrid4Course.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1J.ppKVXXXXc8XFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"
                    , "临床执业助理医师", null, "10.00"));
        }
        mGrid5Course = new ArrayList<CoursesData>();
        for (int i = 0; i < 4; i++) {
            mGrid5Course.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1txDbJVXXXXajXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"
                    , "综合素质", "70.00", "10.00"));
        }
    }

    //初始化考试题库的ListView数据
    private void initExamListViewData() {

        //todo 测试（会计类）
        mList1Course = new ArrayList<CoursesData>();
        //解析Json数据
        mListRequest1 = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //数据形式为obj包嵌套arr,arr里面又嵌套obj;
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < 3; i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        mList1Course.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i4/TB1EmbaLVXXXXXmaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                                "2016年初级会计职称无纸化考试系统", "40.00", jsonObject.getString("learner")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //解析完成之后，完成ListView,显示页面,隐藏刷新按钮
                if (mExamListView != null) {
                    mExamListViewAdapter = new Page1ExamListViewAdapter(getActivity(), mList1Course);
                    mExamListView.setAdapter(mExamListViewAdapter);//适配Video的ListView
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mListRequest1.setTag("page1_exam1");
        MySingleton.getInstance(getActivity()).addToRequestQueue(mListRequest1);
        //金融类
        mList2Course = new ArrayList<CoursesData>();
        for (int i = 0; i < 3; i++) {
            mList2Course.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i3/TB1eXLyLVXXXXcbXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                    "全国银行从业资格无纸化考试专用系统", null, "20.00"));
        }
        //建筑类
        mList3Course = new ArrayList<CoursesData>();
        for (int i = 0; i < 3; i++) {
            mList3Course.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i4/TB1yX5NHVXXXXb7XFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                    "全国二级建造师职业资格考试专用系统", "40.00", "20.00"));
        }
        //医学类
        mList4Course = new ArrayList<CoursesData>();
        for (int i = 0; i < 3; i++) {
            mList4Course.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i1/TB1nLrvJFXXXXXFXVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                    "全国执业药师资格考试无纸化考试专用系统", null, "20.00"));
        }
        //教师类
        mList5Course = new ArrayList<CoursesData>();
        for (int i = 0; i < 3; i++) {
            mList5Course.add(new CoursesData("https://img.alicdn.com/bao/uploaded/i2/TB1m.VfJVXXXXcHXpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                    "国家教师资格无纸化考试专用系统", "50.00", "20.00"));
        }

    }

    //初始化视图
    private void initView() {
        //搜索栏
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ks_page1_toolbar);
        //搜索框
        mSearch = (LinearLayout) view.findViewById(R.id.ks_page1_search);
        //学习记录按钮
        mButton4 = (Button) view.findViewById(R.id.ks_page1_btn4);

        //滚动视图
        mScrollView = (MyScrollView) view.findViewById(R.id.ks_page1_scView);
        //todo 下拉刷新 位置
        mRefreshLayout = (MySwipeRefreshLayout) view.findViewById(R.id.ks_page1_swRefresh);
        //轮播图片
        mConvenientBanner = (ConvenientBanner) view.findViewById(R.id.ks_page1_conBanner);

        //推广三个按钮
        mButton1 = (LinearLayout) view.findViewById(R.id.ks_page1_content_btn1);
        mButton2 = (LinearLayout) view.findViewById(R.id.ks_page1_content_btn2);
        mButton3 = (LinearLayout) view.findViewById(R.id.ks_page1_content_btn3);
        //推广三张图片
        mImageView1 = (ImageView) view.findViewById(R.id.ks_page1_iv1);
        mImageView2 = (ImageView) view.findViewById(R.id.ks_page1_iv2);
        mImageView3 = (ImageView) view.findViewById(R.id.ks_page1_iv3);
        //GridView
        mGridView = (MyGridView) view.findViewById(R.id.ks_page1_gridView);
        mGridViewAdapter = new Page1GridViewAdapter(getActivity(), mCourse);

        //视频推荐
        mVideoRecycleView = (RecyclerView) view.findViewById(R.id.ks_home_video_rcView);

        //实现RecycleView显示方向
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mVideoRecycleView.setLayoutManager(manager);

        mRecycleViewAdapter = new Page1VideoRecycleViewAdapter(getActivity(), mVideoData);
        mRecycleViewAdapter.setOnItemClickListener(new Page1VideoRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), Page44VideoPlayActivity.class);
                intent.putExtra("title", mVideoData.get(position).getTitle());
                //Toast.makeText(getActivity(), "position=" + position, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        mVideoRecycleView.setAdapter(mRecycleViewAdapter);

        //更多图书按钮
        mButton6 = (LinearLayout) view.findViewById(R.id.ks_page1_book_more);

        //切换页面
        mTabHost = (FragmentTabHost) getActivity().findViewById(android.R.id.tabhost);
        //图书分类
        mRadioGroup2 = (RadioGroup) view.findViewById(R.id.ks_page1_book_rg1);
        mRadioButton2 = (RadioButton) view.findViewById(R.id.ks_page1_book_rb1);

        //图书指示器
        mImageView5 = (ImageView) view.findViewById(R.id.ks_page1_book_iv3);
        mBookGridView = (MyGridView) view.findViewById(R.id.ks_page1_book_gridView);

        //更多题库按钮
        mButton5 = (LinearLayout) view.findViewById(R.id.ks_page1_exam_more);
        //题库分类
        mRadioGroup1 = (RadioGroup) view.findViewById(R.id.ks_page1_exam_rg1);
        mRadioButton1 = (RadioButton) view.findViewById(R.id.ks_page1_exam_rb1);

        //题库指示器
        mImageView4 = (ImageView) view.findViewById(R.id.ks_page1_exam_iv3);

        initTabLine();  //获取屏幕1/5

        //考试题库
        mImageLoader = ImageLoader.getInstance();
        mExamListView = (MyListView) view.findViewById(R.id.ks_page1_exam_listView);

        //页面跳转
        mPage1Fragment = new Page1HomeFragment();

        init();
    }

    //视图操作
    private void init() {
        //todo 下拉刷新操作
        initMyRefresh();
        //设置搜索栏背景
        mLinearLayout.setBackgroundColor(Color.argb(0, 0, 191, 255));

        //改变搜索按钮的样式(上图下文字)
        mDrawable = getResources().getDrawable(R.drawable.ico_test_white);
        if (mDrawable != null) {
            mDrawable.setBounds(0, 0, 60, 60);
            mButton4.setCompoundDrawables(null, mDrawable, null, null);
        }
        initConvenientBanner(); //轮播图片操作

        //推广图片
        ImageLoaderHelper.getInstance().loadImage("drawable://" + R.drawable.ico_ks2, mImageView1);
        ImageLoaderHelper.getInstance().loadImage("drawable://" + R.drawable.ico_ks1, mImageView2);
        ImageLoaderHelper.getInstance().loadImage("drawable://" + R.drawable.ico_ks3, mImageView3);

        mGridView.setAdapter(mGridViewAdapter);//适配GridView


        mRadioButton1.setChecked(true); //课程默认指示在会计类

        //todo 适配器的实例化和适配器的绑定我们在解析数据的时候实现
        mExamListView.setOnScrollListener(new PauseOnScrollListener(mImageLoader, true, false));
        mExamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "会计" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                intent.putExtra("image", mList1Course.get(position).getImageUrl());
                intent.putExtra("name", mList1Course.get(position).getTitle());
                intent.putExtra("content", "");
                intent.putExtra("price1", mList1Course.get(position).getPrice1());
                intent.putExtra("price2", mList1Course.get(position).getPrice2());

                startActivity(intent);
            }
        });

        mRadioButton2.setChecked(true); //图书默认在会计类

        //todo 适配器的实例化和适配器的绑定我们在解析数据的时候实现
        mBookGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "会计" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                intent.putExtra("image", mGrid1Course.get(position).getImageUrl());
                intent.putExtra("name", mGrid1Course.get(position).getTitle());
                intent.putExtra("content", "");
                intent.putExtra("price1", mGrid1Course.get(position).getPrice1());
                intent.putExtra("price2", mGrid1Course.get(position).getPrice2());

                startActivity(intent);
            }
        });
        mBookGridView.setOnScrollListener(new PauseOnScrollListener(mImageLoader, true, false));


        mSearch.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mScrollView.setScrollViewListener(this);  //滚动视图监听改变标题栏颜色
        mConvenientBanner.setOnItemClickListener(this); //轮播图的点击事件
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mGridView.setOnItemClickListener(this);
        mButton5.setOnClickListener(this);
        mRadioGroup1.setOnCheckedChangeListener(this);  //课程分类的改变监听
        mButton6.setOnClickListener(this);
        mRadioGroup2.setOnCheckedChangeListener(this);  //图书分类的改变监听
    }

    //todo 下拉刷新操作
    private void initMyRefresh() {
        mRefreshLayout.setColorSchemeResources(R.color.deepSkyBlue, R.color.limeGreen, R.color.orange, R.color.red);//颜色
        mRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);  //大小
        mRefreshLayout.setProgressViewEndTarget(true, 260);   //高度
        mRefreshLayout.post(new Runnable() {                 //自动加载
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);      //开始刷新
                mScrollView.setVisibility(View.GONE);    //隐藏内容界面
                mLinearLayout.setVisibility(View.GONE);  //隐藏标题栏
            }
        });
        //下拉刷新 因为有自动刷新，所以要定义下拉刷新，刷新适配器
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mRefreshLayout.setRefreshing(false);
                                    mExamListViewAdapter.notifyDataSetChanged();
                                    mBookGridViewAdapter.notifyDataSetChanged();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    //轮播图片操作
    private void initConvenientBanner() {
        //加载网络图片
        mConvenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, mNetworkImages)
                .setPageIndicator(new int[]{R.drawable.ico_page_indicator, R.drawable.ico_page_indicator_focused})//指示器
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)//指示器位置
                .setPageTransformer(new ABaseTransformer() {
                    @Override
                    protected void onTransform(View page, float position) {
                        page.setPivotX(position < 0 ? 0 : page.getWidth());
                        page.setScaleX(position < 0 ? 1f + position : 1f - position);
                    }
                });
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page1_search:     //跳转至搜索页面
                startActivity(new Intent(getActivity(), Page6SearchActivity.class));
                break;
            case R.id.ks_page1_btn4:
                //Toast.makeText(getActivity(), "跳转至我的记录页面", Toast.LENGTH_SHORT).show();
                mTabHost.setCurrentTab(3);
                break;
            case R.id.ks_page1_content_btn1:
                //Toast.makeText(getActivity(), "btn1", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Page8AD1WebViewActivity.class));
                //startActivity(new Intent(getActivity(),Page47BuyCarActivity.class));
                break;
            case R.id.ks_page1_content_btn2:
                //Toast.makeText(getActivity(), "btn2", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Page8AD1WebViewActivity.class));
                break;
            case R.id.ks_page1_content_btn3:
                //Toast.makeText(getActivity(), "btn3", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Page8AD1WebViewActivity.class));
                break;
            case R.id.ks_page1_exam_more:
                //Toast.makeText(getActivity(), "处理更多视频课程", Toast.LENGTH_SHORT).show();
                //mTabHost.setCurrentTab(1);
                Intent intent = new Intent(getActivity(), Page7SearchResultActivity.class);
                intent.putExtra("name", "题库");
                startActivity(intent);
                break;
            case R.id.ks_page1_book_more:
                //mTabHost.setCurrentTab(1);   //切换到第二个页面
                Intent intent1 = new Intent(getActivity(), Page7SearchResultActivity.class);
                intent1.putExtra("name", "图书");
                startActivity(intent1);
                break;
        }
    }

    //滚动视图监听改变标题栏颜色
    @Override
    public void onScrollChanged(View scrollView, int x, int y, int oldX, int oldY) {
        if (y <= 500 && y >= 0) {
            float scale = (float) y / 500;
            float alpha = (float) ((scale * 255) / 1.2);

            mLinearLayout.setBackgroundColor(Color.argb((int) alpha, 0, 191, 255));
        }
    }

    //轮播图片的点击事件
    @Override
    public void onItemClick(int position) {
        //TODO 处理
        //Toast.makeText(getActivity(), "跳转页面处理" + position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), Page8AD1WebViewActivity.class));
    }

    //课程分类的GridView的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO 跳转页面处理
        switch (parent.getId()) {
            //课程分类的item点击事件
            case R.id.ks_page1_gridView:
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        String s = mCourse.get(position).getName();
                        Intent intent = new Intent(getActivity(), Page7SearchResultActivity.class);
                        intent.putExtra("name", s);
                        startActivity(intent);
                        break;
                    case 6:
                        String s1 = mCourse.get(position).getName();
                        Intent intent1 = new Intent(getActivity(), Page9FreeVideoActivity.class);
                        intent1.putExtra("name", s1);
                        startActivity(intent1);
                        break;
                    case 7:
                        Intent intent2 = new Intent(getActivity(), Page7SearchResultActivity.class);
                        intent2.putExtra("name", "");
                        startActivity(intent2);
                        break;
                }
                break;
        }
    }


    //RadioGroup的默认改变监听改变指示器位置
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {

            //图书的指示器变化
            case R.id.ks_page1_book_rg1:

                LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) mImageView5.getLayoutParams();

                switch (checkedId) {
                    case R.id.ks_page1_book_rb1:  //会计类
                        lp2.leftMargin = 0;
                        mImageView5.setLayoutParams(lp2);  //改变指示器

                        mBookGridViewAdapter = new Page1BookGridViewAdapter(getActivity(), mGrid1Course);
                        mBookGridView.setAdapter(mBookGridViewAdapter);  //改变内容数据
                        mBookGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), "会计" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                                intent.putExtra("image", mGrid1Course.get(position).getImageUrl());
                                intent.putExtra("name", mGrid1Course.get(position).getTitle());
                                intent.putExtra("content", "");
                                intent.putExtra("price1", mGrid1Course.get(position).getPrice1());
                                intent.putExtra("price2", mGrid1Course.get(position).getPrice2());

                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.ks_page1_book_rb2:  //金融类
                        lp2.leftMargin = mScreenOneFive;
                        mImageView5.setLayoutParams(lp2);

                        mBookGridViewAdapter = new Page1BookGridViewAdapter(getActivity(), mGrid2Course);
                        mBookGridView.setAdapter(mBookGridViewAdapter);
                        mBookGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), "金融" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                                intent.putExtra("image", mGrid2Course.get(position).getImageUrl());
                                intent.putExtra("name", mGrid2Course.get(position).getTitle());
                                intent.putExtra("content", "");
                                intent.putExtra("price1", mGrid2Course.get(position).getPrice1());
                                intent.putExtra("price2", mGrid2Course.get(position).getPrice2());

                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.ks_page1_book_rb3:  //建筑类
                        lp2.leftMargin = mScreenOneFive * 2;
                        mImageView5.setLayoutParams(lp2);

                        mBookGridViewAdapter = new Page1BookGridViewAdapter(getActivity(), mGrid3Course);
                        mBookGridView.setAdapter(mBookGridViewAdapter);
                        mBookGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), "建筑" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                                intent.putExtra("image", mGrid3Course.get(position).getImageUrl());
                                intent.putExtra("name", mGrid3Course.get(position).getTitle());
                                intent.putExtra("content", "");
                                intent.putExtra("price1", mGrid3Course.get(position).getPrice1());
                                intent.putExtra("price2", mGrid3Course.get(position).getPrice2());

                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.ks_page1_book_rb4:  //医学类
                        lp2.leftMargin = mScreenOneFive * 3;
                        mImageView5.setLayoutParams(lp2);

                        mBookGridViewAdapter = new Page1BookGridViewAdapter(getActivity(), mGrid4Course);
                        mBookGridView.setAdapter(mBookGridViewAdapter);
                        mBookGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), "医学" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                                intent.putExtra("image", mGrid4Course.get(position).getImageUrl());
                                intent.putExtra("name", mGrid4Course.get(position).getTitle());
                                intent.putExtra("content", "");
                                intent.putExtra("price1", mGrid4Course.get(position).getPrice1());
                                intent.putExtra("price2", mGrid4Course.get(position).getPrice2());

                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.ks_page1_book_rb5:  //教师类
                        lp2.leftMargin = mScreenOneFive * 4;
                        mImageView5.setLayoutParams(lp2);

                        mBookGridViewAdapter = new Page1BookGridViewAdapter(getActivity(), mGrid5Course);
                        mBookGridView.setAdapter(mBookGridViewAdapter);
                        mBookGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), "教师" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                                intent.putExtra("image", mGrid5Course.get(position).getImageUrl());
                                intent.putExtra("name", mGrid5Course.get(position).getTitle());
                                intent.putExtra("content", "");
                                intent.putExtra("price1", mGrid5Course.get(position).getPrice1());
                                intent.putExtra("price2", mGrid5Course.get(position).getPrice2());

                                startActivity(intent);
                            }
                        });
                        break;
                }
                break;
            //题库的指示器变化
            case R.id.ks_page1_exam_rg1:
                LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) mImageView4.getLayoutParams();

                switch (checkedId) {
                    case R.id.ks_page1_exam_rb1:  //会计类
                        lp1.leftMargin = 0;
                        mImageView4.setLayoutParams(lp1);    //改变指示器的位置

                        mExamListViewAdapter = new Page1ExamListViewAdapter(getActivity(), mList1Course);
                        mBookGridViewAdapter.notifyDataSetChanged();
                        mExamListView.setAdapter(mExamListViewAdapter);  //改变内容数据
                        mExamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), "会计类" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                                intent.putExtra("image", mList1Course.get(position).getImageUrl());
                                intent.putExtra("name", mList1Course.get(position).getTitle());
                                intent.putExtra("content", "");
                                intent.putExtra("price1", mList1Course.get(position).getPrice1());
                                intent.putExtra("price2", mList1Course.get(position).getPrice2());

                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.ks_page1_exam_rb2:  //金融类
                        lp1.leftMargin = mScreenOneFive;
                        mImageView4.setLayoutParams(lp1);


                        mExamListViewAdapter = new Page1ExamListViewAdapter(getActivity(), mList2Course);
                        mExamListView.setAdapter(mExamListViewAdapter);
                        mExamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), "金融类" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                                intent.putExtra("image", mList2Course.get(position).getImageUrl());
                                intent.putExtra("name", mList2Course.get(position).getTitle());
                                intent.putExtra("content", "");
                                intent.putExtra("price1", mList2Course.get(position).getPrice1());
                                intent.putExtra("price2", mList2Course.get(position).getPrice2());

                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.ks_page1_exam_rb3:  //建筑类
                        lp1.leftMargin = mScreenOneFive * 2;
                        mImageView4.setLayoutParams(lp1);

                        mExamListViewAdapter = new Page1ExamListViewAdapter(getActivity(), mList3Course);
                        mExamListView.setAdapter(mExamListViewAdapter);
                        mExamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), "建筑类" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                                intent.putExtra("image", mList3Course.get(position).getImageUrl());
                                intent.putExtra("name", mList3Course.get(position).getTitle());
                                intent.putExtra("content", "");
                                intent.putExtra("price1", mList3Course.get(position).getPrice1());
                                intent.putExtra("price2", mList3Course.get(position).getPrice2());

                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.ks_page1_exam_rb4:  //医学类
                        lp1.leftMargin = mScreenOneFive * 3;
                        mImageView4.setLayoutParams(lp1);

                        mExamListViewAdapter = new Page1ExamListViewAdapter(getActivity(), mList4Course);
                        mExamListView.setAdapter(mExamListViewAdapter);
                        mExamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), "医学类" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                                intent.putExtra("image", mList4Course.get(position).getImageUrl());
                                intent.putExtra("name", mList4Course.get(position).getTitle());
                                intent.putExtra("content", "");
                                intent.putExtra("price1", mList4Course.get(position).getPrice1());
                                intent.putExtra("price2", mList4Course.get(position).getPrice2());

                                startActivity(intent);

                            }
                        });
                        break;
                    case R.id.ks_page1_exam_rb5:  //教师类
                        lp1.leftMargin = mScreenOneFive * 4;
                        mImageView4.setLayoutParams(lp1);

                        mExamListViewAdapter = new Page1ExamListViewAdapter(getActivity(), mList5Course);
                        mExamListView.setAdapter(mExamListViewAdapter);
                        mExamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), "教师类" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Page10BEDetailsActivity.class);
                                intent.putExtra("image", mList5Course.get(position).getImageUrl());
                                intent.putExtra("name", mList5Course.get(position).getTitle());
                                intent.putExtra("content", "");
                                intent.putExtra("price1", mList5Course.get(position).getPrice1());
                                intent.putExtra("price2", mList5Course.get(position).getPrice2());

                                startActivity(intent);
                            }
                        });
                        break;
                }
                break;
        }
    }

    //获取屏幕的1/5  图书和题库下划线的指示器的移动
    private void initTabLine() {
        //获取屏幕的宽度
        Display mDisplay = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics mMetrics = new DisplayMetrics();
        mDisplay.getMetrics(mMetrics);

        mScreenOneFive = mMetrics.widthPixels / 5;
        LinearLayout.LayoutParams mLP1 = (LinearLayout.LayoutParams) mImageView4.getLayoutParams();
        mLP1.width = mScreenOneFive;
        mImageView4.setLayoutParams(mLP1);


        LinearLayout.LayoutParams mLP2 = (LinearLayout.LayoutParams) mImageView5.getLayoutParams();
        mLP2.width = mScreenOneFive;
        mImageView5.setLayoutParams(mLP2);
    }

    private void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            transaction.hide(from)
                    .add(R.id.ks_page1, to)
                    .addToBackStack("page1_1")
                    .commit();
        } else {
            transaction.hide(from)
                    .show(to)
                    .commit();
        }
    }
}
