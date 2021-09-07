package com.yiyang.cn.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ZhiFuTypeModel;

import java.util.List;

public class TuanGouZhiFuAdapter extends BaseQuickAdapter<ZhiFuTypeModel.DataBean, BaseViewHolder> {


    public TuanGouZhiFuAdapter(int layoutResId, @Nullable List<ZhiFuTypeModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiFuTypeModel.DataBean item) {

        Glide.with(mContext).load(item.pay_img_url).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_text, item.payment_method);
        if (item.choose.equals("0")) {
            helper.setVisible(R.id.iv_weixin_choose, false);
        } else if (item.choose.equals("1")) {
            helper.setVisible(R.id.iv_weixin_choose, true);
        }

        helper.addOnClickListener(R.id.cl_main);
    }
}
