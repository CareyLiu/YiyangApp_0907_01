package com.yiyang.cn.activity.tongcheng58.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tongcheng58.model.TcHomeModel;

import java.util.List;

import androidx.annotation.Nullable;

public class TcHomeTagAdapter extends BaseQuickAdapter<TcHomeModel.DataBean.IconListBean, BaseViewHolder> {
    public TcHomeTagAdapter(int layoutResId, @Nullable List<TcHomeModel.DataBean.IconListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TcHomeModel.DataBean.IconListBean item) {
        if (item.isNew()){
            Glide.with(mContext).load(item.getService_type_img_id()).into((ImageView) helper.getView(R.id.iv_tag));
        }else {
            Glide.with(mContext).load(item.getService_type_img()).into((ImageView) helper.getView(R.id.iv_tag));
        }
        helper.setText(R.id.tv_tag, item.getService_type_name());
    }
}
