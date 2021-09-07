package com.yiyang.cn.model;

import java.util.List;

public class GuZhangDetailsModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"install_time":"2020-03-28","of_user_id":"1526","notify_text":"风暖驻车加热器故障：电压过低或过高","gps_x":"45.666027","gaode_y":"126.611563","car_brand_name":"ARCFOXARCFOX-1","gps_y":"126.605662","create_time":"2020-06-28 13:06:15","failure_name":"电压过低或过高","user_name":"no more","car_brand_url_two":"https://shop.hljsdkj.com/commons/images/siye_autoparts/cartype/160_autohomecar__wKgH0Fgu_aSAHWKpAAJCsWK5fVo406.jpg","changjia_name":"无","baidu_y":"126.617946","baidu_x":"45.6742758","notify_id":"2235","sell_phone":"13946026970","ccid":"aaaaaaaaaaaaaaaaaaaaaaa9","car_code":"","xinghao":"无","plate_number":"","gaode_x":"45.6679638","zhu_car_stoppage_no":"1","install_addr":"哈尔滨南岗区电缆街219号","gps_addr":"哈尔滨市南岗区电缆街170号"}]
     */

    private String msg_code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * install_time : 2020-03-28
         * of_user_id : 1526
         * notify_text : 风暖驻车加热器故障：电压过低或过高
         * gps_x : 45.666027
         * gaode_y : 126.611563
         * car_brand_name : ARCFOXARCFOX-1
         * gps_y : 126.605662
         * create_time : 2020-06-28 13:06:15
         * failure_name : 电压过低或过高
         * user_name : no more
         * car_brand_url_two : https://shop.hljsdkj.com/commons/images/siye_autoparts/cartype/160_autohomecar__wKgH0Fgu_aSAHWKpAAJCsWK5fVo406.jpg
         * changjia_name : 无
         * baidu_y : 126.617946
         * baidu_x : 45.6742758
         * notify_id : 2235
         * sell_phone : 13946026970
         * ccid : aaaaaaaaaaaaaaaaaaaaaaa9
         * car_code :
         * xinghao : 无
         * plate_number :
         * gaode_x : 45.6679638
         * zhu_car_stoppage_no : 1
         * install_addr : 哈尔滨南岗区电缆街219号
         * gps_addr : 哈尔滨市南岗区电缆街170号
         */

        private String install_time;
        private String of_user_id;
        private String notify_text;
        private String gps_x;
        private String gaode_y;
        private String car_brand_name;
        private String gps_y;
        private String create_time;
        private String failure_name;
        private String user_name;
        private String car_brand_url_two;
        private String changjia_name;
        private String baidu_y;
        private String baidu_x;
        private String notify_id;
        private String sell_phone;
        private String ccid;
        private String car_code;
        private String xinghao;
        private String plate_number;
        private String gaode_x;
        private String zhu_car_stoppage_no;
        private String install_addr;
        private String gps_addr;

        public String getInstall_time() {
            return install_time;
        }

        public void setInstall_time(String install_time) {
            this.install_time = install_time;
        }

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getNotify_text() {
            return notify_text;
        }

        public void setNotify_text(String notify_text) {
            this.notify_text = notify_text;
        }

        public String getGps_x() {
            return gps_x;
        }

        public void setGps_x(String gps_x) {
            this.gps_x = gps_x;
        }

        public String getGaode_y() {
            return gaode_y;
        }

        public void setGaode_y(String gaode_y) {
            this.gaode_y = gaode_y;
        }

        public String getCar_brand_name() {
            return car_brand_name;
        }

        public void setCar_brand_name(String car_brand_name) {
            this.car_brand_name = car_brand_name;
        }

        public String getGps_y() {
            return gps_y;
        }

        public void setGps_y(String gps_y) {
            this.gps_y = gps_y;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getFailure_name() {
            return failure_name;
        }

        public void setFailure_name(String failure_name) {
            this.failure_name = failure_name;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getCar_brand_url_two() {
            return car_brand_url_two;
        }

        public void setCar_brand_url_two(String car_brand_url_two) {
            this.car_brand_url_two = car_brand_url_two;
        }

        public String getChangjia_name() {
            return changjia_name;
        }

        public void setChangjia_name(String changjia_name) {
            this.changjia_name = changjia_name;
        }

        public String getBaidu_y() {
            return baidu_y;
        }

        public void setBaidu_y(String baidu_y) {
            this.baidu_y = baidu_y;
        }

        public String getBaidu_x() {
            return baidu_x;
        }

        public void setBaidu_x(String baidu_x) {
            this.baidu_x = baidu_x;
        }

        public String getNotify_id() {
            return notify_id;
        }

        public void setNotify_id(String notify_id) {
            this.notify_id = notify_id;
        }

        public String getSell_phone() {
            return sell_phone;
        }

        public void setSell_phone(String sell_phone) {
            this.sell_phone = sell_phone;
        }

        public String getCcid() {
            return ccid;
        }

        public void setCcid(String ccid) {
            this.ccid = ccid;
        }

        public String getCar_code() {
            return car_code;
        }

        public void setCar_code(String car_code) {
            this.car_code = car_code;
        }

        public String getXinghao() {
            return xinghao;
        }

        public void setXinghao(String xinghao) {
            this.xinghao = xinghao;
        }

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }

        public String getGaode_x() {
            return gaode_x;
        }

        public void setGaode_x(String gaode_x) {
            this.gaode_x = gaode_x;
        }

        public String getZhu_car_stoppage_no() {
            return zhu_car_stoppage_no;
        }

        public void setZhu_car_stoppage_no(String zhu_car_stoppage_no) {
            this.zhu_car_stoppage_no = zhu_car_stoppage_no;
        }

        public String getInstall_addr() {
            return install_addr;
        }

        public void setInstall_addr(String install_addr) {
            this.install_addr = install_addr;
        }

        public String getGps_addr() {
            return gps_addr;
        }

        public void setGps_addr(String gps_addr) {
            this.gps_addr = gps_addr;
        }
    }
}
