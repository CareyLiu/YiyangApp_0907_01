package com.yiyang.cn.activity.tuya_device.utils.manager;

import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.utils.TuyaDialogUtils;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.tuya.smart.api.service.MicroServiceManager;
import com.tuya.smart.commonbiz.bizbundle.family.api.AbsBizBundleFamilyService;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.api.ITuyaHomeDeviceStatusListener;
import com.tuya.smart.home.sdk.api.ITuyaHomeStatusListener;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.bean.WeatherBean;
import com.tuya.smart.home.sdk.callback.IIGetHomeWetherSketchCallBack;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;
import com.tuya.smart.home.sdk.callback.ITuyaResultCallback;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.bean.SigMeshBean;

import java.util.List;

public class TuyaHomeManager {
    private static TuyaHomeManager instance;
    private HomeBean homeBean;
    private long homeId;
    private ITuyaHomeStatusListener iTuyaHomeStatusListener;

    public static TuyaHomeManager getHomeManager() {
        if (null == instance) {
            instance = new TuyaHomeManager();
        }
        return instance;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
        initHome();
    }

    private void initHome() {
        if (iTuyaHomeStatusListener != null) {
            TuyaHomeSdk.newHomeInstance(homeId).unRegisterHomeStatusListener(iTuyaHomeStatusListener);
        }

        TuyaHomeSdk.newHomeInstance(homeId).getHomeDetail(new ITuyaHomeResultCallback() {
            @Override
            public void onSuccess(HomeBean bean) {
                Y.e("设置家庭成功了");
                setHomeBean(bean);

                AbsBizBundleFamilyService familyService = MicroServiceManager.getInstance().findServiceByInterface(AbsBizBundleFamilyService.class.getName());
                familyService.setCurrentHomeId(homeId);

                setTianqi(bean);

                setHomeListen();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Y.e("设置家庭失败了");
            }
        });
    }

    private void setHomeListen() {
        Y.e("设置家庭监听成功");
        iTuyaHomeStatusListener = new ITuyaHomeStatusListener() {
            @Override
            public void onDeviceAdded(String devId) {
                Y.e("添加了设备啊啊啊啊  " + devId);
            }

            @Override
            public void onDeviceRemoved(String devId) {
                Y.e("移除了一个设备" + devId);
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_DEVICE_DELETE_TUYA;
                notice.devId = devId;
                RxBus.getDefault().sendRx(notice);
            }

            @Override
            public void onGroupAdded(long groupId) {

            }

            @Override
            public void onGroupRemoved(long groupId) {

            }

            @Override
            public void onMeshAdded(String meshId) {

            }
        };
        TuyaHomeSdk.newHomeInstance(homeId).registerHomeStatusListener(iTuyaHomeStatusListener);
    }

    private void setTianqi(HomeBean bean) {
        if (bean.getLon() != 0) {
            TuyaHomeSdk.newHomeInstance(bean.getHomeId()).getHomeWeatherSketch(bean.getLon(), bean.getLat(), new IIGetHomeWetherSketchCallBack() {
                @Override
                public void onSuccess(WeatherBean result) {
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_TUYA_TIANQI;
                    notice.content = result;
                    RxBus.getDefault().sendRx(notice);
                }

                @Override
                public void onFailure(String errorCode, String errorMsg) {
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_TUYA_TIANQI;
                    notice.content = null;
                    RxBus.getDefault().sendRx(notice);
                }
            });
        } else {
            Notice notice = new Notice();
            notice.type = ConstanceValue.MSG_TUYA_TIANQI;
            notice.content = null;
            RxBus.getDefault().sendRx(notice);
        }
    }

    public DeviceBean isHaveDevice(String devId) {
        DeviceBean mineDeviceBean = null;
        if (homeBean != null) {
            List<DeviceBean> deviceList = homeBean.getDeviceList();

            for (int i = 0; i < deviceList.size(); i++) {
                DeviceBean deviceBean = deviceList.get(i);
                Y.e(" 绑定的设备 " + deviceBean.getName());
                Y.e(deviceBean.getDevId());

                if (devId.equals(deviceBean.getDevId())) {
                    mineDeviceBean = deviceBean;
                    Y.e("获取的功能点是什么啊啊  " + deviceBean.getSchema() + "    " + deviceBean.getIsOnline() + "     " + deviceBean.getDps().toString());
                }
            }
        }
        return mineDeviceBean;
    }
}
