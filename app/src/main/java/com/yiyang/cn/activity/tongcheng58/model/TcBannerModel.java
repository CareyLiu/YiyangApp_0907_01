package com.yiyang.cn.activity.tongcheng58.model;

public class TcBannerModel {

    private String ir_img_id;
    private String ir_img_url;

    public TcBannerModel(String ir_img_id, String ir_img_url) {
        this.ir_img_id = ir_img_id;
        this.ir_img_url = ir_img_url;
    }

    public String getIr_img_id() {
        return ir_img_id;
    }

    public void setIr_img_id(String ir_img_id) {
        this.ir_img_id = ir_img_id;
    }

    public String getIr_img_url() {
        return ir_img_url;
    }

    public void setIr_img_url(String ir_img_url) {
        this.ir_img_url = ir_img_url;
    }
}
