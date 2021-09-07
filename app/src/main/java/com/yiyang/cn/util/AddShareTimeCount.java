package com.yiyang.cn.util;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.yiyang.cn.R;
import com.yiyang.cn.config.MyApplication;


/**
 * Created by Administrator on 2017/3/31.
 */
//计时器
public class AddShareTimeCount extends CountDownTimer {
    private TextView view;

    public AddShareTimeCount(long millisInFuture, long countDownInterval, TextView view) {
        super(millisInFuture, countDownInterval);
        this.view = view;
    }

    @Override
    public void onFinish() {// 计时完毕
        view.setClickable(true);
        view.setText("重新发送");
        view.setTextColor(Color.rgb(65, 141, 255));
        view.setTextSize(13);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程
        view.setClickable(false);
        view.setText("已发送（" + millisUntilFinished / 1000 + "）");
        view.setTextColor(Color.rgb(65, 141, 255));
        view.setTextSize(13);
    }


}