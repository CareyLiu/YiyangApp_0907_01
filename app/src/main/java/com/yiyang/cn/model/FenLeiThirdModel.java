package com.yiyang.cn.model;

import java.util.List;

public class FenLeiThirdModel {
    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 7
     * data : [{"wares_sales_volume":"已售0件","wares_id":"15","agio":"1.915342","red_packet_money":"0.08","money_now":"10.00","wares_type":"1","shop_product_id":"5","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9936","inst_name":"神灯科技旗舰店","wares_name":"竖版语音液晶开关","money_make":"","product_title":"黑色"},{"wares_sales_volume":"已售0件","wares_id":"9","agio":"8.333333","red_packet_money":"0.08","money_now":"10.00","wares_type":"1","shop_product_id":"9","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9935","inst_name":"神灯科技旗舰店","wares_name":"横版数码按键开关","money_make":"","product_title":"套餐5"},{"wares_sales_volume":"已售0件","wares_id":"9","agio":"9.811938","red_packet_money":"4.00","money_now":"12.00","wares_type":"1","shop_product_id":"1","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9931","inst_name":"神灯科技旗舰店","wares_name":"横版数码按键开关","money_make":"","product_title":"套餐1"},{"wares_sales_volume":"已售0件","wares_id":"15","agio":"2.777135","red_packet_money":"0.08","money_now":"12.00","wares_type":"1","shop_product_id":"6","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9937","inst_name":"神灯科技旗舰店","wares_name":"竖版语音液晶开关","money_make":"","product_title":"红色"},{"wares_sales_volume":"已售0件","wares_id":"9","agio":"9.320905","red_packet_money":"0.08","money_now":"14.00","wares_type":"1","shop_product_id":"2","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9933","inst_name":"神灯科技旗舰店","wares_name":"横版数码按键开关","money_make":"","product_title":"套餐2"},{"wares_sales_volume":"已售0件","wares_id":"9","agio":"8.820287","red_packet_money":"0.08","money_now":"16.00","wares_type":"1","shop_product_id":"3","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9932","inst_name":"神灯科技旗舰店","wares_name":"横版数码按键开关","money_make":"","product_title":"套餐3"},{"wares_sales_volume":"已售0件","wares_id":"15","agio":"5.128205","red_packet_money":"0.08","money_now":"40.00","wares_type":"1","shop_product_id":"12","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9938","inst_name":"神灯科技旗舰店","wares_name":"竖版语音液晶开关","money_make":"","product_title":"黄色"}]
     */

    private String next;
    private String msg_code;
    private String msg;
    private String row_num;
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
         * wares_sales_volume : 已售0件
         * wares_id : 15
         * agio : 1.915342
         * red_packet_money : 0.08
         * money_now : 10.00
         * wares_type : 1
         * shop_product_id : 5
         * index_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9936
         * inst_name : 神灯科技旗舰店
         * wares_name : 竖版语音液晶开关
         * money_make :
         * product_title : 黑色
         */

        private String wares_sales_volume;
        private String wares_id;
        private String agio;
        private String red_packet_money;
        private String money_now;
        private String wares_type;
        private String shop_product_id;
        private String index_photo_url;
        private String inst_name;
        private String wares_name;
        private String money_make;
        private String product_title;

        public String getWares_sales_volume() {
            return wares_sales_volume;
        }

        public void setWares_sales_volume(String wares_sales_volume) {
            this.wares_sales_volume = wares_sales_volume;
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

        public String getRed_packet_money() {
            return red_packet_money;
        }

        public void setRed_packet_money(String red_packet_money) {
            this.red_packet_money = red_packet_money;
        }

        public String getMoney_now() {
            return money_now;
        }

        public void setMoney_now(String money_now) {
            this.money_now = money_now;
        }

        public String getWares_type() {
            return wares_type;
        }

        public void setWares_type(String wares_type) {
            this.wares_type = wares_type;
        }

        public String getShop_product_id() {
            return shop_product_id;
        }

        public void setShop_product_id(String shop_product_id) {
            this.shop_product_id = shop_product_id;
        }

        public String getIndex_photo_url() {
            return index_photo_url;
        }

        public void setIndex_photo_url(String index_photo_url) {
            this.index_photo_url = index_photo_url;
        }

        public String getInst_name() {
            return inst_name;
        }

        public void setInst_name(String inst_name) {
            this.inst_name = inst_name;
        }

        public String getWares_name() {
            return wares_name;
        }

        public void setWares_name(String wares_name) {
            this.wares_name = wares_name;
        }

        public String getMoney_make() {
            return money_make;
        }

        public void setMoney_make(String money_make) {
            this.money_make = money_make;
        }

        public String getProduct_title() {
            return product_title;
        }

        public void setProduct_title(String product_title) {
            this.product_title = product_title;
        }
    }
}
