package com.yiyang.cn.activity.yaokongqi.model;

import java.util.List;

public class YaokongTagModel {


    /**
     * label_header : 2801
     * msg_code : 0000
     * msg : ok
     * data : []
     */

    private String label_header;
    private String msg_code;
    private String msg;
    private List<?> data;

    public String getLabel_header() {
        return label_header;
    }

    public void setLabel_header(String label_header) {
        this.label_header = label_header;
    }

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
