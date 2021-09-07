package com.yiyang.cn.adapter.xin_tuanyou;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.JiaYouFirstModel;

import java.util.List;


public class PaiXuAdapter extends BaseQuickAdapter<JiaYouFirstModel.DataBean.OrderListBean, BaseViewHolder> {
    public PaiXuAdapter(int layoutResId, @Nullable List<JiaYouFirstModel.DataBean.OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JiaYouFirstModel.DataBean.OrderListBean item) {

        if (item.getIsSelect().equals("0")) {
            helper.setBackgroundRes(R.id.tv_text, R.drawable.item_km_back);

        } else if (item.getIsSelect().equals("1")) {
            helper.setBackgroundRes(R.id.tv_text, R.drawable.item_km_pink);
        }


        helper.setText(R.id.tv_text, item.getName());
        helper.addOnClickListener(R.id.constrain);

    }
}
