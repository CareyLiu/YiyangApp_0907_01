package com.yiyang.cn.model;

import java.util.List;



public class ZhiFuTypeModel {


    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"is_payment_supported":"2","pay_type":"4","pay_id":"2","pay_img_url":"http://zf-img-jyj.oss-cn-hangzhou.aliyuncs.com/dingdan_icon_wexin@2x.png","payment_method":"微信支付"},{"is_payment_supported":"1","pay_type":"1","pay_id":"1","pay_img_url":"http://zf-img-jyj.oss-cn-hangzhou.aliyuncs.com/dingdan_icon_zhifubao@2x.png","payment_method":"支付宝支付"}]
     */

    public String msg_code;
    public String msg;
    public List<DataBean> data;


    public static class DataBean {
        /**
         * is_payment_supported : 2
         * pay_type : 4
         * pay_id : 2
         * pay_img_url : http://zf-img-jyj.oss-cn-hangzhou.aliyuncs.com/dingdan_icon_wexin@2x.png
         * payment_method : 微信支付
         */

        public String is_payment_supported;
        public String pay_type;
        public String pay_id;
        public String pay_img_url;
        public String payment_method;
        public String choose="1";// 0 否 1 是
    }
}
