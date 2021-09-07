package com.yiyang.cn.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ConsultModel;

/**
 * Created by Sl on 2019/6/12.
 */

public class ConsultListAdapter extends ListBaseAdapter<ConsultModel.DataBean> {


    public ConsultListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_consult;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {

        ImageView ivHead = holder.getView(R.id.iv_head);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvNumber = holder.getView(R.id.tv_model);
        TextView tvFault = holder.getView(R.id.tv_fault);
        TextView tvDate = holder.getView(R.id.tv_date);
        TextView tvState = holder.getView(R.id.tv_state);

        Glide.with(mContext).load(getDataList().get(position).getUser_car_img_url()).into(ivHead);
        tvName.setText(getDataList().get(position).getSub_user_name());
        tvNumber.setText(getDataList().get(position).getPlate_number());
        tvFault.setText(getDataList().get(position).getError_text());
        tvDate.setText(getDataList().get(position).getCreate_time());
        tvState.setText(getDataList().get(position).getState_name());

    }
}
