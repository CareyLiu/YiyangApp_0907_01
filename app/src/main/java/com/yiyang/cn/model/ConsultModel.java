package com.yiyang.cn.model;

import java.util.List;

public class ConsultModel {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 4
     * data : [{"user_car_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9251","service_form_id":"15","of_user_id_car":"455","sub_user_name":"lsz","of_user_accid":"wit_455","create_time":"2019-07-30","state_name":"进行中","sub_accid":"wit_sub_177","user_name_car":"Sunshine ","plate_number":"黑A6L168G","state":"2","addr":"哈尔滨市南岗区电缆街170"},{"user_car_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9256","service_form_id":"14","of_user_id_car":"499","sub_user_name":"小岳岳","of_user_accid":"wit_499","create_time":"2019-07-01","state_name":"进行中","sub_accid":"wit_sub_177","user_name_car":"lsz","plate_number":"黑E6666654","state":"2","addr":"哈尔滨市南岗区电缆街170"},{"user_car_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","service_form_id":"10","of_user_id_car":"660","sub_user_name":"小岳岳","of_user_accid":"wit_660","create_time":"2019-05-29","state_name":"进行中","sub_accid":"wit_sub_177","user_name_car":"加热器","plate_number":"黑A06599","state":"2","addr":""},{"user_car_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","service_form_id":"9","of_user_id_car":"537","sub_user_name":"小岳岳","of_user_accid":"wit_537","create_time":"2019-05-29","state_name":"进行中","sub_accid":"wit_sub_177","user_name_car":"未设置","plate_number":"黑A12345","state":"2","addr":"哈尔滨市南岗区电缆街170"}]
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
         * user_car_img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9251
         * service_form_id : 15
         * of_user_id_car : 455
         * sub_user_name : lsz
         * of_user_accid : wit_455
         * create_time : 2019-07-30
         * state_name : 进行中
         * sub_accid : wit_sub_177
         * user_name_car : Sunshine
         * plate_number : 黑A6L168G
         * state : 2
         * addr : 哈尔滨市南岗区电缆街170
         */

        private String user_car_img_url;
        private String service_form_id;
        private String of_user_id_car;
        private String sub_user_name;
        private String of_user_accid;
        private String create_time;
        private String state_name;
        private String sub_accid;
        private String user_name_car;
        private String plate_number;
        private String state;
        private String addr;

        public String getError_text() {
            return error_text;
        }

        public void setError_text(String error_text) {
            this.error_text = error_text;
        }

        private String error_text = "无故障信息";

        public String getUser_car_img_url() {
            return user_car_img_url;
        }

        public void setUser_car_img_url(String user_car_img_url) {
            this.user_car_img_url = user_car_img_url;
        }

        public String getService_form_id() {
            return service_form_id;
        }

        public void setService_form_id(String service_form_id) {
            this.service_form_id = service_form_id;
        }

        public String getOf_user_id_car() {
            return of_user_id_car;
        }

        public void setOf_user_id_car(String of_user_id_car) {
            this.of_user_id_car = of_user_id_car;
        }

        public String getSub_user_name() {
            return sub_user_name;
        }

        public void setSub_user_name(String sub_user_name) {
            this.sub_user_name = sub_user_name;
        }

        public String getOf_user_accid() {
            return of_user_accid;
        }

        public void setOf_user_accid(String of_user_accid) {
            this.of_user_accid = of_user_accid;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getState_name() {
            return state_name;
        }

        public void setState_name(String state_name) {
            this.state_name = state_name;
        }

        public String getSub_accid() {
            return sub_accid;
        }

        public void setSub_accid(String sub_accid) {
            this.sub_accid = sub_accid;
        }

        public String getUser_name_car() {
            return user_name_car;
        }

        public void setUser_name_car(String user_name_car) {
            this.user_name_car = user_name_car;
        }

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }
    }
}
