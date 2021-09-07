package com.yiyang.cn.aakefudan.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.aakefudan.model.ZixunModel;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.ConsultModel;

import java.util.List;

import androidx.annotation.Nullable;

public class XiuliAdapter extends BaseQuickAdapter<ZixunModel.DataBean.ListBean, BaseViewHolder> {

    public XiuliAdapter(int layoutResId, @Nullable List<ZixunModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZixunModel.DataBean.ListBean item) {


        helper.setText(R.id.tv_name, item.getInst_name());
        helper.setText(R.id.tv_juli, item.getMeter() + "KM");


        Glide.with(mContext).load(item.getUrl())
                .error(R.mipmap.logi_icon)
                .into((ImageView) helper.getView(R.id.iv_icon));

        boolean select = item.isSelect();
        ImageView iv_select = helper.getView(R.id.iv_select);
        if (select) {
            iv_select.setImageResource(R.mipmap.yixuanze);
        } else {
            iv_select.setImageResource(R.mipmap.weixuanze);
        }
    }
}
