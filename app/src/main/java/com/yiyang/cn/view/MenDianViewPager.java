package com.yiyang.cn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class MenDianViewPager extends ViewPager {

    public MenDianViewPager(Context context) {
        super(context);
    }

    public MenDianViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {

        return false;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {

        return false;

    }
}
