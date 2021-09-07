package com.yiyang.cn.activity.tuya_device.changjing.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.tuya.smart.home.sdk.bean.DashBoardBean;

import java.util.List;

import androidx.annotation.Nullable;

public class TuyaTianqiAdapter extends BaseQuickAdapter<DashBoardBean, BaseViewHolder> {
    public TuyaTianqiAdapter(int layoutResId, @Nullable List<DashBoardBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DashBoardBean item) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        Glide.with(mContext).load(item.getIcon()).into(iv_icon);
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_value, item.getValue() + item.getUnit());
    }
}
