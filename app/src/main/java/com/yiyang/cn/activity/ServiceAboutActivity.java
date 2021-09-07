package com.yiyang.cn.activity;

import android.os.Bundle;

import com.yiyang.cn.R;
import com.yiyang.cn.aakefudan.base.ServiceBaseActivity;

public class ServiceAboutActivity extends ServiceBaseActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("关于我们");
    }

    @Override
    public boolean showToolBar() {
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
