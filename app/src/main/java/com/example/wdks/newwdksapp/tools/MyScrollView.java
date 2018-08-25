package com.example.wdks.newwdksapp.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/8/29 0029.
 * 自定义ScrollView，实现滚动视图改变标题栏颜色
 */
public class MyScrollView extends ScrollView {
    private ScrollViewListener scrollViewListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        //x为当前滑动条的横坐标，y表示当前滑动条的纵坐标，oldX为前一次滑动的横坐标，oldY表示前一次滑动的纵坐标
        super.onScrollChanged(x, y, oldX, oldY);
        if (scrollViewListener != null) {
            //在这里将方法暴露出去
            scrollViewListener.onScrollChanged(this, x, y, oldX, oldY);
        }
    }

    //是否要其弹性滑动
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        // 弹性滑动关键则是maxOverScrollX， 以及maxOverScrollY，
        // 一般默认值都是0，需要弹性时，更改其值即可
        // 即就是，为零则不会发生弹性，不为零（>0,负数未测试）则会滑动到其值的位置
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY,
                0, 0, isTouchEvent);
    }

    //接口
    public interface ScrollViewListener {
        void onScrollChanged(View scrollView, int x, int y, int oldX, int oldY);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }
}
