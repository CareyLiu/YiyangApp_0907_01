package com.yiyang.cn.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.model.Home;
import com.yiyang.cn.model.SmartDevices;

/**
 * Created by Sl on 2019/6/12.
 *
 */

public class CarListAdapter extends ListBaseAdapter<SmartDevices.DataBean.CarBoxListBean> {
    public CarListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_bind_car;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        ImageView iv_icon = holder.getView(R.id.iv_icon);
        TextView tv_brand = holder.getView(R.id.tv_brand);
        TextView tv_state = holder.getView(R.id.tv_state);


        Glide.with(mContext).load(getDataList().get(position).getCar_brand_url()).into(iv_icon);
        tv_brand.setText(getDataList().get(position).getCar_brand_name());
        tv_state.setText(getDataList().get(position).getPlate_number());

    }
}
