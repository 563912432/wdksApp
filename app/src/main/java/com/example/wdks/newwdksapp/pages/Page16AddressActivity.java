package com.example.wdks.newwdksapp.pages;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.adapter.Page16AddressListViewAdapter;
import com.example.wdks.newwdksapp.data.Address;
import com.example.wdks.newwdksapp.tools.MyInitWindowLBlue;
import com.example.wdks.newwdksapp.tools.MyListView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

/*
* 收货地址管理页面
* */
public class Page16AddressActivity extends AppCompatActivity implements View.OnClickListener,
        Page16AddressListViewAdapter.MyClickListener {
    private View mBack, mEmpty, mNoEmpty;
    private MyListView mListView;
    private List<Address> mAddress;
    private Page16AddressListViewAdapter mAdapter;
    private Button mButton1;
    private String back_name, back_contact, back_district, back_address;
    private int edit_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page16_address);
        MyInitWindowLBlue.initWindow(this);   //api 19后自定义状态栏颜色  浅蓝色
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mAddress = new ArrayList<Address>();
        mAddress.add(new Address("陈帅", "15275969278", "山东省淄博市张店区", "测试地址"));
        mAddress.add(new Address("再见孙悟空", "13313314241", "山东省淄博市张店区", "国贸大厦"));
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page16_back);   //返回按钮
        mListView = (MyListView) findViewById(R.id.ks_page16_listView);   //主要内容
        mAdapter = new Page16AddressListViewAdapter(this, mAddress);
        mListView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);

        mEmpty = findViewById(R.id.ks_page16_content_empty);   //收货地址为空
        mNoEmpty = findViewById(R.id.ks_page16_content_noEmpty);   //收货地址不为空

        mButton1 = (Button) findViewById(R.id.ks_page16_btn1);   //添加新地址

        init();
    }

    //视图操作
    private void init() {
        //判断收货地址是否为空
        if (mAddress == null || mAddress.size() == 0) {
            mEmpty.setVisibility(View.VISIBLE);
            mNoEmpty.setVisibility(View.GONE);
        } else {
            mNoEmpty.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);
        }

        mBack.setOnClickListener(this);
        mButton1.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page16_back:   //返回按钮
                finish();
                break;
            case R.id.ks_page16_btn1:   //添加新地址按钮
                //Toast.makeText(this, "跳转到添加新地址页面", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(Page16AddressActivity.this, Page17NewAddressActivity.class));
                Intent intent = new Intent(Page16AddressActivity.this, Page17NewAddressActivity.class);
                startActivityForResult(intent, 3);
                break;
        }

    }

    /**
     * 设置跳转  接受返回数据
     *
     * @param requestCode 请求码
     * @param resultCode  返回码
     * @param data        返回数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  如果请求码与返回码等于预期设置的值  则进行后续操作
        if (requestCode == 3 && resultCode == 4) {
            //获取返回的数据并显示
            String name = data.getExtras().getString("name");
            String contact = data.getExtras().getString("contact");
            String district = data.getExtras().getString("district");
            String address = data.getExtras().getString("address");

            mAddress.add(new Address(name, contact, district, address));
            mAdapter.notifyDataSetChanged();
        }
        if (requestCode == 5 && resultCode == 6) {
            //获取返回的数据替代原来数据并显示
            back_name = data.getExtras().getString("name");
            back_contact = data.getExtras().getString("contact");
            back_district = data.getExtras().getString("district");
            back_address = data.getExtras().getString("address");

            mAddress.get(edit_position).setName(back_name);
            mAddress.get(edit_position).setTel(back_contact);
            mAddress.get(edit_position).setDistrict(back_district);
            mAddress.get(edit_position).setAddress(back_address);

            mAdapter.notifyDataSetChanged();
        }
    }

    //设置为默认的按钮点击
    @Override
    public void onChose(BaseAdapter adapter, View view, int position) {
        mAdapter.changeSelected(position);
        String backName = mAddress.get(position).getName();
        String backTel = mAddress.get(position).getTel();
        String backDistrict = mAddress.get(position).getDistrict();
        String backAddress = mAddress.get(position).getAddress();

        // 设置Intent回调 并设置回调内容
        Intent intent = new Intent();
        intent.putExtra("backName", backName);
        intent.putExtra("backTel", backTel);
        intent.putExtra("backDistrict", backDistrict);
        intent.putExtra("backAddress", backAddress);
        setResult(2, intent);
        //关闭
        finish();
    }

    //编辑按钮的点击事件
    @Override
    public void onEditor(BaseAdapter adapter, View view, int position) {
        edit_position = position;
        //逻辑：携带数据的跳转，携带数据的返回
        Intent intent = new Intent(Page16AddressActivity.this, Page46EditAddressActivity.class);
        String to_name = mAddress.get(position).getName();
        String to_contact = mAddress.get(position).getTel();
        String to_district = mAddress.get(position).getDistrict();
        String to_address = mAddress.get(position).getAddress();

        intent.putExtra("to_name", to_name);
        intent.putExtra("to_contact", to_contact);
        intent.putExtra("to_district", to_district);
        intent.putExtra("to_address", to_address);

        startActivityForResult(intent, 5);

    }

    //删除按钮的点击事件
    @Override
    public void onDelete(BaseAdapter adapter, View view, int position) {
        Toast.makeText(this, "删除地址操作" + position, Toast.LENGTH_SHORT).show();
        mAddress.remove(position);
        mAdapter.notifyDataSetChanged();
    }
}
