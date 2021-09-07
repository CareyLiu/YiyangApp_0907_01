package com.yiyang.cn.activity.xiupeichang.model;

import java.util.List;

public class XpcPingjiaModel {


    /**
     * next : 1
     * msg_code : 0000
     * msg : ok
     * data : [{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"499","user_img_url":"https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png","user_to_score":"5.00","user_name":"月亮啦"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2032","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","user_to_score":"5.00","user_name":"阿钊"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2223","user_img_url":"commons/easyui/images/weishezhitouxiang.png","user_to_score":"5.00","user_name":"再详细"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2223","user_img_url":"commons/easyui/images/weishezhitouxiang.png","user_to_score":"5.00","user_name":"再详细"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2223","user_img_url":"commons/easyui/images/weishezhitouxiang.png","user_to_score":"5.00","user_name":"再详细"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2223","user_img_url":"commons/easyui/images/weishezhitouxiang.png","user_to_score":"5.00","user_name":"再详细"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2223","user_img_url":"commons/easyui/images/weishezhitouxiang.png","user_to_score":"5.00","user_name":"再详细"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2223","user_img_url":"commons/easyui/images/weishezhitouxiang.png","user_to_score":"5.00","user_name":"再详细"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2223","user_img_url":"commons/easyui/images/weishezhitouxiang.png","user_to_score":"5.00","user_name":"再详细"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2223","user_img_url":"commons/easyui/images/weishezhitouxiang.png","user_to_score":"5.00","user_name":"再详细"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2223","user_img_url":"commons/easyui/images/weishezhitouxiang.png","user_to_score":"5.00","user_name":"再详细"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2223","user_img_url":"commons/easyui/images/weishezhitouxiang.png","user_to_score":"5.00","user_name":"再详细"}]
     */

    private String next;
    private String msg_code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_to_text : 体验非常好
         * user_to_time : 2020-06-06 09:49:58
         * of_user_id : 499
         * user_img_url : https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png
         * user_to_score : 5.00
         * user_name : 月亮啦
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
