package com.yiyang.cn.model;

import java.util.List;

public class CheckModel {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"version_text":"-","version_no":"1.5.0","version_update":"1","version_down_url":"http://sd-jyj.oss-cn-hangzhou.aliyuncs.com/juyijia_android_v1.5.apk","create_time":"2020-05-26","isnew":"2","version_text_url":"-"}]
     */

    private String msg_code;
    private String msg;
    private String row_num;
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
        /**
         * version_text : -
         * version_no : 1.5.0
         * version_update : 1
         * version_down_url : http://sd-jyj.oss-cn-hangzhou.aliyuncs.com/juyijia_android_v1.5.apk
         * create_time : 2020-05-26
         * isnew : 2
         * version_text_url : -
         */

        private String version_text;
        private String version_no;
        private String version_update;
        private String version_down_url;
        private String create_time;
        private String isnew;
        private String version_text_url;

        public String getVersion_text() {
            return version_text;
        }

        public void setVersion_text(String version_text) {
            this.version_text = version_text;
        }

        public String getVersion_no() {
            return version_no;
        }

        public void setVersion_no(String version_no) {
            this.version_no = version_no;
        }

        public String getVersion_update() {
            return version_update;
        }

        public void setVersion_update(String version_update) {
            this.version_update = version_update;
        }

        public String getVersion_down_url() {
            return version_down_url;
        }

        public void setVersion_down_url(String version_down_url) {
            this.version_down_url = version_down_url;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIsnew() {
            return isnew;
        }

        public void setIsnew(String isnew) {
            this.isnew = isnew;
        }

        public String getVersion_text_url() {
            return version_text_url;
        }

        public void setVersion_text_url(String version_text_url) {
            this.version_text_url = version_text_url;
        }
    }
}
