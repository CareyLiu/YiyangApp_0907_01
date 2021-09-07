package com.yiyang.cn.activity.tuya_device.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.TuyaBaseCameraDeviceActivity;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.device.tongyong.DeviceDingshiActivity;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManagerTwo;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class CameraSetBaojingActivity extends TuyaBaseCameraDeviceActivity {


    @BindView(R.id.iv_yidong_baojing)
    ImageView iv_yidong_baojing;
    @BindView(R.id.iv_yidong_zhuizong)
    ImageView iv_yidong_zhuizong;
    @BindView(R.id.ll_dingshi)
    LinearLayout ll_dingshi;

    private DeviceBean deviceBeen;
    private boolean baojing;
    private boolean zhuizong;
    private ITuyaDevice device;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CameraSetBaojingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera_set_baojing;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("基础功能设置");
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
        init();
        initHuidiao();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(deviceBeen.getDevId())) {
                        getData((String) message.content);
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_STATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_NETWORKSTATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_DEVINFOUPDATE) {


                } else if (message.type == ConstanceValue.MSG_CAMERA_FAIL) {

                }
            }
        }));
    }

    public void getData(String dpStr) {
        JSONObject jsonObject = JSON.parseObject(dpStr);
        Set<String> strings = jsonObject.keySet();
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            // 获得key
            String key = it.next();
            String value = jsonObject.getString(key);
            jieData(key, value, jsonObject);
        }
    }

    private void jieData(String key, String value, JSONObject jsonObject) {
        Y.e("解析出的数据:  " + "key: " + key + ",value:" + value);
        if (key.equals("134")) {
            dismissProgressDialog();
            baojing = jsonObject.getBoolean("134");
            if (baojing) {
                iv_yidong_baojing.setImageResource(R.mipmap.switch_open);
            } else {
                iv_yidong_baojing.setImageResource(R.mipmap.switch_close);
            }
        } else if (key.equals("161")) {
            dismissProgressDialog();
            zhuizong = jsonObject.getBoolean("161");
            if (zhuizong) {
                iv_yidong_zhuizong.setImageResource(R.mipmap.switch_open);
            } else {
                iv_yidong_zhuizong.setImageResource(R.mipmap.switch_close);
            }
        }
    }


    private void init() {
        deviceBeen = TuyaDeviceManagerTwo.getDeviceManager().getDeviceBeen();
        device = TuyaDeviceManagerTwo.getDeviceManager().getDevice();

        Map<String, Object> dps = deviceBeen.getDps();
        baojing = (boolean) dps.get("134");
        zhuizong = (boolean) dps.get("161");

        if (baojing) {
            iv_yidong_baojing.setImageResource(R.mipmap.switch_open);
        } else {
            iv_yidong_baojing.setImageResource(R.mipmap.switch_close);
        }
        if (zhuizong) {
            iv_yidong_zhuizong.setImageResource(R.mipmap.switch_open);
        } else {
            iv_yidong_zhuizong.setImageResource(R.mipmap.switch_close);
        }
    }

    @OnClick({R.id.iv_yidong_baojing, R.id.iv_yidong_zhuizong, R.id.ll_dingshi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_yidong_baojing:
                clickBaojing();
                break;
            case R.id.iv_yidong_zhuizong:
                clickZhuizong();
                break;
            case R.id.ll_dingshi:
                DeviceDingshiActivity.actionStart(mContext);
                break;
        }
    }

    private void clickBaojing() {
        showProgressDialog();
        Map<String, Boolean> dps = new HashMap<>();
        dps.put("134", !baojing);
        device.publishDps(TuyaDeviceManagerTwo.getDeviceManager().getJosnString(dps), new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                dismissProgressDialog();
                Y.t("设置失败:" + error);
            }

            @Override
            public void onSuccess() {

            }
        });
    }

    private void clickZhuizong() {
        showProgressDialog();
        Map<String, Boolean> dps = new HashMap<>();
        dps.put("161", !zhuizong);
        device.publishDps(TuyaDeviceManagerTwo.getDeviceManager().getJosnString(dps), new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                dismissProgressDialog();
                Y.t("设置失败:" + error);
            }

            @Override
            public void onSuccess() {

            }
        });
    }
}
