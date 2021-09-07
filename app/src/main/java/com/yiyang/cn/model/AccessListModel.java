package com.yiyang.cn.model;

import java.util.List;

public class AccessListModel {
    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"user_to_text":"太好了，物超所值，祝双城神灯科技生意兴隆","user_to_time":"2020-07-03 19:38:04","of_user_id":"2156","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","user_to_score":"5.00","user_name":"未设置"}]
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
         * user_to_text : 太好了，物超所值，祝双城神灯科技生意兴隆
         * user_to_time : 2020-07-03 19:38:04
         * of_user_id : 2156
         * user_img_url : https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png
         * user_to_score : 5.00
         * user_name : 未设置
         */

        private String user_to_text;
        private String user_to_time;
        private String of_user_id;
        private String user_img_url;
        private String user_to_score;
        private String user_name;

        public String getUser_to_text() {
            return user_to_text;
        }

        public void setUser_to_text(String user_to_text) {
            this.user_to_text = user_to_text;
        }

        public String getUser_to_time() {
            return user_to_time;
        }

        public void setUser_to_time(String user_to_time) {
            this.user_to_time = user_to_time;
        }

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getUser_img_url() {
            return user_img_url;
        }

        public void setUser_img_url(String user_img_url) {
            this.user_img_url = user_img_url;
        }

        public String getUser_to_score() {
            return user_to_score;
        }

        public void setUser_to_score(String user_to_score) {
            this.user_to_score = user_to_score;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
