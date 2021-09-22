package com.yiyang.cn.selectcity;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class FixedGridView extends GridView
{
  public FixedGridView(Context paramContext)
  {
    super(paramContext);
  }

  public FixedGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public FixedGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  
  @Override
  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {      
      int expandSpec = MeasureSpec.makeMeasureSpec(
              Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
      super.onMeasure(widthMeasureSpec, expandSpec); 
  } 
 

  /*public void onMeasure(int paramInt1, int paramInt2)
  {
    //super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(536870911, -2147483648));
    super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED));
  }*/
}