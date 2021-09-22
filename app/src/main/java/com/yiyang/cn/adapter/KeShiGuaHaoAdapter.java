package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;

import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.KeShiLieBiaoGuaHaoModel;

import java.util.List;

public class KeShiGuaHaoAdapter extends BaseQuickAdapter<KeShiLieBiaoGuaHaoModel, BaseViewHolder> {
    public KeShiGuaHaoAdapter(int layoutResId, @Nullable List<KeShiLieBiaoGuaHaoModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeShiLieBiaoGuaHaoModel item) {


    }
}
