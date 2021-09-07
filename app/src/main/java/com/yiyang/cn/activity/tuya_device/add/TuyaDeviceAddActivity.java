package com.yiyang.cn.activity.tuya_device.add;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.add.adapter.MainVpAdapter;
import com.yiyang.cn.activity.tuya_device.add.adapter.MyViewPager;
import com.yiyang.cn.activity.tuya_device.utils.WifiReceiver;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tuya.smart.home.sdk.bean.WeatherBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class TuyaDeviceAddActivity extends BaseActivity {

    @BindView(R.id.vpg)
    MyViewPager vpg;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_shoudong)
    TextView tv_shoudong;
    @BindView(R.id.line_shoudong)
    View line_shoudong;
    @BindView(R.id.rl_shoudong)
    RelativeLayout rl_shoudong;
    @BindView(R.id.tv_zidong)
    TextView tv_zidong;
    @BindView(R.id.line_zidong)
    View line_zidong;
    @BindView(R.id.rl_zidong)
    RelativeLayout rl_zidong;

    private List<BaseFragment> mFragments = new ArrayList<>();
    private WifiReceiver wifiReceiver;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TuyaDeviceAddActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_add;
    }


    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("添加摄像机");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initWifi();
        initVpg();
        initHuidiao();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_ADD) {
                    finish();
                } else if (message.type == ConstanceValue.MSG_PEIWANG_SUCCESS) {
                    finish();
                }
            }
        }));
    }

    private void initWifi() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) { // 在android 6.0之前会默认返回true
                    reginstWifi();
                } else {
                    Y.tLong("配置wifi需要赋予访问网络定位的权限，不开启将无法获取wifi信息！");
                }
            }
        });
    }

    private void initVpg() {
        mFragments.add(new TuyaDeviceAddShouFragment());
        mFragments.add(new TuyaDeviceAddZiFragment());
        MainVpAdapter mainVpAdapter = new MainVpAdapter(getSupportFragmentManager(), mFragments);
        vpg.setAdapter(mainVpAdapter);
    }

    private void reginstWifi() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        wifiReceiver = new WifiReceiver();
        registerReceiver(wifiReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiReceiver);
    }

    @OnClick({R.id.rl_back, R.id.rl_shoudong, R.id.rl_zidong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_shoudong:
                clickShoudong();
                break;
            case R.id.rl_zidong:
                clickZidong();
                break;
        }
    }

    private void clickShoudong() {
        tv_shoudong.setTextColor(Y.getColor(R.color.color_3));
        tv_zidong.setTextColor(Y.getColor(R.color.color_9));
        line_shoudong.setVisibility(View.VISIBLE);
        line_zidong.setVisibility(View.GONE);
        vpg.setCurrentItem(0);
    }

    private void clickZidong() {
        tv_shoudong.setTextColor(Y.getColor(R.color.color_9));
        tv_zidong.setTextColor(Y.getColor(R.color.color_3));
        line_shoudong.setVisibility(View.GONE);
        line_zidong.setVisibility(View.VISIBLE);
        vpg.setCurrentItem(1);
    }
}
