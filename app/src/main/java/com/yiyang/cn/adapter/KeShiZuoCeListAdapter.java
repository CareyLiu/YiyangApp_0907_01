package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.yiyang.KeShiMingChengModel;

import java.util.List;

public class KeShiZuoCeListAdapter extends BaseQuickAdapter<KeShiMingChengModel, BaseViewHolder> {
    public KeShiZuoCeListAdapter(int layoutResId, @Nullable List<KeShiMingChengModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeShiMingChengModel item) {
        helper.setText(R.id.tv_left_name, item.name);
        helper.addOnClickListener(R.id.rl_background);
        if (item.shiFouXuanZe.equals("0")) {
            helper.setBackgroundColor(R.id.rl_background, mContext.getResources().getColor(R.color.window_backgroundN));
            helper.setTextColor(R.id.tv_left_name, mContext.getResources().getColor(R.color.color_a));
        } else {
            helper.setBackgroundColor(R.id.rl_background, mContext.getResources().getColor(R.color.white));
            helper.setTextColor(R.id.tv_left_name, mContext.getResources().getColor(R.color.black));
        }

    }


}
