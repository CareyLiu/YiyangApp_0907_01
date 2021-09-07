package com.yiyang.cn.model;

import java.util.List;

public class BianMinFaBuBean {
    //便民发布标题
    public String biaoTi;
    //便民发布栏目类型
    public String lanMuLeiXing;
    //便民发布栏目类型名称
    public String lanMuLeiXingMingCheng;
    //便民发布栏目类别
    public String lanMuLeiBie;
    //便民发布栏目类别名称
    public String lanMuLeiBieMingCheng;
    //pro
    public List<ProBean> proBeanList;
    //信息简介/内容描述
    public String neiRongMiaoShu;
    //地址
    public String diZhi;
    //联系电话
    public String lianXiDianHua;
    //微信号
    public String weiXinHao;
    //联系人姓名
    public String lianXiRenXingMing;

    public static class ProBean {
        public String type;
        public String ir_img_id;
        public String ir_img_url;
    }

    //纬度
    public String weiDu;
    //经度
    public String jingDu;
}
