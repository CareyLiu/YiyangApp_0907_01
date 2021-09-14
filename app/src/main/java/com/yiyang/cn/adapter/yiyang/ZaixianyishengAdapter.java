package com.yiyang.cn.adapter.yiyang;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.yiyang.XiaoxiModel;

import java.util.List;

import androidx.annotation.Nullable;

public class ZaixianyishengAdapter extends BaseQuickAdapter<XiaoxiModel, BaseViewHolder> {
    public ZaixianyishengAdapter(int layoutResId, @Nullable List<XiaoxiModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XiaoxiModel item) {

    }
}
