package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.model.ChangJingZhiXingModel;

import java.util.List;

//条件list
public class ChuangJianZhiNengTiaoJianListAdapter extends BaseQuickAdapter<ChangJingZhiXingModel, BaseViewHolder> {

    public ChuangJianZhiNengTiaoJianListAdapter(int layoutResId, @Nullable List<ChangJingZhiXingModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChangJingZhiXingModel item) {

    }
}
