package com.yiyang.cn.model;

import java.util.List;

public class YouZhanDetailsModel {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"priceYfq":"5.48","standard_jiang":"0.42","inst_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10798","oil_jiang":"0.12","x":"45.751592","y":"125.59836","inst_id":"377","inst_name":"道里石化","addr":"黑龙江省哈尔滨市道里区建国街119号","oilPriceList":[{"oilType":"1","oilName":"汽油","isSelect":"1","oilNos":[{"oilNo":"90","oilName":"90#","isSelect":"1","gunNos":[{"gunNo":"7"},{"gunNo":"8"},{"gunNo":"9"}]},{"oilNo":"92","oilName":"92#","isSelect":"0","gunNos":[]}]},{"oilType":"2","oilName":"柴油","isSelect":"0","oilNos":[]}]}]
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
         * priceYfq : 5.48
         * standard_jiang : 0.42
         * inst_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10798
         * oil_jiang : 0.12
         * x : 45.751592
         * y : 125.59836
         * inst_id : 377
         * inst_name : 道里石化
         * addr : 黑龙江省哈尔滨市道里区建国街119号
         * oilPriceList : [{"oilType":"1","oilName":"汽油","isSelect":"1","oilNos":[{"oilNo":"90","oilName":"90#","isSelect":"1","gunNos":[{"gunNo":"7"},{"gunNo":"8"},{"gunNo":"9"}]},{"oilNo":"92","oilName":"92#","isSelect":"0","gunNos":[]}]},{"oilType":"2","oilName":"柴油","isSelect":"0","oilNos":[]}]
         */

        private String priceYfq;
        private String standard_jiang;
        private String inst_photo_url;
        private String oil_jiang;
        private String x;
        private String y;
        private String inst_id;
        private String inst_name;
        private String addr;
        private List<OilPriceListBean> oilPriceList;

        public String getPriceYfq() {
            return priceYfq;
        }

        public void setPriceYfq(String priceYfq) {
            this.priceYfq = priceYfq;
        }

        public String getStandard_jiang() {
            return standard_jiang;
        }

        public void setStandard_jiang(String standard_jiang) {
            this.standard_jiang = standard_jiang;
        }

        public String getInst_photo_url() {
            return inst_photo_url;
        }

        public void setInst_photo_url(String inst_photo_url) {
            this.inst_photo_url = inst_photo_url;
        }

        public String getOil_jiang() {
            return oil_jiang;
        }

        public void setOil_jiang(String oil_jiang) {
            this.oil_jiang = oil_jiang;
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

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public List<OilPriceListBean> getOilPriceList() {
            return oilPriceList;
        }

        public void setOilPriceList(List<OilPriceListBean> oilPriceList) {
            this.oilPriceList = oilPriceList;
        }

        public static class OilPriceListBean {
            /**
             * oilType : 1
             * oilName : 汽油
             * isSelect : 1
             * oilNos : [{"oilNo":"90","oilName":"90#","isSelect":"1","gunNos":[{"gunNo":"7"},{"gunNo":"8"},{"gunNo":"9"}]},{"oilNo":"92","oilName":"92#","isSelect":"0","gunNos":[]}]
             */

            private String oilType;
            private String oilName;
            private String isSelect;
            private List<OilNosBean> oilNos;

            public String getOilType() {
                return oilType;
            }

            public void setOilType(String oilType) {
                this.oilType = oilType;
            }

            public String getOilName() {
                return oilName;
            }

            public void setOilName(String oilName) {
                this.oilName = oilName;
            }

            public String getIsSelect() {
                return isSelect;
            }

            public void setIsSelect(String isSelect) {
                this.isSelect = isSelect;
            }

            public List<OilNosBean> getOilNos() {
                return oilNos;
            }

            public void setOilNos(List<OilNosBean> oilNos) {
                this.oilNos = oilNos;
            }

            public static class OilNosBean {
                /**
                 * oilNo : 90
                 * oilName : 90#
                 * isSelect : 1
                 * gunNos : [{"gunNo":"7"},{"gunNo":"8"},{"gunNo":"9"}]
                 */

                private String oilNo;
                private String oilName;
                private String isSelect;
                private List<GunNosBean> gunNos;

                public String getOilNo() {
                    return oilNo;
                }

                public void setOilNo(String oilNo) {
                    this.oilNo = oilNo;
                }

                public String getOilName() {
                    return oilName;
                }

                public void setOilName(String oilName) {
                    this.oilName = oilName;
                }

                public String getIsSelect() {
                    return isSelect;
                }

                public void setIsSelect(String isSelect) {
                    this.isSelect = isSelect;
                }

                public List<GunNosBean> getGunNos() {
                    return gunNos;
                }

                public void setGunNos(List<GunNosBean> gunNos) {
                    this.gunNos = gunNos;
                }

                public static class GunNosBean {
                    public String getIsSelect() {
                        return isSelect;
                    }

                    public void setIsSelect(String isSelect) {
                        this.isSelect = isSelect;
                    }

                    /**
                     * gunNo : 7
                     */

                    private String isSelect="0";
                    private String gunNo;

                    public String getGunNo() {
                        return gunNo;
                    }

                    public void setGunNo(String gunNo) {
                        this.gunNo = gunNo;
                    }
                }
            }
        }
    }
}
