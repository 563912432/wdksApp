package com.example.wdks.newwdksapp.pages;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wdks.newwdksapp.R;
import com.example.wdks.newwdksapp.data.BuyCar;
import com.example.wdks.newwdksapp.tools.ImageLoaderHelper;
import com.example.wdks.newwdksapp.tools.MyInitWindowGray;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/*
* 购物车页面
* */
public class Page14BuyCarActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBack, mEmpty, mBottom;
    private static final int INITIALIZE = 0;
    private ListView mListView;// 列表
    private ListAdapter mListAdapter;// adapter
    private List<BuyCar> mListData = new ArrayList<BuyCar>();// 数据
    private boolean isBatchModel;// 是否可删除模式
    private LinearLayout mButtonLayout;
    private CheckBox mCheckAll; // 全选 全不选
    private LinearLayout mLineEdit;   //编辑的大布局
    private TextView mEdit; // 切换到删除模式
    private TextView mPriceAll; // 商品总价
    private TextView mSelectNum; // 选中数量
    private Button mDelete; // 删除 结算
    private int totalPrice = 0; // 商品总价
    /**
     * 批量模式下，用来记录当前选中状态
     */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();
    private DecimalFormat df = new DecimalFormat("#####0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page14_buy_car);
        MyInitWindowGray.initWindow(this);    //api 19后自定义状态栏颜色  浅灰色

        initView();
        initListener();
        loadData();
    }

    private void doDelete(List<Integer> ids) {
        for (int i = 0; i < mListData.size(); i++) {
            long dataId = mListData.get(i).getId();
            for (int j = 0; j < ids.size(); j++) {
                int deleteId = ids.get(j);
                if (dataId == deleteId) {
                    mListData.remove(i);
                    i--;
                    ids.remove(j);
                    j--;
                }
            }
        }

        refreshListView();
        mSelectState.clear();
        totalPrice = 0;
        mSelectNum.setText("已选" + 0 + "件商品");
        mPriceAll.setText("￥" + 0.00 + "");
        mCheckAll.setChecked(false);

    }

    private final List<Integer> getSelectedIds() {
        ArrayList<Integer> selectedIds = new ArrayList<Integer>();
        for (int index = 0; index < mSelectState.size(); index++) {
            if (mSelectState.valueAt(index)) {
                selectedIds.add(mSelectState.keyAt(index));
            }
        }
        return selectedIds;
    }

    private void initView() {

        mBack = findViewById(R.id.ks_page14_back);   //返回按钮
        mBottom = findViewById(R.id.ks_page14_bottom_bar);   //底部布局
        mButtonLayout = (LinearLayout) findViewById(R.id.ks_page14_allPrice_total);   //底部中间显示的价格和计件数的父布局
        mCheckAll = (CheckBox) findViewById(R.id.ks_page14_check_box);   //全部选择按钮
        mLineEdit = (LinearLayout) findViewById(R.id.ks_page14_line_subtitle);   //编辑的大布局
        mEdit = (TextView) findViewById(R.id.ks_page14_subtitle);   //完成或者编辑按钮
        mPriceAll = (TextView) findViewById(R.id.ks_page14_total);   //显示总价
        mSelectNum = (TextView) findViewById(R.id.ks_page14_select_num);   //显示总的件数
        mDelete = (Button) findViewById(R.id.ks_page14_buy_or_del);   //结算或者删除按钮
        mListView = (ListView) findViewById(R.id.ks_page14_listView);   //主要内容的ListView
        mEmpty = findViewById(R.id.ks_page14_empty);   //数据为空时显示的界面
    }

    private void initListener() {
        mBack.setOnClickListener(this);
        mLineEdit.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mCheckAll.setOnClickListener(this);

    }

    private void loadData() {
        new LoadDataTask().execute(new Params(INITIALIZE));
    }

    //刷新视图
    private void refreshListView() {
        if (mListAdapter == null) {
            mListAdapter = new ListAdapter();
            mListView.setAdapter(mListAdapter);
            mListView.setOnItemClickListener(mListAdapter);

        } else {
            mListAdapter.notifyDataSetChanged();

        }
    }

    private List<BuyCar> getData() {
        int maxId = 0;
        if (mListData != null && mListData.size() > 0)
            maxId = mListData.get(mListData.size() - 1).getId();

        List<BuyCar> result = new ArrayList<BuyCar>();
        result.add(new BuyCar(maxId + 1, "中级会计职称", 1, 30, "https://img.alicdn.com/bao/uploaded/i3/TB1vF1vJVXXXXXaXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"));
        result.add(new BuyCar(maxId + 2, "2016年初级会计职称无纸化考试系统", 1, 15, "https://img.alicdn.com/bao/uploaded/i4/TB1EmbaLVXXXXXmaXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"));
        result.add(new BuyCar(maxId + 3, "全国银行从业资格无纸化考试专用系统", 1, 15, "https://img.alicdn.com/bao/uploaded/i2/TB1m.VfJVXXXXcHXpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"));
        result.add(new BuyCar(maxId + 4, "临床执业助理医师", 1, 15, "https://img.alicdn.com/bao/uploaded/i2/TB1J.ppKVXXXXc8XFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"));
        result.add(new BuyCar(maxId + 5, "中级会计职称", 1, 15, "https://img.alicdn.com/bao/uploaded/i3/TB1vF1vJVXXXXXaXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg"));

        //根据数据显示视图(购物车是否为空)
        if (result.size() == 0) {
            mEdit.setVisibility(View.GONE);
            mListView.setVisibility(View.GONE);
            mBottom.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        } else {
            mEdit.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.VISIBLE);
            mBottom.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);
        }
        return result;
    }

    class Params {
        int op;

        public Params(int op) {
            this.op = op;
        }

    }

    class Result {
        int op;
        List<BuyCar> list;
    }

    private class LoadDataTask extends AsyncTask<Params, Void, Result> {
        @Override
        protected Result doInBackground(Params... params) {
            Params p = params[0];
            Result result = new Result();
            result.op = p.op;
            result.list = getData();
            return result;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            if (result.op == INITIALIZE) {
                mListData = result.list;
            } else {
                mListData.addAll(result.list);
                Toast.makeText(getApplicationContext(), "添加成功！", Toast.LENGTH_SHORT).show();
            }

            refreshListView();
        }

    }

    //内部适配器
    private class ListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(Page14BuyCarActivity.this).inflate(R.layout.page14_buy_car_listview, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            BuyCar data = mListData.get(position);
            bindListItem(holder, data);
            //挡掉item的焦点
            holder.xia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            //增加数量
            holder.lineAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    int _id = (int) mListData.get(position).getId();

                    boolean selected = mSelectState.get(_id, false);

                    mListData.get(position).setCarNum(mListData.get(position).getCarNum() + 1);

                    notifyDataSetChanged();

                    if (selected) {
                        totalPrice += mListData.get(position).getPrice();
                        mPriceAll.setText("￥" + df.format(totalPrice) + "");

                    }

                }
            });

            //减少数量
            holder.lineReduce.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // TODO Auto-generated method stub
                    if (mListData.get(position).getCarNum() == 1)
                        return;

                    int _id = (int) mListData.get(position).getId();

                    boolean selected = mSelectState.get(_id, false);
                    mListData.get(position).setCarNum(mListData.get(position).getCarNum() - 1);
                    notifyDataSetChanged();

                    if (selected) {
                        totalPrice -= mListData.get(position).getPrice();
                        mPriceAll.setText("￥" + df.format(totalPrice) + "");

                    }

                }
            });
            return view;
        }

        //绑定数据
        private void bindListItem(ViewHolder holder, BuyCar data) {

            holder.content.setText(data.getContent());
            holder.price.setText("￥" + data.getPrice() + "");
            holder.carNum.setText(data.CarNum());
            ImageLoaderHelper.getInstance().loadImage(data.getImageUrl(), holder.image);
            int _id = data.getId();

            boolean selected = mSelectState.get(_id, false);
            holder.checkBox.setChecked(selected);

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            BuyCar bean = mListData.get(position);

            ViewHolder holder = (ViewHolder) view.getTag();
            int _id = (int) bean.getId();

            boolean selected = !mSelectState.get(_id, false);
            holder.checkBox.toggle();
            if (selected) {
                mSelectState.put(_id, true);
                totalPrice += bean.getCarNum() * bean.getPrice();
            } else {
                mSelectState.delete(_id);
                totalPrice -= bean.getCarNum() * bean.getPrice();
            }
            mSelectNum.setText("已选" + mSelectState.size() + "件商品");
            mPriceAll.setText("￥" + df.format(totalPrice) + "");
            if (mSelectState.size() == mListData.size()) {
                mCheckAll.setChecked(true);
            } else {
                mCheckAll.setChecked(false);
            }

        }

    }

    class ViewHolder {
        CheckBox checkBox;
        ImageView image;
        TextView content;
        TextView carNum;
        TextView price;
        LinearLayout xia, lineAdd, lineReduce;

        public ViewHolder(View view) {
            checkBox = (CheckBox) view.findViewById(R.id.ks_page14_listView_check_box);   //选择按钮
            image = (ImageView) view.findViewById(R.id.ks_page14_listView_list_pic);   //图片
            content = (TextView) view.findViewById(R.id.ks_page14_listView_intro);   //标题
            price = (TextView) view.findViewById(R.id.ks_page14_listView_price);   //单价
            xia = (LinearLayout) view.findViewById(R.id.ks_page14_listView_line_xia);   //数量的大布局
            carNum = (TextView) view.findViewById(R.id.ks_page14_listView_num);   //数量
            lineAdd = (LinearLayout) view.findViewById(R.id.ks_page14_listView_line_add);   //增加
            lineReduce = (LinearLayout) view.findViewById(R.id.ks_page14_listView_line_reduce);   //减少

        }
    }

    //视图的点击事件
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ks_page14_back:   //返回按钮的逻辑
                finish();
                break;
            case R.id.ks_page14_line_subtitle:   //编辑或者删除的点击事件
                isBatchModel = !isBatchModel;
                if (isBatchModel) {
                    mEdit.setText("完成");
                    mDelete.setText("删除");
                    mButtonLayout.setVisibility(View.GONE);

                } else {
                    mEdit.setText("编辑");

                    mButtonLayout.setVisibility(View.VISIBLE);
                    mDelete.setText("结算");
                }

                break;

            case R.id.ks_page14_check_box:   //全部选择按钮
                if (mCheckAll.isChecked()) {

                    totalPrice = 0;
                    if (mListData != null) {
                        mSelectState.clear();
                        int size = mListData.size();
                        if (size == 0) {
                            return;
                        }
                        for (int i = 0; i < size; i++) {
                            int _id = (int) mListData.get(i).getId();
                            mSelectState.put(_id, true);
                            totalPrice += mListData.get(i).getCarNum() * mListData.get(i).getPrice();
                        }
                        refreshListView();
                        mPriceAll.setText("￥" + df.format(totalPrice) + "");
                        mSelectNum.setText("已选" + mSelectState.size() + "件商品");

                    }
                } else {
                    if (mListAdapter != null) {
                        totalPrice = 0;
                        mSelectState.clear();
                        refreshListView();
                        mPriceAll.setText("￥" + 0.00 + "");
                        mSelectNum.setText("已选" + 0 + "件商品");
                    }
                }
                break;

            case R.id.ks_page14_buy_or_del:   //结算或者删除按钮的逻辑
                if (isBatchModel) {
                    //删除
                    List<Integer> ids = getSelectedIds();
                    doDelete(ids);
                } else {
                    /*
                    * 1.要判断购物车里有没有商品
                    * 2.要判断有没有选中商品
                    * */
                    if (mListData == null || mListData.size() == 0) {   //第一步，判断购物车里面有没有商品
                        Toast.makeText(this, "请添加商品", Toast.LENGTH_SHORT).show();
                    } else if (totalPrice == 0) {   //第二步，判断总价格是否为0，0代表没有选中商品
                        Toast.makeText(this, "请选择商品", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(Page14BuyCarActivity.this, Page15ConfirmOrderActivity.class));
                    }
                }
                break;

            default:
                break;
        }
    }

}
