package com.yiyang.cn.util;

import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.yiyang.cn.model.FenLeiContentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用于RecyclerView的GridLayoutManager，水平方向上固定间距大小，从而使条目宽度自适应。<br>
 * 配合Brvah的Section使用，不对Head生效，仅对每个Head的子Grid列表生效<br>
 * Section Grid中Item的宽度应设为MATCH_PARAENT
 *
 * @author : renpeng
 * @since : 2018/9/29
 */
public class GridAverageUIDecoration extends RecyclerView.ItemDecoration {

    private float gapHorizontalDp;
    private float gapVerticalDp;
    private float sectionEdgeHPaddingDp;
    private float sectionEdgeVPaddingDp;
    private int gapHSizePx = -1;
    private int gapVSizePx = -1;
    private int sectionEdgeHPaddingPx;
    private int eachItemHPaddingPx; //每个条目应该在水平方向上加的padding 总大小，即=paddingLeft+paddingRight
    private int sectionEdgeVPaddingPx;

    private BaseSectionQuickAdapter mAdapter;

    /**
     * @param gapHorizontalDp       item之间的水平间距
     * @param gapVerticalDp         item之间的垂直间距

     */
    public GridAverageUIDecoration(float gapHorizontalDp, float gapVerticalDp) {
        this.gapHorizontalDp = gapHorizontalDp;
        this.gapVerticalDp = gapVerticalDp;
        this.sectionEdgeHPaddingDp = sectionEdgeHPaddingDp;
        this.sectionEdgeVPaddingDp = sectionEdgeVPaddingDp;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() instanceof GridLayoutManager ) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();


            int spanCount = layoutManager.getSpanCount();
            int position = parent.getChildAdapterPosition(view) ;

            if (gapHSizePx < 0 || gapVSizePx < 0) {
                transformGapDefinition(parent, spanCount);
            }
            outRect.top = gapVSizePx;
            outRect.bottom = 0;

            //下面的visualPos为单个Section内的视觉Pos
            int visualPos = position + 1 ;
            if (visualPos % spanCount == 1) {
                //第一列
                outRect.left = sectionEdgeHPaddingPx;
                outRect.right = eachItemHPaddingPx - sectionEdgeHPaddingPx;
            } else if (visualPos % spanCount == 0) {
                //最后一列
                outRect.left = eachItemHPaddingPx - sectionEdgeHPaddingPx;
                outRect.right = sectionEdgeHPaddingPx;
            } else {
                outRect.left = gapHSizePx - (eachItemHPaddingPx - sectionEdgeHPaddingPx);
                outRect.right = eachItemHPaddingPx - outRect.left;
            }


        Log.w("GridAverageGapItem", "pos=" + position + ",vPos=" + visualPos + "," + outRect.toShortString());
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }





    private void transformGapDefinition(RecyclerView parent, int spanCount) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            parent.getDisplay().getMetrics(displayMetrics);
        }
        gapHSizePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, gapHorizontalDp, displayMetrics);
        gapVSizePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, gapVerticalDp, displayMetrics);
        sectionEdgeHPaddingPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sectionEdgeHPaddingDp, displayMetrics);
        sectionEdgeVPaddingPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sectionEdgeVPaddingDp, displayMetrics);
        eachItemHPaddingPx = (sectionEdgeHPaddingPx * 2 + gapHSizePx * (spanCount - 1)) / spanCount;
    }



    private boolean isLastRow(int visualPos, int spanCount, int sectionItemCount) {
        int lastRowCount = sectionItemCount % spanCount;
        lastRowCount = lastRowCount == 0 ? spanCount : lastRowCount;
        return visualPos > sectionItemCount - lastRowCount;
    }
}
