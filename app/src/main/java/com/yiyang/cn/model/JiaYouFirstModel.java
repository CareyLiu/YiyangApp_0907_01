package com.yiyang.cn.model;

import java.util.List;

public class JiaYouFirstModel {

    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"oil_list":[{"inst_state":"1","priceYfq":"4.64","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10798","distance":"12.9","gasType":"10","x":"45.731192","y":"126.78136","inst_id":"395","inst_name":"道里石化","priceOfficial":"5.91","addr":"黑龙江省哈尔滨市道里区建国街119号","jiang":"1.27"}],"oil_select_list":[{"oil_type_name":"汽油","oil_array":[{"id":"11","name":"90#","isSelect":"0"},{"id":"12","name":"92#","isSelect":"1"},{"id":"13","name":"95#","isSelect":"0"},{"id":"14","name":"98#","isSelect":"0"},{"id":"15","name":"101#","isSelect":"0"}],"oil_type":"1"},{"oil_type_name":"柴油","oil_array":[{"id":"21","name":"-40#","isSelect":"0"},{"id":"22","name":"-35#","isSelect":"0"},{"id":"23","name":"-30#","isSelect":"0"},{"id":"24","name":"-20#","isSelect":"0"},{"id":"25","name":"国四0#","isSelect":"0"},{"id":"26","name":"0#","isSelect":"0"},{"id":"27","name":"-1001#","isSelect":"0"},{"id":"28","name":"-2001#","isSelect":"0"},{"id":"29","name":"-3501#","isSelect":"0"}],"oil_type":"2"},{"oil_type_name":"天然气","oil_array":[{"id":"31","name":"CNG","isSelect":"0"},{"id":"32","name":"LNG","isSelect":"0"}],"oil_type":"3"}],"order_list":[{"name":"智能排序","isSelect":"1","id":"1"},{"name":"价格优先","isSelect":"0","id":"2"},{"name":"距离优先","isSelect":"0","id":"3"}],"brand_list":[{"brand_array":[{"id":"1","name":"中国石化","isSelect":"1"},{"id":"10","name":"众诚石油","isSelect":"1"},{"id":"11","name":"和顺石油","isSelect":"1"},{"id":"12","name":"大桥石化","isSelect":"1"},{"id":"13","name":"山东石化","isSelect":"1"},{"id":"14","name":"美福石油","isSelect":"1"},{"id":"15","name":"强林石化","isSelect":"1"},{"id":"16","name":"中图能源","isSelect":"1"},{"id":"17","name":"京博石化","isSelect":"1"},{"id":"18","name":"悦孚石化","isSelect":"1"},{"id":"19","name":"东明石化","isSelect":"1"},{"id":"2","name":"中国石油","isSelect":"1"},{"id":"20","name":"其他","isSelect":"1"},{"id":"3","name":"壳牌","isSelect":"1"},{"id":"4","name":"中国海油","isSelect":"1"},{"id":"5","name":"中化石油","isSelect":"1"},{"id":"6","name":"中国航油","isSelect":"1"},{"id":"7","name":"道达尔","isSelect":"1"},{"id":"8","name":"加德士","isSelect":"1"},{"id":"9","name":"金盾石化","isSelect":"1"}],"whether_all":"1"}],"diatance_list":[{"name":"6","isSelect":"0","id":"1"},{"name":"10","isSelect":"0","id":"2"},{"name":"15","isSelect":"0","id":"3"},{"name":"20","isSelect":"0","id":"4"},{"name":"50","isSelect":"1","id":"5"}]}]
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
        private List<OilListBean> oil_list;
        private List<OilSelectListBean> oil_select_list;
        private List<OrderListBean> order_list;
        private List<BrandListBean> brand_list;
        private List<DiatanceListBean> diatance_list;

        public List<OilListBean> getOil_list() {
            return oil_list;
        }

        public void setOil_list(List<OilListBean> oil_list) {
            this.oil_list = oil_list;
        }

        public List<OilSelectListBean> getOil_select_list() {
            return oil_select_list;
        }

        public void setOil_select_list(List<OilSelectListBean> oil_select_list) {
            this.oil_select_list = oil_select_list;
        }

        public List<OrderListBean> getOrder_list() {
            return order_list;
        }

        public void setOrder_list(List<OrderListBean> order_list) {
            this.order_list = order_list;
        }

        public List<BrandListBean> getBrand_list() {
            return brand_list;
        }

        public void setBrand_list(List<BrandListBean> brand_list) {
            this.brand_list = brand_list;
        }

        public List<DiatanceListBean> getDiatance_list() {
            return diatance_list;
        }

        public void setDiatance_list(List<DiatanceListBean> diatance_list) {
            this.diatance_list = diatance_list;
        }

        public static class OilListBean {
            /**
             * inst_state : 1
             * priceYfq : 4.64
             * inst_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10798
             * distance : 12.9
             * gasType : 10
             * x : 45.731192
             * y : 126.78136
             * inst_id : 395
             * inst_name : 道里石化
             * priceOfficial : 5.91
             * addr : 黑龙江省哈尔滨市道里区建国街119号
             * jiang : 1.27
             */

            private String inst_state;
            private String priceYfq;
            private String inst_photo_url;
            private String distance;
            private String gasType;
            private String x;
            private String y;
            private String inst_id;
            private String inst_name;
            private String priceOfficial;
            private String addr;
            private String jiang;

            private String oilType;

            public String getOilType() {
                return oilType;
            }

            public void setOilType(String oilType) {
                this.oilType = oilType;
            }

            public String getOilNo() {
                return oilNo;
            }

            public void setOilNo(String oilNo) {
                this.oilNo = oilNo;
            }

            private String oilNo;

            public String getInst_state() {
                return inst_state;
            }

            public void setInst_state(String inst_state) {
                this.inst_state = inst_state;
            }

            public String getPriceYfq() {
                return priceYfq;
            }

            public void setPriceYfq(String priceYfq) {
                this.priceYfq = priceYfq;
            }

            public String getInst_photo_url() {
                return inst_photo_url;
            }

            public void setInst_photo_url(String inst_photo_url) {
                this.inst_photo_url = inst_photo_url;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getGasType() {
                return gasType;
            }

            public void setGasType(String gasType) {
                this.gasType = gasType;
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

            public String getPriceOfficial() {
                return priceOfficial;
            }

            public void setPriceOfficial(String priceOfficial) {
                this.priceOfficial = priceOfficial;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getJiang() {
                return jiang;
            }

            public void setJiang(String jiang) {
                this.jiang = jiang;
            }
        }

        public static class OilSelectListBean {
            /**
             * oil_type_name : 汽油
             * oil_array : [{"id":"11","name":"90#","isSelect":"0"},{"id":"12","name":"92#","isSelect":"1"},{"id":"13","name":"95#","isSelect":"0"},{"id":"14","name":"98#","isSelect":"0"},{"id":"15","name":"101#","isSelect":"0"}]
             * oil_type : 1
             */

            private String oil_type_name;
            private String oil_type;
            private List<OilArrayBean> oil_array;

            public String getOil_type_name() {
                return oil_type_name;
            }

            public void setOil_type_name(String oil_type_name) {
                this.oil_type_name = oil_type_name;
            }

            public String getOil_type() {
                return oil_type;
            }

            public void setOil_type(String oil_type) {
                this.oil_type = oil_type;
            }

            public List<OilArrayBean> getOil_array() {
                return oil_array;
            }

            public void setOil_array(List<OilArrayBean> oil_array) {
                this.oil_array = oil_array;
            }

            public static class OilArrayBean {
                /**
                 * id : 11
                 * name : 90#
                 * isSelect : 0
                 */

                private String id;
                private String name;
                private String isSelect;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIsSelect() {
                    return isSelect;
                }

                public void setIsSelect(String isSelect) {
                    this.isSelect = isSelect;
                }
            }
        }

        public static class OrderListBean {
            /**
             * name : 智能排序
             * isSelect : 1
             * id : 1
             */

            private String name;
            private String isSelect;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIsSelect() {
                return isSelect;
            }

            public void setIsSelect(String isSelect) {
                this.isSelect = isSelect;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class BrandListBean {
            /**
             * brand_array : [{"id":"1","name":"中国石化","isSelect":"1"},{"id":"10","name":"众诚石油","isSelect":"1"},{"id":"11","name":"和顺石油","isSelect":"1"},{"id":"12","name":"大桥石化","isSelect":"1"},{"id":"13","name":"山东石化","isSelect":"1"},{"id":"14","name":"美福石油","isSelect":"1"},{"id":"15","name":"强林石化","isSelect":"1"},{"id":"16","name":"中图能源","isSelect":"1"},{"id":"17","name":"京博石化","isSelect":"1"},{"id":"18","name":"悦孚石化","isSelect":"1"},{"id":"19","name":"东明石化","isSelect":"1"},{"id":"2","name":"中国石油","isSelect":"1"},{"id":"20","name":"其他","isSelect":"1"},{"id":"3","name":"壳牌","isSelect":"1"},{"id":"4","name":"中国海油","isSelect":"1"},{"id":"5","name":"中化石油","isSelect":"1"},{"id":"6","name":"中国航油","isSelect":"1"},{"id":"7","name":"道达尔","isSelect":"1"},{"id":"8","name":"加德士","isSelect":"1"},{"id":"9","name":"金盾石化","isSelect":"1"}]
             * whether_all : 1
             */

            private String whether_all;
            private List<BrandArrayBean> brand_array;

            public String getWhether_all() {
                return whether_all;
            }

            public void setWhether_all(String whether_all) {
                this.whether_all = whether_all;
            }

            public List<BrandArrayBean> getBrand_array() {
                return brand_array;
            }

            public void setBrand_array(List<BrandArrayBean> brand_array) {
                this.brand_array = brand_array;
            }

            public static class BrandArrayBean {
                /**
                 * id : 1
                 * name : 中国石化
                 * isSelect : 1
                 */

                private String id;
                private String name;
                private String isSelect;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIsSelect() {
                    return isSelect;
                }

                public void setIsSelect(String isSelect) {
                    this.isSelect = isSelect;
                }
            }
        }

        public static class DiatanceListBean {
            /**
             * name : 6
             * isSelect : 0
             * id : 1
             */

            private String name;
            private String isSelect;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIsSelect() {
                return isSelect;
            }

            public void setIsSelect(String isSelect) {
                this.isSelect = isSelect;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
