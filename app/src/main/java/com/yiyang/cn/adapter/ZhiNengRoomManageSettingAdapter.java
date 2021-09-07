package com.yiyang.cn.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ZhiNengRoomManageBean;
import com.yiyang.cn.model.ZhiNengRoomManageSettingBean;

import java.util.List;

public class ZhiNengRoomManageSettingAdapter extends BaseQuickAdapter<ZhiNengRoomManageSettingBean.DataBean, BaseViewHolder> {
    public ZhiNengRoomManageSettingAdapter(int layoutResId, @Nullable List<ZhiNengRoomManageSettingBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengRoomManageSettingBean.DataBean item) {
        Glide.with(mContext).load(item.getDevice_type_pic()).into((ImageView) helper.getView(R.id.iv_head));
        helper.setText(R.id.tv_device_name, item.getDevice_name());
        helper.addOnClickListener(R.id.ll_content);
    }
}
