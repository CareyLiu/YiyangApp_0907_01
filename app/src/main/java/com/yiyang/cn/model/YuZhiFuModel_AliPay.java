package com.yiyang.cn.model;

import java.util.List;

public class YuZhiFuModel_AliPay {
    /**
     * msg_code : 0000
     * msg : 成功创建订单
     * data : [{"pay":"app_id=2019041363858556&biz_content=%7B%22out_trade_no%22%3A%222018040320313602917%22%2C%22total_amount%22%3A%22388.00%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E5%A4%A7%E7%A4%BC%E5%8C%85%22%2C%22timeout_express%22%3A%225m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E5%A4%A7%E7%A4%BC%E5%8C%85%22%2C%22seller_id%22%3A%22%22%7D&charset=utf-8&format=JSON&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fshop.hljsdkj.com%2Fmsg%2Fpay%2FalipayCallback&sign_type=RSA2&timestamp=2020-04-16%2017%3A34%3A48&version=1.0&sign=kNp9NnL3NbJYyKUezUNLbQMfcwker%2B8bywsO97hqRB2xHpt%2F6CmS%2ByUiyqi5cgcK02bsRSATZkTSjT66a6vWsZ8maHqmbNAGzlTQUEYv5iJ81qkMzsTt7XjB3oqrP6yOCpq9Ah6R1j7jiMvV8KjkVactACwZY8YbUpjkfmhkB73IrmzqRuOiXkPTE7Iij2xjG8k%2FRcip3wVJLvPRnY1Q3fTBv%2BedvWAWzQgV82gbwnXIaPahXtXgDVTI24JT2%2BozXbHwuRrmDfMNDJzpahcKWHQ5q5PuoV9Ez9prjNSn4ZOlXD1fGsdWFXc0OV%2FW2CnTEtRWYps4fE%2BKOHCBoLXzKw%3D%3D"}]
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
        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        /**
         * pay : app_id=2019041363858556&biz_content=%7B%22out_trade_no%22%3A%222018040320313602917%22%2C%22total_amount%22%3A%22388.00%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E5%A4%A7%E7%A4%BC%E5%8C%85%22%2C%22timeout_express%22%3A%225m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E5%A4%A7%E7%A4%BC%E5%8C%85%22%2C%22seller_id%22%3A%22%22%7D&charset=utf-8&format=JSON&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fshop.hljsdkj.com%2Fmsg%2Fpay%2FalipayCallback&sign_type=RSA2&timestamp=2020-04-16%2017%3A34%3A48&version=1.0&sign=kNp9NnL3NbJYyKUezUNLbQMfcwker%2B8bywsO97hqRB2xHpt%2F6CmS%2ByUiyqi5cgcK02bsRSATZkTSjT66a6vWsZ8maHqmbNAGzlTQUEYv5iJ81qkMzsTt7XjB3oqrP6yOCpq9Ah6R1j7jiMvV8KjkVactACwZY8YbUpjkfmhkB73IrmzqRuOiXkPTE7Iij2xjG8k%2FRcip3wVJLvPRnY1Q3fTBv%2BedvWAWzQgV82gbwnXIaPahXtXgDVTI24JT2%2BozXbHwuRrmDfMNDJzpahcKWHQ5q5PuoV9Ez9prjNSn4ZOlXD1fGsdWFXc0OV%2FW2CnTEtRWYps4fE%2BKOHCBoLXzKw%3D%3D
         */

        private String out_trade_no;

        private String pay;

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }
    }
}
