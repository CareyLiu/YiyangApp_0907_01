package com.yiyang.cn.model;

import java.util.List;

public class MessageModel {
    /**
     * next : 1
     * msg_code : 0000
     * msg : ok
     * row_num : 12
     * data : [{"notify_type":"1","notify_text":"风暖驻车加热器故障：点火塞开路或短路","notify_state":"2","create_time":"2020-04-06 13:30:24","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1628","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：点火塞开路或短路","notify_state":"2","create_time":"2020-04-06 13:30:12","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1627","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：点火塞开路或短路","notify_state":"2","create_time":"2020-04-06 13:29:48","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1626","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：点火塞开路或短路","notify_state":"2","create_time":"2020-04-06 13:27:33","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1625","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：入风口传感器开路或短路","notify_state":"2","create_time":"2020-04-06 13:23:57","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1624","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：壳体温度传感器开路或短路","notify_state":"2","create_time":"2020-04-06 13:21:48","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1623","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：壳体温度传感器开路或短路","notify_state":"2","create_time":"2020-04-06 13:19:27","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1622","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：油泵开路或短路","notify_state":"2","create_time":"2020-04-06 13:06:45","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1621","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：电压过低或过高","notify_state":"2","create_time":"2020-04-06 13:06:07","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1620","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：入风口传感器高温报警","notify_state":"2","create_time":"2020-04-06 11:44:30","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1619","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：入风口传感器开路或短路","notify_state":"2","create_time":"2020-04-06 11:44:09","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1618","user_car_id":"97"},{"notify_type":"1","notify_text":"风暖驻车加热器故障：壳体温度传感器开路或短路","notify_state":"2","create_time":"2020-04-06 11:43:45","other_img_url":"","car_code":"","oper_id":"","notify_read":"1","notify_id":"1617","user_car_id":"97"}]
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
         * notify_type : 1
         * notify_text : 风暖驻车加热器故障：点火塞开路或短路
         * notify_state : 2
         * create_time : 2020-04-06 13:30:24
         * other_img_url :
         * car_code :
         * oper_id :
         * notify_read : 1
         * notify_id : 1628
         * user_car_id : 97
         */

        private String notify_type;
        private String notify_text;
        private String notify_state;
        private String create_time;
        private String other_img_url;
        private String car_code;
        private String oper_id;
        private String notify_read;
        private String notify_id;
        private String user_car_id;

        public String getNotify_type() {
            return notify_type;
        }

        public void setNotify_type(String notify_type) {
            this.notify_type = notify_type;
        }

        public String getNotify_text() {
            return notify_text;
        }

        public void setNotify_text(String notify_text) {
            this.notify_text = notify_text;
        }

        public String getNotify_state() {
            return notify_state;
        }

        public void setNotify_state(String notify_state) {
            this.notify_state = notify_state;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getOther_img_url() {
            return other_img_url;
        }

        public void setOther_img_url(String other_img_url) {
            this.other_img_url = other_img_url;
        }

        public String getCar_code() {
            return car_code;
        }

        public void setCar_code(String car_code) {
            this.car_code = car_code;
        }

        public String getOper_id() {
            return oper_id;
        }

        public void setOper_id(String oper_id) {
            this.oper_id = oper_id;
        }

        public String getNotify_read() {
            return notify_read;
        }

        public void setNotify_read(String notify_read) {
            this.notify_read = notify_read;
        }

        public String getNotify_id() {
            return notify_id;
        }

        public void setNotify_id(String notify_id) {
            this.notify_id = notify_id;
        }

        public String getUser_car_id() {
            return user_car_id;
        }

        public void setUser_car_id(String user_car_id) {
            this.user_car_id = user_car_id;
        }
    }
}
