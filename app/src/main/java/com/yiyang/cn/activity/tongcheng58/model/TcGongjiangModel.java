package com.yiyang.cn.activity.tongcheng58.model;

import java.util.List;

public class TcGongjiangModel {


    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"craftsManList":[{"ir_audit_state":"2","service_type_list":[{"service_type_name":"家具维修"},{"service_type_name":"沙发清洗"},{"service_type_name":"同城快递"},{"service_type_name":"家具拆装"},{"service_type_name":"沙发换面"}],"meter":"6608.9","x":"45.662479","ir_personnal_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12018","y":"126.611618","ir_personnal_name":"小朱","addr":"黑龙江省哈尔滨市道里区"}],"iconList":[{"service_type":"1","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiajuweixiu.png","service_type_name":"家具维修"},{"service_type":"2","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_shafaqingxi.png","service_type_name":"沙发清洗"},{"service_type":"3","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_tongchengkuaidi.png","service_type_name":"同城快递"},{"service_type":"4","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiajuchaizhuang.png","service_type_name":"家具拆装"},{"service_type":"5","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_shafahuanmian.png","service_type_name":"沙发换面"},{"service_type":"6","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_banjia.png","service_type_name":"搬家搬运"},{"service_type":"7","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiajutiemo.png","service_type_name":"家具贴膜"},{"service_type":"8","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_kaisuo.png","service_type_name":"开锁服务"},{"service_type":"9","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_xiudiannao.png","service_type_name":"电脑维修"},{"service_type":"10","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_cizhuanmeifeng.png","service_type_name":"瓷砖美缝"},{"service_type":"11","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiazhengbaojie.png","service_type_name":"家政保洁"},{"service_type":"12","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiaquan.png","service_type_name":"甲醛治理"},{"service_type":"13","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiadianweixu.png","service_type_name":"家电维修"},{"service_type":"14","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiankongweixu.png","service_type_name":"监控维护"},{"service_type":"15","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_guandaoshutong.png","service_type_name":"窗户维修"},{"service_type":"16","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_chekumen.png","service_type_name":"软包房门"},{"service_type":"17","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_diangong.png","service_type_name":"电工服务"},{"service_type":"18","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiadianqingxi.png","service_type_name":"家电清洗"},{"service_type":"19","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_liangcaishifu.png","service_type_name":"墙面大白"},{"service_type":"20","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_bolidingzhi.png","service_type_name":"玻璃定制"},{"service_type":"21","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_shuinuan.png","service_type_name":"水暖服务"},{"service_type":"22","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_mugongfuwu.png","service_type_name":"木工服务"},{"service_type":"23","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_wagongtiezhuan.png","service_type_name":"瓦工贴砖"},{"service_type":"26","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_guandao.png","service_type_name":"管道疏通"},{"service_type":"27","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiajufanxin.png","service_type_name":"家具翻新"},{"service_type":"28","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_chekumen.png","service_type_name":"车库门维修"},{"service_type":"29","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_zhileng.png","service_type_name":"专业制冷"},{"service_type":"30","service_type_img":"http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_baigang.png","service_type_name":"白钢装饰"}]}]
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
        private List<CraftsManListBean> craftsManList;
        private List<IconListBean> iconList;

        public List<CraftsManListBean> getCraftsManList() {
            return craftsManList;
        }

        public void setCraftsManList(List<CraftsManListBean> craftsManList) {
            this.craftsManList = craftsManList;
        }

        public List<IconListBean> getIconList() {
            return iconList;
        }

        public void setIconList(List<IconListBean> iconList) {
            this.iconList = iconList;
        }

        public static class CraftsManListBean {
            /**
             * ir_audit_state : 2
             * service_type_list : [{"service_type_name":"家具维修"},{"service_type_name":"沙发清洗"},{"service_type_name":"同城快递"},{"service_type_name":"家具拆装"},{"service_type_name":"沙发换面"}]
             * meter : 6608.9
             * x : 45.662479
             * ir_personnal_img_url : https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12018
             * y : 126.611618
             * ir_personnal_name : 小朱
             * addr : 黑龙江省哈尔滨市道里区
             */
            public String ir_id;
            private String ir_audit_state;
            private String meter;
            private String x;
            private String ir_personnal_img_url;
            private String y;
            private String ir_personnal_name;
            private String addr;
            private List<ServiceTypeListBean> service_type_list;

            public String getIr_audit_state() {
                return ir_audit_state;
            }

            public void setIr_audit_state(String ir_audit_state) {
                this.ir_audit_state = ir_audit_state;
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

            public String getIr_personnal_img_url() {
                return ir_personnal_img_url;
            }

            public void setIr_personnal_img_url(String ir_personnal_img_url) {
                this.ir_personnal_img_url = ir_personnal_img_url;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getIr_personnal_name() {
                return ir_personnal_name;
            }

            public void setIr_personnal_name(String ir_personnal_name) {
                this.ir_personnal_name = ir_personnal_name;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public List<ServiceTypeListBean> getService_type_list() {
                return service_type_list;
            }

            public void setService_type_list(List<ServiceTypeListBean> service_type_list) {
                this.service_type_list = service_type_list;
            }

            public static class ServiceTypeListBean {
                /**
                 * service_type_name : 家具维修
                 */

                private String service_type_name;

                public String getService_type_name() {
                    return service_type_name;
                }

                public void setService_type_name(String service_type_name) {
                    this.service_type_name = service_type_name;
                }
            }
        }

        public static class IconListBean {
            /**
             * service_type : 1
             * service_type_img : http://znjj-img.oss-cn-hangzhou.aliyuncs.com/home_button30_jiajuweixiu.png
             * service_type_name : 家具维修
             */

            private String service_type;
            private String service_type_img;
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
        }
    }
}
