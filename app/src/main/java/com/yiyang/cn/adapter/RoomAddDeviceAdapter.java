package com.yiyang.cn.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.fragment.znjj.model.ZhiNengModel;

import java.util.List;

import androidx.annotation.Nullable;

public class RoomAddDeviceAdapter extends BaseQuickAdapter<ZhiNengModel.DataBean.DeviceBean, BaseViewHolder> {
    public RoomAddDeviceAdapter(int layoutResId, @Nullable List<ZhiNengModel.DataBean.DeviceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengModel.DataBean.DeviceBean item) {
        Glide.with(mContext).load(item.getDevice_type_pic()).into((ImageView) helper.getView(R.id.iv_head));
        helper.setText(R.id.tv_device_name, item.getDevice_name());
    }
}
