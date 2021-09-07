package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.TuanGouShangJiaListBean;
import com.yiyang.cn.model.ZiJianHomeModel;

import java.util.List;

public class TuanGouShangJiaHeaderListAdapter extends BaseQuickAdapter<TuanGouShangJiaListBean.DataBean.IconBean, BaseViewHolder> {
    public TuanGouShangJiaHeaderListAdapter(int layoutResId, @Nullable List<TuanGouShangJiaListBean.DataBean.IconBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuanGouShangJiaListBean.DataBean.IconBean item) {
        Glide.with(mContext).load(item.getImg_url()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_title, item.getName());
        helper.addOnClickListener(R.id.constrain);
    }

}
