package com.yiyang.cn.adapter;


import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.FenLeiThirdModel;

import java.util.List;

public class GongJiangListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public GongJiangListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.rl_item);
    }
}
