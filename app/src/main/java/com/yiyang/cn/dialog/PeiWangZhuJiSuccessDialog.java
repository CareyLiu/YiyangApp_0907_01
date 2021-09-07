package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.yiyang.cn.R;
import com.yiyang.cn.adapter.OneImageAdapter;
import com.yiyang.cn.model.ZhiNengJiaJu_0007Model;
import com.yiyang.cn.model.ZhiNengJiaJu_0009Model;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PeiWangZhuJiSuccessDialog extends Dialog {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.main)
    RelativeLayout main;
    private Context mContext;
    ZhiNengJiaJu_0009Model zhiNengJiaJu_0009Model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public PeiWangZhuJiSuccessDialog(@NonNull Context context, ZhiNengJiaJu_0009Model zhiNengJiaJu_0009Models) {
        super(context);
        this.mContext = context;
        this.zhiNengJiaJu_0009Model = zhiNengJiaJu_0009Models;
        init();

    }


    private void init() {
        setContentView(R.layout.layout_peiwang_zhuji_success);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }


}
