package com.yiyang.cn.activity.tuya_device;

import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManagerTwo;
import com.yiyang.cn.app.BaseActivity;
import com.tuya.smart.sdk.api.IResultCallback;

import java.util.HashMap;
import java.util.Map;

public class TuyaBaseDeviceActivity extends BaseActivity {

    public void setDp(String key, Object value) {
        Map<String, Object> dps = new HashMap<>();
        dps.put(key, value);
        TuyaDeviceManager.getDeviceManager().getDevice().publishDps(TuyaDeviceManager.getDeviceManager().getJosnString(dps), new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                Y.t("操作失败" + error);
            }

            @Override
            public void onSuccess() {
                Y.e("操作成功:  key = " + key + "  |  value = " + value);
            }
        });
    }


    public void getDp(String dpId) {
        TuyaDeviceManager.getDeviceManager().getDevice().getDp(dpId, new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                Y.e("获取点失败 " + code + "  " + error);
            }

            @Override
            public void onSuccess() {
                Y.e("获取点成功");
            }
        });
    }
}
