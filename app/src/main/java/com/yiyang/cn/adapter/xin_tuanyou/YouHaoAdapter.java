package com.yiyang.cn.adapter.xin_tuanyou;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.OilArrayBean;

import java.util.List;

//多少千米
public class YouHaoAdapter extends BaseSectionQuickAdapter<OilArrayBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public YouHaoAdapter(List<OilArrayBean> data) {
        super(R.layout.item_km2, R.layout.you_header, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OilArrayBean item) {



        if (item.getIsSelect().equals("0")) {
            helper.setBackgroundRes(R.id.tv_text, R.drawable.item_km_back);

        } else if (item.getIsSelect().equals("1")) {
            helper.setBackgroundRes(R.id.tv_text, R.drawable.item_km_pink);
        }


        helper.setText(R.id.tv_text, item.getName());
        helper.addOnClickListener(R.id.constrain);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, OilArrayBean item) {
        helper.setText(R.id.tv_text, item.header);

    }
}




