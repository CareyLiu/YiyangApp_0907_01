package com.yiyang.cn.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ConsultModel;
import com.yiyang.cn.model.RepairPlantModel;

/**
 * Created by Sl on 2019/6/12.
 */

public class RepairPlantAdapter extends ListBaseAdapter<RepairPlantModel.DataBean.ListBean> {


    public RepairPlantAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_repair_plant;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {

       ImageView ivPic = holder.getView(R.id.iv_pic);
       TextView tvDistance = holder.getView(R.id.tv_distance);
       TextView tvAddress = holder.getView(R.id.tv_address);

       Glide.with(mContext).load(getDataList().get(position).getUrl()).into(ivPic);
       tvDistance.setText(getDataList().get(position).getMeter()+"km");
       tvAddress.setText(getDataList().get(position).getAddr());

    }
}
