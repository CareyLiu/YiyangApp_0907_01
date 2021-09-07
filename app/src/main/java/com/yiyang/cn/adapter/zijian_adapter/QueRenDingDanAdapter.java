package com.yiyang.cn.adapter.zijian_adapter;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.app.App;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;

import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.model.GoodsDetails_f;

import java.util.List;

public class QueRenDingDanAdapter extends BaseQuickAdapter<GoodsDetails_f.DataBean.ProductListBean, BaseViewHolder> {
    public QueRenDingDanAdapter(int layoutResId, @Nullable List<GoodsDetails_f.DataBean.ProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsDetails_f.DataBean.ProductListBean item) {


        Glide.with(mContext).load(item.getIndex_photo_url()).into((ImageView) helper.getView(R.id.iv_product));
        helper.setText(R.id.tv_title, item.getProduct_biaoti());
        helper.setText(R.id.tv_xinghao, item.getProduct_title());//型号
        helper.setText(R.id.tv_danjia, item.getMoney_now());//价格
        helper.setText(R.id.tv_number, "x" + item.getCount());
        //  helper.setText(R.id.tv_pay, "实付" + item.getPay_money() + "（含运费" + item.getForm_money_go() + ")");


        String kuaidi = PreferenceHelper.getInstance(mContext).getString(App.KUAIDITYPE, "2");
        String kuaidiFei = PreferenceHelper.getInstance(mContext).getString(App.KUAIDIFEI, "0.00");
        //     消费方式：1.邮递/到店
        //     2.邮递 3.到店
        if (kuaidi.equals("3")) {
            helper.setText(R.id.tv_kuaidi, "到店");
            // tvKuaidi.setText("到店" + dataBean.getWares_money_go());
        } else {
            helper.setText(R.id.tv_kuaidi, "邮递" + " " + kuaidiFei);
        }


        helper.addOnClickListener(R.id.tv_kuaidi);
    }


}
