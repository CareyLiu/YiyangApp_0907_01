package com.yiyang.cn.model;

import java.io.Serializable;

public class ChangJingZhiXingModel implements Serializable {
    public String device_id_one;
    public String device_name_one;
    //二级设备类型id
    public String device_id_two;
    //设备类型名称
    public String device_name_two;
    //我的智能设备id
    public String user_device_id;
    //执行方式：
    //1.开 2.关
    public String pro_go_one;
    //设备名称
    public String device_name;
    //设备图标url
    public String img_url;
    //二级设备类型id
}
