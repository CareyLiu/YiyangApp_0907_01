package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.JingBaoModel;

import java.util.List;

public class GengDuoJingBaoAdapter extends BaseQuickAdapter<JingBaoModel.DataBean, BaseViewHolder> {

    public GengDuoJingBaoAdapter(int layoutResId, @Nullable List<JingBaoModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JingBaoModel.DataBean item) {
        helper.setText(R.id.tv_shijianduan, item.getAlerm_time());
        helper.setText(R.id.tv_menchuangzhuangtai, item.getDevice_state_name());
    }
}
