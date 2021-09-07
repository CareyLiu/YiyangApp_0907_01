package com.yiyang.cn.activity.tuya_device.device.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuya_device.device.model.DeviceGaojing;

import java.util.List;

import androidx.annotation.Nullable;

public class GaojingAdapter extends BaseQuickAdapter<DeviceGaojing, BaseViewHolder> {

    public GaojingAdapter(int layoutResId, @Nullable List<DeviceGaojing> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceGaojing item) {
        helper.setText(R.id.tv_name, item.getName());
        ImageView iv_switch = helper.getView(R.id.iv_switch);
        boolean enabled = item.isEnabled();
        if (enabled) {
            iv_switch.setImageResource(R.mipmap.switch_open);
        } else {
            iv_switch.setImageResource(R.mipmap.switch_close);
        }

        helper.addOnClickListener(R.id.iv_switch);
    }
}
