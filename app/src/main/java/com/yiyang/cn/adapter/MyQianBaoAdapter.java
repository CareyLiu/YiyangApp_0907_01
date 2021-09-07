package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.MyQianBaoXianFeiMingXiModel;

import java.util.List;

public class MyQianBaoAdapter extends BaseQuickAdapter<MyQianBaoXianFeiMingXiModel.DataBean.CunsumerListBean, BaseViewHolder> {


    public MyQianBaoAdapter(int layoutResId, @Nullable List<MyQianBaoXianFeiMingXiModel.DataBean.CunsumerListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyQianBaoXianFeiMingXiModel.DataBean.CunsumerListBean item) {

        helper.addOnClickListener(R.id.constrain);
        //	er_type	收支类型：1.收入 2.支出 3.提现9.退款
        ImageView civImg = helper.getView(R.id.civ_img);
        if (item.getEr_type().equals("1")) {
            civImg.setBackgroundResource(R.mipmap.mingxi_icon_tixian);
        } else if (item.getEr_type().equals("2")) {
            civImg.setBackgroundResource(R.mipmap.mingxi_icon_zhifu);
        } else if (item.getEr_type().equals("3")) {
            civImg.setBackgroundResource(R.mipmap.mingxi_icon_tixian);
        } else if (item.getEr_type().equals("9")) {
            civImg.setBackgroundResource(R.mipmap.mingxi_icon_tuikuan);
        }

        helper.setText(R.id.tv_ziaxian_pay, item.getPause_img_name());
        helper.setText(R.id.tv_date, item.getTime());
        helper.setText(R.id.tv_price, item.getMoney());

    }
}
