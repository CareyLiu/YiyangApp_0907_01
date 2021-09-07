package com.yiyang.cn.activity.wode_page.bazinew.model;

import java.io.Serializable;
import java.util.List;

public class DanganModel  implements Serializable{


    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 2
     * data : [{"lunar_birthday":"一九九九年六月初一 子时","birthday":"1999-06-01","isleap":"0","payment_status":"1","sex":"1","mingpan_user":"","lucky_number":"7","mingpan_id":"596","lucky_goods":"黄金石","birthday_type":"2","birthhour":"00","sex_text":"男","lucky_colour":"水青色","name":"写作业写作业","solar_birthday":"1999年7月13日 子时"},{"lunar_birthday":"二零二零年五月十一 巳时","birthday":"2020-07-01","isleap":"","payment_status":"1","sex":"2","mingpan_user":"","lucky_number":"2","mingpan_id":"595","lucky_goods":"珊瑚","birthday_type":"1","birthhour":"09","sex_text":"女","lucky_colour":"绿色","name":"嗯那","solar_birthday":"2020年7月1日 巳时"}]
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

    public static class DataBean  implements Serializable {
        /**
         * lunar_birthday : 一九九九年六月初一 子时
         * birthday : 1999-06-01
         * isleap : 0
         * payment_status : 1
         * sex : 1
         * mingpan_user :
         * lucky_number : 7
         * mingpan_id : 596
         * lucky_goods : 黄金石
         * birthday_type : 2
         * birthhour : 00
         * sex_text : 男
         * lucky_colour : 水青色
         * name : 写作业写作业
         * solar_birthday : 1999年7月13日 子时
         */

        private String lunar_birthday;
        private String birthday;
        private String isleap;
        private String payment_status;
        private String sex;
        private String mingpan_user;
        private String lucky_number;
        private String mingpan_id;
        private String lucky_goods;
        private String birthday_type;
        private String birthhour;
        private String sex_text;
        private String lucky_colour;
        private String name;
        private String solar_birthday;

        public String getLunar_birthday() {
            return lunar_birthday;
        }

        public void setLunar_birthday(String lunar_birthday) {
            this.lunar_birthday = lunar_birthday;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getIsleap() {
            return isleap;
        }

        public void setIsleap(String isleap) {
            this.isleap = isleap;
        }

        public String getPayment_status() {
            return payment_status;
        }

        public void setPayment_status(String payment_status) {
            this.payment_status = payment_status;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMingpan_user() {
            return mingpan_user;
        }

        public void setMingpan_user(String mingpan_user) {
            this.mingpan_user = mingpan_user;
        }

        public String getLucky_number() {
            return lucky_number;
        }

        public void setLucky_number(String lucky_number) {
            this.lucky_number = lucky_number;
        }

        public String getMingpan_id() {
            return mingpan_id;
        }

        public void setMingpan_id(String mingpan_id) {
            this.mingpan_id = mingpan_id;
        }

        public String getLucky_goods() {
            return lucky_goods;
        }

        public void setLucky_goods(String lucky_goods) {
            this.lucky_goods = lucky_goods;
        }

        public String getBirthday_type() {
            return birthday_type;
        }

        public void setBirthday_type(String birthday_type) {
            this.birthday_type = birthday_type;
        }

        public String getBirthhour() {
            return birthhour;
        }

        public void setBirthhour(String birthhour) {
            this.birthhour = birthhour;
        }

        public String getSex_text() {
            return sex_text;
        }

        public void setSex_text(String sex_text) {
            this.sex_text = sex_text;
        }

        public String getLucky_colour() {
            return lucky_colour;
        }

        public void setLucky_colour(String lucky_colour) {
            this.lucky_colour = lucky_colour;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSolar_birthday() {
            return solar_birthday;
        }

        public void setSolar_birthday(String solar_birthday) {
            this.solar_birthday = solar_birthday;
        }
    }
}
