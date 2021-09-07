package com.yiyang.cn.model;

import java.util.List;

public class MingxiModel {


    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"user_money":"1.50","par_type":"11","form_no":"2018040320314609553","er_type":"2","pay_title":"商城订单","pay_type_name":"微信","form_state_name":"支付成功","inst_name":"","pay_detail":"居安普尼智能门锁 黄色半自动","pay_time":"2019-12-08"}]
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
         * user_money : 1.50
         * par_type : 11
         * form_no : 2018040320314609553
         * er_type : 2
         * pay_title : 商城订单
         * pay_type_name : 微信
         * form_state_name : 支付成功
         * inst_name :
         * pay_detail : 居安普尼智能门锁 黄色半自动
         * pay_time : 2019-12-08
         */

        private String user_money;
        private String par_type;
        private String form_no;
        private String er_type;
        private String pay_title;
        private String pay_type_name;
        private String form_state_name;
        private String inst_name;
        private String pay_detail;
        private String pay_time;

        private String rollback_no;
        private String refund_time;

        /**
         * charge_money : 2.00
         * create_time : 2020-08-01
         * order_money : 14.00
         */

        private String charge_money;
        private String create_time;
        private String order_money;

        public String getRollback_no() {
            return rollback_no;
        }

        public void setRollback_no(String rollback_no) {
            this.rollback_no = rollback_no;
        }

        public String getRefund_time() {
            return refund_time;
        }

        public void setRefund_time(String refund_time) {
            this.refund_time = refund_time;
        }

        public String getUser_money() {
            return user_money;
        }


        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getPar_type() {
            return par_type;
        }

        public void setPar_type(String par_type) {
            this.par_type = par_type;
        }

        public String getForm_no() {
            return form_no;
        }

        public void setForm_no(String form_no) {
            this.form_no = form_no;
        }

        public String getEr_type() {
            return er_type;
        }

        public void setEr_type(String er_type) {
            this.er_type = er_type;
        }

        public String getPay_title() {
            return pay_title;
        }

        public void setPay_title(String pay_title) {
            this.pay_title = pay_title;
        }

        public String getPay_type_name() {
            return pay_type_name;
        }

        public void setPay_type_name(String pay_type_name) {
            this.pay_type_name = pay_type_name;
        }

        public String getForm_state_name() {
            return form_state_name;
        }

        public void setForm_state_name(String form_state_name) {
            this.form_state_name = form_state_name;
        }

        public String getInst_name() {
            return inst_name;
        }

        public void setInst_name(String inst_name) {
            this.inst_name = inst_name;
        }

        public String getPay_detail() {
            return pay_detail;
        }

        public void setPay_detail(String pay_detail) {
            this.pay_detail = pay_detail;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getCharge_money() {
            return charge_money;
        }

        public void setCharge_money(String charge_money) {
            this.charge_money = charge_money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getOrder_money() {
            return order_money;
        }

        public void setOrder_money(String order_money) {
            this.order_money = order_money;
        }
    }
}
