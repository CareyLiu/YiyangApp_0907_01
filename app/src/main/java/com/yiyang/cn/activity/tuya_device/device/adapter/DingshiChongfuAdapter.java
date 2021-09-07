package com.yiyang.cn.activity.tuya_device.device.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuya_device.device.model.DingshiModel;

import java.util.List;

import androidx.annotation.Nullable;

public class DingshiChongfuAdapter extends BaseQuickAdapter<DingshiModel, BaseViewHolder> {
    public DingshiChongfuAdapter(int layoutResId, @Nullable List<DingshiModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DingshiModel item) {
        ImageView iv_select = helper.getView(R.id.iv_select);
        String select = item.getSelect();
        if (select.equals("1")) {
            iv_select.setImageResource(R.mipmap.kaquan_select_s);
        } else {
            iv_select.setImageResource(R.mipmap.kaquan_select_n);
        }

        helper.setText(R.id.tv_xingqi, item.getXingqi());
    }
}
