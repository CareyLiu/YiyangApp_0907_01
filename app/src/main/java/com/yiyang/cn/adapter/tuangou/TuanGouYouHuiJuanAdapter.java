package com.yiyang.cn.adapter.tuangou;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.TuanGouYouHuiJuanModel;

import java.util.List;

public class TuanGouYouHuiJuanAdapter extends BaseQuickAdapter<TuanGouYouHuiJuanModel.DataBean, BaseViewHolder> {

    public TuanGouYouHuiJuanAdapter(int layoutResId, @Nullable List<TuanGouYouHuiJuanModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuanGouYouHuiJuanModel.DataBean item) {
        helper.setText(R.id.tv_title, item.getAgio_title());
        helper.setText(R.id.tv_detail, item.getAgio_detail());
        helper.setText(R.id.tv_date, item.getUser_time() + "到期");
        helper.setText(R.id.tv_money, "¥" + item.getAgio_money());


        View iv_duihuan = helper.getView(R.id.iv_duihuan);
        View iv_zengsong = helper.getView(R.id.iv_zengsong);
        if (isXianBtn) {
            iv_zengsong.setVisibility(View.GONE);
            iv_duihuan.setVisibility(View.GONE);
        }else {
            String ticket_type = item.getTicket_type();
            if (ticket_type.equals("2")) {
                iv_duihuan.setVisibility(View.VISIBLE);
            } else {
                iv_duihuan.setVisibility(View.GONE);
            }
        }

        helper.addOnClickListener(R.id.constrain);
        helper.addOnClickListener(R.id.iv_duihuan);
        helper.addOnClickListener(R.id.iv_zengsong);
    }

    private boolean isXianBtn;

    public void setXianBtn(boolean xianBtn) {
        isXianBtn = xianBtn;
        notifyDataSetChanged();
    }
}
