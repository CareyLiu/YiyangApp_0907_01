package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.TaoKeDetailList;

import java.util.List;

public class TaoKeListAdapter1 extends BaseQuickAdapter<String, BaseViewHolder> {

    public TaoKeListAdapter1(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.constrain);
        if (helper.getAdapterPosition() % 2 == 0) {
            setMargins(helper.getView(R.id.constrain), DensityUtil.dp2px(7), 8, 0, 0);
        } else {
            setMargins(helper.getView(R.id.constrain), 0, 8, 0, 0);
        }

        Log.i("getPostion()", helper.getPosition() + "");
        Log.i("getLayoutPosition()", helper.getLayoutPosition() + "");
        Log.i("getAdapterPosition()", helper.getAdapterPosition() + "");


//        Glide.with(mContext).load(item.getPict_url()).into((ImageView) helper.getView(R.id.iv_product));
//
//        helper.setText(R.id.tv_title, item.getTitle());//标题
//        helper.setText(R.id.tv_yishou, item.getVolume());//已售
//        helper.setText(R.id.tv_xianjia, item.getZk_final_price());//现价
//        helper.setText(R.id.tv_yuanjia, item.getReserve_price());//原价
//        helper.setText(R.id.rtv_youhuijuan, item.getCoupon_start_fee());//优惠券

    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
