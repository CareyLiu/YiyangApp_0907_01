package com.yiyang.cn.model;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

public class MenCiListModel {
    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"room_id":"0","device_ccid":"12010101","device_type":"15","device_category":"01","fj_interval":"","is_alarm":"1","device_name":"门磁检测","work_state":"3","auto_state":"3","device_ccid_up":"aaaaaaaaaaaaaaaa90140018","family_id":"19","noti_info":"","device_type_name":"门磁监测","device_id":"115","device_electric":"","kids_mode":"3","fj_time":"","warn_time":"","server_id":"1/","room_name":"默认房间","timing_state":"0","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11943","online_state":"1","device_state":"2","warn_state":"1","alarm_list":[{"alarm_date":"今天","alerm_time_list":[{"alerm_time":"上午 09:32:00","device_state_name":"发生报警","device_state":"2"},{"alerm_time":"上午 09:31:42","device_state_name":"发生报警","device_state":"1"}]},{"alarm_date":"2020-12-08","alerm_time_list":[{"alerm_time":"下午 17:33:41","device_state_name":"发生报警","device_state":"1"}]}],"fj_times":"","family_name":"心爱的小窝"}]
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
         * room_id : 0
         * device_ccid : 12010101
         * device_type : 15
         * device_category : 01
         * fj_interval :
         * is_alarm : 1
         * device_name : 门磁检测
         * work_state : 3
         * auto_state : 3
         * device_ccid_up : aaaaaaaaaaaaaaaa90140018
         * family_id : 19
         * noti_info :
         * device_type_name : 门磁监测
         * device_id : 115
         * device_electric :
         * kids_mode : 3
         * fj_time :
         * warn_time :
         * server_id : 1/
         * room_name : 默认房间
         * timing_state : 0
         * device_type_pic : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11943
         * online_state : 1
         * device_state : 2
         * warn_state : 1
         * alarm_list : [{"alarm_date":"今天","alerm_time_list":[{"alerm_time":"上午 09:32:00","device_state_name":"发生报警","device_state":"2"},{"alerm_time":"上午 09:31:42","device_state_name":"发生报警","device_state":"1"}]},{"alarm_date":"2020-12-08","alerm_time_list":[{"alerm_time":"下午 17:33:41","device_state_name":"发生报警","device_state":"1"}]}]
         * fj_times :
         * family_name : 心爱的小窝
         */

        private String room_id;
        private String device_ccid;
        private String device_type;
        private String device_category;
        private String fj_interval;
        private String is_alarm;
        private String device_name;
        private String work_state;
        private String auto_state;
        private String device_ccid_up;
        private String family_id;
        private String noti_info;
        private String device_type_name;
        private String device_id;
        private String device_electric;
        private String kids_mode;
        private String fj_time;
        private String warn_time;
        private String server_id;
        private String room_name;
        private String timing_state;
        private String device_type_pic;
        private String online_state;
        private String device_state;
        private String warn_state;
        private String fj_times;
        private String family_name;
        private List<AlarmListBean> alarm_list;

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

        public String getDevice_electric() {
            return device_electric;
        }

        public void setDevice_electric(String device_electric) {
            this.device_electric = device_electric;
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

        public String getWarn_time() {
            return warn_time;
        }

        public void setWarn_time(String warn_time) {
            this.warn_time = warn_time;
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

        public String getDevice_state() {
            return device_state;
        }

        public void setDevice_state(String device_state) {
            this.device_state = device_state;
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

        public List<AlarmListBean> getAlarm_list() {
            return alarm_list;
        }

        public void setAlarm_list(List<AlarmListBean> alarm_list) {
            this.alarm_list = alarm_list;
        }

        public static class AlarmListBean {
            /**
             * alarm_date : 今天
             * alerm_time_list : [{"alerm_time":"上午 09:32:00","device_state_name":"发生报警","device_state":"2"},{"alerm_time":"上午 09:31:42","device_state_name":"发生报警","device_state":"1"}]
             */

            private String alarm_date;
            private List<AlermTimeListBean> alerm_time_list;
            public String sel_alarm_date;//2021-04-24
            public String is_more;//是否更多 0没有 1有

            public String getAlarm_date() {
                return alarm_date;
            }

            public void setAlarm_date(String alarm_date) {
                this.alarm_date = alarm_date;
            }

            public List<AlermTimeListBean> getAlerm_time_list() {
                return alerm_time_list;
            }

            public void setAlerm_time_list(List<AlermTimeListBean> alerm_time_list) {
                this.alerm_time_list = alerm_time_list;
            }

            public static class AlermTimeListBean {
                /**
                 * alerm_time : 上午 09:32:00
                 * device_state_name : 发生报警
                 * device_state : 2
                 */

                private String alerm_time;
                private String device_state_name;
                private String device_state;



                public String getAlerm_time() {
                    return alerm_time;
                }

                public void setAlerm_time(String alerm_time) {
                    this.alerm_time = alerm_time;
                }

                public String getDevice_state_name() {
                    return device_state_name;
                }

                public void setDevice_state_name(String device_state_name) {
                    this.device_state_name = device_state_name;
                }

                public String getDevice_state() {
                    return device_state;
                }

                public void setDevice_state(String device_state) {
                    this.device_state = device_state;
                }
            }
        }
    }
}
