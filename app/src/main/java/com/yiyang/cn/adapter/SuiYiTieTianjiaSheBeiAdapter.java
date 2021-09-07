package com.yiyang.cn.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.model.SuiYiTieSheBeiLieBiaoModel;
import com.yiyang.cn.util.GlideShowImageUtils;

import java.util.List;

public class SuiYiTieTianjiaSheBeiAdapter extends BaseQuickAdapter<SuiYiTieSheBeiLieBiaoModel.DataBean, BaseViewHolder> {

    public SuiYiTieTianjiaSheBeiAdapter(int layoutResId, @Nullable List<SuiYiTieSheBeiLieBiaoModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SuiYiTieSheBeiLieBiaoModel.DataBean item) {
        if (!StringUtils.isEmpty(item.getDevice_name())) {
            helper.setText(R.id.tv_name, item.getDevice_name());
        }
        Glide.with(mContext).load(item.getDevice_type_pic()).apply(GlideShowImageUtils.showZhengFangXing()).into((ImageView) helper.getView(R.id.iv_image));
        if (item.flag.equals("0")) {
            Glide.with(mContext).load(R.mipmap.tuya_faxian_icon_selector_sel).apply(GlideShowImageUtils.showZhengFangXing()).into((ImageView) helper.getView(R.id.iv_choose));
        } else if (item.flag.equals("1")) {
            Glide.with(mContext).load(R.mipmap.tuya_faxian_icon_selector_sel_1).apply(GlideShowImageUtils.showZhengFangXing()).into((ImageView) helper.getView(R.id.iv_choose));
        }
        helper.addOnClickListener(R.id.ll_main);
    }

}
