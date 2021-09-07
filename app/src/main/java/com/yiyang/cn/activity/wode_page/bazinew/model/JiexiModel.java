package com.yiyang.cn.activity.wode_page.bazinew.model;

import java.util.List;

public class JiexiModel {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"birthday":"一九九九年六月初一 子时","sex":"男","name":"男","birthday_type":"阴历","minggong":"minggong","xiongdi":"xiongdi","fuqi":"fuqi","zinv":"zinv","caibo":"caibo","jie":"jie","qianyi":"qianyi","puyi":"puyi","guanlu":"guanlu","tianzhai":"tianzhai","fude":"fude","fumu":"fumu"}]
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
         * birthday : 一九九九年六月初一 子时
         * sex : 男
         * name : 男
         * birthday_type : 阴历
         * minggong : minggong
         * xiongdi : xiongdi
         * fuqi : fuqi
         * zinv : zinv
         * caibo : caibo
         * jie : jie
         * qianyi : qianyi
         * puyi : puyi
         * guanlu : guanlu
         * tianzhai : tianzhai
         * fude : fude
         * fumu : fumu
         */

        private String birthday;
        private String sex;
        private String name;
        private String birthday_type;
        private String minggong;
        private String xiongdi;
        private String fuqi;
        private String zinv;
        private String caibo;
        private String jie;
        private String qianyi;
        private String puyi;
        private String guanlu;
        private String tianzhai;
        private String fude;
        private String fumu;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
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

        public String getBirthday_type() {
            return birthday_type;
        }

        public void setBirthday_type(String birthday_type) {
            this.birthday_type = birthday_type;
        }

        public String getMinggong() {
            return minggong;
        }

        public void setMinggong(String minggong) {
            this.minggong = minggong;
        }

        public String getXiongdi() {
            return xiongdi;
        }

        public void setXiongdi(String xiongdi) {
            this.xiongdi = xiongdi;
        }

        public String getFuqi() {
            return fuqi;
        }

        public void setFuqi(String fuqi) {
            this.fuqi = fuqi;
        }

        public String getZinv() {
            return zinv;
        }

        public void setZinv(String zinv) {
            this.zinv = zinv;
        }

        public String getCaibo() {
            return caibo;
        }

        public void setCaibo(String caibo) {
            this.caibo = caibo;
        }

        public String getJie() {
            return jie;
        }

        public void setJie(String jie) {
            this.jie = jie;
        }

        public String getQianyi() {
            return qianyi;
        }

        public void setQianyi(String qianyi) {
            this.qianyi = qianyi;
        }

        public String getPuyi() {
            return puyi;
        }

        public void setPuyi(String puyi) {
            this.puyi = puyi;
        }

        public String getGuanlu() {
            return guanlu;
        }

        public void setGuanlu(String guanlu) {
            this.guanlu = guanlu;
        }

        public String getTianzhai() {
            return tianzhai;
        }

        public void setTianzhai(String tianzhai) {
            this.tianzhai = tianzhai;
        }

        public String getFude() {
            return fude;
        }

        public void setFude(String fude) {
            this.fude = fude;
        }

        public String getFumu() {
            return fumu;
        }

        public void setFumu(String fumu) {
            this.fumu = fumu;
        }
    }
}
