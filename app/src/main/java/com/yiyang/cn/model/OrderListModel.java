package com.yiyang.cn.model;

import java.io.Serializable;
import java.util.List;

public class OrderListModel implements Serializable {
    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 4
     * data : [{"shop_form_id":"2151","user_pay_check":"1","inst_accid":"","form_no":"2020581027511508","inst_img_url":"","pay_check_name":"应付","shop_product_title":"麦香村西饼屋（平房店）(直接下单)","form_product_money":"1.00","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9077","pay_money":"1.00","form_money_go":"","wares_id":"0","express_url":"","wares_type":"4","shop_product_id":"","inst_id":"263","wares_go_type":"4","pay_count":"1","operate_type":"29","user_pay_check_name":"待付款 ","form_id":"","deduction_amount":"","product_title":"","shop_form_text":"","red_packet_deduction_amount":"","disabled_cause":"","inst_name":"麦香村西饼屋（平房店）","total_money":"1.00"},{"shop_form_id":"2128","user_pay_check":"11","inst_accid":"","form_no":"202057919391508","inst_img_url":"","pay_check_name":"实付","shop_product_title":"麦香村西饼屋（平房店）(直接下单)","form_product_money":"0.00","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9077","pay_money":"0.00","form_money_go":"","wares_id":"0","express_url":"","wares_type":"4","shop_product_id":"","inst_id":"263","wares_go_type":"4","pay_count":"1","operate_type":"","user_pay_check_name":"订单失效","form_id":"","deduction_amount":"","product_title":"","shop_form_text":"","red_packet_deduction_amount":"","disabled_cause":"24小时未付款","inst_name":"麦香村西饼屋（平房店）","total_money":"0.00"},{"shop_form_id":"2104","user_pay_check":"11","inst_accid":"","form_no":"2020561347551508","inst_img_url":"","pay_check_name":"实付","shop_product_title":"美食三","form_product_money":"258.00","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","pay_money":"0.00","form_money_go":"","wares_id":"75","express_url":"","wares_type":"3","shop_product_id":"","inst_id":"263","wares_go_type":"3","pay_count":"1","operate_type":"","user_pay_check_name":"订单失效","form_id":"","deduction_amount":"","product_title":"","shop_form_text":"","red_packet_deduction_amount":"","disabled_cause":"24小时未付款","inst_name":"麦香村西饼屋（平房店）","total_money":"258.00"},{"shop_form_id":"2095","user_pay_check":"11","inst_accid":"","form_no":"2020561115551508","inst_img_url":"","pay_check_name":"实付","shop_product_title":"麦香村西饼屋（平房店）(直接下单)","form_product_money":"1.00","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9077","pay_money":"0.00","form_money_go":"","wares_id":"0","express_url":"","wares_type":"4","shop_product_id":"","inst_id":"263","wares_go_type":"4","pay_count":"1","operate_type":"","user_pay_check_name":"订单失效","form_id":"","deduction_amount":"","product_title":"","shop_form_text":"","red_packet_deduction_amount":"","disabled_cause":"24小时未付款","inst_name":"麦香村西饼屋（平房店）","total_money":"1.00"}]
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

    public static class DataBean implements Serializable {
        /**
         * shop_form_id : 2151
         * user_pay_check : 1
         * inst_accid :
         * form_no : 2020581027511508
         * inst_img_url :
         * pay_check_name : 应付
         * shop_product_title : 麦香村西饼屋（平房店）(直接下单)
         * form_product_money : 1.00
         * index_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9077
         * pay_money : 1.00
         * form_money_go :
         * wares_id : 0
         * express_url :
         * wares_type : 4
         * shop_product_id :
         * inst_id : 263
         * wares_go_type : 4
         * pay_count : 1
         * operate_type : 29
         * user_pay_check_name : 待付款
         * form_id :
         * deduction_amount :
         * product_title :
         * shop_form_text :
         * red_packet_deduction_amount :
         * disabled_cause :
         * inst_name : 麦香村西饼屋（平房店）
         * total_money : 1.00
         */

        private String shop_form_id;
        private String user_pay_check;
        private String inst_accid;
        private String form_no;
        private String inst_img_url;
        private String pay_check_name;
        private String shop_product_title;
        private String form_product_money;
        private String index_photo_url;
        private String pay_money;
        private String form_money_go;
        private String wares_id;
        private String express_url;
        private String wares_type;
        private String shop_product_id;
        private String inst_id;
        private String wares_go_type;
        private String pay_count;
        private String operate_type;
        private String user_pay_check_name;
        private String form_id;
        private String deduction_amount;
        private String product_title;
        private String shop_form_text;
        private String red_packet_deduction_amount;
        private String disabled_cause;
        private String inst_name;
        private String total_money;
        public String shop_type;//团购or订单

