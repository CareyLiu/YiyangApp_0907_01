package com.yiyang.cn.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.BianMinFaBuBean;
import com.yiyang.cn.model.XiangQingTuBean;

import java.util.List;

public class XiangQingTuAdapter extends BaseQuickAdapter<  BianMinFaBuBean.ProBean, BaseViewHolder> {
    public XiangQingTuAdapter(int layoutResId, @Nullable List<  BianMinFaBuBean.ProBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,   BianMinFaBuBean.ProBean item) {
        if (item.type.equals("1")) {
            Glide.with(mContext).load(item.ir_img_url).into((ImageView) helper.getView(R.id.iv_image));
        } else {
            //Glide.with(mContext).load(R.mipmap.shoppicture_icon_add_gray).into((ImageView) helper.getView(R.id.iv_image));
            ImageView imageView = helper.getView(R.id.iv_image);
            imageView.setBackgroundResource(R.mipmap.shoppicture_icon_add_gray);
        }

    }
}
