package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ShengXiaoShiJianActivity extends BaseActivity {
    @BindView(R.id.iv_quantian)
    ImageView ivQuantian;
    @BindView(R.id.rl_quantian)
    RelativeLayout rlQuantian;
    @BindView(R.id.iv_baitian)
    ImageView ivBaitian;
    @BindView(R.id.rl_baitian)
    RelativeLayout rlBaitian;
    @BindView(R.id.iv_yewan)
    ImageView ivYewan;
    @BindView(R.id.rl_yewan)
    RelativeLayout rlYewan;
    @BindView(R.id.iv_zidingyi)
    ImageView ivZidingyi;
    @BindView(R.id.rl_zidingyi)
    RelativeLayout rlZidingyi;
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
    @BindView(R.id.tv_chongfu)
    TextView tvChongfu;
    @BindView(R.id.rl_chongfu)
    RelativeLayout rlChongfu;
    private List<String> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas = new ArrayList<>();
        mDatas.add("0");
        mDatas.add("0");
        mDatas.add("0");
        mDatas.add("0");
        mDatas.add("0");
        mDatas.add("0");
        mDatas.add("0");

        ivQuantian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
        ivBaitian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
        ivYewan.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
        ivZidingyi.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);

        ivQuantian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        rlQuantian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivQuantian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivBaitian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivYewan.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivZidingyi.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivQuantian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel_1);


            }
        });
        rlBaitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivQuantian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivBaitian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivYewan.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivZidingyi.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivBaitian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel_1);


            }
        });
        rlYewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivQuantian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivBaitian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivYewan.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivZidingyi.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivYewan.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel_1);

            }
        });
        rlZidingyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivQuantian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivBaitian.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivYewan.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivZidingyi.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
                ivZidingyi.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
            }
        });


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

                for (int i = 0; i < mDatas.size(); i++) {

                    if (i == 0) {
                        mDatas.set(i, "1");
                    } else if (i == 4) {
                        mDatas.set(i, "1");
                    } else {
                        mDatas.set(i, "0");
                    }
                }

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

                for (int i = 0; i < mDatas.size(); i++) {

                    if (i == 5) {
                        mDatas.set(i, "1");
                    } else if (i == 6) {
                        mDatas.set(i, "1");
                    } else {
                        mDatas.set(i, "0");
                    }
                }
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

                for (int i = 0; i < mDatas.size(); i++) {
                    mDatas.set(i, "1");
                }
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
                tvMeitian.setTextColor(mContext.getResources().getColor(R.color.blue_ff3a85f8));

                tvChongfu.setText("每一天");
            }
        });
        rlChongfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShengXiaoShiJianChongFuActivity.actionStart(mContext, mDatas);
            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DINGSHI_CHONGFU) {
                    mDatas = (List<String>) message.content;
                    Log.i("mDatas", Arrays.toString(mDatas.toArray()));
                }
            }
        }));
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_shengxiaoshijian;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("生效时间");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_rightTitle.setText("保存");
        tv_rightTitle.setTextColor(getResources().getColor(R.color.blue_ff3a85f8));
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "保存");
            }
        });
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
        Intent intent = new Intent(context, ShengXiaoShiJianActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
