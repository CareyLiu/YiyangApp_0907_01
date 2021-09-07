package com.yiyang.cn.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.model.TuanGouYouHuiJuanModel;

import java.io.Serializable;

import butterknife.ButterKnife;

public class KaquanZengActivity extends BaseActivity {

    private TuanGouYouHuiJuanModel.DataBean dataBean;

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("赠送卡券");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, TuanGouYouHuiJuanModel.DataBean dataBean) {
        Intent intent = new Intent(context, KaquanZengActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("dataBean", dataBean);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_kaquan_zengsong;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        dataBean = (TuanGouYouHuiJuanModel.DataBean) getIntent().getSerializableExtra("dataBean");


//        initAdapter();
//        initSM();
//        getNet();
    }
}
