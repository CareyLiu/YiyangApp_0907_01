package com.yiyang.cn.model;


import com.yiyang.cn.adapter.ExpandableRecyclerAdapter;

import static com.yiyang.cn.adapter.ExpandableRecyclerAdapter.TYPE_HEADER;
import static com.yiyang.cn.adapter.ShopCartExpandAdapter.TYPE_ITEM;

public class GoodsItem extends ExpandableRecyclerAdapter.ListItem  {

    public String shopName;//店铺名称
    public Object shopPic;//店铺图片
    public String goosPackage;//商品套餐
    public String goodsName;//商品名称
    public String goodsPrice;//商品价格
    public String goodsPostage;//邮费
    public Object goodsPic;//商品图片
    public boolean isLast;//是否为最后一项
    public boolean isCheck;//是否被选中

    public GoodsItem(String shopName,Object shopPic) {
        super(TYPE_HEADER);
        this.shopName = shopName;
        this.shopPic = shopPic;
    }

    public GoodsItem(String goodsPostage,String goodsPrice,String goodsName,Object goodsPic,String goodsPackage,boolean isLast, boolean isCheck){
        super(TYPE_ITEM);
        this.goosPackage = goodsPackage;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsPostage = goodsPostage;
        this.goodsPic = goodsPic;
        this.isLast = isLast;
        this.isCheck = isCheck;
    }
}
