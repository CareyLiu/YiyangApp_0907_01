package com.yiyang.cn.project_A.zijian_interface;

//自建商城 - 首页
public interface ZiJianHomeInterface {

    /**
     * 访问网络
     */
    void getNet();//请求网络

    /**
     * 实现顶部banner
     */
    void implBanner(); //实现顶部banner

    /**
     * 实现中间列表
     */
    void implHortialList();//实现中间的列表

    /**
     * 实现底部的list
     */
    void implBottomList();//实现底部list

    /**
     * 实现刷新功能
     */
    void implRefresh();//刷新和加载更多

    /**
     * 实现加载更多功能
     */
    void implLoadMore();


    //点击顶部列表
    void onClickHeaderList();

    //点击底部列表
    void onClickFooterList();
}
