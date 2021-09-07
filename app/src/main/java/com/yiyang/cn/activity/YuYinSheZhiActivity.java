package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.config.PreferenceHelper;

import butterknife.BindView;

public class YuYinSheZhiActivity extends BaseActivity {
    @BindView(R.id.rl_yuyin_shezhi)
    RelativeLayout rlYuyinShezhi;
    @BindView(R.id.rl_changjian_wenti)
    RelativeLayout rlChangjianWenti;
    @BindView(R.id.tv_yuyin_huanxing)
    TextView tvYuyinHuanxing;
    @BindView(R.id.scroll_1)
    ScrollView scroll1;
    @BindView(R.id.scroll_2)
    ScrollView scroll2;
    @BindView(R.id.view_line1)
    View viewLine1;
    @BindView(R.id.view_line2)
    View viewLine2;
    @BindView(R.id.yuyin_switch)
    Switch yuyinSwitch;
    private String yuYinZhuShouEnable = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scroll1.setVisibility(View.VISIBLE);


        rlYuyinShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroll1.setVisibility(View.VISIBLE);
                scroll2.setVisibility(View.GONE);
                viewLine1.setVisibility(View.VISIBLE);
                viewLine2.setVisibility(View.GONE);

            }
        });
        rlChangjianWenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroll1.setVisibility(View.GONE);
                scroll2.setVisibility(View.VISIBLE);

                viewLine1.setVisibility(View.GONE);
                viewLine2.setVisibility(View.VISIBLE);
            }
        });

        String yuYinEnable = PreferenceHelper.getInstance(mContext).getString(AppConfig.YUYINZHUSHOU, "0");
        if (yuYinEnable.equals("0")) {
            yuyinSwitch.setChecked(false);
        } else {
            yuyinSwitch.setChecked(true);
        }

        yuyinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.YUYINZHUSHOU, "1");
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_YUYINKAIQITONGZHI;
                    //  n.content = message.toString();
                    RxBus.getDefault().sendRx(n);
                } else {
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.YUYINZHUSHOU, "0");
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_YUYINGUANBITONGZHI;
                    //  n.content = message.toString();
                    RxBus.getDefault().sendRx(n);
                }
            }
        });


    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_yuyin_shezhi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("语音助手");
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
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, YuYinSheZhiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
