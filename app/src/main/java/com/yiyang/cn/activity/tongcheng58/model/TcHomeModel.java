package com.yiyang.cn.activity.tongcheng58.model;

import java.util.List;

public class TcHomeModel {


    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"shopList":[{"inst_device_list":[{"inst_device_name":"刷卡支付"},{"inst_device_name":"免费上网"},{"inst_device_name":"免费停车"},{"inst_device_name":"提供包房"}],"meter":"6608.9","x":"45.662479","y":"126.611618","ir_inst_logo":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016","ir_inst_name":"国尚公司","ir_contact_phone":"15248494682","addr":"黑龙江省哈尔滨市南岗区"}],"iconList":[{"service_type":"1","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiajuweixiu.png","service_type_name":"家具维修"},{"service_type":"2","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_shafaqingxi.png","service_type_name":"沙发清洗"},{"service_type":"3","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_tongchengkuaidi.png","service_type_name":"同城快递"},{"service_type":"4","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiajuchaizhuang.png","service_type_name":"家具拆装"},{"service_type":"5","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_shafahuanmian.png","service_type_name":"沙发换面"},{"service_type":"6","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_banjia.png","service_type_name":"搬家搬运"},{"service_type":"7","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiajutiemo.png","service_type_name":"家具贴膜"},{"service_type":"8","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_kaisuo.png","service_type_name":"开锁服务"},{"service_type":"9","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_xiudiannao.png","service_type_name":"电脑维修"},{"service_type":"10","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_cizhuanmeifeng.png","service_type_name":"瓷砖美缝"},{"service_type":"11","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiazhengbaojie.png","service_type_name":"家政保洁"},{"service_type":"12","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiaquan.png","service_type_name":"甲醛治理"},{"service_type":"13","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiadianweixu.png","service_type_name":"家电维修"},{"service_type":"14","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiankongweixu.png","service_type_name":"监控维护"},{"service_type":"15","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_guandaoshutong.png","service_type_name":"窗户维修"},{"service_type":"16","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_chekumen.png","service_type_name":"软包房门"},{"service_type":"17","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_diangong.png","service_type_name":"电工服务"},{"service_type":"18","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiadianqingxi.png","service_type_name":"家电清洗"},{"service_type":"19","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_liangcaishifu.png","service_type_name":"墙面大白"},{"service_type":"20","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_bolidingzhi.png","service_type_name":"玻璃定制"},{"service_type":"21","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_shuinuan.png","service_type_name":"水暖服务"},{"service_type":"22","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_mugongfuwu.png","service_type_name":"木工服务"},{"service_type":"23","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_wagongtiezhuan.png","service_type_name":"瓦工贴砖"},{"service_type":"24","service_type_name":"房屋改造"},{"service_type":"26","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_guandao.png","service_type_name":"管道疏通"},{"service_type":"27","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiajufanxin.png","service_type_name":"家具翻新"},{"service_type":"28","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_chekumen.png","service_type_name":"车库门维修"},{"service_type":"29","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_zhileng.png","service_type_name":"专业制冷"},{"service_type":"30","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_baigang.png","service_type_name":"白钢装饰"}],"shop_type_list":[{"ir_industry_type_name":"精选","ir_industry_type":"1851"},{"ir_industry_type_name":"其它","ir_industry_type":"1849"},{"ir_industry_type_name":"本地特产","ir_industry_type":"1847"},{"ir_industry_type_name":"洗衣修鞋","ir_industry_type":"1844"},{"ir_industry_type_name":"眼镜店","ir_industry_type":"1842"},{"ir_industry_type_name":"养老院","ir_industry_type":"1840"},{"ir_industry_type_name":"农资","ir_industry_type":"1838"},{"ir_industry_type_name":"美容美发","ir_industry_type":"1836"},{"ir_industry_type_name":"宠物生活","ir_industry_type":"1834"},{"ir_industry_type_name":"生鲜超市","ir_industry_type":"1832"},{"ir_industry_type_name":"名烟名酒","ir_industry_type":"1830"},{"ir_industry_type_name":"婚庆礼仪","ir_industry_type":"1828"},{"ir_industry_type_name":"孕婴商店","ir_industry_type":"1825"},{"ir_industry_type_name":"房产中介","ir_industry_type":"1823"},{"ir_industry_type_name":"精品服饰","ir_industry_type":"1821"},{"ir_industry_type_name":"电子家电","ir_industry_type":"1819"},{"ir_industry_type_name":"文化学校","ir_industry_type":"1817"},{"ir_industry_type_name":"休闲","ir_industry_type":"1815"},{"ir_industry_type_name":"特色美食","ir_industry_type":"1813"},{"ir_industry_type_name":"家具建材","ir_industry_type":"1810"},{"ir_industry_type_name":"汽车生活","ir_industry_type":"1807"}]}]
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
        private List<ShopListBean> shopList;
        private List<IconListBean> iconList;
        private List<ShopTypeListBean> shop_type_list;

        public List<ShopListBean> getShopList() {
            return shopList;
        }

        public void setShopList(List<ShopListBean> shopList) {
            this.shopList = shopList;
        }

        public List<IconListBean> getIconList() {
            return iconList;
        }

        public void setIconList(List<IconListBean> iconList) {
            this.iconList = iconList;
        }

        public List<ShopTypeListBean> getShop_type_list() {
            return shop_type_list;
        }

        public void setShop_type_list(List<ShopTypeListBean> shop_type_list) {
            this.shop_type_list = shop_type_list;
        }

        public static class ShopListBean {
            /**
             * inst_device_list : [{"inst_device_name":"刷卡支付"},{"inst_device_name":"免费上网"},{"inst_device_name":"免费停车"},{"inst_device_name":"提供包房"}]
             * meter : 6608.9
             * x : 45.662479
             * y : 126.611618
             * ir_inst_logo : https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016
             * ir_inst_name : 国尚公司
             * ir_contact_phone : 15248494682
             * addr : 黑龙江省哈尔滨市南岗区
             * ir_id : 1
             */

            private String meter;
            private String x;
            private String y;
            private String ir_inst_logo;
            private String ir_inst_name;
            private String ir_contact_phone;
            private String addr;
            private String ir_id;
            private List<InstDeviceListBean> inst_device_list;

            public String getIr_id() {
                return ir_id;
            }

            public void setIr_id(String ir_id) {
                this.ir_id = ir_id;
            }

            public String getMeter() {
                return meter;
            }

            public void setMeter(String meter) {
                this.meter = meter;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getIr_inst_logo() {
                return ir_inst_logo;
            }

            public void setIr_inst_logo(String ir_inst_logo) {
                this.ir_inst_logo = ir_inst_logo;
            }

            public String getIr_inst_name() {
                return ir_inst_name;
            }

            public void setIr_inst_name(String ir_inst_name) {
                this.ir_inst_name = ir_inst_name;
            }

            public String getIr_contact_phone() {
                return ir_contact_phone;
            }

            public void setIr_contact_phone(String ir_contact_phone) {
                this.ir_contact_phone = ir_contact_phone;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public List<InstDeviceListBean> getInst_device_list() {
                return inst_device_list;
            }

            public void setInst_device_list(List<InstDeviceListBean> inst_device_list) {
                this.inst_device_list = inst_device_list;
            }

            public static class InstDeviceListBean {
                /**
                 * inst_device_name : 刷卡支付
                 */

                private String inst_device_name;

                public String getInst_device_name() {
                    return inst_device_name;
                }

                public void setInst_device_name(String inst_device_name) {
                    this.inst_device_name = inst_device_name;
                }
            }
        }

        public static class IconListBean {
            /**
             * service_type : 1
             * service_type_img : http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiajuweixiu.png
             * service_type_name : 家具维修
             */

            private boolean isNew;
            private String service_type;
            private String service_type_img;
            private int service_type_img_id;
            private String service_type_name;

            public String getService_type() {
                return service_type;
            }

            public void setService_type(String service_type) {
                this.service_type = service_type;
            }

            public String getService_type_img() {
                return service_type_img;
            }

            public void setService_type_img(String service_type_img) {
                this.service_type_img = service_type_img;
            }

            public String getService_type_name() {
                return service_type_name;
            }

            public void setService_type_name(String service_type_name) {
                this.service_type_name = service_type_name;
            }

            public int getService_type_img_id() {
                return service_type_img_id;
            }

            public void setService_type_img_id(int service_type_img_id) {
                this.service_type_img_id = service_type_img_id;
            }

            public boolean isNew() {
                return isNew;
            }

            public void setNew(boolean aNew) {
                isNew = aNew;
            }

            public IconListBean(int service_type_img_id, String service_type_name) {
                this.service_type_img_id = service_type_img_id;
                this.service_type_name = service_type_name;
                this.isNew = true;
            }
        }

        public static class ShopTypeListBean {
            /**
             * ir_industry_type_name : 精选
             * ir_industry_type : 1851
             */

            private String ir_industry_type_name;
            private String ir_industry_type;

            public String getIr_industry_type_name() {
                return ir_industry_type_name;
            }

            public void setIr_industry_type_name(String ir_industry_type_name) {
                this.ir_industry_type_name = ir_industry_type_name;
            }

            public String getIr_industry_type() {
                return ir_industry_type;
            }

            public void setIr_industry_type(String ir_industry_type) {
                this.ir_industry_type = ir_industry_type;
            }
        }
    }
}
