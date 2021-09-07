package com.yiyang.cn.model;

import java.util.List;

public class TuanGouShengChengDingDanBean {
    /**
     * available_balance : 0.00
     * msg_code : 0000
     * msg : ok
     * data : [{"money":"258.00","count":"0"}]
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
         * money : 258.00
         * count : 0
         */

        private String money;
        private String count;

        public String getAvailable_balance() {
            return available_balance;
        }

        public void setAvailable_balance(String available_balance) {
            this.available_balance = available_balance;
        }

        private String available_balance;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
