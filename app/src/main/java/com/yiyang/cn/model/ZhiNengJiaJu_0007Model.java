package com.yiyang.cn.model;

import java.util.List;

public class ZhiNengJiaJu_0007Model {

    /**
     * match_list : [{"device_name":"窗帘电机","device_type_pic":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12013","cd_device_ccid":"16020101"}]
     * code : jyj_0007
     * type : 5
     */

    private String code;
    private String type;
    private List<MatchListBean> match_list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MatchListBean> getMatch_list() {
        return match_list;
    }

    public void setMatch_list(List<MatchListBean> match_list) {
        this.match_list = match_list;
    }

    public static class MatchListBean {
        /**
         * device_name : 窗帘电机
         * device_type_pic : https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12013
         * cd_device_ccid : 16020101
         */

        private String device_name;
        private String device_type_pic;
        private String cd_device_ccid;

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_type_pic() {
            return device_type_pic;
        }

        public void setDevice_type_pic(String device_type_pic) {
            this.device_type_pic = device_type_pic;
        }

        public String getCd_device_ccid() {
            return cd_device_ccid;
        }

        public void setCd_device_ccid(String cd_device_ccid) {
            this.cd_device_ccid = cd_device_ccid;
        }
    }
}
