package com.yiyang.cn.model;

import com.yiyang.cn.adapter.ExpandableRecyclerAdapter;

import java.util.List;

public class ShopCartModel {

    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 2
     * data : [{"shopcart_id":"48","proList":[{"shop_product_title":"横版数码按键开关","shop_product_id":"1","wares_id":"9","form_product_id":"494","disabled_cause":"","wares_money_go":"10.00","product_title":"套餐1","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","form_product_money":"12.00","pay_count":"2","form_product_state":"1","wares_go_type":"1"},{"shop_product_title":"横版数码按键开关","shop_product_id":"2","wares_id":"9","form_product_id":"496","disabled_cause":"","wares_money_go":"10.00","product_title":"套餐2","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","form_product_money":"14.00","pay_count":"2","form_product_state":"1","wares_go_type":"1"}],"inst_id":"187","inst_name":"神灯科技旗舰店","inst_logo_url":"https://shop.hljsdkj.com/manage/subsystem/main/toInstUrl?inst_id=187","subsystem_id":"jcz"},{"shopcart_id":"49","proList":[{"shop_product_title":"大水电路板（12kw）","shop_product_id":"7","wares_id":"20","form_product_id":"495","disabled_cause":"","wares_money_go":"10.00","product_title":"白色","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=5805","form_product_money":"25.00","pay_count":"1","form_product_state":"1","wares_go_type":"1"}],"inst_id":"204","inst_name":"电缆街分店","inst_logo_url":"https://shop.hljsdkj.com/manage/subsystem/main/toInstUrl?inst_id=204","subsystem_id":"jcz"}]
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

    public static class DataBean extends ExpandableRecyclerAdapter.ListItem  {
        /**
         * shopcart_id : 48
         * proList : [{"shop_product_title":"横版数码按键开关","shop_product_id":"1","wares_id":"9","form_product_id":"494","disabled_cause":"","wares_money_go":"10.00","product_title":"套餐1","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","form_product_money":"12.00","pay_count":"2","form_product_state":"1","wares_go_type":"1"},{"shop_product_title":"横版数码按键开关","shop_product_id":"2","wares_id":"9","form_product_id":"496","disabled_cause":"","wares_money_go":"10.00","product_title":"套餐2","index_photo_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102","form_product_money":"14.00","pay_count":"2","form_product_state":"1","wares_go_type":"1"}]
         * inst_id : 187
         * inst_name : 神灯科技旗舰店
         * inst_logo_url : https://shop.hljsdkj.com/manage/subsystem/main/toInstUrl?inst_id=187
         * subsystem_id : jcz
         */

        private String shopcart_id;
        private String inst_id;
        private String inst_name;
        private String inst_logo_url;
        private String subsystem_id;
        private List<ProListBean> proList;

        protected DataBean(int itemType) {
            super(itemType);
        }

        public String getShopcart_id() {
            return shopcart_id;
        }

        public void setShopcart_id(String shopcart_id) {
            this.shopcart_id = shopcart_id;
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

        public String getInst_logo_url() {
            return inst_logo_url;
        }

        public void setInst_logo_url(String inst_logo_url) {
            this.inst_logo_url = inst_logo_url;
        }

        public String getSubsystem_id() {
            return subsystem_id;
        }

        public void setSubsystem_id(String subsystem_id) {
            this.subsystem_id = subsystem_id;
        }

        public List<ProListBean> getProList() {
            return proList;
        }

        public void setProList(List<ProListBean> proList) {
            this.proList = proList;
        }

        public static class ProListBean {
            /**
             * shop_product_title : 横版数码按键开关
             * shop_product_id : 1
             * wares_id : 9
             * form_product_id : 494
             * disabled_cause :
             * wares_money_go : 10.00
             * product_title : 套餐1
             * index_photo_url : https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=7102
             * form_product_money : 12.00
             * pay_count : 2
             * form_product_state : 1
             * wares_go_type : 1
             */

            private String shop_product_title;
            private String shop_product_id;
            private String wares_id;
            private String form_product_id;
            private String disabled_cause;
            private String wares_money_go;
            private String product_title;
            private String index_photo_url;
            private String form_product_money;
            private String pay_count;
            private String form_product_state;
            private String wares_go_type;

            public String getShop_product_title() {
                return shop_product_title;
            }

            public void setShop_product_title(String shop_product_title) {
                this.shop_product_title = shop_product_title;
            }

            public String getShop_product_id() {
                return shop_product_id;
            }

            public void setShop_product_id(String shop_product_id) {
                this.shop_product_id = shop_product_id;
            }

            public String getWares_id() {
                return wares_id;
            }

            public void setWares_id(String wares_id) {
                this.wares_id = wares_id;
            }

            public String getForm_product_id() {
                return form_product_id;
            }

            public void setForm_product_id(String form_product_id) {
                this.form_product_id = form_product_id;
            }

            public String getDisabled_cause() {
                return disabled_cause;
            }

            public void setDisabled_cause(String disabled_cause) {
                this.disabled_cause = disabled_cause;
            }

            public String getWares_money_go() {
                return wares_money_go;
            }

            public void setWares_money_go(String wares_money_go) {
                this.wares_money_go = wares_money_go;
            }

            public String getProduct_title() {
                return product_title;
            }

            public void setProduct_title(String product_title) {
                this.product_title = product_title;
            }

            public String getIndex_photo_url() {
                return index_photo_url;
            }

            public void setIndex_photo_url(String index_photo_url) {
                this.index_photo_url = index_photo_url;
            }

            public String getForm_product_money() {
                return form_product_money;
            }

            public void setForm_product_money(String form_product_money) {
                this.form_product_money = form_product_money;
            }

            public String getPay_count() {
                return pay_count;
            }

            public void setPay_count(String pay_count) {
                this.pay_count = pay_count;
            }

            public String getForm_product_state() {
                return form_product_state;
            }

            public void setForm_product_state(String form_product_state) {
                this.form_product_state = form_product_state;
            }

            public String getWares_go_type() {
                return wares_go_type;
            }

            public void setWares_go_type(String wares_go_type) {
                this.wares_go_type = wares_go_type;
            }
        }
    }
}
