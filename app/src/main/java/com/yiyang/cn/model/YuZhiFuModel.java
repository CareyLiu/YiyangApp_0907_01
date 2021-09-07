package com.yiyang.cn.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YuZhiFuModel {
    /**
     * msg_code : 0000
     * msg : 成功创建订单
     * data : [{"pay":{"package":"Sign=WXPay","out_trade_no":"2018040320313602883","appid":"wxafadbee6859e4228","sign":"6F332CD0183DF5ED601A56BA8C1D8DF6","partnerid":"1501279761","prepayid":"wx161442129636663a15ac32ae1315192200","noncestr":"1587019333009","timestamp":"1587019333"}}]
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
         * pay : {"package":"Sign=WXPay","out_trade_no":"2018040320313602883","appid":"wxafadbee6859e4228","sign":"6F332CD0183DF5ED601A56BA8C1D8DF6","partnerid":"1501279761","prepayid":"wx161442129636663a15ac32ae1315192200","noncestr":"1587019333009","timestamp":"1587019333"}
         */

        private PayBean pay;

        public PayBean getPay() {
            return pay;
        }

        public void setPay(PayBean pay) {
            this.pay = pay;
        }

        public static class PayBean {
            /**
             * package : Sign=WXPay
             * out_trade_no : 2018040320313602883
             * appid : wxafadbee6859e4228
             * sign : 6F332CD0183DF5ED601A56BA8C1D8DF6
             * partnerid : 1501279761
             * prepayid : wx161442129636663a15ac32ae1315192200
             * noncestr : 1587019333009
             * timestamp : 1587019333
             */

            @SerializedName("package")
            private String packageX;
            private String out_trade_no;
            private String appid;
            private String sign;
            private String partnerid;
            private String prepayid;
            private String noncestr;
            private String timestamp;

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }
    }
}
