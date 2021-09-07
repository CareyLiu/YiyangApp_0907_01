package com.yiyang.cn.model;

import java.util.List;

public class OrderTuiKuanDetailsModel {
    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"shop_form_id":"3031","inst_worker_name":"月亮 18249030297","form_no":"20200619165250000003","refund_express_url":"","refund_type":"2","shop_product_title":"横版数码按键开关","refund_express_name":"","user_name":"王大力","refund_rate":"2","refund_over_time":"2020-06-22 16:54:07","refund_no":"rollback_20200619165250000003","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9931","pay_money":"0.50","refund_index":"1","product_title":"套餐1","refund_express_no":"","refund_arr":["发起退款","审核通过","用户发货","商家收货","退款"],"inst_addr_all":"黑龙江省哈尔滨市南岗区花园街道哈哈哈哈","user_phone":"15236586932","user_addr_all":"北京北京市东城区丽景花园1栋301","order_info_arr":["订单备注：","订单编号：20200619165250000003","下单时间：2020-06-19 16:52:50","支付方式：微信支付","付款时间：2020-06-19 16:52:57"]}]
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
        /**
         * shop_form_id : 3031
         * inst_worker_name : 月亮 18249030297
         * form_no : 20200619165250000003
         * refund_express_url :
         * refund_type : 2
         * shop_product_title : 横版数码按键开关
         * refund_express_name :
         * user_name : 王大力
         * refund_rate : 2
         * refund_over_time : 2020-06-22 16:54:07
         * refund_no : rollback_20200619165250000003
         * index_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9931
         * pay_money : 0.50
         * refund_index : 1
         * product_title : 套餐1
         * refund_express_no :
         * refund_arr : ["发起退款","审核通过","用户发货","商家收货","退款"]
         * inst_addr_all : 黑龙江省哈尔滨市南岗区花园街道哈哈哈哈
         * user_phone : 15236586932
         * user_addr_all : 北京北京市东城区丽景花园1栋301
         * order_info_arr : ["订单备注：","订单编号：20200619165250000003","下单时间：2020-06-19 16:52:50","支付方式：微信支付","付款时间：2020-06-19 16:52:57"]
         */

        private String shop_form_id;
        private String inst_worker_name;
        private String form_no;
        private String refund_express_url;
        private String refund_type;
        private String shop_product_title;
        private String refund_express_name;
        private String user_name;
        private String refund_rate;
        private String refund_over_time;
        private String refund_no;
        private String index_photo_url;
        private String pay_money;
        private String refund_index;
        private String product_title;
        private String refund_express_no;
        private String inst_addr_all;
        private String user_phone;
        private String user_addr_all;
        private List<String> refund_arr;
        private List<String> order_info_arr;

        public String getShop_form_id() {
            return shop_form_id;
        }

        public void setShop_form_id(String shop_form_id) {
            this.shop_form_id = shop_form_id;
        }

        public String getInst_worker_name() {
            return inst_worker_name;
        }

        public void setInst_worker_name(String inst_worker_name) {
            this.inst_worker_name = inst_worker_name;
        }

        public String getForm_no() {
            return form_no;
        }

        public void setForm_no(String form_no) {
            this.form_no = form_no;
        }

        public String getRefund_express_url() {
            return refund_express_url;
        }

        public void setRefund_express_url(String refund_express_url) {
            this.refund_express_url = refund_express_url;
        }

        public String getRefund_type() {
            return refund_type;
        }

        public void setRefund_type(String refund_type) {
            this.refund_type = refund_type;
        }

        public String getShop_product_title() {
            return shop_product_title;
        }

        public void setShop_product_title(String shop_product_title) {
            this.shop_product_title = shop_product_title;
        }

        public String getRefund_express_name() {
            return refund_express_name;
        }

        public void setRefund_express_name(String refund_express_name) {
            this.refund_express_name = refund_express_name;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getRefund_rate() {
            return refund_rate;
        }

        public void setRefund_rate(String refund_rate) {
            this.refund_rate = refund_rate;
        }

        public String getRefund_over_time() {
            return refund_over_time;
        }

        public void setRefund_over_time(String refund_over_time) {
            this.refund_over_time = refund_over_time;
        }

        public String getRefund_no() {
            return refund_no;
        }

        public void setRefund_no(String refund_no) {
            this.refund_no = refund_no;
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

        public String getRefund_index() {
            return refund_index;
        }

        public void setRefund_index(String refund_index) {
            this.refund_index = refund_index;
        }

        public String getProduct_title() {
            return product_title;
        }

        public void setProduct_title(String product_title) {
            this.product_title = product_title;
        }

        public String getRefund_express_no() {
            return refund_express_no;
        }

        public void setRefund_express_no(String refund_express_no) {
            this.refund_express_no = refund_express_no;
        }

        public String getInst_addr_all() {
            return inst_addr_all;
        }

        public void setInst_addr_all(String inst_addr_all) {
            this.inst_addr_all = inst_addr_all;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_addr_all() {
            return user_addr_all;
        }

        public void setUser_addr_all(String user_addr_all) {
            this.user_addr_all = user_addr_all;
        }

        public List<String> getRefund_arr() {
            return refund_arr;
        }

        public void setRefund_arr(List<String> refund_arr) {
            this.refund_arr = refund_arr;
        }

        public List<String> getOrder_info_arr() {
            return order_info_arr;
        }

        public void setOrder_info_arr(List<String> order_info_arr) {
            this.order_info_arr = order_info_arr;
        }
    }


    /**
     * msg_code	返回码
     * msg	应答描述
     * data	应答数据
     * shop_form_id	订单id
     * refund_no	退货单号
     * form_no	订单号
     * refund_rate	退款进度：1.发起申请2.商家审核3.用户发货4.商家收货
     * 5.退款成功6.商家拒绝
     * refund_type	退款类型：1.仅退款2.退款退货
     * index_photo_url	商品封面图
     * shop_product_title	商品标题
     * product_title	颜色分类标题
     * pay_money	退款金额
     * inst_addr_all 	收货地址
     * inst_worker_name	收货人及联系电话
     * refund_over_time	退款结束时间
     * refund_express_no	退货物流单号
     * refund_arr	退款进度名称
     */

}
