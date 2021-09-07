package com.yiyang.cn.activity.tuya_device.device.model;

public class DpsTimeModel {
    private Object dps;
    private String time;

    public DpsTimeModel(Object dps, String time) {
        this.dps = dps;
        this.time = time;
    }

    public Object getDps() {
        return dps;
    }

    public void setDps(Object dps) {
        this.dps = dps;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
