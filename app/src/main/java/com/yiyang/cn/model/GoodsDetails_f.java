package com.yiyang.cn.model;

import java.io.Serializable;
import java.util.List;

public class GoodsDetails_f implements Serializable {
    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"inst_accid":"jcz_sub_214","shop_product_title":"神灯教育机器人","shop_colour":"-","isCollection":"0","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10709","assList":[{"user_to_text":"物美价廉","user_to_time":"2020-05-15 17:32:04","of_user_id":"1526","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","user_to_score":"5.00","user_name":"未设置"}],"wares_sales_volume":"已售0件","wares_id":"197","money_begin":"1.00","ass_count":"1","bannerList":[{"vedio_url":"","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10745"},{"vedio_url":"","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10799"}],"wares_type":"1","inst_id":"205","make_num":"","wares_go_type":"1","addr":"黑龙江省哈尔滨市","shop_brand":"-","alone_money":"","wares_detail":"","wares_state":"1","inst_logo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=4638","detailImgList":[{"img_width":"752","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10800","img_height":"3090"}],"shop_product_norms":"-","wares_money_go":"10.00","inst_name":"22","money_end":"1.00","productList":[{"flag":"true","red_packet_money":"8.00","money_now":"1.00","shop_product_count":"70","shop_product_id":"110","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10709","money_make":"","product_title":"套餐A"}]}]
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

    public static class DataBean implements Serializable {
        /**
         * inst_accid : jcz_sub_214
         * shop_product_title : 神灯教育机器人
         * shop_colour : -
         * isCollection : 0
         * index_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10709
         * assList : [{"user_to_text":"物美价廉","user_to_time":"2020-05-15 17:32:04","of_user_id":"1526","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","user_to_score":"5.00","user_name":"未设置"}]
         * wares_sales_volume : 已售0件
         * wares_id : 197
         * money_begin : 1.00
         * ass_count : 1
         * bannerList : [{"vedio_url":"","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10745"},{"vedio_url":"","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10799"}]
         * wares_type : 1
         * inst_id : 205
         * make_num :
         * wares_go_type : 1
         * addr : 黑龙江省哈尔滨市
         * shop_brand : -
         * alone_money :
         * wares_detail :
         * wares_state : 1
         * inst_logo_url : https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=4638
         * detailImgList : [{"img_width":"752","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10800","img_height":"3090"}]
         * shop_product_norms : -
         * wares_money_go : 10.00
         * inst_name : 22
         * money_end : 1.00
         * productList : [{"flag":"true","red_packet_money":"8.00","money_now":"1.00","shop_product_count":"70","shop_product_id":"110","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10709","money_make":"","product_title":"套餐A"}]
         */

        private String inst_accid;
        private String shop_product_title;
        private String shop_colour;
        private String isCollection;
        private String index_photo_url;
        private String wares_sales_volume;
        private String wares_id;
        private String money_begin;
        private String ass_count;
        private String wares_type;
        private String inst_id;
        private String make_num;
        private String wares_go_type;
        private String addr;
        private String shop_brand;
        private String alone_money;
        private String wares_detail;
        private String wares_state;
        private String inst_logo_url;
        private String shop_product_norms;
        private String wares_money_go;
        private String inst_name;
        private String money_end;
        private List<AssListBean> assList;
        private List<BannerListBean> bannerList;
        private List<DetailImgListBean> detailImgList;
        private List<ProductListBean> productList;
        public List<InstallationTypeBean> installationType;

        public String getInst_accid() {
            return inst_accid;
        }

        public void setInst_accid(String inst_accid) {
            this.inst_accid = inst_accid;
        }

        public String getShop_product_title() {
            return shop_product_title;
        }

        public void setShop_product_title(String shop_product_title) {
            this.shop_product_title = shop_product_title;
        }

        public String getShop_colour() {
            return shop_colour;
        }

        public void setShop_colour(String shop_colour) {
            this.shop_colour = shop_colour;
        }

        public String getIsCollection() {
            return isCollection;
        }

        public void setIsCollection(String isCollection) {
            this.isCollection = isCollection;
        }

        public String getIndex_photo_url() {
            return index_photo_url;
        }

        public void setIndex_photo_url(String index_photo_url) {
            this.index_photo_url = index_photo_url;
        }

        public String getWares_sales_volume() {
            return wares_sales_volume;
        }

        public void setWares_sales_volume(String wares_sales_volume) {
            this.wares_sales_volume = wares_sales_volume;
        }

        public String getWares_id() {
            return wares_id;
        }

        public void setWares_id(String wares_id) {
            this.wares_id = wares_id;
        }

        public String getMoney_begin() {
            return money_begin;
        }

        public void setMoney_begin(String money_begin) {
            this.money_begin = money_begin;
        }

        public String getAss_count() {
            return ass_count;
        }

        public void setAss_count(String ass_count) {
            this.ass_count = ass_count;
        }

        public String getWares_type() {
            return wares_type;
        }

        public void setWares_type(String wares_type) {
            this.wares_type = wares_type;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getMake_num() {
            return make_num;
        }

        public void setMake_num(String make_num) {
            this.make_num = make_num;
        }

        public String getWares_go_type() {
            return wares_go_type;
        }

        public void setWares_go_type(String wares_go_type) {
            this.wares_go_type = wares_go_type;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getShop_brand() {
            return shop_brand;
        }

        public void setShop_brand(String shop_brand) {
            this.shop_brand = shop_brand;
        }

        public String getAlone_money() {
            return alone_money;
        }

        public void setAlone_money(String alone_money) {
            this.alone_money = alone_money;
        }

        public String getWares_detail() {
            return wares_detail;
        }

        public void setWares_detail(String wares_detail) {
            this.wares_detail = wares_detail;
        }

        public String getWares_state() {
            return wares_state;
        }

        public void setWares_state(String wares_state) {
            this.wares_state = wares_state;
        }

        public String getInst_logo_url() {
            return inst_logo_url;
        }

        public void setInst_logo_url(String inst_logo_url) {
            this.inst_logo_url = inst_logo_url;
        }

        public String getShop_product_norms() {
            return shop_product_norms;
        }

        public void setShop_product_norms(String shop_product_norms) {
            this.shop_product_norms = shop_product_norms;
        }

        public String getWares_money_go() {
            return wares_money_go;
        }

        public void setWares_money_go(String wares_money_go) {
            this.wares_money_go = wares_money_go;
        }

        public String getInst_name() {
            return inst_name;
        }

        public void setInst_name(String inst_name) {
            this.inst_name = inst_name;
        }

        public String getMoney_end() {
            return money_end;
        }

        public void setMoney_end(String money_end) {
            this.money_end = money_end;
        }

        public List<AssListBean> getAssList() {
            return assList;
        }

        public void setAssList(List<AssListBean> assList) {
            this.assList = assList;
        }

        public List<BannerListBean> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<BannerListBean> bannerList) {
            this.bannerList = bannerList;
        }

        public List<DetailImgListBean> getDetailImgList() {
            return detailImgList;
        }

        public void setDetailImgList(List<DetailImgListBean> detailImgList) {
            this.detailImgList = detailImgList;
        }

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public static class AssListBean implements Serializable {
            /**
             * user_to_text : 物美价廉
             * user_to_time : 2020-05-15 17:32:04
             * of_user_id : 1526
             * user_img_url : https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png
             * user_to_score : 5.00
             * user_name : 未设置
             */

            private String user_to_text;
            private String user_to_time;
            private String of_user_id;
            private String user_img_url;
            private String user_to_score;
            private String user_name;

            public String getNonDataShow() {
                return nonDataShow;
            }

            public void setNonDataShow(String nonDataShow) {
                this.nonDataShow = nonDataShow;
            }

            private String nonDataShow = "";

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

        public static class BannerListBean implements Serializable {
            /**
             * vedio_url :
             * img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10745
             */

            private String vedio_url;
            private String img_url;

            public String getVedio_url() {
                return vedio_url;
            }

            public void setVedio_url(String vedio_url) {
                this.vedio_url = vedio_url;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }

        public static class DetailImgListBean implements Serializable {
            /**
             * img_width : 752
             * img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10800
             * img_height : 3090
             */

            private String img_width;
            private String img_url;
            private String img_height;

            public String getImg_width() {
                return img_width;
            }

            public void setImg_width(String img_width) {
                this.img_width = img_width;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getImg_height() {
                return img_height;
            }

            public void setImg_height(String img_height) {
                this.img_height = img_height;
            }
        }

        public static class ProductListBean implements Serializable {
            public Boolean getFlag() {
                return flag;
            }

            public void setFlag(Boolean flag) {
                this.flag = flag;
            }

            /**
             * flag : true
             * red_packet_money : 8.00
             * money_now : 1.00
             * shop_product_count : 70
             * shop_product_id : 110
             * index_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10709
             * money_make :
             * product_title : 套餐A
             */


            private Boolean flag = false;
            private String red_packet_money;
            private String money_now;
            private String shop_product_count;
            private String shop_product_id;
            private String index_photo_url;
            private String money_make;
            private String product_title;
            public String index_small_photo_url;

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            private String shopName;

            public String getSelect() {
                return select;
            }

            public void setSelect(String select) {
                this.select = select;
            }

            public String getProduct_biaoti() {
                return product_biaoti;
            }

            public void setProduct_biaoti(String product_biaoti) {
                this.product_biaoti = product_biaoti;
            }

            public String getShopImage() {
                return shopImage;
            }

            public void setShopImage(String shopImage) {
                this.shopImage = shopImage;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getDetails_count() {
                return details_count;
            }

            public void setDetails_count(String details_count) {
                this.details_count = details_count;
            }

            private String select = "0";// 0否 1是


            private String product_biaoti;
            private String shopImage;
            private String count;//买了多少
            private String details_count;//商品数量


            public String getRed_packet_money() {
                return red_packet_money;
            }

            public void setRed_packet_money(String red_packet_money) {
                this.red_packet_money = red_packet_money;
            }

            public String getMoney_now() {
                return money_now;
            }

            public void setMoney_now(String money_now) {
                this.money_now = money_now;
            }

            public String getShop_product_count() {
                return shop_product_count;
            }

            public void setShop_product_count(String shop_product_count) {
                this.shop_product_count = shop_product_count;
            }

            public String getShop_product_id() {
                return shop_product_id;
            }

            public void setShop_product_id(String shop_product_id) {
                this.shop_product_id = shop_product_id;
            }

            public String getIndex_photo_url() {
                return index_photo_url;
            }

            public void setIndex_photo_url(String index_photo_url) {
                this.index_photo_url = index_photo_url;
            }

            public String getMoney_make() {
                return money_make;
            }

            public void setMoney_make(String money_make) {
                this.money_make = money_make;
            }

            public String getProduct_title() {
                return product_title;
            }

            public void setProduct_title(String product_title) {
                this.product_title = product_title;
            }
        }

        public static class InstallationTypeBean implements Serializable {

            /**
             * installation_type_name	安装类型名称
             * installation_type_id	安装类型id
             * installation_money	安装金额
             * install_default	默认选中 1.选中 0未选中
             */

            public String installation_type_name;//安装类型名
            public String installation_type_id;
            public String installation_money;
            public String install_default;


        }
    }
}
