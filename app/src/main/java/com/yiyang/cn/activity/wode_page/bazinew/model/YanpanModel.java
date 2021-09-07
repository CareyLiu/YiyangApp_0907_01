package com.yiyang.cn.activity.wode_page.bazinew.model;

import java.util.List;

public class YanpanModel {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"month":["农历2019年7月(2019年08月01日~2019年08月29日)","农历2019年8月(2019年08月30日~2019年09月28日)","农历2020年2月(2020年02月23日~2020年03月23日)","农历2020年5月(2020年06月21日~2020年07月20日)"],"year":["农历2010年(2010年02月14日~2011年02月02日)","农历2014年(2014年01月31日~2015年02月18日)"]}]
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
        private List<String> month;
        private List<String> year;

        public List<String> getMonth() {
            return month;
        }

        public void setMonth(List<String> month) {
            this.month = month;
        }

        public List<String> getYear() {
            return year;
        }

        public void setYear(List<String> year) {
            this.year = year;
        }
    }
}
