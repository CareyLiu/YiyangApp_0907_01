package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.GoodsDetails_f;
import com.yiyang.cn.util.GlideShowImageUtils;

import java.util.List;

public class TaoCanAdapter extends BaseQuickAdapter<GoodsDetails_f.DataBean.ProductListBean, BaseViewHolder> {


    public TaoCanAdapter(int layoutResId, @Nullable List<GoodsDetails_f.DataBean.ProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsDetails_f.DataBean.ProductListBean item) {
        helper.setText(R.id.tv_text, item.getProduct_title());
        helper.addOnClickListener(R.id.constrain);
        Glide.with(mContext).load(item.getIndex_photo_url()).apply(GlideShowImageUtils.showZhengFangXing()).into((ImageView) helper.getView(R.id.iv_product));
        ConstraintLayout constraintLayout = helper.getView(R.id.constrain);
        if (item.getSelect().equals("1")) {
            TextView tv = helper.getView(R.id.tv_text);
            constraintLayout.setBackgroundResource(R.drawable.background_select);
            tv.setTextColor(mContext.getResources().getColor(R.color.FC0100));
        } else {
            TextView tv = helper.getView(R.id.tv_text);
            constraintLayout.setBackgroundResource(R.drawable.background_noselect);
            tv.setTextColor(mContext.getResources().getColor(R.color.black_333333));
        }


    }
}
