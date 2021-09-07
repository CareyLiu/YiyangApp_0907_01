package com.yiyang.cn.model;

import java.util.List;

public class ShareModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"share_detail":"买即送加油打折、红包返还、超值美食套餐，分享立得红包20元！","share_title":"惊爆价588元！】居安普尼\u2022智能门锁 ","share_url":"https://test.hljsdkj.com/shop_new/jyj_pingtai?user_id=1526&invitation_code=MS1526","share_img":"img"}]
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
         * share_detail : 买即送加油打折、红包返还、超值美食套餐，分享立得红包20元！
         * share_title : 惊爆价588元！】居安普尼•智能门锁
         * share_url : https://test.hljsdkj.com/shop_new/jyj_pingtai?user_id=1526&invitation_code=MS1526
         * share_img : img
         */

        private String share_detail;
        private String share_title;
        private String share_url;
        private String share_img;

        public String getShare_detail() {
            return share_detail;
        }

        public void setShare_detail(String share_detail) {
            this.share_detail = share_detail;
        }

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getShare_img() {
            return share_img;
        }

        public void setShare_img(String share_img) {
            this.share_img = share_img;
        }
    }
}
