package com.yiyang.cn.activity.zhinengjiaju;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.AboutUsActivity;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.model.BianJiDingShiNeedModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.app.ConstanceValue.MSG_DINGSHI_NEED;

public class ChuangJianZhiNengDingShiActivity extends BaseActivity {
    @BindView(R.id.tv_chongfu)
    TextView tvChongfu;
    @BindView(R.id.rl_chongfu)
    RelativeLayout rlChongfu;
    @BindView(R.id.tv_kuaijiecaozuo)
    TextView tvKuaijiecaozuo;
    @BindView(R.id.tv_zhouyi_zhouwu)
    TextView tvZhouyiZhouwu;
    @BindView(R.id.tv_zhouliu_zhouri)
    TextView tvZhouliuZhouri;
    @BindView(R.id.tv_meitian)
    TextView tvMeitian;
    @BindView(R.id.tv_jinyici)
    TextView tvJinyici;
    @BindView(R.id.time_packer)
    TimePicker timePacker;
    List<String> mDatas;
    private String hour;//小时
    private String minutes;//分

    private String chongFuLeiXing = "";
    private String xiangQingUse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        xiangQingUse = getIntent().getStringExtra("XiangQingUse");

        mDatas = new ArrayList<>();
        mDatas.add("0");
        mDatas.add("0");
        mDatas.add("0");
        mDatas.add("0");
        mDatas.add("0");
        mDatas.add("1");
        mDatas.add("1");

        chongFuLeiXing = "3";


        tvZhouyiZhouwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvZhouyiZhouwu.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvZhouliuZhouri.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvMeitian.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvJinyici.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvZhouyiZhouwu.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvZhouliuZhouri.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvMeitian.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvJinyici.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvZhouyiZhouwu.setBackgroundResource(R.drawable.blue_stoke_backg);
                tvZhouyiZhouwu.setTextColor(mContext.getResources().getColor(R.color.blue_ff3a85f8));
                tvChongfu.setText("周一至周五");

                mDatas.set(0, "1");
                mDatas.set(1, "1");
                mDatas.set(2, "1");
                mDatas.set(3, "1");
                mDatas.set(4, "1");
                mDatas.set(5, "0");
                mDatas.set(6, "0");
                chongFuLeiXing = "2";
            }
        });
        tvZhouliuZhouri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvZhouyiZhouwu.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvZhouliuZhouri.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvMeitian.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvJinyici.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvZhouyiZhouwu.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvZhouliuZhouri.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvMeitian.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvJinyici.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvZhouliuZhouri.setBackgroundResource(R.drawable.blue_stoke_backg);
                tvZhouliuZhouri.setTextColor(mContext.getResources().getColor(R.color.blue_ff3a85f8));

                tvChongfu.setText("周六周日");

                mDatas.set(0, "0");
                mDatas.set(1, "0");
                mDatas.set(2, "0");
                mDatas.set(3, "0");
                mDatas.set(4, "0");
                mDatas.set(5, "1");
                mDatas.set(6, "1");
                chongFuLeiXing = "3";
            }
        });
        tvMeitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvZhouyiZhouwu.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvZhouliuZhouri.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvMeitian.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvJinyici.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvZhouyiZhouwu.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvZhouliuZhouri.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvMeitian.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvJinyici.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvMeitian.setBackgroundResource(R.drawable.blue_stoke_backg);
                tvMeitian.setTextColor(mContext.getResources().getColor(R.color.blue_ff3a85f8));

                tvChongfu.setText("每天");

                mDatas.set(0, "1");
                mDatas.set(1, "1");
                mDatas.set(2, "1");
                mDatas.set(3, "1");
                mDatas.set(4, "1");
                mDatas.set(5, "1");
                mDatas.set(6, "1");
                chongFuLeiXing = "1";
            }
        });
        tvJinyici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvZhouyiZhouwu.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvZhouliuZhouri.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvMeitian.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvJinyici.setTextColor(mContext.getResources().getColor(R.color.sousuo));
                tvZhouyiZhouwu.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvZhouliuZhouri.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvMeitian.setBackgroundResource(R.drawable.gray_stoke_backg);
                tvJinyici.setBackgroundResource(R.drawable.gray_stoke_backg);

                tvJinyici.setBackgroundResource(R.drawable.blue_stoke_backg);
                tvJinyici.setTextColor(mContext.getResources().getColor(R.color.blue_ff3a85f8));

                tvChongfu.setText("仅一次");
                chongFuLeiXing = "5";
            }
        });
        rlChongfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShengXiaoShiJianChongFuActivity.actionStart(mContext, mDatas);
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_chuangjianchangjing_dingshi;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("定时");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("确定");
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leixing = PreferenceHelper.getInstance(mContext).getString(AppConfig.ZHIXING_LEIXING, "");
                String kaiShiShiJian = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    String minte = String.format("%02d", timePacker.getMinute());
                    kaiShiShiJian = String.valueOf(timePacker.getHour()) + ":" + minte;
                }
                if (!StringUtils.isEmpty(xiangQingUse)) {

                    BianJiDingShiNeedModel bianJiDingShiNeedModel = new BianJiDingShiNeedModel();
                    bianJiDingShiNeedModel.chongFuLeiXing = chongFuLeiXing;
                    bianJiDingShiNeedModel.kaiShiSHiJian = kaiShiShiJian;
                    bianJiDingShiNeedModel.chongFuLeiXing = chongFuLeiXing;
                    bianJiDingShiNeedModel.xingQiList = mDatas;
                    Notice notice = new Notice();
                    notice.type = MSG_DINGSHI_NEED;
                    notice.content = bianJiDingShiNeedModel;
                    RxBus.getDefault().sendRx(notice);

                    finish();
                } else {
                    if (!StringUtils.isEmpty(kaiShiShiJian)) {
                        ChangJingYiJianZhiXingActivity.actionStart(mContext, leixing, kaiShiShiJian, chongFuLeiXing, mDatas);
                        finish();
                    } else {
                        UIHelper.ToastMessage(mContext, "请选择时间");
                    }
                }


            }
        });
        tv_rightTitle.setTextColor(getResources().getColor(R.color.blue_ff3a85f8));
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
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChuangJianZhiNengDingShiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
