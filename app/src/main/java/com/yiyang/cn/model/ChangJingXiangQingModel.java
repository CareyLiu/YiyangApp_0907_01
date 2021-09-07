package com.yiyang.cn.model;

import java.util.List;

public class ChangJingXiangQingModel  {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"condition_list":[{"week_7":"","implement_detail":"只执行一次 16:56","week_6":"","week_5":"","week_4":"","scene_oper_id":"0","week_3":"","week_2":"","week_1":"","week_time_begin":"16:56","week_time_oper":"5","scene_cond_type":"2","device_name":"定时","img_url":"http://bz-goods.oss-cn-hangzhou.aliyuncs.com/addchangjing_icon_time.png"}],"scene_type":"2","scene_title":"吃饭定时","scene_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11982","implement_list":[{"device_name":"灯","implement_detail":"开启","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","scene_oper_id":"23"}]}]
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
         * condition_list : [{"week_7":"","implement_detail":"只执行一次 16:56","week_6":"","week_5":"","week_4":"","scene_oper_id":"0","week_3":"","week_2":"","week_1":"","week_time_begin":"16:56","week_time_oper":"5","scene_cond_type":"2","device_name":"定时","img_url":"http://bz-goods.oss-cn-hangzhou.aliyuncs.com/addchangjing_icon_time.png"}]
         * scene_type : 2
         * scene_title : 吃饭定时
         * scene_pic : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11982
         * implement_list : [{"device_name":"灯","implement_detail":"开启","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","scene_oper_id":"23"}]
         */

        private String scene_type;
        private String scene_title;
        private String scene_pic;
        private List<ConditionListBean> condition_list;
        private List<ImplementListBean> implement_list;

        public String getScene_type() {
            return scene_type;
        }

        public void setScene_type(String scene_type) {
            this.scene_type = scene_type;
        }

        public String getScene_title() {
            return scene_title;
        }

        public void setScene_title(String scene_title) {
            this.scene_title = scene_title;
        }

        public String getScene_pic() {
            return scene_pic;
        }

        public void setScene_pic(String scene_pic) {
            this.scene_pic = scene_pic;
        }

        public List<ConditionListBean> getCondition_list() {
            return condition_list;
        }

        public void setCondition_list(List<ConditionListBean> condition_list) {
            this.condition_list = condition_list;
        }

        public List<ImplementListBean> getImplement_list() {
            return implement_list;
        }

        public void setImplement_list(List<ImplementListBean> implement_list) {
            this.implement_list = implement_list;
        }

        public static class ConditionListBean {
            /**
             * week_7 :
             * implement_detail : 只执行一次 16:56
             * week_6 :
             * week_5 :
             * week_4 :
             * scene_oper_id : 0
             * week_3 :
             * week_2 :
             * week_1 :
             * week_time_begin : 16:56
             * week_time_oper : 5
             * scene_cond_type : 2
             * device_name : 定时
             * img_url : http://bz-goods.oss-cn-hangzhou.aliyuncs.com/addchangjing_icon_time.png
             */

            private String week_7;
            private String implement_detail;
            private String week_6;
            private String week_5;
            private String week_4;
            private String scene_oper_id;
            private String week_3;
            private String week_2;
            private String week_1;
            private String week_time_begin;
            private String week_time_oper;
            private String scene_cond_type;
            private String device_name;
            private String img_url;

            public String getWeek_7() {
                return week_7;
            }

            public void setWeek_7(String week_7) {
                this.week_7 = week_7;
            }

            public String getImplement_detail() {
                return implement_detail;
            }

            public void setImplement_detail(String implement_detail) {
                this.implement_detail = implement_detail;
            }

            public String getWeek_6() {
                return week_6;
            }

            public void setWeek_6(String week_6) {
                this.week_6 = week_6;
            }

            public String getWeek_5() {
                return week_5;
            }

            public void setWeek_5(String week_5) {
                this.week_5 = week_5;
            }

            public String getWeek_4() {
                return week_4;
            }

            public void setWeek_4(String week_4) {
                this.week_4 = week_4;
            }

            public String getScene_oper_id() {
                return scene_oper_id;
            }

            public void setScene_oper_id(String scene_oper_id) {
                this.scene_oper_id = scene_oper_id;
            }

            public String getWeek_3() {
                return week_3;
            }

            public void setWeek_3(String week_3) {
                this.week_3 = week_3;
            }

            public String getWeek_2() {
                return week_2;
            }

            public void setWeek_2(String week_2) {
                this.week_2 = week_2;
            }

            public String getWeek_1() {
                return week_1;
            }

            public void setWeek_1(String week_1) {
                this.week_1 = week_1;
            }

            public String getWeek_time_begin() {
                return week_time_begin;
            }

            public void setWeek_time_begin(String week_time_begin) {
                this.week_time_begin = week_time_begin;
            }

            public String getWeek_time_oper() {
                return week_time_oper;
            }

            public void setWeek_time_oper(String week_time_oper) {
                this.week_time_oper = week_time_oper;
            }

            public String getScene_cond_type() {
                return scene_cond_type;
            }

            public void setScene_cond_type(String scene_cond_type) {
                this.scene_cond_type = scene_cond_type;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }

        public static class ImplementListBean {
            /**
             * device_name : 灯
             * implement_detail : 开启
             * img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920
             * scene_oper_id : 23
             */

            private String device_name;
            private String implement_detail;
            private String img_url;
            private String scene_oper_id;

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getImplement_detail() {
                return implement_detail;
            }

            public void setImplement_detail(String implement_detail) {
                this.implement_detail = implement_detail;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getScene_oper_id() {
                return scene_oper_id;
            }

            public void setScene_oper_id(String scene_oper_id) {
                this.scene_oper_id = scene_oper_id;
            }
        }
    }
}
