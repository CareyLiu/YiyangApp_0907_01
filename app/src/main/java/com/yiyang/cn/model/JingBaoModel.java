package com.yiyang.cn.model;

import java.util.List;

public class JingBaoModel {
    /**
     * next : 1
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"device_state_name":"有人移动","alerm_time":"上午 09:45:51","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 09:46:05","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 09:46:09","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 10:12:17","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 10:12:44","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 10:13:19","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 10:13:32","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 10:14:05","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 10:14:19","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 10:14:42","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 10:14:57","device_state":""},{"device_state_name":"有人移动","alerm_time":"上午 10:15:34","device_state":""}]
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
         * device_state_name : 有人移动
         * alerm_time : 上午 09:45:51
         * device_state :
         */

        private String device_state_name;
        private String alerm_time;
        private String device_state;

        public String getDevice_state_name() {
            return device_state_name;
        }

        public void setDevice_state_name(String device_state_name) {
            this.device_state_name = device_state_name;
        }

        public String getAlerm_time() {
            return alerm_time;
        }

        public void setAlerm_time(String alerm_time) {
            this.alerm_time = alerm_time;
        }

        public String getDevice_state() {
            return device_state;
        }

        public void setDevice_state(String device_state) {
            this.device_state = device_state;
        }
    }
}
