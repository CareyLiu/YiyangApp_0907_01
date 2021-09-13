package com.yiyang.cn.model.yiyang;

public class AnfangModel {

    private int iconId;
    private String name;
    private String time;
    private int state;//1在线  2中断

    public AnfangModel(int iconId, String name, String time, int state) {
        this.iconId = iconId;
        this.name = name;
        this.time = time;
        this.state = state;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
