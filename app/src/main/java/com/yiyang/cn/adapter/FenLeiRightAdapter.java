package com.yiyang.cn.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.FenLeiContentModel;
import com.yiyang.cn.util.GlideShowImageUtils;

import java.util.List;

/**
 * 创建日期：2017/5/15 0015 on 11:30
 * 描述:
 * 作者:刘佳钊
 */
public class FenLeiRightAdapter extends BaseSectionQuickAdapter<FenLeiContentModel, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public FenLeiRightAdapter(List<FenLeiContentModel> data) {
        super(R.layout.item_fenlei_content, R.layout.layout_fenlei_right_header, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FenLeiContentModel item) {
        Glide.with(mContext).load(item.getImg_url()).apply(GlideShowImageUtils.showZhengFangXing()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getItem_name());
        helper.addOnClickListener(R.id.constrain);

    }

    @Override
    protected void convertHead(BaseViewHolder helper, FenLeiContentModel item) {
        helper.setText(R.id.tv_text, item.header);
        //helper.addOnClickListener(R.id.constrain);
    }
}
