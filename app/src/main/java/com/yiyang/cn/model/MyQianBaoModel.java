package com.yiyang.cn.model;

import java.util.List;

public class MyQianBaoModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"red_money_lock":"0.00","red_money_use":"20.01","money_lock":"0.00","money_use":"0.00"}]
     */

    private String msg_code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * red_money_lock : 0.00
         * red_money_use : 20.01
         * money_lock : 0.00
         * money_use : 0.00
         */

        private String red_money_lock;
        private String red_money_use;
        private String money_lock;
        private String money_use;

        public String getRed_money_lock() {
            return red_money_lock;
        }

        public void setRed_money_lock(String red_money_lock) {
            this.red_money_lock = red_money_lock;
        }

        public String getRed_money_use() {
            return red_money_use;
        }

        public void setRed_money_use(String red_money_use) {
            this.red_money_use = red_money_use;
        }

        public String getMoney_lock() {
            return money_lock;
        }

        public void setMoney_lock(String money_lock) {
            this.money_lock = money_lock;
        }

        public String getMoney_use() {
            return money_use;
        }

        public void setMoney_use(String money_use) {
            this.money_use = money_use;
        }
    }
}
