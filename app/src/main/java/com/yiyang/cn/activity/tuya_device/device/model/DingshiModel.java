package com.yiyang.cn.activity.tuya_device.device.model;

public class DingshiModel {

    private String xingqi;
    private String select;

    public DingshiModel(String xingqi, String select) {
        this.xingqi = xingqi;
        this.select = select;
    }

    public String getXingqi() {
        return xingqi;
    }

    public void setXingqi(String xingqi) {
        this.xingqi = xingqi;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }
}
