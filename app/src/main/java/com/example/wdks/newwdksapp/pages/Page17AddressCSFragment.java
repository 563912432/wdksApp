package com.example.wdks.newwdksapp.pages;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.wheelview.MyFragment;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;


/**
 * 选择地区
 */
public class Page17AddressCSFragment extends MyFragment implements View.OnClickListener, OnWheelChangedListener {
    private View view, mAddress;
    private WheelView mViewProvince, mViewCity, mViewDistrict;
    private ImageView mImageView;
    private Button mButton1;
    private Page17AddressMainFragment mMainFragment;
    private Page17AddressCSFragment mCSFragment;
    private ICustomDialogEventListener mCustomDialogEventListener;
    private String s;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.page17_address_cs, container, false);
            initView();
        }
        return view;
    }


    //初始化视图
    private void initView() {
        mAddress = view.findViewById(R.id.ks_page17_address_cs);
        mImageView = (ImageView) view.findViewById(R.id.ks_page17_address_iv1);   //关闭按钮
        mViewProvince = (WheelView) view.findViewById(R.id.ks_page17_address_province);   //省
        mViewCity = (WheelView) view.findViewById(R.id.ks_page17_address_city);   //市
        mViewDistrict = (WheelView) view.findViewById(R.id.ks_page17_address_district);   //区

        mButton1 = (Button) view.findViewById(R.id.ks_page17_address_btn1);   //选择按钮
        mMainFragment = new Page17AddressMainFragment();
        mCSFragment = new Page17AddressCSFragment();
        init();
    }

    //视图操作
    private void init() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();

        mAddress.setOnClickListener(this);
        mImageView.setOnClickListener(this);
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);
        mButton1.setOnClickListener(this);
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), areas));
        mViewDistrict.setCurrentItem(0);
        mCurrentDistrictName = areas[0];
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    private void showSelectedResult() {
        Toast.makeText(getActivity(), "当前选中:" + mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName + "," + mCurrentZipCode, Toast.LENGTH_SHORT).show();
    }


    public static Page17AddressCSFragment newsInstance(String text) {
        Page17AddressCSFragment page17AddressCSFragment = new Page17AddressCSFragment();
        Bundle args = new Bundle();
        args.putString("param", text);
        page17AddressCSFragment.setArguments(args);
        return page17AddressCSFragment;
    }

    /**
     * 回调结果值
     *
     * @param listener
     */
    public void setResultListener(ICustomDialogEventListener listener) {
        mCustomDialogEventListener = listener;
    }


    //自定义接口
    public interface ICustomDialogEventListener {
        void customDialogEvent(String content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page17_address_cs:
                getFragmentManager().popBackStack();
                break;
            case R.id.ks_page17_address_iv1:
                getFragmentManager().popBackStack();
                break;
            case R.id.ks_page17_address_btn1:   //传值返回
                s = mCurrentProviceName + " " + mCurrentCityName + " " + mCurrentDistrictName;
                mCustomDialogEventListener.customDialogEvent(s);
                getFragmentManager().popBackStack();
                break;
        }
    }
}
