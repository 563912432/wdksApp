package com.example.wdks.newwdksapp.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.tools.MyInitWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/*
* 个人资料修改页面
* */
public class Page18PSDataActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack;
    private CircleImageView mCircleImageView;
    private ImageView mImageView1;
    private TextView mTextView1, mTextView2;
    private Button mButton1;
    private static int CAMERA_REQUEST_CODE = 1;
    private static int GALLERY_REQUEST_CODE = 2;
    private static int CROP_REQUEST_CODE = 3;
    private AlertDialog mAlertDialog1;
    private AlertDialog.Builder mBuilder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page18_ps_data);
        MyInitWindow.initWindow(this);
        initView();
    }

    //bitmap数据转化为uri（将bitmap写入sd卡中的一个图像文件中，发挥该图像的uri，既实现了将图片保存到了sd卡中
    // 又实现了把content类型的uri转化为file类型的uri）
    private Uri saveBitmap(Bitmap bitmap) {
        //新建一个路径
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/com.kaoshiApp.avater");
        if (!tmpDir.exists()) {
            tmpDir.mkdir();   //不存在新建一个
        }
        File img = new File(tmpDir.getAbsolutePath() + "avater.png");   //创建要保存文件的对象
        try {
            FileOutputStream fos = new FileOutputStream(img);
            //将图像的数据写入该输出流中(第一个参数为压缩格式，第二个参数为图像的质量，第三个参数为要写入文件的输出流)
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, fos);
            fos.flush();
            fos.close();
            //产生一个file类型的uri作为返回值返回
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //新建方法转化uri（content类型的uri->file类型的uri)
    private Uri convertUri(Uri uri) {
        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(uri);   //inputStream
            Bitmap bitmap = BitmapFactory.decodeStream(is);   //inputStream转为bitmap
            if (is != null) {
                is.close();   //关闭inputStream
            }
            return saveBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //裁剪图片
    private void startImageZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("Crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    //request的请求完成之后返回结果调到地方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {   //判断是否是我们定义的请求码
            if (data == null) {
                return;   //用户点击取消，直接返回，不再处理

            } else {
                Bundle bundle = data.getExtras();  //取数据
                if (bundle != null) {
                    Bitmap bm = bundle.getParcelable("data");   //用bitmap保存数据
                    Uri uri = saveBitmap(bm);   //获取bm的uri
                    startImageZoom(uri);   //启动裁剪界面（参数必须为file类型的uri）
                }
            }
        } else if (requestCode == GALLERY_REQUEST_CODE) {
            if (data == null) {
                return;
            }
            Uri uri = data.getData();   //获取图片的uri（同一资源标识符）
            //获取的uri为content类型的uri，不能直接进行操作，需要转化为file类型的uri
            Uri fileUri = convertUri(uri);
            startImageZoom(fileUri);   //裁剪页面，参数为file类型的uri
        } else if (requestCode == CROP_REQUEST_CODE) {
            if (data == null) {
                return;
            }
            Bundle bundle = data.getExtras();
            Bitmap bitmap = bundle.getParcelable("data");
            mCircleImageView.setImageBitmap(bitmap);
            mAlertDialog1.dismiss();
        }
    }

    //关闭对话框
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAlertDialog1 != null) {
            mAlertDialog1.dismiss();
        }
    }

    //初始化视图
    private void initView() {
        mBack = findViewById(R.id.ks_page18_back);   //返回按钮
        mCircleImageView = (CircleImageView) findViewById(R.id.id_page18_iv1);   //修改头像
        mImageView1 = (ImageView) findViewById(R.id.id_page18_iv2);   //修改密码
        mTextView1 = (TextView) findViewById(R.id.id_page18_tv1);   //修改邮箱
        mTextView2 = (TextView) findViewById(R.id.id_page18_tv2);   //显示邮箱
        mButton1 = (Button) findViewById(R.id.ks_page18_btn1);   //退出登录

        init();
    }

    //视图操作
    private void init() {

        mBack.setOnClickListener(this);
        mCircleImageView.setOnClickListener(this);
        mImageView1.setOnClickListener(this);
        mTextView1.setOnClickListener(this);
        mButton1.setOnClickListener(this);
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ks_page18_back:   //返回按钮
                finish();
                break;
            case R.id.id_page18_iv1:   //修改头像
                View view = LayoutInflater.from(this).inflate(R.layout.page18_setting_tx, null);
                Button mButton1 = (Button) view.findViewById(R.id.ks_page18_tx_btn1);   //拍照
                Button mButton2 = (Button) view.findViewById(R.id.ks_page18_tx_btn2);   //从相册中选取
                Button mButton3 = (Button) view.findViewById(R.id.ks_page18_tx_btn3);   //取消

                mBuilder1 = new AlertDialog.Builder(Page18PSDataActivity.this);
                mBuilder1.setView(view)
                        .setCancelable(false);
                mAlertDialog1 = mBuilder1.show();

                mButton1.setOnClickListener(this);   //从摄像头中获取
                mButton2.setOnClickListener(this);   //从相册中获取
                mButton3.setOnClickListener(this);   //取消

                break;
            case R.id.id_page18_iv2:   //修改密码
                final View view1 = LayoutInflater.from(this).inflate(R.layout.page18_setting_pwd, null);
                View mPassword = view1.findViewById(R.id.ks_page18_password);
                mPassword.setVisibility(View.VISIBLE);
                //对话框显示原来的密码，修改密码并显示
                mBuilder1 = new AlertDialog.Builder(Page18PSDataActivity.this);
                mBuilder1.setView(view1)
                        .setCancelable(false);
                mAlertDialog1 = mBuilder1.show();
                //确定修改
                Button mConfirm1 = (Button) view1.findViewById(R.id.ks_page18_confirm1);
                mConfirm1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText mUNEditText1 = (EditText) view1.findViewById(R.id.ks_page18_un_et1);
                        mAlertDialog1.dismiss();
                    }
                });
                //取消按钮
                Button mConfirm2 = (Button) view1.findViewById(R.id.ks_page18_confirm2);
                mConfirm2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAlertDialog1.dismiss();
                    }
                });
                break;
            case R.id.id_page18_tv1:   //修改邮箱
                //加载相同的布局文件，将字段名称改掉
                final View view2 = LayoutInflater.from(this).inflate(R.layout.page18_setting_pwd, null);
                TextView mPre = (TextView) view2.findViewById(R.id.ks_page18_un_pre);
                TextView mNow = (TextView) view2.findViewById(R.id.ks_page18_un_now);

                mPre.setText("原邮箱地址：");
                mNow.setText("现邮箱地址：");

                //对话框显示原来邮箱地址，修改邮箱地址并显示
                TextView mUNTextView1 = (TextView) view2.findViewById(R.id.ks_page18_un_tv1);
                mUNTextView1.setText(mTextView2.getText());
                mBuilder1 = new AlertDialog.Builder(this);
                mBuilder1.setView(view2)
                        .setCancelable(false);
                mAlertDialog1 = mBuilder1.show();

                //修改按钮
                Button mConfirm3 = (Button) view2.findViewById(R.id.ks_page18_confirm1);
                mConfirm3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText mUNEditText1 = (EditText) view2.findViewById(R.id.ks_page18_un_et1);
                        mTextView2.setText(mUNEditText1.getText());
                        mAlertDialog1.dismiss();
                    }
                });
                //取消按钮
                Button mConfirm4 = (Button) view2.findViewById(R.id.ks_page18_confirm2);
                mConfirm4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAlertDialog1.dismiss();
                    }
                });
                break;
            case R.id.ks_page18_btn1:   //退出登录
                //还应该清除数据
                //1- 清除本地share保存的数据
                SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
                preferences.edit().clear().apply();
                finish();
                break;
            case R.id.ks_page18_tx_btn1:   //拍照
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //获取拍摄的意图
                startActivityForResult(intent, CAMERA_REQUEST_CODE);   //打开新页面，传入请求码
                break;
            case R.id.ks_page18_tx_btn2:   //从相册中获取
                Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);   //获取内容选择的intent
                intent2.setType("image/*");   //设置类型为image类型
                startActivityForResult(intent2, GALLERY_REQUEST_CODE);   //打开新页面，传入请求码
                break;
            case R.id.ks_page18_tx_btn3:   //取消（关闭对话框）
                mAlertDialog1.dismiss();
                break;
        }
    }
}
