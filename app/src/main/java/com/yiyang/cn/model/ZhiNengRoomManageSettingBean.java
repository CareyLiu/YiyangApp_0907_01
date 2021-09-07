package com.yiyang.cn.model;

import java.util.List;

public class ZhiNengRoomManageSettingBean {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"device_id":"46","device_ccid":"01010109","device_tem":"-10","device_category_code":"","device_type":"01","ty_room_id":"","wg_device_ccid":"","server_id":"8/","device_category":"01","ty_device_ccid":"","ty_family_id":"","room_name":"大卧室","device_name":"灯二","device_type_pic":"https://jyj-znjj.oss-cn-hangzhou.aliyuncs.com/images/pic_lamp_off.png","online_state":"1","work_state":"2","warn_state":"3","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"},{"device_id":"53","device_ccid":"11010101","device_tem":"-10","device_category_code":"","device_type":"11","ty_room_id":"","wg_device_ccid":"","server_id":"8/","device_category":"01","ty_device_ccid":"","ty_family_id":"","room_name":"默认房间","device_name":"烟雾报警","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11949","online_state":"1","work_state":"3","warn_state":"1","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"},{"device_id":"55","device_ccid":"13010101","device_tem":"-10","device_category_code":"","device_type":"13","ty_room_id":"","wg_device_ccid":"","server_id":"8/","device_category":"01","ty_device_ccid":"","ty_family_id":"","room_name":"默认房间","device_name":"漏水检测","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11943","online_state":"1","work_state":"3","warn_state":"1","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"},{"device_id":"56","device_ccid":"14010101","device_tem":"-10","device_category_code":"","device_type":"14","ty_room_id":"","wg_device_ccid":"","server_id":"8/","device_category":"01","ty_device_ccid":"","ty_family_id":"","room_name":"默认房间","device_name":"雷达监测","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10526","online_state":"1","work_state":"3","warn_state":"1","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"},{"device_id":"57","device_ccid":"15010101","device_tem":"-10","device_category_code":"","device_type":"15","ty_room_id":"","wg_device_ccid":"","server_id":"8/","device_category":"01","ty_device_ccid":"","ty_family_id":"","room_name":"默认房间","device_name":"紧急开关","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11951","online_state":"1","work_state":"3","warn_state":"1","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"},{"device_id":"90","device_ccid":"","device_tem":"-10","device_category_code":"wf_sp","device_type":"sp","ty_room_id":"0","wg_device_ccid":"0","server_id":"","device_category":"aooci0uh0ufwuaey","ty_device_ccid":"6c79635a6b5ad77e14b3vh","ty_family_id":"29283839","room_name":"默认房间","device_name":"智能摄像机","device_type_pic":"https://images.tuyacn.com/smart/icon/bay1590055958952vKQc/6cb1f8029ec7004b4d4270cc64418097.jpg","online_state":"2","work_state":"2","warn_state":"3","device_ccid_up":""},{"room_name":"默认房间","device_name":"三联随意贴","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11973","online_state":"","work_state":"3","warn_state":"3","device_ccid":"08030101","device_type":"08","device_ccid_up":"aaaaaaaaaaaaaaaa90140018"}]
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
         * device_id : 46
         * device_ccid : 01010109
         * device_tem : -10
         * device_category_code :
         * device_type : 01
         * ty_room_id :
         * wg_device_ccid :
         * server_id : 8/
         * device_category : 01
         * ty_device_ccid :
         * ty_family_id :
         * room_name : 大卧室
         * device_name : 灯二
         * device_type_pic : https://jyj-znjj.oss-cn-hangzhou.aliyuncs.com/images/pic_lamp_off.png
         * online_state : 1
         * work_state : 2
         * warn_state : 3
         * device_ccid_up : aaaaaaaaaaaaaaaa90140018
         */

        private String device_id;
        private String device_ccid;
        private String device_tem;
        private String device_category_code;
        private String device_type;
        private String ty_room_id;
        private String wg_device_ccid;
        private String server_id;
        private String device_category;
        private String ty_device_ccid;
        private String ty_family_id;
        private String room_name;
        private String device_name;
        private String device_type_pic;
        private String online_state;
        private String work_state;
        private String warn_state;
        private String device_ccid_up;

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getDevice_ccid() {
            return device_ccid;
        }

        public void setDevice_ccid(String device_ccid) {
            this.device_ccid = device_ccid;
        }

        public String getDevice_tem() {
            return device_tem;
        }

        public void setDevice_tem(String device_tem) {
            this.device_tem = device_tem;
        }

        public String getDevice_category_code() {
            return device_category_code;
        }

        public void setDevice_category_code(String device_category_code) {
            this.device_category_code = device_category_code;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getTy_room_id() {
            return ty_room_id;
        }

        public void setTy_room_id(String ty_room_id) {
            this.ty_room_id = ty_room_id;
        }

        public String getWg_device_ccid() {
            return wg_device_ccid;
        }

        public void setWg_device_ccid(String wg_device_ccid) {
            this.wg_device_ccid = wg_device_ccid;
        }

        public String getServer_id() {
            return server_id;
        }

        public void setServer_id(String server_id) {
            this.server_id = server_id;
        }

        public String getDevice_category() {
            return device_category;
        }

        public void setDevice_category(String device_category) {
            this.device_category = device_category;
        }

        public String getTy_device_ccid() {
            return ty_device_ccid;
        }

        public void setTy_device_ccid(String ty_device_ccid) {
            this.ty_device_ccid = ty_device_ccid;
        }

        public String getTy_family_id() {
            return ty_family_id;
        }

        public void setTy_family_id(String ty_family_id) {
            this.ty_family_id = ty_family_id;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
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

        public String getOnline_state() {
            return online_state;
        }

        public void setOnline_state(String online_state) {
            this.online_state = online_state;
        }

        public String getWork_state() {
            return work_state;
        }

        public void setWork_state(String work_state) {
            this.work_state = work_state;
        }

        public String getWarn_state() {
            return warn_state;
        }

        public void setWarn_state(String warn_state) {
            this.warn_state = warn_state;
        }

        public String getDevice_ccid_up() {
            return device_ccid_up;
        }

        public void setDevice_ccid_up(String device_ccid_up) {
            this.device_ccid_up = device_ccid_up;
        }
    }
}
