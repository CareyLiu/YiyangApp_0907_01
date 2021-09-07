package com.yiyang.cn.activity.tongcheng58.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tongcheng58.model.TcHomeModel;

import java.util.List;

import androidx.annotation.Nullable;

public class TcHomeItemAdapter extends BaseQuickAdapter<TcHomeModel.DataBean.ShopListBean, BaseViewHolder> {
    public TcHomeItemAdapter(int layoutResId, @Nullable List<TcHomeModel.DataBean.ShopListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TcHomeModel.DataBean.ShopListBean item) {
        Glide.with(mContext).load(item.getIr_inst_logo()).into((ImageView) helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_name, item.getIr_inst_name());
        helper.setText(R.id.tv_juli, item.getMeter() + "m");
        helper.addOnClickListener(R.id.rl_lianxi);

        LinearLayout ll_tag = helper.getView(R.id.ll_tag);
        ll_tag.removeAllViews();

        List<TcHomeModel.DataBean.ShopListBean.InstDeviceListBean> inst_device_list = item.getInst_device_list();
        for (int i = 0; i < inst_device_list.size(); i++) {
            String inst_device_name = inst_device_list.get(i).getInst_device_name();
            View view = View.inflate(mContext, R.layout.tongcheng_item_home_item_type, null);
            TextView tv_tag = view.findViewById(R.id.tv_tag);
            tv_tag.setText(inst_device_name);
            ll_tag.addView(view);
        }
    }
}
