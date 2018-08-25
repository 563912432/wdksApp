package com.example.wdks.newwdksapp.pages;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page4ListViewAdapter;
import com.example.wdks.newwdksapp.adapter.Page4MemberExamExpandListViewAdapter;
import com.example.wdks.newwdksapp.adapter.Page4MemberVideoExpandListViewAdapter;
import com.example.wdks.newwdksapp.data.MyExam;
import com.example.wdks.newwdksapp.data.MyVideo;
import com.example.wdks.newwdksapp.tools.MyExpandableListView;
import com.example.wdks.newwdksapp.tools.MyListView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 会员中心页
 */
public class Page4MemberFragment extends Fragment implements View.OnClickListener, ExpandableListView.OnChildClickListener,
        AdapterView.OnItemClickListener {

    private View view, mEmpty, mEmptyParent, mEmptyChild;
    private CircleImageView mImageView1;
    private TextView mTextView1, mTextView2, mGoBuy;
    private FragmentTabHost mTabHost;
    private LinearLayout mPMessage, mYu, mBuyCar;
    private List<MyExam> mGroup1, mMenu;
    private List<List<MyExam>> mChild1;
    private List<MyExam> list1;
    private MyExpandableListView mExpandList1, mExpandList2;
    private Page4MemberExamExpandListViewAdapter mExpandList1Adapter;
    private List<MyVideo> mGroup2;
    private List<List<MyVideo>> mChild2;
    private Page4MemberVideoExpandListViewAdapter mmExpandList2Adapter;
    private MyListView mListView;
    private Page4ListViewAdapter mListViewAdapter;
    private boolean isFirstLogin = true;
    private ImageView mArrow;
    private boolean isEmpty = true;
    private long mExitTime = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    //初始化数据
    private void initData() {
        mGroup1 = new ArrayList<MyExam>();
        mGroup1.add(new MyExam("我的题库"));

        mChild1 = new ArrayList<List<MyExam>>();
        list1 = new ArrayList<MyExam>();
        list1.add(new MyExam("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg", "会计基础",
                "到期时间：", "2016-10-9"));
        list1.add(new MyExam("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg", "会计电算化",
                "到期时间：", "2016-10-9"));
        list1.add(new MyExam("https://img.alicdn.com/bao/uploaded/i3/TB1rHkCKpXXXXX5XVXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
                "财经法规与会计职业道德财经法规与会计职业道德财经法规与会计职业道德财经法规与会计职业道德",
                "已到期", null));
        mChild1.add(list1);

        mGroup2 = new ArrayList<MyVideo>();
        mGroup2.add(new MyVideo("课程观看记录"));

        mChild2 = new ArrayList<List<MyVideo>>();
        List<MyVideo> list2 = new ArrayList<MyVideo>();
        list2.add(new MyVideo("http://a1.jikexueyuan.com/home/201604/11/6e01/570b17fe71605.jpg", "会计基础",
                "观看到", "第一章 第一节", "18:12", "继续观看"));
        list2.add(new MyVideo("http://a1.jikexueyuan.com/home/201605/10/bb41/5731437213380.jpg", "会计电算化",
                "观看到", "第二章 第十节", "10:00", "继续观看"));
        list2.add(new MyVideo("http://a1.jikexueyuan.com/home/201605/09/2497/572fedc688602.jpg",
                "财经法规与会计职业道德财经法规与会计职业道德", "已看完", null, null, "重新观看"));
        mChild2.add(list2);

        //剩余菜单ListView的数据
        mMenu = new ArrayList<MyExam>();
        mMenu.add(new MyExam("drawable://" + R.drawable.ico_order_record, "订单管理"));
        mMenu.add(new MyExam("drawable://" + R.drawable.ico_goods_address, "收货地址管理"));
        mMenu.add(new MyExam("drawable://" + R.drawable.ico_version, "测试版本"));
        mMenu.add(new MyExam("drawable://" + R.drawable.ico_feedback, "问题反馈"));
        mMenu.add(new MyExam("drawable://" + R.drawable.ico_about, "关于我们"));
        mMenu.add(new MyExam("drawable://" + R.drawable.ico_service, "联系客服"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page4_member, container, false);
            initView();
        }
        return view;
    }

    //页面重新加载生命周期会从onResume开始
    @Override
    public void onResume() {
        super.onResume();
        getLoginInfo(); //获取登录信息
        getFocus();  //监听返回键
    }

    private void getLoginInfo() {
               /*
        * 这个页面是登录逻辑的第一个页面，我们直接获取shared，并定义isFirstLogin为true
        * 然后定义逻辑当为true的时候TextView显示为“点击登录”，并且能点击登录
        *     定义逻辑当为false的时候TextView显示登录后的用户名，并且取消点击
        * =>进登录页面，用户名和密码正确之后，存入shared，并将isFirstLogin改为false
        * */
        SharedPreferences preferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        isFirstLogin = preferences.getBoolean("isFirstLogin", true);
        if (isFirstLogin) {
            mTextView1.setText("点击登录");
            mTextView1.setOnClickListener(this);
        } else {
            String name = preferences.getString("name", "");
            mTextView1.setText(name);
            mTextView1.setClickable(false);
        }
    }

    private void getFocus() {
        if (getView() != null) {
            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        if ((System.currentTimeMillis() - mExitTime) > 2000) {
                            // 如果两次按键时间间隔大于2000毫秒，则不退出
                            //Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                            mExitTime = System.currentTimeMillis();// 更新mExitTime
                        } else {
                            getActivity().finish();
                            System.exit(0);// 否则退出程序
                        }
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    //初始化视图
    private void initView() {
        mImageView1 = (CircleImageView) view.findViewById(R.id.ks_page4_iv1);      //头像
        mTextView1 = (TextView) view.findViewById(R.id.ks_page4_tv1);              //点击登录文字
        mPMessage = (LinearLayout) view.findViewById(R.id.ks_page4_p_info);        //点击修改个人资料
        mYu = (LinearLayout) view.findViewById(R.id.ks_page4_line_yu);              //学习卡余额
        mTextView2 = (TextView) view.findViewById(R.id.ks_page4_tv2);               //显示余额
        mBuyCar = (LinearLayout) view.findViewById(R.id.ks_page4_line_car);         //我的购物车

        //我的题库
        mExpandList1 = (MyExpandableListView) view.findViewById(R.id.ks_page4_expandList1);
        mExpandList1Adapter = new Page4MemberExamExpandListViewAdapter(getActivity(), mGroup1, mChild1);
        mExpandList1.setAdapter(mExpandList1Adapter);

        //我的题库为空
        mEmpty = view.findViewById(R.id.ks_page4_member_exam_empty);
        mEmptyParent = view.findViewById(R.id.ks_page4_member_exam_empty_parent);   //父布局
        mEmptyChild = view.findViewById(R.id.ks_page4_member_exam_empty_child);   //要展开的子布局
        mArrow = (ImageView) view.findViewById(R.id.ks_page4_member_exam_empty_iv);   //箭头
        mArrow.setImageResource(R.drawable.ico_up_gray);   //向上和向下的箭头
        mGoBuy = (TextView) view.findViewById(R.id.ks_page4_member_exam_empty_tv);   //前往购买
        mTabHost = (FragmentTabHost) getActivity().findViewById(android.R.id.tabhost);   //切换tab


        //视频课程观看记录
        mExpandList2 = (MyExpandableListView) view.findViewById(R.id.ks_page4_expandList2);
        mmExpandList2Adapter = new Page4MemberVideoExpandListViewAdapter(getActivity(), mGroup2, mChild2);
        mExpandList2.setAdapter(mmExpandList2Adapter);

        //剩余菜单
        mListView = (MyListView) view.findViewById(R.id.ks_page4_listView);
        mListViewAdapter = new Page4ListViewAdapter(getActivity(), mMenu);
        mListView.setAdapter(mListViewAdapter);

        init();  //视图操作
    }

    //视图操作
    private void init() {

        mTextView2.setText("50.00");
        //获取屏幕的宽度
        Display mDisplay = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics mMetrics = new DisplayMetrics();
        mDisplay.getMetrics(mMetrics);

        if (list1 == null || list1.size() == 0) {
            Toast.makeText(getActivity(), "空", Toast.LENGTH_SHORT).show();
            mExpandList1.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        } else {
            mExpandList1.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);
            //设置指示器的位置
            mExpandList1.setIndicatorBounds(mMetrics.widthPixels - 120, mMetrics.widthPixels - 10);
            mExpandList1.expandGroup(0);   //默认展开
            mExpandList1.setOnChildClickListener(this);
        }

        mExpandList2.setIndicatorBounds(mMetrics.widthPixels - 120, mMetrics.widthPixels - 10);

        mEmptyParent.setOnClickListener(this);
        mGoBuy.setOnClickListener(this);
        mImageView1.setOnClickListener(this);
        mTextView1.setOnClickListener(this);
        mPMessage.setOnClickListener(this);
        mYu.setOnClickListener(this);
        mBuyCar.setOnClickListener(this);
        mmExpandList2Adapter.setOnClickListener(new GoOnClickListener());
        mListView.setOnItemClickListener(this);
    }

    //点击事件监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page4_iv1:
                Toast.makeText(getActivity(), "第一次点击登录+再次点击设置个人信息", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Page11LoginActivity.class));
                break;
            case R.id.ks_page4_tv1:
                Toast.makeText(getActivity(), "第一次点击登录+再次显示个人用户名不能点击", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Page11LoginActivity.class));
                break;
            case R.id.ks_page4_p_info:
                //Toast.makeText(getActivity(), "点击进入修改个人资料页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Page18PSDataActivity.class));
                break;
            case R.id.ks_page4_line_yu:
                //Toast.makeText(getActivity(), "点击进入我的学习卡页面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Page19StudyCardActivity.class);
                intent.putExtra("number", mTextView2.getText().toString());
                startActivity(intent);
                break;
            case R.id.ks_page4_line_car:
                //Toast.makeText(getActivity(), "点击进入我的购物车页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Page14BuyCarActivity.class));
                break;
            case R.id.ks_page4_member_exam_empty_parent:
                if (isEmpty) {
                    mEmptyChild.setVisibility(View.GONE);
                    mArrow.setImageResource(R.drawable.ico_down_gray);
                    isEmpty = false;
                } else {
                    mEmptyChild.setVisibility(View.VISIBLE);
                    mArrow.setImageResource(R.drawable.ico_up_gray);
                    isEmpty = true;
                }
                break;
            case R.id.ks_page4_member_exam_empty_tv:   //前往购买
                mTabHost.setCurrentTab(1);
                break;
        }

    }

    //我的题库的expandableListView的点击监听
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        switch (parent.getId()) {
            case R.id.ks_page4_expandList1:
                String title = mChild1.get(groupPosition).get(childPosition).getName();
                Intent intent = new Intent(getActivity(), Page20MyExamActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
                break;
        }
        return false;
    }

    private class GoOnClickListener implements Page4MemberVideoExpandListViewAdapter.MyClickListener {
        @Override
        public void onWatch(BaseExpandableListAdapter adapter, View view, int position) {
            Toast.makeText(getActivity(), "观看视频" + position, Toast.LENGTH_SHORT).show();
        }
    }

    //listView的Item监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        switch (position) {
            case 0:   //订单记录
                startActivity(new Intent(getActivity(), Page39OrderRecordActivity.class));
                break;
            case 1:   //收货地址管理
                startActivity(new Intent(getActivity(), Page16AddressActivity.class));
                break;
            case 2:   //版本检测
                Toast.makeText(getActivity(), "版本测试", Toast.LENGTH_SHORT).show();
                break;
            case 3:   //问题反馈
                startActivity(new Intent(getActivity(), Page42FeedbackActivity.class));
                break;
            case 4:   //关于我们
                startActivity(new Intent(getActivity(), Page43AboutUsActivity.class));
                break;
            case 5:   //联系客服(调取本机跳转到拨号页面)
                Uri uri = Uri.parse("tel:10086");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;

        }
    }
}
