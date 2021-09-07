package com.yiyang.cn.model;

import java.util.List;

public class TuanGouShangJiaListBeanNew {

    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * data : [{"area_name":"松北区","pay_count":"1","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11841","inst_text":"轮胎","business_status":"1","meter":"17.1","inst_number":"5.0","inst_number_name":"5.0分","value_1":"7","meter_name":"17.1km","value_4_name":"","inst_id":"235","inst_name":"鑫宏兴汽配","per":""},{"area_name":"松北区","pay_count":"0","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11843","inst_text":"汽车内饰","business_status":"","meter":"17.4","inst_number":"5.0","inst_number_name":"5.0分","value_1":"7","meter_name":"17.4km","value_4_name":"","inst_id":"234","inst_name":"金路达汽配","per":""},{"area_name":"香坊区","pay_count":"0","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11845","inst_text":"汽车外饰","business_status":"","meter":"7.2","inst_number":"5.0","inst_number_name":"5.0分","value_1":"7","meter_name":"7.2km","value_4_name":"","inst_id":"233","inst_name":"赛骐汽配","per":""},{"area_name":"道外区","pay_count":"0","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11855","inst_text":"保养","business_status":"","meter":"14.4","inst_number":"5.0","inst_number_name":"5.0分","value_1":"7","meter_name":"14.4km","value_4_name":"","inst_id":"230","inst_name":"凯祥汽配","per":""},{"area_name":"道里区","pay_count":"0","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11857","inst_text":"汽车改装","business_status":"","meter":"9.8","inst_number":"5.0","inst_number_name":"5.0分","value_1":"7","meter_name":"9.8km","value_4_name":"","inst_id":"229","inst_name":"锦鹏汽配","per":""},{"area_name":"道里区","pay_count":"0","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11859","inst_text":"汽车改装","business_status":"","meter":"10.1","inst_number":"5.0","inst_number_name":"5.0分","value_1":"7","meter_name":"10.1km","value_4_name":"","inst_id":"228","inst_name":"壳牌喜力汽配","per":""},{"area_name":"南岗区","pay_count":"0","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11849","inst_text":"汽车美容","business_status":"1","meter":"11.5","inst_number":"5.0","inst_number_name":"5.0分","value_1":"7","meter_name":"11.5km","value_4_name":"","inst_id":"227","inst_name":"伟光汽配","per":""},{"area_name":"南岗区","pay_count":"0","platform_cooperation":"1","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11851","inst_text":"洗车","business_status":"","meter":"11.6","inst_number":"5.0","inst_number_name":"5.0分","value_1":"7","meter_name":"11.6km","value_4_name":"","inst_id":"226","inst_name":"顺驰汽配","per":""}]
     */

    private String next;
    private String msg_code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * area_name : 松北区
         * pay_count : 1
         * platform_cooperation : 1
         * inst_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11841
         * inst_text : 轮胎
         * business_status : 1
         * meter : 17.1
         * inst_number : 5.0
         * inst_number_name : 5.0分
         * value_1 : 7
         * meter_name : 17.1km
         * value_4_name :
         * inst_id : 235
         * inst_name : 鑫宏兴汽配
         * per :
         */

        private String area_name;
        private String pay_count;
        private String platform_cooperation;
        private String inst_photo_url;
        private String inst_text;
        private String business_status;
        private String meter;
        private String inst_number;
        private String inst_number_name;
        private String value_1;
        private String meter_name;
        private String value_4_name;
        private String inst_id;
        private String inst_name;
        private String per;

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

        public String getBusiness_status() {
            return business_status;
        }

        public void setBusiness_status(String business_status) {
            this.business_status = business_status;
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

        public String getValue_1() {
            return value_1;
        }

        public void setValue_1(String value_1) {
            this.value_1 = value_1;
        }

        public String getMeter_name() {
            return meter_name;
        }

        public void setMeter_name(String meter_name) {
            this.meter_name = meter_name;
        }

        public String getValue_4_name() {
            return value_4_name;
        }

        public void setValue_4_name(String value_4_name) {
            this.value_4_name = value_4_name;
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

        public String getPer() {
            return per;
        }

        public void setPer(String per) {
            this.per = per;
        }
    }
}
