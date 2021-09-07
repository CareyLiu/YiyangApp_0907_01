package com.yiyang.cn.util;

import com.google.gson.Gson;
import com.yiyang.cn.model.DongTaiShiTiModel;

import java.util.List;

public class ShuJuJieXiZhuanHuaTool {
    String data = "";
    public List<String> deviceList;

    public ShuJuJieXiZhuanHuaTool(List<String> deviceList) {
        this.deviceList = deviceList;
    }

    public String jieXiShuJu() {

        for (int i = 0; i < deviceList.size(); i++) {
            DongTaiShiTiModel dongTaiShiTiModel = new DongTaiShiTiModel();
            dongTaiShiTiModel.setName(deviceList.get(i));
            // dongTaiShiTiModel.setCus_room("客厅");
            data = new Gson().toJson(dongTaiShiTiModel) + "\r\n" + data;
        }
        System.out.println("deviceNameList的值：" + data);
        return data;
    }
}
