package com.yiyang.cn.model;

import java.util.List;

public class GongJiangRuZhuBean {
    //发布者头像
    public String touXiang;
    //发布者头像图片id
    public String touXiangId;
    //发布者/联系人名称
    public String lianXiRenMingCheng;
    //服务工种
    public String fuWuGongZhong;
    //身份证正面
    public String shenFenZhengZhengMian;
    //身份证正面图片id
    public String shenFenZhengZhengMianId;
    //身份证反面
    public String shenFenZhengFanMian;
    //身份证反面图片id
    public String shenFenZhengFanMianId;
    //纬度
    public String weiDu;
    //经度
    public String jingDu;
    //地址
    public String diZhi;
    //微信号
    public String weiXinHao;
    //个人简介
    public String geRenJianJie;

    //pro
    public List<BianMinFaBuBean.ProBean> proBeanList;

    public static class ProBean {
        public String type;
        public String ir_img_id;
        public String ir_img_url;
    }

}
