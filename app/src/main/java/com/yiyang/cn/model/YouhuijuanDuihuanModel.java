package com.yiyang.cn.model;

import java.util.List;

public class YouhuijuanDuihuanModel {


    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"inst_id":"263","inst_name":"麦香村西饼屋（平房店）"},{"inst_id":"398","inst_name":"兰棱杀猪菜（堡旭分店）"},{"inst_id":"410","inst_name":"兰棱杀猪菜（文昌总店）"}]
     */

    private String msg_code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * inst_id : 263
         * inst_name : 麦香村西饼屋（平房店）
         */

        private String inst_id;
        private String inst_name;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getInst_name() {
            return inst_name;
        }

        public void setInst_name(String inst_name) {
            this.inst_name = inst_name;
        }
    }
}
