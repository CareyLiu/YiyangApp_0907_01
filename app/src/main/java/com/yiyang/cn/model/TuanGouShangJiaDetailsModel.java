package com.yiyang.cn.model;

import java.util.List;

public class TuanGouShangJiaDetailsModel {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"lunboList":[],"favourableList":[{"pay_count":"0","wares_id":"73","agio":"5.2","week":"周一至周日","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","itemtype_id":"2","shop_title":"美食三","inst_id":"261","shop_money_now":"258.00","shop_money_old":"300.00"}],"neighborList":[{"area_name":"北林区","pay_count":"17","wares_id":"70","agio":"5.2","distance":"115.6","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","shop_title":"美食三","inst_id":"258","shop_money_now":"258.00","shop_money_old":"300.00","inst_name":"麦比克火锅"},{"area_name":"南岗区","pay_count":"0","wares_id":"71","agio":"5.2","distance":"10.9","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","shop_title":"美食三","inst_id":"259","shop_money_now":"258.00","shop_money_old":"300.00","inst_name":"您作煮火锅"},{"area_name":"道外区","pay_count":"0","wares_id":"72","agio":"5.2","distance":"14.5","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","shop_title":"美食三","inst_id":"260","shop_money_now":"258.00","shop_money_old":"300.00","inst_name":"寻味烤肉"},{"area_name":"道外区","pay_count":"0","wares_id":"73","agio":"5.2","distance":"14.2","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","shop_title":"美食三","inst_id":"261","shop_money_now":"258.00","shop_money_old":"300.00","inst_name":"鞑子碳烤羊腿"},{"area_name":"松北区","pay_count":"0","wares_id":"74","agio":"5.2","distance":"16.9","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","shop_title":"美食三","inst_id":"262","shop_money_now":"258.00","shop_money_old":"300.00","inst_name":"麦香村西饼屋（松北店）"}],"storeList":{"week_five":"1","week":"周一至周日","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9073","img_count":"0","meter":"14.2km","inst_number":"2.0","inst_number_name":"2.0分","week_two":"1","addr_all":"黑龙江省哈尔滨市道外区太古街道黑龙江省哈尔滨市","average_cost":"¥67/人","inst_phone":"","value_1":"1","week_four":"1","week_one":"1","week_three":"1","week_seven":"1","x":"45.789725","y":"126.650539","inst_id":"261","inst_name":"鞑子碳烤羊腿","time":"08:00-20:00","week_six":"1"},"highScoreList":[{"pay_count":"0","area_name":"双城区","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11079","inst_text":"烤肉","meter":"38.7","inst_number":"5.0","value_4":"","inst_id":"403","inst_name":"齐市烤肉"},{"pay_count":"0","area_name":"道外区","platform_cooperation":"2","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9071","inst_text":"烤肉","meter":"14.5","inst_number":"5.0","value_4":"6.0","inst_id":"260","inst_name":"寻味烤肉"},{"pay_count":"0","area_name":"双城区","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11101","inst_text":"杀猪菜","meter":"38.7","inst_number":"5.0","value_4":"","inst_id":"410","inst_name":"兰棱杀猪菜（文昌总店）"},{"pay_count":"6","area_name":"双城区","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11257","inst_text":"杀猪菜","meter":"39.7","inst_number":"5.0","value_4":"","inst_id":"398","inst_name":"兰棱杀猪菜（堡旭分店）"},{"pay_count":"17","area_name":"北林区","platform_cooperation":"2","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9065","inst_text":"火锅","meter":"115.6","inst_number":"4.0","value_4":"6.0","inst_id":"258","inst_name":"麦比克火锅"}]}]
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
         * lunboList : []
         * favourableList : [{"pay_count":"0","wares_id":"73","agio":"5.2","week":"周一至周日","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","itemtype_id":"2","shop_title":"美食三","inst_id":"261","shop_money_now":"258.00","shop_money_old":"300.00"}]
         * neighborList : [{"area_name":"北林区","pay_count":"17","wares_id":"70","agio":"5.2","distance":"115.6","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","shop_title":"美食三","inst_id":"258","shop_money_now":"258.00","shop_money_old":"300.00","inst_name":"麦比克火锅"},{"area_name":"南岗区","pay_count":"0","wares_id":"71","agio":"5.2","distance":"10.9","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","shop_title":"美食三","inst_id":"259","shop_money_now":"258.00","shop_money_old":"300.00","inst_name":"您作煮火锅"},{"area_name":"道外区","pay_count":"0","wares_id":"72","agio":"5.2","distance":"14.5","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","shop_title":"美食三","inst_id":"260","shop_money_now":"258.00","shop_money_old":"300.00","inst_name":"寻味烤肉"},{"area_name":"道外区","pay_count":"0","wares_id":"73","agio":"5.2","distance":"14.2","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","shop_title":"美食三","inst_id":"261","shop_money_now":"258.00","shop_money_old":"300.00","inst_name":"鞑子碳烤羊腿"},{"area_name":"松北区","pay_count":"0","wares_id":"74","agio":"5.2","distance":"16.9","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_detail":"精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃","shop_title":"美食三","inst_id":"262","shop_money_now":"258.00","shop_money_old":"300.00","inst_name":"麦香村西饼屋（松北店）"}]
         * storeList : {"week_five":"1","week":"周一至周日","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9073","img_count":"0","meter":"14.2km","inst_number":"2.0","inst_number_name":"2.0分","week_two":"1","addr_all":"黑龙江省哈尔滨市道外区太古街道黑龙江省哈尔滨市","average_cost":"¥67/人","inst_phone":"","value_1":"1","week_four":"1","week_one":"1","week_three":"1","week_seven":"1","x":"45.789725","y":"126.650539","inst_id":"261","inst_name":"鞑子碳烤羊腿","time":"08:00-20:00","week_six":"1"}
         * highScoreList : [{"pay_count":"0","area_name":"双城区","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11079","inst_text":"烤肉","meter":"38.7","inst_number":"5.0","value_4":"","inst_id":"403","inst_name":"齐市烤肉"},{"pay_count":"0","area_name":"道外区","platform_cooperation":"2","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9071","inst_text":"烤肉","meter":"14.5","inst_number":"5.0","value_4":"6.0","inst_id":"260","inst_name":"寻味烤肉"},{"pay_count":"0","area_name":"双城区","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11101","inst_text":"杀猪菜","meter":"38.7","inst_number":"5.0","value_4":"","inst_id":"410","inst_name":"兰棱杀猪菜（文昌总店）"},{"pay_count":"6","area_name":"双城区","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11257","inst_text":"杀猪菜","meter":"39.7","inst_number":"5.0","value_4":"","inst_id":"398","inst_name":"兰棱杀猪菜（堡旭分店）"},{"pay_count":"17","area_name":"北林区","platform_cooperation":"2","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9065","inst_text":"火锅","meter":"115.6","inst_number":"4.0","value_4":"6.0","inst_id":"258","inst_name":"麦比克火锅"}]
         */

        private StoreListBean storeList;
        private List<FavourableListBean> favourableList;
        private List<NeighborListBean> neighborList;
        private List<HighScoreListBean> highScoreList;
        private List<LunboListBean> lunboList;

        public StoreListBean getStoreList() {
            return storeList;
        }

        public void setStoreList(StoreListBean storeList) {
            this.storeList = storeList;
        }

        public List<LunboListBean> getLunboList() {
            return lunboList;
        }

        public void setLunboList(List<LunboListBean> lunboList) {
            this.lunboList = lunboList;
        }

        public List<FavourableListBean> getFavourableList() {
            return favourableList;
        }

        public void setFavourableList(List<FavourableListBean> favourableList) {
            this.favourableList = favourableList;
        }

        public List<NeighborListBean> getNeighborList() {
            return neighborList;
        }

        public void setNeighborList(List<NeighborListBean> neighborList) {
            this.neighborList = neighborList;
        }

        public List<HighScoreListBean> getHighScoreList() {
            return highScoreList;
        }

        public void setHighScoreList(List<HighScoreListBean> highScoreList) {
            this.highScoreList = highScoreList;
        }

        public static class StoreListBean {
            /**
             * week_five : 1
             * week : 周一至周日
             * inst_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9073
             * img_count : 0
             * meter : 14.2km
             * inst_number : 2.0
             * inst_number_name : 2.0分
             * week_two : 1
             * addr_all : 黑龙江省哈尔滨市道外区太古街道黑龙江省哈尔滨市
             * average_cost : ¥67/人
             * inst_phone :
             * value_1 : 1
             * week_four : 1
             * week_one : 1
             * week_three : 1
             * week_seven : 1
             * x : 45.789725
             * y : 126.650539
             * inst_id : 261
             * inst_name : 鞑子碳烤羊腿
             * time : 08:00-20:00
             * week_six : 1
             */

            private String week_five;
            private String week;
            private String inst_photo_url;
            private String img_count;
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

            public String getWeek_five() {
                return week_five;
            }

            public void setWeek_five(String week_five) {
                this.week_five = week_five;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getInst_photo_url() {
                return inst_photo_url;
            }

            public void setInst_photo_url(String inst_photo_url) {
                this.inst_photo_url = inst_photo_url;
            }

            public String getImg_count() {
                return img_count;
            }

            public void setImg_count(String img_count) {
                this.img_count = img_count;
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
        }

        public static class FavourableListBean {
            /**
             * pay_count : 0
             * wares_id : 73
             * agio : 5.2
             * week : 周一至周日
             * img_url : https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480
             * shop_detail : 精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃
             * itemtype_id : 2
             * shop_title : 美食三
             * inst_id : 261
             * shop_money_now : 258.00
             * shop_money_old : 300.00
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

        public static class NeighborListBean {
            /**
             * area_name : 北林区
             * pay_count : 17
             * wares_id : 70
             * agio : 5.2
             * distance : 115.6
             * img_url : https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480
             * shop_detail : 精品肥牛，新西兰羊排，澳洲肥牛好吃很好吃
             * shop_title : 美食三
             * inst_id : 258
             * shop_money_now : 258.00
             * shop_money_old : 300.00
             * inst_name : 麦比克火锅
             */

            private String area_name;
            private String pay_count;
            private String wares_id;
            private String agio;
            private String distance;
            private String img_url;
            private String shop_detail;
            private String shop_title;
            private String inst_id;
            private String shop_money_now;
            private String shop_money_old;
            private String inst_name;

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
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

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
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

            public String getInst_name() {
                return inst_name;
            }

            public void setInst_name(String inst_name) {
                this.inst_name = inst_name;
            }
        }

        public static class HighScoreListBean {
            /**
             * pay_count : 0
             * area_name : 双城区
             * platform_cooperation : 1
             * inst_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11079
             * inst_text : 烤肉
             * meter : 38.7
             * inst_number : 5.0
             * value_4 :
             * inst_id : 403
             * inst_name : 齐市烤肉
             */

            private String pay_count;
            private String area_name;
            private String platform_cooperation;
            private String inst_photo_url;
            private String inst_text;
            private String meter;
            private String inst_number;
            private String value_4;
            private String inst_id;
            private String inst_name;

            public String getPay_count() {
                return pay_count;
            }

            public void setPay_count(String pay_count) {
                this.pay_count = pay_count;
            }

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            public String getPlatform_cooperation() {
                return platform_cooperation;
            }

            public void setPlatform_cooperation(String platform_cooperation) {
                this.platform_cooperation = platform_cooperation;
            }

            public String getInst_photo_url() {
                return inst_photo_url;
            }

            public void setInst_photo_url(String inst_photo_url) {
                this.inst_photo_url = inst_photo_url;
            }

            public String getInst_text() {
                return inst_text;
            }

            public void setInst_text(String inst_text) {
                this.inst_text = inst_text;
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

            public String getValue_4() {
                return value_4;
            }

            public void setValue_4(String value_4) {
                this.value_4 = value_4;
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
        }
    }

    public static class LunboListBean {
        /**
         * inst_state : 1
         * img_order : 1
         * create_time : 2020-06-05
         * img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11236
         * inst_id : 410
         * img_id : 11236
         * inst_img_id : 88
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
}
