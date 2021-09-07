package com.yiyang.cn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by Sl on 2019/6/24.
 *
 */

public class ClickableEditText extends androidx.appcompat.widget.AppCompatEditText {
    public ClickableEditText(Context context) {
        super(context);
    }

    public ClickableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
