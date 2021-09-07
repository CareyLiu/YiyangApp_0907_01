package com.yiyang.cn.adapter;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;

import java.util.List;

public class ShengXiaoShiJianChongFuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ShengXiaoShiJianChongFuAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.rl_choose);
        ImageView ivChoose = helper.getView(R.id.iv_xuanze);
        if (item.equals("0")) {
            ivChoose.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel);
        } else if (item.equals("1")) {
            ivChoose.setBackgroundResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        }
        int position = helper.getAdapterPosition();

        if (position == 0) {
            helper.setText(R.id.tv_xingqi, "周一");
        } else if (position == 1) {
            helper.setText(R.id.tv_xingqi, "周二");

        } else if (position == 2) {
            helper.setText(R.id.tv_xingqi, "周三");

        } else if (position == 3) {
            helper.setText(R.id.tv_xingqi, "周四");

        } else if (position == 4) {
            helper.setText(R.id.tv_xingqi, "周五");

        } else if (position == 5) {
            helper.setText(R.id.tv_xingqi, "周六");

        } else if (position == 6) {
            helper.setText(R.id.tv_xingqi, "周天");

        }
    }

}
