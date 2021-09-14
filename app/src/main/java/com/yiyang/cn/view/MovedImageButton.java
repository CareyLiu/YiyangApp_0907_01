package com.yiyang.cn.view;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.widget.AppCompatImageButton;

import com.scwang.smartrefresh.layout.util.DensityUtil;

public class MovedImageButton extends AppCompatImageButton {

    private int lastX;
    private int lastY;
    private float screenWidth;

    public MovedImageButton(Context context) {
        super(context);
    }

    public MovedImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovedImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
    }

    int offsetX;
    int offsetY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.i("ImageButton-00", "x" + String.valueOf(x));
        Log.i("ImageButton-00", "y" + String.valueOf(y));
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //第一种方法
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                //第二种方法
                //offsetLeftAndRight(offsetX);
                //offsetTopAndBottom(offsetY);
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                // adsorbAnim(event.getRawX(), event.getRawY());


            break;
        }
        return super.onTouchEvent(event);
    }

    private void adsorbAnim(float rawX, float rawY) {
        //靠顶吸附
        if (rawY <= DensityUtil.dp2px(200)) {//注意rawY包含了标题栏的高
            animate().setDuration(400)
                    .setInterpolator(new OvershootInterpolator())
                    .yBy(-getY() - getHeight() / 2.0f)
                    .start();
        } else if (rawX >= screenWidth / 2) {//靠右吸附
            animate().setDuration(400)
                    .setInterpolator(new OvershootInterpolator())
                    .xBy(screenWidth - getX() - getWidth() / 2.0f)
                    .start();
        } else {//靠左吸附
            ObjectAnimator animator = ObjectAnimator.ofFloat(this, "x", getX(), -getWidth() / 2.0f);
            animator.setInterpolator(new OvershootInterpolator());
            animator.setDuration(400);
            animator.start();
        }

    }

}
