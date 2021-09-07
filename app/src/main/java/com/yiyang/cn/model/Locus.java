package com.yiyang.cn.model;

import java.util.List;

/**
 * Created by Sl on 2019/7/5.
 *
 */

public class Locus {

    /**
     * next : 1
     * msg_code : 0000
     * msg : ok
     * row_num : 12
     * data : [{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 10:37:01","gps_y":"126.605662","car_gps_id":"15","baidu_y":"126.617947","plate_number":"黑A6L168G","baidu_x":"45.6742758","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:04:01","gps_y":"126.605662","car_gps_id":"23","baidu_y":"126.617942","plate_number":"黑A6L168G","baidu_x":"45.6742750","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:12:08","gps_y":"126.605662","car_gps_id":"29","baidu_y":"126.617943","plate_number":"黑A6L168G","baidu_x":"45.6742737","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:16:32","gps_y":"126.605662","car_gps_id":"34","baidu_y":"126.617940","plate_number":"黑A6L168G","baidu_x":"45.6742759","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:20:32","gps_y":"126.605662","car_gps_id":"42","baidu_y":"126.617944","plate_number":"黑A6L168G","baidu_x":"45.6742791","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:27:01","gps_y":"126.605662","car_gps_id":"48","baidu_y":"126.617950","plate_number":"黑A6L168G","baidu_x":"45.6742768","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:32:33","gps_y":"126.605662","car_gps_id":"54","baidu_y":"126.617941","plate_number":"黑A6L168G","baidu_x":"45.6742775","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:38:01","gps_y":"126.605662","car_gps_id":"60","baidu_y":"126.617947","plate_number":"黑A6L168G","baidu_x":"45.6742747","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:43:01","gps_y":"126.605662","car_gps_id":"65","baidu_y":"126.617947","plate_number":"黑A6L168G","baidu_x":"45.6742774","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:45:37","gps_y":"126.605662","car_gps_id":"71","baidu_y":"126.617940","plate_number":"黑A6L168G","baidu_x":"45.6742799","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:47:33","gps_y":"126.605662","car_gps_id":"75","baidu_y":"126.617953","plate_number":"黑A6L168G","baidu_x":"45.6742771","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"},{"gaode_y":"126.611563","gps_x":"45.666027","create_time":"2019-03-06 11:52:31","gps_y":"126.605662","car_gps_id":"81","baidu_y":"126.617952","plate_number":"黑A6L168G","baidu_x":"45.6742773","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街168号"}]
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
         * gaode_y : 126.611563
         * gps_x : 45.666027
         * create_time : 2019-03-06 10:37:01
         * gps_y : 126.605662
         * car_gps_id : 15
         * baidu_y : 126.617947
         * plate_number : 黑A6L168G
         * baidu_x : 45.6742758
         * gaode_x : 45.6679638
         * gps_addr : 哈尔滨市南岗区电缆街168号
         */

        private String gaode_y;
        private String gps_x;
        private String create_time;
        private String gps_y;
        private String car_gps_id;
        private String baidu_y;
        private String plate_number;
        private String baidu_x;
        private String gaode_x;
        private String gps_addr;

        public String getGaode_y() {
            return gaode_y;
        }

        public void setGaode_y(String gaode_y) {
            this.gaode_y = gaode_y;
        }

        public String getGps_x() {
            return gps_x;
        }

        public void setGps_x(String gps_x) {
            this.gps_x = gps_x;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getGps_y() {
            return gps_y;
        }

        public void setGps_y(String gps_y) {
            this.gps_y = gps_y;
        }

        public String getCar_gps_id() {
            return car_gps_id;
        }

        public void setCar_gps_id(String car_gps_id) {
            this.car_gps_id = car_gps_id;
        }

        public String getBaidu_y() {
            return baidu_y;
        }

        public void setBaidu_y(String baidu_y) {
            this.baidu_y = baidu_y;
        }

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }

        public String getBaidu_x() {
            return baidu_x;
        }

        public void setBaidu_x(String baidu_x) {
            this.baidu_x = baidu_x;
        }

        public String getGaode_x() {
            return gaode_x;
        }

        public void setGaode_x(String gaode_x) {
            this.gaode_x = gaode_x;
        }

        public String getGps_addr() {
            return gps_addr;
        }

        public void setGps_addr(String gps_addr) {
            this.gps_addr = gps_addr;
        }
    }
}
