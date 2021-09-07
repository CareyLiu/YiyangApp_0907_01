package com.yiyang.cn.activity.wode_page.bazinew.model;

import java.util.List;

public class FengshuiDetails {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"mingpan_id":"318","goods_name":"天王财神爷开光佛像","ls_jx":"1","ccid":"202008020010000000000001","ji_img":"http://bz-goods.oss-cn-hangzhou.aliyuncs.com/ceshiji.jpg","xiong_img":"http://bz-goods.oss-cn-hangzhou.aliyuncs.com/ceshixiong.jpg","goods_img":"http://bz-goods.oss-cn-hangzhou.aliyuncs.com/twcskgfx.jpg","mingpan_goods_switch":"1"}]
     */

    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mingpan_id : 318
         * goods_name : 天王财神爷开光佛像
         * ls_jx : 1
         * ccid : 202008020010000000000001
         * ji_img : http://bz-goods.oss-cn-hangzhou.aliyuncs.com/ceshiji.jpg
         * xiong_img : http://bz-goods.oss-cn-hangzhou.aliyuncs.com/ceshixiong.jpg
         * goods_img : http://bz-goods.oss-cn-hangzhou.aliyuncs.com/twcskgfx.jpg
         * mingpan_goods_switch : 1
         */

        private String mingpan_id;
        private String goods_name;
        private String ls_jx;
        private String ccid;
        private String ji_img;
        private String xiong_img;
        private String goods_img;
        private String mingpan_goods_switch;

        public String getMingpan_id() {
            return mingpan_id;
        }

        public void setMingpan_id(String mingpan_id) {
            this.mingpan_id = mingpan_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getLs_jx() {
            return ls_jx;
        }

        public void setLs_jx(String ls_jx) {
            this.ls_jx = ls_jx;
        }

        public String getCcid() {
            return ccid;
        }

        public void setCcid(String ccid) {
            this.ccid = ccid;
        }

        public String getJi_img() {
            return ji_img;
        }

        public void setJi_img(String ji_img) {
            this.ji_img = ji_img;
        }

        public String getXiong_img() {
            return xiong_img;
        }

        public void setXiong_img(String xiong_img) {
            this.xiong_img = xiong_img;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getMingpan_goods_switch() {
            return mingpan_goods_switch;
        }

        public void setMingpan_goods_switch(String mingpan_goods_switch) {
            this.mingpan_goods_switch = mingpan_goods_switch;
        }
    }
}
