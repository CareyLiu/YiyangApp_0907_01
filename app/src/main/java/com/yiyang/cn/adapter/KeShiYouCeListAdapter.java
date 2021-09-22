package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.yiyang.KeShiMingChengModel;
import com.yiyang.cn.model.yiyang.MenZhenLieBiaoModel;

import java.util.List;

public class KeShiYouCeListAdapter extends BaseQuickAdapter<MenZhenLieBiaoModel, BaseViewHolder> {
    public KeShiYouCeListAdapter(int layoutResId, @Nullable List<MenZhenLieBiaoModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenZhenLieBiaoModel item) {
        helper.setText(R.id.tv_keshi_ming, item.name);
        helper.addOnClickListener(R.id.rl_background);
    }


}
