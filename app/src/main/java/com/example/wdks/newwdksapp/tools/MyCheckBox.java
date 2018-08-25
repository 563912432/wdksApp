package com.example.wdks.newwdksapp.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CheckBox;

import com.example.wdks.newwdksapp.R;


/**
 * Created by Administrator on 2016/7/20 0020.
 * 自定义CheckBox
 */
public class MyCheckBox extends CheckBox {
    private int mCheckBoxSize;

    public MyCheckBox(Context context) {
        this(context, null, 0);
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MyCheckBox);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            Log.i("MyRadioButton", "attr:" + attr);
            switch (attr) {
                case R.styleable.MyCheckBox_mCheckBoxSize:
                    mCheckBoxSize = a.getDimensionPixelSize(R.styleable.MyCheckBox_mCheckBoxSize, 50);
                    Log.i("MyRadioButton", "mDrawableSize:" + mCheckBoxSize);
                    break;
                case R.styleable.MyCheckBox_checkBoxTop:
                    drawableTop = a.getDrawable(attr);
                    break;
                case R.styleable.MyCheckBox_checkBoxBottom:
                    drawableRight = a.getDrawable(attr);
                    break;
                case R.styleable.MyCheckBox_checkBoxRight:
                    drawableBottom = a.getDrawable(attr);
                    break;
                case R.styleable.MyCheckBox_checkBoxLeft:
                    drawableLeft = a.getDrawable(attr);
                    break;
                default:
                    break;
            }
        }
        a.recycle();
        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
                                                        Drawable top, Drawable right, Drawable bottom) {

        if (left != null) {
            left.setBounds(0, 0, mCheckBoxSize, mCheckBoxSize);
        }
        if (right != null) {
            right.setBounds(0, 0, mCheckBoxSize, mCheckBoxSize);
        }
        if (top != null) {
            top.setBounds(0, 0, mCheckBoxSize, mCheckBoxSize);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mCheckBoxSize, mCheckBoxSize);
        }
        setCompoundDrawables(left, top, right, bottom);

    }
}
