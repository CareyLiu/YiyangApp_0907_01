package com.yiyang.cn.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.model.Home;

/**
 * Created by Sl on 2019/6/12.
 */

public class GroupBuyAdapter extends ListBaseAdapter<Home.DataBean.ShopListBean> {
    public GroupBuyAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_group_buy;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        ImageView iv_pic = holder.getView(R.id.iv_pic);
        TextView tv_goods_name = holder.getView(R.id.tv_goods_name);
        TextView tv_shop_name = holder.getView(R.id.tv_goods_name);
        TextView tv_price = holder.getView(R.id.tv_price);
        TextView tv_sold_count = holder.getView(R.id.tv_sold_count);

        Glide.with(mContext).load(getDataList().get(position).getIndex_photo_url()).into(iv_pic);
        tv_goods_name.setText(getDataList().get(position).getProduct_title());
        tv_shop_name.setText(getDataList().get(position).getInst_name());
        tv_price.setText(getDataList().get(position).getMoney_make());
        tv_sold_count.setText(getDataList().get(position).getWares_sales_volume());
    }
}
