package com.yiyang.cn.activity.tongcheng58.model;

import java.io.Serializable;
import java.util.List;

public class TcUpLoadModel implements Serializable{

    /**
     * msg_code : 0000
     * msg : 数据上传成功
     * row_num : 1
     * data : [{"img_url":"http://yjn-znjj.oss-cn-hangzhou.aliyuncs.com/20210329104816000001.jpg","img_id":"12052"}]
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

    public static class DataBean implements Serializable {
        /**
         * img_url : http://yjn-znjj.oss-cn-hangzhou.aliyuncs.com/20210329104816000001.jpg
         * img_id : 12052
         */

        private String img_url;
        private String img_id;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }
    }
}
