package com.yiyang.cn.view.magicindicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;

public class MyLinePagerIndicator extends  LinePagerIndicatorEx {
    public MyLinePagerIndicator(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LinearGradient lg = new LinearGradient(getLineRect().left, getLineRect().top, getLineRect().right, getLineRect().bottom, new int[]{Color.WHITE, Color.WHITE}, null, LinearGradient.TileMode.CLAMP);
        getPaint().setShader(lg);
        canvas.drawRoundRect(getLineRect(), getRoundRadius(), getRoundRadius(), getPaint());
    }

}
