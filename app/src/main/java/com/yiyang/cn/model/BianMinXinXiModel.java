package com.yiyang.cn.model;

import java.util.List;

public class BianMinXinXiModel {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"area_name":"","ir_inst_close_time":"","ir_industry_category":"","ir_personnal_name":"","ir_user_phone":"18249030297","area_id":"","ir_id":"14","ir_inst_notice":"","ir_personnal_img_id":"","ir_column_type":"1797","ir_audit_state":"2","ir_inst_open_time":"","ir_user_id":"499","ir_contact_phone":"15248494682","ir_personnal_identity_up_id":"","ir_industry_type_name":"","ir_personnal_identity_down":"","create_time":"2021-03-26","meter":"0.6","ir_manage_text":"","ir_contact_name":"主光源","province_name":"","ir_inst_video_url":"","ir_inst_settled_time":"","ir_personnal_identity_up":"","ir_personnal_identity_down_id":"","ir_validity":"地暖出租","city_id":"","imgList":[{"ir_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016","create_time":"2021-03-26","ir_rotation_img_id":"37","width":"124","ir_img_id":"12016","ir_id":"14","height":"124"},{"ir_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12017","create_time":"2021-03-26","ir_rotation_img_id":"38","width":"124","ir_img_id":"12017","ir_id":"14","height":"124"},{"ir_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12018","create_time":"2021-03-26","ir_rotation_img_id":"39","width":"124","ir_img_id":"12018","ir_id":"14","height":"124"}],"ir_personnal_img_url":"","ir_wx_number":"656565656","ir_inst_begin_time":"","ir_title":"专业清洗地暖以及家电","service_type_list":"","city_name":"","ir_personnal_profession":"","ir_inst_end_time":"","ir_industry_category_name":"","ir_inst_name":"","addr":"黑龙江哈尔滨","ir_column_category":"1798","ir_column_type_name":"出租","ir_inst_device":"","ir_industry_type":"","ir_inst_logo_id":"","ir_column_category_name":"出租","province_id":"","ir_type":"3","x":"45.662479","y":"126.611618","ir_inst_logo":"","ir_agio":""}]
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
         * area_name :
         * ir_inst_close_time :
         * ir_industry_category :
         * ir_personnal_name :
         * ir_user_phone : 18249030297
         * area_id :
         * ir_id : 14
         * ir_inst_notice :
         * ir_personnal_img_id :
         * ir_column_type : 1797
         * ir_audit_state : 2
         * ir_inst_open_time :
         * ir_user_id : 499
         * ir_contact_phone : 15248494682
         * ir_personnal_identity_up_id :
         * ir_industry_type_name :
         * ir_personnal_identity_down :
         * create_time : 2021-03-26
         * meter : 0.6
         * ir_manage_text :
         * ir_contact_name : 主光源
         * province_name :
         * ir_inst_video_url :
         * ir_inst_settled_time :
         * ir_personnal_identity_up :
         * ir_personnal_identity_down_id :
         * ir_validity : 地暖出租
         * city_id :
         * imgList : [{"ir_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016","create_time":"2021-03-26","ir_rotation_img_id":"37","width":"124","ir_img_id":"12016","ir_id":"14","height":"124"},{"ir_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12017","create_time":"2021-03-26","ir_rotation_img_id":"38","width":"124","ir_img_id":"12017","ir_id":"14","height":"124"},{"ir_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12018","create_time":"2021-03-26","ir_rotation_img_id":"39","width":"124","ir_img_id":"12018","ir_id":"14","height":"124"}]
         * ir_personnal_img_url :
         * ir_wx_number : 656565656
         * ir_inst_begin_time :
         * ir_title : 专业清洗地暖以及家电
         * service_type_list :
         * city_name :
         * ir_personnal_profession :
         * ir_inst_end_time :
         * ir_industry_category_name :
         * ir_inst_name :
         * addr : 黑龙江哈尔滨
         * ir_column_category : 1798
         * ir_column_type_name : 出租
         * ir_inst_device :
         * ir_industry_type :
         * ir_inst_logo_id :
         * ir_column_category_name : 出租
         * province_id :
         * ir_type : 3
         * x : 45.662479
         * y : 126.611618
         * ir_inst_logo :
         * ir_agio :
         */

        private String area_name;
        private String ir_inst_close_time;
        private String ir_industry_category;
        private String ir_personnal_name;
        private String ir_user_phone;
        private String area_id;
        private String ir_id;
        private String ir_inst_notice;
        private String ir_personnal_img_id;
        private String ir_column_type;
        private String ir_audit_state;
        private String ir_inst_open_time;
        private String ir_user_id;
        private String ir_contact_phone;
        private String ir_personnal_identity_up_id;
        private String ir_industry_type_name;
        private String ir_personnal_identity_down;
        private String create_time;
        private String meter;
        private String ir_manage_text;
        private String ir_contact_name;
        private String province_name;
        private String ir_inst_video_url;
        private String ir_inst_settled_time;
        private String ir_personnal_identity_up;
        private String ir_personnal_identity_down_id;
        private String ir_validity;
        private String city_id;
        private String ir_personnal_img_url;
        private String ir_wx_number;
        private String ir_inst_begin_time;
        private String ir_title;
        private String service_type_list;
        private String city_name;
        private String ir_personnal_profession;
        private String ir_inst_end_time;
        private String ir_industry_category_name;
        private String ir_inst_name;
        private String addr;
        private String ir_column_category;
        private String ir_column_type_name;
        private String ir_inst_device;
        private String ir_industry_type;
        private String ir_inst_logo_id;
        private String ir_column_category_name;
        private String province_id;
        private String ir_type;
        private String x;
        private String y;
        private String ir_inst_logo;
        private String ir_agio;
        private List<ImgListBean> imgList;

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getIr_inst_close_time() {
            return ir_inst_close_time;
        }

        public void setIr_inst_close_time(String ir_inst_close_time) {
            this.ir_inst_close_time = ir_inst_close_time;
        }

        public String getIr_industry_category() {
            return ir_industry_category;
        }

        public void setIr_industry_category(String ir_industry_category) {
            this.ir_industry_category = ir_industry_category;
        }

        public String getIr_personnal_name() {
            return ir_personnal_name;
        }

        public void setIr_personnal_name(String ir_personnal_name) {
            this.ir_personnal_name = ir_personnal_name;
        }

        public String getIr_user_phone() {
            return ir_user_phone;
        }

        public void setIr_user_phone(String ir_user_phone) {
            this.ir_user_phone = ir_user_phone;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getIr_id() {
            return ir_id;
        }

        public void setIr_id(String ir_id) {
            this.ir_id = ir_id;
        }

        public String getIr_inst_notice() {
            return ir_inst_notice;
        }

        public void setIr_inst_notice(String ir_inst_notice) {
            this.ir_inst_notice = ir_inst_notice;
        }

        public String getIr_personnal_img_id() {
            return ir_personnal_img_id;
        }

        public void setIr_personnal_img_id(String ir_personnal_img_id) {
            this.ir_personnal_img_id = ir_personnal_img_id;
        }

        public String getIr_column_type() {
            return ir_column_type;
        }

        public void setIr_column_type(String ir_column_type) {
            this.ir_column_type = ir_column_type;
        }

        public String getIr_audit_state() {
            return ir_audit_state;
        }

        public void setIr_audit_state(String ir_audit_state) {
            this.ir_audit_state = ir_audit_state;
        }

        public String getIr_inst_open_time() {
            return ir_inst_open_time;
        }

        public void setIr_inst_open_time(String ir_inst_open_time) {
            this.ir_inst_open_time = ir_inst_open_time;
        }

        public String getIr_user_id() {
            return ir_user_id;
        }

        public void setIr_user_id(String ir_user_id) {
            this.ir_user_id = ir_user_id;
        }

        public String getIr_contact_phone() {
            return ir_contact_phone;
        }

        public void setIr_contact_phone(String ir_contact_phone) {
            this.ir_contact_phone = ir_contact_phone;
        }

        public String getIr_personnal_identity_up_id() {
            return ir_personnal_identity_up_id;
        }

        public void setIr_personnal_identity_up_id(String ir_personnal_identity_up_id) {
            this.ir_personnal_identity_up_id = ir_personnal_identity_up_id;
        }

        public String getIr_industry_type_name() {
            return ir_industry_type_name;
        }

        public void setIr_industry_type_name(String ir_industry_type_name) {
            this.ir_industry_type_name = ir_industry_type_name;
        }

        public String getIr_personnal_identity_down() {
            return ir_personnal_identity_down;
        }

        public void setIr_personnal_identity_down(String ir_personnal_identity_down) {
            this.ir_personnal_identity_down = ir_personnal_identity_down;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getMeter() {
            return meter;
        }

        public void setMeter(String meter) {
            this.meter = meter;
        }

        public String getIr_manage_text() {
            return ir_manage_text;
        }

        public void setIr_manage_text(String ir_manage_text) {
            this.ir_manage_text = ir_manage_text;
        }

        public String getIr_contact_name() {
            return ir_contact_name;
        }

        public void setIr_contact_name(String ir_contact_name) {
            this.ir_contact_name = ir_contact_name;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getIr_inst_video_url() {
            return ir_inst_video_url;
        }

        public void setIr_inst_video_url(String ir_inst_video_url) {
            this.ir_inst_video_url = ir_inst_video_url;
        }

        public String getIr_inst_settled_time() {
            return ir_inst_settled_time;
        }

        public void setIr_inst_settled_time(String ir_inst_settled_time) {
            this.ir_inst_settled_time = ir_inst_settled_time;
        }

        public String getIr_personnal_identity_up() {
            return ir_personnal_identity_up;
        }

        public void setIr_personnal_identity_up(String ir_personnal_identity_up) {
            this.ir_personnal_identity_up = ir_personnal_identity_up;
        }

        public String getIr_personnal_identity_down_id() {
            return ir_personnal_identity_down_id;
        }

        public void setIr_personnal_identity_down_id(String ir_personnal_identity_down_id) {
            this.ir_personnal_identity_down_id = ir_personnal_identity_down_id;
        }

        public String getIr_validity() {
            return ir_validity;
        }

        public void setIr_validity(String ir_validity) {
            this.ir_validity = ir_validity;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getIr_personnal_img_url() {
            return ir_personnal_img_url;
        }

        public void setIr_personnal_img_url(String ir_personnal_img_url) {
            this.ir_personnal_img_url = ir_personnal_img_url;
        }

        public String getIr_wx_number() {
            return ir_wx_number;
        }

        public void setIr_wx_number(String ir_wx_number) {
            this.ir_wx_number = ir_wx_number;
        }

        public String getIr_inst_begin_time() {
            return ir_inst_begin_time;
        }

        public void setIr_inst_begin_time(String ir_inst_begin_time) {
            this.ir_inst_begin_time = ir_inst_begin_time;
        }

        public String getIr_title() {
            return ir_title;
        }

        public void setIr_title(String ir_title) {
            this.ir_title = ir_title;
        }

        public String getService_type_list() {
            return service_type_list;
        }

        public void setService_type_list(String service_type_list) {
            this.service_type_list = service_type_list;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getIr_personnal_profession() {
            return ir_personnal_profession;
        }

        public void setIr_personnal_profession(String ir_personnal_profession) {
            this.ir_personnal_profession = ir_personnal_profession;
        }

        public String getIr_inst_end_time() {
            return ir_inst_end_time;
        }

        public void setIr_inst_end_time(String ir_inst_end_time) {
            this.ir_inst_end_time = ir_inst_end_time;
        }

        public String getIr_industry_category_name() {
            return ir_industry_category_name;
        }

        public void setIr_industry_category_name(String ir_industry_category_name) {
            this.ir_industry_category_name = ir_industry_category_name;
        }

        public String getIr_inst_name() {
            return ir_inst_name;
        }

        public void setIr_inst_name(String ir_inst_name) {
            this.ir_inst_name = ir_inst_name;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getIr_column_category() {
            return ir_column_category;
        }

        public void setIr_column_category(String ir_column_category) {
            this.ir_column_category = ir_column_category;
        }

        public String getIr_column_type_name() {
            return ir_column_type_name;
        }

        public void setIr_column_type_name(String ir_column_type_name) {
            this.ir_column_type_name = ir_column_type_name;
        }

        public String getIr_inst_device() {
            return ir_inst_device;
        }

        public void setIr_inst_device(String ir_inst_device) {
            this.ir_inst_device = ir_inst_device;
        }

        public String getIr_industry_type() {
            return ir_industry_type;
        }

        public void setIr_industry_type(String ir_industry_type) {
            this.ir_industry_type = ir_industry_type;
        }

        public String getIr_inst_logo_id() {
            return ir_inst_logo_id;
        }

        public void setIr_inst_logo_id(String ir_inst_logo_id) {
            this.ir_inst_logo_id = ir_inst_logo_id;
        }

        public String getIr_column_category_name() {
            return ir_column_category_name;
        }

        public void setIr_column_category_name(String ir_column_category_name) {
            this.ir_column_category_name = ir_column_category_name;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getIr_type() {
            return ir_type;
        }

        public void setIr_type(String ir_type) {
            this.ir_type = ir_type;
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

        public String getIr_inst_logo() {
            return ir_inst_logo;
        }

        public void setIr_inst_logo(String ir_inst_logo) {
            this.ir_inst_logo = ir_inst_logo;
        }

        public String getIr_agio() {
            return ir_agio;
        }

        public void setIr_agio(String ir_agio) {
            this.ir_agio = ir_agio;
        }

        public List<ImgListBean> getImgList() {
            return imgList;
        }

        public void setImgList(List<ImgListBean> imgList) {
            this.imgList = imgList;
        }

        public static class ImgListBean {
            /**
             * ir_img_url : https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016
             * create_time : 2021-03-26
             * ir_rotation_img_id : 37
             * width : 124
             * ir_img_id : 12016
             * ir_id : 14
             * height : 124
             */

            private String ir_img_url;
            private String create_time;
            private String ir_rotation_img_id;
            private String width;
            private String ir_img_id;
            private String ir_id;
            private String height;

            public String getIr_img_url() {
                return ir_img_url;
            }

            public void setIr_img_url(String ir_img_url) {
                this.ir_img_url = ir_img_url;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getIr_rotation_img_id() {
                return ir_rotation_img_id;
            }

            public void setIr_rotation_img_id(String ir_rotation_img_id) {
                this.ir_rotation_img_id = ir_rotation_img_id;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getIr_img_id() {
                return ir_img_id;
            }

            public void setIr_img_id(String ir_img_id) {
                this.ir_img_id = ir_img_id;
            }

            public String getIr_id() {
                return ir_id;
            }

            public void setIr_id(String ir_id) {
                this.ir_id = ir_id;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }
        }
    }
}
