package com.yiyang.cn.adapter.xin_tuanyou;

import androidx.annotation.Nullable;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.xin_tuanyou.TuanYouList;
import com.yiyang.cn.activity.xin_tuanyou.YouZhanDetailsActivity;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;

import java.util.List;

//多少千米
public class DetailsJiaYouJinEAdapter extends BaseQuickAdapter<YouZhanDetailsActivity.MyModel, BaseViewHolder> {


    public DetailsJiaYouJinEAdapter(int layoutResId, @Nullable List<YouZhanDetailsActivity.MyModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, YouZhanDetailsActivity.MyModel item) {
        if (item.isSelect.equals("0")) {
            helper.setBackgroundRes(R.id.tv_text, R.drawable.item_youzhan_details_back);

        } else if (item.isSelect.equals("1")) {
            helper.setBackgroundRes(R.id.tv_text, R.drawable.item_youzhan_details_pink);
        }

        helper.setText(R.id.tv_text, item.a);
        helper.addOnClickListener(R.id.constrain);
    }
}
