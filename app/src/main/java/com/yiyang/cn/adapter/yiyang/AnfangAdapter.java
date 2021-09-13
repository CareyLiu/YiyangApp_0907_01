package com.yiyang.cn.adapter.yiyang;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.yiyang.AnfangModel;

import java.util.List;

import androidx.annotation.Nullable;

public class AnfangAdapter extends BaseQuickAdapter<AnfangModel, BaseViewHolder> {
    public AnfangAdapter(int layoutResId, @Nullable List<AnfangModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnfangModel item) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        View view_state = helper.getView(R.id.view_state);

        Glide.with(mContext).load(item.getIconId()).into(iv_icon);

        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_time, item.getTime());

        int state = item.getState();
        if (state == 1) {
            helper.setText(R.id.tv_state, "在线");
            view_state.setBackgroundResource(R.drawable.yiyang_state_zaixian);
        } else {
            helper.setText(R.id.tv_state, "中断");
            view_state.setBackgroundResource(R.drawable.yiyang_state_lixian);
        }
    }
}
