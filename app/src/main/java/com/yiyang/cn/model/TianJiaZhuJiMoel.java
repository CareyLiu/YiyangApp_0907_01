package com.yiyang.cn.model;

import java.util.List;

public class TianJiaZhuJiMoel {
    /**
     * is_added : 1
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : []
     */

    private String is_added;
    private String msg_code;
    private String msg;
    private String row_num;
    private List<?> data;

    public String getIs_added() {
        return is_added;
    }

    public void setIs_added(String is_added) {
        this.is_added = is_added;
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

    public String getRow_num() {
        return row_num;
    }

    public void setRow_num(String row_num) {
        this.row_num = row_num;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
