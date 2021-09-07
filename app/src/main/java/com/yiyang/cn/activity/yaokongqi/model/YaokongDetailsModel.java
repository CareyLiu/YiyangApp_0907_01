package com.yiyang.cn.activity.yaokongqi.model;

import java.io.Serializable;
import java.util.List;

public class YaokongDetailsModel implements Serializable {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"room_id":"0","device_ccid":"28010102","device_type":"28","is_voice":"1","device_category":"01","fj_interval":"","is_alarm":"1","ty_device_ccid":"","ty_family_id":"","device_name":"红外遥控2","device_ccid_up":"aaaaaaaaaaaaa00040140128","family_id":"259","noti_info":"","label_header":"2802","device_type_name":"红外遥控","device_id":"973","fj_time":"","control_keys_list":[{"mark_id":"1","mark_status":"1","mark_name":"静音"},{"mark_id":"2","mark_status":"0","mark_name":"电源"},{"mark_id":"3","mark_status":"0","mark_name":"音量加"},{"mark_id":"4","mark_status":"0","mark_name":"音量减"},{"mark_id":"5","mark_status":"0","mark_name":"菜单"},{"mark_id":"6","mark_status":"0","mark_name":"切换"},{"mark_id":"7","mark_status":"0","mark_name":"频道加"},{"mark_id":"8","mark_status":"0","mark_name":"频道减"},{"mark_id":"9","mark_status":"0","mark_name":"上"},{"mark_id":"10","mark_status":"0","mark_name":"下"},{"mark_id":"11","mark_status":"0","mark_name":"左"},{"mark_id":"12","mark_status":"0","mark_name":"右"},{"mark_id":"13","mark_status":"0","mark_name":"确认"}],"ty_room_id":"","server_id":"8/","wifi_user":"","room_name":"默认房间","timing_state":"0","device_type_pic":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12015","online_state":"1","fj_times":"","family_name":"咕哒之家"}]
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

    public static class DataBean  implements Serializable{
        /**
         * room_id : 0
         * device_ccid : 28010102
         * device_type : 28
         * is_voice : 1
         * device_category : 01
         * fj_interval :
         * is_alarm : 1
         * ty_device_ccid :
         * ty_family_id :
         * device_name : 红外遥控2
         * device_ccid_up : aaaaaaaaaaaaa00040140128
         * family_id : 259
         * noti_info :
         * label_header : 2802
         * device_type_name : 红外遥控
         * device_id : 973
         * fj_time :
         * control_keys_list : [{"mark_id":"1","mark_status":"1","mark_name":"静音"},{"mark_id":"2","mark_status":"0","mark_name":"电源"},{"mark_id":"3","mark_status":"0","mark_name":"音量加"},{"mark_id":"4","mark_status":"0","mark_name":"音量减"},{"mark_id":"5","mark_status":"0","mark_name":"菜单"},{"mark_id":"6","mark_status":"0","mark_name":"切换"},{"mark_id":"7","mark_status":"0","mark_name":"频道加"},{"mark_id":"8","mark_status":"0","mark_name":"频道减"},{"mark_id":"9","mark_status":"0","mark_name":"上"},{"mark_id":"10","mark_status":"0","mark_name":"下"},{"mark_id":"11","mark_status":"0","mark_name":"左"},{"mark_id":"12","mark_status":"0","mark_name":"右"},{"mark_id":"13","mark_status":"0","mark_name":"确认"}]
         * ty_room_id :
         * server_id : 8/
         * wifi_user :
         * room_name : 默认房间
         * timing_state : 0
         * device_type_pic : https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12015
         * online_state : 1
         * fj_times :
         * family_name : 咕哒之家
         */

        private String room_id;
        private String device_ccid;
        private String device_type;
        private String is_voice;
        private String device_category;
        private String fj_interval;
        private String is_alarm;
        private String ty_device_ccid;
        private String ty_family_id;
        private String device_name;
        private String device_ccid_up;
        private String family_id;
        private String noti_info;
        private String label_header;
        private String device_type_name;
        private String device_id;
        private String fj_time;
        private String ty_room_id;
        private String server_id;
        private String wifi_user;
        private String room_name;
        private String timing_state;
        private String device_type_pic;
        private String online_state;
        private String fj_times;
        private String family_name;
        private List<ControlKeysListBean> control_keys_list;

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getDevice_ccid() {
            return device_ccid;
        }

        public void setDevice_ccid(String device_ccid) {
            this.device_ccid = device_ccid;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getIs_voice() {
            return is_voice;
        }

        public void setIs_voice(String is_voice) {
            this.is_voice = is_voice;
        }

        public String getDevice_category() {
            return device_category;
        }

        public void setDevice_category(String device_category) {
            this.device_category = device_category;
        }

        public String getFj_interval() {
            return fj_interval;
        }

        public void setFj_interval(String fj_interval) {
            this.fj_interval = fj_interval;
        }

        public String getIs_alarm() {
            return is_alarm;
        }

        public void setIs_alarm(String is_alarm) {
            this.is_alarm = is_alarm;
        }

        public String getTy_device_ccid() {
            return ty_device_ccid;
        }

        public void setTy_device_ccid(String ty_device_ccid) {
            this.ty_device_ccid = ty_device_ccid;
        }

        public String getTy_family_id() {
            return ty_family_id;
        }

        public void setTy_family_id(String ty_family_id) {
            this.ty_family_id = ty_family_id;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_ccid_up() {
            return device_ccid_up;
        }

        public void setDevice_ccid_up(String device_ccid_up) {
            this.device_ccid_up = device_ccid_up;
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

        public String getLabel_header() {
            return label_header;
        }

        public void setLabel_header(String label_header) {
            this.label_header = label_header;
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

        public String getFj_time() {
            return fj_time;
        }

        public void setFj_time(String fj_time) {
            this.fj_time = fj_time;
        }

        public String getTy_room_id() {
            return ty_room_id;
        }

        public void setTy_room_id(String ty_room_id) {
            this.ty_room_id = ty_room_id;
        }

        public String getServer_id() {
            return server_id;
        }

        public void setServer_id(String server_id) {
            this.server_id = server_id;
        }

        public String getWifi_user() {
            return wifi_user;
        }

        public void setWifi_user(String wifi_user) {
            this.wifi_user = wifi_user;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getTiming_state() {
            return timing_state;
        }

        public void setTiming_state(String timing_state) {
            this.timing_state = timing_state;
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

        public String getFj_times() {
            return fj_times;
        }

        public void setFj_times(String fj_times) {
            this.fj_times = fj_times;
        }

        public String getFamily_name() {
            return family_name;
        }

        public void setFamily_name(String family_name) {
            this.family_name = family_name;
        }

        public List<ControlKeysListBean> getControl_keys_list() {
            return control_keys_list;
        }

        public void setControl_keys_list(List<ControlKeysListBean> control_keys_list) {
            this.control_keys_list = control_keys_list;
        }

        public static class ControlKeysListBean implements Serializable{
            /**
             * mark_id : 1
             * mark_status : 1
             * mark_name : 静音
             */

            private String mark_id;
            private String mark_status;
            private String mark_name;

            public String getMark_id() {
                return mark_id;
            }

            public void setMark_id(String mark_id) {
                this.mark_id = mark_id;
            }

            public String getMark_status() {
                return mark_status;
            }

            public void setMark_status(String mark_status) {
                this.mark_status = mark_status;
            }

            public String getMark_name() {
                return mark_name;
            }

            public void setMark_name(String mark_name) {
                this.mark_name = mark_name;
            }
        }
    }
}
