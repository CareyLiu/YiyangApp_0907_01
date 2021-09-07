package com.yiyang.cn.adapter;

import android.content.Context;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.model.LoginUser;

/**
 * Created by Sl on 2019/6/12.
 *
 */

public class RuleListAdapter extends ListBaseAdapter<LoginUser.DataBean> {
    public RuleListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_login_rules;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        TextView tv_rule = holder.getView(R.id.tv_rule);

        tv_rule.setText(getDataList().get(position).getPower_state_name());

    }
}
