package com.yiyang.cn.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.model.SmartDevices;

/**
 * Created by Sl on 2019/6/12.
 *
 */

public class SmartDevicesListAdapter extends ListBaseAdapter<SmartDevices.DataBean.DeviceListBean> {
    public SmartDevicesListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_smart_devices;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {

        ImageView iv_pic = holder.getView(R.id.iv_pic);
        TextView tv_name = holder.getView(R.id.tv_name);

        Glide.with(mContext).load(getDataList().get(position).getImg_url()).into(iv_pic);
        tv_name.setText(getDataList().get(position).getItem_name());

    }
}
