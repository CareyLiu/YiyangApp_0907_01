package com.yiyang.cn.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.AddAddressActivity;
import com.yiyang.cn.activity.LocusActivity;
import com.yiyang.cn.model.AddressModel;
import com.yiyang.cn.model.Locus;

/**
 * Created by Sl on 2019/6/12.
 *
 */

public class AddressListAdapter extends ListBaseAdapter<AddressModel.DataBean> {
    public AddressListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_address;
    }


    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {

        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvPhone = holder.getView(R.id.tv_phone);
        TextView tvDefault = holder.getView(R.id.tv_default);
        TextView tvAddress = holder.getView(R.id.tv_address);
        TextView tvEdit = holder.getView(R.id.tv_edit);



        tvName.setText(getDataList().get(position).getUser_name());
        tvPhone.setText(getDataList().get(position).getUser_phone());
        tvAddress.setText(getDataList().get(position).getAddr_all());
        if (getDataList().get(position).getAddr_check().equals("1")){
            tvDefault.setVisibility(View.VISIBLE);
        }else {
            tvDefault.setVisibility(View.GONE);
        }
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //编辑地址
                mContext.startActivity(new Intent(mContext, AddAddressActivity.class)
                        .putExtra("title","编辑收货地址")
                        .putExtra("code","04130")
                        .putExtra("address_id",getDataList().get(position).getUsers_addr_id())
                        .putExtra("user_name",getDataList().get(position).getUser_name())
                        .putExtra("user_phone",getDataList().get(position).getUser_phone())
                        .putExtra("address",getDataList().get(position).getAddr())
                        .putExtra("province_id",getDataList().get(position).getProvince_id())
                        .putExtra("province_name",getDataList().get(position).getProvince_name())
                        .putExtra("city_id",getDataList().get(position).getCity_id())
                        .putExtra("city_name",getDataList().get(position).getCity_name())
                        .putExtra("area_id",getDataList().get(position).getArea_id())
                        .putExtra("area_name",getDataList().get(position).getArea_name())
                        .putExtra("region",getDataList().get(position).getProvince_name()+"-"+getDataList().get(position).getCity_name()+"-"+getDataList().get(position).getArea_name())
                        .putExtra("isDefault",getDataList().get(position).getAddr_check()));


            }
        });




    }
}
