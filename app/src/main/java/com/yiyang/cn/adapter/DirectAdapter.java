package com.yiyang.cn.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.model.Home;

/**
 * Created by Sl on 2019/6/12.
 *
 */

public class DirectAdapter extends ListBaseAdapter<Home.DataBean.ProShowListBean> {
    public DirectAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_direct;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        TextView tv_goods_name = holder.getView(R.id.tv_goods_name);
        TextView tv_specs = holder.getView(R.id.tv_specs);
        TextView tv_price = holder.getView(R.id.tv_price);
        ImageView iv_pic = holder.getView(R.id.iv_pic);

        Glide.with(mContext).load(getDataList().get(position).getIndex_photo_url()).into(iv_pic);
        tv_goods_name.setText(getDataList().get(position).getWares_name());
        tv_specs.setText(getDataList().get(position).getProduct_title());
        tv_price.setText(getDataList().get(position).getMoney_now());


    }
}
