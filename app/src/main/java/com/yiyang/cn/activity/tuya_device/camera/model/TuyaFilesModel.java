package com.yiyang.cn.activity.tuya_device.camera.model;

public class TuyaFilesModel {
    private int type;//1图片  2视频
    private String filePath;
    private boolean isSelect;

    public TuyaFilesModel(int type, String filePath) {
        this.type = type;
        this.filePath = filePath;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
