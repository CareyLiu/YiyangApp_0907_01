package com.yiyang.cn.activity.tuya_device.add.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuya_device.add.model.TuyaAddDeviceModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TuyaDeviceFinishAdapter extends BaseAdapter {
    private List<TuyaAddDeviceModel> deviceModels;
    private Context context;
    private ViewHolder holder;

    public TuyaDeviceFinishAdapter(List<TuyaAddDeviceModel> deviceModels, Context context) {
        this.deviceModels = deviceModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (deviceModels != null) {
            return deviceModels.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (deviceModels != null) {
            return deviceModels.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.item_tuya_device_search_finish, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (deviceModels != null && deviceModels.size() > i) {
            TuyaAddDeviceModel model = deviceModels.get(i);
            Glide.with(context).load(model.getIcon()).into(holder.iv_device);
            holder.tv_name.setText(model.getName());
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_device)
        ImageView iv_device;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.iv_select)
        ImageView iv_select;
        @BindView(R.id.ll_main)
        LinearLayout ll_main;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
