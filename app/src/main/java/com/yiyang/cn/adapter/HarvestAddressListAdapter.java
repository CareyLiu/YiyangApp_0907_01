package com.yiyang.cn.adapter;

import android.text.TextUtils;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.AddressModel;

import java.util.List;


public class HarvestAddressListAdapter extends BaseQuickAdapter<AddressModel.DataBean, BaseViewHolder> {
    private static final String tag = HarvestAddressListAdapter.class.getSimpleName();

    public HarvestAddressListAdapter(List<AddressModel.DataBean> data) {
        super(R.layout.harvest_address_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressModel.DataBean item) {
        helper.setText(R.id.tv_harvestName, item.getUser_name()) //收货人姓名
                .setText(R.id.tv_harvestPhone, item.getUser_phone()) //收货人电话
                .setText(R.id.tv_harvestAddress, item.getArea_name() + item.getAddr()) //收货人地址
                .addOnClickListener(R.id.ll_harvestDefault) //设置默认收货地址监听
                .addOnClickListener(R.id.ll_harvestModify) //编辑收货地址监听
                .addOnClickListener(R.id.ll_harvestDelete); //删除收货地址监听

    }
}
