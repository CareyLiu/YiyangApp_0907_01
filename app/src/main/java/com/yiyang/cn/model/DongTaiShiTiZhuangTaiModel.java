package com.yiyang.cn.model;

import java.util.List;

public class DongTaiShiTiZhuangTaiModel {

    /**
     * change_state : 1
     * msg_code : 0000
     * msg : ok
     * data : [{"device_list":[{"name":"神灯管家"},{"name":"智能窗帘"},{"name":"智能插座"},{"name":"智能门磁"},{"name":"开关"},{"name":"开关2"},{"name":"开关3"}],"room_list":[{"name":"客厅"},{"name":"主卧"},{"name":"次卧"},{"name":"书房"},{"name":"厨房"},{"name":"卫生间"},{"name":"卧室"}]}]
     */

    private String change_state;
    private String msg_code;
    private String msg;
    private List<DataBean> data;

    public String getChange_state() {
        return change_state;
    }

    public void setChange_state(String change_state) {
        this.change_state = change_state;
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
        private List<DeviceListBean> device_list;
        private List<RoomListBean> room_list;

        public List<DeviceListBean> getDevice_list() {
            return device_list;
        }

        public void setDevice_list(List<DeviceListBean> device_list) {
            this.device_list = device_list;
        }

        public List<RoomListBean> getRoom_list() {
            return room_list;
        }

        public void setRoom_list(List<RoomListBean> room_list) {
            this.room_list = room_list;
        }

        public static class DeviceListBean {
            /**
             * name : 神灯管家
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class RoomListBean {
            /**
             * name : 客厅
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
