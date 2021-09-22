package com.yiyang.cn.activity.a_yiyang;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class YiShengZhuYeActivity extends BaseActivity {
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_yisheng_ming)
    TextView tvYishengMing;
    @BindView(R.id.view_guahao)
    View viewGuahao;
    @BindView(R.id.view_jieshao)
    View viewJieshao;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_yishengzhuye;
    }

    @OnClick({R.id.view_guahao, R.id.view_jieshao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_guahao:
                break;
            case R.id.view_jieshao:
                break;
        }
    }
}
