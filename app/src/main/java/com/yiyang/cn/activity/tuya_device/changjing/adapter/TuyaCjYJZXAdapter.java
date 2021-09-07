package com.yiyang.cn.activity.tuya_device.changjing.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tuya.smart.home.sdk.bean.scene.SceneBean;

import java.util.List;

import androidx.annotation.Nullable;

public class TuyaCjYJZXAdapter extends BaseQuickAdapter<SceneBean, BaseViewHolder> {


    public TuyaCjYJZXAdapter(int layoutResId, @Nullable List<SceneBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void convert(BaseViewHolder helper, SceneBean item) {


        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setGradientType(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(15);
        drawable.setColor(Color.parseColor("#aaaaaa"));
        //helper.getView()

    }
}
