package com.yiyang.cn.model;

import java.util.List;

public class SuiYiTieModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"binding_type":"2","device_name":"随意贴开关（两键）1","device_list":[{"device_type":"08","device_category":"01","wifi_pwd":"","device_id_up":"100","is_alarm":"1","work_state":"2","device_ccid_up":"aaaaaaaaaaaaaaaa90140018","binding_type":"2","casual_switch_sub_num":"0","family_id":"19","device_id":"247","create_time":"2021-01-03","device_category_code":"","ty_room_id":"","server_id":"1/","device_eletric":"","ty_device_ccid_up":"","wifi_user":"","access_token":"","room_name":"默认房间","refresh_token":"","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","online_state":"2","xf_device_id":"","backups":"","room_id":"0","time_set":"","device_ccid":"08010108","modding_set":"","created_at":"","stamp":"1609664940001","is_voice":"","ty_device_ccid":"","ty_family_id":"","device_category_name":"随意贴开关（一键）","device_name":"开关9","update_time":"2021-01-03","casual_switch_name":"随意贴开关（两键）1","binding_device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","expires_in":"","device_type_name":"开关","casual_switch_num":"01","casual_switch_ccid":"08010101","binding_device_name":"插座/插排","time_set_begin":"","binding_device_ccid":"02010101","device_state":"1","warn_state":"3","family_name":"心爱的小窝"}],"device_type_name":"开关","device_ccid":"08010101","family_name":"心爱的小窝","device_category":"01"}]
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
         * binding_type : 2
         * device_name : 随意贴开关（两键）1
         * device_list : [{"device_type":"08","device_category":"01","wifi_pwd":"","device_id_up":"100","is_alarm":"1","work_state":"2","device_ccid_up":"aaaaaaaaaaaaaaaa90140018","binding_type":"2","casual_switch_sub_num":"0","family_id":"19","device_id":"247","create_time":"2021-01-03","device_category_code":"","ty_room_id":"","server_id":"1/","device_eletric":"","ty_device_ccid_up":"","wifi_user":"","access_token":"","room_name":"默认房间","refresh_token":"","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","online_state":"2","xf_device_id":"","backups":"","room_id":"0","time_set":"","device_ccid":"08010108","modding_set":"","created_at":"","stamp":"1609664940001","is_voice":"","ty_device_ccid":"","ty_family_id":"","device_category_name":"随意贴开关（一键）","device_name":"开关9","update_time":"2021-01-03","casual_switch_name":"随意贴开关（两键）1","binding_device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","expires_in":"","device_type_name":"开关","casual_switch_num":"01","casual_switch_ccid":"08010101","binding_device_name":"插座/插排","time_set_begin":"","binding_device_ccid":"02010101","device_state":"1","warn_state":"3","family_name":"心爱的小窝"}]
         * device_type_name : 开关
         * device_ccid : 08010101
         * family_name : 心爱的小窝
         * device_category : 01
         */

        private String binding_type;
        private String device_name;
        private String device_type_name;
        private String device_ccid;
        private String family_name;
        private String device_category;
        private List<DeviceListBean> device_list;

        public String getBinding_type() {
            return binding_type;
        }

        public void setBinding_type(String binding_type) {
            this.binding_type = binding_type;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_type_name() {
            return device_type_name;
        }

        public void setDevice_type_name(String device_type_name) {
            this.device_type_name = device_type_name;
        }

        public String getDevice_ccid() {
            return device_ccid;
        }

        public void setDevice_ccid(String device_ccid) {
            this.device_ccid = device_ccid;
        }

        public String getFamily_name() {
            return family_name;
        }

        public void setFamily_name(String family_name) {
            this.family_name = family_name;
        }

        public String getDevice_category() {
            return device_category;
        }

        public void setDevice_category(String device_category) {
            this.device_category = device_category;
        }

        public List<DeviceListBean> getDevice_list() {
            return device_list;
        }

        public void setDevice_list(List<DeviceListBean> device_list) {
            this.device_list = device_list;
        }

        public static class DeviceListBean {
            /**
             * device_type : 08
             * device_category : 01
             * wifi_pwd :
             * device_id_up : 100
             * is_alarm : 1
             * work_state : 2
             * device_ccid_up : aaaaaaaaaaaaaaaa90140018
             * binding_type : 2
             * casual_switch_sub_num : 0
             * family_id : 19
             * device_id : 247
             * create_time : 2021-01-03
             * device_category_code :
             * ty_room_id :
             * server_id : 1/
             * device_eletric :
             * ty_device_ccid_up :
             * wifi_user :
             * access_token :
             * room_name : 默认房间
             * refresh_token :
             * device_type_pic : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920
             * online_state : 2
             * xf_device_id :
             * backups :
             * room_id : 0
             * time_set :
             * device_ccid : 08010108
             * modding_set :
             * created_at :
             * stamp : 1609664940001
             * is_voice :
             * ty_device_ccid :
             * ty_family_id :
             * device_category_name : 随意贴开关（一键）
             * device_name : 开关9
             * update_time : 2021-01-03
             * casual_switch_name : 随意贴开关（两键）1
             * binding_device_type_pic : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920
             * expires_in :
             * device_type_name : 开关
             * casual_switch_num : 01
             * casual_switch_ccid : 08010101
             * binding_device_name : 插座/插排
             * time_set_begin :
             * binding_device_ccid : 02010101
             * device_state : 1
             * warn_state : 3
             * family_name : 心爱的小窝
             */

            private String device_type;
            private String device_category;
            private String wifi_pwd;
            private String device_id_up;
            private String is_alarm;
            private String work_state;
            private String device_ccid_up;
            private String binding_type;
            private String casual_switch_sub_num;
            private String family_id;
            private String device_id;
            private String create_time;
            private String device_category_code;
            private String ty_room_id;
            private String server_id;
            private String device_eletric;
            private String ty_device_ccid_up;
            private String wifi_user;
            private String access_token;
            private String room_name;
            private String refresh_token;
            private String device_type_pic;
            private String online_state;
            private String xf_device_id;
            private String backups;
            private String room_id;
            private String time_set;
            private String device_ccid;
            private String modding_set;
            private String created_at;
            private String stamp;
            private String is_voice;
            private String ty_device_ccid;
            private String ty_family_id;
            private String device_category_name;
            private String device_name;
            private String update_time;
            private String casual_switch_name;
            private String binding_device_type_pic;
            private String expires_in;
            private String device_type_name;
            private String casual_switch_num;
            private String casual_switch_ccid;
            private String binding_device_name;
            private String time_set_begin;
            private String binding_device_ccid;
            private String device_state;
            private String warn_state;
            private String family_name;

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

            public String getWifi_pwd() {
                return wifi_pwd;
            }

            public void setWifi_pwd(String wifi_pwd) {
                this.wifi_pwd = wifi_pwd;
            }

            public String getDevice_id_up() {
                return device_id_up;
            }

            public void setDevice_id_up(String device_id_up) {
                this.device_id_up = device_id_up;
            }

            public String getIs_alarm() {
                return is_alarm;
            }

            public void setIs_alarm(String is_alarm) {
                this.is_alarm = is_alarm;
            }

            public String getWork_state() {
                return work_state;
            }

            public void setWork_state(String work_state) {
                this.work_state = work_state;
            }

            public String getDevice_ccid_up() {
                return device_ccid_up;
            }

            public void setDevice_ccid_up(String device_ccid_up) {
                this.device_ccid_up = device_ccid_up;
            }

            public String getBinding_type() {
                return binding_type;
            }

            public void setBinding_type(String binding_type) {
                this.binding_type = binding_type;
            }

            public String getCasual_switch_sub_num() {
                return casual_switch_sub_num;
            }

            public void setCasual_switch_sub_num(String casual_switch_sub_num) {
                this.casual_switch_sub_num = casual_switch_sub_num;
            }

            public String getFamily_id() {
                return family_id;
            }

            public void setFamily_id(String family_id) {
                this.family_id = family_id;
            }

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getDevice_category_code() {
                return device_category_code;
            }

            public void setDevice_category_code(String device_category_code) {
                this.device_category_code = device_category_code;
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

            public String getDevice_eletric() {
                return device_eletric;
            }

            public void setDevice_eletric(String device_eletric) {
                this.device_eletric = device_eletric;
            }

            public String getTy_device_ccid_up() {
                return ty_device_ccid_up;
            }

            public void setTy_device_ccid_up(String ty_device_ccid_up) {
                this.ty_device_ccid_up = ty_device_ccid_up;
            }

            public String getWifi_user() {
                return wifi_user;
            }

            public void setWifi_user(String wifi_user) {
                this.wifi_user = wifi_user;
            }

            public String getAccess_token() {
                return access_token;
            }

            public void setAccess_token(String access_token) {
                this.access_token = access_token;
            }

            public String getRoom_name() {
                return room_name;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public String getRefresh_token() {
                return refresh_token;
            }

            public void setRefresh_token(String refresh_token) {
                this.refresh_token = refresh_token;
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

            public String getXf_device_id() {
                return xf_device_id;
            }

            public void setXf_device_id(String xf_device_id) {
                this.xf_device_id = xf_device_id;
            }

            public String getBackups() {
                return backups;
            }

            public void setBackups(String backups) {
                this.backups = backups;
            }

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public String getTime_set() {
                return time_set;
            }

            public void setTime_set(String time_set) {
                this.time_set = time_set;
            }

            public String getDevice_ccid() {
                return device_ccid;
            }

            public void setDevice_ccid(String device_ccid) {
                this.device_ccid = device_ccid;
            }

            public String getModding_set() {
                return modding_set;
            }

            public void setModding_set(String modding_set) {
                this.modding_set = modding_set;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getStamp() {
                return stamp;
            }

            public void setStamp(String stamp) {
                this.stamp = stamp;
            }

            public String getIs_voice() {
                return is_voice;
            }

            public void setIs_voice(String is_voice) {
                this.is_voice = is_voice;
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

            public String getDevice_category_name() {
                return device_category_name;
            }

            public void setDevice_category_name(String device_category_name) {
                this.device_category_name = device_category_name;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getCasual_switch_name() {
                return casual_switch_name;
            }

            public void setCasual_switch_name(String casual_switch_name) {
                this.casual_switch_name = casual_switch_name;
            }

            public String getBinding_device_type_pic() {
                return binding_device_type_pic;
            }

            public void setBinding_device_type_pic(String binding_device_type_pic) {
                this.binding_device_type_pic = binding_device_type_pic;
            }

            public String getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(String expires_in) {
                this.expires_in = expires_in;
            }

            public String getDevice_type_name() {
                return device_type_name;
            }

            public void setDevice_type_name(String device_type_name) {
                this.device_type_name = device_type_name;
            }

            public String getCasual_switch_num() {
                return casual_switch_num;
            }

            public void setCasual_switch_num(String casual_switch_num) {
                this.casual_switch_num = casual_switch_num;
            }

            public String getCasual_switch_ccid() {
                return casual_switch_ccid;
            }

            public void setCasual_switch_ccid(String casual_switch_ccid) {
                this.casual_switch_ccid = casual_switch_ccid;
            }

            public String getBinding_device_name() {
                return binding_device_name;
            }

            public void setBinding_device_name(String binding_device_name) {
                this.binding_device_name = binding_device_name;
            }

            public String getTime_set_begin() {
                return time_set_begin;
            }

            public void setTime_set_begin(String time_set_begin) {
                this.time_set_begin = time_set_begin;
            }

            public String getBinding_device_ccid() {
                return binding_device_ccid;
            }

            public void setBinding_device_ccid(String binding_device_ccid) {
                this.binding_device_ccid = binding_device_ccid;
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

            public String getFamily_name() {
                return family_name;
            }

            public void setFamily_name(String family_name) {
                this.family_name = family_name;
            }
        }
    }
}
