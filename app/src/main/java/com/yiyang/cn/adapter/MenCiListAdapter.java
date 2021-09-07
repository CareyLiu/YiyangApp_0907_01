package com.yiyang.cn.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.yiyang.cn.R;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseSectionQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.AlarmListBean;
import com.yiyang.cn.model.MenCiListModel;

import java.util.List;

public class MenCiListAdapter extends BaseSectionQuickAdapter<AlarmListBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public MenCiListAdapter(int layoutResId, int sectionHeadResId, List<AlarmListBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, AlarmListBean item) {

        helper.setText(R.id.tv_riqi, item.alarm_date);

        if (item.is_more.equals("1")) {
            helper.setVisible(R.id.rrl_gengduo, true);
        } else {
            helper.setVisible(R.id.rrl_gengduo, false);
        }

        helper.addOnClickListener(R.id.rrl_gengduo);
    }

    @Override
    protected void convert(BaseViewHolder helper, AlarmListBean item) {
        helper.setText(R.id.tv_shijianduan, item.alerm_time);
        helper.setText(R.id.tv_menchuangzhuangtai, item.device_state_name);
    }
}
