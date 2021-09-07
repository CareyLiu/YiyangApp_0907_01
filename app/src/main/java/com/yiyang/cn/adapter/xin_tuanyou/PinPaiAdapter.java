package com.yiyang.cn.adapter.xin_tuanyou;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.JiaYouFirstModel;

import java.util.List;

//多少千米
public class PinPaiAdapter extends BaseQuickAdapter<JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean, BaseViewHolder> {
    public PinPaiAdapter(int layoutResId, @Nullable List<JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean item) {

        if (item.getIsSelect().equals("0")) {
            helper.setBackgroundRes(R.id.tv_text, R.drawable.item_km_back);

        } else if (item.getIsSelect().equals("1")) {
            helper.setBackgroundRes(R.id.tv_text, R.drawable.item_km_pink);
        }


        helper.setText(R.id.tv_text, item.getName());
        helper.addOnClickListener(R.id.constrain);

    }
}
