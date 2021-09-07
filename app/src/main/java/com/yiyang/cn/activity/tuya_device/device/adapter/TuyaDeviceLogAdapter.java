package com.yiyang.cn.activity.tuya_device.device.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuya_device.device.model.DpModel;
import com.yiyang.cn.activity.tuya_device.utils.TuyaConfig;

import java.util.List;

import androidx.annotation.Nullable;

public class TuyaDeviceLogAdapter extends BaseQuickAdapter<DpModel.DpsBean, BaseViewHolder> {
    private String productId;

    public TuyaDeviceLogAdapter(int layoutResId, @Nullable List<DpModel.DpsBean> data, String productId) {
        super(layoutResId, data);
        this.productId = productId;
    }

    @Override
    protected void convert(BaseViewHolder helper, DpModel.DpsBean item) {
        String timeStr = item.getTimeStr();
        helper.setText(R.id.tv_time, timeStr);
        String value = item.getValue();

        if (productId.equals(TuyaConfig.PRODUCTID_MENCI)) {
            if (value.equals("true")) {
                helper.setText(R.id.tv_caozuo, "门窗打开");
            } else {
                helper.setText(R.id.tv_caozuo, "门窗关闭");
            }
        } else if (productId.equals(TuyaConfig.PRODUCTID_MENCISUO_SIG)) {
            if (value.equals("true")) {
                helper.setText(R.id.tv_caozuo, "门窗关闭");
            } else {
                helper.setText(R.id.tv_caozuo, "门窗打开");
            }
        } else if (productId.equals(TuyaConfig.PRODUCTID_CHAZUO_WG)) {
            if (value.equals("true")) {
                helper.setText(R.id.tv_caozuo, "开启");
            } else {
                helper.setText(R.id.tv_caozuo, "关闭");
            }
        } else if (productId.equals(TuyaConfig.PRODUCTID_SJ)) {
            if (value.equals("alarm")) {
                helper.setText(R.id.tv_caozuo, "水浸报警");
            } else {
                helper.setText(R.id.tv_caozuo, "正常");
            }
        }
    }
}
