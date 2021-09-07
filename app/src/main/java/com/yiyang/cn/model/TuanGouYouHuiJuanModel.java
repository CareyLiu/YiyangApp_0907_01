package com.yiyang.cn.model;

import java.io.Serializable;
import java.util.List;

public class TuanGouYouHuiJuanModel implements Serializable {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"ticket_type":"2","of_user_id":"2223","agio_order":"2","create_time":"2020-08-02","agio_detail":"美食专享","residue_times":"1","usable_times":"1","agio_state":"1","ticket_user_type":"1","user_agio_id":"535","inst_state":"1","agio_money":"200.00","inst_agio_id":"50","agio_title":"200元美食券","inst_id":"0","user_time":"2020-12-22","validity_time":"2020-12-22"},{"ticket_type":"2","of_user_id":"2223","agio_order":"2","create_time":"2020-08-02","agio_detail":"美食专享","residue_times":"1","usable_times":"1","agio_state":"1","ticket_user_type":"1","user_agio_id":"534","inst_state":"1","agio_money":"200.00","inst_agio_id":"50","agio_title":"200元美食券","inst_id":"0","user_time":"2020-12-22","validity_time":"2020-12-22"},{"ticket_type":"2","of_user_id":"2223","agio_order":"2","create_time":"2020-08-02","agio_detail":"美食专享","residue_times":"1","usable_times":"1","agio_state":"1","ticket_user_type":"1","user_agio_id":"533","inst_state":"1","agio_money":"200.00","inst_agio_id":"50","agio_title":"200元美食券","inst_id":"0","user_time":"2020-12-22","validity_time":"2020-12-22"},{"ticket_type":"1","of_user_id":"2223","agio_order":"2","create_time":"2020-05-12","agio_detail":"呼兰河口湿地专享","residue_times":"60","usable_times":"60","agio_state":"1","ticket_user_type":"5","user_agio_id":"505","inst_state":"1","agio_money":"40.00","inst_agio_id":"36","agio_title":"40元景区免费券","inst_id":"310","user_time":"2020-12-22","validity_time":"2020-12-22"},{"ticket_type":"1","of_user_id":"2223","agio_order":"1","create_time":"2020-05-12","agio_detail":"呼兰河口湿地专享","residue_times":"30","usable_times":"30","agio_state":"1","ticket_user_type":"5","user_agio_id":"504","inst_state":"1","agio_money":"20.00","inst_agio_id":"35","agio_title":"20元景区免费券","inst_id":"310","user_time":"2020-12-22","validity_time":"2020-12-22"},{"ticket_type":"1","of_user_id":"2223","agio_order":"4","create_time":"2020-05-12","agio_detail":"麦克比火锅专享","residue_times":"30","usable_times":"30","agio_state":"1","ticket_user_type":"1","user_agio_id":"503","inst_state":"1","agio_money":"25.00","inst_agio_id":"34","agio_title":"25元美食美食券","inst_id":"258","user_time":"2020-11-12","validity_time":"2020-11-12"},{"ticket_type":"1","of_user_id":"2223","agio_order":"3","create_time":"2020-05-12","agio_detail":"麦香村西饼屋专享","residue_times":"10","usable_times":"10","agio_state":"1","ticket_user_type":"1","user_agio_id":"502","inst_state":"1","agio_money":"40.00","inst_agio_id":"33","agio_title":"40元美食免费券","inst_id":"263","user_time":"2019-12-10","validity_time":"2019-12-10"},{"ticket_type":"1","of_user_id":"2223","agio_order":"2","create_time":"2020-05-12","agio_detail":"麦香村西饼屋专享","residue_times":"30","usable_times":"30","agio_state":"1","ticket_user_type":"1","user_agio_id":"501","inst_state":"1","agio_money":"20.00","inst_agio_id":"32","agio_title":"20元美食免费券","inst_id":"263","user_time":"2019-11-30","validity_time":"2019-11-30"},{"ticket_type":"1","of_user_id":"2223","agio_order":"1","create_time":"2020-05-12","agio_detail":"麦香村西饼屋专享","residue_times":"60","usable_times":"60","agio_state":"1","ticket_user_type":"1","user_agio_id":"500","inst_state":"1","agio_money":"50.00","inst_agio_id":"31","agio_title":"50元美食优惠券","inst_id":"263","user_time":"2020-12-22","validity_time":"2020-12-22"},{"ticket_type":"1","of_user_id":"2223","agio_order":"1","create_time":"2020-05-12","agio_detail":"麦香村西饼屋专享","residue_times":"30","usable_times":"30","agio_state":"1","ticket_user_type":"1","user_agio_id":"499","inst_state":"1","agio_money":"30.00","inst_agio_id":"30","agio_title":"30元美食免费券","inst_id":"263","user_time":"2019-11-12","validity_time":"2019-11-12"}]
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

    public static class DataBean implements Serializable{
        /**
         * ticket_type : 2
         * of_user_id : 2223
         * agio_order : 2
         * create_time : 2020-08-02
         * agio_detail : 美食专享
         * residue_times : 1
         * usable_times : 1
         * agio_state : 1
         * ticket_user_type : 1
         * user_agio_id : 535
         * inst_state : 1
         * agio_money : 200.00
         * inst_agio_id : 50
         * agio_title : 200元美食券
         * inst_id : 0
         * user_time : 2020-12-22
         * validity_time : 2020-12-22
         */

        private String ticket_type;
        private String of_user_id;
        private String agio_order;
        private String create_time;
        private String agio_detail;
        private String residue_times;
        private String usable_times;
        private String agio_state;
        private String ticket_user_type;
        private String user_agio_id;
        private String inst_state;
        private String agio_money;
        private String inst_agio_id;
        private String agio_title;
        private String inst_id;
        private String user_time;
        private String validity_time;

        public String getTicket_type() {
            return ticket_type;
        }

        public void setTicket_type(String ticket_type) {
            this.ticket_type = ticket_type;
        }

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getAgio_order() {
            return agio_order;
        }

        public void setAgio_order(String agio_order) {
            this.agio_order = agio_order;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getAgio_detail() {
            return agio_detail;
        }

        public void setAgio_detail(String agio_detail) {
            this.agio_detail = agio_detail;
        }

        public String getResidue_times() {
            return residue_times;
        }

        public void setResidue_times(String residue_times) {
            this.residue_times = residue_times;
        }

        public String getUsable_times() {
            return usable_times;
        }

        public void setUsable_times(String usable_times) {
            this.usable_times = usable_times;
        }

        public String getAgio_state() {
            return agio_state;
        }

        public void setAgio_state(String agio_state) {
            this.agio_state = agio_state;
        }

        public String getTicket_user_type() {
            return ticket_user_type;
        }

        public void setTicket_user_type(String ticket_user_type) {
            this.ticket_user_type = ticket_user_type;
        }

        public String getUser_agio_id() {
            return user_agio_id;
        }

        public void setUser_agio_id(String user_agio_id) {
            this.user_agio_id = user_agio_id;
        }

        public String getInst_state() {
            return inst_state;
        }

        public void setInst_state(String inst_state) {
            this.inst_state = inst_state;
        }

        public String getAgio_money() {
            return agio_money;
        }

        public void setAgio_money(String agio_money) {
            this.agio_money = agio_money;
        }

        public String getInst_agio_id() {
            return inst_agio_id;
        }

        public void setInst_agio_id(String inst_agio_id) {
            this.inst_agio_id = inst_agio_id;
        }

        public String getAgio_title() {
            return agio_title;
        }

        public void setAgio_title(String agio_title) {
            this.agio_title = agio_title;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getUser_time() {
            return user_time;
        }

        public void setUser_time(String user_time) {
            this.user_time = user_time;
        }

        public String getValidity_time() {
            return validity_time;
        }

        public void setValidity_time(String validity_time) {
            this.validity_time = validity_time;
        }
    }
}
