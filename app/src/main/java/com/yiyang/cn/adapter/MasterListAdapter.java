package com.yiyang.cn.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ConsultModel;
import com.yiyang.cn.model.MasterModel;

/**
 * Created by Sl on 2019/6/12.
 */

public class MasterListAdapter extends ListBaseAdapter<MasterModel.DataBean> {


    public MasterListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_master;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {


        ImageView ivHead = holder.getView(R.id.iv_head);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvModel = holder.getView(R.id.tv_model);
        TextView tvPhone = holder.getView(R.id.tv_phone);
        TextView tvDate = holder.getView(R.id.tv_date);


        Glide.with(mContext).load(getDataList().get(position).getUser_img_url()).into(ivHead);
        tvName.setText(getDataList().get(position).getUser_name());
        tvModel.setText(getDataList().get(position).getPlate_number());
        tvPhone.setText(getDataList().get(position).getUser_phone());
        tvDate.setText("安装时间：" + getDataList().get(position).getCreate_time());


    }
}
