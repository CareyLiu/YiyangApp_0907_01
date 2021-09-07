package com.yiyang.cn.model;

import java.util.List;

public class KongQiJianCeModel  {


    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"gd_list":[{"gd_particulate_matter":"750.0","time":"11:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"12:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"13:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"14:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"15:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"16:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"17:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"18:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"19:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"20:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"21:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"22:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"},{"gd_particulate_matter":"750.0","time":"23:02","gd_carbon_dioxide":"1800.0","gd_cascophen":"400.0","gd_air_quality":"3524.0"}]}]
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
        private List<GdListBean> gd_list;


        public List<GdListBean> getGd_list() {
            return gd_list;
        }

        public void setGd_list(List<GdListBean> gd_list) {
            this.gd_list = gd_list;
        }

        public static class GdListBean {
            /**
             * gd_particulate_matter : 750.0
             * time : 11:02
             * gd_carbon_dioxide : 1800.0
             * gd_cascophen : 400.0
             * gd_air_quality : 3524.0
             */

            private String gd_particulate_matter;
            private String time;
            private String gd_carbon_dioxide;
            private String gd_cascophen;
            private String gd_air_quality;

            public String getGd_particulate_matter() {
                return gd_particulate_matter;
            }

            public void setGd_particulate_matter(String gd_particulate_matter) {
                this.gd_particulate_matter = gd_particulate_matter;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getGd_carbon_dioxide() {
                return gd_carbon_dioxide;
            }

            public void setGd_carbon_dioxide(String gd_carbon_dioxide) {
                this.gd_carbon_dioxide = gd_carbon_dioxide;
            }

            public String getGd_cascophen() {
                return gd_cascophen;
            }

            public void setGd_cascophen(String gd_cascophen) {
                this.gd_cascophen = gd_cascophen;
            }

            public String getGd_air_quality() {
                return gd_air_quality;
            }

            public void setGd_air_quality(String gd_air_quality) {
                this.gd_air_quality = gd_air_quality;
            }
        }
    }
}
