package com.yiyang.cn.fragment.znjj.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.fragment.znjj.model.ZhiNengModel;
import com.suke.widget.SwitchButton;

import java.util.List;

import androidx.annotation.Nullable;

public class ZhiNengChangJingNewAdapter extends BaseQuickAdapter<ZhiNengModel.DataBean.SceneBean, BaseViewHolder> {
    public ZhiNengChangJingNewAdapter(int layoutResId, @Nullable List<ZhiNengModel.DataBean.SceneBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengModel.DataBean.SceneBean item) {
        helper.addOnClickListener(R.id.rrl_main);
        helper.addOnClickListener(R.id.iv_zhixing);
        Glide.with(mContext).load(item.getScene_pic()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getScene_title());
        TextView tvJianJie = helper.getView(R.id.tv_text);
        ImageView doImage = helper.getView(R.id.iv_zhixing);
        /**
         * 场景类型：1.一键执行  2.定时 3.动作触发
         */
        if (item.getScene_type().equals("1")) {
            doImage.setVisibility(View.VISIBLE);
            tvJianJie.setText("一键执行");
        } else if (item.getScene_type().equals("2")) {
            doImage.setVisibility(View.GONE);
            tvJianJie.setText("定时");
        } else if (item.getScene_type().equals("3")) {
            doImage.setVisibility(View.GONE);
            tvJianJie.setText("动作触发");
        }

        SwitchButton switchButton = helper.getView(R.id.csw_switch_button);
        if (item.getScene_state().equals("3")) {
            switchButton.setChecked(true);
            if (item.getScene_type().equals("1")) {
                doImage.setVisibility(View.VISIBLE);
            } else {
                doImage.setVisibility(View.GONE);
            }
        } else if (item.getScene_state().equals("2")) {
            switchButton.setChecked(false);
            doImage.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.view);
    }
}
