package com.yiyang.cn.activity.tuya_device.camera.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuya_device.camera.model.TuyaKongzhiModel;

import java.util.List;

import androidx.annotation.Nullable;

public class TuyaKongzhiAdapter extends BaseQuickAdapter<TuyaKongzhiModel, BaseViewHolder> {
    public TuyaKongzhiAdapter(int layoutResId, @Nullable List<TuyaKongzhiModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuyaKongzhiModel item) {
        helper.setText(R.id.tv_kongzhi, item.getName());
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        if (item.isSelect()) {
            iv_icon.setImageResource(item.getIconIdS());
        } else {
            iv_icon.setImageResource(item.getIconId());
        }

        View view_pingbi = helper.getView(R.id.view_pingbi);
        helper.addOnClickListener(R.id.view_pingbi);
        if (item.isCanClick()) {
            view_pingbi.setVisibility(View.GONE);
        } else {
            view_pingbi.setVisibility(View.VISIBLE);
        }
    }
}
