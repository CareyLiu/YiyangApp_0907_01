package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.db.table.HistoryRecord;

import java.util.List;

/**
 * Created by dell on 2017/5/17.
 */
public class Custom5HistoryAdapter extends BaseQuickAdapter<HistoryRecord, BaseViewHolder> {
    private static final String tag = Custom5HistoryAdapter.class.getSimpleName();

    public Custom5HistoryAdapter(@Nullable List<HistoryRecord> data) {
        super(R.layout.custom3_hot_search_list_item1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryRecord item) {

        helper.addOnClickListener(R.id.iv_delete);
        helper.addOnClickListener(R.id.rl_layout);
        //历史搜索
        helper.setText(R.id.tv_text, item.getName());
    }
}
