package com.yiyang.cn.model;

import java.util.List;

public class ZhiNengChangJingIconModel  {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"name":"自定义场景一","week_time_oper":"","id":"1","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_1.png","scene_type":""},{"name":"自定义场景二","week_time_oper":"","id":"2","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_2.png","scene_type":""},{"name":"自定义场景三","week_time_oper":"","id":"3","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_3.png","scene_type":""},{"name":"自定义场景四","week_time_oper":"","id":"4","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_4.png","scene_type":""},{"name":"自定义场景五","week_time_oper":"","id":"5","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_5.png","scene_type":""},{"name":"自定义场景六","week_time_oper":"","id":"6","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_6.png","scene_type":""},{"name":"自定义场景七","week_time_oper":"","id":"7","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_7.png","scene_type":""},{"name":"自定义场景八","week_time_oper":"","id":"8","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_8.png","scene_type":""},{"name":"自定义场景九","week_time_oper":"","id":"9","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_9.png","scene_type":""},{"name":"自定义场景十","week_time_oper":"","id":"10","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_10.png","scene_type":""},{"name":"自定义场景十一","week_time_oper":"","id":"11","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_11.png","scene_type":""},{"name":"自定义场景十二","week_time_oper":"","id":"12","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_12.png","scene_type":""},{"name":"自定义场景十三","week_time_oper":"","id":"13","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_13.png","scene_type":""},{"name":"自定义场景十四","week_time_oper":"","id":"14","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_14.png","scene_type":""},{"name":"自定义场景十五","week_time_oper":"","id":"15","week_time_begin":"","img_url":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_15.png","scene_type":""}]
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
         * name : 自定义场景一
         * week_time_oper :
         * id : 1
         * week_time_begin :
         * img_url : http://znjj-img.oss-cn-hangzhou.aliyuncs.com/changjing_pic_1.png
         * scene_type :
         */

        private String name;
        private String week_time_oper;
        private String id;
        private String week_time_begin;
        private String img_url;
        private String scene_type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWeek_time_oper() {
            return week_time_oper;
        }

        public void setWeek_time_oper(String week_time_oper) {
            this.week_time_oper = week_time_oper;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWeek_time_begin() {
            return week_time_begin;
        }

        public void setWeek_time_begin(String week_time_begin) {
            this.week_time_begin = week_time_begin;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getScene_type() {
            return scene_type;
        }

        public void setScene_type(String scene_type) {
            this.scene_type = scene_type;
        }
    }
}
