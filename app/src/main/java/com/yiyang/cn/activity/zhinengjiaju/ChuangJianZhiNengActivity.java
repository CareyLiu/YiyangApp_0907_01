package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.flyco.roundview.RoundRelativeLayout;
import com.yiyang.cn.R;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.config.PreferenceHelper;

import butterknife.BindView;

public class ChuangJianZhiNengActivity extends BaseActivity {
    @BindView(R.id.iv_chuangjianchangjing_fisrtimage)
    ImageView ivChuangjianchangjingFisrtimage;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.iv_image_1)
    ImageView ivImage1;
    @BindView(R.id.rl_qixiangbianhua)
    RoundRelativeLayout rlQixiangbianhua;
    @BindView(R.id.iv_image_2)
    ImageView ivImage2;
    @BindView(R.id.rl_weizhibianhua)
    RoundRelativeLayout rlWeizhibianhua;
    @BindView(R.id.iv_image_3)
    ImageView ivImage3;
    @BindView(R.id.rl_dingshi)
    RoundRelativeLayout rlDingshi;
    @BindView(R.id.iv_image_4)
    ImageView ivImage4;
    @BindView(R.id.rl_shebeizhuangtaibianhua)
    RoundRelativeLayout rlShebeizhuangtaibianhua;
    @BindView(R.id.rl_yijianzhixing)
    RoundRelativeLayout rlYijianzhixing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rlQixiangbianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlWeizhibianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlDingshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuangJianZhiNengDingShiActivity.actionStart(mContext);
                PreferenceHelper.getInstance(mContext).putString(AppConfig.ZHIXING_LEIXING, "2");
                finish();
            }
        });
        rlShebeizhuangtaibianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String familyId = PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, "");
                PreferenceHelper.getInstance(mContext).putString(AppConfig.ZHIXING_LEIXING, "3");
                SheBeiBianHuaActivity.actionStart(mContext, familyId,"leiXingBiaoShi");

                finish();
            }
        });
        rlYijianzhixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceHelper.getInstance(mContext).putString(AppConfig.ZHIXING_LEIXING, "1");
                ChangJingYiJianZhiXingActivity.actionStart(mContext, "1", "", "", null);

                finish();
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_chuangjian_zhinengchangjing;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("创建智能场景");
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

    @Override
    public boolean showToolBar() {
        return true;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChuangJianZhiNengActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
