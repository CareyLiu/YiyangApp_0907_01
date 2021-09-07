package com.yiyang.cn.model;

import java.util.List;

public class EmptyModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : []
     */

    private String msg_code;
    private String msg;
    private List<?> data;

    public String getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(String msg_code) {
        this.msg_code = msg_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
