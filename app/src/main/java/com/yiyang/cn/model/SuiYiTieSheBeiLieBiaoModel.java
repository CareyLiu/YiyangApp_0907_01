package com.yiyang.cn.model;

import java.util.List;

public class SuiYiTieSheBeiLieBiaoModel {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 17
     * data : [{"family_id":"17","device_name":"客厅灯","device_type_pic":"https://jyj-znjj.oss-cn-hangzhou.aliyuncs.com/images/pic_lamp_off.png","device_ccid":"01010108","device_ccid_up":"jjjjjjjjjjjjjjjjjjjjjj11"},{"family_id":"17","device_name":"灯2","device_type_pic":"https://jyj-znjj.oss-cn-hangzhou.aliyuncs.com/images/pic_lamp_off.png","device_ccid":"01010109","device_ccid_up":"jjjjjjjjjjjjjjjjjjjjjj11"},{"family_id":"17","device_name":"卧室灯","device_type_pic":"https://jyj-znjj.oss-cn-hangzhou.aliyuncs.com/images/pic_lamp_off.png","device_ccid":"0101010A","device_ccid_up":"jjjjjjjjjjjjjjjjjjjjjj11"},{"family_id":"17","device_name":"插座","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10530","device_ccid":"02010101","device_ccid_up":"jjjjjjjjjjjjjjjjjjjjjj11"},{"family_id":"17","device_name":"插座2","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10530","device_ccid":"02010102","device_ccid_up":"jjjjjjjjjjjjjjjjjjjjjj11"},{"family_id":"17","device_name":"插座3","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10530","device_ccid":"02010103","device_ccid_up":"jjjjjjjjjjjjjjjjjjjjjj11"},{"family_id":"17","device_name":"灯3","device_type_pic":"https://jyj-znjj.oss-cn-hangzhou.aliyuncs.com/images/pic_lamp_off.png","device_ccid":"0101010B","device_ccid_up":"jjjjjjjjjjjjjjjjjjjjjj11"},{"family_id":"19","device_name":"窗帘电机","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_ccid":"16010101","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"},{"family_id":"19","device_name":"灯","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_ccid":"01010108","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"},{"family_id":"19","device_name":"灯2","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_ccid":"01010109","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"},{"family_id":"19","device_name":"灯3","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_ccid":"0101010A","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"},{"family_id":"19","device_name":"插座/插排","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_ccid":"02010101","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"},{"family_id":"8","device_name":"灯","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_ccid":"01010108","device_ccid_up":"aaaaaaaaaaaaaaaa80140018"},{"family_id":"8","device_name":"灯2","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_ccid":"01010109","device_ccid_up":"aaaaaaaaaaaaaaaa80140018"},{"family_id":"8","device_name":"灯3","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_ccid":"0101010A","device_ccid_up":"aaaaaaaaaaaaaaaa80140018"},{"family_id":"8","device_name":"窗帘电机","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_ccid":"16010101","device_ccid_up":"aaaaaaaaaaaaaaaa80140018"},{"family_id":"8","device_name":"插座/插排","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","device_ccid":"02010101","device_ccid_up":"aaaaaaaaaaaaaaaa80140018"}]
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
         * family_id : 17
         * device_name : 客厅灯
         * device_type_pic : https://jyj-znjj.oss-cn-hangzhou.aliyuncs.com/images/pic_lamp_off.png
         * device_ccid : 01010108
         * device_ccid_up : jjjjjjjjjjjjjjjjjjjjjj11
         */

        private String family_id;
        private String device_name;
        private String device_type_pic;
        private String device_ccid;
        private String device_ccid_up;
        public String flag = "0";

        public String getFamily_id() {
            return family_id;
        }

        public void setFamily_id(String family_id) {
            this.family_id = family_id;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_type_pic() {
            return device_type_pic;
        }

        public void setDevice_type_pic(String device_type_pic) {
            this.device_type_pic = device_type_pic;
        }

        public String getDevice_ccid() {
            return device_ccid;
        }

        public void setDevice_ccid(String device_ccid) {
            this.device_ccid = device_ccid;
        }

        public String getDevice_ccid_up() {
            return device_ccid_up;
        }

        public void setDevice_ccid_up(String device_ccid_up) {
            this.device_ccid_up = device_ccid_up;
        }
    }
}
