package com.example.wdks.newwdksapp.pages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;

/*
* 新增收货地址页面主要加载的fragment
* */
public class Page17AddressMainFragment extends Fragment implements View.OnClickListener {
    private View view, mAddress, mBack, mSave;
    private TextView mTextView1, mTextView2;
    private EditText mInput_name, mInput_contact, mInput_address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page17_address_main, container, false);
            initView();
        }
        return view;
    }

    //初始化视图
    private void initView() {

        mBack = view.findViewById(R.id.ks_page17_address_main_back);   //返回按钮
        mSave = view.findViewById(R.id.ks_page17_save);   //保存操作

        mInput_name = (EditText) view.findViewById(R.id.ks_page17_address_main_ed1);   //输入真实姓名
        mInput_contact = (EditText) view.findViewById(R.id.ks_page17_address_main_ed2);   //输入联系方式
        mAddress = view.findViewById(R.id.ks_page17_address_main_address);   //选择地区
        mTextView2 = (TextView) view.findViewById(R.id.ks_page17_address_main_tv2);   //地区显示
        mInput_address = (EditText) view.findViewById(R.id.ks_page17_address_main_ed3);   //输入详细地址

        init();
    }

    //视图操作
    private void init() {
        mBack.setOnClickListener(this);
        mSave.setOnClickListener(this);
        mAddress.setOnClickListener(this);
    }


    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page17_address_main_back:   //返回
                getActivity().finish();
                break;
            case R.id.ks_page17_save:   //保存操作
                String name = mInput_name.getText().toString();   //姓名
                String contact = mInput_contact.getText().toString();   //联系方式
                String district = mTextView2.getText().toString();   //地区
                String address = mInput_address.getText().toString();   //详细地址

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
                    getActivity().setResult(4, intent);
                    //关闭掉当前页面
                    getActivity().finish();
                }
                break;
            case R.id.ks_page17_address_main_address:   //跳转到选择地址的页面选择地址后获取返回值

                hideInputWindow(getActivity());   //隐藏软键盘

                Page17AddressCSFragment page17AddressCSFragment = Page17AddressCSFragment.newsInstance("获取返回值");
                page17AddressCSFragment.setResultListener(new Page17AddressCSFragment.ICustomDialogEventListener() {
                    @Override
                    public void customDialogEvent(String content) {
                        mTextView2.setText(content);
                    }
                });
                getFragmentManager().beginTransaction()
                        .add(R.id.ks_page17_address_main, page17AddressCSFragment)
                        .addToBackStack("page17_address_main")
                        .commit();
                break;
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
