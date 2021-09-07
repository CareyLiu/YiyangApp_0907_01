package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.model.ChangJingZhiXingModel;

import butterknife.BindView;

public class XuanZeGongNengActivity extends BaseActivity {
    @BindView(R.id.ll_kaiqi)
    RelativeLayout llKaiqi;
    @BindView(R.id.rl_guanbi)
    RelativeLayout rlGuanbi;
    @BindView(R.id.iv_kaiqi)
    ImageView ivKaiqi;
    @BindView(R.id.iv_guanbi)
    ImageView ivGuanbi;
    ChangJingZhiXingModel changJingZhiXingModel;
    private String changJingLeixing;
    private String leiXingBiaoShi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changJingZhiXingModel = (ChangJingZhiXingModel) getIntent().getSerializableExtra("changJingXinXi");
        changJingLeixing = PreferenceHelper.getInstance(mContext).getString(AppConfig.ZHIXING_LEIXING, "");
        leiXingBiaoShi = getIntent().getStringExtra("leiXingSanBiaoShi");
        llKaiqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivKaiqi.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
                ivGuanbi.setBackground(null);
                // 开启设备
                changJingZhiXingModel.pro_go_one = "1";
            }
        });
        rlGuanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivGuanbi.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
                ivKaiqi.setBackground(null);
                //关闭设备
                changJingZhiXingModel.pro_go_one = "2";

            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_xuanze_gongneng;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("选择功能");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("下一步");
        tv_rightTitle.setTextColor(getResources().getColor(R.color.blue_ff3a85f8));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leixing = PreferenceHelper.getInstance(mContext).getString(AppConfig.ZHIXING_LEIXING, "");
                    if (StringUtils.isEmpty(changJingZhiXingModel.pro_go_one)) {
                        UIHelper.ToastMessage(mContext, "请选择设备开启或关闭");
                    } else {

                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_ZHINENGJIAJU_SHEBEILIEBIAO;
                    notice.content = changJingZhiXingModel;
                    RxBus.getDefault().sendRx(notice);
                    //TianJiaSheBeiChagnJingActivity.actionStart(mContext);
                    finish();
                }

            }
        });
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
    public static void actionStart(Context context, String familyId, String sheBeiId, ChangJingZhiXingModel changJingZhiXingModel, String leiXingSanBiaoShi) {
        Intent intent = new Intent(context, XuanZeGongNengActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("familyId", familyId);
        intent.putExtra("sheBeiId", sheBeiId);
        intent.putExtra("changJingXinXi", changJingZhiXingModel);
        intent.putExtra("leiXingSanBiaoShi", leiXingSanBiaoShi);
        context.startActivity(intent);
    }
}
