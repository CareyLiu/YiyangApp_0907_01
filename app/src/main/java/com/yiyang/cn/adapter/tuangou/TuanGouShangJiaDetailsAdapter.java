package com.yiyang.cn.adapter.tuangou;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.TuanGouShangJiaDetailsModel;

import java.util.List;

public class TuanGouShangJiaDetailsAdapter extends BaseQuickAdapter<TuanGouShangJiaDetailsModel.DataBean.FavourableListBean, BaseViewHolder> {


    public TuanGouShangJiaDetailsAdapter(int layoutResId, @Nullable List<TuanGouShangJiaDetailsModel.DataBean.FavourableListBean> data) {
        super(layoutResId, data);

    }


    @Override
    protected void convert(BaseViewHolder helper, TuanGouShangJiaDetailsModel.DataBean.FavourableListBean item) {

        Glide.with(mContext).load(item.getImg_url()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_taocan_name, item.getShop_title());
        helper.setText(R.id.tv_shuoming, item.getShop_detail());
        helper.setText(R.id.tv_money, item.getShop_money_now());
        helper.setText(R.id.tv_bannian_xiaoliang, "半年销量:" + item.getPay_count());
        helper.addOnClickListener(R.id.rtv_qianggou);

    }
}
