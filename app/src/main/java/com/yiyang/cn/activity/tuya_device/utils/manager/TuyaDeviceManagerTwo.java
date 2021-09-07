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

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TuyaDeviceManagerTwo implements IDevListener {
    private static TuyaDeviceManagerTwo instance;
    private DeviceBean deviceBean;
    private ITuyaDevice mDevice;
    private Gson gson;

    public static TuyaDeviceManagerTwo getDeviceManager() {
        if (null == instance) {
            instance = new TuyaDeviceManagerTwo();
        }
        return instance;
    }

    public void initDevice(DeviceBean deviceBean) {
        if (deviceBean != null) {
            unRegisterDevListenerTwo();

            this.deviceBean = deviceBean;
            mDevice = TuyaHomeSdk.newDeviceInstance(deviceBean.getDevId());
            mDevice.registerDevListener(this);

            gson = new Gson();

            String schema = deviceBean.getSchema();
            Y.e("我是什么状态 " + deviceBean.getIsOnline());
            Y.e(schema);
            Y.e(deviceBean.getProductBean().getCategory() + "     " + deviceBean.getProductBean().getCategoryCode() + "    " + deviceBean.getProductId());
        }
    }

    public ITuyaDevice getDevice() {
        return mDevice;
    }

    public DeviceBean getDeviceBeen() {
        return deviceBean;
    }

    public void setDeviceBeen(DeviceBean deviceBean) {
        this.deviceBean = deviceBean;
    }

    public String getJosnString(Map dps) {
        return gson.toJson(dps);
    }

    public void unRegisterDevListener() {
        if (mDevice != null) {
            mDevice.unRegisterDevListener();
            mDevice.onDestroy();
            mDevice = null;
        }
    }

    public void unRegisterDevListenerTwo() {
        if (mDevice != null) {
            mDevice.unRegisterDevListener();
        }
    }

    /**
     * dp数据更新
     *
     * @param devId 设备 id
     * @param dpStr 设备发生变动的功能点，为 json 字符串，数据格式：{"101": true}
     */
    @Override
    public void onDpUpdate(String devId, String dpStr) {
        Y.e("Dp点数据更新啦:" + devId + " | " + dpStr);
        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_DEVICE_DATA;
        notice.content = dpStr;
        notice.devId = devId;
        RxBus.getDefault().sendRx(notice);
    }

    /**
     * 设备移除回调
     *
     * @param devId 设备id
     */
    @Override
    public void onRemoved(String devId) {

    }


    /**
     * 设备上下线回调
     *
     * @param devId  设备id
     * @param online 是否在线，在线为 true
     */
    @Override
    public void onStatusChanged(String devId, boolean online) {
        Y.e("设备上下线回调  " + online);
        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_DEVICE_STATUSCHANGED;
        notice.devId = devId;
        notice.content = online;
        RxBus.getDefault().sendRx(notice);
    }


    /**
     * 网络状态发生变动时的回调
     *
     * @param devId  设备id
     * @param status 网络状态是否可用，可用为 true
     */
    @Override
    public void onNetworkStatusChanged(String devId, boolean status) {

    }

    /**
     * 设备信息更新回调
     *
     * @param devId 设备 id
     */
    @Override
    public void onDevInfoUpdate(String devId) {

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

    private int p2pType;

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
    }
}
