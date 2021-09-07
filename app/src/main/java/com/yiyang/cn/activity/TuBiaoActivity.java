package com.yiyang.cn.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.fragment.LineChartFragment;

public class TuBiaoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()    //
                .beginTransaction()
                .add(R.id.ll_main, new LineChartFragment())   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tubiao;
    }

}
