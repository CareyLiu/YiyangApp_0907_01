package com.yiyang.cn.activity.tuya_device.camera.model;

public class TuyaKongzhiModel {

    private int iconId;
    private int iconIdS;
    private String name;
    private boolean isSelect;
    private boolean isCanClick;

    public TuyaKongzhiModel(int iconId, String name) {
        this.iconId = iconId;
        this.name = name;
    }

    public TuyaKongzhiModel(int iconId, int iconIdS, String name) {
        this.iconId = iconId;
        this.iconIdS = iconIdS;
        this.name = name;
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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getIconIdS() {
        return iconIdS;
    }

    public void setIconIdS(int iconIdS) {
        this.iconIdS = iconIdS;
    }

    public boolean isCanClick() {
        return isCanClick;
    }

    public void setCanClick(boolean canClick) {
        isCanClick = canClick;
    }
}
