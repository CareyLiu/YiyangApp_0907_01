package com.yiyang.cn.model;

import java.util.List;

public class ZhiiNengRoomDeviceRoomBean {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"room_id":"0","family_id":"17","noti_info":"控制设备时可通过设备的【位置】或【名称】进行控制","device_type_name":"灯","device_id":"45","device_ccid":"0101","kids_mode":"3","device_type":"01","server_id":"","room_name":"默认房间","device_name":"灯","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10524","online_state":"2","work_state":"2","warn_state":"3","auto_state":"3","family_name":"我的爱家","device_ccid_up":"asasasasasasasasasasas01"}]
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
         * room_id : 0
         * family_id : 17
         * noti_info : 控制设备时可通过设备的【位置】或【名称】进行控制
         * device_type_name : 灯
         * device_id : 45
         * device_ccid : 0101
         * kids_mode : 3
         * device_type : 01
         * server_id :
         * room_name : 默认房间
         * device_name : 灯
         * device_type_pic : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10524
         * online_state : 2
         * work_state : 2
         * warn_state : 3
         * auto_state : 3
         * family_name : 我的爱家
         * device_ccid_up : asasasasasasasasasasas01
         */

        private String room_id;
        private String family_id;
        private String noti_info;
        private String device_type_name;
        private String device_id;
        private String device_ccid;
        private String kids_mode;
        private String device_type;
        private String server_id;
        private String room_name;
        private String device_name;
        private String device_type_pic;
        private String online_state;
        private String work_state;
        private String warn_state;
        private String auto_state;
        private String family_name;
        private String device_ccid_up;
        public String device_state;
        public String is_voice;

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getFamily_id() {
            return family_id;
        }

        public void setFamily_id(String family_id) {
            this.family_id = family_id;
        }

        public String getNoti_info() {
            return noti_info;
        }

        public void setNoti_info(String noti_info) {
            this.noti_info = noti_info;
        }

        public String getDevice_type_name() {
            return device_type_name;
        }

        public void setDevice_type_name(String device_type_name) {
            this.device_type_name = device_type_name;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getDevice_ccid() {
            return device_ccid;
        }

        public void setDevice_ccid(String device_ccid) {
            this.device_ccid = device_ccid;
        }

        public String getKids_mode() {
            return kids_mode;
        }

        public void setKids_mode(String kids_mode) {
            this.kids_mode = kids_mode;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getServer_id() {
            return server_id;
        }

        public void setServer_id(String server_id) {
            this.server_id = server_id;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_type_pic() {
            return device_type_pic;
        }

        public void setDevice_type_pic(String device_type_pic) {
            this.device_type_pic = device_type_pic;
        }

        public String getOnline_state() {
            return online_state;
        }

        public void setOnline_state(String online_state) {
            this.online_state = online_state;
        }

        public String getWork_state() {
            return work_state;
        }

        public void setWork_state(String work_state) {
            this.work_state = work_state;
        }

        public String getWarn_state() {
            return warn_state;
        }

        public void setWarn_state(String warn_state) {
            this.warn_state = warn_state;
        }

        public String getAuto_state() {
            return auto_state;
        }

        public void setAuto_state(String auto_state) {
            this.auto_state = auto_state;
        }

        public String getFamily_name() {
            return family_name;
        }

        public void setFamily_name(String family_name) {
            this.family_name = family_name;
        }

        public String getDevice_ccid_up() {
            return device_ccid_up;
        }

        public void setDevice_ccid_up(String device_ccid_up) {
            this.device_ccid_up = device_ccid_up;
        }
    }
}
