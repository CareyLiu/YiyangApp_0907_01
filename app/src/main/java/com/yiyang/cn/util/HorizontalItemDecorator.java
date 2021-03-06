package com.yiyang.cn.util;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * @author msahakyan
 */
public class HorizontalItemDecorator extends RecyclerView.ItemDecoration {

    private final int mHorizontalSpaceWidth;

    public HorizontalItemDecorator(int horizontalSpaceHeight) {
        this.mHorizontalSpaceWidth = horizontalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.left = outRect.right = mHorizontalSpaceWidth;
    }
}
