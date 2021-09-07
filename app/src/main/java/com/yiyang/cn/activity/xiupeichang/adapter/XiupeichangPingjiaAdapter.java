package com.yiyang.cn.activity.xiupeichang.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.xiupeichang.model.XpcDetailsModel;
import com.yiyang.cn.activity.xiupeichang.model.XpcPingjiaModel;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.util.GlideShowImageUtils;

import java.util.List;

import androidx.annotation.Nullable;

public class XiupeichangPingjiaAdapter extends BaseQuickAdapter<XpcDetailsModel.DataBean.PinglunListBean, BaseViewHolder> {
    public XiupeichangPingjiaAdapter(int layoutResId, @Nullable List<XpcDetailsModel.DataBean.PinglunListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XpcDetailsModel.DataBean.PinglunListBean item) {
        ImageView iv_head = (ImageView) helper.getView(R.id.iv_head);
        Glide.with(mContext).load(item.getUser_img_url()).apply(GlideShowImageUtils.showFace()).into(iv_head);

        helper.setText(R.id.tv_name, item.getUser_name());
        helper.setText(R.id.tv_data, item.getUser_to_time() + "评价");
        helper.setText(R.id.tv_pinglun, item.getUser_to_text());
        helper.setText(R.id.tv_shangpin_details, "综合评分：" + item.getUser_to_score());

        if (item.isHideLine()){
            helper.getView(R.id.view_line).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.view_line).setVisibility(View.VISIBLE);
        }
    }
}
