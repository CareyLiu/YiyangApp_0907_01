package com.yiyang.cn.activity.tongcheng58.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tongcheng58.model.TcBianminModel;

import java.util.List;

import androidx.annotation.Nullable;

public class TcBianminAdapter extends BaseQuickAdapter<TcBianminModel.DataBean.IrNoticeListBean, BaseViewHolder> {
    public TcBianminAdapter(int layoutResId, @Nullable List<TcBianminModel.DataBean.IrNoticeListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TcBianminModel.DataBean.IrNoticeListBean item) {
        Glide.with(mContext).load(item.getNotice_img_url()).into((ImageView) helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_name, item.getIr_title());
        helper.setText(R.id.tv_juli, item.getMeter() + "m");
        helper.addOnClickListener(R.id.ll_lianxi);
    }
}
