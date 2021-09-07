package com.yiyang.cn.activity.xiupeichang.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.xiupeichang.model.XpcDetailsModel;
import com.yiyang.cn.activity.xiupeichang.model.XpcFuwuModel;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.util.GlideShowImageUtils;

import java.util.List;

import androidx.annotation.Nullable;

public class XiupeichangFuwuAdapter extends BaseQuickAdapter<XpcDetailsModel.DataBean.TaocanListBean, BaseViewHolder> {
    public XiupeichangFuwuAdapter(int layoutResId, @Nullable List<XpcDetailsModel.DataBean.TaocanListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XpcDetailsModel.DataBean.TaocanListBean item) {
        ImageView iv_img = (ImageView) helper.getView(R.id.iv_img);
        Glide.with(mContext).load(item.getImg_url()).apply(GlideShowImageUtils.showZhengFangXing()).into(iv_img);
        helper.setText(R.id.tv_name, item.getShop_title());
        helper.setText(R.id.tv_content, item.getShop_detail());
        helper.setText(R.id.tv_money, "¥" + item.getShop_money_now());
        helper.setText(R.id.tv_money_yuan, "¥" + item.getShop_money_old());

        if (item.isHideLine()){
            helper.getView(R.id.view_line).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.view_line).setVisibility(View.VISIBLE);
        }
    }
}
