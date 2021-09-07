package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.flyco.roundview.RoundRelativeLayout;
import com.yiyang.cn.R;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.config.PreferenceHelper;

import butterknife.BindView;

public class TianJiaSheBeiChagnJingActivity extends BaseActivity {
    @BindView(R.id.et_changjingmingcheng)
    EditText etChangjingmingcheng;
    @BindView(R.id.rrl_changjingmingcheng)
    RoundRelativeLayout rrlChangjingmingcheng;
    @BindView(R.id.iv_shengxiao_time)
    ImageView ivShengxiaoTime;
    @BindView(R.id.rrl_shengxiaoshijian)
    RoundRelativeLayout rrlShengxiaoshijian;
    @BindView(R.id.rrl_manzutiaojian)
    RoundRelativeLayout rrlManzutiaojian;
    @BindView(R.id.iv_shebeimingcheng)
    ImageView ivShebeimingcheng;
    @BindView(R.id.rlv_changjingtubiao)
    RoundRelativeLayout rlvChangjingtubiao;
    @BindView(R.id.ll_tianjiatiaojian)
    LinearLayout llTianjiatiaojian;

    private String zhiNengChangJingLeixing;
    private String time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zhiNengChangJingLeixing = PreferenceHelper.getInstance(mContext).getString(AppConfig.ZHIXING_LEIXING, "");
        time = getIntent().getStringExtra("time");
        if (zhiNengChangJingLeixing.equals("1")) {

        } else if (zhiNengChangJingLeixing.equals("2")) {

        } else if (zhiNengChangJingLeixing.equals("3")) {

        }
        rrlShengxiaoshijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShengXiaoShiJianActivity.actionStart(mContext);
            }
        });

    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tianjia_shebeichangjing;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("场景");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String time) {
        Intent intent = new Intent(context, TianJiaSheBeiChagnJingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("time", time);
        context.startActivity(intent);
    }

}
