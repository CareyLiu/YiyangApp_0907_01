package com.yiyang.cn.project_A.zijian_interface;

public interface ZijianDetailsInterface {

    void getNet();//请求网络

    void loadList();

    void setHeader();//加载头信息（banner 和产品型号 ，型号可以选择）

    void setFooter();//加载底部信息 （多张图片）

    void setClick();//所有的点击事件
}
