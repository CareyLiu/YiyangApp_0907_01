package com.yiyang.cn.model;

public class ZhiNengJiaJuNotifyJson {
    /**
     * code : jyj_0006
     * device_ccid : 1201
     * device_ccid_up : jjjjjjjjjjjjjjjjjjjjjj11
     * device_id : 54
     * device_type : 12
     * server_id : 1/
     */

    private String code;
    private String device_ccid;
    private String device_ccid_up;
    private String device_id;
    private String device_type;
    private String server_id;
    public String online_state;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDevice_ccid() {
        return device_ccid;
    }

    public void setDevice_ccid(String device_ccid) {
        this.device_ccid = device_ccid;
    }

    public String getDevice_ccid_up() {
        return device_ccid_up;
    }

    public void setDevice_ccid_up(String device_ccid_up) {
        this.device_ccid_up = device_ccid_up;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getServer_id() {
        return server_id;
    }

    public void setServer_id(String server_id) {
        this.server_id = server_id;
    }
}
