package com.yiyang.cn.model;

import java.util.List;

public class TuiGuangTongJiModel {

    /**
     * next : 1
     * msg_code : 0000
     * msg : ok
     * row_num : 12
     * data : [{"invitation_code":"EF2032","agent_num_two":"1","user_type":"1","user_type_name":"会员","user_name":"阿钊","agent_num_one":"15","user_phone":"18004631006","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png","level_list":[{"of_user_id":"2401","user_type":"1","create_time":"2020-09-07","user_type_name":"会员","user_name":"juyijia_18249031235","user_phone":"18249031235","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png"},{"of_user_id":"2381","user_type":"1","create_time":"2020-09-03","user_type_name":"会员","user_name":"juyijia_13159860816","user_phone":"13159860816","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png"},{"of_user_id":"2347","user_type":"1","create_time":"2020-08-24","user_type_name":"会员","user_name":"易居","user_phone":"15561541313","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11705"},{"of_user_id":"2266","user_type":"1","create_time":"2020-07-31","user_type_name":"会员","user_name":"juyijia_15004678781","user_phone":"15004678781","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png"},{"of_user_id":"2263","user_type":"1","create_time":"2020-07-31","user_type_name":"会员","user_name":"juyijia_15004679957","user_phone":"15004679957","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png"},{"of_user_id":"2262","user_type":"1","create_time":"2020-07-31","user_type_name":"会员","user_name":"juyijia_15004679968","user_phone":"15004679968","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png"},{"of_user_id":"597","user_type":"1","create_time":"2020-07-31","user_type_name":"会员","user_name":"郭旭辉","user_phone":"13101560567","user_img_url":"https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png"},{"of_user_id":"1573","user_type":"1","create_time":"2020-04-29","user_type_name":"会员","user_name":"未设置","user_phone":"15244722422","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11410"},{"of_user_id":"1530","user_type":"1","create_time":"2020-03-30","user_type_name":"会员","user_name":"未设置","user_phone":"19804510802","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png"},{"of_user_id":"1419","user_type":"1","create_time":"2020-01-12","user_type_name":"会员","user_name":"未设置","user_phone":"18745783567","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png"},{"of_user_id":"499","user_type":"1","create_time":"2019-07-01","user_type_name":"会员","user_name":"客服一号001","user_phone":"18249030297","user_img_url":"https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png"},{"of_user_id":"628","user_type":"1","create_time":"2019-04-29","user_type_name":"会员","user_name":"","user_phone":"","user_img_url":""}]}]
     */

    private String next;
    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

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
         * invitation_code : EF2032
         * agent_num_two : 1
         * user_type : 1
         * user_type_name : 会员
         * user_name : 阿钊
         * agent_num_one : 15
         * user_phone : 18004631006
         * user_img_url : https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png
         * level_list : [{"of_user_id":"2401","user_type":"1","create_time":"2020-09-07","user_type_name":"会员","user_name":"juyijia_18249031235","user_phone":"18249031235","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png"},{"of_user_id":"2381","user_type":"1","create_time":"2020-09-03","user_type_name":"会员","user_name":"juyijia_13159860816","user_phone":"13159860816","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png"},{"of_user_id":"2347","user_type":"1","create_time":"2020-08-24","user_type_name":"会员","user_name":"易居","user_phone":"15561541313","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11705"},{"of_user_id":"2266","user_type":"1","create_time":"2020-07-31","user_type_name":"会员","user_name":"juyijia_15004678781","user_phone":"15004678781","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png"},{"of_user_id":"2263","user_type":"1","create_time":"2020-07-31","user_type_name":"会员","user_name":"juyijia_15004679957","user_phone":"15004679957","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png"},{"of_user_id":"2262","user_type":"1","create_time":"2020-07-31","user_type_name":"会员","user_name":"juyijia_15004679968","user_phone":"15004679968","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png"},{"of_user_id":"597","user_type":"1","create_time":"2020-07-31","user_type_name":"会员","user_name":"郭旭辉","user_phone":"13101560567","user_img_url":"https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png"},{"of_user_id":"1573","user_type":"1","create_time":"2020-04-29","user_type_name":"会员","user_name":"未设置","user_phone":"15244722422","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11410"},{"of_user_id":"1530","user_type":"1","create_time":"2020-03-30","user_type_name":"会员","user_name":"未设置","user_phone":"19804510802","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png"},{"of_user_id":"1419","user_type":"1","create_time":"2020-01-12","user_type_name":"会员","user_name":"未设置","user_phone":"18745783567","user_img_url":"https://shop.hljsdkj.com/commons/easyui/images/portrait86x86.png"},{"of_user_id":"499","user_type":"1","create_time":"2019-07-01","user_type_name":"会员","user_name":"客服一号001","user_phone":"18249030297","user_img_url":"https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png"},{"of_user_id":"628","user_type":"1","create_time":"2019-04-29","user_type_name":"会员","user_name":"","user_phone":"","user_img_url":""}]
         */

        private String invitation_code;
        private String agent_num_two;
        private String user_type;
        private String user_type_name;
        private String user_name;
        private String agent_num_one;
        private String user_phone;
        private String user_img_url;
        private List<LevelListBean> level_list;

        public String getInvitation_code() {
            return invitation_code;
        }

        public void setInvitation_code(String invitation_code) {
            this.invitation_code = invitation_code;
        }

        public String getAgent_num_two() {
            return agent_num_two;
        }

        public void setAgent_num_two(String agent_num_two) {
            this.agent_num_two = agent_num_two;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getUser_type_name() {
            return user_type_name;
        }

        public void setUser_type_name(String user_type_name) {
            this.user_type_name = user_type_name;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAgent_num_one() {
            return agent_num_one;
        }

        public void setAgent_num_one(String agent_num_one) {
            this.agent_num_one = agent_num_one;
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

        public List<LevelListBean> getLevel_list() {
            return level_list;
        }

        public void setLevel_list(List<LevelListBean> level_list) {
            this.level_list = level_list;
        }

        public static class LevelListBean {
            /**
             * of_user_id : 2401
             * user_type : 1
             * create_time : 2020-09-07
             * user_type_name : 会员
             * user_name : juyijia_18249031235
             * user_phone : 18249031235
             * user_img_url : https://shop.hljsdkj.com/commons/easyui/images/weishezhitouxiang.png
             */

            private String of_user_id;
            private String user_type;
            private String create_time;
            private String user_type_name;
            private String user_name;
            private String user_phone;
            private String user_img_url;

            public String getOf_user_id() {
                return of_user_id;
            }

            public void setOf_user_id(String of_user_id) {
                this.of_user_id = of_user_id;
            }

            public String getUser_type() {
                return user_type;
            }

            public void setUser_type(String user_type) {
                this.user_type = user_type;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUser_type_name() {
                return user_type_name;
            }

            public void setUser_type_name(String user_type_name) {
                this.user_type_name = user_type_name;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
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
        }
    }
}
