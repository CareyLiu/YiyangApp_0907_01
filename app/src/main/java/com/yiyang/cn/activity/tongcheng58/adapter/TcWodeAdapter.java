package com.yiyang.cn.activity.tongcheng58.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tongcheng58.model.TcWodeModel;

import java.util.List;

import androidx.annotation.Nullable;

public class TcWodeAdapter extends BaseQuickAdapter<TcWodeModel.DataBean.InforListBean, BaseViewHolder> {
    public TcWodeAdapter(int layoutResId, @Nullable List<TcWodeModel.DataBean.InforListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TcWodeModel.DataBean.InforListBean item) {
        Glide.with(mContext).load(item.getIr_img_url()).into((ImageView) helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_name, item.getIr_title());
        helper.addOnClickListener(R.id.bt_bianjie);

        String ir_audit_state = item.getIr_audit_state();
        String ir_audit_state_name = item.getIr_audit_state_name();
        TextView tv_state = helper.getView(R.id.tv_state);
        tv_state.setText(ir_audit_state_name);
        if (ir_audit_state.equals("2")) {
            tv_state.setTextColor(Y.getColor(R.color.tongcheng_yfb));
            tv_state.setBackgroundResource(R.drawable.bg_tongcheng_state_yfb);
        } else if (ir_audit_state.equals("3")) {
            tv_state.setTextColor(Y.getColor(R.color.tongcheng_yjj));
            tv_state.setBackgroundResource(R.drawable.bg_tongcheng_state_yjj);
        } else {
            tv_state.setTextColor(Y.getColor(R.color.tongchneg_shz));
            tv_state.setBackgroundResource(R.drawable.bg_tongcheng_state_shz);
        }

//        if (ir_audit_state.equals("1")) {
//            tv_state.setTextColor(Y.getColor(R.color.tongchneg_shz));
//            tv_state.setBackgroundResource(R.drawable.bg_tongcheng_state_shz);
//        }
    }
}
