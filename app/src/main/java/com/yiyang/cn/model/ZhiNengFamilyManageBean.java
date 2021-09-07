package com.yiyang.cn.model;

import java.util.List;

public class ZhiNengFamilyManageBean {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"member_type":"2","area_name":"","family_id":"17","device_num":"15","addr_all":"","area_id":"","province_name":"","room_num":"2","city_name":"","province_id":"","member":[{"member_id":"20","member_type":"1","member_phone":"17645185187","user_name":"神灯科技～孙先生","member_type_name":"管理员"},{"member_id":"25","member_type":"2","member_phone":"15546512682","user_name":"秦朗","member_type_name":"成员"},{"member_id":"38","member_type":"2","member_phone":"18646330633","user_name":"孙先生","member_type_name":"成员"},{"member_id":"39","member_type":"2","member_phone":"18004631006","user_name":"测试员","member_type_name":"成员"}],"addr":"","family_name":"我的爱家","city_id":""}]
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
         * member_type : 2
         * area_name :
         * family_id : 17
         * device_num : 15
         * addr_all :
         * area_id :
         * province_name :
         * room_num : 2
         * city_name :
         * province_id :
         * member : [{"member_id":"20","member_type":"1","member_phone":"17645185187","user_name":"神灯科技～孙先生","member_type_name":"管理员"},{"member_id":"25","member_type":"2","member_phone":"15546512682","user_name":"秦朗","member_type_name":"成员"},{"member_id":"38","member_type":"2","member_phone":"18646330633","user_name":"孙先生","member_type_name":"成员"},{"member_id":"39","member_type":"2","member_phone":"18004631006","user_name":"测试员","member_type_name":"成员"}]
         * addr :
         * family_name : 我的爱家
         * city_id :
         */

        private String member_type;
        private String area_name;
        private String family_id;
        private String device_num;
        private String addr_all;
        private String area_id;
        private String province_name;
        private String room_num;
        private String city_name;
        private String province_id;
        private String addr;
        private String family_name;
        private String city_id;
        private String ty_family_id;
        private List<MemberBean> member;

        public String getTy_family_id() {
            return ty_family_id;
        }

        public void setTy_family_id(String ty_family_id) {
            this.ty_family_id = ty_family_id;
        }

        public String getMember_type() {
            return member_type;
        }

        public void setMember_type(String member_type) {
            this.member_type = member_type;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getFamily_id() {
            return family_id;
        }

        public void setFamily_id(String family_id) {
            this.family_id = family_id;
        }

        public String getDevice_num() {
            return device_num;
        }

        public void setDevice_num(String device_num) {
            this.device_num = device_num;
        }

        public String getAddr_all() {
            return addr_all;
        }

        public void setAddr_all(String addr_all) {
            this.addr_all = addr_all;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getRoom_num() {
            return room_num;
        }

        public void setRoom_num(String room_num) {
            this.room_num = room_num;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getFamily_name() {
            return family_name;
        }

        public void setFamily_name(String family_name) {
            this.family_name = family_name;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public List<MemberBean> getMember() {
            return member;
        }

        public void setMember(List<MemberBean> member) {
            this.member = member;
        }

        public static class MemberBean {
            /**
             * member_id : 20
             * member_type : 1
             * member_phone : 17645185187
             * user_name : 神灯科技～孙先生
             * member_type_name : 管理员
             */

            private String member_id;
            private String member_type;
            private String member_phone;
            private String user_name;
            private String member_type_name;

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getMember_type() {
                return member_type;
            }

            public void setMember_type(String member_type) {
                this.member_type = member_type;
            }

            public String getMember_phone() {
                return member_phone;
            }

            public void setMember_phone(String member_phone) {
                this.member_phone = member_phone;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getMember_type_name() {
                return member_type_name;
            }

            public void setMember_type_name(String member_type_name) {
                this.member_type_name = member_type_name;
            }
        }
    }
}
