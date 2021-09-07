package com.yiyang.cn.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.TuiGuangTongJiModel;

import java.util.List;

public class TuanYouTuiGuangAdapter extends BaseQuickAdapter<TuiGuangTongJiModel.DataBean.LevelListBean, BaseViewHolder> {
    public TuanYouTuiGuangAdapter(int layoutResId, @Nullable List<TuiGuangTongJiModel.DataBean.LevelListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuiGuangTongJiModel.DataBean.LevelListBean item) {
        Glide.with(mContext).load(item.getUser_img_url()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getUser_name());
        helper.setText(R.id.tv_phone, item.getUser_phone());
        helper.setText(R.id.tv_riqi, item.getCreate_time());
        if (item.getUser_type().equals("1")) {
            helper.setBackgroundRes(R.id.iv_type_name, R.mipmap.jyj_wodetuiguang_icon_huiyuan);
        } else if (item.getUser_type().equals("2")) {
            helper.setBackgroundRes(R.id.iv_type_name, R.mipmap.jyj_wodetuiguang_icon_daren);

        }

    }
}
