package com.yiyang.cn.model;

import java.util.List;

public class ZhiNengRoomManageCreatBean {

    /**
     * msg_code : 0007
     * msg : 操作失败，需要管理员身份
     * row_num : 1
     * data : []
     */

    private String msg_code;
    private String msg;
    private String row_num;
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
