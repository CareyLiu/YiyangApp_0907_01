package com.yiyang.cn.activity.xiupeichang.model;

import java.util.List;

public class XpcDetailsModel {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"week_five":"","lunboList":[{"inst_state":"1","img_order":"1","create_time":"2020-11-05","img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=11690","inst_id":"229","img_id":"11690","inst_img_id":"148"}],"inst_photo_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=11677","meter":"9.8km","inst_number":"5.0","inst_number_name":"5.0分","week_two":"","addr_all":"黑龙江省哈尔滨市道里区抚顺街道荣盛汽配","average_cost":"","inst_phone":"15244771234","value_1":"7","week_four":"","week_one":"","taocanList":[{"pay_count":"14","wares_id":"270","agio":"60","week":"","img_url":"http://192.168.1.127:8080/Frame/uploadFile/showImg?file_id=11659","shop_detail":"贴片补胎","itemtype_id":"2","shop_title":"补胎","inst_id":"229","shop_money_now":"1.00","shop_money_old":"50.00"},{"pay_count":"0","wares_id":"271","agio":"50","week":"","img_url":"http://192.168.1.127:8080/Frame/uploadFile/showImg?file_id=11660","shop_detail":"贴片补胎","itemtype_id":"2","shop_title":"补胎","inst_id":"229","shop_money_now":"100.00","shop_money_old":"200.00"}],"week_three":"","week_seven":"","x":"45.755646","y":"126.622154","pinglunList":[{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"499","user_img_url":"https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png","user_to_score":"5.00","user_name":"月亮啦"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2032","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","user_to_score":"5.00","user_name":"阿钊"}],"inst_id":"229","inst_name":"荣盛汽配","time":"","week_six":""}]
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
         * week_five :
         * lunboList : [{"inst_state":"1","img_order":"1","create_time":"2020-11-05","img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=11690","inst_id":"229","img_id":"11690","inst_img_id":"148"}]
         * inst_photo_url : https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=11677
         * meter : 9.8km
         * inst_number : 5.0
         * inst_number_name : 5.0分
         * week_two :
         * addr_all : 黑龙江省哈尔滨市道里区抚顺街道荣盛汽配
         * average_cost :
         * inst_phone : 15244771234
         * value_1 : 7
         * week_four :
         * week_one :
         * taocanList : [{"pay_count":"14","wares_id":"270","agio":"60","week":"","img_url":"http://192.168.1.127:8080/Frame/uploadFile/showImg?file_id=11659","shop_detail":"贴片补胎","itemtype_id":"2","shop_title":"补胎","inst_id":"229","shop_money_now":"1.00","shop_money_old":"50.00"},{"pay_count":"0","wares_id":"271","agio":"50","week":"","img_url":"http://192.168.1.127:8080/Frame/uploadFile/showImg?file_id=11660","shop_detail":"贴片补胎","itemtype_id":"2","shop_title":"补胎","inst_id":"229","shop_money_now":"100.00","shop_money_old":"200.00"}]
         * week_three :
         * week_seven :
         * x : 45.755646
         * y : 126.622154
         * pinglunList : [{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"499","user_img_url":"https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png","user_to_score":"5.00","user_name":"月亮啦"},{"user_to_text":"体验非常好","user_to_time":"2020-06-06 09:49:58","of_user_id":"2032","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","user_to_score":"5.00","user_name":"阿钊"}]
         * inst_id : 229
         * inst_name : 荣盛汽配
         * time :
         * week_six :
         */

        private String week_five;
        private String inst_photo_url;
        private String meter;
        private String inst_number;
        private String inst_number_name;
        private String week_two;
        private String addr_all;
        private String average_cost;
        private String inst_phone;
        private String value_1;
        private String week_four;
        private String week_one;
        private String week_three;
        private String week_seven;
        private String x;
        private String y;
        private String inst_id;
        private String inst_name;
        private String time;
        private String week_six;
        private List<LunboListBean> lunboList;
        private List<TaocanListBean> taocanList;
        private List<PinglunListBean> pinglunList;

        public String getWeek_five() {
            return week_five;
        }

        public void setWeek_five(String week_five) {
            this.week_five = week_five;
        }

        public String getInst_photo_url() {
            return inst_photo_url;
        }

        public void setInst_photo_url(String inst_photo_url) {
            this.inst_photo_url = inst_photo_url;
        }

        public String getMeter() {
            return meter;
        }

        public void setMeter(String meter) {
            this.meter = meter;
        }

        public String getInst_number() {
            return inst_number;
        }

        public void setInst_number(String inst_number) {
            this.inst_number = inst_number;
        }

        public String getInst_number_name() {
            return inst_number_name;
        }

        public void setInst_number_name(String inst_number_name) {
            this.inst_number_name = inst_number_name;
        }

        public String getWeek_two() {
            return week_two;
        }

        public void setWeek_two(String week_two) {
            this.week_two = week_two;
        }

        public String getAddr_all() {
            return addr_all;
        }

        public void setAddr_all(String addr_all) {
            this.addr_all = addr_all;
        }

        public String getAverage_cost() {
            return average_cost;
        }

        public void setAverage_cost(String average_cost) {
            this.average_cost = average_cost;
        }

        public String getInst_phone() {
            return inst_phone;
        }

        public void setInst_phone(String inst_phone) {
            this.inst_phone = inst_phone;
        }

        public String getValue_1() {
            return value_1;
        }

        public void setValue_1(String value_1) {
            this.value_1 = value_1;
        }

        public String getWeek_four() {
            return week_four;
        }

        public void setWeek_four(String week_four) {
            this.week_four = week_four;
        }

        public String getWeek_one() {
            return week_one;
        }

        public void setWeek_one(String week_one) {
            this.week_one = week_one;
        }

        public String getWeek_three() {
            return week_three;
        }

        public void setWeek_three(String week_three) {
            this.week_three = week_three;
        }

        public String getWeek_seven() {
            return week_seven;
        }

        public void setWeek_seven(String week_seven) {
            this.week_seven = week_seven;
        }

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getInst_name() {
            return inst_name;
        }

        public void setInst_name(String inst_name) {
            this.inst_name = inst_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWeek_six() {
            return week_six;
        }

        public void setWeek_six(String week_six) {
            this.week_six = week_six;
        }

        public List<LunboListBean> getLunboList() {
            return lunboList;
        }

        public void setLunboList(List<LunboListBean> lunboList) {
            this.lunboList = lunboList;
        }

        public List<TaocanListBean> getTaocanList() {
            return taocanList;
        }

        public void setTaocanList(List<TaocanListBean> taocanList) {
            this.taocanList = taocanList;
        }

        public List<PinglunListBean> getPinglunList() {
            return pinglunList;
        }

        public void setPinglunList(List<PinglunListBean> pinglunList) {
            this.pinglunList = pinglunList;
        }

        public static class LunboListBean {
            /**
             * inst_state : 1
             * img_order : 1
             * create_time : 2020-11-05
             * img_url : https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=11690
             * inst_id : 229
             * img_id : 11690
             * inst_img_id : 148
             */

            private String inst_state;
            private String img_order;
            private String create_time;
            private String img_url;
            private String inst_id;
            private String img_id;
            private String inst_img_id;

            public String getInst_state() {
                return inst_state;
            }

            public void setInst_state(String inst_state) {
                this.inst_state = inst_state;
            }

            public String getImg_order() {
                return img_order;
            }

            public void setImg_order(String img_order) {
                this.img_order = img_order;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getInst_id() {
                return inst_id;
            }

            public void setInst_id(String inst_id) {
                this.inst_id = inst_id;
            }

            public String getImg_id() {
                return img_id;
            }

            public void setImg_id(String img_id) {
                this.img_id = img_id;
            }

            public String getInst_img_id() {
                return inst_img_id;
            }

            public void setInst_img_id(String inst_img_id) {
                this.inst_img_id = inst_img_id;
            }
        }

        public static class TaocanListBean {
            /**
             * pay_count : 14
             * wares_id : 270
             * agio : 60
             * week :
             * img_url : http://192.168.1.127:8080/Frame/uploadFile/showImg?file_id=11659
             * shop_detail : 贴片补胎
             * itemtype_id : 2
             * shop_title : 补胎
             * inst_id : 229
             * shop_money_now : 1.00
             * shop_money_old : 50.00
             */

            private String pay_count;
            private String wares_id;
            private String agio;
            private String week;
            private String img_url;
            private String shop_detail;
            private String itemtype_id;
            private String shop_title;
            private String inst_id;
            private String shop_money_now;
            private String shop_money_old;

            private boolean isHideLine;

            public boolean isHideLine() {
                return isHideLine;
            }

            public void setHideLine(boolean hideLine) {
                isHideLine = hideLine;
            }

            public String getPay_count() {
                return pay_count;
            }

            public void setPay_count(String pay_count) {
                this.pay_count = pay_count;
            }

            public String getWares_id() {
                return wares_id;
            }

            public void setWares_id(String wares_id) {
                this.wares_id = wares_id;
            }

            public String getAgio() {
                return agio;
            }

            public void setAgio(String agio) {
                this.agio = agio;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getShop_detail() {
                return shop_detail;
            }

            public void setShop_detail(String shop_detail) {
                this.shop_detail = shop_detail;
            }

            public String getItemtype_id() {
                return itemtype_id;
            }

            public void setItemtype_id(String itemtype_id) {
                this.itemtype_id = itemtype_id;
            }

            public String getShop_title() {
                return shop_title;
            }

            public void setShop_title(String shop_title) {
                this.shop_title = shop_title;
            }

            public String getInst_id() {
                return inst_id;
            }

            public void setInst_id(String inst_id) {
                this.inst_id = inst_id;
            }

            public String getShop_money_now() {
                return shop_money_now;
            }

            public void setShop_money_now(String shop_money_now) {
                this.shop_money_now = shop_money_now;
            }

            public String getShop_money_old() {
                return shop_money_old;
            }

            public void setShop_money_old(String shop_money_old) {
                this.shop_money_old = shop_money_old;
            }
        }

        public static class PinglunListBean {
            /**
             * user_to_text : 体验非常好
             * user_to_time : 2020-06-06 09:49:58
             * of_user_id : 499
             * user_img_url : https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png
             * user_to_score : 5.00
             * user_name : 月亮啦
             */

            private String user_to_text;
            private String user_to_time;
            private String of_user_id;
            private String user_img_url;
            private String user_to_score;
            private String user_name;
            private boolean isHideLine;

            public boolean isHideLine() {
                return isHideLine;
            }

            public void setHideLine(boolean hideLine) {
                isHideLine = hideLine;
            }

            public String getUser_to_text() {
                return user_to_text;
            }

            public void setUser_to_text(String user_to_text) {
                this.user_to_text = user_to_text;
            }

            public String getUser_to_time() {
                return user_to_time;
            }

            public void setUser_to_time(String user_to_time) {
                this.user_to_time = user_to_time;
            }

            public String getOf_user_id() {
                return of_user_id;
            }

            public void setOf_user_id(String of_user_id) {
                this.of_user_id = of_user_id;
            }

            public String getUser_img_url() {
                return user_img_url;
            }

            public void setUser_img_url(String user_img_url) {
                this.user_img_url = user_img_url;
            }

            public String getUser_to_score() {
                return user_to_score;
            }

            public void setUser_to_score(String user_to_score) {
                this.user_to_score = user_to_score;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }
    }
}
