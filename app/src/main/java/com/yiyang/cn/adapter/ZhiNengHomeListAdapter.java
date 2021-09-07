package com.yiyang.cn.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.ZhiNengHomeBean;
import com.yiyang.cn.model.ZhiNengHomeListBean;

import java.util.List;

public class ZhiNengHomeListAdapter extends BaseQuickAdapter<ZhiNengHomeListBean.DataBean, BaseViewHolder> {

    public ZhiNengHomeListAdapter(int layoutResId, @Nullable List<ZhiNengHomeListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengHomeListBean.DataBean item) {
        if (item.getMember_type().equals("1")) {
            helper.setText(R.id.tv_family_name, item.getFamily_name());
        } else {
            helper.setText(R.id.tv_family_name, item.getFamily_name() + "(共享家庭)");
        }
        helper.setText(R.id.tv_room_num, item.getRoom_num() + "个房间");
        helper.setText(R.id.tv_device_num, item.getDevice_num() + "个设备");
        if (item.getActive().equals("1")) {
            helper.setVisible(R.id.iv_check, true);
        } else {
            helper.setVisible(R.id.iv_check, false);
        }
        helper.addOnClickListener(R.id.ll_content);
    }
}
