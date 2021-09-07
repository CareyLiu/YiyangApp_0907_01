package com.yiyang.cn.adapter.tuangou;

import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.model.TuanGouShangJiaDetailsModel;
import com.yiyang.cn.model.TuanGouShangJiaListBean;
import com.yiyang.cn.util.DensityUtils;

import java.util.List;

public class TuanGouShangJiaHighScoreList1Adapter extends BaseQuickAdapter<TuanGouShangJiaDetailsModel.DataBean.HighScoreListBean, BaseViewHolder> {

    private List<TuanGouShangJiaDetailsModel.DataBean.HighScoreListBean> listBeans;

    public TuanGouShangJiaHighScoreList1Adapter(int layoutResId, @Nullable List<TuanGouShangJiaDetailsModel.DataBean.HighScoreListBean> data) {
        super(layoutResId, data);
        listBeans = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, TuanGouShangJiaDetailsModel.DataBean.HighScoreListBean item) {


        helper.addOnClickListener(R.id.constrain);
        Glide.with(mContext).load(item.getInst_photo_url()).apply(new RequestOptions().bitmapTransform(new RoundedCorners(DensityUtils.dp2px(mContext, 10)))).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_shop_name, item.getInst_name());//店铺名
//        helper.setText(R.id.star)
        AppCompatRatingBar appCompatRatingBar = helper.getView(R.id.star);
        appCompatRatingBar.setRating(Float.parseFloat(item.getInst_number()));
        if (!StringUtils.isEmpty(item.getValue_4())) {
            helper.setText(R.id.tv_zhekou, "享受" + item.getValue_4() + "优惠");
        } else {
            helper.setText(R.id.tv_zhekou, "享受" + "优惠");
        }

        helper.setText(R.id.tv_addr_thing_xiao, item.getArea_name() + "  |  " + item.getInst_text() + "  |  " + "销量: " + item.getPay_count());
        helper.setText(R.id.tv_distance, item.getMeter()+"KM");


    }
}
