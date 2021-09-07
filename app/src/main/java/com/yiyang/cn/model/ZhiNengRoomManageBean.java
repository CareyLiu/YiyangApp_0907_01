package com.yiyang.cn.model;

import java.util.List;

public class ZhiNengRoomManageBean {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"room_id":"11","room_name":"主卧","family_id":"17","device_number":"2"},{"room_id":"13","room_name":"餐厅","family_id":"17","device_number":"0"},{"room_id":"0","room_name":"默认房间","family_id":"17","device_number":"13"}]
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
         * room_id : 11
         * room_name : 主卧
         * family_id : 17
         * device_number : 2
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
}
