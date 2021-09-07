package com.yiyang.cn.activity.tuya_device.add.model;

import java.io.Serializable;

public class TuyaAddDeviceModel implements Serializable {
    private String name;
    private String icon;
    private String deviceId;
    private boolean isSelect;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
