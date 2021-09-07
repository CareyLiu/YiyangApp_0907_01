package com.yiyang.cn.fragment.znjj.model;

import java.util.List;

public class ZhiNengModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"member_type":"1","area_name":"东城区","family_id":"249","device_num":"2","ty_room_id":"0","room":[{"room_id":"59","room_name":"客厅","family_id":"249","device_number":"0"},{"room_id":"0","room_name":"默认房间","family_id":"249","device_number":"2"}],"province_name":"北京","scene":[{"of_user_id":"2374","family_id":"249","week_7":"","week_6":"","create_time":"2021-02-22","week_5":"","week_4":"","week_time_type":"1","week_3":"","scene_id":"156","week_2":"","week_1":"","week_time_begin":"","scene_type":"1","scene_title":"回家","scene_pic":"http://znjj-cj.oss-cn-hangzhou.aliyuncs.com/changjing_pic_huijia.png","week_time_oper":"","scene_type_name":"默认场景：一键执行","week_time_end":"","con_type":"9","scene_group":"1","scene_oper_count":"0","scene_oper_time":"","scene_state":"2"},{"of_user_id":"2374","family_id":"249","week_7":"","week_6":"","create_time":"2021-02-22","week_5":"","week_4":"","week_time_type":"1","week_3":"","scene_id":"157","week_2":"","week_1":"","week_time_begin":"","scene_type":"1","scene_title":"离家","scene_pic":"http://znjj-cj.oss-cn-hangzhou.aliyuncs.com/changjing_pic_lijia.png","week_time_oper":"","scene_type_name":"默认场景：一键执行","week_time_end":"","con_type":"9","scene_group":"1","scene_oper_count":"0","scene_oper_time":"","scene_state":"2"},{"of_user_id":"2374","family_id":"249","week_7":"","week_6":"","create_time":"2021-02-22","week_5":"1","week_4":"1","week_time_type":"1","week_3":"1","scene_id":"158","week_2":"1","week_1":"1","week_time_begin":"08:00","scene_type":"2","scene_title":"起床","scene_pic":"http://znjj-cj.oss-cn-hangzhou.aliyuncs.com/changjing_pic_qichuang.png","week_time_oper":"2","scene_type_name":"默认场景：定时","week_time_end":"","con_type":"9","scene_group":"2","scene_oper_count":"0","scene_oper_time":"","scene_state":"2"},{"of_user_id":"2374","family_id":"249","week_7":"","week_6":"","create_time":"2021-02-22","week_5":"1","week_4":"1","week_time_type":"1","week_3":"1","scene_id":"159","week_2":"1","week_1":"1","week_time_begin":"20:00","scene_type":"2","scene_title":"睡眠","scene_pic":"http://znjj-cj.oss-cn-hangzhou.aliyuncs.com/changjing_pic_shuimian.png","week_time_oper":"2","scene_type_name":"默认场景：定时","week_time_end":"","con_type":"9","scene_group":"2","scene_oper_count":"0","scene_oper_time":"","scene_state":"2"},{"of_user_id":"2374","family_id":"249","week_7":"","week_6":"","create_time":"2021-03-06","week_5":"","week_4":"","week_time_type":"1","week_3":"","scene_id":"206","week_2":"","week_1":"","week_time_begin":"","scene_type":"1","scene_title":"哦哦","scene_pic":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_4.png","week_time_oper":"","scene_type_name":"自定义场景：一键执行","week_time_end":"","con_type":"9","scene_group":"1","scene_oper_count":"0","scene_oper_time":"","scene_state":"3"}],"ty_family_id":"30574633","city_name":"北京市","user_id":"2374","temperature":"23","humidity":"38","family_name":"测试之家","device":[{"device_id":"738","device_ccid":"aaaaaaaaaaaaa00010140118","device_tem":"10","device_category_code":"","device_type":"00","ty_room_id":"","wg_device_ccid":"","server_id":"8/","device_category":"01","ty_device_ccid":"","ty_family_id":"","room_name":"默认房间","device_name":"神灯管家","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","online_state":"1","work_state":"3","device_ccid_up":"0"},{"device_id":"740","device_ccid":"02010101","device_tem":"10","device_category_code":"","device_type":"02","ty_room_id":"","wg_device_ccid":"","server_id":"8/","device_category":"01","ty_device_ccid":"","ty_family_id":"","room_name":"默认房间","device_name":"插座","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","online_state":"1","work_state":"2","warn_state":"3","device_ccid_up":"aaaaaaaaaaaaa00010140118"}]}]
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
         * member_type : 1
         * area_name : 东城区
         * family_id : 249
         * device_num : 2
         * ty_room_id : 0
         * room : [{"room_id":"59","room_name":"客厅","family_id":"249","device_number":"0"},{"room_id":"0","room_name":"默认房间","family_id":"249","device_number":"2"}]
         * province_name : 北京
         * scene : [{"of_user_id":"2374","family_id":"249","week_7":"","week_6":"","create_time":"2021-02-22","week_5":"","week_4":"","week_time_type":"1","week_3":"","scene_id":"156","week_2":"","week_1":"","week_time_begin":"","scene_type":"1","scene_title":"回家","scene_pic":"http://znjj-cj.oss-cn-hangzhou.aliyuncs.com/changjing_pic_huijia.png","week_time_oper":"","scene_type_name":"默认场景：一键执行","week_time_end":"","con_type":"9","scene_group":"1","scene_oper_count":"0","scene_oper_time":"","scene_state":"2"},{"of_user_id":"2374","family_id":"249","week_7":"","week_6":"","create_time":"2021-02-22","week_5":"","week_4":"","week_time_type":"1","week_3":"","scene_id":"157","week_2":"","week_1":"","week_time_begin":"","scene_type":"1","scene_title":"离家","scene_pic":"http://znjj-cj.oss-cn-hangzhou.aliyuncs.com/changjing_pic_lijia.png","week_time_oper":"","scene_type_name":"默认场景：一键执行","week_time_end":"","con_type":"9","scene_group":"1","scene_oper_count":"0","scene_oper_time":"","scene_state":"2"},{"of_user_id":"2374","family_id":"249","week_7":"","week_6":"","create_time":"2021-02-22","week_5":"1","week_4":"1","week_time_type":"1","week_3":"1","scene_id":"158","week_2":"1","week_1":"1","week_time_begin":"08:00","scene_type":"2","scene_title":"起床","scene_pic":"http://znjj-cj.oss-cn-hangzhou.aliyuncs.com/changjing_pic_qichuang.png","week_time_oper":"2","scene_type_name":"默认场景：定时","week_time_end":"","con_type":"9","scene_group":"2","scene_oper_count":"0","scene_oper_time":"","scene_state":"2"},{"of_user_id":"2374","family_id":"249","week_7":"","week_6":"","create_time":"2021-02-22","week_5":"1","week_4":"1","week_time_type":"1","week_3":"1","scene_id":"159","week_2":"1","week_1":"1","week_time_begin":"20:00","scene_type":"2","scene_title":"睡眠","scene_pic":"http://znjj-cj.oss-cn-hangzhou.aliyuncs.com/changjing_pic_shuimian.png","week_time_oper":"2","scene_type_name":"默认场景：定时","week_time_end":"","con_type":"9","scene_group":"2","scene_oper_count":"0","scene_oper_time":"","scene_state":"2"},{"of_user_id":"2374","family_id":"249","week_7":"","week_6":"","create_time":"2021-03-06","week_5":"","week_4":"","week_time_type":"1","week_3":"","scene_id":"206","week_2":"","week_1":"","week_time_begin":"","scene_type":"1","scene_title":"哦哦","scene_pic":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_4.png","week_time_oper":"","scene_type_name":"自定义场景：一键执行","week_time_end":"","con_type":"9","scene_group":"1","scene_oper_count":"0","scene_oper_time":"","scene_state":"3"}]
         * ty_family_id : 30574633
         * city_name : 北京市
         * user_id : 2374
         * temperature : 23
         * humidity : 38
         * family_name : 测试之家
         * device : [{"device_id":"738","device_ccid":"aaaaaaaaaaaaa00010140118","device_tem":"10","device_category_code":"","device_type":"00","ty_room_id":"","wg_device_ccid":"","server_id":"8/","device_category":"01","ty_device_ccid":"","ty_family_id":"","room_name":"默认房间","device_name":"神灯管家","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","online_state":"1","work_state":"3","device_ccid_up":"0"},{"device_id":"740","device_ccid":"02010101","device_tem":"10","device_category_code":"","device_type":"02","ty_room_id":"","wg_device_ccid":"","server_id":"8/","device_category":"01","ty_device_ccid":"","ty_family_id":"","room_name":"默认房间","device_name":"插座","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","online_state":"1","work_state":"2","warn_state":"3","device_ccid_up":"aaaaaaaaaaaaa00010140118"}]
         */

        private String member_type;
        private String area_name;
        private String family_id;
        private String device_num;
        private String ty_room_id;
        private String province_name;
        private String ty_family_id;
        private String city_name;
        private String user_id;
        private String temperature;
        private String humidity;
        private String family_name;
        private List<RoomBean> room;
        private List<SceneBean> scene;
        private List<DeviceBean> device;

        public String getMember_type() {
            return member_type;
        }

        public void setMember_type(String member_type) {
            this.member_type = member_type;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getFamily_id() {
            return family_id;
        }

        public void setFamily_id(String family_id) {
            this.family_id = family_id;
        }

        public String getDevice_num() {
            return device_num;
        }

        public void setDevice_num(String device_num) {
            this.device_num = device_num;
        }

        public String getTy_room_id() {
            return ty_room_id;
        }

        public void setTy_room_id(String ty_room_id) {
            this.ty_room_id = ty_room_id;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getTy_family_id() {
            return ty_family_id;
        }

        public void setTy_family_id(String ty_family_id) {
            this.ty_family_id = ty_family_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getFamily_name() {
            return family_name;
        }

        public void setFamily_name(String family_name) {
            this.family_name = family_name;
        }

        public List<RoomBean> getRoom() {
            return room;
        }

        public void setRoom(List<RoomBean> room) {
            this.room = room;
        }

        public List<SceneBean> getScene() {
            return scene;
        }

        public void setScene(List<SceneBean> scene) {
            this.scene = scene;
        }

        public List<DeviceBean> getDevice() {
            return device;
        }

        public void setDevice(List<DeviceBean> device) {
            this.device = device;
        }

        public static class RoomBean {
            /**
             * room_id : 59
             * room_name : 客厅
             * family_id : 249
             * device_number : 0
             */

            private String room_id;
            private String room_name;
            private String family_id;
            private String device_number;

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public String getRoom_name() {
                return room_name;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public String getFamily_id() {
                return family_id;
            }

            public void setFamily_id(String family_id) {
                this.family_id = family_id;
            }

            public String getDevice_number() {
                return device_number;
            }

            public void setDevice_number(String device_number) {
                this.device_number = device_number;
            }
        }

        public static class SceneBean {
            /**
             * of_user_id : 2374
             * family_id : 249
             * week_7 :
             * week_6 :
             * create_time : 2021-02-22
             * week_5 :
             * week_4 :
             * week_time_type : 1
             * week_3 :
             * scene_id : 156
             * week_2 :
             * week_1 :
             * week_time_begin :
             * scene_type : 1
             * scene_title : 回家
             * scene_pic : http://znjj-cj.oss-cn-hangzhou.aliyuncs.com/changjing_pic_huijia.png
             * week_time_oper :
             * scene_type_name : 默认场景：一键执行
             * week_time_end :
             * con_type : 9
             * scene_group : 1
             * scene_oper_count : 0
             * scene_oper_time :
             * scene_state : 2
             */

            private String of_user_id;
            private String family_id;
            private String week_7;
            private String week_6;
            private String create_time;
            private String week_5;
            private String week_4;
            private String week_time_type;
            private String week_3;
            private String scene_id;
            private String week_2;
            private String week_1;
            private String week_time_begin;
            private String scene_type;
            private String scene_title;
            private String scene_pic;
            private String week_time_oper;
            private String scene_type_name;
            private String week_time_end;
            private String con_type;
            private String scene_group;
            private String scene_oper_count;
            private String scene_oper_time;
            private String scene_state;

            public String getOf_user_id() {
                return of_user_id;
            }

            public void setOf_user_id(String of_user_id) {
                this.of_user_id = of_user_id;
            }

            public String getFamily_id() {
                return family_id;
            }

            public void setFamily_id(String family_id) {
                this.family_id = family_id;
            }

            public String getWeek_7() {
                return week_7;
            }

            public void setWeek_7(String week_7) {
                this.week_7 = week_7;
            }

            public String getWeek_6() {
                return week_6;
            }

            public void setWeek_6(String week_6) {
                this.week_6 = week_6;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getWeek_5() {
                return week_5;
            }

            public void setWeek_5(String week_5) {
                this.week_5 = week_5;
            }

            public String getWeek_4() {
                return week_4;
            }

            public void setWeek_4(String week_4) {
                this.week_4 = week_4;
            }

            public String getWeek_time_type() {
                return week_time_type;
            }

            public void setWeek_time_type(String week_time_type) {
                this.week_time_type = week_time_type;
            }

            public String getWeek_3() {
                return week_3;
            }

            public void setWeek_3(String week_3) {
                this.week_3 = week_3;
            }

            public String getScene_id() {
                return scene_id;
            }

            public void setScene_id(String scene_id) {
                this.scene_id = scene_id;
            }

            public String getWeek_2() {
                return week_2;
            }

            public void setWeek_2(String week_2) {
                this.week_2 = week_2;
            }

            public String getWeek_1() {
                return week_1;
            }

            public void setWeek_1(String week_1) {
                this.week_1 = week_1;
            }

            public String getWeek_time_begin() {
                return week_time_begin;
            }

            public void setWeek_time_begin(String week_time_begin) {
                this.week_time_begin = week_time_begin;
            }

            public String getScene_type() {
                return scene_type;
            }

            public void setScene_type(String scene_type) {
                this.scene_type = scene_type;
            }

            public String getScene_title() {
                return scene_title;
            }

            public void setScene_title(String scene_title) {
                this.scene_title = scene_title;
            }

            public String getScene_pic() {
                return scene_pic;
            }

            public void setScene_pic(String scene_pic) {
                this.scene_pic = scene_pic;
            }

            public String getWeek_time_oper() {
                return week_time_oper;
            }

            public void setWeek_time_oper(String week_time_oper) {
                this.week_time_oper = week_time_oper;
            }

            public String getScene_type_name() {
                return scene_type_name;
            }

            public void setScene_type_name(String scene_type_name) {
                this.scene_type_name = scene_type_name;
            }

            public String getWeek_time_end() {
                return week_time_end;
            }

            public void setWeek_time_end(String week_time_end) {
                this.week_time_end = week_time_end;
            }

            public String getCon_type() {
                return con_type;
            }

            public void setCon_type(String con_type) {
                this.con_type = con_type;
            }

            public String getScene_group() {
                return scene_group;
            }

            public void setScene_group(String scene_group) {
                this.scene_group = scene_group;
            }

            public String getScene_oper_count() {
                return scene_oper_count;
            }

            public void setScene_oper_count(String scene_oper_count) {
                this.scene_oper_count = scene_oper_count;
            }

            public String getScene_oper_time() {
                return scene_oper_time;
            }

            public void setScene_oper_time(String scene_oper_time) {
                this.scene_oper_time = scene_oper_time;
            }

            public String getScene_state() {
                return scene_state;
            }

            public void setScene_state(String scene_state) {
                this.scene_state = scene_state;
            }
        }

        public static class DeviceBean {
            /**
             * device_id : 738
             * device_ccid : aaaaaaaaaaaaa00010140118
             * device_tem : 10
             * device_category_code :
             * device_type : 00
             * ty_room_id :
             * wg_device_ccid :
             * server_id : 8/
             * device_category : 01
             * ty_device_ccid :
             * ty_family_id :
             * room_name : 默认房间
             * device_name : 神灯管家
             * device_type_pic : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920
             * online_state : 1
             * work_state : 3
             * device_ccid_up : 0
             * warn_state : 3
             */

            private String device_id;
            private String device_ccid;
            private String device_tem;
            private String device_category_code;
            private String device_type;
            private String ty_room_id;
            private String wg_device_ccid;
            private String server_id;
            private String device_category;
            private String ty_device_ccid;
            private String ty_family_id;
            private String room_name;
            private String device_name;
            private String device_type_pic;
            private String online_state;
            private String work_state;
            private String device_ccid_up;
            private String warn_state;

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

            public String getDevice_tem() {
                return device_tem;
            }

            public void setDevice_tem(String device_tem) {
                this.device_tem = device_tem;
            }

            public String getDevice_category_code() {
                return device_category_code;
            }

            public void setDevice_category_code(String device_category_code) {
                this.device_category_code = device_category_code;
            }

            public String getDevice_type() {
                return device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;
            }

            public String getTy_room_id() {
                return ty_room_id;
            }

            public void setTy_room_id(String ty_room_id) {
                this.ty_room_id = ty_room_id;
            }

            public String getWg_device_ccid() {
                return wg_device_ccid;
            }

            public void setWg_device_ccid(String wg_device_ccid) {
                this.wg_device_ccid = wg_device_ccid;
            }

            public String getServer_id() {
                return server_id;
            }

            public void setServer_id(String server_id) {
                this.server_id = server_id;
            }

            public String getDevice_category() {
                return device_category;
            }

            public void setDevice_category(String device_category) {
                this.device_category = device_category;
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

            public String getDevice_ccid_up() {
                return device_ccid_up;
            }

            public void setDevice_ccid_up(String device_ccid_up) {
                this.device_ccid_up = device_ccid_up;
            }

            public String getWarn_state() {
                return warn_state;
            }

            public void setWarn_state(String warn_state) {
                this.warn_state = warn_state;
            }
        }
    }
}
