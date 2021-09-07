package com.yiyang.cn.model;

import java.util.List;

public class DianPuXiangQingModel {
    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"inst_accid":"jcz_sub_224","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10943","inst_photo_id":"10943","isCollection":"1","addr_all":"黑龙江省哈尔滨市道里区抚顺街道黑龙江省哈尔滨市","waresList":[{"wares_sales_volume":"已售0件","wares_id":"203","shop_product_title":"春天安全门","money_begin":"998.00","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10961","wares_type":"1","shop_product_id":"114"}],"subsystem_id":"jcz","x":"45.756462","shop_type":"","y":"126.617992","collection_count":"1","inst_id":"407","inst_name":"春天门业"}]
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
         * inst_accid : jcz_sub_224
         * inst_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10943
         * inst_photo_id : 10943
         * isCollection : 1
         * addr_all : 黑龙江省哈尔滨市道里区抚顺街道黑龙江省哈尔滨市
         * waresList : [{"wares_sales_volume":"已售0件","wares_id":"203","shop_product_title":"春天安全门","money_begin":"998.00","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10961","wares_type":"1","shop_product_id":"114"}]
         * subsystem_id : jcz
         * x : 45.756462
         * shop_type :
         * y : 126.617992
         * collection_count : 1
         * inst_id : 407
         * inst_name : 春天门业
         */

        private String inst_accid;
        private String inst_photo_url;
        private String inst_photo_id;
        private String isCollection;
        private String addr_all;
        private String subsystem_id;
        private String x;
        private String shop_type;
        private String y;
        private String collection_count;
        private String inst_id;
        private String inst_name;
        private List<WaresListBean> waresList;

        public String getInst_accid() {
            return inst_accid;
        }

        public void setInst_accid(String inst_accid) {
            this.inst_accid = inst_accid;
        }

        public String getInst_photo_url() {
            return inst_photo_url;
        }

        public void setInst_photo_url(String inst_photo_url) {
            this.inst_photo_url = inst_photo_url;
        }

        public String getInst_photo_id() {
            return inst_photo_id;
        }

        public void setInst_photo_id(String inst_photo_id) {
            this.inst_photo_id = inst_photo_id;
        }

        public String getIsCollection() {
            return isCollection;
        }

        public void setIsCollection(String isCollection) {
            this.isCollection = isCollection;
        }

        public String getAddr_all() {
            return addr_all;
        }

        public void setAddr_all(String addr_all) {
            this.addr_all = addr_all;
        }

        public String getSubsystem_id() {
            return subsystem_id;
        }

        public void setSubsystem_id(String subsystem_id) {
            this.subsystem_id = subsystem_id;
        }

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        public String getCollection_count() {
            return collection_count;
        }

        public void setCollection_count(String collection_count) {
            this.collection_count = collection_count;
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

        public List<WaresListBean> getWaresList() {
            return waresList;
        }

        public void setWaresList(List<WaresListBean> waresList) {
            this.waresList = waresList;
        }

        public static class WaresListBean {
            /**
             * wares_sales_volume : 已售0件
             * wares_id : 203
             * shop_product_title : 春天安全门
             * money_begin : 998.00
             * wares_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10961
             * wares_type : 1
             * shop_product_id : 114
             */

            private String wares_sales_volume;
            private String wares_id;
            private String shop_product_title;
            private String money_begin;
            private String wares_photo_url;
            private String wares_type;
            private String shop_product_id;

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

            public String getShop_product_title() {
                return shop_product_title;
            }

            public void setShop_product_title(String shop_product_title) {
                this.shop_product_title = shop_product_title;
            }

            public String getMoney_begin() {
                return money_begin;
            }

            public void setMoney_begin(String money_begin) {
                this.money_begin = money_begin;
            }

            public String getWares_photo_url() {
                return wares_photo_url;
            }

            public void setWares_photo_url(String wares_photo_url) {
                this.wares_photo_url = wares_photo_url;
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
        }
    }
}
