package com.yiyang.cn.model;

import java.util.List;

public class XinTuanYouShengChengDingDanBean {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"gunNo":"4","money":"0.01","paid_money":"0.00","count":"0","litre":"0.00","oilName":"90#","inst_id":"377","reduction_money":"0.01","available_balance":"0.00","oilNo":"90"}]
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
         * gunNo : 4
         * money : 0.01
         * paid_money : 0.00
         * count : 0
         * litre : 0.00
         * oilName : 90#
         * inst_id : 377
         * reduction_money : 0.01
         * available_balance : 0.00
         * oilNo : 90
         */

        private String gunNo;
        private String money;
        private String paid_money;
        private String count;
        private String litre;
        private String oilName;
        private String inst_id;
        private String reduction_money;
        private String available_balance;
        private String oilNo;
        private String is_buy;
        private String wx_pay;

        public String getIs_buy() {
            return is_buy;
        }

        public void setIs_buy(String is_buy) {
            this.is_buy = is_buy;
        }

        public String getWx_pay() {
            return wx_pay;
        }

        public void setWx_pay(String wx_pay) {
            this.wx_pay = wx_pay;
        }

        public String getAli_pay() {
            return ali_pay;
        }

        public void setAli_pay(String ali_pay) {
            this.ali_pay = ali_pay;
        }

        private String ali_pay;

        public String getGunNo() {
            return gunNo;
        }

        public void setGunNo(String gunNo) {
            this.gunNo = gunNo;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPaid_money() {
            return paid_money;
        }

        public void setPaid_money(String paid_money) {
            this.paid_money = paid_money;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getLitre() {
            return litre;
        }

        public void setLitre(String litre) {
            this.litre = litre;
        }

        public String getOilName() {
            return oilName;
        }

        public void setOilName(String oilName) {
            this.oilName = oilName;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getReduction_money() {
            return reduction_money;
        }

        public void setReduction_money(String reduction_money) {
            this.reduction_money = reduction_money;
        }

        public String getAvailable_balance() {
            return available_balance;
        }

        public void setAvailable_balance(String available_balance) {
            this.available_balance = available_balance;
        }

        public String getOilNo() {
            return oilNo;
        }

        public void setOilNo(String oilNo) {
            this.oilNo = oilNo;
        }
    }
}
