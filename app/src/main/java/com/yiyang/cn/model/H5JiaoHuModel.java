package com.yiyang.cn.model;

public class H5JiaoHuModel {


    /**
     * pay : {"key":"","key1":""}
     * type : 1
     */

    private PayBean pay;
    private String type;

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class PayBean {
        /**
         * key :
         * key1 :
         */

        private String key;
        private String key1;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey1() {
            return key1;
        }

        public void setKey1(String key1) {
            this.key1 = key1;
        }
    }
}
