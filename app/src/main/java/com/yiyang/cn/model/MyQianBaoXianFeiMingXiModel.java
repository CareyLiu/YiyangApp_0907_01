package com.yiyang.cn.model;

import java.io.Serializable;
import java.util.List;

public class MyQianBaoXianFeiMingXiModel {
    /**
     * typeNext : 1
     * msg_code : 0000
     * msg : ok
     * data : [{"income":"收入¥0.01","cunsumerList":[{"user_pay_id":"547","par_type":"15","er_type":"2","pay_title":"直接下单","money":"-0.02","time":"05月16日   13:31","pause_img_name":"支出"},{"user_pay_id":"545","par_type":"12","er_type":"1","pay_title":"用户获得红包","money":"+0.00","time":"05月16日   13:31","pause_img_name":"收入"},{"user_pay_id":"544","par_type":"15","er_type":"2","pay_title":"直接下单","money":"-0.02","time":"05月16日   13:31","pause_img_name":"支出"},{"user_pay_id":"542","par_type":"12","er_type":"1","pay_title":"用户获得红包","money":"+0.00","time":"05月16日   13:31","pause_img_name":"收入"},{"user_pay_id":"541","par_type":"15","er_type":"2","pay_title":"直接下单","money":"-0.12","time":"05月16日   11:30","pause_img_name":"支出"},{"user_pay_id":"539","par_type":"12","er_type":"1","pay_title":"用户获得红包","money":"+0.00","time":"05月16日   11:30","pause_img_name":"收入"},{"user_pay_id":"538","par_type":"15","er_type":"2","pay_title":"直接下单","money":"-0.02","time":"05月16日   11:27","pause_img_name":"支出"},{"user_pay_id":"536","par_type":"12","er_type":"1","pay_title":"用户获得红包","money":"+0.00","time":"05月16日   11:27","pause_img_name":"收入"}],"month":"2020-05","pay":"支出¥0.20"}]
     */

    private String typeNext;
    private String msg_code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * income : 收入¥0.01
         * cunsumerList : [{"user_pay_id":"547","par_type":"15","er_type":"2","pay_title":"直接下单","money":"-0.02","time":"05月16日   13:31","pause_img_name":"支出"},{"user_pay_id":"545","par_type":"12","er_type":"1","pay_title":"用户获得红包","money":"+0.00","time":"05月16日   13:31","pause_img_name":"收入"},{"user_pay_id":"544","par_type":"15","er_type":"2","pay_title":"直接下单","money":"-0.02","time":"05月16日   13:31","pause_img_name":"支出"},{"user_pay_id":"542","par_type":"12","er_type":"1","pay_title":"用户获得红包","money":"+0.00","time":"05月16日   13:31","pause_img_name":"收入"},{"user_pay_id":"541","par_type":"15","er_type":"2","pay_title":"直接下单","money":"-0.12","time":"05月16日   11:30","pause_img_name":"支出"},{"user_pay_id":"539","par_type":"12","er_type":"1","pay_title":"用户获得红包","money":"+0.00","time":"05月16日   11:30","pause_img_name":"收入"},{"user_pay_id":"538","par_type":"15","er_type":"2","pay_title":"直接下单","money":"-0.02","time":"05月16日   11:27","pause_img_name":"支出"},{"user_pay_id":"536","par_type":"12","er_type":"1","pay_title":"用户获得红包","money":"+0.00","time":"05月16日   11:27","pause_img_name":"收入"}]
         * month : 2020-05
         * pay : 支出¥0.20
         */

        private String income;
        private String month;
        private String pay;
        private List<CunsumerListBean> cunsumerList;

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public List<CunsumerListBean> getCunsumerList() {
            return cunsumerList;
        }

        public void setCunsumerList(List<CunsumerListBean> cunsumerList) {
            this.cunsumerList = cunsumerList;
        }

        public static class CunsumerListBean implements Serializable {
            /**
             * user_pay_id : 547
             * par_type : 15
             * er_type : 2
             * pay_title : 直接下单
             * money : -0.02
             * time : 05月16日   13:31
             * pause_img_name : 支出
             */

            private String user_pay_id;
            private String par_type;
            private String er_type;
            private String pay_title;
            private String money;
            private String time;
            private String pause_img_name;
            private String form_state_name;
            private String inst_name;
            private String pay_time;
            private String user_money;
            private String form_no;
            private String create_time;
            private String pay_type_name;
            private String pay_detail;
            private String refund_time;

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

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public String getUser_money() {
                return user_money;
            }

            public void setUser_money(String user_money) {
                this.user_money = user_money;
            }

            public String getForm_no() {
                return form_no;
            }

            public void setForm_no(String form_no) {
                this.form_no = form_no;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getPay_type_name() {
                return pay_type_name;
            }

            public void setPay_type_name(String pay_type_name) {
                this.pay_type_name = pay_type_name;
            }

            public String getPay_detail() {
                return pay_detail;
            }

            public void setPay_detail(String pay_detail) {
                this.pay_detail = pay_detail;
            }

            public String getRefund_time() {
                return refund_time;
            }

            public void setRefund_time(String refund_time) {
                this.refund_time = refund_time;
            }

            public String getRollback_no() {
                return rollback_no;
            }

            public void setRollback_no(String rollback_no) {
                this.rollback_no = rollback_no;
            }

            private String rollback_no;


            public String getUser_pay_id() {
                return user_pay_id;
            }

            public void setUser_pay_id(String user_pay_id) {
                this.user_pay_id = user_pay_id;
            }

            public String getPar_type() {
                return par_type;
            }

            public void setPar_type(String par_type) {
                this.par_type = par_type;
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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getPause_img_name() {
                return pause_img_name;
            }

            public void setPause_img_name(String pause_img_name) {
                this.pause_img_name = pause_img_name;
            }
        }
    }
}
