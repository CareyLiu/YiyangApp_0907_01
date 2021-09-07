package com.yiyang.cn.activity.tuya_device.device.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuya_device.utils.TuyaConfig;
import com.yiyang.cn.activity.tuya_device.device.model.ZishebeiModel;

import java.util.List;

import androidx.annotation.Nullable;

public class WangguanAdapter extends BaseQuickAdapter<ZishebeiModel.DataBean, BaseViewHolder> {
    public WangguanAdapter(int layoutResId, @Nullable List<ZishebeiModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZishebeiModel.DataBean deviceBean) {
        Glide.with(mContext).load(deviceBean.getDevice_type_pic()).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_device_name, deviceBean.getDevice_name());
        helper.setText(R.id.tv_room_name, deviceBean.getRoom_name());
        ImageView iv_switch = helper.getView(R.id.iv_switch);
        iv_switch.setImageResource(R.mipmap.img_device_switch_open);

        String category = deviceBean.getDevice_type();
        if (category.equals(TuyaConfig.CATEGORY_CHAZUO)) {
            iv_switch.setVisibility(View.VISIBLE);
        } else {
            iv_switch.setVisibility(View.GONE);
        }
    }
}
