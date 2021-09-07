package com.yiyang.cn.adapter.xiupeichang;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.TuanGouShangJiaListBean;

import java.util.List;

public class XiuPeiChangHomeAdapter extends BaseQuickAdapter<TuanGouShangJiaListBean.DataBean.IconBean, BaseViewHolder> {

    public XiuPeiChangHomeAdapter(int layoutResId, @Nullable List<TuanGouShangJiaListBean.DataBean.IconBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuanGouShangJiaListBean.DataBean.IconBean item) {
        Glide.with(mContext).load(item.getImg_url()).into((ImageView) helper.getView(R.id.iv_image));
        if (item.chooseFlag) {
            helper.setBackgroundRes(R.id.iv_background, R.mipmap.button_bg_sel);
        } else {
            helper.setBackgroundRes(R.id.iv_background, R.mipmap.button_bg_nor);

        }
    }
}
