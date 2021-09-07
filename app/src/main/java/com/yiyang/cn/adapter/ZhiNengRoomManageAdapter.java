package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.ZhiNengRoomManageBean;

import java.util.List;

public class ZhiNengRoomManageAdapter extends BaseQuickAdapter<ZhiNengRoomManageBean.DataBean, BaseViewHolder> {
    public ZhiNengRoomManageAdapter(int layoutResId, @Nullable List<ZhiNengRoomManageBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengRoomManageBean.DataBean item) {
        helper.setText(R.id.tv_room_name, item.getRoom_name());
        helper.setText(R.id.tv_device_num, item.getDevice_number() + "个设备");
        helper.addOnClickListener(R.id.ll_content);
    }
}
