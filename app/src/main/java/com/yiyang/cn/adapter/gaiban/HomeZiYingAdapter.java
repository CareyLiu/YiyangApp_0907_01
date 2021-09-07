package com.yiyang.cn.adapter.gaiban;

import android.widget.ImageView;

import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.model.Home;

import java.util.List;

public class HomeZiYingAdapter extends BaseQuickAdapter<Home.DataBean.ProShowListBean, com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder> {
    public HomeZiYingAdapter(int layoutResId, @Nullable List<Home.DataBean.ProShowListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder helper, Home.DataBean.ProShowListBean item) {
        helper.setText(R.id.tv_title, item.getWares_name());
        helper.setText(R.id.tv_jiage, "¥"+item.getMoney_now());
        Glide.with(mContext).load(item.getIndex_photo_url()).into((ImageView) helper.getView(R.id.iv_product));

    }


}
