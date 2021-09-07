package com.yiyang.cn.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ZhiNengSheBeiLieBiaoModel;

import java.util.List;

public class SheBeiBianHua_SheBeiLieBiaoAdapter extends BaseQuickAdapter<ZhiNengSheBeiLieBiaoModel.DataBean, BaseViewHolder> {
    public SheBeiBianHua_SheBeiLieBiaoAdapter(int layoutResId, @Nullable List<ZhiNengSheBeiLieBiaoModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengSheBeiLieBiaoModel.DataBean item) {
        helper.addOnClickListener(R.id.rrl_main);
        Glide.with(mContext).load(item.getImg_url()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_text, item.getDevice_name());
    }
}
