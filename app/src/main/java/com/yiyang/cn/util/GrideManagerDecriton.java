package com.yiyang.cn.util;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.recyclerview.widget.RecyclerView;

public class GrideManagerDecriton extends GridItemDecoration {
    public GrideManagerDecriton(Drawable divider) {
        super(divider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

    }
}
