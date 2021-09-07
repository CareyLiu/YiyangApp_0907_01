package com.yiyang.cn.activity.tongcheng58.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tongcheng58.model.ShangjiaDetailModel;
import com.yiyang.cn.activity.tongcheng58.model.TcBianminModel;

import java.util.List;

import androidx.annotation.Nullable;

public class TcSheshiAdapter extends BaseQuickAdapter<ShangjiaDetailModel.DataBean.TypeArrayBean, BaseViewHolder> {
    public TcSheshiAdapter(int layoutResId, @Nullable List<ShangjiaDetailModel.DataBean.TypeArrayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShangjiaDetailModel.DataBean.TypeArrayBean item) {
        helper.setText(R.id.tv_name, item.getName());
        ImageView iv_select = helper.getView(R.id.iv_select);
        String defaultX = item.getDefaultX();
        if (TextUtils.isEmpty(defaultX)) {
            iv_select.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel);
        } else {
            if (defaultX.equals("1")) {
                iv_select.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
            } else {
                iv_select.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel);
            }
        }
    }
}
