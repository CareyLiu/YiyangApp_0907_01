package com.yiyang.cn.model;

import java.util.List;

public class JingYingXiangBean {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"item_id":"1","item_name":"aa","item":[{"item_id":"2","item_name":"bb","img_url":"http://www.ba.com/asd.jpg"}]}]
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
         * item_id : 1
         * item_name : aa
         * item : [{"item_id":"2","item_name":"bb","img_url":"http://www.ba.com/asd.jpg"}]
         */

        private String item_id;
        private String item_name;
        private List<ItemBean> item;

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * item_id : 2
             * item_name : bb
             * img_url : http://www.ba.com/asd.jpg
             */

            private String item_id;
            private String item_name;
            private String img_url;

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getItem_name() {
                return item_name;
            }

            public void setItem_name(String item_name) {
                this.item_name = item_name;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }
}
