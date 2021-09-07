package com.yiyang.cn.model;

import java.util.List;

public class DaLiBaoListModel {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"sys_type_value1":"488.00","sys_type_value3":"","sys_type_text6":"","sys_type_value2":"79.00","sys_type_text7":"","sys_type_value5":"","sys_type_value4":"","ticket_list":[{"coupon_two_html_url":"","sys_type_value1":"3次","coupon_two_img_url":"","sys_type_name":"二甲医院全年免费体检","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10572","coupon_name":"二甲医院全年免费体检(不限次数)"},{"coupon_two_html_url":"","sys_type_value1":"5张","coupon_two_img_url":"","sys_type_name":"KTV免费券","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10564","coupon_name":"东北三省旅游景点门票（100张不限次数门票）"},{"coupon_two_html_url":"","sys_type_value1":"10张","coupon_two_img_url":"","sys_type_name":"洗浴门票券","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10565","coupon_name":"全国加油省钱（覆盖三万多家，中石油中石化在列直降1.9元）"},{"coupon_two_html_url":"","sys_type_value1":"10张","coupon_two_img_url":"","sys_type_name":"洗车免费券","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10570","coupon_name":"大型洗浴中心门票（赠送10张）"},{"coupon_two_html_url":"","sys_type_value1":"100张","coupon_two_img_url":"","sys_type_name":"东北三省旅游景点门票","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10563","coupon_name":"KTV包房或门票（200家免费赠）"},{"coupon_two_html_url":"","sys_type_value1":"1张","coupon_two_img_url":"","sys_type_name":"300元加油券","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10567","coupon_name":"免费洗车（免费五次仅限道里区七家并不断增加汽车网点）"},{"coupon_two_html_url":"","sys_type_value1":"7次","coupon_two_img_url":"","sys_type_name":"家教1对1上门免费上课","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10569","coupon_name":"汽车修配厂（免费充气，免费检测线路，免费检测油路，免费检测底盘）"},{"coupon_two_html_url":"","sys_type_value1":"1次","coupon_two_img_url":"","sys_type_name":"0～25岁免费写真10张","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10571","coupon_name":"学霸笔记免费下载（初高中）"},{"coupon_two_html_url":"","sys_type_value1":"10张","coupon_two_img_url":"","sys_type_name":"肯德基9折优惠券","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10568","coupon_name":"0-25岁每年免费赠送10张写真(如果是0岁可以送到25岁)"},{"coupon_two_html_url":"","sys_type_value1":"10次","coupon_two_img_url":"","sys_type_name":"游泳馆健身房（免费体验）","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10562","coupon_name":"游泳馆健身房（免费体验）"},{"coupon_two_html_url":"","sys_type_value1":"10次","coupon_two_img_url":"","sys_type_name":"商城即返现金(自买省分享赚）","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10560","coupon_name":"商城即返现金(自买省分享赚）"},{"coupon_two_html_url":"","sys_type_value1":"10次","coupon_two_img_url":"","sys_type_name":"饭店吃饭即得红包,买产品即送红包","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10561","coupon_name":"饭店吃饭即得红包,买产品即送红包"},{"coupon_two_html_url":"","sys_type_value1":"10次","coupon_two_img_url":"","sys_type_name":"肯德基麦当劳星巴克（打折券免费送）","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10566","coupon_name":"肯德基麦当劳星巴克（打折券免费送）"},{"coupon_two_html_url":"","sys_type_value1":"10次","coupon_two_img_url":"","sys_type_name":"吃穿住行都省钱实现躺赚","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10573","coupon_name":"吃穿住行都省钱实现躺赚"}]}]
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
         * sys_type_value1 : 488.00
         * sys_type_value3 :
         * sys_type_text6 :
         * sys_type_value2 : 79.00
         * sys_type_text7 :
         * sys_type_value5 :
         * sys_type_value4 :
         * ticket_list : [{"coupon_two_html_url":"","sys_type_value1":"3次","coupon_two_img_url":"","sys_type_name":"二甲医院全年免费体检","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10572","coupon_name":"二甲医院全年免费体检(不限次数)"},{"coupon_two_html_url":"","sys_type_value1":"5张","coupon_two_img_url":"","sys_type_name":"KTV免费券","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10564","coupon_name":"东北三省旅游景点门票（100张不限次数门票）"},{"coupon_two_html_url":"","sys_type_value1":"10张","coupon_two_img_url":"","sys_type_name":"洗浴门票券","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10565","coupon_name":"全国加油省钱（覆盖三万多家，中石油中石化在列直降1.9元）"},{"coupon_two_html_url":"","sys_type_value1":"10张","coupon_two_img_url":"","sys_type_name":"洗车免费券","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10570","coupon_name":"大型洗浴中心门票（赠送10张）"},{"coupon_two_html_url":"","sys_type_value1":"100张","coupon_two_img_url":"","sys_type_name":"东北三省旅游景点门票","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10563","coupon_name":"KTV包房或门票（200家免费赠）"},{"coupon_two_html_url":"","sys_type_value1":"1张","coupon_two_img_url":"","sys_type_name":"300元加油券","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10567","coupon_name":"免费洗车（免费五次仅限道里区七家并不断增加汽车网点）"},{"coupon_two_html_url":"","sys_type_value1":"7次","coupon_two_img_url":"","sys_type_name":"家教1对1上门免费上课","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10569","coupon_name":"汽车修配厂（免费充气，免费检测线路，免费检测油路，免费检测底盘）"},{"coupon_two_html_url":"","sys_type_value1":"1次","coupon_two_img_url":"","sys_type_name":"0～25岁免费写真10张","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10571","coupon_name":"学霸笔记免费下载（初高中）"},{"coupon_two_html_url":"","sys_type_value1":"10张","coupon_two_img_url":"","sys_type_name":"肯德基9折优惠券","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10568","coupon_name":"0-25岁每年免费赠送10张写真(如果是0岁可以送到25岁)"},{"coupon_two_html_url":"","sys_type_value1":"10次","coupon_two_img_url":"","sys_type_name":"游泳馆健身房（免费体验）","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10562","coupon_name":"游泳馆健身房（免费体验）"},{"coupon_two_html_url":"","sys_type_value1":"10次","coupon_two_img_url":"","sys_type_name":"商城即返现金(自买省分享赚）","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10560","coupon_name":"商城即返现金(自买省分享赚）"},{"coupon_two_html_url":"","sys_type_value1":"10次","coupon_two_img_url":"","sys_type_name":"饭店吃饭即得红包,买产品即送红包","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10561","coupon_name":"饭店吃饭即得红包,买产品即送红包"},{"coupon_two_html_url":"","sys_type_value1":"10次","coupon_two_img_url":"","sys_type_name":"肯德基麦当劳星巴克（打折券免费送）","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10566","coupon_name":"肯德基麦当劳星巴克（打折券免费送）"},{"coupon_two_html_url":"","sys_type_value1":"10次","coupon_two_img_url":"","sys_type_name":"吃穿住行都省钱实现躺赚","coupon_icon_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10573","coupon_name":"吃穿住行都省钱实现躺赚"}]
         */

        private String sys_type_value1;
        private String sys_type_value3;
        private String sys_type_text6;
        private String sys_type_value2;
        private String sys_type_text7;
        private String sys_type_value5;
        private String sys_type_value4;
        private List<TicketListBean> ticket_list;

        public String getSys_type_value1() {
            return sys_type_value1;
        }

        public void setSys_type_value1(String sys_type_value1) {
            this.sys_type_value1 = sys_type_value1;
        }

        public String getSys_type_value3() {
            return sys_type_value3;
        }

        public void setSys_type_value3(String sys_type_value3) {
            this.sys_type_value3 = sys_type_value3;
        }

        public String getSys_type_text6() {
            return sys_type_text6;
        }

        public void setSys_type_text6(String sys_type_text6) {
            this.sys_type_text6 = sys_type_text6;
        }

        public String getSys_type_value2() {
            return sys_type_value2;
        }

        public void setSys_type_value2(String sys_type_value2) {
            this.sys_type_value2 = sys_type_value2;
        }

        public String getSys_type_text7() {
            return sys_type_text7;
        }

        public void setSys_type_text7(String sys_type_text7) {
            this.sys_type_text7 = sys_type_text7;
        }

        public String getSys_type_value5() {
            return sys_type_value5;
        }

        public void setSys_type_value5(String sys_type_value5) {
            this.sys_type_value5 = sys_type_value5;
        }

        public String getSys_type_value4() {
            return sys_type_value4;
        }

        public void setSys_type_value4(String sys_type_value4) {
            this.sys_type_value4 = sys_type_value4;
        }

        public List<TicketListBean> getTicket_list() {
            return ticket_list;
        }

        public void setTicket_list(List<TicketListBean> ticket_list) {
            this.ticket_list = ticket_list;
        }

        public static class TicketListBean {
            /**
             * coupon_two_html_url :
             * sys_type_value1 : 3次
             * coupon_two_img_url :
             * sys_type_name : 二甲医院全年免费体检
             * coupon_icon_img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10572
             * coupon_name : 二甲医院全年免费体检(不限次数)
             */

            private String coupon_two_html_url;
            private String sys_type_value1;
            private String coupon_two_img_url;
            private String sys_type_name;
            private String coupon_icon_img_url;
            private String coupon_name;

            public String getCoupon_two_name() {
                return coupon_two_name;
            }

            public void setCoupon_two_name(String coupon_two_name) {
                this.coupon_two_name = coupon_two_name;
            }

            private String coupon_two_name;

            public String getCoupon_two_html_url() {
                return coupon_two_html_url;
            }

            public void setCoupon_two_html_url(String coupon_two_html_url) {
                this.coupon_two_html_url = coupon_two_html_url;
            }

            public String getSys_type_value1() {
                return sys_type_value1;
            }

            public void setSys_type_value1(String sys_type_value1) {
                this.sys_type_value1 = sys_type_value1;
            }

            public String getCoupon_two_img_url() {
                return coupon_two_img_url;
            }

            public void setCoupon_two_img_url(String coupon_two_img_url) {
                this.coupon_two_img_url = coupon_two_img_url;
            }

            public String getSys_type_name() {
                return sys_type_name;
            }

            public void setSys_type_name(String sys_type_name) {
                this.sys_type_name = sys_type_name;
            }

            public String getCoupon_icon_img_url() {
                return coupon_icon_img_url;
            }

            public void setCoupon_icon_img_url(String coupon_icon_img_url) {
                this.coupon_icon_img_url = coupon_icon_img_url;
            }

            public String getCoupon_name() {
                return coupon_name;
            }

            public void setCoupon_name(String coupon_name) {
                this.coupon_name = coupon_name;
            }
        }
    }
}
