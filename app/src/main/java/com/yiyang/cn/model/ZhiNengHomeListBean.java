package com.yiyang.cn.model;

import java.util.List;

public class ZhiNengHomeListBean {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"member_type":"1","active":"2","device_num":"0","family_id":"30","room_num":"0","family_name":"我的家"},{"member_type":"2","active":"1","device_num":"15","family_id":"17","room_num":"2","family_name":"我的爱家"}]
     */

    private String msg_code;
    private String msg;
    private String row_num;
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
         * member_type : 1
         * active : 2
         * device_num : 0
         * family_id : 30
         * room_num : 0
         * family_name : 我的家
         */

        private String member_type;
        private String active;
        private String device_num;
        private String family_id;
        private String ty_family_id;
        private String room_num;
        private String family_name;

        public String getTy_family_id() {
            return ty_family_id;
        }

        public void setTy_family_id(String ty_family_id) {
            this.ty_family_id = ty_family_id;
        }

        public String getMember_type() {
            return member_type;
        }

        public void setMember_type(String member_type) {
            this.member_type = member_type;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getDevice_num() {
            return device_num;
        }

        public void setDevice_num(String device_num) {
            this.device_num = device_num;
        }

        public String getFamily_id() {
            return family_id;
        }

        public void setFamily_id(String family_id) {
            this.family_id = family_id;
        }

        public String getRoom_num() {
            return room_num;
        }

        public void setRoom_num(String room_num) {
            this.room_num = room_num;
        }

        public String getFamily_name() {
            return family_name;
        }

        public void setFamily_name(String family_name) {
            this.family_name = family_name;
        }
    }
}
