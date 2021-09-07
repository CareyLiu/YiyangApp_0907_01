package com.yiyang.cn.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.GouWuCheZhengHeModel;
import com.yiyang.cn.util.GlideShowImageUtils;

import java.util.List;

public class GouWuCheQueRenDingDanAdapter extends BaseSectionQuickAdapter<GouWuCheZhengHeModel, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GouWuCheQueRenDingDanAdapter(List<GouWuCheZhengHeModel> data) {
        super(R.layout.item_querendingdan_tiaomu, R.layout.item_querendingdan_header, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, GouWuCheZhengHeModel item) {
        Glide.with(mContext).setDefaultRequestOptions(GlideShowImageUtils.showZhengFangXing()).load(item.getInst_logo_url()).into((ImageView) helper.getView(R.id.shop_img));
        helper.setText(R.id.tv_dianpu_ming, item.getInst_name());//店铺名
        ImageView dianPuSelect = helper.getView(R.id.dianpu_select);
    }

    @Override
    protected void convert(BaseViewHolder helper, GouWuCheZhengHeModel item) {
        Glide.with(mContext).setDefaultRequestOptions(GlideShowImageUtils.showZhengFangXing()).load(item.getIndex_photo_url()).into((ImageView) helper.getView(R.id.iv_product));
        helper.setText(R.id.tv_guige_ming, item.getProduct_title());
        helper.setText(R.id.tv_shangpin_ming, item.getShop_product_title());
        helper.setText(R.id.tv_jine, "¥ " + item.getForm_product_money());
        helper.setText(R.id.tv_youji, "邮寄： ¥" + item.getWares_money_go());
        helper.setText(R.id.tv_shuliang, "×" + item.getPay_count());
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
            helper.setVisible(R.id.rl_1, true);
            ConstraintLayout cl_item1 = helper.getView(R.id.rl_1);
            cl_item1.setBackgroundResource(R.drawable.item_yuanjiao_bottom_zuo_you);

            EditText editText = helper.getView(R.id.et_liuyan);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    item.setLiuyan(editText.getText().toString());
                }
            });
        } else {
            ConstraintLayout cl_item = helper.getView(R.id.cl_item);
            cl_item.setBackgroundResource(R.color.white);
            helper.setVisible(R.id.rl_1, false);
            ConstraintLayout cl_item1 = helper.getView(R.id.rl_1);
            ViewGroup.LayoutParams layoutParams = cl_item1.getLayoutParams();
            cl_item1.setVisibility(View.GONE);
            layoutParams.height = 0;
            layoutParams.width = 0;
            cl_item1.setLayoutParams(layoutParams);
        }
    }
}
