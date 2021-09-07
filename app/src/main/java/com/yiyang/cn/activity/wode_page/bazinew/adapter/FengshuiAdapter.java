package com.yiyang.cn.activity.wode_page.bazinew.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.model.FengshuiModel;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

public class FengshuiAdapter extends BaseQuickAdapter<FengshuiModel.DataBean, BaseViewHolder> {
    public FengshuiAdapter(int layoutResId, @Nullable List<FengshuiModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FengshuiModel.DataBean item) {
        String mingpan_user = item.getMingpan_user();
        if ("1".equals(mingpan_user)) {
            helper.getView(R.id.tv_moren).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_moren).setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_baijian, "摆件：" + item.getGoods_name());
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_sex, item.getSex_text());
        helper.setText(R.id.tv_sex, item.getSex_text());
        helper.setText(R.id.tv_sex, item.getSex_text());
        helper.setText(R.id.tv_yangli, "阳历：" + item.getSolar_birthday());
        helper.setText(R.id.tv_yinli, "阴历：" + item.getLunar_birthday());

        Glide.with(mContext).load(item.getGoods_img()).into((ImageView) helper.getView(R.id.iv_baijian));
        helper.addOnClickListener(R.id.iv_baijian);
    }
}
