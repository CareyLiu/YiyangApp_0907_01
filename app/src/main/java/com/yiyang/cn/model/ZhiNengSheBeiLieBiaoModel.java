package com.yiyang.cn.model;

import java.util.List;

public class ZhiNengSheBeiLieBiaoModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"device_name":"灯","device_name_two":"神灯智能灯泡","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_id_two":"01","user_device_id":"218","device_name_one":"灯","device_id_one":"01"},{"device_name":"灯2","device_name_two":"神灯智能灯泡","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_id_two":"01","user_device_id":"219","device_name_one":"灯","device_id_one":"01"},{"device_name":"灯3","device_name_two":"神灯智能灯泡","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_id_two":"01","user_device_id":"220","device_name_one":"灯","device_id_one":"01"},{"device_name":"窗帘电机","device_name_two":"神灯智能电动窗帘","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_id_two":"01","user_device_id":"223","device_name_one":"窗帘电机","device_id_one":"16"},{"device_name":"插座/插排","device_name_two":"神灯智能插座","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_id_two":"01","user_device_id":"228","device_name_one":"插座/插排","device_id_one":"02"}]
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
         * device_name : 灯
         * device_name_two : 神灯智能灯泡
         * img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920
         * device_id_two : 01
         * user_device_id : 218
         * device_name_one : 灯
         * device_id_one : 01
         */

        private String device_name;
        private String device_name_two;
        private String img_url;
        private String device_id_two;
        private String user_device_id;
        private String device_name_one;
        private String device_id_one;

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_name_two() {
            return device_name_two;
        }

        public void setDevice_name_two(String device_name_two) {
            this.device_name_two = device_name_two;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getDevice_id_two() {
            return device_id_two;
        }

        public void setDevice_id_two(String device_id_two) {
            this.device_id_two = device_id_two;
        }

        public String getUser_device_id() {
            return user_device_id;
        }

        public void setUser_device_id(String user_device_id) {
            this.user_device_id = user_device_id;
        }

        public String getDevice_name_one() {
            return device_name_one;
        }

        public void setDevice_name_one(String device_name_one) {
            this.device_name_one = device_name_one;
        }

        public String getDevice_id_one() {
            return device_id_one;
        }

        public void setDevice_id_one(String device_id_one) {
            this.device_id_one = device_id_one;
        }
    }
}
