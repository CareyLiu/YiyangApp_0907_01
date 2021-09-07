package com.yiyang.cn.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ZhiNengChangJingIconModel;

import java.util.List;


public class ZhiNengTuBiaoAdapter extends BaseQuickAdapter<ZhiNengChangJingIconModel.DataBean, BaseViewHolder> {
    public ZhiNengTuBiaoAdapter(int layoutResId, @Nullable List<ZhiNengChangJingIconModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengChangJingIconModel.DataBean item) {

        helper.addOnClickListener(R.id.cl_icon);
        Glide.with(mContext).load(item.getImg_url()).into((ImageView) helper.getView(R.id.iv_icon));
    }

}
