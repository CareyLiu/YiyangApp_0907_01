package com.yiyang.cn.model;

import java.util.List;

public class ServiceInfo {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"of_user_id":"499","user_phone":"18249030297","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9256","user_age":"29","user_name":"lsz"}]
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
         * of_user_id : 499
         * user_phone : 18249030297
         * user_img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9256
         * user_age : 29
         * user_name : lsz
         */

        private String of_user_id;
        private String user_phone;
        private String user_img_url;
        private String user_age;
        private String user_name;

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_img_url() {
            return user_img_url;
        }

        public void setUser_img_url(String user_img_url) {
            this.user_img_url = user_img_url;
        }

        public String getUser_age() {
            return user_age;
        }

        public void setUser_age(String user_age) {
            this.user_age = user_age;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
