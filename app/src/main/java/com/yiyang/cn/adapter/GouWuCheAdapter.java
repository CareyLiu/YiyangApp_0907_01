package com.yiyang.cn.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.GouWuCheDataModel;
import com.yiyang.cn.model.GouWuCheZhengHeModel;
import com.yiyang.cn.util.GlideShowImageUtils;

import java.util.List;

public class GouWuCheAdapter extends BaseSectionQuickAdapter<GouWuCheZhengHeModel, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public GouWuCheAdapter(int layoutResId, int sectionHeadResId, List<GouWuCheZhengHeModel> data) {
        super(R.layout.item_gouwuche_tiaomu, R.layout.item_gouwuche_header, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, GouWuCheZhengHeModel item) {

        helper.addOnClickListener(R.id.dianpu_select);

        Glide.with(mContext).setDefaultRequestOptions(GlideShowImageUtils.showZhengFangXing()).load(item.getInst_logo_url()).into((ImageView) helper.getView(R.id.shop_img));
        helper.setText(R.id.tv_dianpu_ming, item.getInst_name());//店铺名
        ImageView dianPuSelect = helper.getView(R.id.dianpu_select);
        if (item.getIsSelect().equals("0")) {
            dianPuSelect.setBackgroundResource(R.mipmap.quxiaodingdan_button_nor);
        } else {
            dianPuSelect.setBackgroundResource(R.mipmap.quxiaodingdan_button_sel);
        }

    }

    @Override
    protected void convert(BaseViewHolder helper, GouWuCheZhengHeModel item) {

        helper.addOnClickListener(R.id.iv_select_item);
        helper.addOnClickListener(R.id.cl_item);
        helper.addOnLongClickListener(R.id.cl_item);
        Glide.with(mContext).setDefaultRequestOptions(GlideShowImageUtils.showZhengFangXing()).load(item.getIndex_photo_url()).into((ImageView) helper.getView(R.id.iv_product));
        helper.setText(R.id.tv_guige_ming, item.getProduct_title());
        helper.setText(R.id.tv_shangpin_ming, item.getShop_product_title());
        helper.setText(R.id.tv_jine, "¥ " + item.getForm_product_money());
        helper.setText(R.id.tv_youji, "邮寄： ¥" + item.getWares_money_go());
        helper.setText(R.id.tv_shuliang, item.getPay_count());
        if (item.getWares_go_type().equals("2")) {

            helper.setVisible(R.id.tv_youji, true);
        } else if (item.getWares_go_type().equals("3")) {
            helper.setVisible(R.id.tv_youji, false);

        }
        helper.addOnClickListener(R.id.tv_jiahao);
        helper.addOnClickListener(R.id.tv_jianhao);

        if (item.getBottomYuanJiao().equals("1")) {
            ConstraintLayout cl_item = helper.getView(R.id.cl_item);

            cl_item.setBackgroundResource(R.drawable.item_yuanjiao_bottom_zuo_you);
        } else {
            ConstraintLayout cl_item = helper.getView(R.id.cl_item);

            cl_item.setBackgroundResource(R.color.white);
        }
        ImageView ivSelectItem = helper.getView(R.id.iv_select_item);


        //添加失效商品
        if (item.getForm_product_state().equals("2")) {//2 失效 1 正常
            ivSelectItem.setBackgroundResource(R.mipmap.gouwuche_shixiao);
        } else if (item.getForm_product_state().equals("1")) {
            if (item.getIsSelect().equals("0")) {
                ivSelectItem.setBackgroundResource(R.mipmap.quxiaodingdan_button_nor);
            } else {
                ivSelectItem.setBackgroundResource(R.mipmap.quxiaodingdan_button_sel);
            }

        }


    }
}
