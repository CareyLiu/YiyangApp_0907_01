package com.yiyang.cn.model;

import java.util.List;

public class WenShiDuChuanGanQiModel {
    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"room_id":"0","device_hum":"10","environment_status_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/tuya9.png","device_ccid":"36010101","device_type":"36","is_voice":"","device_category":"01","fj_interval":"","is_alarm":"1","ty_device_ccid":"","ty_family_id":"","device_name":"温湿度检测2","work_state":"3","environment_status_des":"当前温度舒适","auto_state":"3","hum_list":[{"device_hum":"20","time":"11:04"},{"device_hum":"30","time":"12:04"},{"device_hum":"40","time":"13:04"},{"device_hum":"50","time":"14:04"},{"device_hum":"10","time":"15:30"}],"device_ccid_up":"aaaaaaaaaaaaaaaa80140018","family_id":"8","noti_info":"","device_type_name":"温湿度检测","device_id":"290","kids_mode":"3","fj_time":"","device_tem":"17","tem_list":[{"device_tem":"20","time":"11:04"},{"device_tem":"13","time":"12:04"},{"device_tem":"11","time":"13:04"},{"device_tem":"15","time":"14:04"},{"device_tem":"17","time":"15:30"}],"ty_room_id":"","server_id":"8/","room_name":"默认房间","timing_state":"0","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","online_state":"1","warn_state":"3","fj_times":"","family_name":"我的家"}]
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
         * device_hum : 10
         * environment_status_img : http://znjj-img.oss-cn-hangzhou.aliyuncs.com/tuya9.png
         * device_ccid : 36010101
         * device_type : 36
         * is_voice :
         * device_category : 01
         * fj_interval :
         * is_alarm : 1
         * ty_device_ccid :
         * ty_family_id :
         * device_name : 温湿度检测2
         * work_state : 3
         * environment_status_des : 当前温度舒适
         * auto_state : 3
         * hum_list : [{"device_hum":"20","time":"11:04"},{"device_hum":"30","time":"12:04"},{"device_hum":"40","time":"13:04"},{"device_hum":"50","time":"14:04"},{"device_hum":"10","time":"15:30"}]
         * device_ccid_up : aaaaaaaaaaaaaaaa80140018
         * family_id : 8
         * noti_info :
         * device_type_name : 温湿度检测
         * device_id : 290
         * kids_mode : 3
         * fj_time :
         * device_tem : 17
         * tem_list : [{"device_tem":"20","time":"11:04"},{"device_tem":"13","time":"12:04"},{"device_tem":"11","time":"13:04"},{"device_tem":"15","time":"14:04"},{"device_tem":"17","time":"15:30"}]
         * ty_room_id :
         * server_id : 8/
         * room_name : 默认房间
         * timing_state : 0
         * device_type_pic : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920
         * online_state : 1
         * warn_state : 3
         * fj_times :
         * family_name : 我的家
         */

        private String room_id;
        private String device_hum;
        private String environment_status_img;
        private String device_ccid;
        private String device_type;
        private String is_voice;
        private String device_category;
        private String fj_interval;
        private String is_alarm;
        private String ty_device_ccid;
        private String ty_family_id;
        private String device_name;
        private String work_state;
        private String environment_status_des;
        private String auto_state;
        private String device_ccid_up;
        private String family_id;
        private String noti_info;
        private String device_type_name;
        private String device_id;
        private String kids_mode;
        private String fj_time;
        private String device_tem;
        private String ty_room_id;
        private String server_id;
        private String room_name;
        private String timing_state;
        private String device_type_pic;
        private String online_state;
        private String warn_state;
        private String fj_times;
        private String family_name;
        private List<HumListBean> hum_list;
        private List<TemListBean> tem_list;

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getDevice_hum() {
            return device_hum;
        }

        public void setDevice_hum(String device_hum) {
            this.device_hum = device_hum;
        }

        public String getEnvironment_status_img() {
            return environment_status_img;
        }

        public void setEnvironment_status_img(String environment_status_img) {
            this.environment_status_img = environment_status_img;
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

        public String getWork_state() {
            return work_state;
        }

        public void setWork_state(String work_state) {
            this.work_state = work_state;
        }

        public String getEnvironment_status_des() {
            return environment_status_des;
        }

        public void setEnvironment_status_des(String environment_status_des) {
            this.environment_status_des = environment_status_des;
        }

        public String getAuto_state() {
            return auto_state;
        }

        public void setAuto_state(String auto_state) {
            this.auto_state = auto_state;
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

        public String getKids_mode() {
            return kids_mode;
        }

        public void setKids_mode(String kids_mode) {
            this.kids_mode = kids_mode;
        }

        public String getFj_time() {
            return fj_time;
        }

        public void setFj_time(String fj_time) {
            this.fj_time = fj_time;
        }

        public String getDevice_tem() {
            return device_tem;
        }

        public void setDevice_tem(String device_tem) {
            this.device_tem = device_tem;
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

        public String getWarn_state() {
            return warn_state;
        }

        public void setWarn_state(String warn_state) {
            this.warn_state = warn_state;
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

        public List<HumListBean> getHum_list() {
            return hum_list;
        }

        public void setHum_list(List<HumListBean> hum_list) {
            this.hum_list = hum_list;
        }

        public List<TemListBean> getTem_list() {
            return tem_list;
        }

        public void setTem_list(List<TemListBean> tem_list) {
            this.tem_list = tem_list;
        }

        public static class HumListBean {
            /**
             * device_hum : 20
             * time : 11:04
             */

            private String device_hum;
            private String time;

            public String getDevice_hum() {
                return device_hum;
            }

            public void setDevice_hum(String device_hum) {
                this.device_hum = device_hum;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class TemListBean {
            /**
             * device_tem : 20
             * time : 11:04
             */

            private String device_tem;
            private String time;

            public String getDevice_tem() {
                return device_tem;
            }

            public void setDevice_tem(String device_tem) {
                this.device_tem = device_tem;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
