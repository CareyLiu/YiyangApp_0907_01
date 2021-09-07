package com.yiyang.cn.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ChangJingXiangQingModel;

import java.util.List;

public class ZhiNengChangJingSheBeiAdapter extends BaseQuickAdapter<ChangJingXiangQingModel.DataBean.ImplementListBean, BaseViewHolder> {
    public ZhiNengChangJingSheBeiAdapter(int layoutResId, @Nullable List<ChangJingXiangQingModel.DataBean.ImplementListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChangJingXiangQingModel.DataBean.ImplementListBean item) {
        Glide.with(mContext).load(item.getImg_url()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getDevice_name());
        helper.setText(R.id.tv_shebei_zhuangtai, item.getImplement_detail());
        helper.addOnClickListener(R.id.ll_item);
    }
}
