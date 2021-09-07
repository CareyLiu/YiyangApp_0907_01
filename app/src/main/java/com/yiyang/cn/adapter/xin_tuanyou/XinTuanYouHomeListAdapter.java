package com.yiyang.cn.adapter.xin_tuanyou;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.JiaYouFirstModel;

import java.math.BigDecimal;
import java.util.List;

public class XinTuanYouHomeListAdapter extends BaseQuickAdapter<JiaYouFirstModel.DataBean.OilListBean, BaseViewHolder> {
    public XinTuanYouHomeListAdapter(int layoutResId, @Nullable List<JiaYouFirstModel.DataBean.OilListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JiaYouFirstModel.DataBean.OilListBean item) {
        helper.addOnClickListener(R.id.constrain);
        helper.setText(R.id.tv_shop_name, item.getInst_name());
        helper.setText(R.id.tv_address, item.getAddr());
        helper.setText(R.id.tv_price, item.getPriceYfq());
        BigDecimal cha = new BigDecimal(item.getPriceOfficial()).subtract(new BigDecimal(item.getPriceYfq()));
        helper.setText(R.id.tv_yijiang, "已降" + cha.toString());
        helper.setText(R.id.tv_guobiao_jia, "国标价" + item.getPriceOfficial());
        helper.setText(R.id.tv_distance, item.getDistance() + "km");

        helper.addOnClickListener(R.id.iv_daohang);
    }
}
