package com.yiyang.cn.adapter.tuangou;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.model.TuanGouShangJiaListBean;
import com.yiyang.cn.util.DensityUtils;

import java.util.List;

public class TuanGouShangJiaListAdapter extends BaseQuickAdapter<TuanGouShangJiaListBean.DataBean.StoreListBean, BaseViewHolder> {

    private List<TuanGouShangJiaListBean.DataBean.StoreListBean> listBeans;

    public TuanGouShangJiaListAdapter(int layoutResId, @Nullable List<TuanGouShangJiaListBean.DataBean.StoreListBean> data) {
        super(layoutResId, data);
        listBeans = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, TuanGouShangJiaListBean.DataBean.StoreListBean item) {


        helper.addOnClickListener(R.id.constrain);
        Glide.with(mContext).load(item.getInst_photo_url()).apply(new RequestOptions().bitmapTransform(new RoundedCorners(DensityUtils.dp2px(mContext, 10)))).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_shop_name, item.getInst_name());//店铺名
//        helper.setText(R.id.star)
        AppCompatRatingBar appCompatRatingBar = helper.getView(R.id.star);
        if (!StringUtils.isEmpty(item.getInst_number())) {
            appCompatRatingBar.setRating(Float.parseFloat(item.getInst_number()));
        }
        if (!StringUtils.isEmpty(item.getValue_4_name())) {
            helper.setText(R.id.tv_zhekou, "享受" + item.getValue_4_name() + "优惠");
        } else {
            helper.setText(R.id.tv_zhekou, "享受" + "优惠");
        }

        helper.setText(R.id.tv_addr_thing_xiao, item.getArea_name() + "  |  " + item.getInst_text() + "  |  " + "销量: " + item.getPay_count());
        helper.setText(R.id.tv_distance, item.getMeter_name());


    }
}
