package com.yiyang.cn.model;

import java.util.List;

public class DingDanDetailsModel {
    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"shop_form_id":"2809","inst_accid":"jcz_sub_191","user_pay_check":"1","inst_img_url":"https://shop.hljsdkj.com/manage/subsystem/main/toInstUrl?inst_id=187","pay_check_name":"待付款","shop_product_title":"横版数码按键开关","user_name":"老多了","pay_code":"","form_product_money":"12.00","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9931","express_no":"","pay_money":"22.00","form_money_go":"10.00","inst_x":"","wares_id":"9","express_url":"","wares_type":"1","shop_product_id":"1","deduction_money":"","user_phone":"15236586932","inst_id":"187","user_addr_all":"北京北京市东城区太可怜了拉锯太可怜了","deduction_name":"","pay_count":"1","operate_type":"21","user_pay_check_name":"待付款 ","express_name":"","product_title":"套餐1","url":"https://shop.hljsdkj.com/inst/main/xc/twoCode?content=null","inst_y":"","express_id":"","inst_addr_all":"","inst_name":"神灯科技旗舰店","order_info_arr":["订单备注：","订单编号：20200601100057000001","下单时间：2020-06-01 10:00:57"],"pay_code_state":""}]
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
         * shop_form_id : 2809
         * inst_accid : jcz_sub_191
         * user_pay_check : 1
         * inst_img_url : https://shop.hljsdkj.com/manage/subsystem/main/toInstUrl?inst_id=187
         * pay_check_name : 待付款
         * shop_product_title : 横版数码按键开关
         * user_name : 老多了
         * pay_code :
         * form_product_money : 12.00
         * index_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9931
         * express_no :
         * pay_money : 22.00
         * form_money_go : 10.00
         * inst_x :
         * wares_id : 9
         * express_url :
         * wares_type : 1
         * shop_product_id : 1
         * deduction_money :
         * user_phone : 15236586932
         * inst_id : 187
         * user_addr_all : 北京北京市东城区太可怜了拉锯太可怜了
         * deduction_name :
         * pay_count : 1
         * operate_type : 21
         * user_pay_check_name : 待付款
         * express_name :
         * product_title : 套餐1
         * url : https://shop.hljsdkj.com/inst/main/xc/twoCode?content=null
         * inst_y :
         * express_id :
         * inst_addr_all :
         * inst_name : 神灯科技旗舰店
         * order_info_arr : ["订单备注：","订单编号：20200601100057000001","下单时间：2020-06-01 10:00:57"]
         * pay_code_state :
         */

        private String shop_form_id;
        private String inst_accid;
        private String user_pay_check;
        private String inst_img_url;
        private String pay_check_name;
        private String shop_product_title;
        private String user_name;
        private String pay_code;
        private String form_product_money;
        private String index_photo_url;
        private String express_no;
        private String pay_money;
        private String form_money_go;
        private String inst_x;
        private String wares_id;
        private String express_url;
        private String wares_type;
        private String shop_product_id;
        private String deduction_money;
        private String user_phone;
        private String inst_id;
        private String user_addr_all;
        private String deduction_name;
        private String pay_count;
        private String operate_type;
        private String user_pay_check_name;
        private String express_name;
        private String product_title;
        private String url;
        private String inst_y;
        private String express_id;
        private String inst_addr_all;
        private String inst_name;
        private String pay_code_state;
        private List<String> order_info_arr;
        private List<TaocanListBean> taocan_list;

        public List<TaocanListBean> getTaocan_list() {
            return taocan_list;
        }

        public void setTaocan_list(List<TaocanListBean> taocan_list) {
            this.taocan_list = taocan_list;
        }

        public static class TaocanListBean {
            /**
             * menu_detail_id : 40
             * menu_pay : 28.00
             * menu_text : 发文字呀
             * menu_count : 1
             */

            private String menu_detail_id;
            private String menu_pay;
            private String menu_text;
            private String menu_count;

            public String getMenu_detail_id() {
                return menu_detail_id;
            }

            public void setMenu_detail_id(String menu_detail_id) {
                this.menu_detail_id = menu_detail_id;
            }

            public String getMenu_pay() {
                return menu_pay;
            }

            public void setMenu_pay(String menu_pay) {
                this.menu_pay = menu_pay;
            }

            public String getMenu_text() {
                return menu_text;
            }

            public void setMenu_text(String menu_text) {
                this.menu_text = menu_text;
            }

            public String getMenu_count() {
                return menu_count;
            }

            public void setMenu_count(String menu_count) {
                this.menu_count = menu_count;
            }
        }

        public String getShop_form_id() {
            return shop_form_id;
        }

        public void setShop_form_id(String shop_form_id) {
            this.shop_form_id = shop_form_id;
        }

        public String getInst_accid() {
            return inst_accid;
        }

        public void setInst_accid(String inst_accid) {
            this.inst_accid = inst_accid;
        }

        public String getUser_pay_check() {
            return user_pay_check;
        }

        public void setUser_pay_check(String user_pay_check) {
            this.user_pay_check = user_pay_check;
        }

        public String getInst_img_url() {
            return inst_img_url;
        }

        public void setInst_img_url(String inst_img_url) {
            this.inst_img_url = inst_img_url;
        }

        public String getPay_check_name() {
            return pay_check_name;
        }

        public void setPay_check_name(String pay_check_name) {
            this.pay_check_name = pay_check_name;
        }

        public String getShop_product_title() {
            return shop_product_title;
        }

        public void setShop_product_title(String shop_product_title) {
            this.shop_product_title = shop_product_title;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPay_code() {
            return pay_code;
        }

        public void setPay_code(String pay_code) {
            this.pay_code = pay_code;
        }

        public String getForm_product_money() {
            return form_product_money;
        }

        public void setForm_product_money(String form_product_money) {
            this.form_product_money = form_product_money;
        }

        public String getIndex_photo_url() {
            return index_photo_url;
        }

        public void setIndex_photo_url(String index_photo_url) {
            this.index_photo_url = index_photo_url;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public String getForm_money_go() {
            return form_money_go;
        }

        public void setForm_money_go(String form_money_go) {
            this.form_money_go = form_money_go;
        }

        public String getInst_x() {
            return inst_x;
        }

        public void setInst_x(String inst_x) {
            this.inst_x = inst_x;
        }

        public String getWares_id() {
            return wares_id;
        }

        public void setWares_id(String wares_id) {
            this.wares_id = wares_id;
        }

        public String getExpress_url() {
            return express_url;
        }

        public void setExpress_url(String express_url) {
            this.express_url = express_url;
        }

        public String getWares_type() {
            return wares_type;
        }

        public void setWares_type(String wares_type) {
            this.wares_type = wares_type;
        }

        public String getShop_product_id() {
            return shop_product_id;
        }

        public void setShop_product_id(String shop_product_id) {
            this.shop_product_id = shop_product_id;
        }

        public String getDeduction_money() {
            return deduction_money;
        }

        public void setDeduction_money(String deduction_money) {
            this.deduction_money = deduction_money;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getUser_addr_all() {
            return user_addr_all;
        }

        public void setUser_addr_all(String user_addr_all) {
            this.user_addr_all = user_addr_all;
        }

        public String getDeduction_name() {
            return deduction_name;
        }

        public void setDeduction_name(String deduction_name) {
            this.deduction_name = deduction_name;
        }

        public String getPay_count() {
            return pay_count;
        }

        public void setPay_count(String pay_count) {
            this.pay_count = pay_count;
        }

        public String getOperate_type() {
            return operate_type;
        }

        public void setOperate_type(String operate_type) {
            this.operate_type = operate_type;
        }

        public String getUser_pay_check_name() {
            return user_pay_check_name;
        }

        public void setUser_pay_check_name(String user_pay_check_name) {
            this.user_pay_check_name = user_pay_check_name;
        }

        public String getExpress_name() {
            return express_name;
        }

        public void setExpress_name(String express_name) {
            this.express_name = express_name;
        }

        public String getProduct_title() {
            return product_title;
        }

        public void setProduct_title(String product_title) {
            this.product_title = product_title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getInst_y() {
            return inst_y;
        }

        public void setInst_y(String inst_y) {
            this.inst_y = inst_y;
        }

        public String getExpress_id() {
            return express_id;
        }

        public void setExpress_id(String express_id) {
            this.express_id = express_id;
        }

        public String getInst_addr_all() {
            return inst_addr_all;
        }

        public void setInst_addr_all(String inst_addr_all) {
            this.inst_addr_all = inst_addr_all;
        }

        public String getInst_name() {
            return inst_name;
        }

        public void setInst_name(String inst_name) {
            this.inst_name = inst_name;
        }

        public String getPay_code_state() {
            return pay_code_state;
        }

        public void setPay_code_state(String pay_code_state) {
            this.pay_code_state = pay_code_state;
        }

        public List<String> getOrder_info_arr() {
            return order_info_arr;
        }

        public void setOrder_info_arr(List<String> order_info_arr) {
            this.order_info_arr = order_info_arr;
        }
    }

    /**
     * msg_code	返回码	6	是
     * msg	应答描述	30	是
     * data	应答数据		否
     * shop_form_id	订单id
     * inst_id	卖家机构id
     * inst_img_url	卖家头像
     * inst_name	卖家名称
     * inst_accid	卖家accid
     * wares_type	订单类型：1.普通2.拼单 3.团购
     * user_pay_check	付款状态
     * user_pay_check_name	付款状态名称
     * pay_check_name	款项名称
     * pay_money	总金额
     * shop_product_id	商品颜色分类id
     * index_photo_url	商品颜色分类封面url
     * shop_product_title	商品标题
     * product_title	商品颜色分类标题
     * form_product_money	商品单价
     * pay_count	购买数量
     * form_money_go	运费
     * inst_x	卖家坐标x（到店消费显示）
     * inst_y	卖家坐标y（到店消费显示）
     * inst_addr_all	卖家详细地址（到店消费显示）
     * pay_code	到店消费二维码（到店消费显示）
     * pay_code_state	二维码状态：1.未使用2.已使用
     * user_name	买家姓名（邮递消费显示）
     * user_phone	买家联系电话（邮递消费显示）
     * user_addr_all	买家收货地址（邮递消费显示）
     * express_id	快递公司id（邮递消费显示）
     * express_no	快递单号
     * express_name	快递公司名称
     * express_url	查看物流url
     * order_info_arr	订单信息数组
     * make_id	拼团id
     * make_state	拼单状态：1.拼团中 2.成团 3.失败 4.未付款5.付款超时
     * make_state_name	拼单状态名称
     * count_all	成团人数
     * difference	还差几人
     * member	user_img_url	成员头像列表
     * 	make_level	级别(1.团长 2.团员)
     */
    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"shop_form_id":"2809","inst_accid":"jcz_sub_191","user_pay_check":"1","inst_img_url":"https://shop.hljsdkj.com/manage/subsystem/main/toInstUrl?inst_id=187","pay_check_name":"待付款","shop_product_title":"横版数码按键开关","user_name":"老多了","pay_code":"","form_product_money":"12.00","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9931","pay_money":"22.00","form_money_go":"10.00","inst_x":"","wares_id":"9","wares_type":"1","shop_product_id":"1","deduction_money":"","user_phone":"15236586932","inst_id":"187","user_addr_all":"北京北京市东城区太可怜了拉锯太可怜了","deduction_name":"","pay_count":"1","operate_type":"21","user_pay_check_name":"待付款 ","product_title":"套餐1","url":"https://shop.hljsdkj.com/inst/main/xc/twoCode?content=null","inst_y":"","inst_addr_all":"","inst_name":"神灯科技旗舰店","order_info_arr":["订单备注：","订单编号：20200601100057000001","下单时间：2020-06-01 10:00:57"],"pay_code_state":""}]
     */


}
