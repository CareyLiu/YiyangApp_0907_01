package com.yiyang.cn.fragment.znjj.utils;

import android.content.Context;

import com.airbnb.lottie.animation.content.Content;
import com.google.gson.Gson;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.fragment.znjj.model.ZhiNengModel;
import com.tuya.smart.sdk.bean.DeviceBean;

public class MqttManager {
    private static MqttManager instance;
    private Context mContext;


    public static MqttManager getMqttManager() {
        if (null == instance) {
            instance = new MqttManager();
        }
        return instance;
    }


    public void initMqtt(Context context, ZhiNengModel.DataBean dataBean) {
        mContext = context;
        PreferenceHelper.getInstance(mContext).putString(AppConfig.PEIWANG_FAMILYID, dataBean.getFamily_id());

        



    }


}
