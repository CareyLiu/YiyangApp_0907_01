package com.yiyang.cn.model;

import java.util.List;

public class GuanZhuDianPuListModel {
    /**
     * typeNext : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 2
     * data : [{"collection_id":"241","inst_state":"1","inst_state_name":"正常","inst_photo_url":"https://shop.hljsdkj.com/manage/subsystem/main/toInstUrl?inst_id=407","wares_type":"3","inst_id":"407","inst_name":"春天门业"},{"collection_id":"240","inst_state":"1","inst_state_name":"正常","inst_photo_url":"https://shop.hljsdkj.com/manage/subsystem/main/toInstUrl?inst_id=187","wares_type":"3","inst_id":"187","inst_name":"神灯科技旗舰店"}]
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
         * collection_id : 241
         * inst_state : 1
         * inst_state_name : 正常
         * inst_photo_url : https://shop.hljsdkj.com/manage/subsystem/main/toInstUrl?inst_id=407
         * wares_type : 3
         * inst_id : 407
         * inst_name : 春天门业
         */

        private String collection_id;
        private String inst_state;
        private String inst_state_name;
        private String inst_photo_url;
        private String wares_type;
        private String inst_id;
        private String inst_name;

        public String getCollection_id() {
            return collection_id;
        }

        public void setCollection_id(String collection_id) {
            this.collection_id = collection_id;
        }

        public String getInst_state() {
            return inst_state;
        }

        public void setInst_state(String inst_state) {
            this.inst_state = inst_state;
        }

        public String getInst_state_name() {
            return inst_state_name;
        }

        public void setInst_state_name(String inst_state_name) {
            this.inst_state_name = inst_state_name;
        }

        public String getInst_photo_url() {
            return inst_photo_url;
        }

        public void setInst_photo_url(String inst_photo_url) {
            this.inst_photo_url = inst_photo_url;
        }

        public String getWares_type() {
            return wares_type;
        }

        public void setWares_type(String wares_type) {
            this.wares_type = wares_type;
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
