package com.yiyang.cn.activity.tuya_device.utils.manager;

import android.app.Activity;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaTishiDialog;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.IDevListener;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.bean.Timer;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TuyaDeviceManager {
    private static TuyaDeviceManager instance;
    private DeviceBean deviceBean;
    private String devId;
    private Gson gson;
    private Timer timer;

    private int p2pType;

    public static TuyaDeviceManager getDeviceManager() {
        if (null == instance) {
            instance = new TuyaDeviceManager();
        }
        return instance;
    }

    public void initDevice(DeviceBean deviceBean) {
        this.deviceBean = deviceBean;
        this.devId = deviceBean.getDevId();
        this.gson = new Gson();

        Y.e("设备Id时多少 " + deviceBean.getDevId());

        String schema = deviceBean.getSchema();
        Y.e("我是什么状态 " + deviceBean.getIsOnline());
        Y.e(schema);
        Y.e(deviceBean.getProductBean().getCategory() + "     " + deviceBean.getProductBean().getCategoryCode() + "    " + deviceBean.getProductId());
    }

    public ITuyaDevice getDevice() {
        return TuyaHomeSdk.newDeviceInstance(devId);
    }

    public DeviceBean getDeviceBeen() {
        return deviceBean;
    }

    public String getDevId() {
        return devId;
    }

    public String getJosnString(Map dps) {
        return gson.toJson(dps);
    }

    public void getData(String devId, String dpStr) {
        JSONObject jsonObject = JSON.parseObject(dpStr);
        Set<String> strings = jsonObject.keySet();
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            // 获得key
            String key = it.next();
            String value = jsonObject.getString(key);
            Y.e("key: " + key + ",value:" + value);
        }
    }

    public int getP2pType() {
        Map<String, Object> map = deviceBean.getSkills();
        p2pType = 1;
        if (map == null || map.size() == 0) {
            p2pType = 1;
        } else {
            p2pType = (Integer) (map.get("p2pType"));
        }
        return p2pType;
    }

    //定义GB的计算常量
    private static final int GB = 1024 * 1024;
    //定义MB的计算常量
    private static final int MB = 1024;

    public static String bytes2kb(long bytes) {
        //格式化小数
        DecimalFormat format = new DecimalFormat("###.0");
        if (bytes / GB >= 1) {
            return format.format(bytes / GB) + "GB";
        } else if (bytes / MB >= 1) {
            return format.format(bytes / MB) + "MB";
        } else {
            return bytes + "KB";
        }
    }

    public void device_removed(Activity mActivity) {
        try {
            TuyaTishiDialog dialog = new TuyaTishiDialog(mActivity, new TuyaTishiDialog.TishiDialogListener() {
                @Override
                public void onClickCancel(View v, TuyaTishiDialog dialog) {

                }

                @Override
                public void onClickConfirm(View v, TuyaTishiDialog dialog) {

                }

                @Override
                public void onDismiss(TuyaTishiDialog dialog) {
                    mActivity.finish();
                }
            });
            dialog.setTextCont("设备已被移除");
            dialog.setTextConfirm("确定");
            dialog.setTextCancel("");
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
