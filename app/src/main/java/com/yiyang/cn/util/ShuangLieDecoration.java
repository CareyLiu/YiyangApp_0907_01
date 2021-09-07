package com.yiyang.cn.util;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.scwang.smartrefresh.layout.util.DesignUtil;

public class ShuangLieDecoration extends RecyclerView.ItemDecoration {

    Activity activity;

    public ShuangLieDecoration(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int leftSpace = 10;
        int rightSpace = 10;
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            int totalSpace = 10 + 10 + 10 * (spanCount - 1);
            int itemNeedSpace = totalSpace / spanCount;
            if (parent.getChildAdapterPosition(view) % 2 == 0) {
                //      最左一条
                outRect.left = DensityUtil.dp2px(leftSpace);
                outRect.right = DensityUtil.dp2px(itemNeedSpace - leftSpace);
            } else if (parent.getChildAdapterPosition(view) % 2 == 1) {
                //    最右一条
                outRect.left = DensityUtil.dp2px(itemNeedSpace - rightSpace);
                outRect.right = DensityUtil.dp2px(rightSpace);
            }
            Log.i("spanCount", spanCount + "");
            Log.i("getchildadapterposition", parent.getChildAdapterPosition(view) % 2 + "" + parent.getChildAdapterPosition(view));
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
    }


}