        public String getInstallation_type_id() {
            return installation_type_id;
        }

        public void setInstallation_type_id(String installation_type_id) {
            this.installation_type_id = installation_type_id;
        }

        private String installation_type_id;

        public String getShop_form_id() {
            return shop_form_id;
        }

        public void setShop_form_id(String shop_form_id) {
            this.shop_form_id = shop_form_id;
        }

        public String getUser_pay_check() {
            return user_pay_check;
        }

        public void setUser_pay_check(String user_pay_check) {
            this.user_pay_check = user_pay_check;
        }

        public String getInst_accid() {
            return inst_accid;
        }

        public void setInst_accid(String inst_accid) {
            this.inst_accid = inst_accid;
        }

        public String getForm_no() {
            return form_no;
        }

        public void setForm_no(String form_no) {
            this.form_no = form_no;
        }

        public String getInst_img_url() {
            return inst_img_url;
        }

        public void setInst_img_url(String inst_img_url) {
            this.inst_img_url = inst_img_url;
        }

        public String getPay_check_name() {
            return pay_check_name;
        }

        public void setPay_check_name(String pay_check_name) {
            this.pay_check_name = pay_check_name;
        }

        public String getShop_product_title() {
            return shop_product_title;
        }

        public void setShop_product_title(String shop_product_title) {
            this.shop_product_title = shop_product_title;
        }

        public String getForm_product_money() {
            return form_product_money;
        }

        public void setForm_product_money(String form_product_money) {
            this.form_product_money = form_product_money;
        }

        public String getIndex_photo_url() {
            return index_photo_url;
        }

        public void setIndex_photo_url(String index_photo_url) {
            this.index_photo_url = index_photo_url;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public String getForm_money_go() {
            return form_money_go;
        }

        public void setForm_money_go(String form_money_go) {
            this.form_money_go = form_money_go;
        }

        public String getWares_id() {
            return wares_id;
        }

        public void setWares_id(String wares_id) {
            this.wares_id = wares_id;
        }

        public String getExpress_url() {
            return express_url;
        }

        public void setExpress_url(String express_url) {
            this.express_url = express_url;
        }

        public String getWares_type() {
            return wares_type;
        }

        public void setWares_type(String wares_type) {
            this.wares_type = wares_type;
        }

        public String getShop_product_id() {
            return shop_product_id;
        }

        public void setShop_product_id(String shop_product_id) {
            this.shop_product_id = shop_product_id;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getWares_go_type() {
            return wares_go_type;
        }

        public void setWares_go_type(String wares_go_type) {
            this.wares_go_type = wares_go_type;
        }

        public String getPay_count() {
            return pay_count;
        }

        public void setPay_count(String pay_count) {
            this.pay_count = pay_count;
        }

        public String getOperate_type() {
            return operate_type;
        }

        public void setOperate_type(String operate_type) {
            this.operate_type = operate_type;
        }

        public String getUser_pay_check_name() {
            return user_pay_check_name;
        }

        public void setUser_pay_check_name(String user_pay_check_name) {
            this.user_pay_check_name = user_pay_check_name;
        }

        public String getForm_id() {
            return form_id;
        }

        public void setForm_id(String form_id) {
            this.form_id = form_id;
        }

        public String getDeduction_amount() {
            return deduction_amount;
        }

        public void setDeduction_amount(String deduction_amount) {
            this.deduction_amount = deduction_amount;
        }

        public String getProduct_title() {
            return product_title;
        }

        public void setProduct_title(String product_title) {
            this.product_title = product_title;
        }

        public String getShop_form_text() {
            return shop_form_text;
        }

        public void setShop_form_text(String shop_form_text) {
            this.shop_form_text = shop_form_text;
        }

        public String getRed_packet_deduction_amount() {
            return red_packet_deduction_amount;
        }

        public void setRed_packet_deduction_amount(String red_packet_deduction_amount) {
            this.red_packet_deduction_amount = red_packet_deduction_amount;
        }

        public String getDisabled_cause() {
            return disabled_cause;
        }

        public void setDisabled_cause(String disabled_cause) {
            this.disabled_cause = disabled_cause;
        }

        public String getInst_name() {
            return inst_name;
        }

        public void setInst_name(String inst_name) {
            this.inst_name = inst_name;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }
    }
}
