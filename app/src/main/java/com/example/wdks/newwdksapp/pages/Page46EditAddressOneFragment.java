package com.example.wdks.newwdksapp.pages;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;


/**
 * 编辑收货地址的第一个Fragment
 */
public class Page46EditAddressOneFragment extends Fragment implements View.OnClickListener {
    private View view, mBack, mSave, mEditAddress;
    private EditText mName, mContact, mAddress;
    private TextView mDistrict;
    private String to_name, to_contact, to_district, to_address;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    //初始化数据
    private void initData() {
        Intent intent = getActivity().getIntent();
        to_name = intent.getExtras().getString("to_name");
        to_contact = intent.getExtras().getString("to_contact");
        to_district = intent.getExtras().getString("to_district");
        to_address = intent.getExtras().getString("to_address");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page46_edit_address_one, null);
            initView();
        }
        return view;
    }

    //初始化视图
    private void initView() {
        mBack = view.findViewById(R.id.ks_page46_address_one_back);   //返回按钮
        mSave = view.findViewById(R.id.ks_page46_address_one_save);   //保存操作
        mName = (EditText) view.findViewById(R.id.ks_page46_address_one_ed1);   //姓名
        mContact = (EditText) view.findViewById(R.id.ks_page46_address_one_ed2);   //联系方式
        mEditAddress = view.findViewById(R.id.ks_page46_address_one_address);   //点击进入修改地址页面
        mDistrict = (TextView) view.findViewById(R.id.ks_page46_address_one_tv2);   //显示地区
        mAddress = (EditText) view.findViewById(R.id.ks_page46_address_one_ed3);   //详细地址

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);
        mSave.setOnClickListener(this);
        mEditAddress.setOnClickListener(this);

        mName.setText(to_name);
        mContact.setText(to_contact);
        mDistrict.setText(to_district);
        mAddress.setText(to_address);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page46_address_one_back:   //返回按钮
                getActivity().finish();
                break;
            case R.id.ks_page46_address_one_address:   //跳转到获取地址的页面，获取返回值

                hideInputWindow(getActivity());   //隐藏软键盘

                Page46EditAddressTwoFragment page46EditAddressTwoFragment = Page46EditAddressTwoFragment.newsInstance("获取返回值");
                page46EditAddressTwoFragment.setResultListener(new Page46EditAddressTwoFragment.ICustomDialogEventListener() {
                    @Override
                    public void customDialogEvent(String content) {
                        mDistrict.setText(content);
                    }
                });
                getFragmentManager().beginTransaction()
                        .add(R.id.ks_page46_address_one, page46EditAddressTwoFragment)
                        .addToBackStack("page46_one")
                        .commit();
                break;
            case R.id.ks_page46_address_one_save:   //保存操作
                String name = mName.getText().toString();   //姓名
                String contact = mContact.getText().toString();   //联系方式
                String district = mDistrict.getText().toString();   //地区
                String address = mAddress.getText().toString();   //详细地址

                if (name.equals("")) {
                    Toast.makeText(getActivity(), "请输入姓名", Toast.LENGTH_SHORT).show();
                } else if (contact.equals("")) {
                    Toast.makeText(getActivity(), "请输入联系方式", Toast.LENGTH_SHORT).show();
                } else if (district.equals("请选择地区")) {
                    Toast.makeText(getActivity(), "请选择地区", Toast.LENGTH_SHORT).show();
                } else if (address.equals("")) {
                    Toast.makeText(getActivity(), "请输入详细地址", Toast.LENGTH_SHORT).show();
                } else {
                    //保存操作
                    // 设置Intent回调 并设置回调内容
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("contact", contact);
                    intent.putExtra("district", district);
                    intent.putExtra("address", address);
                    //添加返回结果
                    getActivity().setResult(6, intent);
                    //关闭掉当前页面
                    getActivity().finish();

                    break;

                }
        }
    }

    //判断键盘是否显示，如果显示，将其隐藏掉
    public void hideInputWindow(Activity context) {
        if (context == null) {
            return;
        }
        final View v = ((Activity) context).getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
