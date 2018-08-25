package com.example.wdks.newwdksapp.pages;


import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyProgressDialogConn;

/**
 * 首页没有网络连接时显示的页面
 */
public class Page5ConnP1Fragment extends Fragment {
    private View view;
    private ImageView mImageView;
    private Dialog mDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.page5_conn_p1, container, false);
            initView();
        }

        return view;
    }

    //初始化视图
    private void initView() {
        mImageView = (ImageView) view.findViewById(R.id.ks_page5_iv1);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyProgressDialogConn dialog = new MyProgressDialogConn();
                mDialog = dialog.createLoader(getActivity());
                mDialog.show();
                ConnectivityManager conManger = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = conManger.getActiveNetworkInfo();
                if (info != null) {
                    //有网络连接
                    getFragmentManager().beginTransaction()
                            .add(R.id.ks_page5_p1, new Page1HomeFragment())
                            .commit();
                    mDialog.dismiss();
                } else {
                    //没有网络连接 加载框显示2秒后消失
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mDialog.dismiss();
                        }
                    }).start();
                }
            }
        });
    }

}
