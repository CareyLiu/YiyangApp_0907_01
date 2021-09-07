package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.Home;

import org.w3c.dom.Text;

import java.util.List;

public class FenLeiHomeLeftListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public int strPosition;
    private boolean isSelect = false;

    public FenLeiHomeLeftListAdapter(int layoutResId, @Nullable List<String> data, String strPosition) {
        super(layoutResId, data);
        this.strPosition = Integer.parseInt(strPosition);
    }

    public FenLeiHomeLeftListAdapter(int layoutResId, @Nullable List<String> data, String strPosition, boolean isSelect) {
        super(layoutResId, data);
        this.strPosition = Integer.parseInt(strPosition);
        this.isSelect = isSelect;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (helper.getLayoutPosition() == strPosition) {
            //  helper.getView(R.id.tv_title).setSelected(true);
            TextView tvTitle = helper.getView(R.id.tv_title);
            if (isSelect) {
                tvTitle.setTextColor(mContext.getResources().getColor(R.color.color_main));
            } else {
                tvTitle.setTextColor(mContext.getResources().getColor(R.color.red_61832));
            }
            helper.getView(R.id.constrain).setBackgroundColor(mContext.getResources().getColor(R.color.white));
        } else {
            TextView tvTitle = helper.getView(R.id.tv_title);
            tvTitle.setTextColor(mContext.getResources().getColor(R.color.color_3));
            helper.getView(R.id.constrain).setBackgroundColor(mContext.getResources().getColor(R.color.grayfff5f5f5));
        }
        helper.setText(R.id.tv_title, item);
        helper.addOnClickListener(R.id.constrain);
    }
}
