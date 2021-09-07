package com.yiyang.cn.fragment.znjj.adapter;

import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.fragment.znjj.model.ZhiNengModel;
import com.yiyang.cn.model.ZhiNengHomeBean;

import java.util.List;

import androidx.annotation.Nullable;

public class ZhiNengRoomListNewAdapter extends BaseQuickAdapter<ZhiNengModel.DataBean.RoomBean, BaseViewHolder> {
    public ZhiNengRoomListNewAdapter(int layoutResId, @Nullable List<ZhiNengModel.DataBean.RoomBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengModel.DataBean.RoomBean item) {
        helper.setText(R.id.tv_room_name, item.getRoom_name());
        helper.setText(R.id.tv_device_num, item.getDevice_number() + "个设备");
        helper.addOnClickListener(R.id.ll_content);
    }
}
