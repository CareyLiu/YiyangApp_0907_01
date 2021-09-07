package com.yiyang.cn.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.Home;

import java.util.List;

public class ShengHuoListAdapter extends BaseQuickAdapter<Home.DataBean.LifeListBean, BaseViewHolder> {
    public ShengHuoListAdapter(int layoutResId, @Nullable List<Home.DataBean.LifeListBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Home.DataBean.LifeListBean item) {
        helper.setText(R.id.tv_text, item.service_type_name);
        Glide.with(mContext).load(item.service_type_img).into((ImageView) helper.getView(R.id.iv_image));

//        if (helper.getAdapterPosition() == 0) {
//            setMargins(helper.getView(R.id.constrain), DensityUtil.dp2px(13), DensityUtil.dp2px(10), 0, 0);
//        }
//        setMargins(helper.getView(R.id.constrain), DensityUtil.dp2px(13), DensityUtil.dp2px(10), 0, 0);


        Log.i("getPosition   ：   ", helper.getPosition() + "");
        Log.i("getAdapterPosition ：  ", helper.getAdapterPosition() + "");
        Log.i("getLayoutPosition  ： ", helper.getLayoutPosition() + "");
        //    Log.i("getOldPosition  ： ",helper.getOldPosition()+"");


        helper.addOnClickListener(R.id.constrain);
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
