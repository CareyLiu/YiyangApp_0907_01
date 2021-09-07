package com.yiyang.cn.model;

import java.util.List;

public class TuiGuangMaModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"invitation_code":"GL1508","display":"0","referral_code_url":"https://shop.hljsdkj.com/shop_new/select?user_id=114n103x109D110x117n99J100E120D117n99g"}]
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
         * invitation_code : GL1508
         * display : 0
         * referral_code_url : https://shop.hljsdkj.com/shop_new/select?user_id=114n103x109D110x117n99J100E120D117n99g
         */

        private String invitation_code;
        private String display;
        private String referral_code_url;

        public String getInvitation_code() {
            return invitation_code;
        }

        public void setInvitation_code(String invitation_code) {
            this.invitation_code = invitation_code;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }

        public String getReferral_code_url() {
            return referral_code_url;
        }

        public void setReferral_code_url(String referral_code_url) {
            this.referral_code_url = referral_code_url;
        }
    }
}
