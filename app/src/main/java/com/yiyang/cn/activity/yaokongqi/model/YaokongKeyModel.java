package com.yiyang.cn.activity.yaokongqi.model;

import java.io.Serializable;

public class YaokongKeyModel implements Serializable {
    private String mark_id;
    private String mark_name;
    private String mark_status;

    public String getMark_id() {
        return mark_id;
    }

    public void setMark_id(String mark_id) {
        this.mark_id = mark_id;
    }

    public String getMark_name() {
        return mark_name;
    }

    public void setMark_name(String mark_name) {
        this.mark_name = mark_name;
    }

    public String getMark_status() {
        return mark_status;
    }

    public void setMark_status(String mark_status) {
        this.mark_status = mark_status;
    }

    public YaokongKeyModel(String mark_id, String mark_name, String mark_status) {
        this.mark_id = mark_id;
        this.mark_name = mark_name;
        this.mark_status = mark_status;
    }
}
