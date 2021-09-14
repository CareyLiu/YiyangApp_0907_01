package com.yiyang.cn.model.yiyang;

public class XiaoxiModel {
    private String name;
    private String time;
    private int num;

    public XiaoxiModel(String name, String time, int num) {
        this.name = name;
        this.time = time;
        this.num = num;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
