package com.yiyang.cn.model;

import java.util.List;

public class TuanGouShangPinDetailsModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"pay_count":"0","commentList":[],"agio":"8.6","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9077","inst_text":"蛋糕","text_1":[{"menu_title":"头盘","menu":[{"menu_text":"萨拉米肠配熏肉","menu_count":"3份","menu_pay":"48"}]}],"inst_number":"1.0","shop_money_old":"300.00","wares_id":"75","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480","shop_type":"1","shop_title":"美食三","text_2":[{"prompt_title":"有效期","prompt":[{"prompt_text":"·2015.7.27至2108.12.25（周末、法定节日通用）"}]},{"prompt_title":"使用时间","prompt":[{"prompt_text":"·11:00-20:00"}]},{"prompt_title":"使用规则","prompt":[{"prompt_text":"·无需预约，消费高峰时需要等位"}]}],"inst_id":"263","shop_money_now":"258.00","inst_name":"麦香村西饼屋（平房店）","addr":"黑龙江省哈尔滨市"}]
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
         * pay_count : 0
         * commentList : []
         * agio : 8.6
         * inst_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9077
         * inst_text : 蛋糕
         * text_1 : [{"menu_title":"头盘","menu":[{"menu_text":"萨拉米肠配熏肉","menu_count":"3份","menu_pay":"48"}]}]
         * inst_number : 1.0
         * shop_money_old : 300.00
         * wares_id : 75
         * img_url : https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=6480
         * shop_type : 1
         * shop_title : 美食三
         * text_2 : [{"prompt_title":"有效期","prompt":[{"prompt_text":"·2015.7.27至2108.12.25（周末、法定节日通用）"}]},{"prompt_title":"使用时间","prompt":[{"prompt_text":"·11:00-20:00"}]},{"prompt_title":"使用规则","prompt":[{"prompt_text":"·无需预约，消费高峰时需要等位"}]}]
         * inst_id : 263
         * shop_money_now : 258.00
         * inst_name : 麦香村西饼屋（平房店）
         * addr : 黑龙江省哈尔滨市
         */

        private String pay_count;
        private String agio;
        private String inst_photo_url;
        private String inst_text;
        private String inst_number;
        private String shop_money_old;
        private String wares_id;
        private String img_url;
        private String shop_type;
        private String shop_title;
        private String inst_id;
        private String shop_money_now;
        private String inst_name;
        private String addr;
        private List<?> commentList;
        private List<Text1Bean> text_1;
        private List<Text2Bean> text_2;

        public String getPay_count() {
            return pay_count;
        }

        public void setPay_count(String pay_count) {
            this.pay_count = pay_count;
        }

        public String getAgio() {
            return agio;
        }

        public void setAgio(String agio) {
            this.agio = agio;
        }

        public String getInst_photo_url() {
            return inst_photo_url;
        }

        public void setInst_photo_url(String inst_photo_url) {
            this.inst_photo_url = inst_photo_url;
        }

        public String getInst_text() {
            return inst_text;
        }

        public void setInst_text(String inst_text) {
            this.inst_text = inst_text;
        }

        public String getInst_number() {
            return inst_number;
        }

        public void setInst_number(String inst_number) {
            this.inst_number = inst_number;
        }

        public String getShop_money_old() {
            return shop_money_old;
        }

        public void setShop_money_old(String shop_money_old) {
            this.shop_money_old = shop_money_old;
        }

        public String getWares_id() {
            return wares_id;
        }

        public void setWares_id(String wares_id) {
            this.wares_id = wares_id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getShop_money_now() {
            return shop_money_now;
        }

        public void setShop_money_now(String shop_money_now) {
            this.shop_money_now = shop_money_now;
        }

        public String getInst_name() {
            return inst_name;
        }

        public void setInst_name(String inst_name) {
            this.inst_name = inst_name;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public List<?> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<?> commentList) {
            this.commentList = commentList;
        }

        public List<Text1Bean> getText_1() {
            return text_1;
        }

        public void setText_1(List<Text1Bean> text_1) {
            this.text_1 = text_1;
        }

        public List<Text2Bean> getText_2() {
            return text_2;
        }

        public void setText_2(List<Text2Bean> text_2) {
            this.text_2 = text_2;
        }

        public static class Text1Bean {
            /**
             * menu_title : 头盘
             * menu : [{"menu_text":"萨拉米肠配熏肉","menu_count":"3份","menu_pay":"48"}]
             */

            private String menu_title;
            private List<MenuBean> menu;


            public String menu_detail_id;
            public String menu_pay;
            public String menu_text;
            public String menu_count;


            public String getMenu_title() {
                return menu_title;
            }

            public void setMenu_title(String menu_title) {
                this.menu_title = menu_title;
            }

            public List<MenuBean> getMenu() {
                return menu;
            }

            public void setMenu(List<MenuBean> menu) {
                this.menu = menu;
            }

            public static class MenuBean {
                /**
                 * menu_text : 萨拉米肠配熏肉
                 * menu_count : 3份
                 * menu_pay : 48
                 */

                private String menu_text;
                private String menu_count;
                private String menu_pay;


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

                public String getMenu_pay() {
                    return menu_pay;
                }

                public void setMenu_pay(String menu_pay) {
                    this.menu_pay = menu_pay;
                }
            }
        }

        public static class Text2Bean {
            /**
             * prompt_title : 有效期
             * prompt : [{"prompt_text":"·2015.7.27至2108.12.25（周末、法定节日通用）"}]
             */

            private String prompt_title;
            private List<PromptBean> prompt;

            public String prompt_detail_id;
            public String prompt_text;

            public String getPrompt_title() {
                return prompt_title;
            }

            public void setPrompt_title(String prompt_title) {
                this.prompt_title = prompt_title;
            }

            public List<PromptBean> getPrompt() {
                return prompt;
            }

            public void setPrompt(List<PromptBean> prompt) {
                this.prompt = prompt;
            }

            public static class PromptBean {
                /**
                 * prompt_text : ·2015.7.27至2108.12.25（周末、法定节日通用）
                 */

                private String prompt_text;

                public String getPrompt_text() {
                    return prompt_text;
                }

                public void setPrompt_text(String prompt_text) {
                    this.prompt_text = prompt_text;
                }
            }
        }
    }
}
