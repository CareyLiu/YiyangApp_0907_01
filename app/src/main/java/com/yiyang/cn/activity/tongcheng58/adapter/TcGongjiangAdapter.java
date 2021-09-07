package com.yiyang.cn.activity.tongcheng58.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tongcheng58.model.TcGongjiangModel;
import com.yiyang.cn.activity.tongcheng58.model.TcHomeModel;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

public class TcGongjiangAdapter extends BaseQuickAdapter<TcGongjiangModel.DataBean.CraftsManListBean, BaseViewHolder> {
    public TcGongjiangAdapter(int layoutResId, @Nullable List<TcGongjiangModel.DataBean.CraftsManListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TcGongjiangModel.DataBean.CraftsManListBean item) {
        Glide.with(mContext).load(item.getIr_personnal_img_url()).into((ImageView) helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_name, item.getIr_personnal_name());
        helper.setText(R.id.tv_juli, item.getMeter() + "m");

        LinearLayout ll_tag = helper.getView(R.id.ll_tag);
        ll_tag.removeAllViews();

        List<TcGongjiangModel.DataBean.CraftsManListBean.ServiceTypeListBean> service_type_list = item.getService_type_list();
        for (int i = 0; i < service_type_list.size(); i++) {
            String inst_device_name = service_type_list.get(i).getService_type_name();
            View view = View.inflate(mContext, R.layout.tongcheng_item_home_item_type, null);
            TextView tv_tag = view.findViewById(R.id.tv_tag);
            tv_tag.setText(inst_device_name);
            ll_tag.addView(view);
        }
    }
}
