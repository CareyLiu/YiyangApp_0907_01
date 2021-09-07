package com.yiyang.cn.model;

import java.util.List;

public class ShangJiaListModel {
    /**
     * typeNext : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 3
     * data : [{"collection_id":"207","wares_id":"198","money_begin":"988.00","wares_type":"1","shop_product_id":"109","product_color":"套餐A","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10700","wares_state_name":"正常","product_title":"神灯风水摆件","wares_state":"1"},{"collection_id":"201","wares_id":"188","money_begin":"988.00","wares_type":"1","shop_product_id":"100","product_color":"套餐一","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10622","wares_state_name":"正常","product_title":"神灯风暖","wares_state":"1"},{"collection_id":"186","wares_id":"179","money_begin":"988.00","wares_type":"1","shop_product_id":"101","product_color":"智能家居含（智能窗帘）","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10617","wares_state_name":"正常","product_title":"智能家居","wares_state":"1"}]
     */

    private String typeNext;
    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

    public String getTypeNext() {
        return typeNext;
    }

    public void setTypeNext(String typeNext) {
        this.typeNext = typeNext;
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
         * collection_id : 207
         * wares_id : 198
         * money_begin : 988.00
         * wares_type : 1
         * shop_product_id : 109
         * product_color : 套餐A
         * index_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10700
         * wares_state_name : 正常
         * product_title : 神灯风水摆件
         * wares_state : 1
         */

        private String collection_id;
        private String wares_id;
        private String money_begin;
        private String wares_type;
        private String shop_product_id;
        private String product_color;
        private String index_photo_url;
        private String wares_state_name;
        private String product_title;
        private String wares_state;

        public String getCollection_id() {
            return collection_id;
        }

        public void setCollection_id(String collection_id) {
            this.collection_id = collection_id;
        }

        public String getWares_id() {
            return wares_id;
        }

        public void setWares_id(String wares_id) {
            this.wares_id = wares_id;
        }

        public String getMoney_begin() {
            return money_begin;
        }

        public void setMoney_begin(String money_begin) {
            this.money_begin = money_begin;
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

        public String getProduct_color() {
            return product_color;
        }

        public void setProduct_color(String product_color) {
            this.product_color = product_color;
        }

        public String getIndex_photo_url() {
            return index_photo_url;
        }

        public void setIndex_photo_url(String index_photo_url) {
            this.index_photo_url = index_photo_url;
        }

        public String getWares_state_name() {
            return wares_state_name;
        }

        public void setWares_state_name(String wares_state_name) {
            this.wares_state_name = wares_state_name;
        }

        public String getProduct_title() {
            return product_title;
        }

        public void setProduct_title(String product_title) {
            this.product_title = product_title;
        }

        public String getWares_state() {
            return wares_state;
        }

        public void setWares_state(String wares_state) {
            this.wares_state = wares_state;
        }
    }
}
