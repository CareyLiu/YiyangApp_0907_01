package com.yiyang.cn.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.model.Home;

/**
 * Created by Sl on 2019/6/12.
 */

public class HotGoodsAdapter extends ListBaseAdapter<Home.DataBean.IndexShowListBean> {
    public HotGoodsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_hot;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        ImageView iv_pic = holder.getView(R.id.iv_pic);
        TextView tv_reduce = holder.getView(R.id.tv_reduce);
        TextView tv_goods_name = holder.getView(R.id.tv_goods_name);
        TextView tv_price = holder.getView(R.id.tv_price);

        Glide.with(mContext).load(getDataList().get(position).getIndex_photo_url()).into(iv_pic);
        tv_goods_name.setText(getDataList().get(position).getWares_name());
        tv_price.setText(getDataList().get(position).getMoney_now());
        tv_reduce.setText("直降 "+getDataList().get(position).getMoney_lower());
    }

}
