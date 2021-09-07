package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.TuanGouShangJiaDetailsModel;
import com.yiyang.cn.util.DensityUtils;

import java.util.List;

public class ShangJiaNearbyAdapter extends BaseQuickAdapter<TuanGouShangJiaDetailsModel.DataBean.NeighborListBean, BaseViewHolder> {
    public ShangJiaNearbyAdapter(int layoutResId, @Nullable List<TuanGouShangJiaDetailsModel.DataBean.NeighborListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuanGouShangJiaDetailsModel.DataBean.NeighborListBean item) {
        Glide.with(mContext).load(item.getImg_url()).apply(new RequestOptions().bitmapTransform(new RoundedCorners(DensityUtils.dp2px(mContext, 10)))).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getInst_name());
        helper.setText(R.id.tv_shuoming, item.getShop_detail());
        helper.setText(R.id.tv_money, item.getShop_money_now());
        helper.setText(R.id.tv_distance, item.getArea_name()+" "+item.getDistance()+"km");
        helper.setText(R.id.tv_yishou, "已售： " + item.getPay_count());
        helper.addOnClickListener(R.id.constrain);
    }
}
