package com.yiyang.cn.adapter.yiyang;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.yiyang.XiaoxiModel;

import java.util.List;

import androidx.annotation.Nullable;

public class XiaoxiAdapter extends BaseQuickAdapter<XiaoxiModel, BaseViewHolder> {
    public XiaoxiAdapter(int layoutResId, @Nullable List<XiaoxiModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XiaoxiModel item) {
        TextView tv_count = helper.getView(R.id.tv_count);
        int num = item.getNum();
        if (num > 0) {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText("" + num);
        } else {
            tv_count.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_time, item.getTime());
    }
}
