package com.yiyang.cn.activity.tongcheng58.model;

import java.util.List;

public class TcBianminModel {


    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 4
     * data : [{"irNoticeList":[{"ir_title":"专业清洗地暖以及家电","ir_audit_state":"2","meter":"6608.9","x":"45.662479","y":"126.611618","notice_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016","ir_contact_phone":"15248494682","addr":"黑龙江哈尔滨","ir_id":"14"},{"ir_title":"专业清洗地暖以及家电","ir_audit_state":"2","meter":"6608.9","x":"45.662479","y":"126.611618","notice_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016","ir_contact_phone":"15248494682","addr":"黑龙江哈尔滨","ir_id":"10"},{"ir_title":"专业清洗地暖以及家电","ir_audit_state":"2","meter":"6608.9","x":"45.662479","y":"126.611618","notice_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016","ir_contact_phone":"15248494682","addr":"黑龙江哈尔滨","ir_id":"9"},{"ir_title":"买鞋修鞋","ir_audit_state":"2","meter":"6608.9","x":"45.662479","y":"126.611618","notice_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016","ir_contact_phone":"15248494682","addr":"","ir_id":"8"}],"irNoticeTypeList":[{"ir_column_type":"1852","ir_column_type_name":"附近"},{"ir_column_type":"1805","ir_column_type_name":"二手物品"},{"ir_column_type":"1803","ir_column_type_name":"招聘"},{"ir_column_type":"1801","ir_column_type_name":"卖房"},{"ir_column_type":"1799","ir_column_type_name":"出兑"},{"ir_column_type":"1797","ir_column_type_name":"出租"}]}]
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

    public static class DataBean {
        private List<IrNoticeListBean> irNoticeList;
        private List<IrNoticeTypeListBean> irNoticeTypeList;

        public List<IrNoticeListBean> getIrNoticeList() {
            return irNoticeList;
        }

        public void setIrNoticeList(List<IrNoticeListBean> irNoticeList) {
            this.irNoticeList = irNoticeList;
        }

        public List<IrNoticeTypeListBean> getIrNoticeTypeList() {
            return irNoticeTypeList;
        }

        public void setIrNoticeTypeList(List<IrNoticeTypeListBean> irNoticeTypeList) {
            this.irNoticeTypeList = irNoticeTypeList;
        }

        public static class IrNoticeListBean {
            /**
             * ir_title : 专业清洗地暖以及家电
             * ir_audit_state : 2
             * meter : 6608.9
             * x : 45.662479
             * y : 126.611618
             * notice_img_url : https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016
             * ir_contact_phone : 15248494682
             * addr : 黑龙江哈尔滨
             * ir_id : 14
             */

            private String ir_title;
            private String ir_audit_state;
            private String meter;
            private String x;
            private String y;
            private String notice_img_url;
            private String ir_contact_phone;
            private String addr;
            private String ir_id;

            public String getIr_title() {
                return ir_title;
            }

            public void setIr_title(String ir_title) {
                this.ir_title = ir_title;
            }

            public String getIr_audit_state() {
                return ir_audit_state;
            }

            public void setIr_audit_state(String ir_audit_state) {
                this.ir_audit_state = ir_audit_state;
            }

            public String getMeter() {
                return meter;
            }

            public void setMeter(String meter) {
                this.meter = meter;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getNotice_img_url() {
                return notice_img_url;
            }

            public void setNotice_img_url(String notice_img_url) {
                this.notice_img_url = notice_img_url;
            }

            public String getIr_contact_phone() {
                return ir_contact_phone;
            }

            public void setIr_contact_phone(String ir_contact_phone) {
                this.ir_contact_phone = ir_contact_phone;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getIr_id() {
                return ir_id;
            }

            public void setIr_id(String ir_id) {
                this.ir_id = ir_id;
            }
        }

        public static class IrNoticeTypeListBean {
            /**
             * ir_column_type : 1852
             * ir_column_type_name : 附近
             */

            private String ir_column_type;
            private String ir_column_type_name;

            public String getIr_column_type() {
                return ir_column_type;
            }

            public void setIr_column_type(String ir_column_type) {
                this.ir_column_type = ir_column_type;
            }

            public String getIr_column_type_name() {
                return ir_column_type_name;
            }

            public void setIr_column_type_name(String ir_column_type_name) {
                this.ir_column_type_name = ir_column_type_name;
            }
        }
    }
}
