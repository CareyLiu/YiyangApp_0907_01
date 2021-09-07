package com.yiyang.cn.adapter.dingdan;

import androidx.annotation.Nullable;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.yiyang.cn.model.OrderListModel;

import java.util.List;

public class OrderListAdapter extends BaseQuickAdapter<OrderListModel.DataBean, BaseViewHolder> {

    public OrderListAdapter(int layoutResId, @Nullable List<OrderListModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListModel.DataBean item) {
        Glide.with(mContext).load(item.getInst_img_url()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_shop_name, item.getInst_name());
        Glide.with(mContext).load(item.getIndex_photo_url()).into((ImageView) helper.getView(R.id.iv_product));
        helper.setText(R.id.tv_title, item.getShop_product_title());
        helper.setText(R.id.tv_xinghao, item.getProduct_title());//型号
        helper.setText(R.id.tv_danjia, item.getForm_product_money());//价格
        helper.setText(R.id.tv_number, "x" + item.getPay_count());
        if (item.getWares_go_type().equals("2")) {
            helper.setText(R.id.tv_pay, "实付" + item.getPay_money() + "（含运费" + item.getForm_money_go() + ")");
        } else {
            helper.setText(R.id.tv_pay, "实付" + item.getPay_money());
        }

        helper.setVisible(R.id.tv_caozuo, true);
        helper.setVisible(R.id.tv_caozuo1, true);
        helper.setVisible(R.id.tv_caozuo2, false);
        helper.addOnClickListener(R.id.tv_caozuo2);


        helper.addOnClickListener(R.id.constrain);

        /**
         * user_pay_check	买家状态:1.待付款 2.待分享(拼)3.待发货 4.已发货 5.到店消费6.待评价 7.完成 8.退款申请 9.退款中 10.退款/退货 11 订单失效
         */
        switch (item.getUser_pay_check()) {
            case "1":
                helper.setText(R.id.tv_yipingjia, "待付款");

                helper.setText(R.id.tv_caozuo, "去支付");
                helper.setText(R.id.tv_caozuo1, "取消订单");

                break;
            case "2":
                helper.setText(R.id.tv_yipingjia, "待分享");

                helper.setText(R.id.tv_caozuo, "申请退款");
                helper.setText(R.id.tv_caozuo1, "删除订单");

                break;
            case "3":
                helper.setText(R.id.tv_yipingjia, "待发货");

                helper.setText(R.id.tv_caozuo1, "催发货");
                helper.setText(R.id.tv_caozuo, "申请退款");
                break;
            case "4":
                helper.setText(R.id.tv_yipingjia, "已发货");

                helper.setText(R.id.tv_caozuo, "确认收货");
                helper.setText(R.id.tv_caozuo1, "查看物流");
                helper.setVisible(R.id.tv_caozuo2, true);
                helper.setText(R.id.tv_caozuo2, "申请退款");

                break;
            case "5":
                helper.setText(R.id.tv_yipingjia, "到店消费");

                helper.setText(R.id.tv_caozuo1, "申请退款");
                helper.setVisible(R.id.tv_caozuo1, true);
                helper.setVisible(R.id.tv_caozuo, true);
                helper.setText(R.id.tv_caozuo, "联系卖家");

                //helper.setText(R.id.tv_caozuo2, "联系买家");
                break;
            case "6":
                helper.setText(R.id.tv_yipingjia, "待评价");
/**
 * 消费方式：2.邮递3.到店4.直接下单
 */
                switch (item.getWares_go_type()) {
                    case "2":
                    case "3":
                    case "4":
                        helper.setText(R.id.tv_caozuo, "删除订单");
                        helper.setText(R.id.tv_caozuo1, "去评价");
                        break;
                }
                break;
            case "7":
                helper.setText(R.id.tv_yipingjia, "完成");

                helper.setText(R.id.tv_caozuo, "申请退款");
                helper.setVisible(R.id.tv_caozuo, false);
                helper.setText(R.id.tv_caozuo1, "删除订单");

                break;
            case "8":
            case "9":
                helper.setText(R.id.tv_yipingjia, "退款中");
                helper.setText(R.id.tv_caozuo, "申请退款");
                helper.setVisible(R.id.tv_caozuo, false);
                helper.setText(R.id.tv_caozuo1, "再次购买");

                break;

            case "10":
                helper.setText(R.id.tv_yipingjia, "退款/退货");

                helper.setText(R.id.tv_caozuo1, "再次购买");
                helper.setText(R.id.tv_caozuo, "删除订单");

                break;
            case "11":
                helper.setText(R.id.tv_yipingjia, "订单失效");

                helper.setText(R.id.tv_caozuo1, "删除订单");
                //helper.setText(R.id.tv_caozuo1, "删除订单");
                //helper.setText(R.id.tv_caozuo2, "联系买家");
                helper.setVisible(R.id.tv_caozuo, false);

                break;

        }

        helper.addOnClickListener(R.id.tv_caozuo);
        helper.addOnClickListener(R.id.tv_caozuo1);

    }
}
