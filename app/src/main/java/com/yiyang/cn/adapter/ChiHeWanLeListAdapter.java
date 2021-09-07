package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.Home;
import com.yiyang.cn.model.MessageModel;

import java.util.List;

public class ChiHeWanLeListAdapter extends BaseQuickAdapter<Home.DataBean.IconListBean, BaseViewHolder> {
    public ChiHeWanLeListAdapter(int layoutResId, @Nullable List<Home.DataBean.IconListBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Home.DataBean.IconListBean item) {
        helper.setText(R.id.tv_text, item.getName());
        Glide.with(mContext).load(item.getImg_url()).into((ImageView) helper.getView(R.id.iv_image));

//        if (helper.getAdapterPosition() == 0) {
//            setMargins(helper.getView(R.id.constrain), DensityUtil.dp2px(13), DensityUtil.dp2px(10), 0, 0);
//        }

        setMargins(helper.getView(R.id.constrain), DensityUtil.dp2px(13), DensityUtil.dp2px(10), 0, 0);

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
