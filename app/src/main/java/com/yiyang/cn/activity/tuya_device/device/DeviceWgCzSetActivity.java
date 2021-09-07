package com.yiyang.cn.activity.tuya_device.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaBottomDialog;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaBottomDialogView;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class DeviceWgCzSetActivity extends TuyaBaseDeviceActivity {

    @BindView(R.id.ll_device_info)
    LinearLayout ll_device_info;
    @BindView(R.id.tv_shangdian)
    TextView tv_shangdian;
    @BindView(R.id.ll_device_shangdian)
    LinearLayout ll_device_shangdian;
    @BindView(R.id.tv_zhishideng)
    TextView tv_zhishideng;
    @BindView(R.id.ll_device_zhishideng)
    LinearLayout ll_device_zhishideng;

    private DeviceBean mDeviceBeen;
    private ITuyaDevice mDevice;
    private String relay_status;//上电状态
    private String light_mode;//指示灯状态

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DeviceWgCzSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_wangguan_chazuo_set;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设置");
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

    private void init() {
        mDeviceBeen = TuyaDeviceManager.getDeviceManager().getDeviceBeen();
        mDevice = TuyaDeviceManager.getDeviceManager().getDevice();

        Map<String, Object> dps = mDeviceBeen.getDps();
        relay_status = dps.get("38").toString();
        setRelayStatus();
        light_mode = dps.get("40").toString();
        setLightMode();
    }

    private void setRelayStatus() {
        switch (relay_status) {
            case "off":
                tv_shangdian.setText("断电");
                break;
            case "on":
                tv_shangdian.setText("通电");
                break;
            case "memory":
                tv_shangdian.setText("保持重启前的状态");
                break;
        }
    }

    private void setLightMode() {
        switch (light_mode) {
            case "relay":
                tv_zhishideng.setText("指示开关");
                break;
            case "pos":
                tv_zhishideng.setText("指示位置");
                break;
            case "none":
                tv_zhishideng.setText("关闭指示灯");
                break;
        }
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(mDeviceBeen.getDevId())) {
                        getData(message.content.toString());
                    }
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
            Object value = jsonObject.get(key);
            jieData(key, value);
        }
    }

    private void jieData(String key, Object value) {
        Y.e("解析出的数据:  " + "key = " + key + "  |  value = " + value);
        if (key.equals("38")) {
            relay_status = value.toString();
            setRelayStatus();
        } else if (key.equals("40")) {
            light_mode = value.toString();
            setLightMode();
        }
    }

    @OnClick({R.id.ll_device_info, R.id.ll_device_shangdian, R.id.ll_device_zhishideng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_device_info:
                DeviceWgCzLogActivity.actionStart(mContext);
                break;
            case R.id.ll_device_shangdian:
                clickShangdian();
                break;
            case R.id.ll_device_zhishideng:
                clickZhishideng();
                break;
        }
    }

    private void clickZhishideng() {
        List<String> names = new ArrayList<>();
        names.add("指示开关");
        names.add("指示位置");
        names.add("关闭指示灯");
        final TuyaBottomDialog bottomDialog = new TuyaBottomDialog(this);
        bottomDialog.setModles(names);
        bottomDialog.setClickListener(new TuyaBottomDialogView.ClickListener() {
            @Override
            public void onClickItem(int pos) {
                bottomDialog.dismiss();
                if (pos == 0) {
                    setZhishideng(0);
                } else if (pos == 1) {
                    setZhishideng(1);
                } else if (pos == 2) {
                    setZhishideng(2);
                }
            }

            @Override
            public void onClickCancel(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.showBottom();
    }

    private void setZhishideng(int pos) {
        switch (pos) {
            case 0:
                setDp("40", "relay");
                break;
            case 1:
                setDp("40", "pos");
                break;
            case 2:
                setDp("40", "none");
                break;
        }
    }

    private void clickShangdian() {
        List<String> names = new ArrayList<>();
        names.add("断电");
        names.add("通电");
        names.add("保持重启前的状态");
        final TuyaBottomDialog bottomDialog = new TuyaBottomDialog(this);
        bottomDialog.setModles(names);
        bottomDialog.setClickListener(new TuyaBottomDialogView.ClickListener() {
            @Override
            public void onClickItem(int pos) {
                bottomDialog.dismiss();
                if (pos == 0) {
                    setduandian(0);
                } else if (pos == 1) {
                    setduandian(1);
                } else if (pos == 2) {
                    setduandian(2);
                }
            }

            @Override
            public void onClickCancel(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.showBottom();
    }

    private void setduandian(int pos) {
        switch (pos) {
            case 0:
                setDp("38", "off");
                break;
            case 1:
                setDp("38", "on");
                break;
            case 2:
                setDp("38", "memory");
                break;
        }
    }
}
