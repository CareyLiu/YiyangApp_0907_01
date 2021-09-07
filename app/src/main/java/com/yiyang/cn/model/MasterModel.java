package com.yiyang.cn.model;

import java.util.List;

public class MasterModel {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 3
     * data : [{"of_user_id":"499","user_phone":"18249030297","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9256","plate_number":"阿斯顿·马丁Rapide(黑E6666654)","create_time":"2018-08-31","user_name":"lsz"},{"of_user_id":"537","user_phone":"13091891781","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","plate_number":"玛莎拉蒂总裁(黑A12345)","create_time":"2018-12-22","user_name":"未设置"},{"of_user_id":"660","user_phone":"15204506812","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","plate_number":"奥迪奥迪A3(黑A06599)","create_time":"2019-05-07","user_name":"加热器"}]
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
         * of_user_id : 499
         * user_phone : 18249030297
         * user_img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9256
         * plate_number : 阿斯顿·马丁Rapide(黑E6666654)
         * create_time : 2018-08-31
         * user_name : lsz
         */

        private String of_user_id;
        private String user_phone;
        private String user_img_url;
        private String plate_number;
        private String create_time;
        private String user_name;

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_img_url() {
            return user_img_url;
        }

        public void setUser_img_url(String user_img_url) {
            this.user_img_url = user_img_url;
        }

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
