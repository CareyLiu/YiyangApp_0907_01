package com.yiyang.cn.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.GuanZhuDianPuListModel;
import com.yiyang.cn.util.GlideShowImageUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GuanZhuDianPuListAdapter extends BaseQuickAdapter<GuanZhuDianPuListModel.DataBean, BaseViewHolder> {
    public GuanZhuDianPuListAdapter(int layoutResId, @Nullable List<GuanZhuDianPuListModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuanZhuDianPuListModel.DataBean item) {
        helper.setText(R.id.tv_title, item.getInst_name());
        Glide.with(mContext).applyDefaultRequestOptions(GlideShowImageUtils.showZhengFangXing()).load(item.getInst_photo_url()).into((CircleImageView) helper.getView(R.id.iv_image));

        helper.addOnClickListener(R.id.constrain);

    }
}
