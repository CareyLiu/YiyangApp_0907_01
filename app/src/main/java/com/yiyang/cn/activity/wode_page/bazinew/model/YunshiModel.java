package com.yiyang.cn.activity.wode_page.bazinew.model;

import java.util.List;

public class YunshiModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"birthday":"1992年11月23日 戌时","lunar_birthday":"一九九二年十月廿九 戌时","time_text":"2018年02月16日~2019年02月04日(农历2018年)运势分析","birthday_type":"阳历","ex_text":"戊运年：（吉）运程颇吉，可独资创业或扩充.未婚者成婚,已婚者宜注意感情困扰。防损友。父母滞运。\n\u203b此年在外，人际关系佳，与人相处融洽，多利用此阶段多交好朋友。\n\u203b此年如有任何事情，朋友、同学可拉你一把。\n\u203b此年在合伙的关系上，会有贵人出面办理。\n\u203b此年男女感情易有波折，忍过此阶段，即可拨云见雾。\n","ex_hour":"","sex":"男","name":"哦哟喂","lock":"1","solar_birthday":"1992年11月23日 戌时"}]
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
         * birthday : 1992年11月23日 戌时
         * lunar_birthday : 一九九二年十月廿九 戌时
         * time_text : 2018年02月16日~2019年02月04日(农历2018年)运势分析
         * birthday_type : 阳历
         * ex_text : 戊运年：（吉）运程颇吉，可独资创业或扩充.未婚者成婚,已婚者宜注意感情困扰。防损友。父母滞运。
         ※此年在外，人际关系佳，与人相处融洽，多利用此阶段多交好朋友。
         ※此年如有任何事情，朋友、同学可拉你一把。
         ※此年在合伙的关系上，会有贵人出面办理。
         ※此年男女感情易有波折，忍过此阶段，即可拨云见雾。
         * ex_hour :
         * sex : 男
         * name : 哦哟喂
         * lock : 1
         * solar_birthday : 1992年11月23日 戌时
         */

        private String birthday;
        private String lunar_birthday;
        private String time_text;
        private String birthday_type;
        private String ex_text;
        private String ex_dress;
        private String ex_caishenwei;
        private String ex_hour;
        private String sex;
        private String name;
        private String lock;
        private String solar_birthday;

        public String getEx_dress() {
            return ex_dress;
        }

        public void setEx_dress(String ex_dress) {
            this.ex_dress = ex_dress;
        }

        public String getEx_caishenwei() {
            return ex_caishenwei;
        }

        public void setEx_caishenwei(String ex_caishenwei) {
            this.ex_caishenwei = ex_caishenwei;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getLunar_birthday() {
            return lunar_birthday;
        }

        public void setLunar_birthday(String lunar_birthday) {
            this.lunar_birthday = lunar_birthday;
        }

        public String getTime_text() {
            return time_text;
        }

        public void setTime_text(String time_text) {
            this.time_text = time_text;
        }

        public String getBirthday_type() {
            return birthday_type;
        }

        public void setBirthday_type(String birthday_type) {
            this.birthday_type = birthday_type;
        }

        public String getEx_text() {
            return ex_text;
        }

        public void setEx_text(String ex_text) {
            this.ex_text = ex_text;
        }

        public String getEx_hour() {
            return ex_hour;
        }

        public void setEx_hour(String ex_hour) {
            this.ex_hour = ex_hour;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLock() {
            return lock;
        }

        public void setLock(String lock) {
            this.lock = lock;
        }

        public String getSolar_birthday() {
            return solar_birthday;
        }

        public void setSolar_birthday(String solar_birthday) {
            this.solar_birthday = solar_birthday;
        }
    }
}
