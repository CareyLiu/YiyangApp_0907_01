package com.yiyang.cn.activity.tongcheng58.model;

import java.util.List;

public class TcSheshiModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 5
     * data : [{"name":"刷卡支付","id":"1"},{"name":"免费上网","id":"2"},{"name":"免费停车","id":"3"},{"name":"提供包房","id":"4"},{"name":"桌游娱乐","id":"5"}]
     */

    private String msg_code;
    private String msg;
    private String row_num;
    private List<ShangjiaDetailModel.DataBean.TypeArrayBean> data;

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

    public List<ShangjiaDetailModel.DataBean.TypeArrayBean> getData() {
        return data;
    }

    public void setData(List<ShangjiaDetailModel.DataBean.TypeArrayBean> data) {
        this.data = data;
    }


}
