package com.yiyang.cn.activity.tuya_device.add.zi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.utils.WifiReceiver;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.config.PreferenceHelper;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class TuyaAddCameraActivity extends BaseActivity {


    @BindView(R.id.tv_huashu1)
    TextView tvHuashu1;
    @BindView(R.id.ll_huashu2)
    LinearLayout llHuashu2;
    @BindView(R.id.tv_huashu3)
    TextView tvHuashu3;
    @BindView(R.id.tv_wifi_ming)
    TextView tvWifiMing;
    @BindView(R.id.ll_wifi_name)
    LinearLayout llWifiName;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.iv_icon_mima)
    ImageView ivIconMima;
    @BindView(R.id.et_wifi_mima)
    EditText etWifiMima;
    @BindView(R.id.iv_see_mima)
    ImageView ivSeeMima;
    @BindView(R.id.ll_wifi_mima)
    RelativeLayout llWifiMima;
    @BindView(R.id.view_line_1)
    View viewLine1;
    @BindView(R.id.tv_huashu4)
    TextView tvHuashu4;
    @BindView(R.id.iv_peiwang_mima)
    ImageView ivPeiwangMima;
    @BindView(R.id.rll_kaishilianjie)
    TextView rllKaishilianjie;

    private WifiReceiver wifiReceiver;
    private String seeMiMa = "0";//0 隐藏 1显示
    private String jiZhuMiMa = "1";//0 不记住  1 记住
    private boolean isOnWifiConect;
    private String bssid;
    private String cameraType;


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String cameraType) {
        Intent intent = new Intent(context, TuyaAddCameraActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("cameraType", cameraType);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_add_camera;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("摄像机配网");
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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initHuidiao();
        initPeizhi();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_WIFI_INFO) {
                    rllKaishilianjie.setEnabled(true);
                    isOnWifiConect = true;

                    WifiInfo wifiInfo = (WifiInfo) message.content;
                    String name = wifiInfo.getSSID().replace("\"", "");
                    bssid = wifiInfo.getBSSID();
                    tvWifiMing.setText(name);
                    String wifiName = PreferenceHelper.getInstance(mContext).getString(AppConfig.TUYA_PEIWANG_ADMIN_GET, "");
                    if (wifiName.equals(name)) {
                        String mima = PreferenceHelper.getInstance(mContext).getString(AppConfig.TUYA_PEIWANG_MIMA_GET, "");
                        etWifiMima.setText(mima);
                    } else {
                        etWifiMima.setText("");
                    }
                } else if (message.type == ConstanceValue.MSG_WIFI_CLOSE) {
                    isOnWifiConect = false;
                    rllKaishilianjie.setEnabled(false);
                    tvWifiMing.setText("Wifi断开，请连接Wifi");
                    etWifiMima.setText("");
                    bssid = "";
                } else if (message.type == ConstanceValue.MSG_DEVICE_ADD) {
                    finish();
                } else if (message.type == ConstanceValue.MSG_PEIWANG_SUCCESS) {
                    finish();
                }
            }
        }));
    }

    private void initPeizhi() {
        cameraType = getIntent().getStringExtra("cameraType");

        ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_buxianshimima);
        ivPeiwangMima.setBackgroundResource(R.mipmap.peiwang_icon_mima_jizhu);

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


    private void reginstWifi() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        wifiReceiver = new WifiReceiver();
        registerReceiver(wifiReceiver, filter);
    }



    @OnClick({R.id.ll_wifi_name, R.id.iv_see_mima, R.id.iv_peiwang_mima, R.id.rll_kaishilianjie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_wifi_name:
                startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                break;
            case R.id.iv_see_mima:
                clickMima();
                break;
            case R.id.iv_peiwang_mima:
                setPeiWangMiMa();
                break;
            case R.id.rll_kaishilianjie:
                clickXiayibu();
                break;
        }
    }


    private void clickMima() {
        if (seeMiMa.equals("0")) {
            seeMiMa = "1";
            ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_xianshimima);
            etWifiMima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            seeMiMa = "0";
            ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_buxianshimima);
            etWifiMima.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    private void setPeiWangMiMa() {
        if (jiZhuMiMa.equals("0")) {
            ivPeiwangMima.setBackgroundResource(R.mipmap.peiwang_icon_mima_jizhu);
            jiZhuMiMa = "1";
        } else if (jiZhuMiMa.equals("1")) {
            ivPeiwangMima.setBackgroundResource(R.mipmap.peiwang_icon_mima_weixuanze);
            jiZhuMiMa = "0";
        }
    }

    private void clickXiayibu() {
        String wifiName = tvWifiMing.getText().toString();
        String mima = etWifiMima.getText().toString();
        if (TextUtils.isEmpty(mima)) {
            Y.t("请输入您的配网密码");
        } else {
            PreferenceHelper.getInstance(mContext).putString(AppConfig.TUYA_PEIWANG_ADMIN, wifiName);
            PreferenceHelper.getInstance(mContext).putString(AppConfig.TUYA_PEIWANG_MIMA, mima);
            PreferenceHelper.getInstance(mContext).putString(AppConfig.TUYA_PEIWANG_BSSID, bssid);
            if (jiZhuMiMa.equals("1")) {
                PreferenceHelper.getInstance(mContext).putString(AppConfig.TUYA_PEIWANG_ADMIN_GET, wifiName);
                PreferenceHelper.getInstance(mContext).putString(AppConfig.TUYA_PEIWANG_MIMA_GET, mima);
            } else {
                PreferenceHelper.getInstance(mContext).removeKey(AppConfig.TUYA_PEIWANG_ADMIN_GET);
                PreferenceHelper.getInstance(mContext).removeKey(AppConfig.TUYA_PEIWANG_MIMA_GET);
            }
            Y.hideInputMethod(etWifiMima);
            if (cameraType.equals("0")) {
                TuyaAddCameraWifiActivity.actionStart(mContext, wifiName, mima);
            } else {
                TuyaAddCameraEwmActivity.actionStart(mContext, wifiName, mima);
            }
        }
    }
}
