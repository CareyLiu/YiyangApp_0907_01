package com.yiyang.cn.activity.wode_page.bazinew.model;

import java.util.List;

public class FengshuiModel {

    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 4
     * data : [{"lunar_birthday":"二零二零年五月十六 子时","birthday":"2020-07-06","goods_name":"未绑定","isleap":"","payment_status":"1","sex":"1","mingpan_user":"","mingpan_id":"603","birthday_type":"1","birthhour":"00","sex_text":"男","name":"哦哦哦","goods_state":"2","solar_birthday":"2020年7月6日 子时","goods_img":"http://bz-goods.oss-cn-hangzhou.aliyuncs.com/jiahao.png"},{"lunar_birthday":"一九六八年九月廿八 申时","birthday":"1968-11-18","goods_name":"未绑定","isleap":"","payment_status":"2","sex":"2","mingpan_user":"","mingpan_id":"318","birthday_type":"1","birthhour":"15","sex_text":"女","name":"高玉珍","goods_state":"2","solar_birthday":"1968年11月18日 申时","goods_img":"http://bz-goods.oss-cn-hangzhou.aliyuncs.com/jiahao.png"},{"lunar_birthday":"一九九五年七月十九 午时","birthday":"1995-07-19","goods_name":"未绑定","isleap":"0","payment_status":"2","sex":"2","mingpan_user":"2","mingpan_id":"259","birthday_type":"2","birthhour":"11","sex_text":"女","name":"佀爽","goods_state":"2","solar_birthday":"1995年8月14日 午时","goods_img":"http://bz-goods.oss-cn-hangzhou.aliyuncs.com/jiahao.png"},{"lunar_birthday":"一九九二年九月廿七 申时","birthday":"1992-10-22","goods_name":"天王财神爷开光佛像","isleap":"","payment_status":"2","sex":"1","mingpan_user":"2","mingpan_id":"221","birthday_type":"1","birthhour":"15","sex_text":"男","name":"岳建男","goods_state":"1","solar_birthday":"1992年10月22日 申时","goods_img":"http://bz-goods.oss-cn-hangzhou.aliyuncs.com/twcskgfx.jpg"}]
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
         * lunar_birthday : 二零二零年五月十六 子时
         * birthday : 2020-07-06
         * goods_name : 未绑定
         * isleap :
         * payment_status : 1
         * sex : 1
         * mingpan_user :
         * mingpan_id : 603
         * birthday_type : 1
         * birthhour : 00
         * sex_text : 男
         * name : 哦哦哦
         * goods_state : 2
         * solar_birthday : 2020年7月6日 子时
         * goods_img : http://bz-goods.oss-cn-hangzhou.aliyuncs.com/jiahao.png
         */

        private String lunar_birthday;
        private String birthday;
        private String goods_name;
        private String isleap;
        private String payment_status;
        private String sex;
        private String mingpan_user;
        private String mingpan_id;
        private String birthday_type;
        private String birthhour;
        private String sex_text;
        private String name;
        private String goods_state;
        private String solar_birthday;
        private String goods_img;

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

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
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

        public String getMingpan_id() {
            return mingpan_id;
        }

        public void setMingpan_id(String mingpan_id) {
            this.mingpan_id = mingpan_id;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGoods_state() {
            return goods_state;
        }

        public void setGoods_state(String goods_state) {
            this.goods_state = goods_state;
        }

        public String getSolar_birthday() {
            return solar_birthday;
        }

        public void setSolar_birthday(String solar_birthday) {
            this.solar_birthday = solar_birthday;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }
    }
}
