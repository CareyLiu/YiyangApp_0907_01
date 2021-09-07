package com.yiyang.cn.activity.tuya_device.add.model;

import java.io.Serializable;
import java.util.List;

public class DeviceListModel implements Serializable{

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"is_product":"","item_id_up":"0","item":[{"is_product":"","item_id_up":"1696","item":[{"is_product":"1","item_id_up":"1697","item":[],"order_flag":"first","item_detail":"","img_detail_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_id":"1698","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_name":"神灯音箱A款","category":"01","type":"00"},{"is_product":"2","item_id_up":"1697","item":[],"order_flag":"last","item_detail":"","img_detail_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_id":"1699","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_name":"神灯音箱B款","category":"02","type":"00"}],"order_flag":"-","item_detail":"","img_detail_url":"","item_id":"1697","img_url":"","item_name":"主机","category":"","type":""}],"order_flag":"first","item_detail":"","img_detail_url":"","item_id":"1696","img_url":"","item_name":"主机","category":"","type":""},{"is_product":"","item_id_up":"0","item":[{"is_product":"","item_id_up":"1700","item":[{"is_product":"1","item_id_up":"1701","item":[],"order_flag":"-","item_detail":"","img_detail_url":"http://192.168.100.31:8080/Frame/uploadFile/showImg?file_id=11922","item_id":"1746","img_url":"http://192.168.100.31:8080/Frame/uploadFile/showImg?file_id=11921","item_name":"摄像头","category":"01","type":"18"}],"order_flag":"-","item_detail":"","img_detail_url":"","item_id":"1701","img_url":"","item_name":"摄像头","category":"","type":""}],"item_detail":"","img_detail_url":"","item_id":"1700","img_url":"","item_name":"摄像机","category":"","type":""}]
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

    public static class DataBean implements Serializable{
        /**
         * is_product :
         * item_id_up : 0
         * item : [{"is_product":"","item_id_up":"1696","item":[{"is_product":"1","item_id_up":"1697","item":[],"order_flag":"first","item_detail":"","img_detail_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_id":"1698","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_name":"神灯音箱A款","category":"01","type":"00"},{"is_product":"2","item_id_up":"1697","item":[],"order_flag":"last","item_detail":"","img_detail_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_id":"1699","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_name":"神灯音箱B款","category":"02","type":"00"}],"order_flag":"-","item_detail":"","img_detail_url":"","item_id":"1697","img_url":"","item_name":"主机","category":"","type":""}]
         * order_flag : first
         * item_detail :
         * img_detail_url :
         * item_id : 1696
         * img_url :
         * item_name : 主机
         * category :
         * type :
         */

        private String is_product;
        private String item_id_up;
        private String order_flag;
        private String item_detail;
        private String img_detail_url;
        private String item_id;
        private String img_url;
        private String item_name;
        private String category;
        private String type;
        private List<ItemBeanX> item;

        public String getIs_product() {
            return is_product;
        }

        public void setIs_product(String is_product) {
            this.is_product = is_product;
        }

        public String getItem_id_up() {
            return item_id_up;
        }

        public void setItem_id_up(String item_id_up) {
            this.item_id_up = item_id_up;
        }

        public String getOrder_flag() {
            return order_flag;
        }

        public void setOrder_flag(String order_flag) {
            this.order_flag = order_flag;
        }

        public String getItem_detail() {
            return item_detail;
        }

        public void setItem_detail(String item_detail) {
            this.item_detail = item_detail;
        }

        public String getImg_detail_url() {
            return img_detail_url;
        }

        public void setImg_detail_url(String img_detail_url) {
            this.img_detail_url = img_detail_url;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<ItemBeanX> getItem() {
            return item;
        }

        public void setItem(List<ItemBeanX> item) {
            this.item = item;
        }

        public static class ItemBeanX implements Serializable {
            /**
             * is_product :
             * item_id_up : 1696
             * item : [{"is_product":"1","item_id_up":"1697","item":[],"order_flag":"first","item_detail":"","img_detail_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_id":"1698","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_name":"神灯音箱A款","category":"01","type":"00"},{"is_product":"2","item_id_up":"1697","item":[],"order_flag":"last","item_detail":"","img_detail_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_id":"1699","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920","item_name":"神灯音箱B款","category":"02","type":"00"}]
             * order_flag : -
             * item_detail :
             * img_detail_url :
             * item_id : 1697
             * img_url :
             * item_name : 主机
             * category :
             * type :
             */

            private String is_product;
            private String item_id_up;
            private String order_flag;
            private String item_detail;
            private String img_detail_url;
            private String item_id;
            private String img_url;
            private String item_name;
            private String category;
            private String type;
            private List<ItemBean> item;

            public String getIs_product() {
                return is_product;
            }

            public void setIs_product(String is_product) {
                this.is_product = is_product;
            }

            public String getItem_id_up() {
                return item_id_up;
            }

            public void setItem_id_up(String item_id_up) {
                this.item_id_up = item_id_up;
            }

            public String getOrder_flag() {
                return order_flag;
            }

            public void setOrder_flag(String order_flag) {
                this.order_flag = order_flag;
            }

            public String getItem_detail() {
                return item_detail;
            }

            public void setItem_detail(String item_detail) {
                this.item_detail = item_detail;
            }

            public String getImg_detail_url() {
                return img_detail_url;
            }

            public void setImg_detail_url(String img_detail_url) {
                this.img_detail_url = img_detail_url;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getItem_name() {
                return item_name;
            }

            public void setItem_name(String item_name) {
                this.item_name = item_name;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ItemBean> getItem() {
                return item;
            }

            public void setItem(List<ItemBean> item) {
                this.item = item;
            }

            public static class ItemBean implements Serializable{
                /**
                 * is_product : 1
                 * item_id_up : 1697
                 * item : []
                 * order_flag : first
                 * item_detail :
                 * img_detail_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920
                 * item_id : 1698
                 * img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920
                 * item_name : 神灯音箱A款
                 * category : 01
                 * type : 00
                 */

                private String is_product;
                private String item_id_up;
                private String order_flag;
                private String item_detail;
                private String img_detail_url;
                private String item_id;
                private String img_url;
                private String item_name;
                private String category;
                private String type;
                private List<?> item;

                public String getIs_product() {
                    return is_product;
                }

                public void setIs_product(String is_product) {
                    this.is_product = is_product;
                }

                public String getItem_id_up() {
                    return item_id_up;
                }

                public void setItem_id_up(String item_id_up) {
                    this.item_id_up = item_id_up;
                }

                public String getOrder_flag() {
                    return order_flag;
                }

                public void setOrder_flag(String order_flag) {
                    this.order_flag = order_flag;
                }

                public String getItem_detail() {
                    return item_detail;
                }

                public void setItem_detail(String item_detail) {
                    this.item_detail = item_detail;
                }

                public String getImg_detail_url() {
                    return img_detail_url;
                }

                public void setImg_detail_url(String img_detail_url) {
                    this.img_detail_url = img_detail_url;
                }

                public String getItem_id() {
                    return item_id;
                }

                public void setItem_id(String item_id) {
                    this.item_id = item_id;
                }

                public String getImg_url() {
                    return img_url;
                }

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }

                public String getItem_name() {
                    return item_name;
                }

                public void setItem_name(String item_name) {
                    this.item_name = item_name;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<?> getItem() {
                    return item;
                }

                public void setItem(List<?> item) {
                    this.item = item;
                }
            }
        }
    }
}
