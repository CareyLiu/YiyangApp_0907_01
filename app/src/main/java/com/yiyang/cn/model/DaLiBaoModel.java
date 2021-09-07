package com.yiyang.cn.model;

import java.util.List;

public class DaLiBaoModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 13
     * data : [{"wares_list":[{"wares_id":"179","shop_product_id":"91","shop_product_title":"智能家居","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10756","money_range":"180.00～1588.00"},{"wares_id":"188","shop_product_id":"","shop_product_title":"神灯风暖","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10622","money_range":"988.00"},{"wares_id":"189","shop_product_id":"105","shop_product_title":"神灯水暖","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10841","money_range":"1588.00"},{"wares_id":"190","shop_product_id":"104","shop_product_title":"神灯驻车电动空调","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10844","money_range":"1588.00～1888.00"},{"wares_id":"191","shop_product_id":"103","shop_product_title":"空气净化器","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10843","money_range":"988.00～1588.00"},{"wares_id":"192","shop_product_id":"158","shop_product_title":"居安普尼智能门锁","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10836","money_range":"988.00～1588.00"},{"wares_id":"193","shop_product_id":"107","shop_product_title":"厨房智慧宝","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10657","money_range":"988.00"},{"wares_id":"194","shop_product_id":"108","shop_product_title":"倒车自动刹车系统","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10680","money_range":"988.00"},{"wares_id":"195","shop_product_id":"111","shop_product_title":"神灯净水器","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10716","money_range":"988.00～1588.00"},{"wares_id":"196","shop_product_id":"112","shop_product_title":"神灯手机控车","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10711","money_range":"1588.00"},{"wares_id":"197","shop_product_id":"110","shop_product_title":"神灯教育机器人","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10743","money_range":"988.00"},{"wares_id":"198","shop_product_id":"109","shop_product_title":"神灯风水摆件","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10700","money_range":"988.00～1588.00"},{"wares_id":"199","shop_product_id":"113","shop_product_title":"神灯太空按摩椅","wares_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10934","money_range":"1588.00～1888.00"}],"rule_list":[{"name":"凡购买以上商品赠送18000大礼包，包括：","id":"1"},{"name":"二甲医院全年不限次数免费体检","id":"2"},{"name":"东北三省旅游景点门票100张不限次数门票","id":"3"},{"name":"加油省钱（全国加油特惠覆盖全国大中小城市3W多家，中石油中石化在列最高直降1.9元）","id":"4"},{"name":"赠送大型洗浴中心门票10张","id":"5"},{"name":"200家KTV包房或门票免费赠","id":"6"},{"name":"五次免费洗车仅限道里区七家并现不断增加洗车网点","id":"7"},{"name":"汽车修配厂免费充气，免费检测电路，免费检测油路，免费检测底盘","id":"8"},{"name":"初高中学霸笔记免费下载","id":"9"},{"name":"0-25岁每年免费赠送10张写真，（如果是0岁可以送到25岁）","id":"10"},{"name":"游泳馆健身房免费体验","id":"11"},{"name":"饭店吃饭即得红包，买产品即送红包","id":"12"},{"name":"商城即返现金，（自买省分享赚）","id":"13"},{"name":"肯德基麦当劳星巴克打折卷免费送","id":"14"},{"name":"吃穿住行都省钱实现躺赚","id":"15"},{"name":"药品全部九折","id":"16"},{"name":"赠送保险公司家财险","id":"17"}]}]
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
        private List<WaresListBean> wares_list;
        private List<RuleListBean> rule_list;

        public List<WaresListBean> getWares_list() {
            return wares_list;
        }

        public void setWares_list(List<WaresListBean> wares_list) {
            this.wares_list = wares_list;
        }

        public List<RuleListBean> getRule_list() {
            return rule_list;
        }

        public void setRule_list(List<RuleListBean> rule_list) {
            this.rule_list = rule_list;
        }

        public static class WaresListBean {
            /**
             * wares_id : 179
             * shop_product_id : 91
             * shop_product_title : 智能家居
             * wares_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10756
             * money_range : 180.00～1588.00
             */

            private String wares_id;
            private String shop_product_id;
            private String shop_product_title;
            private String wares_photo_url;
            private String money_range;

            public String getWares_id() {
                return wares_id;
            }

            public void setWares_id(String wares_id) {
                this.wares_id = wares_id;
            }

            public String getShop_product_id() {
                return shop_product_id;
            }

            public void setShop_product_id(String shop_product_id) {
                this.shop_product_id = shop_product_id;
            }

            public String getShop_product_title() {
                return shop_product_title;
            }

            public void setShop_product_title(String shop_product_title) {
                this.shop_product_title = shop_product_title;
            }

            public String getWares_photo_url() {
                return wares_photo_url;
            }

            public void setWares_photo_url(String wares_photo_url) {
                this.wares_photo_url = wares_photo_url;
            }

            public String getMoney_range() {
                return money_range;
            }

            public void setMoney_range(String money_range) {
                this.money_range = money_range;
            }
        }

        public static class RuleListBean {
            /**
             * name : 凡购买以上商品赠送18000大礼包，包括：
             * id : 1
             */

            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
