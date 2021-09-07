package com.yiyang.cn.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ChangJingModel;
import com.yiyang.cn.model.ChangJingZhiXingModel;

import java.util.List;

public class ZhiNengChangJingSheBeiAdapter1 extends BaseQuickAdapter<ChangJingZhiXingModel, BaseViewHolder> {
    public ZhiNengChangJingSheBeiAdapter1(int layoutResId, @Nullable List<ChangJingZhiXingModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChangJingZhiXingModel item) {
        Glide.with(mContext).load(item.img_url).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.device_name);
        if (item.pro_go_one.equals("1")) {
            helper.setText(R.id.tv_shebei_zhuangtai, "开");
        } else if (item.pro_go_one.equals("2")) {
            helper.setText(R.id.tv_shebei_zhuangtai, "关");
        }
    }
}
