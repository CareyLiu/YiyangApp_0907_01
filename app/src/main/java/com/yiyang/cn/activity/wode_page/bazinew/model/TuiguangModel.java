package com.yiyang.cn.activity.wode_page.bazinew.model;

import java.util.List;

public class TuiguangModel {


    /**
     * typeNext : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"agent_num_total":"1","agent_num_week":"0","promoters_list":[{"of_user_id":"379","create_time":"2020-01-03","user_name":"越加按钮","user_phone":"15004679965","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9413","id":"1.0","m_id":"1"}],"agent_num_month":"0"}]
     */

    private String typeNext;
    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

    public String getTypeNext() {
        return typeNext;
    }

    public void setTypeNext(String typeNext) {
        this.typeNext = typeNext;
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
         * agent_num_total : 1
         * agent_num_week : 0
         * promoters_list : [{"of_user_id":"379","create_time":"2020-01-03","user_name":"越加按钮","user_phone":"15004679965","user_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9413","id":"1.0","m_id":"1"}]
         * agent_num_month : 0
         */

        private String agent_num_total;
        private String agent_num_week;
        private String agent_num_month;
        private List<PromotersListBean> promoters_list;

        public String getAgent_num_total() {
            return agent_num_total;
        }

        public void setAgent_num_total(String agent_num_total) {
            this.agent_num_total = agent_num_total;
        }

        public String getAgent_num_week() {
            return agent_num_week;
        }

        public void setAgent_num_week(String agent_num_week) {
            this.agent_num_week = agent_num_week;
        }

        public String getAgent_num_month() {
            return agent_num_month;
        }

        public void setAgent_num_month(String agent_num_month) {
            this.agent_num_month = agent_num_month;
        }

        public List<PromotersListBean> getPromoters_list() {
            return promoters_list;
        }

        public void setPromoters_list(List<PromotersListBean> promoters_list) {
            this.promoters_list = promoters_list;
        }

        public static class PromotersListBean {
            /**
             * of_user_id : 379
             * create_time : 2020-01-03
             * user_name : 越加按钮
             * user_phone : 15004679965
             * user_img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9413
             * id : 1.0
             * m_id : 1
             */

            private String of_user_id;
            private String create_time;
            private String user_name;
            private String user_phone;
            private String user_img_url;
            private String id;
            private String m_id;

            public String getOf_user_id() {
                return of_user_id;
            }

            public void setOf_user_id(String of_user_id) {
                this.of_user_id = of_user_id;
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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getM_id() {
                return m_id;
            }

            public void setM_id(String m_id) {
                this.m_id = m_id;
            }
        }
    }
}
