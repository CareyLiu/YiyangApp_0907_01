package com.yiyang.cn.activity.wode_page.bazinew.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.taokeshagncheng.QueRenDingDanActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;

import androidx.annotation.Nullable;

public class BaziBaseActivity extends BaseActivity {

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImmersionBar.with(this).statusBarDarkFont(false).fitsSystemWindows(false).statusBarColor(R.color.bazi_main).init();
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.white));
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setBackgroundColor(Color.parseColor("#6666CC"));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 通过类名启动Activity add
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     */
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }


    public void t(String msg) {
        UIHelper.ToastMessage(mContext, msg, Toast.LENGTH_SHORT);
    }
}
