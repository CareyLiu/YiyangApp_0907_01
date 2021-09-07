package com.yiyang.cn.model;

import com.chad.library.adapter.base.entity.SectionEntity;

public class GouWuCheZhengHeModel extends SectionEntity {
    private String shopcart_id;
    private String inst_id;
    private String inst_name;
    private String inst_logo_url;
    private String subsystem_id;

    private String shop_product_title;
    private String shop_product_id;
    private String wares_id;
    private String form_product_id;
    private String disabled_cause;
    private String wares_money_go;
    private String product_title;
    private String index_photo_url;
    private String form_product_money;
    private String pay_count;
    private String form_product_state;
    private String wares_go_type;
    private String bottomYuanJiao = "0";//0不是圆角 1 是圆角
    private String isSelect = "0";//默认没有选中
    private String liuyan = "";
    private String installation_type_id;//安装id


    public String getInstallation_type_id() {
        return installation_type_id;
    }

    public void setInstallation_type_id(String installation_type_id) {
        this.installation_type_id = installation_type_id;
    }


    public String getLiuyan() {
        return liuyan;
    }

    public void setLiuyan(String liuyan) {
        this.liuyan = liuyan;
    }


    public String getBottomYuanJiao() {
        return bottomYuanJiao;
    }

    public void setBottomYuanJiao(String bottomYuanJiao) {
        this.bottomYuanJiao = bottomYuanJiao;
    }


    public String getBiaoshi() {
        return biaoshi;
    }

    public void setBiaoshi(String biaoshi) {
        this.biaoshi = biaoshi;
    }

    private String biaoshi = "0x11";//规则 同一个店铺下的商品会有相同的 0x11 + for循环的i 也就是第一个循环的循环号

    public GouWuCheZhengHeModel(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public String getShopcart_id() {
        return shopcart_id;
    }

    public void setShopcart_id(String shopcart_id) {
        this.shopcart_id = shopcart_id;
    }

    public String getInst_id() {
        return inst_id;
    }

    public void setInst_id(String inst_id) {
        this.inst_id = inst_id;
    }

    public String getInst_name() {
        return inst_name;
    }

    public void setInst_name(String inst_name) {
        this.inst_name = inst_name;
    }

    public String getInst_logo_url() {
        return inst_logo_url;
    }

    public void setInst_logo_url(String inst_logo_url) {
        this.inst_logo_url = inst_logo_url;
    }

    public String getSubsystem_id() {
        return subsystem_id;
    }

    public void setSubsystem_id(String subsystem_id) {
        this.subsystem_id = subsystem_id;
    }

    public String getShop_product_title() {
        return shop_product_title;
    }

    public void setShop_product_title(String shop_product_title) {
        this.shop_product_title = shop_product_title;
    }

    public String getShop_product_id() {
        return shop_product_id;
    }

    public void setShop_product_id(String shop_product_id) {
        this.shop_product_id = shop_product_id;
    }

    public String getWares_id() {
        return wares_id;
    }

    public void setWares_id(String wares_id) {
        this.wares_id = wares_id;
    }

    public String getForm_product_id() {
        return form_product_id;
    }

    public void setForm_product_id(String form_product_id) {
        this.form_product_id = form_product_id;
    }

    public String getDisabled_cause() {
        return disabled_cause;
    }

    public void setDisabled_cause(String disabled_cause) {
        this.disabled_cause = disabled_cause;
    }

    public String getWares_money_go() {
        return wares_money_go;
    }

    public void setWares_money_go(String wares_money_go) {
        this.wares_money_go = wares_money_go;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getIndex_photo_url() {
        return index_photo_url;
    }

    public void setIndex_photo_url(String index_photo_url) {
        this.index_photo_url = index_photo_url;
    }

    public String getForm_product_money() {
        return form_product_money;
    }

    public void setForm_product_money(String form_product_money) {
        this.form_product_money = form_product_money;
    }

    public String getPay_count() {
        return pay_count;
    }

    public void setPay_count(String pay_count) {
        this.pay_count = pay_count;
    }

    public String getForm_product_state() {
        return form_product_state;
    }

    public void setForm_product_state(String form_product_state) {
        this.form_product_state = form_product_state;
    }

    public String getWares_go_type() {
        return wares_go_type;
    }

    public void setWares_go_type(String wares_go_type) {
        this.wares_go_type = wares_go_type;
    }

    public String getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(String isSelect) {
        this.isSelect = isSelect;
    }
}
