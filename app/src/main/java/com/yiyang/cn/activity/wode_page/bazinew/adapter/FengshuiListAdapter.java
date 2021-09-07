package com.yiyang.cn.activity.wode_page.bazinew.adapter;

import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

public class FengshuiListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public FengshuiListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
