package com.yiyang.cn.model;

import java.util.List;

public class ShuJuQuXianModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"tem_list":[{"device_tem":"13.6","time":"01"}],"hum_list":[{"device_hum":"40.5","time":"01"}]}]
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
        private List<TemListBean> tem_list;
        private List<HumListBean> hum_list;

        public List<TemListBean> getTem_list() {
            return tem_list;
        }

        public void setTem_list(List<TemListBean> tem_list) {
            this.tem_list = tem_list;
        }

        public List<HumListBean> getHum_list() {
            return hum_list;
        }

        public void setHum_list(List<HumListBean> hum_list) {
            this.hum_list = hum_list;
        }

        public static class TemListBean {
            /**
             * device_tem : 13.6
             * time : 01
             */

            private String device_tem;
            private String time;

            public String getDevice_tem() {
                return device_tem;
            }

            public void setDevice_tem(String device_tem) {
                this.device_tem = device_tem;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class HumListBean {
            /**
             * device_hum : 40.5
             * time : 01
             */

            private String device_hum;
            private String time;

            public String getDevice_hum() {
                return device_hum;
            }

            public void setDevice_hum(String device_hum) {
                this.device_hum = device_hum;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
