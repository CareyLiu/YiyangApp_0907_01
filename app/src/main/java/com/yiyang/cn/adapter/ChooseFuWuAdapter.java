package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.FuWuGongZhongBean;

import java.util.List;

public class ChooseFuWuAdapter extends BaseQuickAdapter<FuWuGongZhongBean.DataBean, BaseViewHolder> {
    public ChooseFuWuAdapter(int layoutResId, @Nullable List<FuWuGongZhongBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FuWuGongZhongBean.DataBean item) {
        helper.setText(R.id.tv_text, item.getName());
        helper.addOnClickListener(R.id.ll_main);
        if (item.type.equals("0")) {
            helper.setTextColor(R.id.tv_text, mContext.getResources().getColor(R.color.color_FFFC0100));
            helper.setBackgroundRes(R.id.rl_item, R.drawable.chong_zhi_gray);
        } else if (item.type.equals("1")) {
            helper.setBackgroundRes(R.id.rl_item, R.drawable.chong_zhi_pink);
            helper.setTextColor(R.id.tv_text, mContext.getResources().getColor(R.color.black_333333));
        }
    }
}
