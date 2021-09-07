package com.yiyang.cn.model;

import java.util.List;


/**
 * msg_code	返回码	6	是
 * msg	应答描述	30	是
 * data	应答数据		否
 * user_img_url	用户头像地址	100	否
 * user_name	姓名	10	是
 * user_sex	性别(1.男 2.女 3.其它)	1	是
 * user_birthday	生日		是
 * user_phone	电话号（中间带四个星号，且判断位数是否是十三位）		是
 * alipay_number	提现账号（带*）		否
 * alipay_uname	提现人姓名（带*）
 * score_tx	提现手续费
 * score_zd	最低提现金额(元)
 * collect_ware_count	收藏商品数量		是
 * collect_shop_count	关注店铺数量		是
 * browse_count	浏览数量		是
 * voucher_count	红包卡券		是
 * pay_pwd_check	是否设置支付密码：
 * 1.已经设置 2.未设置
 * alipay_number_check	是否设置提现账户：
 * 1.已经设置 2.未设置
 * agent_up_url	我的代理页面进入地址
 * referral_code_url	推荐页地址
 * invitation_code	邀请码
 * agent_user_type	用户是否是代理商 1.是 2.不是
 */
public class MineModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"of_user_id":"1526","collect_ware_count":"1","user_name":"未设置","pay_pwd_check":"2","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","makeList":[],"agent_up_url":"https://shop.hljsdkj.com/shop_new/app/userAgentTreePage?token=1588840v83422600c000L000Z000F0","user_sex":"1","user_birthday":"2000-01-01","alipay_uname":"***l","alipay_number":"","phone":"15946250162","voucher_count":"0","score_zd":"0.01","user_phone":"159****0162","alipay_number_check":"2","browse_count":"0","collect_shop_count":"0","score_tx":"1%"}]
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
         * of_user_id : 1526
         * collect_ware_count : 1
         * user_name : 未设置
         * pay_pwd_check : 2
         * user_img_url : https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png
         * makeList : []
         * agent_up_url : https://shop.hljsdkj.com/shop_new/app/userAgentTreePage?token=1588840v83422600c000L000Z000F0
         * user_sex : 1
         * user_birthday : 2000-01-01
         * alipay_uname : ***l
         * alipay_number :
         * phone : 15946250162
         * voucher_count : 0
         * score_zd : 0.01
         * user_phone : 159****0162
         * alipay_number_check : 2
         * browse_count : 0
         * collect_shop_count : 0
         * score_tx : 1%
         */

        private String of_user_id;
        private String collect_ware_count;
        private String user_name;
        private String pay_pwd_check;
        private String user_img_url;
        private String agent_up_url;
        private String user_sex;
        private String user_birthday;
        private String alipay_uname;
        private String alipay_number;
        private String phone;
        private String voucher_count;
        private String score_zd;
        private String user_phone;
        private String alipay_number_check; //1 已经设置 2 未设置
        private String browse_count;
        private String collect_shop_count;
        private String score_tx;
        private List<?> makeList;

        public String getWx_pay_number_check() {
            return wx_pay_number_check;
        }

        public void setWx_pay_number_check(String wx_pay_number_check) {
            this.wx_pay_number_check = wx_pay_number_check;
        }

        private String wx_pay_number_check;

        public String getAgent_url() {
            return agent_url;
        }

        public void setAgent_url(String agent_url) {
            this.agent_url = agent_url;
        }

        private String agent_url;

        public String getAgent_user_type() {
            return agent_user_type;
        }

        public void setAgent_user_type(String agent_user_type) {
            this.agent_user_type = agent_user_type;
        }

        private String agent_user_type;

        public String getReferral_code_url() {
            return referral_code_url;
        }

        public void setReferral_code_url(String referral_code_url) {
            this.referral_code_url = referral_code_url;
        }

        private String referral_code_url;

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getCollect_ware_count() {
            return collect_ware_count;
        }

        public void setCollect_ware_count(String collect_ware_count) {
            this.collect_ware_count = collect_ware_count;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPay_pwd_check() {
            return pay_pwd_check;
        }

        public void setPay_pwd_check(String pay_pwd_check) {
            this.pay_pwd_check = pay_pwd_check;
        }

        public String getUser_img_url() {
            return user_img_url;
        }

        public void setUser_img_url(String user_img_url) {
            this.user_img_url = user_img_url;
        }

        public String getAgent_up_url() {
            return agent_up_url;
        }

        public void setAgent_up_url(String agent_up_url) {
            this.agent_up_url = agent_up_url;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_birthday() {
            return user_birthday;
        }

        public void setUser_birthday(String user_birthday) {
            this.user_birthday = user_birthday;
        }

        public String getAlipay_uname() {
            return alipay_uname;
        }

        public void setAlipay_uname(String alipay_uname) {
            this.alipay_uname = alipay_uname;
        }

        public String getAlipay_number() {
            return alipay_number;
        }

        public void setAlipay_number(String alipay_number) {
            this.alipay_number = alipay_number;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getVoucher_count() {
            return voucher_count;
        }

        public void setVoucher_count(String voucher_count) {
            this.voucher_count = voucher_count;
        }

        public String getScore_zd() {
            return score_zd;
        }

        public void setScore_zd(String score_zd) {
            this.score_zd = score_zd;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getAlipay_number_check() {
            return alipay_number_check;
        }

        public void setAlipay_number_check(String alipay_number_check) {
            this.alipay_number_check = alipay_number_check;
        }

        public String getBrowse_count() {
            return browse_count;
        }

        public void setBrowse_count(String browse_count) {
            this.browse_count = browse_count;
        }

        public String getCollect_shop_count() {
            return collect_shop_count;
        }

        public void setCollect_shop_count(String collect_shop_count) {
            this.collect_shop_count = collect_shop_count;
        }

        public String getScore_tx() {
            return score_tx;
        }

        public void setScore_tx(String score_tx) {
            this.score_tx = score_tx;
        }

        public List<?> getMakeList() {
            return makeList;
        }

        public void setMakeList(List<?> makeList) {
            this.makeList = makeList;
        }
    }
}
