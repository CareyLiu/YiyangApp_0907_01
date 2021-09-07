package com.yiyang.cn.model;

import java.io.Serializable;
import java.util.List;

public class GoodsDetails implements Serializable {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"inst_accid":"jcz_sub_200","shop_product_title":"横版数码按键开关","shop_colour":"-","isCollection":"0","assList":[{"user_to_text":"东西非常好，下次还会选购，不要错过哦","user_to_time":"2019-06-24 14:39:23","of_user_id":"455","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9251","user_to_score":"5.00","user_name":"Sunshine "},{"user_to_text":"东西非常好，下次还会选购，不要错过哦","user_to_time":"2019-06-24 14:39:23","of_user_id":"455","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9251","user_to_score":"5.00","user_name":"Sunshine "},{"user_to_text":"东西非常好，下次还会选购，不要错过哦","user_to_time":"2019-06-24 14:39:23","of_user_id":"455","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9251","user_to_score":"5.00","user_name":"Sunshine "}],"wares_sales_volume":"已售112件","wares_id":"9","money_begin":"10.00","ass_count":"15","bannerList":[{"vedio_url":"http://smvideo.duowan.com/video_upload/12785.mp4","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6644"},{"vedio_url":"","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6639"},{"vedio_url":"","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6643"},{"vedio_url":"","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6641"},{"vedio_url":"","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6642"}],"wares_type":"1","inst_id":"187","make_num":"","wares_go_type":"1","addr":"黑龙江省哈尔滨市","shop_brand":"神灯","alone_money":"","wares_detail":"<p><img src=\"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8035\" data-filename=\"横版数码键开关.jpg\" style=\"width: 177px;\"><br><\/p><p>横版数码按键开关<\/p>","wares_state":"1","inst_logo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8594","shop_product_norms":"SD-k-07","wares_money_go":"10.00","inst_name":"神灯科技旗舰店","money_end":"18.00","productList":[{"flag":"false","money_now":"12.00","shop_product_count":"-92","shop_product_id":"1","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","money_make":"","product_title":"套餐1"},{"flag":"false","money_now":"16.00","shop_product_count":"1208","shop_product_id":"3","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","money_make":"","product_title":"套餐3"},{"flag":"true","money_now":"14.00","shop_product_count":"73","shop_product_id":"2","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","money_make":"","product_title":"套餐2"},{"flag":"false","money_now":"18.00","shop_product_count":"-66","shop_product_id":"4","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","money_make":"","product_title":"套餐4"},{"flag":"false","money_now":"10.00","shop_product_count":"-32","shop_product_id":"9","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","money_make":"","product_title":"套餐5"}]}]
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
         * inst_accid : jcz_sub_200
         * shop_product_title : 横版数码按键开关
         * shop_colour : -
         * isCollection : 0
         * assList : [{"user_to_text":"东西非常好，下次还会选购，不要错过哦","user_to_time":"2019-06-24 14:39:23","of_user_id":"455","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9251","user_to_score":"5.00","user_name":"Sunshine "},{"user_to_text":"东西非常好，下次还会选购，不要错过哦","user_to_time":"2019-06-24 14:39:23","of_user_id":"455","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9251","user_to_score":"5.00","user_name":"Sunshine "},{"user_to_text":"东西非常好，下次还会选购，不要错过哦","user_to_time":"2019-06-24 14:39:23","of_user_id":"455","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9251","user_to_score":"5.00","user_name":"Sunshine "}]
         * wares_sales_volume : 已售112件
         * wares_id : 9
         * money_begin : 10.00
         * ass_count : 15
         * bannerList : [{"vedio_url":"http://smvideo.duowan.com/video_upload/12785.mp4","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6644"},{"vedio_url":"","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6639"},{"vedio_url":"","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6643"},{"vedio_url":"","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6641"},{"vedio_url":"","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6642"}]
         * wares_type : 1
         * inst_id : 187
         * make_num :
         * wares_go_type : 1
         * addr : 黑龙江省哈尔滨市
         * shop_brand : 神灯
         * alone_money :
         * wares_detail : <p><img src="https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8035" data-filename="横版数码键开关.jpg" style="width: 177px;"><br></p><p>横版数码按键开关</p>
         * wares_state : 1
         * inst_logo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8594
         * shop_product_norms : SD-k-07
         * wares_money_go : 10.00
         * inst_name : 神灯科技旗舰店
         * money_end : 18.00
         * productList : [{"flag":"false","money_now":"12.00","shop_product_count":"-92","shop_product_id":"1","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","money_make":"","product_title":"套餐1"},{"flag":"false","money_now":"16.00","shop_product_count":"1208","shop_product_id":"3","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","money_make":"","product_title":"套餐3"},{"flag":"true","money_now":"14.00","shop_product_count":"73","shop_product_id":"2","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","money_make":"","product_title":"套餐2"},{"flag":"false","money_now":"18.00","shop_product_count":"-66","shop_product_id":"4","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","money_make":"","product_title":"套餐4"},{"flag":"false","money_now":"10.00","shop_product_count":"-32","shop_product_id":"9","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","money_make":"","product_title":"套餐5"}]
         */

        private String inst_accid;
        private String shop_product_title;
        private String shop_colour;
        private String isCollection;
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
        private List<ProductListBean> productList;

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

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public static class AssListBean implements Serializable {
            public String getNoneDataShwo() {
                return noneDataShwo;
            }

            public void setNoneDataShwo(String noneDataShwo) {
                this.noneDataShwo = noneDataShwo;
            }

            /**
             * user_to_text : 东西非常好，下次还会选购，不要错过哦
             * user_to_time : 2019-06-24 14:39:23
             * of_user_id : 455
             * user_img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9251
             * user_to_score : 5.00
             * user_name : Sunshine
             */
            private String noneDataShwo = "";
            private String user_to_text;
            private String user_to_time;
            private String of_user_id;
            private String user_img_url;
            private String user_to_score;
            private String user_name;

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
             * vedio_url : http://smvideo.duowan.com/video_upload/12785.mp4
             * img_url : https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6644
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

        public static class ProductListBean implements Serializable {
            public String getSelect() {
                return select;
            }

            public void setSelect(String select) {
                this.select = select;
            }

            /**
             * flag : false
             * money_now : 12.00
             * shop_product_count : -92
             * shop_product_id : 1
             * index_photo_url : https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102
             * money_make :
             * product_title : 套餐1
             */

            private String select = "0";// 0否 1是
            private Boolean flag = false;
            private String money_now;
            private String shop_product_count;
            private String shop_product_id;
            private String index_photo_url;
            private String money_make;
            private String product_title;

            public String getShopImage() {
                return shopImage;
            }

            public void setShopImage(String shopImage) {
                this.shopImage = shopImage;
            }

            private String shopImage;

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            private String shopName;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            private String count;//买了多少

            public String getProduct_biaoti() {
                return product_biaoti;
            }

            public void setProduct_biaoti(String product_biaoti) {
                this.product_biaoti = product_biaoti;
            }

            private String product_biaoti;

            public String getDetails_count() {
                return details_count;
            }

            public void setDetails_count(String details_count) {
                this.details_count = details_count;
            }

            private String details_count;//商品数量

            public Boolean getFlag() {
                return flag;
            }

            public void setFlag(Boolean flag) {
                this.flag = flag;
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
    }
}
