package com.yiyang.cn.model;

import java.util.List;

public class ChandiModel {


    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"code_name":"北京","cl":[{"code_id":"1101","code_name":"北京市","code_id_up":"11","cl":[{"code_id_up":"1101","code_id":"110101","code_name":"东城区"},{"code_id_up":"1101","code_id":"110102","code_name":"西城区"},{"code_id_up":"1101","code_id":"110103","code_name":"崇文区"},{"code_id_up":"1101","code_id":"110104","code_name":"宣武区"},{"code_id_up":"1101","code_id":"110105","code_name":"朝阳区"},{"code_id_up":"1101","code_id":"110106","code_name":"丰台区"},{"code_id_up":"1101","code_id":"110107","code_name":"石景山区"},{"code_id_up":"1101","code_id":"110108","code_name":"海淀区"},{"code_id_up":"1101","code_id":"110109","code_name":"门头沟区"},{"code_id_up":"1101","code_id":"110111","code_name":"房山区"},{"code_id_up":"1101","code_id":"110112","code_name":"通州区"},{"code_id_up":"1101","code_id":"110113","code_name":"顺义区"},{"code_id_up":"1101","code_id":"110114","code_name":"昌平区"},{"code_id_up":"1101","code_id":"110115","code_name":"大兴区"},{"code_id_up":"1101","code_id":"110116","code_name":"怀柔区"},{"code_id_up":"1101","code_id":"110117","code_name":"平谷区"},{"code_id_up":"1101","code_id":"110228","code_name":"密云县"},{"code_id_up":"1101","code_id":"110229","code_name":"延庆县"}]}],"code_id":"11"},{"code_name":"天津","cl":[{"code_id":"1201","code_name":"天津市","code_id_up":"12","cl":[{"code_id_up":"1201","code_id":"120101","code_name":"和平区"},{"code_id_up":"1201","code_id":"120102","code_name":"河东区"},{"code_id_up":"1201","code_id":"120103","code_name":"河西区"},{"code_id_up":"1201","code_id":"120104","code_name":"南开区"},{"code_id_up":"1201","code_id":"120105","code_name":"河北区"},{"code_id_up":"1201","code_id":"120106","code_name":"红桥区"},{"code_id_up":"1201","code_id":"120107","code_name":"塘沽区"},{"code_id_up":"1201","code_id":"120108","code_name":"汉沽区"},{"code_id_up":"1201","code_id":"120109","code_name":"大港区"},{"code_id_up":"1201","code_id":"120110","code_name":"东丽区"},{"code_id_up":"1201","code_id":"120111","code_name":"西青区"},{"code_id_up":"1201","code_id":"120112","code_name":"津南区"},{"code_id_up":"1201","code_id":"120113","code_name":"北辰区"},{"code_id_up":"1201","code_id":"120114","code_name":"武清区"},{"code_id_up":"1201","code_id":"120115","code_name":"宝坻区"}]},{"code_id":"1202","code_name":"市辖县","code_id_up":"12","cl":[{"code_id_up":"1202","code_id":"120221","code_name":"宁河县"},{"code_id_up":"1202","code_id":"120223","code_name":"静海县"},{"code_id_up":"1202","code_id":"120225","code_name":"蓟县"}]}],"code_id":"12"}]
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
         * code_name : 北京
         * cl : [{"code_id":"1101","code_name":"北京市","code_id_up":"11","cl":[{"code_id_up":"1101","code_id":"110101","code_name":"东城区"},{"code_id_up":"1101","code_id":"110102","code_name":"西城区"},{"code_id_up":"1101","code_id":"110103","code_name":"崇文区"},{"code_id_up":"1101","code_id":"110104","code_name":"宣武区"},{"code_id_up":"1101","code_id":"110105","code_name":"朝阳区"},{"code_id_up":"1101","code_id":"110106","code_name":"丰台区"},{"code_id_up":"1101","code_id":"110107","code_name":"石景山区"},{"code_id_up":"1101","code_id":"110108","code_name":"海淀区"},{"code_id_up":"1101","code_id":"110109","code_name":"门头沟区"},{"code_id_up":"1101","code_id":"110111","code_name":"房山区"},{"code_id_up":"1101","code_id":"110112","code_name":"通州区"},{"code_id_up":"1101","code_id":"110113","code_name":"顺义区"},{"code_id_up":"1101","code_id":"110114","code_name":"昌平区"},{"code_id_up":"1101","code_id":"110115","code_name":"大兴区"},{"code_id_up":"1101","code_id":"110116","code_name":"怀柔区"},{"code_id_up":"1101","code_id":"110117","code_name":"平谷区"},{"code_id_up":"1101","code_id":"110228","code_name":"密云县"},{"code_id_up":"1101","code_id":"110229","code_name":"延庆县"}]}]
         * code_id : 11
         */

        private String code_name;
        private String code_id;
        private List<ClBeanX> cl;

        public String getCode_name() {
            return code_name;
        }

        public void setCode_name(String code_name) {
            this.code_name = code_name;
        }

        public String getCode_id() {
            return code_id;
        }

        public void setCode_id(String code_id) {
            this.code_id = code_id;
        }

        public List<ClBeanX> getCl() {
            return cl;
        }

        public void setCl(List<ClBeanX> cl) {
            this.cl = cl;
        }

        public static class ClBeanX {
            /**
             * code_id : 1101
             * code_name : 北京市
             * code_id_up : 11
             * cl : [{"code_id_up":"1101","code_id":"110101","code_name":"东城区"},{"code_id_up":"1101","code_id":"110102","code_name":"西城区"},{"code_id_up":"1101","code_id":"110103","code_name":"崇文区"},{"code_id_up":"1101","code_id":"110104","code_name":"宣武区"},{"code_id_up":"1101","code_id":"110105","code_name":"朝阳区"},{"code_id_up":"1101","code_id":"110106","code_name":"丰台区"},{"code_id_up":"1101","code_id":"110107","code_name":"石景山区"},{"code_id_up":"1101","code_id":"110108","code_name":"海淀区"},{"code_id_up":"1101","code_id":"110109","code_name":"门头沟区"},{"code_id_up":"1101","code_id":"110111","code_name":"房山区"},{"code_id_up":"1101","code_id":"110112","code_name":"通州区"},{"code_id_up":"1101","code_id":"110113","code_name":"顺义区"},{"code_id_up":"1101","code_id":"110114","code_name":"昌平区"},{"code_id_up":"1101","code_id":"110115","code_name":"大兴区"},{"code_id_up":"1101","code_id":"110116","code_name":"怀柔区"},{"code_id_up":"1101","code_id":"110117","code_name":"平谷区"},{"code_id_up":"1101","code_id":"110228","code_name":"密云县"},{"code_id_up":"1101","code_id":"110229","code_name":"延庆县"}]
             */

            private String code_id;
            private String code_name;
            private String code_id_up;
            private List<ClBean> cl;

            public String getCode_id() {
                return code_id;
            }

            public void setCode_id(String code_id) {
                this.code_id = code_id;
            }

            public String getCode_name() {
                return code_name;
            }

            public void setCode_name(String code_name) {
                this.code_name = code_name;
            }

            public String getCode_id_up() {
                return code_id_up;
            }

            public void setCode_id_up(String code_id_up) {
                this.code_id_up = code_id_up;
            }

            public List<ClBean> getCl() {
                return cl;
            }

            public void setCl(List<ClBean> cl) {
                this.cl = cl;
            }

            public static class ClBean {
                /**
                 * code_id_up : 1101
                 * code_id : 110101
                 * code_name : 东城区
                 */

                private String code_id_up;
                private String code_id;
                private String code_name;

                public String getCode_id_up() {
                    return code_id_up;
                }

                public void setCode_id_up(String code_id_up) {
                    this.code_id_up = code_id_up;
                }

                public String getCode_id() {
                    return code_id;
                }

                public void setCode_id(String code_id) {
                    this.code_id = code_id;
                }

                public String getCode_name() {
                    return code_name;
                }

                public void setCode_name(String code_name) {
                    this.code_name = code_name;
                }
            }
        }
    }
}
