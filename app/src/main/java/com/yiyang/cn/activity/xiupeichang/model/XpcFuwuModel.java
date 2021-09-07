package com.yiyang.cn.activity.xiupeichang.model;

import java.util.List;

public class XpcFuwuModel {


    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * data : [{"pay_count":"13","wares_id":"270","agio":"60","week":"","img_url":"http://192.168.1.127:8080/Frame/uploadFile/showImg?file_id=11659","shop_detail":"贴片补胎","itemtype_id":"2","shop_title":"补胎","inst_id":"229","shop_money_now":"30.00","shop_money_old":"50.00"},{"pay_count":"0","wares_id":"271","agio":"50","week":"","img_url":"http://192.168.1.127:8080/Frame/uploadFile/showImg?file_id=11660","shop_detail":"贴片补胎","itemtype_id":"2","shop_title":"补胎","inst_id":"229","shop_money_now":"100.00","shop_money_old":"200.00"}]
     */

    private String next;
    private String msg_code;
    private String msg;
    private List<DataBean> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pay_count : 13
         * wares_id : 270
         * agio : 60
         * week :
         * img_url : http://192.168.1.127:8080/Frame/uploadFile/showImg?file_id=11659
         * shop_detail : 贴片补胎
         * itemtype_id : 2
         * shop_title : 补胎
         * inst_id : 229
         * shop_money_now : 30.00
         * shop_money_old : 50.00
         */

        private String pay_count;
        private String wares_id;
        private String agio;
        private String week;
        private String img_url;
        private String shop_detail;
        private String itemtype_id;
        private String shop_title;
        private String inst_id;
        private String shop_money_now;
        private String shop_money_old;

        public String getPay_count() {
            return pay_count;
        }

        public void setPay_count(String pay_count) {
            this.pay_count = pay_count;
        }

        public String getWares_id() {
            return wares_id;
        }

        public void setWares_id(String wares_id) {
            this.wares_id = wares_id;
        }

        public String getAgio() {
            return agio;
        }

        public void setAgio(String agio) {
            this.agio = agio;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getShop_detail() {
            return shop_detail;
        }

        public void setShop_detail(String shop_detail) {
            this.shop_detail = shop_detail;
        }

        public String getItemtype_id() {
            return itemtype_id;
        }

        public void setItemtype_id(String itemtype_id) {
            this.itemtype_id = itemtype_id;
        }

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getShop_money_now() {
            return shop_money_now;
        }

        public void setShop_money_now(String shop_money_now) {
            this.shop_money_now = shop_money_now;
        }

        public String getShop_money_old() {
            return shop_money_old;
        }

        public void setShop_money_old(String shop_money_old) {
            this.shop_money_old = shop_money_old;
        }
    }
}
