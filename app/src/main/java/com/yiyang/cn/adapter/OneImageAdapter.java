package com.yiyang.cn.adapter;


import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ZhiNengJiaJu_0007Model;

import java.util.List;

public class OneImageAdapter extends BaseQuickAdapter<ZhiNengJiaJu_0007Model.MatchListBean, BaseViewHolder> {

    public OneImageAdapter(int layoutResId, @Nullable List<ZhiNengJiaJu_0007Model.MatchListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengJiaJu_0007Model.MatchListBean item) {
        Glide.with(mContext).load(item.getDevice_type_pic()).into((ImageView) helper.getView(R.id.iv_image));
        helper.addOnClickListener(R.id.iv_image);
    }
}
