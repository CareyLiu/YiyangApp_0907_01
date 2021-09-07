package com.yiyang.cn.aakefudan.model;

import java.util.List;

public class ZixunModel {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"worker_state":"2","y_begin":"126.605662","user_car_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10774","service_form_id":"20","error_text":"","list":[{"meter":"6.9","x":"45.710883","y":"126.66782","inst_id":"232","tel":"15244778888","inst_name":"哈尔滨新华夏轮胎商行","type":"1","addr":"哈尔滨新华夏轮胎商行","url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7818"},{"meter":"7.7","x":"45.710733","y":"126.681296","inst_id":"233","tel":"15045856123","inst_name":"天启汽车电器先锋店","type":"1","addr":"天启汽车电器先锋店","url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7817"},{"meter":"3.6","x":"45.638060","y":"126.629939","inst_id":"","tel":"","inst_name":"诚信重汽修理配件","type":"2","addr":"大连北路与102国道交叉口西南100米附近","url":""},{"meter":"6.8","x":"45.686571","y":"126.522668","inst_id":"","tel":"","inst_name":"哈尔滨众兴汽车售后服务站","type":"2","addr":"新发镇机场路216号(胜利加油站院内)","url":""},{"meter":"7.8","x":"45.697240","y":"126.694854","inst_id":"","tel":"","inst_name":"龙信汽车维修配件","type":"2","addr":"进乡街95号","url":""},{"meter":"7.8","x":"45.603573","y":"126.650619","inst_id":"","tel":"","inst_name":"伟东汽车电器修配","type":"2","addr":"兴建街哈飞家园6号楼","url":""},{"meter":"8.1","x":"45.738365","y":"126.615386","inst_id":"","tel":"","inst_name":"天顺修车保养维修站","type":"2","addr":"清明四道街41-3号","url":""},{"meter":"8.4","x":"45.740870","y":"126.615223","inst_id":"","tel":"","inst_name":"宇盛汽车维修有限公司","type":"2","addr":"通达街122号附近","url":""},{"meter":"8.5","x":"45.741406","y":"126.591244","inst_id":"","tel":"","inst_name":"技领汽配维修保养","type":"2","addr":"康安小区4栋2-7技领汽车维修保养","url":""},{"meter":"9.1","x":"45.666237","y":"126.488875","inst_id":"","tel":"","inst_name":"有财汽车配件修理厂","type":"2","addr":"共建路与新企路交叉口西北150米","url":""},{"meter":"9.6","x":"45.739912","y":"126.668911","inst_id":"","tel":"","inst_name":"龙飞汽修洗车美容轮胎(赣水路分店)","type":"2","addr":"六顺街道赣水路2号","url":""},{"meter":"9.6","x":"45.752504","y":"126.601453","inst_id":"","tel":"","inst_name":"中策车空间汽车快捷服务中心(哈尔滨哈药路旗舰店)","type":"2","addr":"建国北四道街4号1层","url":""}],"of_user_state":"2","user_car_id":"30","of_user_accid":"wit_455","action_name":"完成咨询","car_user_name":"孙先生","action":"2","plate_number":"黑A6L168G","servicing_addr":"哈尔滨市南岗区电缆街170号","x_begin":"45.666027"}]
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
        public static Object ListBean;
        /**
         * worker_state : 2
         * y_begin : 126.605662
         * user_car_img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10774
         * service_form_id : 20
         * error_text :
         * list : [{"meter":"6.9","x":"45.710883","y":"126.66782","inst_id":"232","tel":"15244778888","inst_name":"哈尔滨新华夏轮胎商行","type":"1","addr":"哈尔滨新华夏轮胎商行","url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7818"},{"meter":"7.7","x":"45.710733","y":"126.681296","inst_id":"233","tel":"15045856123","inst_name":"天启汽车电器先锋店","type":"1","addr":"天启汽车电器先锋店","url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7817"},{"meter":"3.6","x":"45.638060","y":"126.629939","inst_id":"","tel":"","inst_name":"诚信重汽修理配件","type":"2","addr":"大连北路与102国道交叉口西南100米附近","url":""},{"meter":"6.8","x":"45.686571","y":"126.522668","inst_id":"","tel":"","inst_name":"哈尔滨众兴汽车售后服务站","type":"2","addr":"新发镇机场路216号(胜利加油站院内)","url":""},{"meter":"7.8","x":"45.697240","y":"126.694854","inst_id":"","tel":"","inst_name":"龙信汽车维修配件","type":"2","addr":"进乡街95号","url":""},{"meter":"7.8","x":"45.603573","y":"126.650619","inst_id":"","tel":"","inst_name":"伟东汽车电器修配","type":"2","addr":"兴建街哈飞家园6号楼","url":""},{"meter":"8.1","x":"45.738365","y":"126.615386","inst_id":"","tel":"","inst_name":"天顺修车保养维修站","type":"2","addr":"清明四道街41-3号","url":""},{"meter":"8.4","x":"45.740870","y":"126.615223","inst_id":"","tel":"","inst_name":"宇盛汽车维修有限公司","type":"2","addr":"通达街122号附近","url":""},{"meter":"8.5","x":"45.741406","y":"126.591244","inst_id":"","tel":"","inst_name":"技领汽配维修保养","type":"2","addr":"康安小区4栋2-7技领汽车维修保养","url":""},{"meter":"9.1","x":"45.666237","y":"126.488875","inst_id":"","tel":"","inst_name":"有财汽车配件修理厂","type":"2","addr":"共建路与新企路交叉口西北150米","url":""},{"meter":"9.6","x":"45.739912","y":"126.668911","inst_id":"","tel":"","inst_name":"龙飞汽修洗车美容轮胎(赣水路分店)","type":"2","addr":"六顺街道赣水路2号","url":""},{"meter":"9.6","x":"45.752504","y":"126.601453","inst_id":"","tel":"","inst_name":"中策车空间汽车快捷服务中心(哈尔滨哈药路旗舰店)","type":"2","addr":"建国北四道街4号1层","url":""}]
         * of_user_state : 2
         * user_car_id : 30
         * of_user_accid : wit_455
         * action_name : 完成咨询
         * car_user_name : 孙先生
         * action : 2
         * plate_number : 黑A6L168G
         * servicing_addr : 哈尔滨市南岗区电缆街170号
         * x_begin : 45.666027
         */

        private String worker_state;
        private String y_begin;
        private String user_car_img_url;
        private String service_form_id;
        private String error_text;
        private String of_user_state;
        private String user_car_id;
        private String of_user_accid;
        private String action_name;
        private String car_user_name;
        private String action;
        private String plate_number;
        private String servicing_addr;
        private String x_begin;
        private List<ListBean> list;

        public String getWorker_state() {
            return worker_state;
        }

        public void setWorker_state(String worker_state) {
            this.worker_state = worker_state;
        }

        public String getY_begin() {
            return y_begin;
        }

        public void setY_begin(String y_begin) {
            this.y_begin = y_begin;
        }

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

        public String getError_text() {
            return error_text;
        }

        public void setError_text(String error_text) {
            this.error_text = error_text;
        }

        public String getOf_user_state() {
            return of_user_state;
        }

        public void setOf_user_state(String of_user_state) {
            this.of_user_state = of_user_state;
        }

        public String getUser_car_id() {
            return user_car_id;
        }

        public void setUser_car_id(String user_car_id) {
            this.user_car_id = user_car_id;
        }

        public String getOf_user_accid() {
            return of_user_accid;
        }

        public void setOf_user_accid(String of_user_accid) {
            this.of_user_accid = of_user_accid;
        }

        public String getAction_name() {
            return action_name;
        }

        public void setAction_name(String action_name) {
            this.action_name = action_name;
        }

        public String getCar_user_name() {
            return car_user_name;
        }

        public void setCar_user_name(String car_user_name) {
            this.car_user_name = car_user_name;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }

        public String getServicing_addr() {
            return servicing_addr;
        }

        public void setServicing_addr(String servicing_addr) {
            this.servicing_addr = servicing_addr;
        }

        public String getX_begin() {
            return x_begin;
        }

        public void setX_begin(String x_begin) {
            this.x_begin = x_begin;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * meter : 6.9
             * x : 45.710883
             * y : 126.66782
             * inst_id : 232
             * tel : 15244778888
             * inst_name : 哈尔滨新华夏轮胎商行
             * type : 1
             * addr : 哈尔滨新华夏轮胎商行
             * url : http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7818
             */

            private String meter;
            private String x;
            private String y;
            private String inst_id;
            private String tel;
            private String inst_name;
            private String type;
            private String addr;
            private String url;
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
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

            public String getInst_id() {
                return inst_id;
            }

            public void setInst_id(String inst_id) {
                this.inst_id = inst_id;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getInst_name() {
                return inst_name;
            }

            public void setInst_name(String inst_name) {
                this.inst_name = inst_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
